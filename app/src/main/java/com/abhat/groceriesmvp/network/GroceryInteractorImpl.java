package com.abhat.groceriesmvp.network;

import android.util.Log;

import com.abhat.groceriesmvp.App;
import com.abhat.groceriesmvp.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anirudh on 18/4/17.
 */

public class GroceryInteractorImpl implements GroceryInteractor {

    private ArrayList<String> Vegetable;
    private ArrayList<String> price;
    private onGroceryPricesFetchFinished mListener;
    @Override
    public void fetchGroceryPrices(onGroceryPricesFetchFinished listener) {
        this.mListener = listener;
        groceryPrices.start();
    }

    Thread groceryPrices = new Thread(new Runnable() {
        @Override
        public void run() {
            if (isOnline()) {
                groceryPrices();
            } else {
                ((MainActivity)App.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mListener.networkError();
                    }
                });
            }
        }
    });


    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    private void groceryPrices() {
        String url = "http://www.hopcoms.kar.nic.in/RateList.aspx";
        Vegetable = new ArrayList<String>();
        price = new ArrayList<String>();
        try {
            Document doc  = Jsoup.connect(url).get();
            int j = 2;
            for (int i = 0; i < 120; i++) {
                Elements elem = doc.select("span#ctl00_LC_grid1_ctl" + ((j < 10 )? 0 + "" + j : j) + "_Label" + 1);
                Elements elem1 = doc.select("span#ctl00_LC_grid1_ctl" + ((j < 10 )? 0 + "" + j : j) + "_Label" + 2);
                Elements elem2 = doc.select("span#ctl00_LC_grid1_ctl" + ((j < 10 )? 0 + "" + j : j) + "_Label" + 3);
                Elements elem3 = doc.select("span#ctl00_LC_grid1_ctl" + ((j < 10 )? 0 + "" + j : j) + "_Label" + 4);
                Vegetable.add(elem.get(0).text());
                Vegetable.add(elem2.get(0).text());
                price.add(elem1.get(0).text());
                price.add(elem3.get(0).text());
                j++;
            }

        } catch (Exception e) {
            //e.printStackTrace();
            for (int k=0;k<Vegetable.size(); k++) {
                Log.d("VEGETABLES", "Vegetable: " + Vegetable.get(k) + "   Price: " + price.get(k));
            }
            setAdapter();
        }
    }

    private void setAdapter() {
        ((MainActivity)App.getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mListener != null) {
                    mListener.onSuccess(Vegetable, price);
                }
            }
        });
    }
}
