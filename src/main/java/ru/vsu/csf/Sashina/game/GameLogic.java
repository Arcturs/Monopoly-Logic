package ru.vsu.csf.Sashina.game;

import ru.vsu.csf.Sashina.cell.*;
import ru.vsu.csf.Sashina.player.*;
import ru.vsu.csf.Sashina.streets.*;

import java.util.*;
import java.util.stream.Collectors;

//TODO: механика аукциона и обмена
//TODO: сделать интерфейс, через который взимодействует консоль
//TODO: boolean переписать с логическими высказываниями
//TODO: стратегии???

public class GameLogic extends GameBoard{
    private List<Player> players;

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    private int throwDice () {
        Random rnd = new Random();
        return rnd.nextInt(6) + 1;
    }

    private boolean playerInJail (Player player, int n1, int n2) {
        if (n1 == n2) {
            player.setStatus(PlayerStatus.ACTIVE);
            sendMessage("Hooray! You're out of jail!");
            return true;
        }
        player.increaseChances();
        if (player.getChances() == 3) {
            sendMessage("You need to pay 30M.");
            if (player.getCash() >= 30) {
                player.outOfJail(-30);
            } else {
                checkCash(player, 30);
                player.getMoney(-30);
            }
            sendMessage("You are out of jail.");
            return true;
        }
        return false;
    }

    protected void outOfJail (Player player) {
        player.outOfJail(-15);
    }

    public void gameMenu (Player player, boolean[] actions) {
        String message = java.text.MessageFormat.format("Player {0}, it''s your turn to move!", player.getName());
        sendMessage(message);
        message = java.text.MessageFormat.format("You have {0}M.", player.getCash());
        sendMessage(message);
        sendMessage("Your streets: ");
        for (Map.Entry<Colour, List<Cell>> kv : player.getMonopolyControl().entrySet()) {
            sendMessage(kv.getKey().getName() + ": ");
            for (Cell value : kv.getValue()) {
                sendMessage(value.getStreet().getName() + " {" + value.getStreet().getMonopolyLevel()
                        + "} " + ", ");
            }
        }
        sendMessage("Your actions: 1) Throw dice");
        actions[0] = true;
        if (player.getMonopolyControl().size() != 0) {
            sendMessage("2) Set street on bail");
            actions[1] = true;
        }
        if (player.getOnBailStreets().size() != 0 && !player.isInJail()) {
            sendMessage("3) Release from pledge");
            actions[2] = true;
        }
        if (player.getMonopolies().size() != 0 && !player.isInJail()) {
            sendMessage("4) Build a house or a hotel");
            actions[3] = true;
        }
        if (player.getMonopolies().size() != 0) {
            sendMessage("5) Sell a house or a hotel");
            actions[4] = true;
        }
        if (player.isInJail() && player.getCash() >= 15) {
            sendMessage("6) Get out of jail (15M.)");
            actions[5] = true;
        }
        sendMessage("7) Finish the game.");
        actions[6] = true;
    }

    public void actions(int action, Player player, boolean[] actions) {
        switch (action) {
            case 1: break;
            case 2: getStreetOnBail(player);
                gameMenu(player, actions);
                break;
            case 3: releaseStreetFromPledge(player);
                gameMenu(player, actions);
                break;
            case 4: buildHouseOrHotel(player);
                break;
            case 5: sellHouseOrHotel(player);
                gameMenu(player, actions);
                break;
            case 6: outOfJail(player);
                gameMenu(player, actions);
                sendMessage("Hooray! You''re out of jail!");
                break;
            case 7: bankrupt(player);
                break;
        }
    }

    public void playGame() {
        while (players.size() != 1) {
            List<Player> list = new ArrayList<>();
            for (Player player : players) {
                int n = 0;
                while (n != 3) {
                    gameMenu(player, new boolean[7]);
                    if (player.isBankrupt()) {
                        list = players.stream()
                                .filter(p -> !p.equals(player))
                                .collect(Collectors.toList());
                        break;
                    }
                    int n1 = throwDice(), n2 = throwDice();
                    String message = java.text.MessageFormat.format("Player {0} threw {1} and {2}.", player.getName(), n1, n2);
                    sendMessage(message);

                    if (player.isInJail()) {
                        boolean status = playerInJail(player, n1, n2);
                        if (!status) break;
                        else n = 0;
                    }
                    if (player.isBankrupt()) {
                        list = players.stream()
                                .filter(p -> !p.equals(player))
                                .collect(Collectors.toList());
                        break;
                    }

                    Cell cell = getCell(player.changePlayerPosition(n1 + n2));
                    message = java.text.MessageFormat.format("You moved to {0}.", cell.getName());
                    sendMessage(message);

                    cell.doAction(getGameBoardObject(), player, n1 + n2);
                    int[] array = cell.sendAnswer();
                    if (array.length != 0 && array[1] != 0 && array[0] != 0) {
                        if (!cell.getMessages().isEmpty())
                            cell.getMessages().forEach(this::sendMessage);
                        cell.getAnswer(checkAnswer(array[0], array[1]));
                    }
                    if (!cell.getMessages().isEmpty())
                        cell.getMessages().forEach(this::sendMessage);
                    if (player.isInJail()) break;
                    if (player.isBankrupt()) {
                        list = players.stream()
                                .filter(p -> !p.equals(player))
                                .collect(Collectors.toList());
                        break;
                    }

                    if (n1 == n2) n++;
                    else break;
                }
                if (n == 3) {
                    sendMessage("What a cheater! Move to jail!");
                    player.inJail();
                }
                sendMessage("\n");
            }
            if (!list.isEmpty()) {
                players.clear();
                players.addAll(list);
            }
        }
        String message = java.text.MessageFormat.format("Player {0} is the winner!", players.get(0).getName());
        sendMessage(message);
    }

    protected void bankrupt(Player player) {
        setGameBoard(player.bankrupt(getGameBoard()));
    }

}
