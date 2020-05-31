package krados.oose.assignment.controller;

import krados.oose.assignment.controller.exceptions.UpdaterException;
import krados.oose.assignment.model.ShopItem;

import java.util.LinkedList;

public interface ShopUpdater {
    LinkedList<ShopItem> update() throws UpdaterException;
}
