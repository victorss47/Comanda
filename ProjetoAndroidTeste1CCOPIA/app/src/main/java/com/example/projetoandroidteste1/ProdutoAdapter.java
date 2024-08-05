package com.example.projetoandroidteste1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    private List<Produto> produtos;
    private Comanda comanda;

    public ProdutoAdapter(List<Produto> produtos, Comanda comanda) {
        this.produtos = produtos;
        this.comanda = comanda;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.nomeProduto.setText(produto.getNome());
        holder.quantidadeProduto.setText(String.valueOf(produto.getQuantidade()));

        holder.btnMenos.setOnClickListener(v -> {
            int quantidade = produto.getQuantidade();
            if (quantidade > 0) {
                quantidade--;
                produto.setQuantidade(quantidade);
                holder.quantidadeProduto.setText(String.valueOf(quantidade));
                atualizarValorTotal();
            }
        });

        holder.btnMais.setOnClickListener(v -> {
            int quantidade = produto.getQuantidade();
            quantidade++;
            produto.setQuantidade(quantidade);
            holder.quantidadeProduto.setText(String.valueOf(quantidade));
            atualizarValorTotal();
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto;
        TextView quantidadeProduto;
        Button btnMenos;
        Button btnMais;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            quantidadeProduto = itemView.findViewById(R.id.quantidadeProduto);
            btnMenos = itemView.findViewById(R.id.btnMenos);
            btnMais = itemView.findViewById(R.id.btnMais);
        }
    }

    private void atualizarValorTotal() {
        double valorTotal = 0.0;
        for (Produto produto : produtos) {
            valorTotal += produto.getPreco() * produto.getQuantidade();
        }
        comanda.setValorTotal(valorTotal);
    }
}
