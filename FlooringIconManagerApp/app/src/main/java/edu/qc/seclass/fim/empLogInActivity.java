package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class empLogInActivity extends AppCompatActivity {

    private Button empLogInButton2;
    EditText empUserName, empPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_log_in);

        empLogInButton2 = findViewById(R.id.empLogInButton2);
        empUserName = findViewById(R.id.empUserName);
        empPassword = findViewById(R.id.empPassword);

        empLogInButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBO dbo = new DBO(empLogInActivity.this);
                //checking employee credentials, if true then proceed to next activity, if false unable to log in
                //the default login is username: admin password:admin
                if(dbo.checkEmpCredentials(empUserName.getText().toString(), empPassword.getText().toString())){
                    openEmployeeMenu();
                }
                else {
                    Toast.makeText(empLogInActivity.this, "Unable to log-in. Please check credentials", Toast.LENGTH_SHORT).show();

                }



            }
        });
    }

    public void openEmployeeMenu(){
        Intent openEmployeeMenuIntent = new Intent(this, empMenuActivity.class);
        openEmployeeMenuIntent.putExtra("name", empUserName.getText().toString());
        startActivity(openEmployeeMenuIntent);
    }
}