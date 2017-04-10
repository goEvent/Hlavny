package com.example.lucia.goevent;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn, btnSignUp, btnVyberUdalosti;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // vytvorenie databazy
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();


        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);
        btnVyberUdalosti = (Button) findViewById(R.id.buttonVyberUdalosti);

        //postlačení registrácie
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// intent pre registráciu
                Intent intentSignUP = new Intent(getApplicationContext(), SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });


        btnVyberUdalosti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// intent pre registráciu
                Intent intentProfilPodujatia = new Intent(getApplicationContext(), ProfilPodujatiaActivity.class);
                startActivity(intentProfilPodujatia);
            }
        });

    }

    // fcia pre prihlásenie
    public void signIn(View V) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");


        final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

        // po stlačení prihlásenia
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // dostaneme reťazec hesla a username
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                // načíta databázu hesiel pre príslušné username
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                // skontroluje či sedí heslo s username
                if (password.equals(storedPassword)) {

                    Intent intentMenu = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intentMenu);
                } else {
                    Toast.makeText(MainActivity.this, "Heslo sa nezhoduje", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }


}
