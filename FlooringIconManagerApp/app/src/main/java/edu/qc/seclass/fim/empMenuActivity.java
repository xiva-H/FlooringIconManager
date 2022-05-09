package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class empMenuActivity extends AppCompatActivity {

    Button searchProductButton;
    Button editProductButton;
    Button deleteProductButton;
    Button addProductButton;
    Button logOutButton;
    TextView empHeader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_menu);
        if(getIntent().getExtras() != null) {
            String uname = getIntent().getStringExtra("name");
            empHeader = findViewById(R.id.textView16);
            uname = "Employee #: "+ uname;
            empHeader.setText(uname);
        }


        searchProductButton = findViewById(R.id.searchProdBut);
        searchProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchProductActivity();
            }
        });
        editProductButton = findViewById(R.id.editProdBut);
        editProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditProductActivity();
            }
        });
        deleteProductButton = findViewById(R.id.delProdBut);
        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeleteProductActivity();
            }
        });
        addProductButton = findViewById(R.id.addProdBut);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddProductActivity();
            }
        });
        logOutButton = findViewById(R.id.logOutBut);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogOutActivity();
            }
        });

    }

    private void openLogOutActivity() {
        Intent logOutIntent = new Intent(this, MainActivity.class);
        startActivity(logOutIntent);
    }

    private void openAddProductActivity() {
        Intent addProductIntent = new Intent(this, addProductActivity.class);
        startActivity(addProductIntent);
    }

    private void openDeleteProductActivity() {
        Intent delProductIntent = new Intent(this, delProductActivity.class);
        startActivity(delProductIntent);
    }

    private void openEditProductActivity() {
        Intent editProductIntent = new Intent(this, editProductActivity.class);
        startActivity(editProductIntent);
    }

    private void openSearchProductActivity() {
        Intent searchProductIntent = new Intent(this, searchActivity.class);
        startActivity(searchProductIntent);
    }
}