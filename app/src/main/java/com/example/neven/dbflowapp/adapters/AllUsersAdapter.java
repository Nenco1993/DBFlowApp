package com.example.neven.dbflowapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.neven.dbflowapp.R;
import com.example.neven.dbflowapp.models.Users;

import java.util.List;

/**
 * Created by Neven on 19.1.2017..
 */
public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.MyViewHolder> {

    private Context context;
    private List<Users> listUsers;

    public AllUsersAdapter(Context context, List<Users> listUsers) {
        this.context = context;
        this.listUsers = listUsers;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvUserFullName)
        TextView tvFullUserName;

        public MyViewHolder(View itemView) {
            super(itemView);


            ButterKnife.bind(this, itemView);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_all_users, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Users singleUser = listUsers.get(position);
     //   holder.tvFullUserName.setText(singleUser.firstName + " " + singleUser.lastName);
        holder.tvFullUserName.setText("isus");

    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }
}
