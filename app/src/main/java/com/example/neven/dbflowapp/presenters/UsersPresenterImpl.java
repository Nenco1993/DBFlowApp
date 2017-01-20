package com.example.neven.dbflowapp.presenters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;
import com.example.neven.dbflowapp.models.Users;
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

    public UsersPresenterImpl(UsersView usersView) {
        this.usersView = usersView;
    }

    @Override
    public void loadContent() {

        SQLite.select().from(Users.class).async().queryResultCallback(this).execute();


    }

    @Override
    public void onUserLongSelected(Users user, View view) {

        usersView.showPopUpMenu(view);

    }

    @Override
    public void onUserSelected(Users user) {

        usersView.showUsersDetails(user);

    }

    @Override
    public void onQueryResult(QueryTransaction<Users> transaction, @NonNull CursorResult<Users> tResult) {


        List<Users> listUsers = tResult.toListClose();
        usersView.showUsers(listUsers);


    }

    @Override
    public void onError(Transaction transaction, Throwable error) {
        error.printStackTrace();

    }
}
