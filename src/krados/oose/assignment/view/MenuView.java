package krados.oose.assignment.view;

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
}
