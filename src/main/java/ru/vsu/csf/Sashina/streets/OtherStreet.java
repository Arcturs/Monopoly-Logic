package ru.vsu.csf.Sashina.streets;

public class OtherStreet extends Streets{

    public OtherStreet(String name, int price, int cP, int rent, Colour colour) {
        super(name, price, cP, rent, colour);
    }

    @Override
    public void buy() {
        levelUpMonopoly();
        setRent(getBasicRent() * getMonopolyLevel());
    }

    @Override
    public void bailOther() {
        decreaseMonopoly();
        setRent(getRent() / 2);
    }
}
