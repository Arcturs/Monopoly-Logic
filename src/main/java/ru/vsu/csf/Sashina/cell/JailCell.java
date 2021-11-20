package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

import java.util.ArrayList;
import java.util.List;

public class JailCell extends Cell {
    private List<String> messages = new ArrayList<>();

    public JailCell(int number) {
        super(TypeOfCell.JAIL, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        messages.clear();
        messages.add("Do you see that ugly building? Well, make sure you'll try not to visit it one day. You've seen jail.");
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
