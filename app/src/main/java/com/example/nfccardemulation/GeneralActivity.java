package com.example.nfccardemulation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import com.example.nfccardemulation.database.AppDatabase;
import com.example.nfccardemulation.entities.Card;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class GeneralActivity extends AppCompatActivity {
    //    private TextView mTextMessage;
//    final Fragment scanCardFragment = new ScanCardFragment();
//    final Fragment reportFragment = new ReportsFragment();
//    final Fragment notificationFragment = new NotificationFragment();
//    final FragmentManager fm = getSupportFragmentManager();
//    Fragment active = scanCardFragment;
    int REQUEST_SCAN = 101;
    int REQUEST_AUTOTEST = 200;
    private Button btnScan;
    SharedPreferences preferences;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        private static final int CONTENT_VIEW_ID = 10101010;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_scan_card:
//                    fm.beginTransaction().hide(active).show(scanCardFragment).commit();
//                    active = scanCardFragment;
                    break;
                case R.id.navigation_reports:
                      Intent a = new Intent(GeneralActivity.this, ReportsActivity.class);
                      startActivity(a);
//                    fm.beginTransaction().hide(active).show(reportFragment).commit();
//                    active = reportFragment;
                    break;

                case R.id.navigation_cards:
                    Intent c = new Intent(GeneralActivity.this, CardListActivity.class);
                    startActivity(c);
                    break;
                case R.id.navigation_notifications:
                    Intent b = new Intent(GeneralActivity.this, ReportsActivity.class);
                    startActivity(b);
//                    fm.beginTransaction().hide(active).show(notificationFragment).commit();
//                    active = notificationFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        btnScan = findViewById(R.id.btnScan);
//        fm.beginTransaction().add(R.id.main_container, notificationFragment, "3").hide(notificationFragment).commit();
//        fm.beginTransaction().add(R.id.main_container, reportFragment, "2").hide(reportFragment).commit();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        fm.beginTransaction().add(R.id.main_container, scanCardFragment, "1").commit();
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeneralActivity.this, CardIOActivity.class)
                        .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
                        .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)
                        .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true)
                        .putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true)
                        .putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "en")
                        .putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, Color.GREEN)
                        .putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true);
                startActivityForResult(intent, REQUEST_SCAN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_SCAN || requestCode == REQUEST_AUTOTEST) && data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                AppDatabase database = AppDatabase.getAppDatabse(this);
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                Card card = new Card();
                card.setNumber(scanResult.cardNumber);
                card.setCvv(scanResult.cvv);
                card.setExpiration(scanResult.expiryYear);
                card.setUserId(preferences.getInt("userId", 1));
                database.cardDao().insertCars(card);
                Toast.makeText(GeneralActivity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Save failed", Toast.LENGTH_LONG).show();
            }
        }
    }

}
