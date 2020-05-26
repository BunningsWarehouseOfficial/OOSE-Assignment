package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.FullInventoryException;
import krados.oose.assignment.controller.exceptions.ItemNotFoundException;
import krados.oose.assignment.model.Armour;
import krados.oose.assignment.model.Enemy;
import krados.oose.assignment.model.Player;
import krados.oose.assignment.model.Weapon;

public class GameEngine {
    public static void main(String[] args) {
        //TODO just stuff everything into here for now and 'methodise' later
        //TODO consider changing various methods from public to protected/package-private LOW PRIORITY
        //TODO don't forget to test inventory/ui stuff with potions, as I didn't create any potions in initial testing

        // temp
        Player player = new Player(); //Default player
        try {
            player.equipWeapon(new Weapon("Excalibur", 20, "Sword", "slashing", 6, 12));
            player.equipArmour(new Armour("Adamantium Chestplate", 30, "Adamantium", 10, 14));
            player.addArmour(new Armour("Titanium Chestplate", 25, "Titanium", 9, 12));
            player.addWeapon(new Weapon("Excalibur 2", 40, "Longsword", "slashing", 9, 15));
        }
        catch (ItemNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (FullInventoryException e) {
            System.out.println("Error: " + e.getMessage() + " - max inventory size is " + Player.INVENTORY_SIZE);
        }
        // end temp
        ShopController shop = new ShopController();

        MenuController.menu(player, shop);
    }

    private void injectDependencies() { //TODO could just be in main() ?

    }

    private void centralFactory() { //TODO could just be in main() ?

    }
}
