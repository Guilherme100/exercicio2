package com.example.cadastroex;

import java.io.File;

import com.example.cadastroexemplo.dao.PessoaDao;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Formulario extends Activity {

	private FormularioHelper helper;
	private Button btn;
	private String caminhoArquivo;
		
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Intent intent = getIntent();
		final Pessoa pessoaSelecionada = (Pessoa) intent
				.getSerializableExtra("pessoaSelecionado");
		

		helper = new FormularioHelper(this);
		
		btn = (Button) findViewById(R.id.botao);
		
		if (pessoaSelecionada != null){
			btn.setText("Alterar");
			helper.colocaPessoaNaTela(pessoaSelecionada);
		}
		btnOnClick(pessoaSelecionada);
		
		ImageView foto = helper.getFoto();
		
		foto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irParaCamera = new Intent (
						MediaStore.ACTION_IMAGE_CAPTURE);
				
				caminhoArquivo = Environment.getExternalStorageDirectory().toString()+"/" + System.currentTimeMillis() + ".png";
				
				File arquivo = new File(caminhoArquivo);
				Uri localImagem = Uri.fromFile(arquivo);
				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localImagem);
				startActivityForResult(irParaCamera, 123);
			}
		});
	   }
	
		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 123){
			if (resultCode == Activity.RESULT_OK){
				helper.carregaImagem(caminhoArquivo);
			}else{
				caminhoArquivo = null;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
		
		
 
		private void btnOnClick(final Pessoa pessoaSelecionado) {
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Pessoa pessoa = helper.pegaPessoaDoFormulario1();
				PessoaDao dao = new PessoaDao(Formulario.this);
				if (pessoaSelecionado == null){
					dao.salva(pessoa);
			}else {
				pessoa.setId(pessoaSelecionado.getId());
				dao.alterar(pessoa);
			}
				dao.close();
				
				finish();
			}
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
