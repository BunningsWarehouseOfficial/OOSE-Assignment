package krados.oose.assignment.view;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.controller.exceptions.ItemException;
import krados.oose.assignment.model.*;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class View {
    public static void playerAttributes(Player p) { //Generic listing of player stats and inventory
        System.out.println("\n\n\n" +
            "========= PLAYER ========= \n" +
            p.getName() + " (" + p.getHealth() + " / " + p.getMaxHealth() + " health) \n" +
            p.getGold() + " gold \n" +
            "Inventory: (" + p.getNumItems() + " / " + Player.INVENTORY_SIZE + " items) \n" +
            listInventory(p)
        );
    }

    public static int selectOption() throws InputErrorException {
        Scanner sc = new Scanner(System.in);
        int cmd;

        System.out.print("> ");
        try {
            cmd = Integer.parseInt(sc.nextLine());
        }
        catch (IllegalArgumentException | InputMismatchException ex) {
            throw new InputErrorException("Input must be an integer", ex);
        }
        return cmd;
    }

    public static void balanceChange(int gold) {
        if (gold > 0) {
            System.out.println("+ " + gold + " gold");
        }
        else if (gold < 0) {
            System.out.println("- " + (-gold) + " gold");
        }
    }

    public static void inputNumberPrompt(String itemType, String action) {
        System.out.println("Input the number of the " + itemType + " you would like to " + action);
    }

    public static void inputBoundsError(int lower, int upper) {
        System.out.println("Input Error: Input is out of bounds, must be >= " + lower + " and <= " + upper);
    }
    public static void inputError(InputErrorException e) {
        System.out.println("Input Error: " + e.getMessage());
    }
    public static void itemError(ItemException e) {
        System.out.println("Item Error: " + e.getMessage());
    }

    //PRIVATE METHODS
    private static String listInventory(Player p) { //Lower detail listing of player equipped items and inventory
        String list = "";
        Weapon eqW = p.getEquippedWeapon();
        Armour eqA = p.getEquippedArmour();
        LinkedList<ShopItem> inventory = p.getInventory();
        int jj = 1;

        //Showing two equipped items first with emphasis
        list += "@ " + jj + ". " + eqW.toStringShortened();
        jj++;

        list += "@ " + jj + ". " + eqA.toStringShortened();
        jj++;

        //Showing the rest of the player inventory
        for (ShopItem i : inventory) {
            list += "  " + jj + ". " + i.toStringShortened();
            jj++;
        }
        return list;
    }
}
