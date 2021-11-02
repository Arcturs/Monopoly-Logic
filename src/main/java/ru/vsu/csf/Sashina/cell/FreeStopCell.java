package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

public class FreeStopCell extends Cell{
    private String message;

    public FreeStopCell(int number) {
        super(TypeOfCell.FREE_STOP, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        message = "Welcome to free parking. Nothing really happens here :(";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
