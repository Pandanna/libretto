package com.example.libretto.classi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.libretto.R;

import java.util.ArrayList;

public class AdapterEsami extends BaseAdapter {
    Context context;
    ArrayList<Esame> esami;
    LayoutInflater layoutInflater;

    public AdapterEsami(Context context, ArrayList<Esame> esami)
    {
        this.context = context;
        this.esami = esami;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.esami.size();
    }

    @Override
    public Object getItem(int position) {
        return this.esami.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = layoutInflater.inflate(R.layout.riga, null);
        TextView voto = view.findViewById(R.id.voto_esame);
        TextView nome = view.findViewById(R.id.nome_esame);
        TextView cfu = view.findViewById(R.id.cfu_esame);
        voto.setText(esami.get(i).getVoto() + "");
        nome.setText(esami.get(i).getNome() + "");
        cfu.setText(esami.get(i).getCFU() + "");
        return view;
    }
}
