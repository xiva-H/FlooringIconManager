package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class editProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner editfloorSpinner;
    private Spinner editstoreSpinner;
    private Button editsearchB;
    private EditText editproductNameInput;


    //spinners for the dynamically visibile options
    private Spinner editwoodInput1;
    private Spinner editwoodInput2;
    private Spinner editstoneMaterialInput;
    private Spinner editlaminateInput;
    private Spinner editvinylInput;
    private Spinner edittileInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        editstoreSpinner = findViewById(R.id.editStoreId);
        ArrayAdapter<CharSequence> storeAdapter = ArrayAdapter.createFromResource(this, R.array.storeSearch, android.R.layout.simple_spinner_item);
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editstoreSpinner.setAdapter(storeAdapter);
        editstoreSpinner.setOnItemSelectedListener(this);

        editfloorSpinner = findViewById(R.id.editType);
        ArrayAdapter<CharSequence> floorAdapter = ArrayAdapter.createFromResource(this, R.array.floorSearch, android.R.layout.simple_spinner_item);
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editfloorSpinner.setAdapter(floorAdapter);
        editfloorSpinner.setOnItemSelectedListener(this);

        editwoodInput1 = findViewById(R.id.editWoodType1);
        ArrayAdapter<CharSequence> wood1Adapter = ArrayAdapter.createFromResource(this, R.array.wood1Search, android.R.layout.simple_spinner_item);
        wood1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editwoodInput1.setAdapter(wood1Adapter);
        editwoodInput1.setOnItemSelectedListener(this);

        editwoodInput2 = findViewById(R.id.editWoodType2);
        ArrayAdapter<CharSequence> wood2Adapter = ArrayAdapter.createFromResource(this, R.array.wood2Search, android.R.layout.simple_spinner_item);
        wood2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editwoodInput2.setAdapter(wood2Adapter);
        editwoodInput2.setOnItemSelectedListener(this);

        editstoneMaterialInput = findViewById(R.id.editStoneType);
        ArrayAdapter<CharSequence> stoneAdapter = ArrayAdapter.createFromResource(this, R.array.stoneSearch, android.R.layout.simple_spinner_item);
        stoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editstoneMaterialInput.setAdapter(stoneAdapter);
        editstoneMaterialInput.setOnItemSelectedListener(this);


        editlaminateInput = findViewById(R.id.editLaminateType);
        ArrayAdapter<CharSequence> laminateAdapter = ArrayAdapter.createFromResource(this, R.array.laminateSearch, android.R.layout.simple_spinner_item);
        laminateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editlaminateInput.setAdapter(laminateAdapter);
        editlaminateInput.setOnItemSelectedListener(this);

        editvinylInput = findViewById(R.id.editVinylType);
        ArrayAdapter<CharSequence> vinylAdapter = ArrayAdapter.createFromResource(this, R.array.vinylSearch, android.R.layout.simple_spinner_item);
        vinylAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editvinylInput.setAdapter(vinylAdapter);
        editvinylInput.setOnItemSelectedListener(this);

        edittileInput = findViewById(R.id.editTileType);
        ArrayAdapter<CharSequence> tileAdapter = ArrayAdapter.createFromResource(this, R.array.tileSearch, android.R.layout.simple_spinner_item);
        tileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edittileInput.setAdapter(tileAdapter);
        edittileInput.setOnItemSelectedListener(this);


        editsearchB = (Button) findViewById(R.id.editProdBut);
        editsearchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editproductNameInput = findViewById(R.id.editProductName);
                String productnameStr = editproductNameInput.getText().toString();
                String storeInput = editstoreSpinner.getSelectedItem().toString();
                String typeInput = editfloorSpinner.getSelectedItem().toString();
                String woodspin1 = editwoodInput1.getSelectedItem().toString();
                String woodspin2 = editwoodInput2.getSelectedItem().toString();
                String stonespin = editstoneMaterialInput.getSelectedItem().toString();
                String laminatespin = editlaminateInput.getSelectedItem().toString();
                String vinylspin = editvinylInput.getSelectedItem().toString();
                String tilespin = edittileInput.getSelectedItem().toString();

                if(typeInput.equals("All Floors")
                        && productnameStr.length() == 0
                        && storeInput.equals("All Store Locations")){
                    Toast.makeText(editProductActivity.this, "Please fill in at least one field!", Toast.LENGTH_SHORT).show();

                }
                else {
                    openSearchResultActivity(productnameStr, storeInput, typeInput, woodspin1, woodspin2, stonespin, laminatespin, vinylspin, tilespin);
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
        if(text.equals("Wood")){
            editlaminateInput.setVisibility(View.INVISIBLE);
            editlaminateInput.setSelection(0);
            editstoneMaterialInput.setVisibility(View.INVISIBLE);
            editstoneMaterialInput.setSelection(0);
            editvinylInput.setVisibility(View.INVISIBLE);
            editvinylInput.setSelection(0);
            edittileInput.setVisibility(View.INVISIBLE);
            edittileInput.setSelection(0);
            editwoodInput1.setVisibility(View.VISIBLE);
            editwoodInput2.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Stone")){
            editwoodInput1.setVisibility(View.INVISIBLE);
            editwoodInput1.setSelection(0);
            editwoodInput2.setVisibility(View.INVISIBLE);
            editwoodInput2.setSelection(0);
            editlaminateInput.setVisibility(View.INVISIBLE);
            editlaminateInput.setSelection(0);
            editvinylInput.setVisibility(View.INVISIBLE);
            editvinylInput.setSelection(0);
            edittileInput.setVisibility(View.INVISIBLE);
            edittileInput.setSelection(0);
            editstoneMaterialInput.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Laminate")){
            editwoodInput1.setVisibility(View.INVISIBLE);
            editwoodInput1.setSelection(0);
            editwoodInput2.setVisibility(View.INVISIBLE);
            editwoodInput2.setSelection(0);
            editstoneMaterialInput.setVisibility(View.INVISIBLE);
            editstoneMaterialInput.setSelection(0);
            editvinylInput.setVisibility(View.INVISIBLE);
            editvinylInput.setSelection(0);
            edittileInput.setVisibility(View.INVISIBLE);
            edittileInput.setSelection(0);
            editlaminateInput.setVisibility(View.VISIBLE);

        }
        else if(text.equals("Vinyl")){
            editwoodInput1.setVisibility(View.INVISIBLE);
            editwoodInput1.setSelection(0);
            editwoodInput2.setVisibility(View.INVISIBLE);
            editwoodInput2.setSelection(0);
            editstoneMaterialInput.setVisibility(View.INVISIBLE);
            editstoneMaterialInput.setSelection(0);
            editlaminateInput.setVisibility(View.INVISIBLE);
            editlaminateInput.setSelection(0);
            edittileInput.setVisibility(View.INVISIBLE);
            edittileInput.setSelection(0);
            editvinylInput.setVisibility(View.VISIBLE);

        }
        else if(text.equals("Tile")){
            editwoodInput1.setVisibility(View.INVISIBLE);
            editwoodInput1.setSelection(0);
            editwoodInput2.setVisibility(View.INVISIBLE);
            editwoodInput2.setSelection(0);
            editstoneMaterialInput.setVisibility(View.INVISIBLE);
            editstoneMaterialInput.setSelection(0);
            editlaminateInput.setVisibility(View.INVISIBLE);
            editlaminateInput.setSelection(0);
            editvinylInput.setVisibility(View.INVISIBLE);
            editvinylInput.setSelection(0);
            edittileInput.setVisibility(View.VISIBLE);

        }
        else if(text.equals("All Floors")){
            editwoodInput1.setVisibility(View.INVISIBLE);
            editwoodInput1.setSelection(0);
            editwoodInput2.setVisibility(View.INVISIBLE);
            editwoodInput2.setSelection(0);
            editstoneMaterialInput.setVisibility(View.INVISIBLE);
            editstoneMaterialInput.setSelection(0);
            editlaminateInput.setVisibility(View.INVISIBLE);
            editlaminateInput.setSelection(0);
            editvinylInput.setVisibility(View.INVISIBLE);
            editvinylInput.setSelection(0);
            edittileInput.setVisibility(View.INVISIBLE);
            edittileInput.setSelection(0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void openSearchResultActivity(String name,
                                         String store,
                                         String floor,
                                         String wood1,
                                         String wood2,
                                         String stone1,
                                         String laminate1,
                                         String vinyl1,
                                         String tile1){
        Intent searchEditIntent = new Intent(this, searchEditResultActivity.class);
        searchEditIntent.putExtra("name", name);
        searchEditIntent.putExtra("store", store);
        searchEditIntent.putExtra("floor", floor);
        searchEditIntent.putExtra("wood1", wood1);
        searchEditIntent.putExtra("wood2", wood2);
        searchEditIntent.putExtra("stone1", stone1);
        searchEditIntent.putExtra("laminate1", laminate1);
        searchEditIntent.putExtra("vinyl1", vinyl1);
        searchEditIntent.putExtra("tile1", tile1);

        //searchPhrase = productNameInput.getText().toString();
        startActivity(searchEditIntent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}