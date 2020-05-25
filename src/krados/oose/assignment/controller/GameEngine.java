package krados.oose.assignment.controller;

import krados.oose.assignment.model.Player;

public class GameEngine {
    public static void main(String[] args) {
        //TODO just stuff everything into here for now and 'methodise' later
        Player player = new Player(30, "X Æ A-12", 100);
        MenuController.menu(player);
    }

    private void injectDependencies() { //TODO could just be in main() ?

    }

    private void centralFactory() { //TODO could just be in main() ?

    }
}
