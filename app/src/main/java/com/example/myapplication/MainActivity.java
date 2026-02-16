package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewGoods;
    private TextView tvSelectedCount;
    private Button btnShowChecked;

    private ArrayList<Good> goodsList;
    private GoodsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewGoods = findViewById(R.id.listViewGoods);

        // --- Создаем список товаров ---
        goodsList = new ArrayList<>();
        goodsList.add(new Good(1, "Laptop", 1200));
        goodsList.add(new Good(2, "Phone", 800));
        goodsList.add(new Good(3, "Tablet", 500));
        goodsList.add(new Good(4, "Headphones", 200));
        goodsList.add(new Good(5, "Keyboard", 150));

        // --- Добавляем Header ---
        View header = LayoutInflater.from(this)
                .inflate(R.layout.header_mygoods, listViewGoods, false);
        listViewGoods.addHeaderView(header);

        // --- Добавляем Footer ---
        View footer = LayoutInflater.from(this)
                .inflate(R.layout.footer_mygoods, listViewGoods, false);
        listViewGoods.addFooterView(footer);

        tvSelectedCount = footer.findViewById(R.id.tvSelectedCount);
        btnShowChecked = footer.findViewById(R.id.btnShowChecked);

        // --- Создаем адаптер ---
        adapter = new GoodsAdapter(this, goodsList, new GoodsAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheckChanged() {
                updateSelectedCount();
            }
        });

        listViewGoods.setAdapter(adapter);

        // --- Кнопка перехода во вторую Activity ---
        btnShowChecked.setOnClickListener(v -> {
            ArrayList<Good> selectedGoods = new ArrayList<>();

            for (Good good : goodsList) {
                if (good.isChecked()) {
                    selectedGoods.add(good);
                }
            }

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("selected_goods", selectedGoods);
            startActivity(intent);
        });
    }

    // --- Обновление счетчика ---
    private void updateSelectedCount() {
        int count = 0;

        for (Good good : goodsList) {
            if (good.isChecked()) {
                count++;
            }
        }

        tvSelectedCount.setText("Selected items: " + count);
    }
}