package krados.oose.assignment.model;

public interface Weapon extends ShopItem {
    String getName();
    double getMinDamage();
    double getMaxDamage();
    String getTypeDamage();
    String getType();
    int getNumEnchantments();

    double attack();
}
