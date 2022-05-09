package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton empLogInButton;
    private Button guestLogInButton;
    private ImageButton wood;
    private ImageButton vinyl;
    private ImageButton stone;
    private ImageButton tile;
    private ImageButton laminate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empLogInButton = (ImageButton) findViewById(R.id.empLogInButton);
        empLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openempLogInActivity();
            }
        });

        guestLogInButton = (Button) findViewById(R.id.guestLogButton);
        guestLogInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openGuestLogInActivity();
            }
        });

        String storeInput = "All Store Locations";
        String woodspin1 = "All Wood Types";
        String woodspin2 = "All Wood Species";
        String stonespin = "All Stone Materials";
        String laminatespin = "All Laminate";
        String vinylspin = "All Vinyl";
        String tilespin = "All Tile Material";

        wood = (ImageButton) findViewById(R.id.woodButton);
        wood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openSearchResultActivity("", storeInput, "Wood", woodspin1, woodspin2, stonespin, laminatespin, vinylspin, tilespin);
            }
        });

        vinyl = (ImageButton) findViewById(R.id.vinylButton);
        vinyl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openSearchResultActivity("", storeInput, "Vinyl", woodspin1, woodspin2, stonespin, laminatespin, vinylspin, tilespin);
            }
        });

        stone = (ImageButton) findViewById(R.id.stoneButton);
        stone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openSearchResultActivity("", storeInput, "Stone", woodspin1, woodspin2, stonespin, laminatespin, vinylspin, tilespin);
            }
        });
        tile = (ImageButton) findViewById(R.id.tileButton);
        tile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openSearchResultActivity("", storeInput, "Tile", woodspin1, woodspin2, stonespin, laminatespin, vinylspin, tilespin);
            }
        });
        laminate = (ImageButton) findViewById(R.id.laminateButton);
        laminate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openSearchResultActivity("", storeInput, "Laminate", woodspin1, woodspin2, stonespin, laminatespin, vinylspin, tilespin);
            }
        });
    }
    public void openempLogInActivity(){
        Intent empLogInintent = new Intent(this, empLogInActivity.class);
        startActivity(empLogInintent);
    }

    public void openGuestLogInActivity(){
        Intent guestLogInIntent = new Intent (this, searchActivity.class);
        startActivity(guestLogInIntent);
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

        //searchPhrase = productNameInput.getText().toString();
        startActivity(searchIntent);
    }
}