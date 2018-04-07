package br.com.hlnengenharia.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.model.Usuario;

/**
 * Created by rn-15 on 22/03/2018.
 */

public class LoginAdapter extends BaseAdapter {
    private final Usuario usuarios;
    private Context context;

    public LoginAdapter(Usuario usuarios, Context context) {
        this.usuarios = usuarios;
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
                view = inflater.inflate(R.layout.activity_login, parent,false);

        return view;
    }
}
