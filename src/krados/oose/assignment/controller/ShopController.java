package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.*;
import krados.oose.assignment.model.*;
import krados.oose.assignment.view.ShopView;
import krados.oose.assignment.view.View;

import java.util.LinkedList;

public class ShopController { //TODO final: make package-private if still an option
    private LinkedList<ShopItem> shopInventory; //TODO could this be placed elsewhere to make all controllers static?
    private int[] enchantmentCosts;

    //CONSTRUCTOR
    public ShopController() {
        shopInventory = new LinkedList<>();

        //The costs of the enchantments for sale in the order they are shown in UI
        enchantmentCosts = new int[] {
            Damage2.COST,
            Damage5.COST,
            FireDamage.COST,
            PowerUp.COST
        };
    }

    //TODO shop inventory that's separate to controller?
    public void addItem(ShopItem item) {
        shopInventory.addLast(item);
    }

    public Player openShop(Player p) {
        int cmd = -1;
        do {
            try {
                ShopView.playerAttributes(p);
                ShopView.displayOptions(shopInventory, enchantmentCosts);
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
                        View.inputNumberPrompt("enchantment", "buy");
                        cmd = View.selectOption();
                        View.inputNumberPrompt("weapon from your inventory", "enchant");
                        int cmdTarget = View.selectOption();

                        enchantWeapon(p, cmd, cmdTarget);
                        break;

                    default:
                        if (cmd != 0) {
                            View.inputBoundsError(0, 3);
                        }
                        break;
                }
            }
            catch (InputErrorException ex) {
                View.inputError(ex);
                cmd = -1; //Reset the cmd value to prevent accidental early exit from submenu
            }
            catch (ItemException ex) { //bug got exception when enchanting non-equipped item (#3)
                View.itemError(ex);
            }
        } while (cmd != 0); //TODO retest all the UIs after Model rework
        return p;
    }

    //PRIVATE
    private void sellItem(Player p, int cmd) throws ItemException, InputErrorException {
        try {
            if (cmd < 1) {
                throw new InputErrorException("Input must be > 0");
            }
            else if (cmd == 1 || cmd == 2) {
                throw new InputErrorException("Equipped items can not be sold to the shop");
            }
            else if (cmd > Player.INVENTORY_SIZE) {
                throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
            }

            int index = cmd - 3; //Must account for 2 equipped items being shown first, as first item, 3, has index 0
            int value = p.sellItem(index);
            View.balanceChange(value);
        }
        catch (ItemNotFoundException ex) {
            throw new ItemException("Couldn't sell item " + cmd + " (" + ex.getMessage() + ")", ex);
        }
    }

    private void buyItem(Player p, int cmd) throws ItemException, InputErrorException {
        try {
            ShopItem buying = shopInventory.get(cmd - 1);
            int cost = buying.getCost();

            if (p.getGold() >= cost) { //Check that the player has enough gold
                buying.givePlayer(p); //Give the item to the player
                p.setGold(p.getGold() - cost);
                View.balanceChange(-cost);
            }
            else {
                throw new ItemException("Player can not afford to purchase the item, " + (cost - p.getGold()) +
                                        " more gold is required");
            }
        }
        catch (FullInventoryException ex) {
            throw new ItemException("There was a problem purchasing the item (" + ex.getMessage() + ")", ex);
        }
        catch (IndexOutOfBoundsException ex) {
            throw new InputErrorException("Input must be > 0 and <= " + shopInventory.size(), ex);
        }
    }

    private void enchantWeapon(Player p, int cmd, int cmdTarget) throws ItemException, InputErrorException {
        try {
            int numWeapons = p.getNumWeapons();

            if (cmdTarget < 1) {
                throw new InputErrorException("Second input must be > 0");
            }
            else if (cmdTarget == 2 || (cmdTarget > numWeapons + 2 && cmdTarget <= p.getNumItems())) { //Check if weapon
                throw new InputErrorException("Item " + cmdTarget + " is not a weapon");
            }
            else if (cmdTarget > Player.INVENTORY_SIZE) {
                throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
            }

            int cost = enchantmentCosts[cmd - 1];
            if (p.getGold() >= cost) { //Check that the player has enough gold
                Weapon target;
                if (cmdTarget == 1) {
                    target = p.getEquippedWeapon();
                }
                else {
                    target = p.getWeapon(cmdTarget - 3); //Need to account for 2 equipped items and need an index
                    p.removeWeapon(target); //Removing original weapon from inventory
                }

                switch (cmd) { //Wrapping original weapon with the decoration
                    case 1: //First listed enchantment
                        target = new Damage2(target);
                        break;

                    case 2: //Second listed enchantment
                        target = new Damage5(target);
                        break;

                    case 3: //Third listed enchantment
                        target = new FireDamage(target);
                        break;

                    case 4: //Fourth listed enchantment
                        target = new PowerUp(target);
                        break;
                }
                if (cmdTarget == 1) {
                    p.equipWeapon(target); //Re-equip the now decorated weapon
                }
                else {
                    p.addWeapon(target); //Re-adding decorated weapon to inventory
                }

                p.setGold(p.getGold() - cost);
                View.balanceChange(-cost);
            }
            else {
                throw new ItemException("Player can not afford to purchase the item, " + (cost - p.getGold()) +
                                        " more gold is required");
            }
        }
        catch (IndexOutOfBoundsException ex) {
            throw new InputErrorException("First input must be > 0 and <= " + shopInventory.size(), ex);
        }
    }

    //private Updater updater; //TODO implement shop updating
    //TODO check values are != when loading from file or even somewhere higher up
}
