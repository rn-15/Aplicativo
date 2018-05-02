package br.com.hlnengenharia.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import br.com.hlnengenharia.app.R;

public class InspecaoAdapter extends BaseAdapter {
    private Context context;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        if(view==null)
            view = inflater.inflate(R.layout.activity_data_km_obs, parent, false);
        return view;
    }
}
