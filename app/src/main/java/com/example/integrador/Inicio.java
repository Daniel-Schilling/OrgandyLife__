package com.example.integrador;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.view.View;

public class Inicio extends AppCompatActivity {

    private Button btConfigurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.inicializaComponentes();
        this.btConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itInicio = new Intent(Inicio.this, configuracao2.class);

                startActivity(itInicio);
                finish();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void inicializaComponentes() {
        this.btConfigurar = (findViewById(R.id.bt_configurar));
    }

}
