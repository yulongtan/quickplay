package yulongproductions.com.quickplay;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    private EditText mNameText;
    private EditText mPassword;
    private Button loginButton;
    private GlobalFunctions mGlobalFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        this.mNameText = (EditText) findViewById(R.id.nameEditText);
        this.mPassword = (EditText) findViewById(R.id.passwordEditText);
        this.loginButton = (Button) findViewById(R.id.loginButton);
        this.mGlobalFunctions = new GlobalFunctions(this);
        this.mGlobalFunctions.setupUI(findViewById(R.id.mainLayout));



        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = convert(mNameText);
                String password = convert(mPassword);
                if (isEmpty(username) || isEmpty(password)) {
                    alertUserAboutError();
                } else {
                    startApp(username, password);
                }
            }
        });
    }

    private void startApp(String username, String password) {
        Intent intent = new Intent(MainActivity.this, FindGameActivity.class);
        intent.putExtra(getString(R.string.username), username);
        intent.putExtra(getString(R.string.password), password);
        startActivity(intent);
    }

    private String convert(EditText e) {
        return e.getText().toString();
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_message");
    }

    private boolean isEmpty(String field) {
        return field == null || field.equals("");
    }
}
