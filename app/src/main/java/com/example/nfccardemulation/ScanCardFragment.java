package com.example.nfccardemulation;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import io.card.payment.CardIOActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanCardFragment extends Fragment {

    int REQUEST_SCAN = 101;
    int REQUEST_AUTOTEST = 200;
    @BindView(R.id.btnScan)
    Button btnScan;

    public ScanCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan_card, container, false);

    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnScan:
//                Toast.makeText(getActivity(), "click", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), CardIOActivity.class)
//                        .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
//                        .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)
//                        .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true)
//                        .putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true)
//                        .putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "en")
//                        .putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, Color.GREEN)
//                        .putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true);
//                startActivityForResult(intent, REQUEST_SCAN);
//                break;
//            default:
//                break;
//        }
//    }

}
