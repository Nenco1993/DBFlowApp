package com.example.neven.dbflowapp.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by Neven on 18.1.2017..
 */

@Table(database = MyDatabase.class)
public class Users extends BaseModel implements Serializable {


    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String firstName;

    @Column
    public String lastName;

    @Column
    public String phoneNumber;

    @Column
    public String address;

    @Column
    public String roomNumber;

    @Column
    public boolean vip;


}
