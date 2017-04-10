package com.example.lucia.goevent;

/**
 * Created by lucia on 9.4.2017.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends Activity {
    EditText editTextUserName, editTextPassword, editTextConfirmPassword;
    Button btnCreateAccount;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();


        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                // skontroluje či su prázdne miesta
                if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Prázdne pole", Toast.LENGTH_LONG).show();
                    return;
                }
                // skontroluje či obidva hesla sa zhodujú
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Heslo sa nezhoduje", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // uloží dáta v databáze
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(), "Účet bol vytvorený ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
