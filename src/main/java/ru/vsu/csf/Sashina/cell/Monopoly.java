package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.streets.Colour;

import java.util.ArrayList;
import java.util.List;

public class Monopoly {

    private Colour colour;
    private Cell street1;
    private Cell street2;
    private Cell street3;

    public Monopoly(List<Cell> list) {
        this.colour = list.get(0).getStreet().getColour();
        this.street1 = list.get(0);
        this.street2 = list.get(1);
        if (list.size() == 3) this.street3 = list.get(2);
    }

    public Colour getColour() {
        return colour;
    }

    public List<Cell> getStreets () {
        List<Cell> list = new ArrayList<>();
        list.add(street1);
        list.add(street2);
        if (street3 != null) list.add(street3);
        return list;
    }

    public boolean isCompleted () {
        if (street3 != null)
            return street1.getColouredStreet().isHotelBuilt() && street2.getColouredStreet().isHotelBuilt() && street3.getColouredStreet().isHotelBuilt();
        return street1.getColouredStreet().isHotelBuilt() && street2.getColouredStreet().isHotelBuilt();
    }

    public boolean hasAnyHouse () {
        //замес таков, что я очень надеюсь, что можно будет передавать прямо улицу
        if (street3 != null) return street1.getColouredStreet().isHouse() || street2.getColouredStreet().isHouse()
                || street3.getColouredStreet().isHouse();
        else return street1.getColouredStreet().isHouse() || street2.getColouredStreet().isHouse();
    }
}
