package com.example.neven.dbflowapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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


        presenter = new UsersPresenterImpl(this, getLayoutInflater());
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


                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                        alertDialogBuilder.setMessage("Update user:");
                        View viewUpdate = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                        alertDialogBuilder.setView(viewUpdate);

                        final EditText etFirstNameUpdate = (EditText) viewUpdate.findViewById(R.id.etFirstNameUpdate);
                        final EditText etLastNameUpdate = (EditText) viewUpdate.findViewById(R.id.etLastNameUpdate);
                        final EditText etAddressUpdate = (EditText) viewUpdate.findViewById(R.id.etAddressUpdate);
                        final EditText etPhoneNumberUpdate = (EditText) viewUpdate.findViewById(R.id.etPhoneNumberUpdate);
                        final EditText etRoomNumberUpdate = (EditText) viewUpdate.findViewById(R.id.etRoomNumberUpdate);


                        etFirstNameUpdate.setText(user.firstName);
                        etLastNameUpdate.setText(user.lastName);
                        etAddressUpdate.setText(user.address);
                        etPhoneNumberUpdate.setText(user.phoneNumber);
                        etRoomNumberUpdate.setText(user.roomNumber);


                        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                String fname = etFirstNameUpdate.getText().toString();
                                String lname = etLastNameUpdate.getText().toString();
                                String address = etAddressUpdate.getText().toString();
                                String phoneNumber = etPhoneNumberUpdate.getText().toString();
                                String roomNumber = etRoomNumberUpdate.getText().toString();


                                user.firstName = fname;
                                user.lastName = lname;
                                user.address = address;
                                user.phoneNumber = phoneNumber;
                                user.roomNumber = roomNumber;


                                presenter.updateUser(user, view);
                                presenter.loadContent();
                                Toast.makeText(getBaseContext(), "updated", Toast.LENGTH_SHORT).show();

                            }
                        });

                        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();


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
