package com.example.kaloseatsapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class nemmeaccredito extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://172.18.0.1/kaloseats";
    private static final String USER = "alexandre";
    private static final String PASSWORD = "password123";

    private LinearLayout linearLayout;
    public List<List> idList = new ArrayList<List>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.llfp);
        new  InfoAsyncTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class InfoAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voLid) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                ConstraintLayout.LayoutParams clp = new Constraints.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                ConstraintLayout.LayoutParams iep = new Constraints.LayoutParams(
                        275, 275
                );
                ConstraintLayout.LayoutParams tnp = new Constraints.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                String sql = "SELECT nomeEstabelecimento FROM estabelecimentos";

                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                int rowcount = 0;
                if (resultSet.last()) {
                    rowcount = resultSet.getRow();
                    resultSet.beforeFirst();
                    // not rs.first() because the rs.next() below will move on, missing the first element
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
                TextView NomeEstabelecimento3 = findViewById(R.id.txt_NomeEstabelecimento3);
                TextView NomeEstabelecimento4 = findViewById(R.id.txt_NomeEstabelecimento4);
                TextView NomeEstabelecimento5 = findViewById(R.id.txt_NomeEstabelecimento5);
                TextView Desc = findViewById(R.id.txt_Desc1);
                TextView Desc2 = findViewById(R.id.txt_Desc2);
                TextView Desc3 = findViewById(R.id.txt_Desc3);
                TextView Desc4 = findViewById(R.id.txt_Desc4);
                TextView Desc5 = findViewById(R.id.txt_Desc5);

                NomeEstabelecimento1.setText(result.get("nomeEstabelecimento1"));
                NomeEstabelecimento2.setText(result.get("nomeEstabelecimento2"));
                NomeEstabelecimento3.setText(result.get("nomeEstabelecimento3"));
                NomeEstabelecimento4.setText(result.get("nomeEstabelecimento4"));
                NomeEstabelecimento5.setText(result.get("nomeEstabelecimento5"));
                Desc.setText(result.get("morada1"));
                Desc2.setText(result.get("morada2"));
                Desc3.setText(result.get("morada3"));
                Desc4.setText(result.get("morada4"));
                Desc5.setText(result.get("morada5"));
            }
        }
    }
}