package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

public class ChanceCell extends Cell{
    private String message = "";

    public ChanceCell(int number) {
        super(TypeOfCell.CHANCE, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        switch(dice) {
            case 2:
                message = "You exceed the speed limit on a road. You paid 15M.";
                gb.checkCash(player, 15);
                player.getMoney(-15);
                break;
            case 3:
                player.sendPlayerToLocation(39);
                gb.street(player);
                break;
            case 4:
                message = "Ouch, you need to pay visit to your doctor. You paid 50M.";
                gb.checkCash(player, 50);
                player.getMoney(-50);
                break;
            case 5:
                message = "Hooray, you're on vacation! You got 100M.";
                player.getMoney(100);
                break;
            case 6:
                message = "Congratulations, you've won the second place in beauty contest. You got 10M.";
                player.getMoney(10);
                break;
            case 7:
                message = "University bill. You paid 50M.";
                gb.checkCash(player, 50);
                player.getMoney(-50);
                break;
            case 8:
                message = "One crazy woman just paid you. Wow, you got 20M.";
                player.getMoney(20);
                break;
            case 9:
                message = "You were in hospital for two weeks. You paid 100M.";
                gb.checkCash(player, 100);
                player.getMoney(-100);
                break;
            case 10:
                message = "Go to jail.";
                player.inJail();
                break;
            case 11:
                message = "You've won 25M in lottery! You got 25M.";
                player.getMoney(25);
                break;
            case 12:
                player.changePlayerPosition(-3);
                if (gb.getCell(player.getPosition()).getType() == TypeOfCell.STREET)
                    gb.street(player);
                else gb.getCell(player.getPosition()).doAction(gb, player, dice);
                break;
        }
   }

    @Override
    public String getMessage() {
        return message;
    }
}
