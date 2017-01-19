package com.example.neven.dbflowapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.neven.dbflowapp.R;
import com.example.neven.dbflowapp.adapters.AllUsersAdapter;
import com.example.neven.dbflowapp.models.Users;
import com.example.neven.dbflowapp.presenters.UsersPresenter;
import com.example.neven.dbflowapp.presenters.UsersPresenterImpl;
import com.example.neven.dbflowapp.views.UsersView;

import java.util.List;

public class AllUsersActivity extends AppCompatActivity implements UsersView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        UsersPresenter presenter = new UsersPresenterImpl(this);
        presenter.loadContent();


    }

    @Override
    public void showUsers(List<Users> listUsers) {

        AllUsersAdapter adapter = new AllUsersAdapter(getBaseContext(), listUsers);

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }
}
