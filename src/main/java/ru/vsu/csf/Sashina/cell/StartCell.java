package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

import java.util.ArrayList;
import java.util.List;

public class StartCell extends Cell{
    private List<String> messages = new ArrayList<>();

    public StartCell (int position) {
        super(TypeOfCell.START, position);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        messages.clear();
        messages.add("You are on start. You got 200M!");
        player.getMoney(200);
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
