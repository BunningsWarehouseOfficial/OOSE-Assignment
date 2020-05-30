package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.FullInventoryException;
import krados.oose.assignment.controller.exceptions.ItemNotFoundException;
import krados.oose.assignment.model.*;

public class GameEngine {
    public static void main(String[] args) {
        //TODO just stuff everything into here for now and 'methodise' later
        //TODO consider changing various methods from public to protected/package-private LOW PRIORITY
        //TODO don't forget to test inventory/ui stuff with potions, as I didn't create any potions in initial testing

        //TODO final: remove unused anythings where applicable
        //TODO final: make another git branch and remove all left over TODOs on there only for the assignment submission

        // temp
        Player player = new Player(); //Default player
        try {
            player.equipWeapon(new WeaponItem("Excalibur", 20, "Sword", "slashing", 50, 50));
            player.equipArmour(new Armour("Astra Leggings", 30, "Adamantium", 50, 50));
            player.addArmour(new Armour("Titanium Chestplate", 25, "Titanium", 9, 12));
            player.addWeapon(new WeaponItem("Excalibur 2", 40, "Longsword", "slashing", 9, 15));
            ShopItem pot = new Potion("Fire Potion", 10, false, 4, 6);
            pot.givePlayer(player);
        }
        catch (ItemNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        catch (FullInventoryException ex) {
            System.out.println("Error: " + ex.getMessage() + " - max inventory size is " + Player.INVENTORY_SIZE);
        }
        // end temp
        ShopController shop = new ShopController();
        shop.addItem(new WeaponItem("Yeetblade", 15, "Blade", "stabbing", 2, 12));
        shop.addItem(new Armour("Cactus Pants", 5, "Cactus", 0, 8));
        shop.addItem(new Potion("Gamer Juice", 10, true, 12, 16));

        MenuController.menu(player, shop);
    }

    private void injectDependencies() { //TODO could just be in main() ?

    }

    private void centralFactory() { //TODO could just be in main() ?

    }
}
