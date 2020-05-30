package krados.oose.assignment.view;

import krados.oose.assignment.controller.Controller;
import krados.oose.assignment.model.Enemy;

public class BattleView {
    public static void displayOptions() {
        System.out.println("\n" +
            "========= BATTLE ========= \n" +
            "[1] Attack \n" +
            "[2] Use Potion "
        );
    }

    public static void displayEnemy(Enemy e) {
        System.out.println(
            "========= ENEMY ========= \n" +
            e.getSpecies() + " (" + Controller.cleanDouble(e.getHealth()) + " / " + e.getMaxHealth() + " health) \n" +
            "Reward: " + e.getReward() + " gold"
        );
    }

    public static void playerTurn() {
        System.out.println("\n= Player Turn =");
    }

    public static void enemyTurn() {
        System.out.println("\n= Enemy Turn =");
    }

    public static void displayAttack(String attacker, String defender, double damage) {
        System.out.println(attacker + " attacked " + defender + " (" + Controller.cleanDouble(damage) +
                           " damage)");
    }

    public static void displayDefend(String defender, String attacker, double defended, double damageTaken) {
        System.out.println(defender + " defended " + attacker + "'s attack by " + Controller.cleanDouble(defended) +
                         " points (" + Controller.cleanDouble(damageTaken) + " damage taken)");
    }

    public static void usedPotion(String playerName, String potionName, int effect, boolean healing) {
        System.out.print(playerName + " used " + potionName + " on ");
        if (healing) {
            System.out.println("themselves (+ " + effect + " health)");
        }
        else {
            System.out.println("the enemy (" + effect + " damage)");
        }
    }

    public static void enemyAbility(String species, String ability) {
        System.out.println("  The " + species + " used their " + ability + " ability! ");
    }

    public static void displayWinGame() {
        System.out.println("\n" +
            "======== YOU WON! ======== \n" +
            "You have defeated the dragon! \n" +
            "Would you like to play again? \n" +
            "[1] Play Again \n" +
            "[0] Exit "
        );
    }

    public static void displayWinBattle(String species) {
        System.out.println("\nYou successfully defeated the " + species);
    }

    public static void recoverHealth(double healthIncrease) {
        System.out.println("+ " + Controller.cleanDouble(healthIncrease) + " health");
    }

    public static void displayLoss() {
        System.out.println("\n" +
            "======== YOU LOST ======== \n" +
            "Would you like to play again? \n" +
            "[1] Play Again \n" +
            "[0] Exit "
        );
    }
}
