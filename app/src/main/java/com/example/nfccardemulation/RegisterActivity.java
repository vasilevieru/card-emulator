package com.example.nfccardemulation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.nfccardemulation.database.AppDatabase;
import com.example.nfccardemulation.entities.User;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    //    private String BASE_URL = "http://172.16.2.232:5896/api/users/";
    private static final String TAG = "SignupActivity";

    //    @BindView(R.id.input_name)
    private EditText _nameText;
    //    @BindView(R.id.input_email)
    private EditText _emailText;
    //    @BindView(R.id.input_password)
    private EditText _passwordText;
    //    @BindView(R.id.btn_signup)
    private Button _signupButton;
    //    @BindView(R.id.link_login)
    private TextView _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        _nameText = findViewById(R.id.input_name);
        _emailText = findViewById(R.id.input_email);
        _passwordText = findViewById(R.id.input_password);
        _signupButton = findViewById(R.id.btn_signup);
        _loginLink = findViewById(R.id.link_login);

        _signupButton.setOnClickListener(v -> signup());

        _loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
//        _nameText = findViewById(R.id.input_name);
//        _emailText = findViewById(R.id.input_email);
//        _passwordText = findViewById(R.id.input_password);
//        _signupButton = findViewById(R.id.btn_signup);
//        setContentView(R.layout.activity_register);
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.

        User user = new User();
        user.setName(_nameText.getText().toString());
        user.setEmail(_emailText.getText().toString());
        user.setPassword(_passwordText.getText().toString());

        AppDatabase database = AppDatabase.getAppDatabse(getApplicationContext());

        database.userDao().insertUser(user);

        progressDialog.dismiss();
        Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void sendWorkPostRequest() {
        String urlString = "http:172.16.2.232:5896/api/users/1";

        URL url = null;
        try {
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Name", _nameText.getText().toString());
            connection.setRequestProperty("Email", _emailText.getText().toString());
            connection.setRequestProperty("Password", _passwordText.getText().toString());
            connection.setDoOutput(true);

            OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
            outputStream.flush();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
