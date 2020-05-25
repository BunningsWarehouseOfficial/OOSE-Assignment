package krados.oose.assignment.view;

import krados.oose.assignment.model.Player;
import krados.oose.assignment.model.ShopItem;

import java.util.LinkedList;

public class ShopView {
    public static void displayOptions(Player player, LinkedList<ShopItem> shopInventory) {
        View.playerAttributes(player);
        System.out.println(
            "========== SHOP ========== \n" +
            "[1] Sell Item \n" +
            "[2] Buy Item \n" +
            "[3] Enchant Weapon \n" +
            //"[4] Show Item Details \n" + //TODO implement viewing item details, option of choosing either shop or inv
            "[0] Exit "
        );
    }
}
