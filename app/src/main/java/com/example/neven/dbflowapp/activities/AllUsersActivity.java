package com.example.neven.dbflowapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.neven.dbflowapp.R;
import com.example.neven.dbflowapp.adapters.AllUsersAdapter;
import com.example.neven.dbflowapp.models.Users;
import com.example.neven.dbflowapp.presenters.UsersPresenter;
import com.example.neven.dbflowapp.presenters.UsersPresenterImpl;
import com.example.neven.dbflowapp.views.UsersView;

import java.util.List;

public class AllUsersActivity extends AppCompatActivity implements UsersView, AllUsersAdapter.AllUsersClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private UsersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        presenter = new UsersPresenterImpl(this);
        presenter.loadContent();


    }

    @Override
    public void showUsers(final List<Users> listUsers) {

        AllUsersAdapter adapter = new AllUsersAdapter(listUsers);

        adapter.setUsersClickListener(this);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showUsersDetails(Users user) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        Intent intent = new Intent(getBaseContext(), UserDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    @Override
    public void showPopUpMenu(final View view, final Users user) {

        Context wrapper = new ContextThemeWrapper(getBaseContext(), R.style.PopupMenu);

        PopupMenu popupMenu = new PopupMenu(wrapper, view);
        popupMenu.getMenuInflater().inflate(R.menu.all_users_controls, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {


                    case R.id.itemDelete:

                        presenter.deleteUser(user);
                        presenter.loadContent();
                        Toast.makeText(getBaseContext(), "deleted", Toast.LENGTH_SHORT).show();


                        break;


                    case R.id.itemUpdate:

                        Toast.makeText(getBaseContext(), "updated", Toast.LENGTH_SHORT).show();


                        break;


                }


                return true;
            }
        });

        popupMenu.show();


    }


    @Override
    public void onUserClicked(Users user) {

        presenter.onUserSelected(user);


    }


    @Override
    public void onUserLongClicked(Users user, View view) {


        presenter.onUserLongSelected(user, view);


    }


}
