package ru.vsu.csf.Sashina.player;

import ru.vsu.csf.Sashina.cell.Cell;
import ru.vsu.csf.Sashina.cell.Monopoly;
import ru.vsu.csf.Sashina.cell.TypeOfCell;
import ru.vsu.csf.Sashina.streets.*;

import java.util.*;
import java.util.stream.Collectors;

public class Player {

    private static final int startCash = 1500;

    private final String name;
    private int position = 0;
    private int cash = startCash;
    private PlayerStatus status = PlayerStatus.ACTIVE;
    private Map<Colour, List<Cell>> monopolyControl = new HashMap<>();
    private List<Monopoly> monopolies = new ArrayList<>();
    private List<Cell> onBailStreets = new ArrayList<>();
    private int chances = 0;

    public Player(String chip) {
        this.name = chip;
    }

    @Override
    public boolean equals (Object player) {
        if (player == this) return true;
        if (player == null) return false;
        if (this.getClass() != player.getClass()) return false;

        Player pl = (Player) player;
        return Objects.equals(this.name, pl.getName());
    }

    @Override
    public int hashCode () {
        return Objects.hash(name);
    }

    public int getPosition() {
        return position;
    }

    public int getCash() {
        return cash;
    }

    public String getName() {
        return name;
    }

    public Map<Colour, List<Cell>> getMonopolyControl() {
        return monopolyControl;
    }

    public List<Cell> getOnBailStreets() {
        return onBailStreets;
    }

    public List<Monopoly> getMonopolies() {
        return monopolies;
    }

    public int getChances() {
        return chances;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public void setChances(int chances) {
        this.chances = chances;
    }

    public void increaseChances () {
        chances++;
    }

    public Cell[] buyStreet (Cell[] cells, Cell cell, int price) {
        cell.getStreet().occupyStreet(this);
        getMoney(-price);

        if (cell.isStreetExists()) {
            ColouredStreet street = cell.getColouredStreet();
            if (monopolyControl.containsKey(street.getColour())) {
                List<Cell> list = monopolyControl.get(street.getColour());
                if (street.getColour() == Colour.BLUE || street.getColour() == Colour.BROWN) PlayerHelper.buyStreet(list, cells, cell, monopolyControl, monopolies);
                else {
                    if (list.size() == 1) {
                        list.add(cell);
                        monopolyControl.replace(street.getColour(), list);
                    } else PlayerHelper.buyStreet(list, cells, cell, monopolyControl, monopolies);
                }
            } else {
                List<Cell> list = new ArrayList<>();
                PlayerHelper.buyStreet(list, cell, street.getColour(), monopolyControl);
            }
        } else {
            Colour colour = cell.getOtherStreet().getColour();
            if (monopolyControl.containsKey(colour)) PlayerHelper.buyStreet(cells, cell, monopolyControl);
            else {
                List<Cell> list = new ArrayList<>();
                PlayerHelper.buyStreet(list, cell, colour, monopolyControl);
            }
        }
        return cells;
    }

    public Cell[] setStreetOnBail (Cell[] cells, Cell cell) {
        List<Cell> list = monopolyControl.get(cell.getStreet().getColour());
        List<Cell> newList = new ArrayList<>();
        if (cell.getStreet().getMonopolyLevel() > 0) {
            for (Cell c : list) {
                if (c.equals(cell)) {
                    if (!c.isStreetExists()) PlayerHelper.bailCell(c, c.getOtherStreet(), cells);
                    else PlayerHelper.bailCell(c, c.getColouredStreet(), cells);
                    getMoney(c.getStreet().getCollateralPrice());
                } else {
                    if (!c.isStreetExists()) PlayerHelper.bailOtherCells(c, c.getOtherStreet(), cells, newList);
                    else PlayerHelper.bailOtherCells(c, c.getColouredStreet(), cells, newList);
                }
            }
        } else {
            for (Cell c : list) {
                if (!c.equals(cell)) newList.add(c);
                else {
                    if (!c.isStreetExists()) PlayerHelper.setOnBail(c.getOtherStreet(), c, cells);
                    else PlayerHelper.setOnBail(c.getColouredStreet(), c, cells);
                    getMoney(c.getStreet().getCollateralPrice());
                }
            }
        }
        if (newList.isEmpty()) monopolyControl.remove(cell.getStreet().getColour());
        else monopolyControl.replace(cell.getStreet().getColour(), newList);
        onBailStreets.add(cell);
        return cells;
    }

    public Cell[] releaseFromPledge (Cell[] cells, Cell cell) {
        getMoney((int)(-1.1 * cell.getStreet().getCollateralPrice()));
        onBailStreets.remove(cell);
        return buyStreet(cells, cell, 0);
    }

    public Cell[] buildHouseOrHotel (Cell[] cells, Cell cell) {
        Cell newCell = new Cell(TypeOfCell.STREET, cell.getPosition());
        ColouredStreet street = cell.getColouredStreet();
        int monopolyLevel = street.getMonopolyLevel() - 1;
        if (monopolyLevel == 4) {
            street.buyHotel();
            getMoney(-street.getHotelPrice());
        } else {
            street.buyHouse();
            getMoney(-street.getHousePrice());
        }
        street.levelUpMonopoly();
        newCell.setStreet(street);

        List<Monopoly> newList = new ArrayList<>();
        for (Monopoly monopoly: monopolies) {
            if (monopoly.getColour() == street.getColour()) {
                List<Cell> list = new ArrayList<>();
                List<Cell> streets = monopoly.getStreets();
                for (Cell c : streets) {
                    if (!c.equals(cell)) {
                        list.add(c);
                    }
                }
                list.add(newCell);
                Monopoly newMonopoly = new Monopoly(list);
                newList.add(newMonopoly);
            } else {
                newList.add(monopoly);
            }
        }
        monopolies.clear();
        monopolies.addAll(newList);

        cells[newCell.getPosition()] = newCell;
        return cells;
    }

    public Cell[] sellHousesAndHotel (Cell[] cells, Cell cell) {
        for (Monopoly monopoly : monopolies) {
            if (monopoly.getColour() == cell.getColouredStreet().getColour()) {
                for (Cell c : monopoly.getStreets()) {
                    ColouredStreet s = c.getColouredStreet();
                    Cell newCell = new Cell(TypeOfCell.STREET, c.getPosition());
                    s.sellHousesAndHotel();
                    newCell.setStreet(s);
                    cells[newCell.getPosition()] = newCell;
                    if (cell.equals(c)) {
                        cells = setStreetOnBail(cells, newCell);
                    }
                }
                monopolies.remove(monopoly);
                break;
            }
        }
        return cells;
    }

    public Cell[] sellHouseOrHotel (Cell[] cells, Cell cell) {
        Cell newCell = new Cell(TypeOfCell.STREET, cell.getPosition());
        ColouredStreet street = cell.getColouredStreet();
        street.decreaseMonopoly();

        if (street.getMonopolyLevel() == 4) {
            street.setHotel(false);
        } else if (street.getMonopolyLevel() == 1) {
            street.setRent(street.getRent() * 2);
        } else {
            int[] array = street.getPriceWithHouses();
            street.setRent(array[street.getMonopolyLevel() - 1]);
        }

        for (Monopoly monopoly: monopolies) {
            if (monopoly.getColour() == street.getColour()) {
                List<Cell> streets = monopoly.getStreets();
                List<Cell> list = streets.stream()
                        .filter(c -> !c.equals(cell))
                        .collect(Collectors.toList());
                list.add(newCell);
                Monopoly newMonopoly = new Monopoly(list);
                monopolies.remove(monopoly);
                monopolies.add(newMonopoly);
                break;
            }
        }
        newCell.setStreet(street);
        cells[newCell.getPosition()] = newCell;
        setStreetOnBail(cells, newCell);
        return cells;
    }

    public void getMoney (int money) {
        cash += money;
    }

    public void inJail () {
        status = PlayerStatus.IN_PRISON;
        position = 10;
    }

    public int changePlayerPosition (int move) {
        position += move;
        if (position > 40) cash += 200;
        position %= 40;
        return position;
    }

    public void sendPlayerToLocation(int location) {
        if (position > location) cash += 200;
        position = location;
    }

    public boolean isBankrupt() {
        return status == PlayerStatus.BANKRUPT;
    }

    public Cell[] bankrupt(Cell[] cells) {
        status = PlayerStatus.BANKRUPT;
        monopolies.clear();
        for (Map.Entry<Colour, List<Cell>> kv : monopolyControl.entrySet()) {
            for (Cell value : kv.getValue()) {
                Cell newCell = new Cell(TypeOfCell.STREET, value.getPosition());
                if (!value.isStreetExists()) {
                    OtherStreet s = value.getOtherStreet();
                    s.sell();
                    newCell.setStreet(s);
                } else {
                    ColouredStreet s = value.getColouredStreet();
                    s.sell();
                    newCell.setStreet(s);
                }
                cells[newCell.getPosition()] = newCell;
            }
        }
        //может здесь всунуть потоки?
        monopolyControl.clear();
        for (Cell c : onBailStreets) {
            Cell newCell = new Cell(TypeOfCell.STREET, c.getPosition());
            if (!c.isStreetExists()) {
                OtherStreet s = c.getOtherStreet();
                s.sell();
                newCell.setStreet(s);
            } else {
                ColouredStreet s = c.getColouredStreet();
                s.sell();
                newCell.setStreet(s);
            }
            cells[newCell.getPosition()] = newCell;
        }
        onBailStreets.clear();
        return cells;
    }

    public boolean isInJail() {
        return status == PlayerStatus.IN_PRISON;
    }

    public void outOfJail(int money) {
        getMoney(money);
        setStatus(PlayerStatus.ACTIVE);
        setChances(0);
    }
}
