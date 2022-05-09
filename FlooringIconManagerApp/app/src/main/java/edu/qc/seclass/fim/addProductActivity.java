package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //feilds from the activity
    private EditText productNameInput;
    private EditText idInput;
    private EditText brandInput;
    private EditText colorInput;
    private EditText sizeInput;
    private EditText qtyInput;
    private EditText priceInput;
    private Spinner storeSpinner;
    private Spinner floorSpinner;
    private Spinner woodInput1;
    private Spinner woodInput2;
    private Spinner stoneMaterialInput;
    private Spinner laminateInput;
    private Spinner vinylInput;
    private Spinner tileInput;
    private Button addProductB;
    private DBO addDBO;
    //will be used to verify fields
    private boolean id, name, store, brand, color, size, qty, price, floor,
            lam, stone, tile, vinyl, wood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //spinners for the user inputs
        storeSpinner = findViewById(R.id.add_storeid);
        ArrayAdapter<CharSequence> storeAdapter = ArrayAdapter.createFromResource(this, R.array.add_storestr, android.R.layout.simple_spinner_item);
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        storeSpinner.setAdapter(storeAdapter);
        storeSpinner.setOnItemSelectedListener(this);

        floorSpinner = findViewById(R.id.add_floor);
        ArrayAdapter<CharSequence> floorAdapter = ArrayAdapter.createFromResource(this, R.array.add_floorstr, android.R.layout.simple_spinner_item);
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorSpinner.setAdapter(floorAdapter);
        floorSpinner.setOnItemSelectedListener(this);

        woodInput1 = findViewById(R.id.add_wood1);
        ArrayAdapter<CharSequence> wood1Adapter = ArrayAdapter.createFromResource(this, R.array.add_wood1str, android.R.layout.simple_spinner_item);
        wood1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        woodInput1.setAdapter(wood1Adapter);
        woodInput1.setOnItemSelectedListener(this);

        woodInput2 = findViewById(R.id.add_wood2);
        ArrayAdapter<CharSequence> wood2Adapter = ArrayAdapter.createFromResource(this, R.array.add_wood2str, android.R.layout.simple_spinner_item);
        wood1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        woodInput2.setAdapter(wood2Adapter);
        woodInput2.setOnItemSelectedListener(this);

        stoneMaterialInput = findViewById(R.id.add_stone);
        ArrayAdapter<CharSequence> stoneAdapter = ArrayAdapter.createFromResource(this, R.array.add_stonestr, android.R.layout.simple_spinner_item);
        stoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stoneMaterialInput.setAdapter(stoneAdapter);
        stoneMaterialInput.setOnItemSelectedListener(this);


        laminateInput = findViewById(R.id.add_laminate);
        ArrayAdapter<CharSequence> laminateAdapter = ArrayAdapter.createFromResource(this, R.array.add_laminatestr, android.R.layout.simple_spinner_item);
        laminateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        laminateInput.setAdapter(laminateAdapter);
        laminateInput.setOnItemSelectedListener(this);

        vinylInput = findViewById(R.id.add_vinyl);
        ArrayAdapter<CharSequence> vinylAdapter = ArrayAdapter.createFromResource(this, R.array.add_vinylstr, android.R.layout.simple_spinner_item);
        vinylAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vinylInput.setAdapter(vinylAdapter);
        vinylInput.setOnItemSelectedListener(this);

        tileInput = findViewById(R.id.add_tile);
        ArrayAdapter<CharSequence> tileAdapter = ArrayAdapter.createFromResource(this, R.array.add_tilestr, android.R.layout.simple_spinner_item);
        tileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tileInput.setAdapter(tileAdapter);
        tileInput.setOnItemSelectedListener(this);

        addProductB = (Button) findViewById(R.id.addProdBut2);
        addProductB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //grabbing input from all fields and converting them to string

                idInput = findViewById(R.id.addProductID);
                String idInputStr = idInput.getText().toString();

                productNameInput = findViewById(R.id.addProductName);
                String productnameStr = productNameInput.getText().toString();

                brandInput = findViewById(R.id.addBrand);
                String brandInputStr = brandInput.getText().toString();

                colorInput = findViewById(R.id.addColor);
                String colorInputStr = colorInput.getText().toString();

                sizeInput = findViewById(R.id.addSize);
                String sizeInputStr = sizeInput.getText().toString();

                qtyInput = findViewById(R.id.add_qty);
                String qtyInputStr = qtyInput.getText().toString();

                priceInput = findViewById(R.id.addPrice);
                String priceInputStr = priceInput.getText().toString();

                /*setting floor subtypes to be false so it can do a manual check to see if they are valid*/
                lam = false;
                stone = false;
                vinyl = false;
                tile = false;
                wood = false;


                //grabbing input from all fields and converting them to string
                String storeInput = storeSpinner.getSelectedItem().toString();
                String typeInput = floorSpinner.getSelectedItem().toString();
                String woodspin1 = woodInput1.getSelectedItem().toString();
                String woodspin2 = woodInput2.getSelectedItem().toString();
                String stonespin = stoneMaterialInput.getSelectedItem().toString();
                String laminatespin = laminateInput.getSelectedItem().toString();
                String vinylspin = vinylInput.getSelectedItem().toString();
                String tilespin = tileInput.getSelectedItem().toString();

                addDBO = new DBO(addProductActivity.this);

                /*checking if fields are valid. if they are valid then their respective boolean value
                * will be set to true which will allow add product to DB to run*/
                if(idInputStr.length() == 0){
                    id = false;
                    Toast.makeText(addProductActivity.this, "Enter Product ID!", Toast.LENGTH_SHORT).show();
                }
                else{
                    id = true;
                }
                if(productnameStr.length() == 0){
                    name = false;
                    Toast.makeText(addProductActivity.this, "Enter Product Name!", Toast.LENGTH_SHORT).show();
                }
                else{
                    name = true;
                }
                if(storeInput.equals("Add Store")){
                    store = false;
                    Toast.makeText(addProductActivity.this, "Enter Store!", Toast.LENGTH_SHORT).show();
                }
                else{
                    store = true;
                }
                if(brandInputStr.length() == 0){
                    brand = false;
                    Toast.makeText(addProductActivity.this, "Enter Brand!", Toast.LENGTH_SHORT).show();
                }
                else{
                    brand = true;
                }
                if(colorInputStr.length() == 0){
                    color = false;
                    Toast.makeText(addProductActivity.this, "Enter Color!", Toast.LENGTH_SHORT).show();
                }
                else{
                    color = true;
                }
                if(sizeInputStr.length() == 0){
                    size = false;
                    Toast.makeText(addProductActivity.this, "Enter Size!", Toast.LENGTH_SHORT).show();
                }
                else{
                    size = true;
                }
                if(qtyInputStr.length() == 0){
                    qty = false;
                    Toast.makeText(addProductActivity.this, "Enter Quantity!", Toast.LENGTH_SHORT).show();
                }
                else{
                    qty = true;
                }
                if(priceInputStr.length() == 0){
                    price = false;
                    Toast.makeText(addProductActivity.this, "Enter Price!", Toast.LENGTH_SHORT).show();
                }
                else{
                    price = true;
                }
                if(typeInput.equals("Add Floor")){
                    floor = false;
                    Toast.makeText(addProductActivity.this, "Enter Floor Type!", Toast.LENGTH_SHORT).show();
                }
                else{
                    floor = true;
                }
                if(typeInput.equals("Laminate")){
                    if(laminatespin.equals("Add Laminate Type")){
                        Toast.makeText(addProductActivity.this, "Enter Laminate Type!", Toast.LENGTH_SHORT).show();
                        lam = false;
                    }
                    else {
                        lam = true;
                    }
                }
                if(typeInput.equals("Stone")){
                    if(stonespin.equals("Add Stone Materials")){
                        Toast.makeText(addProductActivity.this, "Enter Stone Type!", Toast.LENGTH_SHORT).show();
                        stone = false;
                    }
                    else{
                        stone = true;
                    }
                }
                if(typeInput.equals("Tile")){
                    if(tilespin.equals("Add Tile Material")){
                        Toast.makeText(addProductActivity.this, "Enter Tile Type!", Toast.LENGTH_SHORT).show();
                        tile = false;
                    }
                    else {
                        tile = true;
                    }
                }
                if(typeInput.equals("Vinyl")){
                    if(vinylspin.equals("Add Vinyl Type")){
                        Toast.makeText(addProductActivity.this, "Enter Vinyl Type!", Toast.LENGTH_SHORT).show();
                        vinyl = false;
                    }
                    else {
                        vinyl = true;
                    }
                }
                if(typeInput.equals("Wood")){
                    if(woodspin1.equals("Add Wood Types") || woodspin2.equals("Add Wood Species")){
                        Toast.makeText(addProductActivity.this, "Enter Wood Type!", Toast.LENGTH_SHORT).show();
                        wood = false;
                    }
                    else {
                        wood = true;
                    }
                }


                //checking if all inputs are valid
                if(id && name && store && brand && color && size && qty && price && floor &&
                        (lam || stone || tile || vinyl || wood)) {


                    addProductToDB(idInputStr,
                            productnameStr,
                            colorInputStr,
                            sizeInputStr,
                            priceInputStr,
                            brandInputStr,
                            typeInput,
                            storeInput,
                            qtyInputStr,
                            woodspin1,
                            woodspin2,
                            stonespin,
                            laminatespin,
                            vinylspin,
                            tilespin);

                }


            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        /* This takes care of the spinners and resets other spinners to default value if one spinner
        * is selected. It also controls visibility of sub types so only the correct subtype will
        * show up*/
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
        else if(text.equals("Add Floor")){
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
    //function to add products to database
    private void addProductToDB(String proId,
                                String name,
                                String color,
                                String size,
                                String price,
                                String brand,
                                String floor,
                                String store,
                                String qty,
                                String wood1,
                                String wood2,
                                String stone,
                                String lam1,
                                String vinyl1,
                                String tile1){

        //add row returns a long, if value is -1 floor was not successfully added.
        if(addDBO.addRow(proId, name, color, size, price, brand, floor, store, qty, wood1, wood2, stone, lam1, vinyl1, tile1) !=  -1){
            Toast.makeText(addProductActivity.this, "Floor Added", Toast.LENGTH_LONG).show();
            Intent openEmployeeMenuIntent = new Intent(addProductActivity.this, empMenuActivity.class);
            startActivity(openEmployeeMenuIntent);
        }
        else{
            Toast.makeText(addProductActivity.this, "Oops! something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}