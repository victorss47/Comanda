package com.example.projetoandroidteste1;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ComandaAdapter extends RecyclerView.Adapter<ComandaAdapter.ComandaViewHolder> {
    private List<Comanda> comandas;

    public ComandaAdapter(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    @NonNull
    @Override
    public ComandaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comanda, parent, false);
        return new ComandaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComandaViewHolder holder, int position) {
        Comanda comanda = comandas.get(position);
        holder.numeroComanda.setText("Comanda #" + comanda.getNumero());
        holder.valorComanda.setText(String.format("R$ %.2f", comanda.getValorTotal()));

        if (comanda.getValorTotal() > 150.0) {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_orange_light));
        } else {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public int getItemCount() {
        return comandas.size();
    }

    static class ComandaViewHolder extends RecyclerView.ViewHolder {
        TextView numeroComanda;
        TextView valorComanda;

        public ComandaViewHolder(@NonNull View itemView) {
            super(itemView);
            numeroComanda = itemView.findViewById(R.id.numeroComanda);
            valorComanda = itemView.findViewById(R.id.valorComanda);
        }
    }
}
