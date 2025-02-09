package br.com.movingtechbrasil.repertoriomusical;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CantoresActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPessoas;
    private RecyclerView.LayoutManager layoutManager;
    private CantorRecyclerViewAdapter cantorRecyclerViewAdapter;
    private  CantorRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    private List<Cantor> listaDeCantores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantores);

        recyclerViewPessoas = findViewById(R.id.recyclerViewCantores);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewPessoas.setLayoutManager(layoutManager);
        recyclerViewPessoas.setHasFixedSize(true);
        recyclerViewPessoas.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        onItemClickListener = new CantorRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Cantor cantor = listaDeCantores.get(position);
                Toast.makeText(getApplicationContext(),getString(R.string.artista)+" "+cantor.getNomeCantor()+ getString(R.string.foi_clicado), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Cantor cantor = listaDeCantores.get(position);
                Toast.makeText(getApplicationContext(),getString(R.string.artista)+" "+cantor.getNomeCantor()+ getString(R.string.clique_longo), Toast.LENGTH_LONG).show();
            }
        };

        buscarCantores();

    }

    private void buscarCantores(){

        String[] cantores_nomes = getResources().getStringArray(R.array.nome_cantores);
        int[] cantores_origens = getResources().getIntArray(R.array.origem);
        int[] cantores_generos_musicais = getResources().getIntArray(R.array.genero_musical);
        int[] cantores_conjuncoes_musicais = getResources().getIntArray(R.array.conjuncao_musical);

        listaDeCantores = new ArrayList<>();
        Cantor cantor;
        Origem origem;
        GeneroMusical generoMusical;
        Origem[] origens = Origem.values();
        GeneroMusical[] generosMusicais = GeneroMusical.values();

        for(int posicao=0; posicao < cantores_nomes.length; posicao++){
            origem = origens[cantores_origens[posicao]];
            generoMusical = generosMusicais[cantores_generos_musicais[posicao]];
            cantor = new Cantor(cantores_nomes[posicao],origem,generoMusical,cantores_conjuncoes_musicais[posicao]);
            listaDeCantores.add(cantor);
        }

        cantorRecyclerViewAdapter = new CantorRecyclerViewAdapter(this, listaDeCantores, onItemClickListener);
        recyclerViewPessoas.setAdapter(cantorRecyclerViewAdapter);
    }
}