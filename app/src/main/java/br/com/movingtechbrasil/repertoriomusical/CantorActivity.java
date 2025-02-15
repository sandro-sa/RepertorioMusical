package br.com.movingtechbrasil.repertoriomusical;

import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.Objects;

public class CantorActivity extends AppCompatActivity {
    public static final String KEY_NOME_CANTOR = "KEY_NOME_CANTOR";
    public static final String KEY_ORIGEM = "KEY_ORIGEM";
    public static final String KEY_CONJUNCAO_MUSICAL = "KEY_CONJUNCAO_MUSICAL";
    public static final String KEY_GENERO_MUSICAL = "KEY_GENERO_MUSICAL";
    private boolean erros = false;
    private String respostaErro = "";
    private TextInputEditText textInputNomeCantor;
    private Spinner spinnerConjuncaoMusical;
    private RadioButton nacional, internacional;
    private CheckBox mpb, rock, samba;

    private Cantor cantor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantor);
        setTitle(getString(R.string.cadastro_de_artista));
        textInputNomeCantor = findViewById(R.id.idtextInputNomeCantor);
        nacional = findViewById(R.id.idRadioButtonNacional);
        internacional = findViewById(R.id.idRadioButtonInternacional);
        mpb = findViewById(R.id.idCheckBoxMpb);
        rock = findViewById(R.id.idCheckBoxRock);
        samba = findViewById(R.id.idCheckBoxSamba);
        spinnerConjuncaoMusical = findViewById(R.id.idSpinnerConjuncaoMusical);

        conjuncaoMusical();
    }
    public void salvar(){

        cantor = new Cantor();
        validarInpunt(cantor);
        validarCheckBoxOrigem(cantor);
        validarGeneroMusical(cantor);
        validarSpinnerConjuncaoMusical(cantor);
        if(erros){
           mensagemErro(respostaErro);

        }else{
            mensagemSucesso(cantor);
        }

    }
    public void limparCampos(){

        textInputNomeCantor.setText(null);
        nacional.setChecked(false);
        internacional.setChecked(false);
        mpb.setChecked(false);
        rock.setChecked(false);
        samba.setChecked(false);
        spinnerConjuncaoMusical.setSelection(0);
        Toast.makeText( getApplicationContext(), R.string.formulario_resetado, Toast.LENGTH_LONG).show();

    }
    private void conjuncaoMusical(){

        ArrayList<String> listaDeConjuncoesMusicais = new ArrayList<>();
        listaDeConjuncoesMusicais.add(getString(R.string.solo));
        listaDeConjuncoesMusicais.add(getString(R.string.banda));
        listaDeConjuncoesMusicais.add(getString(R.string.grupo));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDeConjuncoesMusicais);
        spinnerConjuncaoMusical.setAdapter(adapter);

    }
    private void validarInpunt(Cantor cantor){

        String nomeDaMusica = Objects.requireNonNull(textInputNomeCantor.getText()).toString();
        if(nomeDaMusica.trim().isEmpty()){
            erros = true;
            respostaErro += getString(R.string.preencher_nome_da_banda_grupo_cantor_ar)+"\n";
        }else{
            cantor.setNomeCantor(nomeDaMusica.trim());
        }

    }
    private void validarCheckBoxOrigem(Cantor cantor){

        if(nacional.isChecked()){
            cantor.setOrigem(Origem.Nacional);
        } else if (internacional.isChecked()) {
            cantor.setOrigem(Origem.Internacional);

        }else{
            erros = true;
            respostaErro += getString(R.string.selecionar_origem) + "\n";
        }

    }
    private void validarGeneroMusical(Cantor cantor){

        if(mpb.isChecked()||rock.isChecked() || samba.isChecked()){

            if(mpb.isChecked()){
                cantor.setGeneroMusical(GeneroMusical.Mpb);
            }
            if(rock.isChecked()){
                cantor.setGeneroMusical(GeneroMusical.Rock);
            }
            if(samba.isChecked()){
                cantor.setGeneroMusical(GeneroMusical.Samba);
            }

        }else{
            respostaErro += getString(R.string.selecionar_um_genero_musical);
            erros = true;
        }

    }
    private void validarSpinnerConjuncaoMusical(Cantor cantor){

        int conjuncaoMusical = spinnerConjuncaoMusical.getSelectedItemPosition();

        if(conjuncaoMusical == AdapterView.INVALID_POSITION){
            respostaErro += getString(R.string.erro_ao_carregar_conjucao_musical);
            erros = true;

        }else{
            cantor.setConjucaoMusical(conjuncaoMusical);
        }

    }
    private void mensagemSucesso(Cantor cantor){

        String mensagem = "Deseja salva os dados do artista:\n"+cantor.getNomeCantor();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.salvar_dados);
        dialog.setMessage(mensagem);
        dialog.setCancelable(false);
        dialog.setIcon(android.R.drawable.ic_menu_send);
        dialog.setPositiveButton(R.string.sim, (dialog2, which) -> {
            Toast.makeText( getApplicationContext(), getString(R.string.dados_salvo_com_sucesso), Toast.LENGTH_LONG).show();
            limparCampos();
        });

        dialog.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intentResposta = new Intent();
                intentResposta.putExtra(KEY_NOME_CANTOR, cantor.getNomeCantor());
                intentResposta.putExtra(KEY_ORIGEM, cantor.getOrigem().toString());
                intentResposta.putExtra(KEY_CONJUNCAO_MUSICAL, cantor.getConjucaoMusical());
                intentResposta.putExtra(KEY_GENERO_MUSICAL, cantor.getGeneroMusical().toString());

                setResult(CantorActivity.RESULT_OK, intentResposta);

                finish();
            }
        });

        dialog.create();
        dialog.show();

    }
    private void mensagemErro(String resposta){

        AlertDialog.Builder dialogErro = new AlertDialog.Builder(this);
        dialogErro.setTitle(R.string.acoes_necessaria);
        dialogErro.setMessage(resposta);
        dialogErro.setIcon(android.R.drawable.ic_dialog_info);
        dialogErro.setPositiveButton(R.string.sair, (dialog, which) -> {
            erros = false;
            respostaErro =  "";
        });
        dialogErro.create();
        dialogErro.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cantor_opcoes,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idMenuItem = item.getItemId();

        if(idMenuItem == R.id.menuItemSalvar){
            salvar();
            return true;
        } else if (idMenuItem == R.id.menuItemLimpar) {
            limparCampos();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}