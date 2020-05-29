package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.model.Enemy;
import krados.oose.assignment.model.Player;
import krados.oose.assignment.view.MenuView;
import krados.oose.assignment.view.View;

public class BattleController {
    public static void startBattle(Player player, Enemy enemy) {
        double attackDamage;

        do {
            if (player.getHealth() > 0) { //Player turn
                attackDamage = playerTurn(player);
                //todo signalling
                enemy.defend(attackDamage);
                //todo signalling
            }

            if (enemy.getHealth() > 0) { //Enemy turn
                attackDamage = enemyTurn(enemy);
                //todo signalling
                player.defend(attackDamage);
                //todo signalling
            }
        } while ();
    }

    public static double playerTurn(Player p) {
        double attackDamage = 0;
        int cmd = -1;

        try {
            View.playerAttributes(p);
            MenuView.displayOptions();
            cmd = View.selectOption();

            switch (cmd) {
                case 1: //Attack
                    attackDamage = 0/*TODO attack*/;
                    break;

                case 2: //Use Potion
                    View.inputNumberPrompt("potion", "use");
                    cmd = View.selectOption();
                    attackDamage = usePotion(p, cmd);
                    break;

                default:
                    View.inputBoundsError(1, 2);
                    break;
            }
        }
        catch (InputErrorException ex) {
            View.inputError(ex);
            cmd = -1; //Reset the cmd value to prevent accidental early exit of program
        }
        return attackDamage;
    }

    public static int enemyTurn(Enemy e) {

    }

    //PRIVATE
    private static double usePotion(Player p, int cmd) {
        double attackDamage = 0; //If the potion is healing, no damage will be sent to the enemy

        //TODO exception style stuff like chooseWeapon() and sellWeapon() to get Potion object

        if (potion.isHealing()) {
            double afterHealing = p.getHealth() + potion.use();
            double newHealth = Math.min(p.getMaxHealth(), afterHealing);
            p.setHealth(newHealth);
            //todo signalling
        }
        else {
            attackDamage = potion.use();
            //todo signalling
        }
        //todo signalling outside here if common battle view class used for both healing and damaging

        return attackDamage;
    }
}
