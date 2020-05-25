package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.model.Player;
import krados.oose.assignment.view.MenuView;
import krados.oose.assignment.view.View;

import java.util.InputMismatchException;

public class MenuController {
    public static void menu(Player p) {
        int cmd = -1;
        do {
            try {
                MenuView.displayOptions(p);
                cmd = View.selectOption();

                switch (cmd) {
                    case 1: //Shop

                        break;

                    case 2: //Choose Character Name

                        break;

                    case 3: //Choose Weapon

                        break;

                    case 4: //Choose Armour

                        break;

                    case 5: //Start Battle

                        break;

                    default:
                        if (cmd != 0) {
                            View.inputBoundsError(0, 5);
                        }
                        break;
                }
            }
            catch (InputErrorException e) {
                View.inputError(e);
            }
        } while (cmd != 0);
    }
}
