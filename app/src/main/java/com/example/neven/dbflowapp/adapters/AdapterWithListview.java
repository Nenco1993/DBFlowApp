package com.example.neven.dbflowapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.neven.dbflowapp.R;
import com.example.neven.dbflowapp.models.Users;

import java.util.List;

/**
 * Created by Neven on 19.1.2017..
 */
public class AdapterWithListview extends BaseAdapter {

    private List<Users> listUsers;
    private Context context;

    public AdapterWithListview(List<Users> listUsers, Context context) {
        this.listUsers = listUsers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_item_all_users, parent, false);





        return convertView;
    }
}
