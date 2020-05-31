package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.UpdaterException;
import krados.oose.assignment.model.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class FileShopUpdater implements ShopUpdater {
    public static final String UPDATE_FILE = "resources/ShopInput1.txt";
    public static final int INPUT_LIMIT = 10000;

    public LinkedList<ShopItem> update() throws UpdaterException {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        LinkedList<ShopItem> shopInventory = new LinkedList<>();

        try {
            fileStrm = new FileInputStream(UPDATE_FILE);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            int lineNum = 1;

            String line = bufRdr.readLine(); //Read initial line
            while (line != null)
            {
                String errorMsg = "Invalid line format in " + UPDATE_FILE + ", line " + lineNum + " (";
                if (line.contains(","))
                {
                    //VALIDATION
                    String[] lineArray = line.split(","); //Retrieving the item elements from the line

                    //Checking the item type and checking for the associated number of elements
                    String type = lineArray[0].trim();
                    switch (type) {
                        case "W":
                            if (lineArray.length != 7) {
                                throw new UpdaterException(errorMsg + "a weapon needs 7 specified values, " +
                                    lineArray.length + " were found)");
                            }

                            //Checking the weapon damage type
                            String typeDamage = lineArray[5];
                            if (typeDamage.replaceAll("\\s", "").length() < 2) {
                                throw new UpdaterException(errorMsg + "weapon damage type must contain at least 2 " +
                                    "non-space characters)");
                            }

                            //Checking the weapon type
                            String typeWeapon = lineArray[6];
                            if (typeWeapon.replaceAll("\\s", "").length() < 2) {
                                throw new UpdaterException(errorMsg + "weapon type must contain at least 2 non-space " +
                                    "characters)");
                            }
                            break;

                        case "A":
                            if (lineArray.length != 6) {
                                throw new UpdaterException(errorMsg + "an armour needs 6 specified values, " +
                                    lineArray.length + " were found)");
                            }

                            //Checking the armour material
                            String material = lineArray[5];
                            if (material.replaceAll("\\s", "").length() < 2) {
                                throw new UpdaterException(errorMsg + "armour material must contain at least 2 non-space " +
                                        "characters)");
                            }
                            break;

                        case "P":  //Validation for potion
                            if (lineArray.length != 6) {
                                throw new UpdaterException(errorMsg + "a potion needs 6 specified values, " +
                                        lineArray.length + " were found)");
                            }

                            //Checking the potion type
                            String typePotion = lineArray[5].trim();
                            if (!typePotion.equals("H") && !typePotion.equals("D")) {
                                throw new UpdaterException(errorMsg + "a potion must be either healing or damaging as " +
                                        "specified by a single letter, ie. 'H' or 'D')");
                            }
                            break;

                        default:
                            throw new UpdaterException(errorMsg + "first value must be a valid item type represented " +
                                "as a single letter, ie. 'W', 'A' or 'P')"); //test
                    }

                    //Checking the name of the item
                    String name = lineArray[1].trim(); //Number of non-space characters must be >= 1
                    if (name.replaceAll("\\s", "").length() < 2) {
                        throw new UpdaterException(errorMsg + "item name must contain at least 2 non-space " +
                            "characters)");
                    }

                    //Checking the min and max stats of the item
                    int min = Integer.parseInt(lineArray[2].trim());
                    int max = Integer.parseInt(lineArray[3].trim());
                    if (min < 0 || max < 0 || min >= INPUT_LIMIT || max >= INPUT_LIMIT) {
                        throw new UpdaterException(errorMsg + "item min and max effect values must be >= 0 and < " +
                            INPUT_LIMIT + ")");
                    }
                    else if (max < min) {
                        throw new UpdaterException(errorMsg + "an item's max effect can not be less than its min " +
                            "effect " + INPUT_LIMIT + ")");
                    }

                    //Checking the cost of the item
                    int cost = Integer.parseInt(lineArray[4].trim());
                    if (cost < 0 || cost >= INPUT_LIMIT) {
                        throw new UpdaterException(errorMsg + "item cost must be >= 0 and < " + INPUT_LIMIT + ")");
                    }

                    //UPDATING
                    ShopItem item;
                    char typeChar = type.charAt(0);
                    if (typeChar == 'W') {
                        String typeWeapon = lineArray[6].trim();
                        String typeDamage = lineArray[5].trim();
                        item = new WeaponItem(name, cost, typeWeapon, typeDamage, min, max);
                    }
                    else if (typeChar == 'A') {
                        String material = lineArray[5].trim();
                        item = new Armour(name, cost, material, min, max);
                    }
                    else {
                        char typePotion = lineArray[5].trim().charAt(0);
                        boolean isHealing = false;
                        if (typePotion == 'H') {
                            isHealing = true;
                        }
                        item = new Potion(name, cost, isHealing, min, max);
                    }
                    shopInventory.addLast(item); //Adding the new shop item to the new shop inventory
                    lineNum++;
                }
                else
                { //Checking for a lack of commas
                    throw new UpdaterException(errorMsg + "no ',' found)");
                }
                line = bufRdr.readLine(); //Next line
            }

            fileStrm.close();
        }
        catch (IOException ex1) {
            if (fileStrm != null) {
                try {
                    fileStrm.close();
                }
                catch (IOException ex2) {}
            }
            throw new UpdaterException("Couldn't update shop from file (" + ex1.getMessage() + ")", ex1);
        }
        return shopInventory;
    }
}
