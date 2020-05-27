package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.FullInventoryException;
import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.controller.exceptions.ItemException;
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

    //MUTATORS //TODO shop inventory that's separate to controller?
    public void addItem(ShopItem item) {
        shopInventory.addLast(item);
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
                        View.inputNumberPrompt("item", "sell");
                        cmd = View.selectOption();
                        sellItem(p, cmd);
                        break;

                    case 2: //Buy Item
                        View.inputNumberPrompt("item", "buy");
                        cmd = View.selectOption();
                        buyItem(p, cmd);
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
                cmd = -1; //Reset the cmd value to prevent accidental early exit from submenu
            }
            catch (ItemException e) {
                View.itemError(e);
            }
        } while (cmd != 0);
        return p;
    }

    //PRIVATE
    private void sellItem(Player p, int cmd) throws ItemException, InputErrorException {
        try { //BUG After selling 4, could sell the now 3 by typing 4 <---------------------------- !!!!!!!!!!!!!!
              // Also still haven't tested buying an item btw lol
            if (cmd < 1) {
                throw new InputErrorException("Input must be > 0");
            }
            else if (cmd == 1 || cmd == 2) {
                throw new InputErrorException("Equipped items can not be sold to the shop");
            }
            else if (cmd > Player.INVENTORY_SIZE) {
                throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
            }

            //cmd is based on UI inventory list, but an index is required and sellable items start at 3
            int value = p.sellItem(cmd - 3);
            View.balanceChange(value);
        }
        catch (ItemNotFoundException e) {
            throw new ItemException("Couldn't sell item " + cmd + " (" + e.getMessage() + ")", e);
        }
    }

    private void buyItem(Player p, int cmd) throws ItemException {
        ShopItem bought = shopInventory.get(cmd);
        try {
            bought.givePlayer(p);
            View.balanceChange(-bought.getCost());
        }
        catch (FullInventoryException e) {
            throw new ItemException("There was a problem purchasing the item (" + e.getMessage() + ")", e);
        }
    }

    //private Updater updater; //TODO implement shop updating
    //TODO check values are != when loading from file or even somewhere higher up
}
