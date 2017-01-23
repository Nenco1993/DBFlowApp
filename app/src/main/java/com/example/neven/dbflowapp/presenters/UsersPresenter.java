package com.example.neven.dbflowapp.presenters;

import android.view.View;
import com.example.neven.dbflowapp.models.Users;

/**
 * Created by Neven on 18.1.2017..
 */
public interface UsersPresenter {

    void loadContent();

    void onUserSelected(Users user);

    void onUserLongSelected(Users user,View view);

    void deleteUser(Users user);

    void updateUser(Users user,View view);


}
