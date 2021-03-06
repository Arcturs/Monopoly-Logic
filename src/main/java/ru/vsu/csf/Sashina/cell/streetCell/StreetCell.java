package ru.vsu.csf.Sashina.cell.streetCell;

import ru.vsu.csf.Sashina.cell.Cell;
import ru.vsu.csf.Sashina.cell.TypeOfCell;
import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.player.Player;
import ru.vsu.csf.Sashina.streets.ColouredStreet;
import ru.vsu.csf.Sashina.streets.OtherStreet;
import ru.vsu.csf.Sashina.streets.Streets;

import java.util.ArrayList;
import java.util.List;

public class StreetCell extends Cell {
    private List<String> messages = new ArrayList<>();
    private Streets street;
    private Player player;
    private GameBoard gameBoard;
    private int a = 0, b = 0, answer = 0;
    private int actionIndex = 1;

    public StreetCell(int number) {
        super(TypeOfCell.STREET, number);
    }

    @Override
    public void setStreet(ColouredStreet street) {
        super.setStreet(street);
    }

    @Override
    public void setStreet(OtherStreet street) {
        super.setStreet(street);
    }

    @Override
    public void doAction(GameBoard gb, Player player, int dice) {
        messages.clear();
        this.player = player;
        gameBoard = gb;
        street = getStreet();
        if (street.isAvailable()) askToBuyStreet();
        else if (street.isOccupied() && street.getHolder().equals(player)) messages.add("It's your street");
        else if (street.isOccupied() && !street.getHolder().equals(player)) payRentOrBail();
    }

    @Override
    public void doAction (StreetActions action, GameBoard gb, Player player, int dice) {
        this.player = player;
        gameBoard = gb;
        street = getStreet();
        if (action == StreetActions.BUY) buyStreet();
        if (action == StreetActions.RENT) payRent();
    }

    private void askToBuyStreet() {
        String message = java.text.MessageFormat.format("{0} is available and costs {1}M.",
                street.getName(), street.getPrice());
        messages.add(message);
        messages.add("Would you like to buy it?");
        messages.add("1) Yes     2) No");
        messages.add("Your answer: ");
        a = 1;
        b = 2;
    }

    private void buyStreet() {
        gameBoard.setGameBoard(player.buyStreet(gameBoard.getGameBoard(), this, street.getPrice()));
        messages.add("You bought this street.");
    }

    private void buyStreetOrBail() {
        if (player.getCash() >= street.getPrice()) buyStreet();
        else bailOtherStreets();
    }

    private void bailOtherStreets() {
        messages.add("You can't afford buying it. Do you want to set some streets on bail?");
        messages.add("1) Yes       2)No");
        a = 1;
        b = 2;
        actionIndex++;
    }

    private void bailOrRejectStreet() {
        actionIndex = 1;
        while (player.getCash() < street.getPrice() && !player.getMonopolyControl().isEmpty())
            gameBoard.bailOrSell(player);
        if (player.getCash() < street.getPrice() && player.getMonopolyControl().isEmpty()) {
            messages.add("You still can''t afford it.");
            //??????????????
        } else {
            gameBoard.setGameBoard(player.buyStreet(gameBoard.getGameBoard(), this, street.getPrice()));
            messages.add("You bought this street.");
        }
    }

    private void payRent() {
        Player holder = street.getHolder();
        int rent = street.getRent();
        String message = java.text.MessageFormat.format("You''re on the {0}''s property. You need to pay {1}M.",
                holder.getName(), rent);
        messages.add(message);
        holder.getMoney(rent);
        player.getMoney(-rent);
    }

    private void payRentOrBail() {
        if (!street.isOnBail()) {
            gameBoard.checkCash(player, street.getRent());
            payRent();
        }
    }

    @Override
    public int[] sendAnswer() {
        return new int[]{a, b};
    }

    @Override
    public void getAnswer(int answer) {
        this.answer = answer;
        a = 0;
        b = 0;
        messages.clear();
        if (actionIndex == 1 && answer == 1) buyStreetOrBail();
        else if (answer == 1) bailOrRejectStreet();
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
