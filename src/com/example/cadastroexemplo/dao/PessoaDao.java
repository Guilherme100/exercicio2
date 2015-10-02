package com.example.cadastroexemplo.dao;
import java.util.ArrayList;
import java.util.List;

import com.example.cadastroex.Pessoa;
import com.example.cadastroex.Lista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PessoaDao extends SQLiteOpenHelper {

	private static final String TABELA = "Pessoa";
	private static final String DATABASE = "Cadastro";
	private static final int VERSAO = 3;

	public PessoaDao(Context context) {
		super(context, DATABASE, null, VERSAO);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE "
				+ TABELA 
				+ " (id INTEGER PRIMARY KEY,"
				+ " nome TEXT UNIQUE NOT NULL,"
				+ " telefone TEXT,"
				+ " foto TEXT)";
		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String ddl = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(ddl);
		
		this.onCreate(db);
	   	
	}
	
	public void salva (Pessoa pessoa){
		ContentValues values = toContentValues(pessoa);
		
		getWritableDatabase().insert(TABELA, null, values);
		
	
	}
	public void deletar (Pessoa pessoa){
		String[] args = {pessoa.getId().toString()};
		getWritableDatabase().delete(TABELA, "id=?", args);
	
}
	
  public void alterar (Pessoa pessoa){
	  ContentValues values = toContentValues(pessoa);
	  
	  String[] args = { pessoa.getId().toString()};
	  getWritableDatabase().update(TABELA, values, "id=?",args);
		  
	  }
  
  
	
	private ContentValues toContentValues(Pessoa pessoa) {
		ContentValues values = new ContentValues();
		values.put("nome",pessoa. getNome());
		values.put("telefone", pessoa.getTelefone());
		values.put("foto", pessoa.getFoto());
		return values;
	}
	
		public List<Pessoa> getLista() {
			String[] colunas = {"id", "nome", "telefone", "foto"};
			Cursor cursor = getWritableDatabase().query(TABELA,colunas, null, null, null, null, null);
			
			ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
			
			while(cursor.moveToNext()){
				Pessoa pessoa = new Pessoa();
				
				pessoa.setId(cursor.getLong(0));
				pessoa.setNome(cursor.getString(1));
				pessoa.setTelefone(cursor.getString(2));
				pessoa.setFoto(cursor.getString(3));
				
				pessoas.add(pessoa);
				
				
			}
			return pessoas;
			
			
			}
		
		
		
	}
	