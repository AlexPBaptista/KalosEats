package com.example.kaloseatsapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://172.18.0.1/kaloseats";
    private static final String USER = "alexandre";
    private static final String PASSWORD = "password123";

        private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.llfp);
        new InfoAsyncTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT nomeEstabelecimento FROM estabelecimentos";

                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                int rowcount = 0;
                if (resultSet.last()) {
                    rowcount = resultSet.getRow();
                    resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
                }
                for (int i = 1; i <= rowcount; i++) {
                    sql = "SELECT nomeEstabelecimento, morada FROM estabelecimentos WHERE idEstabelecimento='" + Integer.toString(i) + "'";

                    statement = connection.prepareStatement(sql);
                    resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        info.put("nomeEstabelecimento" + Integer.toString(i), resultSet.getString(1));
                        info.put("morada" + Integer.toString(i), resultSet.getString(2));
                    }
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }

            return info;
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (!result.isEmpty()) {
                TextView NomeEstabelecimento1 = findViewById(R.id.txt_NomeEstabelecimento1);
                TextView NomeEstabelecimento2 = findViewById(R.id.txt_NomeEstabelecimento2);
                TextView Desc1 = findViewById(R.id.txt_Desc1);
                TextView Desc2 = findViewById(R.id.txt_Desc2);

                NomeEstabelecimento1.setText(result.get("nomeEstabelecimento1"));
                NomeEstabelecimento2.setText(result.get("nomeEstabelecimento2"));
                Desc1.setText(result.get("morada1"));
                Desc2.setText(result.get("morada2"));
            }
        }
    }
}