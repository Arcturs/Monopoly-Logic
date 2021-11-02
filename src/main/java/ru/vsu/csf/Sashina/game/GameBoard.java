package ru.vsu.csf.Sashina.game;

import ru.vsu.csf.Sashina.cell.*;
import ru.vsu.csf.Sashina.player.Player;
import ru.vsu.csf.Sashina.streets.Colour;
import ru.vsu.csf.Sashina.streets.ColouredStreet;
import ru.vsu.csf.Sashina.streets.OtherStreet;

import java.util.List;
import java.util.Map;

public class GameBoard {
    private Cell[] gameBoard = CellInitializer();

    private static Cell[] CellInitializer () {
        Cell[] cells = new Cell[40];

        Cell cell = new StartCell(0);
        cells[0] = cell;

        cell = new StreetCell(1);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Mediteranean Avenue").setPrice(60).
                setCollateralPrice(30).setRent(2).setColour(Colour.BROWN).setHousePrice(50).
                setPriceWithHouses(new int[]{10, 30, 90, 160}).setPriceWithHotel(250).build());
        cells[1] = cell;

        cell = new PublicTreasuryCell(2);
        cells[2] = cell;

        cell = new StreetCell(3);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Baltic Avenue").setPrice(60).
                setCollateralPrice(30).setRent(4).setColour(Colour.BROWN).setHousePrice(50).
                setPriceWithHouses(new int[]{20, 60, 180, 320}).setPriceWithHotel(450).build());
        cells[3] = cell;

        cell = new TaxCell(4);
        cells[4] = cell;

        cell = new StreetCell(5);
        cell.setStreet(new OtherStreet("Reading Railroad", 200, 100, 25, Colour.RL));
        cells[5] = cell;

        cell = new StreetCell(6);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Oriental Avenue").setPrice(100).
                setCollateralPrice(50).setRent(6).setColour(Colour.CYAN).setHousePrice(50).
                setPriceWithHouses(new int[]{30, 90, 270, 400}).setPriceWithHotel(550).build());
        cells[6] = cell;

        cell = new ChanceCell(7);
        cells[7] = cell;

        cell = new StreetCell(8);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Vermont Avenue").setPrice(100).
                setCollateralPrice(50).setRent(6).setColour(Colour.CYAN).setHousePrice(50).
                setPriceWithHouses(new int[]{30, 90, 270, 400}).setPriceWithHotel(550).build());
        cells[8] = cell;

        cell = new StreetCell(9);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Connecticut Avenue").setPrice(120).
                setCollateralPrice(60).setRent(8).setColour(Colour.CYAN).setHousePrice(50).
                setPriceWithHouses(new int[]{40, 100, 300, 450}).setPriceWithHotel(600).build());
        cells[9] = cell;

        cell = new JailCell(10);
        cells[10] = cell;

        cell = new StreetCell(11);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("St. Charles Place").setPrice(140).
                setCollateralPrice(70).setRent(10).setColour(Colour.PINK).setHousePrice(100).
                setPriceWithHouses(new int[]{50, 150, 450, 625}).setPriceWithHotel(750).build());
        cells[11] = cell;

        cell = new StreetCell(12);
        cell.setStreet(new OtherStreet("Electric company", 150, 75, 25, Colour.E));
        cells[12] = cell;

        cell = new StreetCell(13);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("States Avenue").setPrice(140).
                setCollateralPrice(70).setRent(10).setColour(Colour.PINK).setHousePrice(100).
                setPriceWithHouses(new int[]{50, 150, 450, 625}).setPriceWithHotel(750).build());
        cells[13] = cell;

        cell = new StreetCell(14);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Virginia Avenue").setPrice(160).
                setCollateralPrice(80).setRent(12).setColour(Colour.PINK).setHousePrice(100).
                setPriceWithHouses(new int[]{60, 180, 500, 700}).setPriceWithHotel(900).build());
        cells[14] = cell;

        cell = new StreetCell(15);
        cell.setStreet(new OtherStreet("Pennsylvania Railroad", 200, 100, 25, Colour.RL));
        cells[15] = cell;

        cell = new StreetCell(16);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("St. James Place").setPrice(180).
                setCollateralPrice(90).setRent(14).setColour(Colour.ORANGE).setHousePrice(100).
                setPriceWithHouses(new int[]{70, 200, 550, 750}).setPriceWithHotel(950).build());
        cells[16] = cell;

        cell = new PublicTreasuryCell(17);
        cells[17] = cell;

        cell = new StreetCell(18);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Tennessee Avenue").setPrice(180).
                setCollateralPrice(90).setRent(14).setColour(Colour.ORANGE).setHousePrice(100).
                setPriceWithHouses(new int[]{70, 200, 550, 750}).setPriceWithHotel(950).build());
        cells[18] = cell;

        cell = new StreetCell(19);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("New York Avenue").setPrice(200).
                setCollateralPrice(100).setRent(16).setColour(Colour.ORANGE).setHousePrice(100).
                setPriceWithHouses(new int[]{80, 220, 600, 800}).setPriceWithHotel(950).build());
        cells[19] = cell;

        cell = new FreeStopCell(20);
        cells[20] = cell;

        cell = new StreetCell(21);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Kentucky Avenue").setPrice(220).
                setCollateralPrice(110).setRent(18).setColour(Colour.RED).setHousePrice(150).
                setPriceWithHouses(new int[]{90, 250, 700, 875}).setPriceWithHotel(1050).build());
        cells[21] = cell;

        cell = new ChanceCell(22);
        cells[22] = cell;

        cell = new StreetCell(23);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Indiana Avenue").setPrice(220).
                setCollateralPrice(110).setRent(18).setColour(Colour.RED).setHousePrice(150).
                setPriceWithHouses(new int[]{90, 250, 700, 875}).setPriceWithHotel(1050).build());
        cells[23] = cell;

        cell = new StreetCell(24);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Illinois Avenue").setPrice(240).
                setCollateralPrice(120).setRent(20).setColour(Colour.RED).setHousePrice(150).
                setPriceWithHouses(new int[]{100, 300, 750, 925}).setPriceWithHotel(1100).build());
        cells[24] = cell;

        cell = new StreetCell(25);
        cell.setStreet(new OtherStreet("B. & O. Railroad", 200, 100, 25, Colour.RL));
        cells[25] = cell;

        cell = new StreetCell(26);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Atlantic Avenue").setPrice(260).
                setCollateralPrice(130).setRent(22).setColour(Colour.YELLOW).setHousePrice(150).
                setPriceWithHouses(new int[]{110, 330, 800, 975}).setPriceWithHotel(1150).build());
        cells[26] = cell;

        cell = new StreetCell(27);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Ventnor Avenue").setPrice(260).
                setCollateralPrice(130).setRent(22).setColour(Colour.YELLOW).setHousePrice(150).
                setPriceWithHouses(new int[]{110, 330, 800, 975}).setPriceWithHotel(1150).build());
        cells[27] = cell;

        cell = new StreetCell(28);
        cell.setStreet(new OtherStreet("Water works", 150, 75, 25, Colour.E));
        cells[28] = cell;

        cell = new StreetCell(29);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Marvin Gardens").setPrice(280).
                setCollateralPrice(140).setRent(24).setColour(Colour.YELLOW).setHousePrice(150).
                setPriceWithHouses(new int[]{120, 360, 850, 1025}).setPriceWithHotel(1200).build());
        cells[29] = cell;

        cell = new GoToJailCell(30);
        cells[30] = cell;

        cell = new StreetCell(31);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Pacific Avenue").setPrice(300).
                setCollateralPrice(150).setRent(26).setColour(Colour.GREEN).setHousePrice(200).
                setPriceWithHouses(new int[]{130, 390, 900, 1100}).setPriceWithHotel(1275).build());
        cells[31] = cell;

        cell = new StreetCell(32);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("North Carolina Avenue").setPrice(300).
                setCollateralPrice(150).setRent(26).setColour(Colour.GREEN).setHousePrice(200).
                setPriceWithHouses(new int[]{130, 390, 900, 1100}).setPriceWithHotel(1275).build());
        cells[32] = cell;

        cell = new PublicTreasuryCell(33);
        cells[33] = cell;

        cell = new StreetCell(34);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Pennsylvania Avenue").setPrice(320).
                setCollateralPrice(160).setRent(28).setColour(Colour.GREEN).setHousePrice(200).
                setPriceWithHouses(new int[]{150, 450, 1000, 1200}).setPriceWithHotel(1400).build());
        cells[34] = cell;

        cell = new StreetCell(35);
        cell.setStreet(new OtherStreet("Short Line", 200, 100, 25, Colour.RL));
        cells[35] = cell;

        cell = new ChanceCell(36);
        cells[36] = cell;

        cell = new StreetCell(37);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Park Place").setPrice(350).
                setCollateralPrice(175).setRent(35).setColour(Colour.BLUE).setHousePrice(200).
                setPriceWithHouses(new int[]{175, 500, 1100, 1300}).setPriceWithHotel(1500).build());
        cells[37] = cell;

        cell = new TaxCell(38);
        cells[38] = cell;

        cell = new StreetCell(39);
        cell.setStreet(new ColouredStreet.ColouredStreetBuilder().setName("Boardwalk").setPrice(400).
                setCollateralPrice(200).setRent(50).setColour(Colour.BLUE).setHousePrice(200).
                setPriceWithHouses(new int[]{200, 600, 1400, 1700}).setPriceWithHotel(2000).build());
        cells[39] = cell;

        return cells;
    }

    public GameBoard () {}

    public Cell[] getGameBoard() {
        return gameBoard;
    }

    public GameBoard getGameBoardObject() {
        return this;
    }

    public Cell getCell(int position) {
        return gameBoard[position];
    }

    public void setCell (Cell cell) {
        gameBoard[cell.getPosition()] = cell;
    }

    public void setGameBoard(Cell[] gameBoard) {
        this.gameBoard = gameBoard;
    }

    protected void getStreetOnBail (Player player) {
        if (player.getMonopolyControl().isEmpty() && player.getCash() <= 0) {
            gameBoard = player.bankrupt(gameBoard);
            return;
        }
        Map<Colour, List<Cell>> streets = player.getMonopolyControl();
        sendMessage("You can set on Bail these streets: ");
        for (Map.Entry<Colour, List<Cell>> kv : streets.entrySet()) {
            sendMessage(kv.getKey().getName() + ": ");
            for (Cell value : kv.getValue()) {
                sendMessage(value.getStreet().getName() + " {" + value.getStreet().getMonopolyLevel()
                        + "} " + ", " );
            }
        }
    }

    protected void setStreetOnBail (String colour, Player player) {
        try {
            Colour c = Colour.fromStringToColour(colour);
            List<Cell> list = player.getMonopolyControl().get(c);
            sendMessage("Choose the street: ");
            int i = 1;
            for (Cell cell : list) {
                String message = java.text.MessageFormat.format("{0}) {1}", i, cell.getStreet().getName());
                sendMessage(message);
                i++;
            }
            int answer = checkAnswer(1, i);
            Cell cell = list.get(answer - 1);

            if (cell.getStreet().getMonopolyLevel() >= 2) {
                sendMessage("Before setting this street on Bail, you need to sell all houses and hotel");
                sendMessage("Would you like to sell them?");
                sendMessage("1) Yes           2) No");
                answer = checkAnswer(1, 2);
                if (answer == 1) {
                    gameBoard = player.sellHousesAndHotel(gameBoard, cell);
                    sendMessage("You sold all houses and hotel.");
                }
                else return;
            }
            gameBoard = player.setStreetOnBail(gameBoard, cell);
            String message = java.text.MessageFormat.format("You set your street on bail. You got {0}M.", cell.getStreet().getCollateralPrice());
            sendMessage(message);
        } catch (Exception e) {
            sendMessage("There is a mistake, please, try again!");
        }
    }

    protected void releaseStreetFromPledge (Player player) {
        if (player.getCash() <= 0 && player.getMonopolyControl().isEmpty()) {
            gameBoard = player.bankrupt(gameBoard);
            return;
        }
        List<Cell> streets = player.getOnBailStreets();
        sendMessage("You can release from pledge these streets: ");
        int i = 1;
        for (Cell c : streets) {
            String message = java.text.MessageFormat.format("{0}) {1}", i, c.getStreet().getName());
            sendMessage(message);
            i++;
        }
        int answer = checkAnswer(1, i);
        Cell street = streets.get(answer - 1);
        String message = java.text.MessageFormat.format("To release this street from pledge you need to pay {0}M.", street.getStreet().getCollateralPrice() * 1.1);
        sendMessage(message);
        sendMessage("Would you like to continue?");
        sendMessage("1)Yes      2)No");
        answer = checkAnswer(1, 2);
        if (answer == 1 && player.getCash() < street.getStreet().getCollateralPrice() * 1.1) {
            sendMessage("You can''t release this street from pledge");
            return;
        }
        if (answer == 1) gameBoard = player.releaseFromPledge(gameBoard, street);
    }

    protected void buildHouseOrHotel (Player player) {
        List<Monopoly> monopolies = player.getMonopolies();
        sendMessage("You can build a house or a hotel on these monopolies: ");
        int i = 0;
        for (Monopoly monopoly : monopolies) {
            if (!monopoly.isCompleted()) {
                i++;
                String message = java.text.MessageFormat.format("{0}) {1}", i, monopoly.getColour().getName());
                sendMessage(message);
            }
        }
        if (i == 0) {
            sendMessage("You''ve upgraded all your monopolies!");
            return;
        }
        int answer = checkAnswer(1, i);
        Monopoly monopoly = monopolies.get(answer - 1);

        sendMessage("On which street would you like to build a house/hotel?");
        i = 0;
        List<Cell> streets = monopoly.getStreets();
        for (Cell street : streets) {
            if (street.getColouredStreet().getMonopolyLevel() != 6) {
                i++;
                int price = street.getColouredStreet().getHousePrice();
                if (street.getColouredStreet().getMonopolyLevel() == 5) price = street.getColouredStreet().getHotelPrice();
                String message = java.text.MessageFormat.format("{0}) {1} costs {2}",
                        i, street.getStreet().getName(), price);
                sendMessage(message);
            }
        }
        if (i == 0) {
            sendMessage("All streets have hotels!");
            return;
        }
        answer = checkAnswer(1, i);
        Cell street = streets.get(answer - 1);

        int price = street.getColouredStreet().getHousePrice();
        if (street.getColouredStreet().getMonopolyLevel() == 5) price = street.getColouredStreet().getHotelPrice();
        if (player.getCash() < price) {
            sendMessage("You can''t afford buying a house/hotel. Would you like to set some streets on bail?");
            sendMessage("1)Yes       2)No");
            answer = checkAnswer(1, 2);
            if (answer == 2) return;
            else {
                while (player.getCash() < price && !player.getMonopolyControl().isEmpty()) getStreetOnBail(player);
                if (player.getCash() < price && player.getMonopolyControl().isEmpty()) {
                    sendMessage("You still can''t afford it.");
                    return;
                }
            }
        }
        gameBoard = player.buildHouseOrHotel(gameBoard, street);
    }

    protected void sellHouseOrHotel (Player player) {
        List<Monopoly> monopolies = player.getMonopolies();
        sendMessage("You can sell a house or a hotel on these monopolies: ");
        int i = 1;
        for (Monopoly monopoly : monopolies) {
            String message = java.text.MessageFormat.format("{0}) {1}", i, monopoly.getColour().getName());
            sendMessage(message);
            i++;
        }
        int answer = checkAnswer(1, i);
        Monopoly monopoly = monopolies.get(answer - 1);
        if (!monopoly.hasAnyHouse()) {
            sendMessage("This monopoly has no houses.");
            return;
        }
        sendMessage("On which street would you like to sell a house/hotel?");
        i = 1;
        List<Cell> streets = monopoly.getStreets();
        for (Cell street : streets) {
            String message = java.text.MessageFormat.format("{0}) {1}",
                    i, street.getStreet().getName());
            sendMessage(message);
            i++;
        }
        answer = checkAnswer(1, i);
        Cell street = streets.get(answer - 1);

        if (street.getStreet().getMonopolyLevel() < 2) {
            sendMessage("There are no houses on this street.");
            return;
        }
        gameBoard = player.sellHouseOrHotel(gameBoard, street);
    }

    public void checkCash (Player player, int price) {
        if (player.getCash() < price) {
            while (player.getCash() < price && !player.getMonopolyControl().isEmpty()) bailOrSell(player);
            if (player.getCash() < price && player.getMonopolyControl().isEmpty()) {
                gameBoard = player.bankrupt(gameBoard);
            }
        }
    }

    public int checkAnswer (int a, int b) {
        return 0;
    }

    protected void bailOrSell (Player player) {
        if (player.getMonopolies().isEmpty()) {
            sendMessage("You need to set some streets on bail.");
            getStreetOnBail(player);
        } else {
            sendMessage("You need to set some streets on bail or sell houses.");
            sendMessage("1) Sell house");
            sendMessage("2) Set street on bail");
            int answer = checkAnswer(1, 2);
            if (answer == 2) getStreetOnBail(player);
            else sellHouseOrHotel(player);
        }
    }

    protected void sendMessage(String message) {}

    public void street (Player player) {}
}
