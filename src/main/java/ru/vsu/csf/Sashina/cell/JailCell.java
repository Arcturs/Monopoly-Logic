package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

public class JailCell extends Cell {
    private String message;

    public JailCell(int number) {
        super(TypeOfCell.JAIL, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        message = "Do you see that ugly building? Well, make sure you'll try not to visit it one day. You've seen jail.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
