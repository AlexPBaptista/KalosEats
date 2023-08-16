package com.example.kaloseatsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://172.18.0.1/kaloseats";
    private static final String USER = "alexandre";
    private static final String PASSWORD = "password123";
    public TextView email = findViewById(R.id.et_email);
    public TextView password = findViewById(R.id.et_password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new InfoAsyncTask().execute();
    }

    public void abreregisto(View view) {
        startActivity(new Intent(register.this, login.class));
    }
    @SuppressLint("StaticFieldLeak")
    private class InfoAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voLid) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String nome =  email.getText().toString().split("@")[0];

                String sql = "INSERT INTO contasapp (nome, email, password) VALUES (" +
                        "'"+nome+"', '"+email.getText()+"', '"+password.getText()+"'" +
                        ");";

                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();


            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }

            return info;
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            startActivity(new Intent(register.this, login.class));
        }
    }
}