package krados.oose.assignment.view;

import krados.oose.assignment.controller.exceptions.InputErrorException;
import krados.oose.assignment.controller.exceptions.ItemNotFoundException;
import krados.oose.assignment.model.*;

import java.util.InputMismatchException;
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
        catch (IllegalArgumentException | InputMismatchException e) {
            throw new InputErrorException("Input must be an integer", e);
        }
        return cmd;
    }

    public static void balanceChange(int gold) {
        if (gold > 0) {
            System.out.println("+ " + gold + " gold");
        }
        else if (gold < 0) {
            System.out.println("- " + gold + " gold");
        }
    }

    public static void inputBoundsError(int lower, int upper) {
        System.out.println("Error: Input is out of bounds, must be >= " + lower + " and <= " + upper);
    }
    public static void inputError(InputErrorException e) {
        System.out.println("Error: " + e.getMessage());
    }
    public static void itemError(ItemNotFoundException e) { //TODO should this and above inherit from higher exception?
        System.out.println("Error: " + e.getMessage()); //TODO dodge above by moving to MenuView? Is it used elsewhere?
    }

    //PRIVATE METHODS
    private static String listInventory(Player p) { //Generic listing of player equipped items and inventory
        String list = "";
        Weapon eqW = p.getEquippedWeapon();
        Armour eqA = p.getEquippedArmour();
        int ii = 1; //TODO this doesn't list out type/material, need to list at least one other place (item details?)

        //Showing two equipped items first with emphasis
        list += "@ " + ii + ". " + eqW.getName() + " (" + eqW.getMinDamage() + " - " + eqW.getMaxDamage() + " " +
                eqW.getTypeDamage() + " damage) \n";
        ii++;
        list += "@ " + ii + ". " + eqA.getName() + " (" + eqA.getMinDefence() + " - " + eqA.getMaxDefence() + " " +
                " defence) \n";
        ii++;

        //Showing the rest of the inventory
        for (Weapon w : p.getInvWeapons()) {
            list += "  " + ii + ". " + w.getName() + " (" + w.getMinDamage() + " - " + w.getMaxDamage() + " " +
                    w.getTypeDamage() + " damage) \n";
            ii++;
        }
        for (Armour a : p.getInvArmours()) {
            list += "  " + ii + ". " + a.getName() + " (" + a.getMinDefence() + " - " + a.getMaxDefence() + " " +
                    " defence) \n";
            ii++;
        }
        for (Potion pot : p.getInvPotions()) {
            list += "  " + ii + ". " + pot.getName() + " (" + pot.getMinEffect() + " - " + pot.getMaxEffect();
            if (pot.isHealing()) {
                list += " healing) \n";
            }
            else {
                list += " damage) \n";
            }
            ii++;
        }
        return list;
    }
}
