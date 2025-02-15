package br.com.movingtechbrasil.repertoriomusical;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        setTitle(R.string.sobre);

    }

    public void acessarLinkedin(View view){
        acessarSite("https://www.linkedin.com/in/sandro-a-desa/");
    }

    private void acessarSite(String endereco){

        Intent intentAbertura = new Intent(Intent.ACTION_VIEW);

        intentAbertura.setData(Uri.parse(endereco));

        if(intentAbertura.resolveActivity(getPackageManager()) != null){
            startActivity(intentAbertura);
        }else{
            Toast.makeText(this, R.string.nenhum_navegar_instalado,Toast.LENGTH_LONG).show();
        }
    }

    public void enviarEmailParaAutor(View  view){
        enviarEmail( new String[]{"sandrosa@alunos.utfpr.edu.br"}, "Infomações sobre o app");
    }

    private void enviarEmail(String[] enderecos, String assuntos){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"));
        intent.putExtra(Intent.EXTRA_EMAIL, enderecos);
        intent.putExtra(Intent.EXTRA_SUBJECT, assuntos);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Toast.makeText(this, R.string.nenhum_aplicativo_para_envio_de_emails_instalado,Toast.LENGTH_LONG).show();
        }
    }
}