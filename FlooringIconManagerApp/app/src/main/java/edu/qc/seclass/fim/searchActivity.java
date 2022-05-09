package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class searchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner floorSpinner;
    //private Spinner categorySpinner;
    private Spinner storeSpinner;
    private Button searchB;
    private EditText productNameInput;

    //spinners for the dynamically visibile options
    private Spinner woodInput1;
    private Spinner woodInput2;
    private Spinner stoneMaterialInput;
    private Spinner laminateInput;
    private Spinner vinylInput;
    private Spinner tileInput;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        storeSpinner = findViewById(R.id.storeIdInput);
        ArrayAdapter<CharSequence> storeAdapter = ArrayAdapter.createFromResource(this, R.array.storeSearch, android.R.layout.simple_spinner_item);
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        storeSpinner.setAdapter(storeAdapter);
        storeSpinner.setOnItemSelectedListener(this);

        floorSpinner = findViewById(R.id.floorInput);
        ArrayAdapter<CharSequence> floorAdapter = ArrayAdapter.createFromResource(this, R.array.floorSearch, android.R.layout.simple_spinner_item);
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorSpinner.setAdapter(floorAdapter);
        floorSpinner.setOnItemSelectedListener(this);

        woodInput1 = findViewById(R.id.woodInput1);
        ArrayAdapter<CharSequence> wood1Adapter = ArrayAdapter.createFromResource(this, R.array.wood1Search, android.R.layout.simple_spinner_item);
        wood1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        woodInput1.setAdapter(wood1Adapter);
        woodInput1.setOnItemSelectedListener(this);

        woodInput2 = findViewById(R.id.woodInput2);
        ArrayAdapter<CharSequence> wood2Adapter = ArrayAdapter.createFromResource(this, R.array.wood2Search, android.R.layout.simple_spinner_item);
        wood2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        woodInput2.setAdapter(wood2Adapter);
        woodInput2.setOnItemSelectedListener(this);

        stoneMaterialInput = findViewById(R.id.stoneMaterialInput);
        ArrayAdapter<CharSequence> stoneAdapter = ArrayAdapter.createFromResource(this, R.array.stoneSearch, android.R.layout.simple_spinner_item);
        stoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stoneMaterialInput.setAdapter(stoneAdapter);
        stoneMaterialInput.setOnItemSelectedListener(this);


        laminateInput = findViewById(R.id.laminateInput);
        ArrayAdapter<CharSequence> laminateAdapter = ArrayAdapter.createFromResource(this, R.array.laminateSearch, android.R.layout.simple_spinner_item);
        laminateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        laminateInput.setAdapter(laminateAdapter);
        laminateInput.setOnItemSelectedListener(this);

        vinylInput = findViewById(R.id.vinylInput);
        ArrayAdapter<CharSequence> vinylAdapter = ArrayAdapter.createFromResource(this, R.array.vinylSearch, android.R.layout.simple_spinner_item);
        vinylAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vinylInput.setAdapter(vinylAdapter);
        vinylInput.setOnItemSelectedListener(this);

        tileInput = findViewById(R.id.tileInput);
        ArrayAdapter<CharSequence> tileAdapter = ArrayAdapter.createFromResource(this, R.array.tileSearch, android.R.layout.simple_spinner_item);
        tileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tileInput.setAdapter(tileAdapter);
        tileInput.setOnItemSelectedListener(this);


        searchB = (Button) findViewById(R.id.searchButton);
        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productNameInput = findViewById(R.id.productNameInput);
                String productnameStr = productNameInput.getText().toString();
                String storeInput = storeSpinner.getSelectedItem().toString();
                String typeInput = floorSpinner.getSelectedItem().toString();
                String woodspin1 = woodInput1.getSelectedItem().toString();
                String woodspin2 = woodInput2.getSelectedItem().toString();
                String stonespin = stoneMaterialInput.getSelectedItem().toString();
                String laminatespin = laminateInput.getSelectedItem().toString();
                String vinylspin = vinylInput.getSelectedItem().toString();
                String tilespin = tileInput.getSelectedItem().toString();

                if(typeInput.equals("All Floors")
                        && productnameStr.length() == 0
                        && storeInput.equals("All Store Locations")){
                    Toast.makeText(searchActivity.this, "Please fill in at least one field!", Toast.LENGTH_SHORT).show();

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
            laminateInput.setVisibility(View.INVISIBLE);
            laminateInput.setSelection(0);
            stoneMaterialInput.setVisibility(View.INVISIBLE);
            stoneMaterialInput.setSelection(0);
            vinylInput.setVisibility(View.INVISIBLE);
            vinylInput.setSelection(0);
            tileInput.setVisibility(View.INVISIBLE);
            tileInput.setSelection(0);
            woodInput1.setVisibility(View.VISIBLE);
            woodInput2.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Stone")){
            woodInput1.setVisibility(View.INVISIBLE);
            woodInput1.setSelection(0);
            woodInput2.setVisibility(View.INVISIBLE);
            woodInput2.setSelection(0);
            laminateInput.setVisibility(View.INVISIBLE);
            laminateInput.setSelection(0);
            vinylInput.setVisibility(View.INVISIBLE);
            vinylInput.setSelection(0);
            tileInput.setVisibility(View.INVISIBLE);
            tileInput.setSelection(0);
            stoneMaterialInput.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Laminate")){
            woodInput1.setVisibility(View.INVISIBLE);
            woodInput1.setSelection(0);
            woodInput2.setVisibility(View.INVISIBLE);
            woodInput2.setSelection(0);
            stoneMaterialInput.setVisibility(View.INVISIBLE);
            stoneMaterialInput.setSelection(0);
            vinylInput.setVisibility(View.INVISIBLE);
            vinylInput.setSelection(0);
            tileInput.setVisibility(View.INVISIBLE);
            tileInput.setSelection(0);
            laminateInput.setVisibility(View.VISIBLE);

        }
        else if(text.equals("Vinyl")){
            woodInput1.setVisibility(View.INVISIBLE);
            woodInput1.setSelection(0);
            woodInput2.setVisibility(View.INVISIBLE);
            woodInput2.setSelection(0);
            stoneMaterialInput.setVisibility(View.INVISIBLE);
            stoneMaterialInput.setSelection(0);
            laminateInput.setVisibility(View.INVISIBLE);
            laminateInput.setSelection(0);
            tileInput.setVisibility(View.INVISIBLE);
            tileInput.setSelection(0);
            vinylInput.setVisibility(View.VISIBLE);

        }
        else if(text.equals("Tile")){
            woodInput1.setVisibility(View.INVISIBLE);
            woodInput1.setSelection(0);
            woodInput2.setVisibility(View.INVISIBLE);
            woodInput2.setSelection(0);
            stoneMaterialInput.setVisibility(View.INVISIBLE);
            stoneMaterialInput.setSelection(0);
            laminateInput.setVisibility(View.INVISIBLE);
            laminateInput.setSelection(0);
            vinylInput.setVisibility(View.INVISIBLE);
            vinylInput.setSelection(0);
            tileInput.setVisibility(View.VISIBLE);

        }
        else if(text.equals("All Floors")){
            woodInput1.setVisibility(View.INVISIBLE);
            woodInput1.setSelection(0);
            woodInput2.setVisibility(View.INVISIBLE);
            woodInput2.setSelection(0);
            stoneMaterialInput.setVisibility(View.INVISIBLE);
            stoneMaterialInput.setSelection(0);
            laminateInput.setVisibility(View.INVISIBLE);
            laminateInput.setSelection(0);
            vinylInput.setVisibility(View.INVISIBLE);
            vinylInput.setSelection(0);
            tileInput.setVisibility(View.INVISIBLE);
            tileInput.setSelection(0);
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
        Intent searchIntent = new Intent(this, searchResultActivity.class);
        searchIntent.putExtra("name", name);
        searchIntent.putExtra("store", store);
        searchIntent.putExtra("floor", floor);
        searchIntent.putExtra("wood1", wood1);
        searchIntent.putExtra("wood2", wood2);
        searchIntent.putExtra("stone1", stone1);
        searchIntent.putExtra("laminate1", laminate1);
        searchIntent.putExtra("vinyl1", vinyl1);
        searchIntent.putExtra("tile1", tile1);

        startActivity(searchIntent);
    }

}

