package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

import java.util.ArrayList;
import java.util.List;

public class FreeStopCell extends Cell{
    private List<String> messages = new ArrayList<>();

    public FreeStopCell(int number) {
        super(TypeOfCell.FREE_STOP, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        messages.clear();
        messages.add("Welcome to free parking. Nothing really happens here :(");
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
