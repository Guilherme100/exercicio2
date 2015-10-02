package com.example.cadastroex;

import java.util.List;

import android.app.Activity;
import android.content.ClipData.Item;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaPessoaAdapter extends BaseAdapter {

	private final List<Pessoa> alunos;
	private final Activity activity;

	public ListaPessoaAdapter(Activity activity, List<Pessoa> alunos) {

		this.activity = activity;
		this.alunos = alunos;
	}

	@Override
	public int getCount() {

		return alunos.size();
	}

	@Override
	public Object getItem(int position) {
		return alunos.get(position);
	}

	@Override
	public long getItemId(int position) {

		return alunos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = activity.getLayoutInflater().inflate(R.layout.item, null);

		Pessoa aluno = alunos.get(position);
		TextView nome = (TextView) view.findViewById(R.id.nome);
		nome.setText(aluno.toString());
		if (aluno.getFoto() != null) {
			ImageView foto = (ImageView) view.findViewById(R.id.foto);
			Bitmap imagem = BitmapFactory.decodeFile(aluno.getFoto());
			Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100,
					true);

			foto.setImageBitmap(imagemReduzida);
		}
		return view;
	}

}
