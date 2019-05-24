package com.example.nfccardemulation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import com.example.nfccardemulation.database.AppDatabase;
import com.example.nfccardemulation.entities.Card;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class MainActivity extends AppCompatActivity {

    int REQUEST_SCAN = 101;
    int REQUEST_AUTOTEST = 200;
    @BindView(R.id.btnScan) Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardIOActivity.class)
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
                card.setExpiration(22);
                card.setUserId(1);
                database.cardDao().insertCars(card);
                Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(this, "Save failed", Toast.LENGTH_LONG);
            }
        }
    }
}
