package ru.vsu.csf.Sashina.player;

import ru.vsu.csf.Sashina.cell.Cell;
import ru.vsu.csf.Sashina.cell.Monopoly;
import ru.vsu.csf.Sashina.cell.TypeOfCell;
import ru.vsu.csf.Sashina.streets.Colour;
import ru.vsu.csf.Sashina.streets.ColouredStreet;
import ru.vsu.csf.Sashina.streets.OtherStreet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerHelper {

    public static void buyStreet(List<Cell> list, Cell[] cells, Cell cell, Map<Colour, List<Cell>> monopolyControl,
                                 List<Monopoly> monopolies) {
        List<Cell> newList = new ArrayList<>();
        List<Cell> listForMonopolies = new ArrayList<>();

        for (Cell c : list) {
            ColouredStreet s = c.getColouredStreet();
            s.buy();
            Cell newCell = new Cell(TypeOfCell.STREET, c.getPosition());
            newCell.setStreet(s);
            cells[newCell.getPosition()] = newCell;
            newList.add(newCell);
            listForMonopolies.add(newCell);
        }

        ColouredStreet street = cell.getColouredStreet();
        street.buy();
        cell.setStreet(street);
        cells[cell.getPosition()] = cell;
        newList.add(cell);
        monopolyControl.replace(street.getColour(), newList);
        listForMonopolies.add(cell);
        monopolies.add(new Monopoly(listForMonopolies));
    }

    public static void buyStreet(Cell[] cells, Cell cell, Map<Colour, List<Cell>> monopolyControl) {
        List<Cell> list = monopolyControl.get(cell.getOtherStreet().getColour());
        List<Cell> newList = new ArrayList<>();

        for (Cell c : list) {
            OtherStreet s = c.getOtherStreet();
            s.buy();
            Cell newCell = new Cell(TypeOfCell.STREET, c.getPosition());
            newCell.setStreet(s);
            newList.add(newCell);
            cells[newCell.getPosition()] = newCell;
        }

        int k = 1;
        if (list.size() == 1) k = 2;
        if (list.size() == 2) k = 4;
        if (list.size() == 3) k = 8;

        OtherStreet street = cell.getOtherStreet();
        street.setMonopolyLevel(list.size());
        street.setRent(street.getRent() * k);
        cell.setStreet(street);
        cells[cell.getPosition()] = cell;
        newList.add(cell);
        monopolyControl.replace(cell.getOtherStreet().getColour(), newList);
    }

    public static void buyStreet(List<Cell> list, Cell cell, Colour colour, Map<Colour, List<Cell>> monopolyControl) {
        list.add(cell);
        monopolyControl.put(colour, list);
    }

    public static void bailCell (Cell cell, OtherStreet street, Cell[] cells) {
        street.onBail();
        Cell newCell = new Cell(TypeOfCell.STREET, cell.getPosition());
        newCell.setStreet(street);
        cells[newCell.getPosition()] = newCell;
    }

    public static void bailCell (Cell cell, ColouredStreet street, Cell[] cells) {
        street.onBail();
        Cell newCell = new Cell(TypeOfCell.STREET, cell.getPosition());
        newCell.setStreet(street);
        cells[newCell.getPosition()] = newCell;
    }

    public static void bailOtherCells (Cell cell, OtherStreet street, Cell[] cells, List<Cell> newList) {
        street.decreaseStreetLevel();
        Cell newCell = new Cell(TypeOfCell.STREET, cell.getPosition());
        newCell.setStreet(street);
        cells[newCell.getPosition()] = newCell;
        newList.add(newCell);
    }

    public static void bailOtherCells (Cell cell, ColouredStreet street, Cell[] cells, List<Cell> newList) {
        street.sellHousesAndHotel();
        Cell newCell = new Cell(TypeOfCell.STREET, cell.getPosition());
        newCell.setStreet(street);
        cells[newCell.getPosition()] = newCell;
        newList.add(newCell);
    }

    public static void setOnBail (ColouredStreet street, Cell cell, Cell[] cells) {
        street.onBail();
        Cell newCell = new Cell(TypeOfCell.STREET, cell.getPosition());
        newCell.setStreet(street);
        cells[newCell.getPosition()] = newCell;
    }

    public static void setOnBail (OtherStreet street, Cell cell, Cell[] cells) {
        street.onBail();
        Cell newCell = new Cell(TypeOfCell.STREET, cell.getPosition());
        newCell.setStreet(street);
        cells[newCell.getPosition()] = newCell;
    }
}
