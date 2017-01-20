package com.example.neven.dbflowapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.neven.dbflowapp.R;
import com.example.neven.dbflowapp.models.Users;

public class UserDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tvFirstName)
    TextView tvFirstName;

    @BindView(R.id.tvLastName)
    TextView tvLastName;

    @BindView(R.id.tvPhoneNumber)
    TextView tvPhoneNumber;

    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.tvRoomNumber)
    TextView tvRoomNumber;

    @BindView(R.id.tvVIP)
    TextView tvVip;

    @BindString(R.string.regular)
    String regular;

    @BindString(R.string.vip)
    String vip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        Users singleUser = (Users) bundle.getSerializable("user");

        tvFirstName.setText(singleUser.firstName);
        tvLastName.setText(singleUser.lastName);
        tvPhoneNumber.setText(singleUser.phoneNumber);
        tvAddress.setText(singleUser.address);
        tvRoomNumber.setText(singleUser.roomNumber);
        if (singleUser.vip) {


            tvVip.setText(vip);


        } else {

            tvVip.setText(regular);


        }


    }
}
