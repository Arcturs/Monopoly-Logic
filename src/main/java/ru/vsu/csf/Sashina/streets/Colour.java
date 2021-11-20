package ru.vsu.csf.Sashina.streets;

import java.util.Locale;

public enum Colour {
    BROWN("Brown"),
    CYAN("Cyan"),
    PINK("Pink"),
    ORANGE("Orange"),
    RED("Red"),
    YELLOW("Yellow"),
    GREEN("Green"),
    BLUE("Blue"),
    RL("Railroad"),
    E("Energy");

    private String code;

    Colour (String code) {
        this.code = code;
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


}
