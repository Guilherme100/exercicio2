package com.example.cadastroex;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cadastroexemplo.dao.PessoaDao;

public class Lista extends Activity {
	private Pessoa pessoa;
	private ListView lista;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);

		lista = (ListView) findViewById(R.id.lista_pessoa);
		
		registerForContextMenu(lista);
	
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Pessoa pessoaSelecionada = (Pessoa) adapter.getItemAtPosition(position);
			
				
				Intent irPara = new Intent (Lista.this, Formulario.class);
				irPara.putExtra("pessoaSelecionada", pessoaSelecionada);
				
				startActivity(irPara);
				
			}
		
		});
		
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				pessoa = (Pessoa) adapter.getItemAtPosition(position);
				
				return false;
			}
	
		});
		

	}

@Override
protected void onResume() {
	super.onResume();
	carregaLista();
}

private void carregaLista() {
	PessoaDao dao = new PessoaDao(this);
	
	List<Pessoa > pessoa = dao.getLista();
	dao.close();
	
	ListaPessoaAdapter adapter = new ListaPessoaAdapter(this, pessoa);
	
	lista.setAdapter(adapter);
	
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.lista, menu);
	
	return super.onCreateOptionsMenu(menu);
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
int itemClicado = item.getItemId();
switch (itemClicado) {
case R.id.menu_novo:
	Intent intent = new Intent(this, Formulario.class);
	startActivity(intent);
	break;

default:
	break;
}
	return super.onOptionsItemSelected(item);
}

@Override
public void onCreateContextMenu(ContextMenu menu, View v,
		ContextMenuInfo menuInfo) {
	

menuDeletar(menu);
	
	super.onCreateContextMenu(menu, v, menuInfo);
}



private void menuDeletar(ContextMenu menu){
	MenuItem deletar = menu.add("Deletar");
	
	deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
		
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			PessoaDao dao = new PessoaDao(Lista.this);
			dao.deletar(pessoa);
			dao.close();
			carregaLista();
			
			return false;
		}
	});
}

	}

