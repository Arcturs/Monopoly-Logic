package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GoToJailCell extends Cell{
    private List<String> messages = new ArrayList<>();

    public GoToJailCell(int number) {
        super(TypeOfCell.GO_TO_JAIL, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        messages.clear();
        messages.add("Ouch, police caught you. Go to jail.");
        player.inJail();
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
