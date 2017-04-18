package com.abhat.groceriesmvp.activities;

import java.util.ArrayList;

/**
 * Created by cumulations on 18/4/17.
 */

public interface MainView {
    void showProgressBar();
    void hideProgressBar();
    void setGroceryPrices(ArrayList<String> groceries, ArrayList<String> groceryPrices);
}
