package com.example.nfccardemulation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nfccardemulation.database.AppDatabase;
import com.example.nfccardemulation.entities.Card;
import com.example.nfccardemulation.entities.Cost;
import com.example.nfccardemulation.entities.CostsOfShop;
import com.example.nfccardemulation.entities.Shop;
import com.hadiidbouk.charts.BarData;
import com.hadiidbouk.charts.ChartProgressBar;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportsActivity extends AppCompatActivity {

    private static String select = null;
    private ChartProgressBar mChart;
    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        mChart = findViewById(R.id.ChartProgressBar);
        pieChart = findViewById(R.id.piechart);
        Intent intent = getIntent();
        int s = intent.getIntExtra("cardId", 0);

//        insertCosts();
        if (s > 0) {
            getCostsOfCard(s);
        } else {
            getAllCosts();
            getAllCostsByShop();
        }


//        insertCosts();

//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//
//        recyclerView = findViewById(R.id.recyclerView);
//        adapter = new RecyclerViewAdapter(cardList, this);
//
//        layoutManager = new LinearLayoutManager(getApplicationContext());
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
//        recyclerView.setAdapter(adapter);
//        getCards();
//        ((RecyclerViewAdapter) adapter).setClickListener(this);
//
//        adapter.notifyDataSetChanged();


//        BottomNavigationView navigation = findViewById(R.id.nav_view);
//        navigation.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.navigation_scan_card:
//                    Intent a = new Intent(ReportsActivity.this, GeneralActivity.class);
//                    startActivity(a);
//                    break;
//                case R.id.navigation_reports:
//                    break;
//                case R.id.navigation_notifications:
//                    Intent b = new Intent(ReportsActivity.this, NotificationActivity.class);
//                    startActivity(b);
//                    break;
//            }
//            return false;
//        });
    }
    private void getAllCosts() {
        List<Cost> costs;

        AppDatabase database = AppDatabase.getAppDatabse(getApplicationContext());
        costs = database.costDao().getAllCosts();
        ArrayList<BarData> dataList = new ArrayList<>();

        BarData data;

        for (Cost val : costs) {
            data = new BarData(getMonth(val.getDate()), val.price, val.price + " MDL");
            dataList.add(data);
        }

        mChart.setDataList(dataList);
        mChart.build();

    }

    private void getAllCostsByShop() {
        AppDatabase database = AppDatabase.getAppDatabse(getApplicationContext());

        List<CostsOfShop> costsOfShop = database.costDao().getCostsOfShop();
        String[] colors = { "#FE6DA8", "#56B7F1", "#CDA67F",  "#FED70E", "#FE6DA8", "#56B7F1"};

        int i = 0;
        for (CostsOfShop c: costsOfShop) {
            pieChart.addPieSlice(new PieModel(c.getName(), (int)c.getPrice(), Color.parseColor(colors[i++])));
        }

        pieChart.startAnimation();
    }

    private void getCostsOfCard(int cardId) {
        List<Cost> costs;
        AppDatabase database = AppDatabase.getAppDatabse(getApplicationContext());
        costs = database.costDao().getAllCostsOfCard(cardId);
        ArrayList<BarData> dataList = new ArrayList<>();

        BarData data;

        for (Cost val : costs) {
            data = new BarData(getMonth(val.getDate()), val.price, val.price + " MDL");
            dataList.add(data);
        }

        mChart.setDataList(dataList);
        mChart.build();

    }

    private void insertCosts() {
        AppDatabase database = AppDatabase.getAppDatabse(getApplicationContext());

        Shop shop = new Shop();
        shop.setName("Fourchette");
        shop.setCategory("Alimentar");
        database.shopDao().insertShops(shop);

        shop = new Shop();
        shop.setName("Metro Cash&Carry");
        shop.setCategory("Universal");
        database.shopDao().insertShops(shop);

        Cost cost = new Cost();
        cost.setCardId(2);
        cost.setPrice(69.6f);
        cost.setShopId(1);
        cost.setDate(new Date(2019, 8, 3));
        database.costDao().inserCosts(cost);

        cost = new Cost();
        cost.setCardId(2);
        cost.setPrice(33.3f);
        cost.setShopId(2);
        cost.setDate(new Date(2019, 9, 20));
        database.costDao().inserCosts(cost);

        cost = new Cost();
        cost.setCardId(2);
        cost.setPrice(15.1f);
        cost.setShopId(1);
        cost.setDate(new Date(2019, 3, 20));
        database.costDao().inserCosts(cost);

        cost = new Cost();
        cost.setCardId(1);
        cost.setPrice(30.1f);
        cost.setShopId(2);
        cost.setDate(new Date(2019, 4, 20));
        database.costDao().inserCosts(cost);

        cost = new Cost();
        cost.setCardId(1);
        cost.setPrice(58.1f);
        cost.setShopId(2);
        cost.setDate(new Date(2019, 5, 20));
        database.costDao().inserCosts(cost);

        cost = new Cost();
        cost.setCardId(1);
        cost.setPrice(73.5f);
        cost.setShopId(1);
        cost.setDate(new Date(2019, 6, 20));
        database.costDao().inserCosts(cost);

        cost = new Cost();
        cost.setCardId(1);
        cost.setPrice(120.5f);
        cost.setShopId(1);
        cost.setDate(new Date(2019, 1, 20));
        database.costDao().inserCosts(cost);

        cost = new Cost();
        cost.setCardId(1);
        cost.setPrice(220.f);
        cost.setShopId(1);
        cost.setDate(new Date(2019, 7, 20));
        database.costDao().inserCosts(cost);
//        Cost cost = new Cost();
//        cost.se
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                cardList.clear();
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private String getMonth(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
        simpleDateformat = new SimpleDateFormat("MMM");
        // three digit abbreviation
        return simpleDateformat.format(date);
    }

//    @Override
//    public void onClick(View v, int position) {
//        Intent intent = new Intent(ReportsActivity.this, NotificationActivity.class);
//        startActivity(intent);
//    }
}
