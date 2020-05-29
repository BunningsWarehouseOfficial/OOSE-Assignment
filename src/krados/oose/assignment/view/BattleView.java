package krados.oose.assignment.view;

import krados.oose.assignment.model.Enemy;
import krados.oose.assignment.model.ShopItem;

import java.util.LinkedList;

public class BattleView {
    public static void displayOptions() {
        System.out.println(
            "========= BATTLE ========= \n" +
            "[1] Attack \n" +
            "[2] Use Potion "
        );
    }

    public static void displayEnemy(Enemy e) {
        System.out.println(
            "========= ENEMY ========= \n" +
            e.getSpecies() + " (" + e.getHealth() + " / " + e.getMaxHealth() + " health) \n" +
            "Reward: " + e.getReward() + " gold "
        );
    }
}
