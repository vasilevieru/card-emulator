package com.example.nfccardemulation;

import android.content.Intent;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nfccardemulation.adapters.RecyclerViewAdapter;
import com.example.nfccardemulation.constants.Constants;
import com.example.nfccardemulation.entities.Card;
import com.example.nfccardemulation.interfaces.ItemClickListener;
import com.example.nfccardemulation.interfaces.OnItemClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hadiidbouk.charts.BarData;
import com.hadiidbouk.charts.ChartProgressBar;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReportsActivity extends AppCompatActivity {

    private static List<Card> cardList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private static String select = null;
    private ChartProgressBar mChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        getCosts();

//        mChart = findViewById(R.id.ChartProgressBar);
//
//        ArrayList<BarData> dataList = new ArrayList<>();
//
//        BarData data = new BarData("Sept", 3.4f, "3.4$");
//
//        dataList.add(data);
//
//        data = new BarData("Oct", 8f, "8$");
//        dataList.add(data);
//
//        data = new BarData("Nov", 1.8f, "1.8$");
//        dataList.add(data);
//
//        data = new BarData("Sept", 7.3f, "7.3");
//        dataList.add(data);
//
//
//        mChart.setDataList(dataList);
//        mChart.build();
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

    private void getCosts() {
//        HttpURLConnection connection = null;
//        try {
//            URL url = new URL(Constants.url + "/costs");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            InputStream inputStream = connection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream);
//
//            BufferedReader bufferedReader = new BufferedReader(reader);
//            String line = bufferedReader.readLine();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            connection.disconnect();
//        }

        HttpClient client = new DefaultHttpClient();

        HttpGet get = new HttpGet(Constants.url + "/costs");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpContext localContext = new BasicHttpContext();

        HttpResponse response = null;
        try {
            response = client.execute(get, localContext);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                String result = EntityUtils.toString(response.getEntity());


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void getCards() {


        ArrayList<BarData> dataList = new ArrayList<>();

        BarData data = new BarData("Sept", 3.4f, "3.4$");

        dataList.add(data);

        data = new BarData("Oct", 8f, "8$");
        dataList.add(data);

        data = new BarData("Nov", 1.8f, "1.8$");
        dataList.add(data);

        data = new BarData("Sept", 7.3f, "7.3");
        dataList.add(data);


        mChart.setDataList(dataList);
        mChart.build();

//        Card card = new Card();
//        card.setNumber("2133 2345 1235 8657");
//        card.setCvv("234");
//        card.setExpiration(23);
//        cardList.add(card);
//
//        return cardList;
    }

//    @Override
//    public void onClick(View v, int position) {
//        Intent intent = new Intent(ReportsActivity.this, NotificationActivity.class);
//        startActivity(intent);
//    }
}
