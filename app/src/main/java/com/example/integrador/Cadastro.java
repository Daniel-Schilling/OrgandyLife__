package com.example.integrador;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.Camera;

import androidx.appcompat.app.AppCompatActivity;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class Cadastro extends AppCompatActivity implements Validator.ValidationListener {



    @NotEmpty(message = "Campo obrigatório!")
    @Length(min = 3, max = 40, message = "O nome deve ter entre 3 e 40 caracteres")
    private EditText etNome;

    @NotEmpty(message = "Campo obrigatório!")
    @Email(message = "Email inválido!")
    private EditText etEmail;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC, message = "Senha deve conter no mínimo 6 números!")
    private EditText etSenha;

    @NotEmpty(message = "Campo obrigatório!")
    @ConfirmPassword(message = "Senha não confirmado!")
    private EditText etConfSenha;

    @NotEmpty(message = "Campo obrigatório!")
    @Length(min = 10, max = 10, message = "Deve ser digitado a sua matrícula")
    private EditText etMatricula;

    private Button btCadastro;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.inicializaComponentes();
        this.btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });


    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.cb_cadastrar:
                if (checked){
                }
            else
                break;
        }
    }

    private void inicializaComponentes(){
        this.etNome = findViewById(R.id.et_nome_completo);
        this.etEmail = findViewById(R.id.et_cadastro_email);
        this.etSenha = findViewById(R.id.et_cadastrar_senha);
        this.etConfSenha = findViewById(R.id.et_conf_senha);
        this.btCadastro = findViewById(R.id.bt_cadastro);
        this.etMatricula = findViewById(R.id.et_matricula);
        this.validator = new Validator(this);
        this.validator.setValidationListener(this);

    }

    @Override
    public void onValidationSucceeded(){
        Toast.makeText(this, "PASSOU NA VALIDAÇÃO!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors){
        Toast.makeText(this, "ERRO NA VALIDAÇÃO!",Toast.LENGTH_LONG).show();
        for (ValidationError erro:errors){
            View componente = erro.getView();
            String mensagemErro = erro.getCollatedErrorMessage(this);
            if(componente instanceof EditText) {
                ((TextView) componente).setError(mensagemErro);
            }
        }



    }
    public class Camera extends Activity{
        @Override
        public void onCreate(Bundle savedInstanteState) {
            super.onCreate(savedInstanteState);
            setContentView(R.layout.activity_cadastro);
            ImageButton b = (ImageButton) findViewById(R.id.bt_foto_perfil);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(i, 0);
                }
            });
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null){
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    ImageView img = (ImageView) findViewById(R.id.imagem);
                    img.setImageBitmap(bitmap);
                }
            }
        }
        }
    }
