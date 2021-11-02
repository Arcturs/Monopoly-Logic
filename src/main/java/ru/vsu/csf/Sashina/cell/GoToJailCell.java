package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

public class GoToJailCell extends Cell{
    private String message;

    public GoToJailCell(int number) {
        super(TypeOfCell.GO_TO_JAIL, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        message = "Ouch, police caught you. Go to jail.";
        player.inJail();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
