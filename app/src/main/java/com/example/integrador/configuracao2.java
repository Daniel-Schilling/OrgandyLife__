package com.example.integrador;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;

import java.util.List;


public class configuracao2 extends AppCompatActivity implements Validator.ValidationListener {

    private Button btSair;

    @Length(min = 3, max = 45, message = "O nome deve ter entre 3 e 45 caracteres")
    private EditText etMudaNome;

    @Email(message = "Email inválido!")
    private EditText etMudaEmail;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC, message = "Senha deve conter no mínimo 6 número")
    private EditText etMudaSenha;

    @ConfirmPassword(message = "Senha não confirmada!")
    private EditText etMudaConfSenha;

    private Button btSalvar;
    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao2);
        this.inicializaComponentes();
        this.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
        this.btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itConfiguracao = new Intent(configuracao2.this, Login.class);

                startActivity(itConfiguracao);
                finish();
            }
        });
    }
    private void inicializaComponentes() {
        this.btSair = findViewById(R.id.btSair);
        this.etMudaNome = findViewById(R.id.etMudaNome);
        this.etMudaEmail = findViewById(R.id.etMudaEmail);
        this.etMudaSenha = findViewById(R.id.etMudaSenha);
        this.etMudaConfSenha = findViewById(R.id.etMudaConfSenha);
        this.btSalvar = findViewById(R.id.btSalvar);
    }
    @Override
    public void onValidationSucceeded(){
        Toast.makeText(this, "ATUALIZADO!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors){
        for (ValidationError erro:errors){
            View componente = erro.getView();
            String mensagemErro = erro.getCollatedErrorMessage(this);
            if (componente instanceof EditText){
                ((TextView) componente).setError(mensagemErro);
            }
        }
    }
}