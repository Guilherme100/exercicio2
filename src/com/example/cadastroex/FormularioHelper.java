package com.example.cadastroex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class FormularioHelper {
	private EditText nome;
	private EditText telefone;
	private ImageView foto;
	private Pessoa pessoa;

	public FormularioHelper(Formulario formulario) {
		nome = (EditText) formulario.findViewById(R.id.nome);
		telefone = (EditText) formulario.findViewById(R.id.telefone);
		foto = (ImageView) formulario.findViewById(R.id.foto);
		pessoa = new Pessoa();
	}

	public Pessoa pegaPessoaDoFormulario1() {

		pessoa.setNome(nome.getText().toString());
		pessoa.setTelefone(telefone.getText().toString());
		return pessoa;

	}

	public void colocaPessoaNaTela(Pessoa pessoa) {
		this.pessoa = pessoa;
		nome.setText(pessoa.getNome());
		telefone.setText(pessoa.getTelefone());


		if (pessoa.getFoto() != null) {
			carregaImagem(pessoa.getFoto());
		}

	}

	public ImageView getFoto() {
		return this.foto;
	}

	public void carregaImagem(String caminhoArquivo) {
		pessoa.setFoto(caminhoArquivo);
		Bitmap imagem = BitmapFactory.decodeFile(caminhoArquivo);
		Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100,
				true);

		foto.setImageBitmap(imagemReduzida);

	}


	

}