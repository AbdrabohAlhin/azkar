package com.example.noralzikr;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AzkarCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar_categories);

        CardView cardSabah = findViewById(R.id.cardSabah);
        CardView cardMasa = findViewById(R.id.cardMasa);
        CardView cardNom = findViewById(R.id.cardNom);


        cardSabah.setOnClickListener(v -> openAzkarScreen(0));


        cardMasa.setOnClickListener(v -> openAzkarScreen(1));


        cardNom.setOnClickListener(v -> openAzkarScreen(2));
    }

    private void openAzkarScreen(int categoryType) {
        Intent intent = new Intent(AzkarCategoriesActivity.this, AzkarActivity.class);
        intent.putExtra("category_type", categoryType);
        startActivity(intent);
    }
}
