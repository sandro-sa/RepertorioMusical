package br.com.movingtechbrasil.repertoriomusical;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CantoresActivity extends AppCompatActivity {
    private ListView listViewCantores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantores);

        listViewCantores = findViewById(R.id.idListViewCantores);

        listViewCantores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cantor cantor = (Cantor) listViewCantores.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),getString(R.string.artista)+" "+cantor.getNomeCantor()+ getString(R.string.foi_clicado), Toast.LENGTH_LONG).show();
            }
        });

        buscarCantores();

    }

    private void buscarCantores(){
        String[] cantores_nomes = getResources().getStringArray(R.array.nome_cantores);
        int[] cantores_origens = getResources().getIntArray(R.array.origem);
        int[] cantores_generos_musicais = getResources().getIntArray(R.array.genero_musical);
        int[] cantores_conjuncoes_musicais = getResources().getIntArray(R.array.conjuncao_musical);

        List<Cantor> listaDeCantores = new ArrayList<>();
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

        CantorAdapter cantorAdapter = new CantorAdapter(this, listaDeCantores);

        listViewCantores.setAdapter(cantorAdapter);
    }
}