package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class searchEditResultActivity extends AppCompatActivity implements editRecycleAdapter.listClickListener{

    //private Button result;
    private DBO searchDBO;
    RecyclerView rw;
    editRecycleAdapter ra;
    ArrayList<String> product_id, product_name, product_brand,
            product_qty, product_store, product_sqft,
            product_color, product_price, product_type, product_size;
    ArrayList<String> p_wood1, p_wood2, p_stone, p_tile, p_laminate, p_vinyl, p_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_edit_result);

        if(getIntent().getExtras() != null) {
            String name = getIntent().getStringExtra("name");
            String store = getIntent().getStringExtra("store");
            String floor = getIntent().getStringExtra("floor");
            String wood1 = getIntent().getStringExtra("wood1");
            String wood2 = getIntent().getStringExtra("wood2");
            String stone1 = getIntent().getStringExtra("stone1");
            String laminate1 = getIntent().getStringExtra("laminate1");
            String vinyl1 = getIntent().getStringExtra("vinyl1");
            String tile1 = getIntent().getStringExtra("tile1");


            rw = findViewById(R.id.recyclerView2);
            searchDBO = new DBO(searchEditResultActivity.this);
            product_id = new ArrayList<>();
            product_name = new ArrayList<>();
            product_brand = new ArrayList<>();
            product_qty = new ArrayList<>();
            product_store = new ArrayList<>();
            product_sqft = new ArrayList<>();
            product_color = new ArrayList<>();
            product_price = new ArrayList<>();
            product_type = new ArrayList<>();
            product_size = new ArrayList<>();
            p_wood1 = new ArrayList<>();
            p_wood2 = new ArrayList<>();
            p_stone = new ArrayList<>();
            p_tile = new ArrayList<>();
            p_laminate = new ArrayList<>();
            p_vinyl = new ArrayList<>();
            p_key = new ArrayList<>();

            //getProducts(name);
            Boolean anyTrueCat = (!wood1.equals("All Wood Types") ||
                    !wood2.equals("All Wood Species") ||
                    !stone1.equals("All Stone Materials") ||
                    !laminate1.equals("All Laminate") ||
                    !vinyl1.equals("All Vinyl") ||
                    !tile1.equals("All Tile Material"));

            //String flrStr = get_Col(floor, wood1);


            //Query the name, floor, the category and the store but with wood filled out
            if(!name.isEmpty() &&
                    !store.equals("All Store Locations") &&
                    !floor.equals("All Floors") &&
                    !wood1.equals("All Wood Types") &&
                    !wood2.equals("All Wood Species") &&
                    stone1.equals("All Stone Materials") &&
                    laminate1.equals("All Laminate") &&
                    vinyl1.equals("All Vinyl") &&
                    tile1.equals("All Tile Material")){
                getProducts(name, floor, "wood_type", wood1,"wood_species", wood2, store, 15);
            }

            //Query for name, floor the category and the store
            else if(!name.isEmpty() &&
                    !store.equals("All Store Locations") &&
                    !floor.equals("All Floors") &&
                    anyTrueCat) {
                if(floor.equals("Wood")){
                    if(!wood1.equals("All Wood Types")){
                        getProducts(name, floor, "wood_type", wood1, store,null, null,14);

                    }
                    else{
                        getProducts(name, floor,"wood_species", wood2,store, null, null,14);

                    }
                }
                else if(floor.equals("Stone")){
                    getProducts(name,floor, "stone_material", stone1,store, null ,null,14);
                }
                else if(floor.equals("Laminate")){
                    getProducts(name,floor, "water_resistant", laminate1,store,null,null,14);
                }
                else if(floor.equals("Vinyl")){
                    getProducts(name,floor, "water_proof", vinyl1, store, null,null,14);
                }
                else if(floor.equals("Tile")){
                    getProducts(name,floor, "tile_material", tile1,store, null, null,14);
                }

            }

            //Query the floor, the category and the store but with wood filled out
            else if(name.isEmpty() &&
                    !store.equals("All Store Locations") &&
                    !floor.equals("All Floors") &&
                    !wood1.equals("All Wood Types") &&
                    !wood2.equals("All Wood Species") &&
                    stone1.equals("All Stone Materials") &&
                    laminate1.equals("All Laminate") &&
                    vinyl1.equals("All Vinyl") &&
                    tile1.equals("All Tile Material")){
                getProducts(floor, "wood_type", wood1,"wood_species", wood2, store, null, 13);
            }

            //Query for floor the category and the store
            else if(name.isEmpty() &&
                    !store.equals("All Store Locations") &&
                    !floor.equals("All Floors") &&
                    anyTrueCat) {
                if(floor.equals("Wood")){
                    if(!wood1.equals("All Wood Types")){
                        getProducts(floor, "wood_type", wood1,store, null, null,null,12);

                    }
                    else{
                        getProducts(floor, "wood_species", wood2,store,null, null, null,12);

                    }
                }
                else if(floor.equals("Stone")){
                    getProducts(floor, "stone_material", stone1,store, null ,null,null,12);
                }
                else if(floor.equals("Laminate")){
                    getProducts(floor, "water_resistant", laminate1,store,null,null,null,12);
                }
                else if(floor.equals("Vinyl")){
                    getProducts(floor, "water_proof", vinyl1,store, null,null,null,12);
                }
                else if(floor.equals("Tile")){
                    getProducts(floor, "tile_material", tile1,store, null, null,null,12);
                }

            }

            //Query for name, floor and category type with both wood types
            else if(!name.isEmpty() &&
                    store.equals("All Store Locations") &&
                    !floor.equals("All Floors") &&
                    !wood1.equals("All Wood Types") &&
                    !wood2.equals("All Wood Species") &&
                    stone1.equals("All Stone Materials") &&
                    laminate1.equals("All Laminate") &&
                    vinyl1.equals("All Vinyl") &&
                    tile1.equals("All Tile Material")){
                getProducts(name, floor, "wood_type", wood1,"wood_species", wood2,null,10);
            }

            //Query for name, floor and category
            else if(!name.isEmpty() &&
                    store.equals("All Store Locations") &&
                    !floor.equals("All Floors") &&
                    anyTrueCat) {
                if(floor.equals("Wood")){
                    if(!wood1.equals("All Wood Types")){
                        getProducts(name, floor, "wood_type", wood1, null, null,null,9);

                    }
                    else{
                        getProducts(name, floor, "wood_species", wood2, null, null,null,9);

                    }
                }
                else if(floor.equals("Stone")){
                    getProducts(name, floor,"stone_material", stone1, null,null,null,9);
                }
                else if(floor.equals("Laminate")){
                    getProducts(name, floor, "water_resistant", laminate1,null,null,null, 9);
                }
                else if(floor.equals("Vinyl")){
                    getProducts(name, floor,"water_proof", vinyl1, null,null,null,9);
                }
                else if(floor.equals("Tile")){
                    getProducts(name, floor,"tile_material", tile1, null, null,null,9);
                }

            }

            //Query for name, floor and store
            else if (!name.isEmpty() && !store.equals("All Store Locations") && !floor.equals("All Floors")){
                getProducts(name, floor, store, null, null, null, null,11);
            }

            //Query for floor and category type with both wood types
            else if(name.isEmpty() &&
                    store.equals("All Store Locations") &&
                    !floor.equals("All Floors") &&
                    !wood1.equals("All Wood Types") &&
                    !wood2.equals("All Wood Species") &&
                    stone1.equals("All Stone Materials") &&
                    laminate1.equals("All Laminate") &&
                    vinyl1.equals("All Vinyl") &&
                    tile1.equals("All Tile Material")){
                getProducts(floor, "wood_type", wood1,"wood_species", wood2, null,null,8);
            }

            //Query for floor and category type
            else if(name.isEmpty() &&
                    store.equals("All Store Locations") &&
                    !floor.equals("All Floors") &&
                    anyTrueCat){
                if(floor.equals("Wood")){
                    if(!wood1.equals("All Wood Types")){
                        getProducts(floor, "wood_type", wood1,null, null, null,null,7);

                    }
                    else{
                        getProducts(floor, "wood_species", wood2,null, null, null,null,7);

                    }
                }
                else if(floor.equals("Stone")){
                    getProducts(floor, "stone_material", stone1,null, null,  null,null,7);
                }
                else if(floor.equals("Laminate")){
                    getProducts(floor, "water_resistant", laminate1,null,null, null,null,7);
                }
                else if(floor.equals("Vinyl")){
                    getProducts(floor, "water_proof", vinyl1,null, null, null,null,7);
                }
                else if(floor.equals("Tile")){
                    getProducts(floor, "tile_material", tile1,null, null, null,null,7);
                }
            }

            //Query for Store and floor
            else if(name.isEmpty() && !store.equals("All Store Locations") && !floor.equals("All Floors")){
                getProducts(floor, store, null,null, null, null,null,6);
            }

            //Query for name and store
            else if(!name.isEmpty() && !store.equals("All Store Locations") && floor.equals("All Floors")){
                getProducts(name, store, null,null, null, null,null,5);
            }

            //Query for name and floor
            else if(!name.isEmpty() && store.equals("All Store Locations") && !floor.equals("All Floors")){
                getProducts(name, floor,null, null, null, null,null,4);
            }

            //Query for store
            else if(name.isEmpty() && !store.equals("All Store Locations") && floor.equals("All Floors")){
                getProducts(store, null, null, null, null,  null,null,3);
            }

            //Query for floor
            else if(name.isEmpty() && store.equals("All Store Locations") && !floor.equals("All Floors")){
                getProducts(floor, null, null, null, null, null,null,2);
            }

            //Query for name
            else if(!name.isEmpty() && store.equals("All Store Locations") && floor.equals("All Floors")){
                getProducts(name, null, null, null, null,  null,null,1);
            }




            ra = new editRecycleAdapter(searchEditResultActivity.this, product_name, product_brand,
                    product_qty, product_sqft, product_store, product_color, product_price,
                    product_type, product_size, this);
            rw.setAdapter(ra);
            rw.setLayoutManager(new LinearLayoutManager(searchEditResultActivity.this));
        }



        //result = findViewById(R.id.product1);
        /*result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProductPage();
            }
        });*/
    }

    /*private void openProductPage() {
        Intent openProdPageIntent = new Intent(this, productInfoPageActivity.class);
        startActivity(openProdPageIntent);
    }*/
    //getting products with 1 arg
    void getProducts(String searchPhrase1, String searchPhrase2, String searchPhrase3, String searchPhrase4, String searchPhrase5, String searchPhrase6, String searchPhrase7, int searchFlag){
        Cursor c = null;
        if(searchFlag == 1){
            c = searchDBO.searchByName(searchPhrase1);
        }
        else if (searchFlag == 2){
            c = searchDBO.searchByType(searchPhrase1);
        }
        else if (searchFlag == 3){
            c = searchDBO.searchByStore(Integer.parseInt(searchPhrase1));
        }
        else if (searchFlag == 4){
            c = searchDBO.searchNameType(searchPhrase1, searchPhrase2);
        }
        else if (searchFlag == 5){
            c = searchDBO.searchNameStore(searchPhrase1, Integer.parseInt(searchPhrase2));
        }
        else if (searchFlag == 6){
            c = searchDBO.searchTypeStore(searchPhrase1, Integer.parseInt(searchPhrase2));
        }
        else if (searchFlag == 7){
            c = searchDBO.searchTypeCategory(searchPhrase1, searchPhrase2, searchPhrase3);
        }
        else if (searchFlag == 8){
            c = searchDBO.searchTypeCategoryWood2(searchPhrase1, searchPhrase2, searchPhrase3, searchPhrase4, searchPhrase5);
        }
        else if (searchFlag == 9){
            c = searchDBO.searchNameTypeCategory(searchPhrase1, searchPhrase2, searchPhrase3, searchPhrase4);
        }
        else if (searchFlag == 10){
            c = searchDBO.searchNameTypeCategoryWood(searchPhrase1, searchPhrase2, searchPhrase3, searchPhrase4, searchPhrase5, searchPhrase6);
        }
        else if (searchFlag == 11){
            c = searchDBO.searchNameTypeStore(searchPhrase1, searchPhrase2,  Integer.parseInt(searchPhrase3));
        }
        else if (searchFlag == 12){
            c = searchDBO.searchTypeCategoryStore(searchPhrase1, searchPhrase2, searchPhrase3, Integer.parseInt(searchPhrase4));
        }
        else if (searchFlag == 13){
            c = searchDBO.searchTypeCategoryStoreWood(searchPhrase1, searchPhrase2, searchPhrase3, searchPhrase4, searchPhrase5, Integer.parseInt(searchPhrase4));
        }
        else if (searchFlag == 14){
            c = searchDBO.searchNameTypeCategoryStore(searchPhrase1, searchPhrase2, searchPhrase3, searchPhrase4, Integer.parseInt(searchPhrase5));
        }
        else if (searchFlag == 15){
            c = searchDBO.searchNameTypeCategoryStoreWood(searchPhrase1, searchPhrase2, searchPhrase3, searchPhrase4, searchPhrase5, searchPhrase6, Integer.parseInt(searchPhrase7));
        }

        if(c.getCount() != 0){
            while(c.moveToNext()){
                p_key.add(c.getString(0));
                product_id.add(c.getString(1));
                product_name.add(c.getString(2));
                product_brand.add(c.getString(6));
                product_qty.add(c.getString(9));
                product_store.add(c.getString(8));
                product_sqft.add(Double.toString(Double.valueOf(c.getString(4)) * Double.valueOf(c.getString(9))));//change this later
                product_color.add(c.getString(3));
                product_price.add(c.getString(5));
                product_type.add(c.getString(7));
                product_size.add(c.getString(4));
                p_wood1.add(c.getString(10));
                p_wood2.add(c.getString(11));
                p_stone.add(c.getString(12));
                p_laminate.add(c.getString(13));
                p_vinyl.add(c.getString(14));
                p_tile.add(c.getString(15));

            }
        }
        else{
            Toast.makeText(this, "No items found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListClick(int pos) {
        product_name.get(pos);
        Intent i = new Intent(this, editUpdateActivity.class);
        i.putExtra("key", String.valueOf(p_key.get(pos)));
        i.putExtra("id", String.valueOf(product_id.get(pos)));
        i.putExtra("name", String.valueOf(product_name.get(pos)));
        i.putExtra("store", String.valueOf(product_store.get(pos)));
        i.putExtra("brand", String.valueOf(product_brand.get(pos)));
        i.putExtra("color", String.valueOf(product_color.get(pos)));
        i.putExtra("size", String.valueOf(product_size.get(pos)));
        i.putExtra("price", String.valueOf(product_price.get(pos)));
        i.putExtra("type", String.valueOf(product_type.get(pos)));
        i.putExtra("qty", String.valueOf(product_qty.get(pos)));
        i.putExtra("wood1", String.valueOf(p_wood1.get(pos)));
        i.putExtra("wood2", String.valueOf(p_wood2.get(pos)));
        i.putExtra("stone1", String.valueOf(p_stone.get(pos)));
        i.putExtra("laminate1", String.valueOf(p_laminate.get(pos)));
        i.putExtra("vinyl1", String.valueOf(p_vinyl.get(pos)));
        i.putExtra("tile1", String.valueOf(p_tile.get(pos)));
        startActivity(i);

    }

    /*private String get_Col(String floor_type, String wood_chk){
        if(floor_type.equals("Wood")){
            if(!wood_chk.equals("All Wood Types")){
                return "wood_type";

            }
            else{
                return "wood_species";
            }
        }
        else if(floor_type.equals("Stone")){
            return "stone_material";
        }
        else if(floor_type.equals("Laminate")){
            return "water_resistant";
        }
        else if(floor_type.equals("Vinyl")){
            return "water_proof";
        }
        else if(floor_type.equals("Tile")){
            return "tile_material";
        }
        return "";
    }*/

}
