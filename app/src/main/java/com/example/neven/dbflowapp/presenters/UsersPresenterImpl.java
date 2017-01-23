package com.example.neven.dbflowapp.presenters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.neven.dbflowapp.R;
import com.example.neven.dbflowapp.models.Users;
import com.example.neven.dbflowapp.models.Users_Table;
import com.example.neven.dbflowapp.views.UsersView;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

/**
 * Created by Neven on 18.1.2017..
 */
public class UsersPresenterImpl implements UsersPresenter, QueryTransaction.QueryResultCallback<Users>, Transaction.Error {

    private UsersView usersView;
    private String whichQuery;
    private LayoutInflater inflater;
    private Context context;


    public UsersPresenterImpl(UsersView usersView, LayoutInflater inflater) {
        this.usersView = usersView;
        this.inflater = inflater;

    }

    @Override
    public void loadContent() {

        whichQuery = "save";

        SQLite.select().from(Users.class).async().queryResultCallback(this).execute();


    }

    @Override
    public void onUserLongSelected(Users user, View view) {

        usersView.showPopUpMenu(view, user);

    }


    @Override
    public void onUserSelected(Users user) {

        usersView.showUsersDetails(user);

    }

    @Override
    public void deleteUser(Users user) {


        user.delete();


    }

    @Override
    public void updateUser(Users user, View view) {

        whichQuery = "update";

        context = view.getContext();

        SQLite.select().from(Users.class).where(Users_Table.id.eq(user.id)).async().queryResultCallback(this).execute();

        user.update();


    }

    @Override
    public void onQueryResult(final QueryTransaction<Users> transaction, @NonNull final CursorResult<Users> tResult) {

        switch (whichQuery) {

            case "save":

                List<Users> listUsers = tResult.toListClose();
                usersView.showUsers(listUsers);


                break;


        }


    }

    @Override
    public void onError(Transaction transaction, Throwable error) {
        error.printStackTrace();

    }
}
