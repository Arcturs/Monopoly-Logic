package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;
import ru.vsu.csf.Sashina.streets.*;

import java.util.Objects;

public class Cell{

    private final TypeOfCell type;
    private final int position;

    private ColouredStreet street;
    private OtherStreet otherStreet;

    public Cell (TypeOfCell type, int number) {
        this.type = type;
        this.position = number;
    }

    @Override
    public boolean equals (Object cell) {
        if (cell == this) return true;
        if (cell == null) return false;
        if (this.getClass() != cell.getClass()) return false;

        Cell c = (Cell) cell;
        return Objects.equals(this.position, c.getPosition());
    }

    @Override
    public int hashCode () {
        return Objects.hash(position);
    }

    public void setStreet(ColouredStreet street) {
        this.street = street;
        this.otherStreet = null;
    }

    public void setStreet(OtherStreet street) {
        this.street = null;
        this.otherStreet = street;
    }

    public TypeOfCell getType() {
        return type;
    }

    public ColouredStreet getColouredStreet() {
        return street;
    }

    public OtherStreet getOtherStreet() {
        return otherStreet;
    }

    public Streets getStreet () {
        if (isStreetExists()) return street;
        return otherStreet;
    }

    public int getPosition() {
        return position;
    }

    public boolean isStreetExists() {
        return street != null;
    }

    public String getName() {
        if (type == TypeOfCell.CHANCE) return "Chance";
        if (type == TypeOfCell.TAX) return "Tax";
        if (type == TypeOfCell.FREE_STOP) return "Free stop";
        if (type == TypeOfCell.START) return "Start";
        if (type == TypeOfCell.JAIL) return "Jail";
        if (type == TypeOfCell.PUBLIC_TREASURY) return "Public Treasury";
        if (type == TypeOfCell.GO_TO_JAIL) return "Go to jail";
        if (isStreetExists()) return street.getName();
        return otherStreet.getName();
    }

    public void doAction (GameBoard gb, Player player, int dice) {}

    public String getMessage() {return "";}
}
