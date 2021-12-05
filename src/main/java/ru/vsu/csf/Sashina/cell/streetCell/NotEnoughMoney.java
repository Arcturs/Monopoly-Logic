package ru.vsu.csf.Sashina.cell.streetCell;

public class NotEnoughMoney extends RuntimeException{
    public NotEnoughMoney(String message) {
        super(message);
    }
}
