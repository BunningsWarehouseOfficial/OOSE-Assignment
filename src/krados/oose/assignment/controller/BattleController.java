package krados.oose.assignment.controller;

import krados.oose.assignment.model.Enemy;
import krados.oose.assignment.model.Player;

public class BattleController {
    private int numBattles; //TODO should this class be an object instance or should value be stored in Controller?

    public void startBattle(Player player) {
        Enemy currentEnemy = Enemy.makeEnemy(numBattles);
    }
}
