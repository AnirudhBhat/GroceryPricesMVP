package com.abhat.groceriesmvp.network;

import java.util.ArrayList;

/**
 * Created by cumulations on 18/4/17.
 */

public interface GroceryInteractor {
    interface onGroceryPricesFetchFinished {
        void networkError();

        void onSuccess(ArrayList<String> groceries, ArrayList<String> groceryPrices);
    }

    void fetchGroceryPrices(onGroceryPricesFetchFinished listener);
}
