package br.com.hlnengenharia.app.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by rn-15 on 08/03/2018.
 */

public class HelperDAO extends SQLiteOpenHelper {


    private static final String NOME = "BD_APLICATIVO";
    private static final int VERSAO = 21;

    public HelperDAO(Context context) {
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userSQL = "CREATE TABLE Usuarios(usuario_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_nome TEXT NOT NULL, usuario_profissao TEXT NOT NULL, usuario_email TEXT NOT NULL," +
                "usuario_telefone TEXT NOT NULL, usuario_senha TEXT NOT NULL, usuario_confirmaSenha TEXT NOT NULL);";
        String empresaSQL = "CREATE TABLE Empresas(empresa_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "empresa_nome TEXT NOT NULL, empresa_segmento TEXT NOT NULL);";
        String unidadeSQL = "CREATE TABLE Unidades(unidade_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "unidade_nome TEXT NOT NULL, unidade_cidade TEXT NOT NULL," +
                "idEmpresa TEXT NOT NULL, FOREIGN KEY (idEmpresa) REFERENCES Empresas (empresa_id));";
        String setorSQL = "CREATE TABLE Setores(setor_id INTEGER PRIMARY KEY AUTOINCREMENT, setor_nome TEXT NOT NULL, " +
                "setor_atividade TEXT, idUnidade TEXT NOT NULL, FOREIGN KEY (idUnidade) REFERENCES Unidades (unidade_id));";

        String inspecaoSQL = "CREATE TABLE Inspecoes (inspecao_id INTEGER PRIMARY KEY AUTOINCREMENT, inspecao_nome TEXT NOT NULL," +
                "idSetor TEXT NOT NULL, FOREIGN KEY (idSetor) REFERENCES Setores (setor_id));";
        String perguntaSQL = "CREATE TABLE Perguntas (pergunta_id INTEGER PRIMARY KEY AUTOINCREMENT, pergunta_desc TEXT NOT NULL, " +
                "idInspecao TEXT NOT NULL, FOREIGN KEY (idInspecao) REFERENCES Inspecoes (inspecao_id));";

        String carroSQL = "CREATE TABLE Carro (carro_id INTEGER PRIMARY KEY, carro_nome TEXT NOT NULL);";
        String carroPerguntaSQL = "CREATE TABLE CarroPergunta (cpergunta_id INTEGER PRIMARY KEY, cpergunta TEXT NOT NULL);";
        String carroHoraDataSQL = "CREATE TABLE DiaHora(dh_id INTEGER PRIMARY KEY, dia TEXT, hora TEXT, km TEXT, " +
                "idCarro TEXT NOT NULL, FOREIGN KEY (idCarro) REFERENCES Carro(carro_id));";

        String carroRespostaSQL = "CREATE TABLE CarroResposta (cresposta_id INTEGER PRIMARY KEY," +
                "idCarro INTEGER REFERENCES Carro(carro_id)," +
                "idPergunta INTEGER REFERENCES CarroPergunta(cpergunta_id)," +
                "idHora INTEGER REFERENCES carroHoraDataSQL (dh_id)," +
                "cresposta_desc TEXT, cresposta_obs TEXT);";




        db.execSQL(userSQL);
        db.execSQL(empresaSQL);
        db.execSQL(unidadeSQL);
        db.execSQL(setorSQL);
        db.execSQL(inspecaoSQL);

        db.execSQL(perguntaSQL);

        db.execSQL(carroSQL);
        db.execSQL(carroPerguntaSQL);
        db.execSQL(carroRespostaSQL);
        db.execSQL(carroHoraDataSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String userSQL = "DROP TABLE IF EXISTS Usuarios;";
        String empresaSQL = "DROP TABLE IF EXISTS Empresas;";
        String unidadeSQL = "DROP TABLE IF EXISTS Unidades;";
        String inspecaoSQL = "DROP TABLE IF EXISTS Inspecoes;";
        String setorSQL = "DROP TABLE IF EXISTS Setores;";

        String perguntaSQL = "DROP TABLE IF EXISTS Perguntas;";

        String carroSQL = "DROP TABLE IF EXISTS Carro";
        String carroPerguntaSQL = "DROP TABLE IF EXISTS CarroPergunta;";
        String carroRespostaSQL = "DROP TABLE IF EXISTS CarroResposta;";
        String carroHoraDataSQL = "DROP TABLE IF EXISTS DiaHora;";

        db.execSQL(userSQL);
        db.execSQL(empresaSQL);
        db.execSQL(unidadeSQL);
        db.execSQL(setorSQL);
        db.execSQL(inspecaoSQL);

        db.execSQL(perguntaSQL);

        db.execSQL(carroSQL);
        db.execSQL(carroPerguntaSQL);
        db.execSQL(carroRespostaSQL);
        db.execSQL(carroHoraDataSQL);

        onCreate(db);
    }
}
