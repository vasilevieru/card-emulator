package com.example.nfccardemulation

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQUEST_SCAN = 101
    val REQUEST_AUTOTEST = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnScan.setOnClickListener {
            var intent = Intent(this@MainActivity, CardIOActivity::class.java)
            .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
            .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)
            .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true)
            .putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true)
            .putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "en")
            .putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, Color.GREEN)
            .putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true)
        startActivityForResult(intent, REQUEST_SCAN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_SCAN || requestCode == REQUEST_AUTOTEST) && data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            number.visibility = View.VISIBLE
            expirationDate.visibility = View.VISIBLE
            cvv.visibility = View.VISIBLE
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                val scanResult = data.getParcelableExtra<CreditCard>(CardIOActivity.EXTRA_SCAN_RESULT)
                // Never log a raw card number. Avoid displaying it, but if necessary use scanResult.formattedCardNumber can intend of .redactedCardNumber
                number.text = "Card Number: " + scanResult.formattedCardNumber
                if (scanResult.isExpiryValid) {
                    expirationDate.text = "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear
                }
                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    cvv.text = "CVV has " + scanResult.cvv.length + " digits"
                }
            } else {
                number.visibility = View.GONE
                expirationDate.visibility = View.GONE
                cvv.visibility = View.GONE
                resultDisplayStr.visibility = View.VISIBLE
                resultDisplayStr.text = "Scan was canceled."
            }
        }
    }
}
