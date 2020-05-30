package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.controller.exceptions.ItemException;
import krados.oose.assignment.controller.exceptions.ItemNotFoundException;
import krados.oose.assignment.model.Enemy;
import krados.oose.assignment.model.Player;
import krados.oose.assignment.model.Potion;
import krados.oose.assignment.view.BattleView;
import krados.oose.assignment.view.View;

public class BattleController {
    public static boolean startBattle(Player player, Enemy enemy) {

        String playerName = player.getName();
        String enemySpecies = enemy.getSpecies();
        boolean battling = true;
        boolean endGame = false;

        do {
            double attackDamage;
            double defended;
            double originalHealth;

            View.playerAttributes(player);
            BattleView.displayEnemy(enemy);

            //Player turn
            if (player.getHealth() > 0) {
                attackDamage = playerTurn(player, enemySpecies);

                originalHealth = enemy.getHealth();
                defended = enemy.defend(attackDamage);
                if (attackDamage > 0.0) { //Remove defend statement if player did no damage
                    BattleView.displayDefend(enemySpecies, playerName, defended, originalHealth - enemy.getHealth());
                }
            }
            else {
                endGame = playerLoss(player);
                battling = false;
            }

            if (battling) {
                //Enemy turn
                if (enemy.getHealth() > 0) {
                    attackDamage = enemyTurn(enemy, playerName);

                    originalHealth = player.getHealth();
                    defended = player.defend(attackDamage);
                    if (attackDamage > 0.0) { //Remove defend statement if enemy did no damage
                       BattleView.displayDefend(playerName, enemySpecies, defended,
                                                originalHealth - player.getHealth());
                    }
                }
                else {
                    endGame = playerWin(player, enemy.getReward(), enemySpecies);
                    battling = false;
                }
            }
        } while (battling);
        return endGame;
    }

    //PRIVATE
    private static double playerTurn(Player p, String enemySpecies) {
        double attackDamage = 0;
        int cmd;
        boolean finished = false;

        do {
            try {
                BattleView.displayOptions();
                cmd = View.selectOption();

                switch (cmd) {
                    case 1: //Attack
                        BattleView.playerTurn();
                        attackDamage = p.attack();
                        BattleView.displayAttack(p.getName(), enemySpecies, attackDamage);
                        finished = true;
                        break;

                    case 2: //Use Potion
                        View.inputNumberPrompt("potion", "use");
                        cmd = View.selectOption();
                        attackDamage = usePotion(p, cmd);
                        finished = true;
                        break;

                    default:
                        View.inputBoundsError(1, 2);
                        break;
                }
            }
            catch (InputErrorException ex) {
                View.inputError(ex);
            }
            catch (ItemException ex) {
                View.itemError(ex);
            }
        } while (!finished);
        return attackDamage;
    }

    private static double enemyTurn(Enemy e, String playerName) {
        double attackDamage = e.attack(); //Get standard attack
        BattleView.enemyTurn();
        BattleView.displayAttack(e.getSpecies(), playerName, attackDamage);

        attackDamage = e.ability(attackDamage); //Potentially modify standard attack with any used abilities
        String usedAbility = e.getUsedAbility();
        if (usedAbility != null) {
            BattleView.enemyAbility(e.getSpecies(), usedAbility);
            e.setUsedAbility(null);
        }
        return attackDamage;
    }

    private static double usePotion(Player p, int cmd) throws ItemException, InputErrorException {
        double attackDamage = 0; //If the potion is healing, no damage will be sent to the enemy
        int numWeapons = p.getNumWeapons();
        int numArmours = p.getNumArmours();

        //Retrieve potion from player inventory using the item number as shown in UI
        try {
            if (cmd < 1) {
                throw new InputErrorException("Input must be > 0");
            }
            else if (cmd <= numWeapons + numArmours + 2) { //Checking if user chose a weapon or an armour
                throw new InputErrorException("Item " + cmd + " is not a potion");
            }
            else if (cmd > Player.INVENTORY_SIZE) {
                throw new InputErrorException("Player only has an inventory of size " + Player.INVENTORY_SIZE);
            }

            int index = cmd - 3 - numWeapons - numArmours; //Account for equipped items and weapons/armours shown first
            Potion potion = p.getPotion(index);
            BattleView.playerTurn();

            int effect = potion.use();
            if (potion.isHealing()) {
                double afterHealing = p.getHealth() + effect;
                double newHealth = Math.min(p.getMaxHealth(), afterHealing);
                p.setHealth(newHealth);
            }
            else {
                attackDamage = effect;
            }
            p.removePotion(potion);
            BattleView.usedPotion(p.getName(), potion.getName(), effect, potion.isHealing());
        }
        catch (ItemNotFoundException ex) {
            throw new ItemException("Could not find the potion (" + ex.getMessage() + ")", ex);
        }
        return attackDamage;
    }

    private static boolean playerLoss(Player p) {
        int cmd;
        boolean endGame = true;

        do {
            try {
                BattleView.displayLoss();
                cmd = View.selectOption();

                if (cmd == 1) { //Play Again
                    p.setHealth(p.getMaxHealth());
                    p.setGold(Player.DEFAULT_STARTING_GOLD);
                    //todo need to use factory to get default inventory, currently here previous inventory is kept

                    endGame = false;
                    cmd = 0;
                }
                else {
                    if (cmd != 0) {
                        View.inputBoundsError(0, 1);
                    }
                }
            }
            catch (InputErrorException ex) {
                View.inputError(ex);
                cmd = -1; //Reset the cmd value to prevent accidental early exit from submenu
            }
        } while (cmd != 0);
        return endGame;
    }

    private static boolean playerWin(Player p, int reward, String enemySpecies) {
        boolean endGame = true;
        double newHealth = Math.min(p.getMaxHealth(), p.getHealth() * 1.5);
        double recoveredHealth = newHealth - p.getHealth(); //Used for displaying recovered health after winning battle
        p.setHealth(newHealth);

        if (enemySpecies.equals("Dragon")) { //Winning the game
            int cmd;

            do {
                try {
                    BattleView.displayWinGame();
                    cmd = View.selectOption();

                    if (cmd == 1) { //Play Again
                        p.setHealth(p.getMaxHealth());
                        p.setGold(Player.DEFAULT_STARTING_GOLD);
                        //todo need to use factory to get default inventory, currently here previous inventory is kept

                        endGame = false;
                        cmd = 0;
                    }
                    else {
                        if (cmd != 0) {
                            View.inputBoundsError(0, 1);
                        }
                    }
                }
                catch (InputErrorException ex) {
                    View.inputError(ex);
                    cmd = -1; //Reset the cmd value to prevent accidental early exit from submenu
                }
            } while (cmd != 0);
        }
        else { //Winning the battle
            p.setGold(p.getGold() + reward); //Reward tha player after winning a battle

            BattleView.displayWinBattle(enemySpecies);
            View.balanceChange(reward);
            if (recoveredHealth > 0.0) {
                BattleView.recoverHealth(recoveredHealth); //Recover player health after winning a battle
            }
            endGame = false;
        }
        return endGame;
    }
}
