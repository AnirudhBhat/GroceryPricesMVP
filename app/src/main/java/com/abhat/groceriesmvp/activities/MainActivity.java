package com.abhat.groceriesmvp;

import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.abhat.groceriesmvp.activities.MainView;
import com.abhat.groceriesmvp.adapters.GroceryAdapter;
import com.abhat.groceriesmvp.network.GroceryInteractorImpl;
import com.abhat.groceriesmvp.presenters.GroceryPresenter;
import com.abhat.groceriesmvp.presenters.GroceryPresenterImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView{

    private RecyclerView mRecyclerView;
    private RelativeLayout mRelativeLayout;
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
        mRelativeLayout = (RelativeLayout)findViewById(R.id.activity_main);
        mAdapter = new GroceryAdapter(this, new ArrayList<String>(), new ArrayList<String>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mGroceryPresenter = new GroceryPresenterImpl(this, new GroceryInteractorImpl());
        mGroceryPresenter.fetchGroceryPrices();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
    public void showNetworkError() {
        Snackbar.make(mRelativeLayout, "No internet connection, Please try again later!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setGroceryPrices(ArrayList<String> groceries, ArrayList<String> groceryPrices) {
        mAdapter.setLists(groceries, groceryPrices);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        android.widget.SearchView searchView = (android.widget.SearchView)
                MenuItemCompat.getActionView(menu.findItem(R.id.search));

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mAdapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.filter(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
