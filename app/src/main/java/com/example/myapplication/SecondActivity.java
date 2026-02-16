package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ListView listViewBasket;
    private ArrayList<Good> selectedGoods;
    private BasketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listViewBasket = findViewById(R.id.listViewBasket);

        // Получаем переданные товары
        selectedGoods = (ArrayList<Good>) getIntent().getSerializableExtra("selected_goods");

        if (selectedGoods == null) {
            selectedGoods = new ArrayList<>();
        }

        adapter = new BasketAdapter(this, selectedGoods);
        listViewBasket.setAdapter(adapter);
    }
}

