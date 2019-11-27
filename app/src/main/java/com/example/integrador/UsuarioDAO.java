package com.example.integrador;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {
    private ConexaoCadastro conexao;
    private SQLiteDatabase banco;

    public UsuarioDAO(Context context){
        conexao = new ConexaoCadastro(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(CadastroUsuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getSenha());
        values.put("senha", usuario.getSenha());
        values.put("confirma senha", usuario.getConfSenha());
        values.put("matrícula", usuario.getMatricula());
        return banco.insert("aluno", null, values);


    }
}
