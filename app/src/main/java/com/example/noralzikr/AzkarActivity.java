package com.example.noralzikr;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class AzkarActivity extends AppCompatActivity {

    private ListView azkarListView;
    private ArrayList<Dhikr> list;
    private AzkarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar);


        azkarListView = findViewById(R.id.azkarListView);


        int categoryType = getIntent().getIntExtra("category_type", 0);
        list = new ArrayList<>();

        if (categoryType == 0) {

            list.add(new Dhikr(1, "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ وَالْحَمْدُ لِلَّهِ", 1));
            list.add(new Dhikr(2, "يَا حَيُّ يَا قَيُّومُ بِرَحْمَتِكَ أَسْتَغِيثُ", 3));
        } else if (categoryType == 1) {

            list.add(new Dhikr(3, "أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ وَالْحَمْدُ لِلَّهِ", 1));
            list.add(new Dhikr(4, "اللَّهُمَّ بِكَ أَمْسَيْنَا وَبِكَ أَصْبَحْنَا", 1));
        } else {

            list.add(new Dhikr(5, "بِاسْمِكَ رَبِّي وَضَعْتُ جَنْبِي", 1));
            list.add(new Dhikr(6, "اللَّهُمَّ قِنِي عَذَابَكَ يَوْمَ تَبْعَثُ وعِبَادَكَ", 3));
        }


        adapter = new AzkarAdapter(this, list);
        azkarListView.setAdapter(adapter);
    }
}
