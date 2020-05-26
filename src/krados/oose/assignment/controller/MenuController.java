package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.controller.exceptions.ItemNotFoundException;
import krados.oose.assignment.model.*;
import krados.oose.assignment.view.MenuView;
import krados.oose.assignment.view.View;

import java.util.LinkedList;

//TODO remove inventory planning/template
//@ 1. Excalibur (6.0 - 12.0 slashing damage)
//     {$} 20 gold   |   [Type: Sword]

//@ 2. Leather Armour (5 - 15 defence)
//     {$} 10 gold   |   [Material: Leather]

//  3. Explosive Potion (20.0 - 20.0 damage)
//     {$} 20 gold

public class MenuController {
    public static void menu(Player p, ShopController shop) {
        int cmd = -1;
        do {
            try {
                View.playerAttributes(p);
                MenuView.displayOptions();
                cmd = View.selectOption();

                switch (cmd) {
                    case 1: //Shop
                        p = shop.openShop(p);
                        break;

                    case 2: //Change Character Name
                        changeName(p);
                        break;

                    case 3: //Choose Weapon
                        MenuView.changeWeaponPrompt();
                        cmd = View.selectOption();
                        chooseWeapon(p, cmd);
                        break;

                    case 4: //Choose Armour
                        MenuView.changeArmourPrompt();
                        cmd = View.selectOption();
                        chooseArmour(p, cmd);
                        break;

                    case 5: //Start Battle

                        break;

                    default:
                        if (cmd != 0) {
                            View.inputBoundsError(0, 5);
                        }
                        break;
                }
            }
            catch (InputErrorException e) {
                View.inputError(e);
            }
            catch (ItemNotFoundException e) {
                View.itemError(e);
                cmd = -1; //Reset the cmd value to prevent accidental early exit of program from submenu
            }
        } while (cmd != 0);
    }

    //PRIVATE METHODS
    private static void changeName(Player p) throws InputErrorException {
        String name = MenuView.changeName();
        if (name.replaceAll("\\s","").length() < 1) {
            throw new InputErrorException("Player name must contain at least 1 non-space character");
        }
        name = name.trim(); //Trim leading and trailing whitespace to keep a clean UI
        p.setName(name);
    }

    private static void chooseWeapon(Player p, int cmd) throws ItemNotFoundException, InputErrorException {
        int index;
        int invWeaponsSize = p.getInvWeapons().size();
        int invArmoursSize = p.getInvArmours().size();

        //Retrieve a weapon from player inventory using the item number as shown in UI
        if (cmd == 1) {
            throw new InputErrorException("Item " + cmd + " is already equipped");
        }
        else if (cmd == 2 || (cmd > invWeaponsSize + 2 && cmd <= invWeaponsSize + invArmoursSize + 2)) {
            throw new InputErrorException("Item " + cmd + " is not a weapon");
        }
        else if (cmd > 2 && cmd <= invWeaponsSize + 2) {
            index = cmd - 3; //Must account for 2 equipped items being shown first, as first weapon, 3, has index 0
        }
        else if (cmd > Player.INVENTORY_SIZE) {
            throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
        }
        else {
            throw new ItemNotFoundException("Item " + cmd + " could not be found in player inventory");
        }
        Weapon selected = p.getWeapon(index);

        p.equipWeapon(selected);
    }

    private static void chooseArmour(Player p, int cmd) throws ItemNotFoundException, InputErrorException {
        int index;
        int invWeaponsSize = p.getInvWeapons().size();
        int invArmoursSize = p.getInvArmours().size();

        //Retrieve armour from player inventory using the item number as shown in UI
        if (cmd == 2) {
            throw new InputErrorException("Item " + cmd + " is already equipped");
        }
        else if (cmd == 1 || (cmd > 2 && cmd <= invWeaponsSize + 2)) {
            throw new InputErrorException("Item " + cmd + " is not armour");
        }
        else if (cmd > invWeaponsSize + 2 && cmd <= invWeaponsSize + 2 + invArmoursSize) {
            index = cmd - 3 - invWeaponsSize; //Must account for 2 equipped items and all the weapons being shown first
        }
        else if (cmd > Player.INVENTORY_SIZE) {
            throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
        }
        else {
            throw new ItemNotFoundException("Item " + cmd + " could not be found in player inventory");
        }
        Armour selected = p.getArmour(index);

        p.equipArmour(selected);
    }
}
