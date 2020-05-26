package krados.oose.assignment.view;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {
    public static void displayOptions() {
        System.out.println(
            "========== MENU ========== \n" +
            "[1] Shop \n" +
            "[2] Change Character Name \n" +
            "[3] Choose Weapon \n" +
            "[4] Choose Armour \n" +
            "[5] Start Battle! \n" +
            "[0] Exit "
        );
    }

    public static String changeName() {
        Scanner sc = new Scanner(System.in);
        String name;

        System.out.println("What name would you like?");
        System.out.print("> ");
        name = sc.nextLine();
        return name;
    }

    public static void changeWeaponPrompt() {
        System.out.println("Input the number of the weapon you would like to equip");
    }
    public static void changeArmourPrompt() {
        System.out.println("Input the number of the armour you would like to equip");
    }
}
