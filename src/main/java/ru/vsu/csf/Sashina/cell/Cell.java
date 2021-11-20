package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;
import ru.vsu.csf.Sashina.streets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell{

    private final TypeOfCell type;
    private final int position;

    private Streets street;
    private ColouredStreet colouredStreet;

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
        colouredStreet = street;
    }

    public void setStreet(OtherStreet street) {
        this.street = street;
    }

    public void setStreet(Streets street) {
        this.street = street;
    }

    public TypeOfCell getType() {
        return type;
    }

    public Streets getStreet () {
        return street;
    }

    public ColouredStreet getColouredStreet () {
        return colouredStreet;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        if (type == TypeOfCell.STREET) return street.getName();
        else return type.getCode();
    }

    public void doAction (GameBoard gb, Player player, int dice) {}

    public List<String> getMessages() {return new ArrayList<>();}

    public int[] sendAnswer() {
        return new int[]{};
    }

    public void getAnswer(int answer) {}
}
