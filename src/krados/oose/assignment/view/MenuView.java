package krados.oose.assignment.view;

import krados.oose.assignment.model.Player;

public class MenuView {
    public static void displayOptions(Player player) {
        View.playerAttributes(player);
        System.out.println(
            "========== MENU ========== \n" +
            "[1] Shop \n" +
            "[2] Choose Character Name \n" +
            "[3] Choose Weapon \n" +
            "[4] Choose Armour \n" +
            "[5] Start Battle \n" +
            "[0] Exit "
        );
    }
}
