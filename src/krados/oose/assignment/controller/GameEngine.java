package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.ItemNotFoundException;
import krados.oose.assignment.controller.exceptions.UpdaterException;
import krados.oose.assignment.model.*;
import krados.oose.assignment.view.View;

import java.util.LinkedList;

public class GameEngine {
    public static void main(String[] args) {
        //TODO final: put comments everywhere
        //TODO final: make another git branch and remove all left over TODOs on there only for the assignment submission

        Player player = new Player(); //Default player
        ShopController shop = new ShopController();
        ShopUpdater updater = new FileShopUpdater();
        try {
            //Updating the shop inventory with the initial items (in this case, from a text input file)
            LinkedList<ShopItem> shopInventory = updater.update();
            shop.setInventory(updater.update());

            equipCheapestItems(player, shopInventory);
            MenuController.menu(player, shop);
        }
        catch (UpdaterException ex) {
            View.updaterError(ex);
        }
        catch (ItemNotFoundException ex) {
            View.itemError(ex);
        }
    }

    public static void equipCheapestItems(Player player, LinkedList<ShopItem> inventory) throws ItemNotFoundException {
        //Finding and equipping the cheapest weapon
        Weapon cheapestWeapon = null;
        for (ShopItem item : inventory) { //Searching for the cheapest weapon
            if (item instanceof Weapon) {
                if (cheapestWeapon == null) { //Finding the first candidate
                    cheapestWeapon = (Weapon)item;
                }
                else if (item.getCost() < cheapestWeapon.getCost()) { //Finding consecutive cheaper candidates
                    cheapestWeapon = (Weapon)item;
                }
            }
        }
        player.equipWeapon(cheapestWeapon);

        //Finding and equipping the cheapest armour
        Armour cheapestArmour = null;
        for (ShopItem item : inventory) { //Searching for the cheapest armour
            if (item instanceof Armour) {
                if (cheapestArmour == null) { //Finding the first candidate
                    cheapestArmour = (Armour)item;
                }
                else if (item.getCost() < cheapestArmour.getCost()) { //Finding consecutive cheaper candidates
                    cheapestArmour = (Armour)item;
                }
            }
        }
        player.equipArmour(cheapestArmour);
    }
}
