package br.com.hlnengenharia.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import br.com.hlnengenharia.app.R;

/**
 * Created by rn-15 on 22/03/2018.
 */

public class CadUsuarioAdapter extends BaseAdapter {

    private Context context;

    public CadUsuarioAdapter(Context context) {
        this.context = context;
    }

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
            view = inflater.inflate(R.layout.activity_cad_usuario, parent,false);
        return view;
    }
}
