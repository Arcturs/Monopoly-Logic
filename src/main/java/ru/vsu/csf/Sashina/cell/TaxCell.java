package ru.vsu.csf.Sashina.cell;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;

public class TaxCell extends Cell{
    private String message;

    public TaxCell(int number) {
        super(TypeOfCell.TAX, number);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        if (this.getPosition() == 4) {
            message = "You were on Income tax. You paid 200M. ";
            gb.checkCash(player, 200);
            player.getMoney(-200);
        }
        if (this.getPosition() == 38) {
            message = "You were on Luxury tax. You paid 100M.";
            gb.checkCash(player, 100);
            player.getMoney(-100);
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
