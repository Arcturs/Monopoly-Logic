package ru.vsu.csf.Sashina.streets;

import java.awt.*;
import java.util.Locale;

public enum Colour {
    BROWN("Brown", new Color(149, 49, 1)),
    CYAN("Cyan", new Color(60, 174, 233)),
    PINK("Pink", new Color(191, 23, 87)),
    ORANGE("Orange", new Color(240, 136, 17)),
    RED("Red", new Color(227, 32, 32)),
    YELLOW("Yellow", new Color(235, 232, 56)),
    GREEN("Green", new Color(40, 199, 38)),
    BLUE("Blue", new Color(45, 15, 191)),
    RL("Railroad", new Color(0, 0, 0)),
    E("Energy", new Color(222, 131, 222));

    private String code;
    private Color color;

    Colour (String code, Color color) {
        this.code = code;
        this.color = color;
    }

    public static Colour fromStringToColour(String s) throws Exception{
        s = s.toLowerCase(Locale.ROOT);
        if (s.equals("blue")) return BLUE;
        if (s.equals("brown")) return BROWN;
        if (s.equals("energy")) return E;
        if (s.equals("cyan")) return CYAN;
        if (s.equals("railroad")) return RL;
        if (s.equals("green")) return GREEN;
        if (s.equals("orange")) return ORANGE;
        if (s.equals("pink")) return PINK;
        if (s.equals("red")) return RED;
        if (s.equals("yellow")) return YELLOW;
        else throw new Exception();
    }

    public String getName() {
        return code;
    }

    public Color getColor() {
        return color;
    }
}
