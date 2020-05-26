package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.controller.exceptions.ItemNotFoundException;
import krados.oose.assignment.model.Enemy;
import krados.oose.assignment.model.Player;
import krados.oose.assignment.model.ShopItem;
import krados.oose.assignment.view.ShopView;
import krados.oose.assignment.view.View;

import java.util.LinkedList;

public class ShopController {
    private LinkedList<ShopItem> shopInventory; //TODO could this be placed elsewhere to make all controllers static?

    //CONSTRUCTOR
    public ShopController() {
        shopInventory = new LinkedList<ShopItem>();
    }

    public Player openShop(Player p) {
        int cmd = -1;
        do {
            try {
                ShopView.playerAttributes(p);
                ShopView.displayOptions(shopInventory);
                cmd = View.selectOption();

                switch (cmd) {
                    case 1: //Sell Item
                        ShopView.sellItemPrompt();
                        cmd = View.selectOption();
                        sellItem(p, cmd);
                        break;

                    case 2: //Buy Item
                        System.out.println("Buy Item");
                        break;

                    case 3: //Enchant Weapon
                        System.out.println("Enchant Weapon");
                        //add cost of enchantment to weapon cost
                        break;

                    default:
                        if (cmd != 0) {
                            View.inputBoundsError(0, 3);
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
        return p;
    }

    //PRIVATE
    private static void sellItem(Player p, int cmd) throws ItemNotFoundException, InputErrorException {
        int index = cmd - 3;
        if (cmd < 1) {
            throw new ItemNotFoundException("Item " + cmd + " could not be found in player inventory");
        }
        else if (cmd == 1 || cmd == 2) {
            throw new InputErrorException("Equipped items can not be sold to the shop");
        }
        else if (cmd > Player.INVENTORY_SIZE) {
            throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
        }
        else {
            int value = p.sellItem(cmd - 3); //cmd is based on UI inventory list, where sellable items start at 3
            View.balanceChange(value);
        }
    }

    //private Updater updater; //TODO implement shop updating
    //TODO check values are != when loading from file or even somewhere higher up
}
