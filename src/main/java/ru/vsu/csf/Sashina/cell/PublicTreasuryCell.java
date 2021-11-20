package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PublicTreasuryCell extends Cell{
    private List<String> messages = new ArrayList<>();

    public PublicTreasuryCell(int number) {
        super(TypeOfCell.PUBLIC_TREASURY, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        messages.clear();
        switch(dice) {
            case 2:
                messages.add("There was a mistake in your bank account. You got 200M.");
                player.getMoney(200);
                break;
            case 3:
                player.sendPlayerToLocation(0);
                break;
            case 4:
                messages.add("Ouch, you need to pay a visit to your doctor. You paid 50M.");
                gb.checkCash(player, 50);
                player.getMoney(-50);
                break;
            case 5:
                messages.add("Hooray, you're on vacation! You got 100M.");
                player.getMoney(100);
                break;
            case 6:
                messages.add("Congratulations, you've won the second place in beauty contest. You got 10M.");
                player.getMoney(10);
                break;
            case 7:
                messages.add("University bill. You paid 50M.");
                gb.checkCash(player, 50);
                player.getMoney(-50);
                break;
            case 8:
                messages.add("One crazy woman has just paid you. Wow, you got 20M.");
                player.getMoney(20);
                break;
            case 9:
                messages.add("You were in a hospital for two weeks. You paid 100M.");
                gb.checkCash(player, 100);
                player.getMoney(-100);
                break;
            case 10:
                messages.add("Go to jail.");
                player.inJail();
                break;
            case 11:
                messages.add("You've won 25M in lottery! You got 25M.");
                player.getMoney(25);
                break;
            case 12:
                player.changePlayerPosition(-3);
                gb.getCell(player.getPosition()).doAction(gb, player, dice);
                break;
        }
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
