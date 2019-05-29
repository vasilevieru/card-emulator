package com.example.nfccardemulation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.nfccardemulation.adapters.RecyclerViewAdapter;
import com.example.nfccardemulation.database.AppDatabase;
import com.example.nfccardemulation.entities.Card;
import com.example.nfccardemulation.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends AppCompatActivity implements ItemClickListener {

    private static List<Card> cardList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getCards(preferences.getInt("userId", 1));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(cardList, this);

        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
        ((RecyclerViewAdapter) adapter).setClickListener(this);

        adapter.notifyDataSetChanged();

    }

    private void getCards(int userId) {
        AppDatabase database = AppDatabase.getAppDatabse(getApplicationContext());

        cardList = database.cardDao().getAllCards(userId);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(CardListActivity.this, ReportsActivity.class);
        intent.putExtra("cardId", cardList.get(position).getId());
        startActivity(intent);
    }
}
