package br.com.movingtechbrasil.repertoriomusical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CantorAdapter extends BaseAdapter {

    private final Context context;
    private final List<Cantor> listaDeCantores;

    private final String[] tiposConjucoesMusicais;

    private static class CantorHolder{
        public TextView cardTextViewNomeCantor;
        public TextView cardTextViewOrigem;
        public TextView cardTextViewGeneroMusical;
        public TextView cardTextViewConjuncaoMusical;
    }
    public CantorAdapter(Context context, List<Cantor> listaDeCantores) {
        this.context = context;
        this.listaDeCantores = listaDeCantores;
        tiposConjucoesMusicais = context.getResources().getStringArray(R.array.tipos_conjuncoes_musicais);
    }

    @Override
    public int getCount() {
        return listaDeCantores.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeCantores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CantorHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.card_lista_cantores,parent,false);

            holder = new CantorHolder();
            holder.cardTextViewNomeCantor = convertView.findViewById(R.id.cardTextViewNomeCantor);
            holder.cardTextViewOrigem = convertView.findViewById(R.id.cardTextViewOrigem);
            holder.cardTextViewGeneroMusical = convertView.findViewById(R.id.cardTextViewGeneroMusical);
            holder.cardTextViewConjuncaoMusical = convertView.findViewById(R.id.cardTextViewConjuncaoMusical);

            convertView.setTag(holder);

        }else{

            holder = (CantorHolder)  convertView.getTag();
        }
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

        return convertView;
    }
}
