package com.example.listadetarefas;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdicionar;
    ArrayList<String> itens;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        itens = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                itens
        );

        listView.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v -> {
            itens.add("Item " + (itens.size() + 1));
            adapter.notifyDataSetChanged();
        });
    }
}