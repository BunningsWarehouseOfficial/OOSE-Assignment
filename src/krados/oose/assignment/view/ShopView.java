package krados.oose.assignment.view;

import krados.oose.assignment.model.*;

import java.util.LinkedList;

public class ShopView {
    public static void displayOptions(LinkedList<ShopItem> shopInventory) {
        System.out.println(
            "========== SHOP ========== \n" +
            listShopStock(shopInventory) + " \n" +
            "[1] Sell Item \n" +
            "[2] Buy Item \n" +
            "[3] Enchant Weapon \n" +
            "[0] Back "
        );
    }

    //FIXME the exact same as View version but with different method call at end
    public static void playerAttributes(Player p) { //Shop-specific listing of player attributes
        System.out.println("\n\n\n" +
                "========= PLAYER ========= \n" +
                p.getName() + " (" + p.getHealth() + " / " + p.getMaxHealth() + " health) \n" +
                p.getGold() + " gold \n" +
                "Inventory: (" + p.getNumItems() + " / " + Player.INVENTORY_SIZE + " items) \n" +
                listShoppingInventory(p)
        );
    }

    //PRIVATE METHODS
    private static String listShopStock(LinkedList<ShopItem> items) { //Listing items that the shop has for sale
        String list = "";
        int jj = 1;

        for (ShopItem i : items) {
            list += jj + ". " + i.toString();
            list += "   {$} " + i.getCost() + " gold \n";
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

        //Showing the rest of the inventory
        for (ShopItem i : inventory) {
            list += "  " + jj + ". " + i.toString();
            list += "     " + i.getSellValue();
            jj++;
        }
        return list;
    }
}
