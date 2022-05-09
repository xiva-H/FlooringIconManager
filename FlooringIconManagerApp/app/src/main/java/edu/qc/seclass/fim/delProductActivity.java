package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class delProductActivity extends AppCompatActivity {

    public Button deleteButton;
    public TextView productId;
    public TextView storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_product);

        productId = findViewById(R.id.delProductID);
        storeId = findViewById(R.id.delStoreID);
        deleteButton = findViewById(R.id.delProdBut);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productId.getText().toString().length() == 0 || storeId.getText().toString().length() == 0){
                    Toast.makeText(delProductActivity.this, "Please enter product id and store id!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DBO myDB = new DBO(delProductActivity.this);
                    if (myDB.deleteProduct(productId.getText().toString(), storeId.getText().toString()) != 0) {
                        Toast.makeText(delProductActivity.this, "Floor deleted!", Toast.LENGTH_SHORT).show();
                        Intent openEmployeeMenuIntent = new Intent(delProductActivity.this, empMenuActivity.class);
                        startActivity(openEmployeeMenuIntent);
                    } else {
                        Toast.makeText(delProductActivity.this, "Cant find floor!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}