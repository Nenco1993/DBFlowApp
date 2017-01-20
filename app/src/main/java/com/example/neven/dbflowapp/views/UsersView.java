package com.example.neven.dbflowapp.views;

import android.view.View;
import com.example.neven.dbflowapp.models.Users;

import java.util.List;

/**
 * Created by Neven on 18.1.2017..
 */
public interface UsersView {

    void showUsers(List<Users> listUsers);

    void showUsersDetails(Users user);

    void showPopUpMenu(View view);

}
