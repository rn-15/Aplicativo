package br.com.hlnengenharia.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import br.com.hlnengenharia.app.R;

public class DpsLoginAdapter extends BaseAdapter {
    private Context context;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.activity_tela_depois_do_login, parent,false);
        return view;

    }
}
