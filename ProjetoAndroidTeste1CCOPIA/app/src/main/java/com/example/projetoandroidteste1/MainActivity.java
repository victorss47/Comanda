package com.example.projetoandroidteste1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ComandaAdapter adapter;
    private List<Comanda> comandas;
    private FloatingActionButton fab;
    private Button btnExcluirComanda;

    private final ActivityResultLauncher<Intent> editComandaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // Atualizar a lista de comandas quando voltar
                    comandas = new ArrayList<>(ComandasData.getAllComandas());
                    adapter.notifyDataSetChanged();
                }
            }
    );

    private static Context context;

    @Override
    protected void onResume() {
        super.onResume();
        comandas.clear();
        comandas.addAll(ComandasData.getAllComandas());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ComandasData.initialize(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> criarNovaComanda());

        btnExcluirComanda = findViewById(R.id.btnExcluirComanda);
        btnExcluirComanda.setOnClickListener(v -> excluirComanda());

        comandas = new ArrayList<>(ComandasData.getAllComandas());

        adapter = new ComandaAdapter(comandas);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try {
                    Comanda comanda = comandas.get(position);
                    Intent intent = new Intent(MainActivity.this, EditComandaActivity.class);
                    intent.putExtra("comandaNumero", comanda.getNumero());
                    editComandaLauncher.launch(intent);
                } catch (Exception e) {
                    Log.e("MainActivity", "Erro ao clicar em comanda", e);
                    Toast.makeText(MainActivity.this, "Erro ao abrir comanda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // Long click
            }


        }));
    }

    private void criarNovaComanda() {
        int novoNumero = gerarNumeroDeComanda();

        if (novoNumero == -1) {
            Toast.makeText(this, "Não é possível criar mais comandas.", Toast.LENGTH_SHORT).show();
            return;
        }

        Comanda novaComanda = new Comanda(novoNumero);
        ComandasData.salvarComanda(novaComanda);
        comandas.add(novaComanda);
        adapter.notifyDataSetChanged();

        new AlertDialog.Builder(this)
                .setTitle("Comanda Criada")
                .setMessage("A comanda número " + novoNumero + " foi criada.")
                .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                .show();
                recreate();
    }

    private void excluirComanda() {
        // Exibir um diálogo solicitando o número da comanda
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir Comanda");

        final EditText input = new EditText(this);
        input.setHint("Número da Comanda");
        builder.setView(input);

        builder.setPositiveButton("Excluir", (dialog, which) -> {
            String numeroStr = input.getText().toString();
            if (!numeroStr.isEmpty()) {
                try {
                    int numero = Integer.parseInt(numeroStr);
                    if (ComandasData.existeComanda(numero)) {
                        ComandasData.excluirComanda(numero);
                        comandas.removeIf(comanda -> comanda.getNumero() == numero);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Comanda " + numero + " excluída.", Toast.LENGTH_SHORT).show();
                        recreate();
                    } else {
                        Toast.makeText(this, "Comanda não encontrada.", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Número inválido.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Por favor, insira um número.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private int gerarNumeroDeComanda() {
        for (int i = 1; i <= 1000; i++) {
            if (!ComandasData.existeComanda(i)) {
                return i;
            }
        }
        return -1; // Não há números disponíveis
    }

    public static Context getContext() {
        return context;
    }
}
