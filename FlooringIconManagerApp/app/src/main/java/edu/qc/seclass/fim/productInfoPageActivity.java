package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class productInfoPageActivity extends AppCompatActivity {
    TextView v_name, v_id, v_store, v_brand, v_color, v_size, v_type,
            v_price, v_qty, v_wood1, v_wood2, v_stone, v_lam, v_vinyl, v_tile, v_sqft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info_page);
        v_name = findViewById(R.id.name_view);
        v_id = findViewById(R.id.id_view);
        v_store = findViewById(R.id.store_view);
        v_brand = findViewById(R.id.brand_view);
        v_color = findViewById(R.id.color_view);
        v_size = findViewById(R.id.size_view);
        v_type = findViewById(R.id.floor_view);
        v_price = findViewById(R.id.price_view);
        v_qty = findViewById(R.id.qty_view);
        v_wood1 = findViewById(R.id.wood1_view);
        v_wood2 = findViewById(R.id.wood2_view);
        v_stone = findViewById(R.id.stone_view);
        v_lam = findViewById(R.id.lam_view);
        v_vinyl = findViewById(R.id.vinyl_view);
        v_tile = findViewById(R.id.tile_view);
        v_sqft = findViewById(R.id.sqft_view);



        if (getIntent().getExtras() != null) {
            String product_sqft = getIntent().getStringExtra("product_sqft");
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

            v_name.setText(name);
            v_id.setText(id);
            v_store.setText(store);
            v_brand.setText(brand);
            v_color.setText(color);
            v_size.setText(size);
            v_type.setText(type);
            v_price.setText(price);
            v_qty.setText(qty);
            v_wood1.setText(wood1);
            v_wood2.setText(wood2);
            v_stone.setText(stone1);
            v_lam.setText(laminate1);
            v_tile.setText(tile1);
            v_vinyl.setText(vinyl1);
            v_sqft.setText(product_sqft);

            String floorstr = v_type.getText().toString();
            if(floorstr.equals("Wood")){
                v_lam.setVisibility(View.INVISIBLE);
                v_stone.setVisibility(View.INVISIBLE);
                v_vinyl.setVisibility(View.INVISIBLE);
                v_tile.setVisibility(View.INVISIBLE);
                v_wood1.setVisibility(View.VISIBLE);
                v_wood2.setVisibility(View.VISIBLE);
            }
            else if(floorstr.equals("Laminate")){
                v_lam.setVisibility(View.VISIBLE);
                v_stone.setVisibility(View.INVISIBLE);
                v_vinyl.setVisibility(View.INVISIBLE);
                v_tile.setVisibility(View.INVISIBLE);
                v_wood1.setVisibility(View.INVISIBLE);
                v_wood2.setVisibility(View.INVISIBLE);
            }
            else if(floorstr.equals("Stone")){
                v_lam.setVisibility(View.INVISIBLE);
                v_stone.setVisibility(View.VISIBLE);
                v_vinyl.setVisibility(View.INVISIBLE);
                v_tile.setVisibility(View.INVISIBLE);
                v_wood1.setVisibility(View.INVISIBLE);
                v_wood2.setVisibility(View.INVISIBLE);
            }
            else if(floorstr.equals("Vinyl")){
                v_lam.setVisibility(View.INVISIBLE);
                v_stone.setVisibility(View.INVISIBLE);
                v_vinyl.setVisibility(View.VISIBLE);
                v_tile.setVisibility(View.INVISIBLE);
                v_wood1.setVisibility(View.INVISIBLE);
                v_wood2.setVisibility(View.INVISIBLE);
            }
            else if(floorstr.equals("Tile")){
                v_lam.setVisibility(View.INVISIBLE);
                v_stone.setVisibility(View.INVISIBLE);
                v_vinyl.setVisibility(View.INVISIBLE);
                v_tile.setVisibility(View.VISIBLE);
                v_wood1.setVisibility(View.INVISIBLE);
                v_wood2.setVisibility(View.INVISIBLE);
            }
        }
    }
}