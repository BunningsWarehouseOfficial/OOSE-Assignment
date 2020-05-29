package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.controller.exceptions.ItemException;
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
                        View.inputNumberPrompt("weapon", "equip");
                        cmd = View.selectOption();
                        chooseWeapon(p, cmd);
                        break;

                    case 4: //Choose Armour
                        View.inputNumberPrompt("armour", "equip");
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
                cmd = -1; //Reset the cmd value to prevent accidental early exit of program
            }
            catch (ItemException e) { //TODO make ItemException
                View.itemError(e);
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

    private static void chooseWeapon(Player p, int cmd) throws ItemException, InputErrorException {
        int numWeapons = p.getNumWeapons();

        //Retrieve a weapon from player inventory using the item number as shown in UI
        try {
            if (cmd < 1) {
                throw new InputErrorException("Input must be > 0");
            }
            else if (cmd == 1) { //Checking if user chose equipped weapon
                throw new InputErrorException("Item " + cmd + " is already equipped");
            }
            else if (cmd == 2 || (cmd > numWeapons + 2 && cmd <= p.getNumItems())) { //Checking if user chose non-weapon
                throw new InputErrorException("Item " + cmd + " is not a weapon");
            }
            else if (cmd > Player.INVENTORY_SIZE) {
                throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
            }

            int index = cmd - 3; //Must account for 2 equipped items being shown first, as first weapon, 3, has index 0
            Weapon selected = p.getWeapon(index);
            p.equipWeapon(selected);
        }
        catch (ItemNotFoundException e) {
            throw new ItemException("Could not equip the item (" + e.getMessage() + ")", e);
        }
    }

    private static void chooseArmour(Player p, int cmd) throws ItemException, InputErrorException {
        int numWeapons = p.getNumWeapons();
        int numArmours = p.getNumArmours();

        //Retrieve armour from player inventory using the item number as shown in UI
        try {
            if (cmd < 1) {
                throw new InputErrorException("Input must be > 0");
            }
            else if (cmd == 2) { //Checking if user chose equipped armour
                throw new InputErrorException("Item " + cmd + " is already equipped");
            }
            else if (cmd == 1 || cmd <= numWeapons + 2) { //Checking if user chose a weapon
                throw new InputErrorException("Item " + cmd + " is not armour");
            }
            else if (cmd > numWeapons + numArmours + 2 && cmd <= p.getNumItems()) { //Checking if user chose a potion
                throw new InputErrorException("Item " + cmd + " is not armour");
            }
            else if (cmd > Player.INVENTORY_SIZE) {
                throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
            }

            int index = cmd - 3 - numWeapons; //Must account for 2 equipped items and all the weapons being shown first
            Armour selected = p.getArmour(index);
            p.equipArmour(selected);
        }
        catch (ItemNotFoundException e) {
            throw new ItemException("Could not equip the item (" + e.getMessage() + ")", e);
        }
    }
}
