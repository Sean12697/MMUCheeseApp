package com.example.seand.mmucheeseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        Cheese theCheese = (Cheese) extras.get("cheese");
        System.out.println("received from the intent: "+ theCheese.name);

        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description);
        title.setText(theCheese.name);
        description.setText(theCheese.description);
    }
}
