package com.example.test3;

/**
 * Created by Денис on 12.08.2017.
 */

public class CurrencyHelper {

    public String formatPrice(String title, Number price) {
        if (price == null) {
            return title;
        } else {
            return (title + " Цена: " + price + " руб.");
        }
    }
}
