package com.example.noralzikr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView mainGridView;
    private ArrayList<GridItem> list;
    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. ربط الـ GridView من الواجهة الـ XML
        mainGridView = findViewById(R.id.mainGridView);

        // 2. تجهيز أقسام الشاشة الرئيسية الأربعة
        list = new ArrayList<>();
        list.add(new GridItem("الأذكار", R.drawable.ic_quran));
        list.add(new GridItem("الاقتباسات", R.drawable.ic_trending));
        list.add(new GridItem("الإحصائيات", R.drawable.ic_favorite));
        list.add(new GridItem("الإعدادات", R.drawable.ic_settings));

        // 3. تشغيل الأداپتر وعرض العناصر داخل الشبكة
        adapter = new GridAdapter(this, list);
        mainGridView.setAdapter(adapter);

        // 4. تفعيل حدث الضغط والتنقل باستخدام الـ Intent
        mainGridView.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                // عند الضغط على الكرت الأول (الأذكار) -> يفتح شاشة التصنيفات
                Intent intent = new Intent(MainActivity.this, AzkarCategoriesActivity.class);
                startActivity(intent);
            } else if (position == 3) {
                // عند الضغط على الكرت الرابع (الإعدادات) -> يفتح شاشة عن التطبيق وبيانات الطلاب
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}