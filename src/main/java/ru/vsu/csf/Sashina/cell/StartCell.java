package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

public class StartCell extends Cell{
    private String message;

    public StartCell (int position) {
        super(TypeOfCell.START, position);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        message = "You are on start. You got 200M!";
        player.getMoney(200);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
