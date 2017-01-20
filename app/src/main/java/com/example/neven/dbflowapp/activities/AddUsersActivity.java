package com.example.neven.dbflowapp.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.neven.dbflowapp.R;
import com.example.neven.dbflowapp.models.Users;
import com.example.neven.dbflowapp.presenters.AddUsersPresenter;
import com.example.neven.dbflowapp.presenters.AddUsersPresenterImpl;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

public class AddUsersActivity extends AppCompatActivity {

    @BindView(R.id.etFirstName)
    EditText etFirstName;

    @BindView(R.id.etLastName)
    EditText etLastName;

    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;

    @BindView(R.id.etAddress)
    EditText etAddress;

    @BindView(R.id.etRoomNumber)
    EditText etRoomNumber;

    @BindView(R.id.rbYes)
    RadioButton rbYes;

    @BindView(R.id.rbNo)
    RadioButton rbNo;

    @BindView(R.id.bSave)
    Button bSave;

    @BindView(R.id.bShowAllUsers)
    Button bShowAllUsers;
    private AddUsersPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);

        ButterKnife.bind(this);


        presenter = new AddUsersPresenterImpl(getBaseContext());


    }


    @OnClick(R.id.bSave)
    void saveClicked() {

        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String address = etAddress.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String roomNumber = etRoomNumber.getText().toString();
        boolean rbYesState = rbYes.isChecked();
        boolean rbNoState = rbNo.isChecked();

        Users user = new Users();
        user.firstName = firstName;
        user.lastName = lastName;
        user.address = address;
        user.phoneNumber = phoneNumber;
        user.roomNumber = roomNumber;

        if (rbYesState) {

            user.vip = true;


        } else if (rbNoState) {

            user.vip = false;


        } else {

            user.vip = false;

        }

        presenter.saveUser(user);

        Intent intent = new Intent(getBaseContext(), AllUsersActivity.class);
        startActivity(intent);


    }

    @OnClick(R.id.bShowAllUsers)
    void showAllUsers(){

        Intent intent = new Intent(getBaseContext(), AllUsersActivity.class);
        startActivity(intent);


    }
}
