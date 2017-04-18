package com.abhat.groceriesmvp.presenters;

import com.abhat.groceriesmvp.activities.MainView;
import com.abhat.groceriesmvp.network.GroceryInteractor;

import java.util.ArrayList;

/**
 * Created by cumulations on 18/4/17.
 */

public class GroceryPresenterImpl implements GroceryPresenter, GroceryInteractor.onGroceryPricesFetchFinished {

    private MainView mMainView;
    private GroceryInteractor mGroceryInteractor;

    public GroceryPresenterImpl(MainView mainView, GroceryInteractor groceryInteractor) {
        this.mMainView = mainView;
        this.mGroceryInteractor = groceryInteractor;
    }

    @Override
    public void fetchGroceryPrices() {
        if (mMainView != null) {
            mMainView.showProgressBar();
            mGroceryInteractor.fetchGroceryPrices(this);
        }
    }

    @Override
    public void networkError() {

    }

    @Override
    public void onSuccess(ArrayList<String> groceries, ArrayList<String> groceryPrices) {
        mMainView.hideProgressBar();
        mMainView.setGroceryPrices(groceries, groceryPrices);
    }
}
