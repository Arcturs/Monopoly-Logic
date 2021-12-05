package ru.vsu.csf.Sashina.streets;

public class ColouredStreet extends Streets{
    private final int housePrice;
    private final int[] priceWithHouses;
    private final int hotelPrice;
    private final int priceWithHotel;
    private boolean hotel = false;
    private boolean house = false;

    public ColouredStreet(String name, int price, int cP, int rent, Colour colour,
                   int hP, int[] pWH, int pWHotel) {
        super(name, price, cP, rent, colour);
        this.housePrice = hP;
        this.priceWithHouses = pWH;
        this.hotelPrice = hP;
        this.priceWithHotel = pWHotel;
    }

    @Override
    public int getHousePrice() {
        return housePrice;
    }

    @Override
    public int[] getPriceWithHouses() {
        return priceWithHouses;
    }

    @Override
    public int getHotelPrice() {
        return hotelPrice;
    }

    public int getPriceWithHotel() {
        return priceWithHotel;
    }

    @Override
    public boolean isHotelBuilt () {
        return hotel;
    }

    public void buildHotel () {
        hotel = true;
    }

    @Override
    public void setHotel (boolean flag) {
        hotel = flag;
    }

    public void setHouse(boolean house) {
        this.house = house;
    }

    @Override
    public boolean isHouse() {
        return house;
    }

    @Override
    public void buyHouse() {
        int monopolyLevel = getMonopolyLevel() - 1;
        setRent(priceWithHouses[monopolyLevel]);
        setHouse(true);
        levelUpMonopoly();
    }

    @Override
    public void buyHotel() {
        buildHotel();
        setRent(getPriceWithHotel());
        levelUpMonopoly();
    }

    @Override
    public void buy() {
        levelUpMonopoly();
        setRent(getRent() * 2);
    }

    @Override
    public void bailOther() {
        setMonopolyLevel(0);
        setHotel(false);
        setHouse(false);
        setRent(getBasicRent());
    }

    public static class ColouredStreetBuilder {
        private String name;
        private int price;
        private int cP;
        private int rent;
        private Colour colour;
        private int housePrice;
        private int[] pWH;
        private int pWHotel;

        public ColouredStreetBuilder setName (String name) {
            this.name = name;
            return this;
        }

        public ColouredStreetBuilder setPrice (int price) {
            this.price = price;
            return this;
        }

        public ColouredStreetBuilder setCollateralPrice (int price) {
            this.cP = price;
            return this;
        }

        public ColouredStreetBuilder setRent (int price) {
            this.rent = price;
            return this;
        }

        public ColouredStreetBuilder setColour (Colour colour) {
            this.colour = colour;
            return this;
        }

        public ColouredStreetBuilder setHousePrice (int price) {
            this.housePrice = price;
            return this;
        }

        public ColouredStreetBuilder setPriceWithHouses (int[] price) {
            this.pWH = price;
            return this;
        }

        public ColouredStreetBuilder setPriceWithHotel (int price) {
            this.pWHotel = price;
            return this;
        }

        public ColouredStreet build() {
            return new ColouredStreet(name, price, cP, rent,
                    colour, housePrice, pWH, pWHotel);
        }
    }
}
