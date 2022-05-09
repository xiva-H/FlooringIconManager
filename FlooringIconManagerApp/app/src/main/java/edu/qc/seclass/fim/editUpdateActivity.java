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

public class editUpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //fields from edit product input
    EditText p_name, p_brand, p_color, p_size, p_id, p_price, p_qty;
    private Spinner updateStoreSpinner, updatefloorSpinner, updateWood1Spinner, updateWood2Spinner,
    updateStoneSpinner, updateLaminateSpinner, updateVinylSpinner, updateTileSpinner;
    //flags to check if fields are empty
    private boolean b_id, b_name, b_store, b_brand, b_color, b_size, b_qty, b_price, floor,
            lam, stone, tile, vinyl, wood;
    DBO db = new DBO(editUpdateActivity.this);
    Button u_but;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_update);
        //linking user inputs
        p_id = findViewById(R.id.eu_productid);
        p_name = findViewById(R.id.eu_name);
        p_brand = findViewById(R.id.eu_brand);
        p_color = findViewById(R.id.eu_color);
        p_size = findViewById(R.id.eu_sqft);
        p_price = findViewById(R.id.eu_price);
        p_qty = findViewById(R.id.eu_qty);

        /*grabbing data from previous activity to pass into our update function, this data was gathered
        * when you clicked an item on the list and it pulls out all the row attributes*/
        if (getIntent().getExtras() != null) {
            String key = getIntent().getStringExtra("key");
            String id = getIntent().getStringExtra("id");
            String name = getIntent().getStringExtra("name");
            String store = getIntent().getStringExtra("store");
            String brand = getIntent().getStringExtra("brand");
            String color = getIntent().getStringExtra("color");
            String size = getIntent().getStringExtra("size");
            String type = getIntent().getStringExtra("type");
            String price = getIntent().getStringExtra("price");
            String qty = getIntent().getStringExtra("qty");
            String wood1 = getIntent().getStringExtra("wood1");
            String wood2 = getIntent().getStringExtra("wood2");
            String stone1 = getIntent().getStringExtra("stone1");
            String laminate1 = getIntent().getStringExtra("laminate1");
            String vinyl1 = getIntent().getStringExtra("vinyl1");
            String tile1 = getIntent().getStringExtra("tile1");
            u_but = findViewById(R.id.updateProdBut);

            /* set the user input fields to the current information we have on the product so user
            * doesnt have to fill everything else in again*/
            p_id.setText(id);
            p_name.setText(name);
            p_brand.setText(brand);
            p_color.setText(color);
            p_size.setText(size);
            p_price.setText(price);
            p_qty.setText(qty);

            //same as above but with spinners instead of edit text
            updateStoreSpinner = findViewById(R.id.eu_store);
            ArrayAdapter<CharSequence> storeAdapter = ArrayAdapter.createFromResource(this, R.array.update_storestr, android.R.layout.simple_spinner_item);
            storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            updateStoreSpinner.setAdapter(storeAdapter);
            updateStoreSpinner.setOnItemSelectedListener(this);
            if(Integer.parseInt(store) == 1001){
                updateStoreSpinner.setSelection(0);
            }
            if(Integer.parseInt(store) == 1002){
                updateStoreSpinner.setSelection(1);
            }
            else if (Integer.parseInt(store) == 1003){
                updateStoreSpinner.setSelection(2);
            }

            updatefloorSpinner = findViewById(R.id.eu_floor);

            ArrayAdapter<CharSequence> floorAdapter = ArrayAdapter.createFromResource(this, R.array.update_floorstr, android.R.layout.simple_spinner_item);
            floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            updatefloorSpinner.setAdapter(floorAdapter);
            updatefloorSpinner.setOnItemSelectedListener(this);
            if(type.toString().equals("Laminate")){
                updatefloorSpinner.setSelection(0);
            }
            else if (type.toString().equals("Stone")){
                updatefloorSpinner.setSelection(1);
            }
            else if (type.toString().equals("Tile")){
                updatefloorSpinner.setSelection(2);
            }
            else if (type.toString().equals("Vinyl")){
                updatefloorSpinner.setSelection(3);
            }
            else if (type.toString().equals("Wood")){
                updatefloorSpinner.setSelection(4);
            }

            updateWood1Spinner = findViewById(R.id.eu_wood1);
            ArrayAdapter<CharSequence> wood1Adapter = ArrayAdapter.createFromResource(this, R.array.update_wood1str, android.R.layout.simple_spinner_item);
            wood1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            updateWood1Spinner.setAdapter(wood1Adapter);
            updateWood1Spinner.setOnItemSelectedListener(this);

            if(wood1.equals("Add Wood Types")){
                updateWood1Spinner.setSelection(0);
            }
            else if (wood1.equals("Engineered")){
                updateWood1Spinner.setSelection(1);
            }
            else if (wood1.equals("Solid")){
                updateWood1Spinner.setSelection(2);
            }
            else if (wood1.equals("Hybrid")){
                updateWood1Spinner.setSelection(3);
            }

            updateWood2Spinner = findViewById(R.id.eu_wood2);
            ArrayAdapter<CharSequence> wood2Adapter = ArrayAdapter.createFromResource(this, R.array.update_wood2str, android.R.layout.simple_spinner_item);
            wood2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            updateWood2Spinner.setAdapter(wood2Adapter);
            updateWood2Spinner.setOnItemSelectedListener(this);

            if(wood2.equals("Add Wood Species")){
                updateWood2Spinner.setSelection(0);
            }
            else if (wood2.equals("Oak")){
                updateWood2Spinner.setSelection(1);
            }
            else if (wood2.equals("Pine")){
                updateWood2Spinner.setSelection(2);
            }
            else if (wood2.equals("Maple")){
                updateWood2Spinner.setSelection(3);
            }
            else if (wood2.equals("Walnut")){
                updateWood2Spinner.setSelection(4);
            }
            else if (wood2.equals("Hickory")){
                updateWood2Spinner.setSelection(5);
            }

            updateStoneSpinner = findViewById(R.id.eu_stone);
            ArrayAdapter<CharSequence> stoneAdapter = ArrayAdapter.createFromResource(this, R.array.update_stonestr, android.R.layout.simple_spinner_item);
            stoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            updateStoneSpinner.setAdapter(stoneAdapter);
            updateStoneSpinner.setOnItemSelectedListener(this);

            if(stone1.equals("Add Stone Materials")){
                updateStoneSpinner.setSelection(0);
            }
            else if (stone1.equals("Granite")){
                updateStoneSpinner.setSelection(1);
            }
            else if (stone1.equals("Limestone")){
                updateStoneSpinner.setSelection(2);
            }
            else if (stone1.equals("SandStone")){
                updateStoneSpinner.setSelection(3);
            }
            else if (stone1.equals("Marble")){
                updateStoneSpinner.setSelection(4);
            }
            else if (stone1.equals("Slate")){
                updateStoneSpinner.setSelection(5);
            }


            updateLaminateSpinner = findViewById(R.id.eu_laminate);
            ArrayAdapter<CharSequence> laminateAdapter = ArrayAdapter.createFromResource(this, R.array.update_laminatestr, android.R.layout.simple_spinner_item);
            laminateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            updateLaminateSpinner.setAdapter(laminateAdapter);
            updateLaminateSpinner.setOnItemSelectedListener(this);

            if(laminate1.equals("Add Laminate Type")){
                updateLaminateSpinner.setSelection(0);
            }
            else if (laminate1.equals("Water Resistant")){
                updateLaminateSpinner.setSelection(1);
            }
            else if (laminate1.equals("Non-Water Resistant")){
                updateLaminateSpinner.setSelection(2);
            }

            updateVinylSpinner = findViewById(R.id.eu_vinyl);
            ArrayAdapter<CharSequence> vinylAdapter = ArrayAdapter.createFromResource(this, R.array.update_vinylstr, android.R.layout.simple_spinner_item);
            vinylAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            updateVinylSpinner.setAdapter(vinylAdapter);
            updateVinylSpinner.setOnItemSelectedListener(this);

            if(vinyl1.equals("Add Vinyl Type")){
                updateVinylSpinner.setSelection(0);
            }
            else if (vinyl1.equals("Waterproof")){
                updateVinylSpinner.setSelection(1);
            }
            else if (vinyl1.equals("Non-Waterproof")){
                updateVinylSpinner.setSelection(2);
            }

            updateTileSpinner = findViewById(R.id.eu_tile);
            ArrayAdapter<CharSequence> tileAdapter = ArrayAdapter.createFromResource(this, R.array.update_tilestr, android.R.layout.simple_spinner_item);
            tileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            updateTileSpinner.setAdapter(tileAdapter);
            updateTileSpinner.setOnItemSelectedListener(this);

            if(tile1.equals("Add Tile Material")){
                updateTileSpinner.setSelection(0);
            }
            else if (tile1.equals("Ceramic")){
                updateTileSpinner.setSelection(1);
            }
            else if (tile1.equals("Porcelain")){
                updateTileSpinner.setSelection(2);
            }
            else if (tile1.equals("Glass")){
                updateTileSpinner.setSelection(3);
            }
            else if (tile1.equals("Marble")){
                updateTileSpinner.setSelection(4);
            }
            else if (tile1.equals("Granite")){
                updateTileSpinner.setSelection(5);
            }
            u_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //grabs the current selection of user input and set as strings
                    p_id = findViewById(R.id.eu_productid);
                    p_name = findViewById(R.id.eu_name);
                    p_brand = findViewById(R.id.eu_brand);
                    p_color = findViewById(R.id.eu_color);
                    p_size = findViewById(R.id.eu_sqft);
                    p_price = findViewById(R.id.eu_price);
                    p_qty = findViewById(R.id.eu_qty);
                    String p_id_str = p_id.getText().toString();
                    String p_name_str = p_name.getText().toString();
                    String p_color_str = p_color.getText().toString();
                    String p_size_str = p_size.getText().toString();
                    String p_price_str = p_price.getText().toString();
                    String p_brand_str = p_brand.getText().toString();
                    String p_qty_str = p_qty.getText().toString();

                    //setting these flags to false by default
                    lam = false;
                    stone = false;
                    vinyl = false;
                    tile = false;
                    wood = false;

                    //getting strings from the spinners
                    String p_store_str = updateStoreSpinner.getSelectedItem().toString();
                    String p_floor_str = updatefloorSpinner.getSelectedItem().toString();
                    String p_wood1_str = updateWood1Spinner.getSelectedItem().toString();
                    String p_wood2_str = updateWood2Spinner.getSelectedItem().toString();
                    String p_stone_str = updateStoneSpinner.getSelectedItem().toString();
                    String p_laminate_str = updateLaminateSpinner.getSelectedItem().toString();
                    String p_vinyl_str = updateVinylSpinner.getSelectedItem().toString();
                    String p_tile_str = updateTileSpinner.getSelectedItem().toString();

                    /*checking if fields are valid. if they are valid then their respective boolean value
                     * will be set to true which will allow edit product to DB to run*/
                    if(p_id_str.length() == 0){
                        b_id = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Product ID!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        b_id = true;
                    }
                    if(p_name_str.length() == 0){
                        b_name = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Product Name!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        b_name = true;
                    }
                    if(p_store_str.equals("Add Store")){
                        b_store = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Store!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        b_store = true;
                    }
                    if(p_brand_str.length() == 0){
                        b_brand = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Brand!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        b_brand = true;
                    }
                    if(p_color_str.length() == 0){
                        b_color = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Color!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        b_color = true;
                    }
                    if(p_size_str.length() == 0){
                        b_size = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Size!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        b_size = true;
                    }
                    if(p_qty_str.length() == 0){
                        b_qty = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Quantity!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        b_qty = true;
                    }
                    if(p_price_str.length() == 0){
                        b_price = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Price!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        b_price = true;
                    }
                    if(p_floor_str.equals("Add Floor")){
                        floor = false;
                        Toast.makeText(editUpdateActivity.this, "Enter Floor Type!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        floor = true;
                    }
                    if(p_floor_str.equals("Laminate")){
                        if(p_laminate_str.equals("Add Laminate Type")){
                            Toast.makeText(editUpdateActivity.this, "Enter Laminate Type!", Toast.LENGTH_SHORT).show();
                            lam = false;
                        }
                        else {
                            lam = true;
                        }
                    }
                    if(p_floor_str.equals("Stone")){
                        if(p_stone_str.equals("Add Stone Materials")){
                            Toast.makeText(editUpdateActivity.this, "Enter Stone Type!", Toast.LENGTH_SHORT).show();
                            stone = false;
                        }
                        else{
                            stone = true;
                        }
                    }
                    if(p_floor_str.equals("Tile")){
                        if(p_tile_str.equals("Add Tile Material")){
                            Toast.makeText(editUpdateActivity.this, "Enter Tile Type!", Toast.LENGTH_SHORT).show();
                            tile = false;
                        }
                        else {
                            tile = true;
                        }
                    }
                    if(p_floor_str.equals("Vinyl")){
                        if(p_vinyl_str.equals("Add Vinyl Type")){
                            Toast.makeText(editUpdateActivity.this, "Enter Vinyl Type!", Toast.LENGTH_SHORT).show();
                            vinyl = false;
                        }
                        else {
                            vinyl = true;
                        }
                    }
                    if(p_floor_str.equals("Wood")){
                        if(p_wood1_str.equals("Add Wood Types") || p_wood2_str.equals("Add Wood Species")){
                            Toast.makeText(editUpdateActivity.this, "Enter Wood Type!", Toast.LENGTH_SHORT).show();
                            wood = false;
                        }
                        else {
                            wood = true;
                        }
                    }


                    //checking if all inputs are valid
                    if(b_id && b_name && b_store && b_brand && b_color && b_size && b_qty && b_price && floor &&
                            (lam || stone || tile || vinyl || wood)) {
                        /*pass args to update product in dbo, will get long result from update product function
                        to determine if editing was successful*/
                        long ch = db.updateProduct(p_id_str,
                                p_name_str,
                                p_color_str,
                                p_size_str,
                                p_price_str,
                                p_brand_str,
                                p_floor_str,
                                p_store_str,
                                p_qty_str,
                                p_wood1_str,
                                p_wood2_str,
                                p_stone_str,
                                p_laminate_str,
                                p_vinyl_str,
                                p_tile_str,
                                Integer.parseInt(key));
                        if (ch != -1) {
                            Toast.makeText(editUpdateActivity.this, "Floor Updated", Toast.LENGTH_SHORT).show();
                            Intent openEmployeeMenuIntent = new Intent(editUpdateActivity.this, empMenuActivity.class);
                            startActivity(openEmployeeMenuIntent);
                        } else {
                            Toast.makeText(editUpdateActivity.this, "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        /* This takes care of the spinners and resets other spinners to default value if one spinner
         * is selected. It also controls visibility of sub types so only the correct subtype will
         * show up*/
        if(text.equals("Wood")){
            updateLaminateSpinner.setVisibility(View.INVISIBLE);
            updateLaminateSpinner.setSelection(0);
            updateStoneSpinner.setVisibility(View.INVISIBLE);
            updateStoneSpinner.setSelection(0);
            updateVinylSpinner.setVisibility(View.INVISIBLE);
            updateVinylSpinner.setSelection(0);
            updateTileSpinner.setVisibility(View.INVISIBLE);
            updateTileSpinner.setSelection(0);
            updateWood1Spinner.setVisibility(View.VISIBLE);
            updateWood2Spinner.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Stone")){
            updateWood1Spinner.setVisibility(View.INVISIBLE);
            updateWood1Spinner.setSelection(0);
            updateWood2Spinner.setVisibility(View.INVISIBLE);
            updateWood2Spinner.setSelection(0);
            updateLaminateSpinner.setVisibility(View.INVISIBLE);
            updateLaminateSpinner.setSelection(0);
            updateVinylSpinner.setVisibility(View.INVISIBLE);
            updateVinylSpinner.setSelection(0);
            updateTileSpinner.setVisibility(View.INVISIBLE);
            updateTileSpinner.setSelection(0);
            updateStoneSpinner.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Laminate")){
            updateWood1Spinner.setVisibility(View.INVISIBLE);
            updateWood1Spinner.setSelection(0);
            updateWood2Spinner.setVisibility(View.INVISIBLE);
            updateWood2Spinner.setSelection(0);
            updateStoneSpinner.setVisibility(View.INVISIBLE);
            updateStoneSpinner.setSelection(0);
            updateVinylSpinner.setVisibility(View.INVISIBLE);
            updateVinylSpinner.setSelection(0);
            updateTileSpinner.setVisibility(View.INVISIBLE);
            updateTileSpinner.setSelection(0);
            updateLaminateSpinner.setVisibility(View.VISIBLE);

        }
        else if(text.equals("Vinyl")){
            updateWood1Spinner.setVisibility(View.INVISIBLE);
            updateWood1Spinner.setSelection(0);
            updateWood2Spinner.setVisibility(View.INVISIBLE);
            updateWood2Spinner.setSelection(0);
            updateStoneSpinner.setVisibility(View.INVISIBLE);
            updateStoneSpinner.setSelection(0);
            updateLaminateSpinner.setVisibility(View.INVISIBLE);
            updateLaminateSpinner.setSelection(0);
            updateTileSpinner.setVisibility(View.INVISIBLE);
            updateTileSpinner.setSelection(0);
            updateVinylSpinner.setVisibility(View.VISIBLE);

        }
        else if(text.equals("Tile")){
            updateWood1Spinner.setVisibility(View.INVISIBLE);
            updateWood1Spinner.setSelection(0);
            updateWood2Spinner.setVisibility(View.INVISIBLE);
            updateWood2Spinner.setSelection(0);
            updateStoneSpinner.setVisibility(View.INVISIBLE);
            updateStoneSpinner.setSelection(0);
            updateLaminateSpinner.setVisibility(View.INVISIBLE);
            updateLaminateSpinner.setSelection(0);
            updateVinylSpinner.setVisibility(View.INVISIBLE);
            updateVinylSpinner.setSelection(0);
            updateTileSpinner.setVisibility(View.VISIBLE);

        }
        else if(text.equals("All Floors")){
            updateWood1Spinner.setVisibility(View.INVISIBLE);
            updateWood1Spinner.setSelection(0);
            updateWood2Spinner.setVisibility(View.INVISIBLE);
            updateWood2Spinner.setSelection(0);
            updateStoneSpinner.setVisibility(View.INVISIBLE);
            updateStoneSpinner.setSelection(0);
            updateLaminateSpinner.setVisibility(View.INVISIBLE);
            updateLaminateSpinner.setSelection(0);
            updateVinylSpinner.setVisibility(View.INVISIBLE);
            updateVinylSpinner.setSelection(0);
            updateTileSpinner.setVisibility(View.INVISIBLE);
            updateTileSpinner.setSelection(0);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}