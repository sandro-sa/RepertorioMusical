package br.com.movingtechbrasil.repertoriomusical;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class CantorActivity extends AppCompatActivity {
    private boolean erros = false;
    private String respostaSucesso = "",  respostaErro = "";
    private TextInputEditText textInputNomeCantor;
    private Spinner spinnerConjuncaoMusical;
    private RadioButton nacional, internacional;
    private CheckBox mpb, rock, samba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cantor);

        textInputNomeCantor = findViewById(R.id.idtextInputNomeCantor);
        nacional = findViewById(R.id.idRadioButtonNacional);
        internacional = findViewById(R.id.idRadioButtonInternacional);
        mpb = findViewById(R.id.idCheckBoxMpb);
        rock = findViewById(R.id.idCheckBoxRock);
        samba = findViewById(R.id.idCheckBoxSamba);
        spinnerConjuncaoMusical = findViewById(R.id.idSpinnerConjuncaoMusical);


        conjuncaoMusical();

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void salvar(View view){

        validarInpunt();
        validarCheckBoxOrigem();
        validarGeneroMusical();
        validarSpinnerConjuncaoMusical();

        if(erros){
            mensagemErro(respostaErro);

        }else{
            mensagemSucesso(respostaSucesso);
        }

    }
    public void limparCampos(View view){

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
    private void validarInpunt(){

        String nomeDaMusica = Objects.requireNonNull(textInputNomeCantor.getText()).toString();
        if(nomeDaMusica.trim().isEmpty()){
            erros = true;
            respostaErro += getString(R.string.preencher_nome_da_banda_grupo_cantor_ar)+"\n";
        }else{
            System.out.println(nomeDaMusica.trim());
            respostaSucesso =  nomeDaMusica.trim() + "\n" ;
        }
    }
    private void validarCheckBoxOrigem(){

        if(nacional.isChecked()){
            respostaSucesso += getString(R.string.origem_nacional)+"\n";
        } else if (internacional.isChecked()) {
            respostaSucesso += getString(R.string.origem_internacional)+"\n";
        }else{
            erros = true;
            respostaErro += getString(R.string.selecionar_origem) + "\n";
        }

    }
    private void validarGeneroMusical(){

        if(mpb.isChecked()||rock.isChecked() || samba.isChecked()){
            respostaSucesso += getString(R.string.genero);
            if(mpb.isChecked()){
                respostaSucesso += getString(R.string.mpb)+" \n";
            }
            if(rock.isChecked()){
                respostaSucesso += getString(R.string.rock)+" \n";
            }
            if(samba.isChecked()){
                respostaSucesso += getString(R.string.samba)+" \n";
            }
        }else{
            respostaErro += getString(R.string.selecionar_um_genero_musical);
            erros = true;
        }

    }
    private void validarSpinnerConjuncaoMusical(){

        String conjuncaoMusical = (String) spinnerConjuncaoMusical.getSelectedItem();
        if(conjuncaoMusical == null){
            erros = true;
            respostaErro += getString(R.string.erro_ao_carregar_conjucao_musical);
        }else{
            respostaSucesso += getString(R.string.conjuncao_musical)+conjuncaoMusical;
        }
    }
    public void mensagemSucesso(String resposta){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.salvar_dados);
        dialog.setMessage(resposta);
        dialog.setCancelable(false);
        dialog.setIcon(android.R.drawable.ic_menu_send);
        dialog.setPositiveButton(R.string.sim, (dialog2, which) -> {
            Toast.makeText( getApplicationContext(), getString(R.string.dados_salvo_com_sucesso), Toast.LENGTH_LONG).show();
            limparCampos(null);
        });

        dialog.setNegativeButton(R.string.nao, (dialog1, which) -> Toast.makeText( getApplicationContext(), getString(R.string.nenhum_dados_salvo), Toast.LENGTH_LONG).show());

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
            respostaSucesso = "";
        });
        dialogErro.create();
        dialogErro.show();
    }
}