package br.com.movingtechbrasil.repertoriomusical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CantorRecyclerViewAdapter extends RecyclerView.Adapter<CantorRecyclerViewAdapter.CantorHolder> {

    private OnItemClickListener onItemClickListener;
    private Context context;

    private List<Cantor> listaDeCantores;

    private  String[] tiposConjucoesMusicais;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);

    }

    public class CantorHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView cardTextViewNomeCantor;
        public TextView cardTextViewOrigem;
        public TextView cardTextViewGeneroMusical;
        public TextView cardTextViewConjuncaoMusical;

        public CantorHolder(@NonNull View itemView){
            super(itemView);

            cardTextViewNomeCantor = itemView.findViewById(R.id.cardTextViewNomeCantor);
            cardTextViewOrigem = itemView.findViewById(R.id.cardTextViewOrigem);
            cardTextViewGeneroMusical = itemView.findViewById(R.id.cardTextViewGeneroMusical);
            cardTextViewConjuncaoMusical = itemView.findViewById(R.id.cardTextViewConjuncaoMusical);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(view != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    onItemClickListener.onItemClick(view, position);
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {

            if(view != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    onItemClickListener.onItemLongClick(view, position);
                    return  true;
                }
            }
            return false;
        }

    }
    public CantorRecyclerViewAdapter(Context context, List<Cantor> listaDeCantores, OnItemClickListener listener) {
        this.context = context;
        this.listaDeCantores = listaDeCantores;
        this.onItemClickListener = listener;
        tiposConjucoesMusicais = context.getResources().getStringArray(R.array.tipos_conjuncoes_musicais);
    }

    @NonNull
    @Override
    public CantorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.card_lista_cantores,parent,false);
        return new CantorHolder(convertView);
    }
    @Override
    public void onBindViewHolder(@NonNull CantorHolder holder, int position) {

        Cantor cantor = listaDeCantores.get(position);
        holder.cardTextViewNomeCantor.setText(cantor.getNomeCantor());

        switch (cantor.getOrigem()){
            case Nacional:
                holder.cardTextViewOrigem.setText(R.string.nacional);
                break;

            case Internacional:
                holder.cardTextViewOrigem.setText(R.string.internacional);
                break;
        }

        switch (cantor.getGeneroMusical()){
            case Rock:
                holder.cardTextViewGeneroMusical.setText(R.string.rock);
                break;
            case Mpb:
                holder.cardTextViewGeneroMusical.setText(R.string.mpb);
                break;
            case Samba:
                holder.cardTextViewGeneroMusical.setText(R.string.samba);
                break;
        }

        holder.cardTextViewConjuncaoMusical.setText(tiposConjucoesMusicais[cantor.getConjucaoMusical()]);

    }

    @Override
    public int getItemCount() {
        return listaDeCantores.size();
    }

}
