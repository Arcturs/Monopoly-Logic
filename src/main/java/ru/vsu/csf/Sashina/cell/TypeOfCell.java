package ru.vsu.csf.Sashina.cell;

public enum TypeOfCell {
    START("Start"),
    STREET("Street"),
    CHANCE("Chance"),
    PUBLIC_TREASURY("Public Treasury"),
    TAX("Tax"),
    JAIL("Jail"),
    FREE_STOP("Free Stop"),
    GO_TO_JAIL("Go to jail");

    private String code;

    TypeOfCell (String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
