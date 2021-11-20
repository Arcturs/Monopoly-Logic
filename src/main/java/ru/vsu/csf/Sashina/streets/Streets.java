package ru.vsu.csf.Sashina.streets;

import ru.vsu.csf.Sashina.player.Player;

public class Streets {

    private final String name;
    private final int price;
    private final int collateralPrice;
    private int rent;
    private final int basicRent;
    private StreetStatus status = StreetStatus.AVAILABLE;
    private Player holder = null;
    private int monopolyLevel = 0;
    private final Colour colour;

    public Streets(String name, int price, int cP, int rent, Colour colour) {
        this.name = name;
        this.price = price;
        this.collateralPrice = cP;
        this.rent = rent;
        this.basicRent = rent;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCollateralPrice() {
        return collateralPrice;
    }

    public int getRent() {
        return rent;
    }

    public int getMonopolyLevel() {
        return monopolyLevel;
    }

    public Colour getColour() {
        return colour;
    }

    public void levelUpMonopoly() {
        monopolyLevel++;
    }

    public void decreaseMonopoly() {
        monopolyLevel--;
    }

    public void setMonopolyLevel(int level) {
        this.monopolyLevel = level;
    }

    public Player getHolder() {
        return holder;
    }

    public int getBasicRent() {
        return basicRent;
    }

    public void setOnBail () {
        status = StreetStatus.ON_BAIL;
    }

    public void occupyStreet (Player player) {
        holder = player;
        status = StreetStatus.OCCUPIED;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setStatus(StreetStatus status) {
        this.status = status;
    }

    public boolean isOnBail() {
        return status == StreetStatus.ON_BAIL;
    }

    public boolean isOccupied () {
        return status == StreetStatus.OCCUPIED;
    }

    public boolean isAvailable() {
        return status == StreetStatus.AVAILABLE;
    }

    public void buy () {
    }

    public void bailOther() {}

    public void onBail() {
        setMonopolyLevel(0);
        setRent(getBasicRent());
        setOnBail();
    }

    public void sell() {
        setRent(getBasicRent());
        setMonopolyLevel(0);
        setStatus(StreetStatus.AVAILABLE);
    }
}
