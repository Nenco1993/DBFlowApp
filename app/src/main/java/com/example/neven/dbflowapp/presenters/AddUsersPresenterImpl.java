package com.example.neven.dbflowapp.presenters;

import android.content.Context;
import android.widget.Toast;
import com.example.neven.dbflowapp.models.Users;

/**
 * Created by Neven on 18.1.2017..
 */
public class AddUsersPresenterImpl implements AddUsersPresenter {


    private Context context;



    public AddUsersPresenterImpl(Context context) {

        this.context = context;
    }


    @Override
    public void saveUser(Users user) {


        user.save();
        Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();



    }




}
