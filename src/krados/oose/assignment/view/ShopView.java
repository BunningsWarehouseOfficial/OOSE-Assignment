package krados.oose.assignment.view;

import krados.oose.assignment.model.*;

import java.util.LinkedList;

public class ShopView {
    public static void displayOptions(LinkedList<ShopItem> shopInventory) {
        System.out.println(
            "========== SHOP ========== \n" +
            "[1] Sell Item \n" +
            "[2] Buy Item \n" +
            "[3] Enchant Weapon \n" +
            "[0] Back "
        );
    }

    //FIXME the exact same as View version but with different method call at end
    public static void playerAttributes(Player p) { //Shop-specific listing of player attributes
        System.out.println("\n\n\n" +
                "========= PLAYER ========= \n" +
                p.getName() + " (" + p.getHealth() + " / " + p.getMaxHealth() + " health) \n" +
                p.getGold() + " gold \n" +
                "Inventory: (" + p.getNumItems() + " / " + Player.INVENTORY_SIZE + " items) \n" +
                listShoppingInventory(p)
        );
    }

    public static void sellItemPrompt() {
        System.out.println("Input the number of the item you would like to sell.");
    }

    //PRIVATE METHODS
    private static String listShoppingInventory(Player p) { //Shop specific listing of inventory with extra detail
        String list = "";
        Weapon eqW = p.getEquippedWeapon();
        Armour eqA = p.getEquippedArmour();
        int ii = 1; //TODO this doesn't list out type/material, need to list at least one other place (item details?)

        //Showing two equipped items first with emphasis (optional sell statements for equipped items commented out)
        list += "@ " + ii + ". " + eqW.getName() + " (" + eqW.getMinDamage() + " - " + eqW.getMaxDamage() + " " +
                eqW.getTypeDamage() + " damage) [Type: " + eqW.getType() + "] \n";
        //list += "     {$} equipped, can't sell \n";
        ii++;

        list += "@ " + ii + ". " + eqA.getName() + " (" + eqA.getMinDefence() + " - " + eqA.getMaxDefence() +
                " defence) [Material: " + eqA.getMaterial() + "] \n";
        //list += "     {$} equipped, can't sell \n";
        ii++;

        //Showing the rest of the inventory
        for (Weapon w : p.getInvWeapons()) {
            list += "  " + ii + ". " + w.getName() + " (" + w.getMinDamage() + " - " + w.getMaxDamage() + " " +
                    w.getTypeDamage() + " damage) [Type: " + w.getType() + "] \n";
            list += "     {$} " + w.getCost() / 2 + " gold \n";
            ii++;
        }
        for (Armour a : p.getInvArmours()) {
            list += "  " + ii + ". " + a.getName() + " (" + a.getMinDefence() + " - " + a.getMaxDefence() + " " +
                    " defence) [Material: " + a.getMaterial() + "] \n";
            list += "     {$} " + a.getCost() / 2 + " gold \n";
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
            list += "     {$} " + pot.getCost() / 2 + " gold \n";
            ii++;
        }
        return list;
    }
}
