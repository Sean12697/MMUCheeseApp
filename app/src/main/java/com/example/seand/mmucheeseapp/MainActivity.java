package com.example.seand.mmucheeseapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ListView cheeseList;
    public ArrayList<Cheese> allCheeses;
    public String[] cheeseNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cheeseList = (ListView)findViewById(R.id.cheeseList);
        allCheeses = new ArrayList<>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HttpURLConnection urlConnection;
        InputStream in = null;
        try {
            URL url = new URL("http://radikaldesign.co.uk/sandbox/index.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
            e.printStackTrace();
            }
        String response = convertStreamToString(in);

        try {
            JSONArray jsonArray = new JSONArray(response);
            cheeseNames = new String[jsonArray.length()];
            for (int i=0; i < jsonArray.length(); i++)   {
                String name = jsonArray.getJSONObject(i).get("name").toString();
                String description = jsonArray.getJSONObject(i).get("description").toString();
                cheeseNames[i] = name;
                Cheese cheese = new Cheese(name, description);
                allCheeses.add(cheese);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cheeseNames);
        cheeseList.setAdapter(arrayAdapter);

        cheeseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //Toast.makeText(MainActivity.this, "you pressed " + allCheeses.get(i).name, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra("cheese", allCheeses.get(i));
                startActivity(intent);
            }
        });
    }

    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
