package ru.vsu.csf.Sashina.game;

import ru.vsu.csf.Sashina.player.Player;

public interface GameInterface {

    void gameMenu(Player player, boolean[] actions);

    void getStreetOnBail(Player player);

    int checkAnswer(int a, int b);

    void sendMessage(String message);
}
