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


    private List<Users> listUsers;
    private Context context;
    private AllUsersClickListener allUsersClickListener;


    public AllUsersAdapter(List<Users> listUsers, Context context) {
        this.listUsers = listUsers;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvUserFullName)
        TextView tvFullUserName;


        MyViewHolder(View itemView) {
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
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Users singleUser = listUsers.get(position);
        holder.tvFullUserName.setText(singleUser.firstName + " " + singleUser.lastName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                allUsersClickListener.onUserClicked(singleUser);


            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                allUsersClickListener.onUserLongClicked(singleUser, holder.itemView);


                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public void setUsersClickListener(AllUsersClickListener allUsersClickListener) {
        this.allUsersClickListener = allUsersClickListener;
    }

    public interface AllUsersClickListener {

        void onUserClicked(Users user);

        void onUserLongClicked(Users user, View view);


    }


}
