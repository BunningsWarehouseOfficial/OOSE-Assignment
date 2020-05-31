package krados.oose.assignment.view;

import krados.oose.assignment.controller.Controller;
import krados.oose.assignment.model.*;

import java.util.LinkedList;

@SuppressWarnings("StringConcatenationInLoop")
public class ShopView {
    public static void displayOptions(LinkedList<ShopItem> shopInventory, int[] costs) {
        System.out.println(
            "========== SHOP ========== \n" +
            listShopStock(shopInventory, costs) + " \n" +
            "[1] Sell Item \n" +
            "[2] Buy Item \n" +
            "[3] Enchant Weapon \n" +
            "[0] Back "
        );
    }

    public static void playerAttributes(Player p) { //Shop-specific listing of player attributes
        System.out.println("\n\n\n" +
                "========= PLAYER ========= \n" +
                p.getName() + " (" + Controller.cleanDouble(p.getHealth()) + " / " + p.getMaxHealth() + " health) \n" +
                p.getGold() + " gold \n" +
                "Inventory: (" + p.getNumItems() + " / " + Player.INVENTORY_SIZE + " items) \n" +
                listShoppingInventory(p)
        );
    }

    //PRIVATE METHODS
    private static String listShopStock(LinkedList<ShopItem> items, int[] costs) {
        String list = "";
        int jj = 1;

        //Showing items in the shop inventory
        for (ShopItem i : items) {
            list += jj + ". " + i;
            list += "   {$} " + i.getCost() + " gold \n";
            jj++;
        }

        //Showing available enchantments
        if (costs.length > 0) { //If there are none for sale, avoiding having a list with an empty header
            jj = 1;
            list += "\nEnchantments: \n";

            list += jj + ". " + "Damage +2 (Adds 2 to attack damage) \n";
            list += "   {$} " + costs[jj - 1] + " gold \n";
            jj++;

            list += jj + ". " + "Damage +5 (Adds 5 to attack damage) \n";
            list += "   {$} " + costs[jj - 1] + " gold \n";
            jj++;

            list += jj + ". " + "Fire Damage (Adds between 5 - 10 to attack damage) \n";
            list += "   {$} " + costs[jj - 1] + " gold \n";
            jj++;

            list += jj + ". " + "Power-Up (Multiplies attack damage by 1.1) \n";
            list += "   {$} " + costs[jj - 1] + " gold \n";
            jj++;
        }
        return list;
    }

    private static String listShoppingInventory(Player p) { //Listing of player inventory for the shop with extra detail
        String list = "";
        Weapon eqW = p.getEquippedWeapon();
        Armour eqA = p.getEquippedArmour();
        LinkedList<ShopItem> inventory = p.getInventory();
        int jj = 1;

        //Showing two equipped items first with emphasis (optional sell statements for equipped items commented out)
        list += "@ " + jj + ". " + eqW;
        jj++;

        list += "@ " + jj + ". " + eqA;
        jj++;

        //Showing the rest of the player inventory
        for (ShopItem i : inventory) {
            list += "  " + jj + ". " + i;
            list += "     " + i.getSellValue();
            jj++;
        }
        return list;
    }
}
