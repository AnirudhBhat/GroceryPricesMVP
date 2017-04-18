package com.abhat.groceriesmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.abhat.groceriesmvp.activities.MainView;
import com.abhat.groceriesmvp.adapters.GroceryAdapter;
import com.abhat.groceriesmvp.network.GroceryInteractorImpl;
import com.abhat.groceriesmvp.presenters.GroceryPresenter;
import com.abhat.groceriesmvp.presenters.GroceryPresenterImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView{

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private GroceryPresenter mGroceryPresenter;
    private GroceryAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.setContext(this);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView)findViewById(R.id.groceriesRecyclerView);
        mAdapter = new GroceryAdapter(this, new ArrayList<String>(), new ArrayList<String>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGroceryPresenter = new GroceryPresenterImpl(this, new GroceryInteractorImpl());
        mGroceryPresenter.fetchGroceryPrices();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setGroceryPrices(ArrayList<String> groceries, ArrayList<String> groceryPrices) {
        mRecyclerView.setAdapter(new GroceryAdapter(this, groceries, groceryPrices));
    }
}