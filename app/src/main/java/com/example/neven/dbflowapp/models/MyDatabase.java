package com.example.neven.dbflowapp.models;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Neven on 18.1.2017..
 */

@Database(name = MyDatabase.NAME,version = MyDatabase.VERSION)
public class MyDatabase {

    public static final String NAME = "users";
    public static final int VERSION = 1;

}
