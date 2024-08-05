package com.example.projetoandroidteste1;

import com.example.projetoandroidteste1.R;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class EditComandaActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProdutos;
    private ProdutoAdapter produtoAdapter;
    private List<Produto> produtos;
    private Comanda comanda;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_comanda);

        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);
        btnSalvar = findViewById(R.id.btnSalvar);

        int comandaNumero = getIntent().getIntExtra("comandaNumero", -1);
        if (comandaNumero == -1) {
            finish();
            return;
        }

        comanda = ComandasData.getComanda(comandaNumero);
        if (comanda == null) {
            Toast.makeText(this, "Número da comanda inválido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        produtos = new ArrayList<>(comanda.getProdutos().values());

        produtoAdapter = new ProdutoAdapter(produtos, comanda);
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProdutos.setAdapter(produtoAdapter);

        btnSalvar.setOnClickListener(v -> {
            ComandasData.salvarComanda(comanda);
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();

        });
    }
}
