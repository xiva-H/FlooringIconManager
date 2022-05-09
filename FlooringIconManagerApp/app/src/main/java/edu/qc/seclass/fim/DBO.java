package edu.qc.seclass.fim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBO extends SQLiteOpenHelper {


    public DBO(@Nullable Context context) {
        super(context, "fimDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //creating the employee table to check credentials on first creation
        sqLiteDatabase.execSQL("CREATE TABLE emp_credentials(emp_key INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                            "emp_id TEXT, " +
                                                            "emp_pw TEXT)");

        //adds admin column to the employee table
        ContentValues contentValues = new ContentValues();
        contentValues.put("emp_id", "admin");
        contentValues.put("emp_pw", "admin");
        sqLiteDatabase.insert("emp_credentials", null, contentValues);

        //creating the stock table
        sqLiteDatabase.execSQL("CREATE TABLE stock_table(stock_key INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "product_id TEXT, " +
                "product_name TEXT, " +
                "color TEXT, " +
                "size_sq_ft NUMERIC(10,2), " +
                "price NUMERIC(10,2), " +
                "brand TEXT, " +
                "type TEXT, " +
                "store_id INT, " +
                "qty INT, " +
                "wood_type TEXT, " +
                "wood_species TEXT, " +
                "stone_material TEXT, " +
                "water_resistant TEXT," +
                "water_proof TEXT, " +
                "tile_material TEXT)");

        //adding sample data, will add more later
        ContentValues cv2 = new ContentValues();
        cv2.put("product_id", 101);
        cv2.put("product_name", "Graystone Tavertine Porcelain Tile");
        cv2.put("color", "Grey");
        cv2.put("size_sq_ft", 11.52);
        cv2.put("price", 16.01);
        cv2.put("brand", "Avella");
        cv2.put("type", "Tile");
        cv2.put("store_id", 1001);
        cv2.put("qty", 254);
        cv2.put("tile_material", "Porcelain");
        sqLiteDatabase.insert("stock_table", null, cv2);

        ContentValues cv3 = new ContentValues();
        cv3.put("product_id", 102);
        cv3.put("product_name", "Bianco Venato Marble");
        cv3.put("color", "White");
        cv3.put("size_sq_ft", 5);
        cv3.put("price", 69.99);
        cv3.put("brand", "Tilefornia");
        cv3.put("type", "Stone");
        cv3.put("store_id", 1002);
        cv3.put("qty", 77);
        cv3.put("stone_material", "Marble");
        sqLiteDatabase.insert("stock_table", null, cv3);

        ContentValues cv4 = new ContentValues();
        cv4.put("product_id", 103);
        cv4.put("product_name", "Duravana Hybrid Resilient Flooring");
        cv4.put("color", "Blonde");
        cv4.put("size_sq_ft", 23.92);
        cv4.put("price", 81.09);
        cv4.put("brand", "Duravana");
        cv4.put("type", "Wood");
        cv4.put("store_id", 1003);
        cv4.put("qty", 57);
        cv4.put("wood_type", "Hybrid");
        cv4.put("wood_species", "Oak");
        sqLiteDatabase.insert("stock_table", null, cv4);

        ContentValues cv5 = new ContentValues();
        cv5.put("product_id", 104);
        cv5.put("product_name", "Mont-Blanc Pine WaterResist Rigid Plank Flooring");
        cv5.put("color", "White");
        cv5.put("size_sq_ft", 28.52);
        cv5.put("price", 65.03);
        cv5.put("brand", "CoreLuxe");
        cv5.put("type", "Laminate");
        cv5.put("store_id", 1003);
        cv5.put("qty", 94);
        cv5.put("water_resistant", "Water Resistant");
        sqLiteDatabase.insert("stock_table", null, cv5);

        ContentValues cv6 = new ContentValues();
        cv6.put("product_id", 105);
        cv6.put("product_name", "Luminescent Sky Marble Luxury Vinyl Tile");
        cv6.put("color", "White");
        cv6.put("size_sq_ft", 20.69);
        cv6.put("price", 72.18);
        cv6.put("brand", "Lifeproof");
        cv6.put("type", "Vinyl");
        cv6.put("store_id", 1001);
        cv6.put("qty", 65);
        cv6.put("water_proof", "Waterproof");
        sqLiteDatabase.insert("stock_table", null, cv6);



    }

    //method for versioning
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS test_tab");
        onCreate(sqLiteDatabase);

    }

    //method to check if the id and password matches any rows in the employee table
    public boolean checkEmpCredentials(String id, String pw) {
        SQLiteDatabase readDB = this.getReadableDatabase();
        Cursor c = readDB.rawQuery("SELECT emp_id " +
                        "FROM emp_credentials " +
                        "WHERE emp_id = ? AND emp_pw = ?",
                new String[]{id, pw}, null);
        int x = c.getCount();
        c.close();
        return x != 0;
    }

    /*below are all the search functions required for the search activity, a cursor will be returned
    for you to pull data out of and display. Refer to diagram to link up text boxes with search methods*/

    //flag type 1 used search results
    public Cursor searchByName(String searchPhrase){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        //Cursor c = readDB.rawQuery("SELECT * FROM stock_table", null);

        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                                        "WHERE product_name LIKE ? OR brand LIKE ?",
                new String[]{"%" + searchPhrase + "%", "%" + searchPhrase + "%"}, null);

        return c;
    }
    //flag type 2 used search results
    public Cursor searchByType(String searchType){

        Cursor c = searchQ1Internal("type", String.valueOf(searchType));
        return c;
    }
    //flag type 3 used search results
    public Cursor searchByStore(int searchStore){

        Cursor c = searchQ1Internal("store_id", String.valueOf(searchStore));
        return c;
    }

    //2searches with 2 text boxes filled in
    //Search by Name and Type
    //flag type 4 used search results
    public Cursor searchNameType(String searchPhrase, String searchType){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE (product_name LIKE ? OR brand LIKE ?) AND type = ?",
                new String[]{"%" + searchPhrase + "%", "%" + searchPhrase + "%", searchType}, null);
        return c;
    }
    //2 searches
    //Search by Name and store
    //flag type 5 used search results
    public Cursor searchNameStore(String searchPhrase, int searchStore){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE (product_name LIKE ? OR brand LIKE ?) AND store_id = ?",
                new String[]{"%" + searchPhrase + "%", "%" + searchPhrase + "%", String.valueOf(searchStore)}, null);
        return c;
    }
    //2 searches
    //Search by Type and store
    //flag type 6 used search results
    public Cursor searchTypeStore(String searchType, int searchStore){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE type LIKE ?  AND store_id = ?",
                new String[]{searchType, String.valueOf(searchStore)}, null);
        return c;
    }
    //searches with 3 text boxes filled in
    //2 search by Type Category
    //flag type 7 used search results
    public Cursor searchTypeCategory(String searchType, String catCol, String searchCat){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE type = ? AND " + catCol + " = ?",
                new String[]{ searchType, searchCat}, null);
        return c;
    }
    //searches with 3 text boxes filled in****
    //2 search by Type Category but with wood
    //flag type 8 used search results
    public Cursor searchTypeCategoryWood2(String searchType, String catCol1, String searchCat1, String catCol2, String searchCat2){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE type = ? AND " + catCol1 + " = ? AND " + catCol2 + " = ?",
                new String[]{ searchType, searchCat1, searchCat2}, null);
        return c;
    }

    //searches with 3 text boxes filled in***
    //3 search by Name Type Category**
    //flag type 9 used search results
    public Cursor searchNameTypeCategory(String searchPhrase, String searchType, String catCol, String searchCat){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE (product_name LIKE ? OR brand LIKE ?) AND type = ? AND " + catCol + " = ?",
                new String[]{"%" + searchPhrase + "%", "%" + searchPhrase + "%", searchType, searchCat}, null);
        return c;
    }

    //searches with 3 text boxes filled in
    //3 search by Name Type Category for wood**
    //flag type 10 used search results
    public Cursor searchNameTypeCategoryWood(String searchPhrase, String searchType, String catCol1, String searchCat1, String catCol2, String searchCat2){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE (product_name LIKE ? OR brand LIKE ?) AND type = ? AND " +catCol1 + " = ? AND " + catCol2 + " = ?",
                new String[]{"%" + searchPhrase + "%", "%" + searchPhrase + "%", searchType, searchCat1, searchCat2}, null);
        return c;
    }

    //3 searches
    //Search by Name Type and Store**
    //flag type 11 used search results
    public Cursor searchNameTypeStore(String searchPhrase, String searchType, int searchStore){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE (product_name LIKE ? OR brand LIKE ?) AND type = ? AND store_id = ?",
                new String[]{"%" + searchPhrase + "%", "%" + searchPhrase + "%", searchType, String.valueOf(searchStore)}, null);
        return c;
    }

    //searches with 3 text boxes filled in
    //3 search by Type Category Store
    //flag type 12 used search results
    public Cursor searchTypeCategoryStore(String searchType, String catCol, String searchCat, int searchStore){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE type = ? AND " + catCol + " = ? AND store_id = ?",
                new String[]{searchType, searchCat, String.valueOf(searchStore)}, null);
        return c;
    }
    //searches with 3 text boxes filled in
    //3 search by Type Category Store for WOOD
    //flag type 13 used search results
    public Cursor searchTypeCategoryStoreWood(String searchType, String catCol1, String searchCat1,String catCol2, String searchCat2, int searchStore){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE type = ? AND store AND type = ? AND " +catCol1 + " = ? AND " + catCol2 + " = ? AND store_id = ?",
                new String[]{searchType, searchCat1, searchCat2, String.valueOf(searchStore)}, null);
        return c;
    }


    //searches with all text boxes filled in
    //flag type 14 used search results*
    //with wood
    public Cursor searchNameTypeCategoryStore(String searchPhrase, String searchType, String catCol, String searchCat, int searchStore){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE (product_name LIKE ? OR brand LIKE ?) AND type = ? AND " + catCol + " = ? AND store_id = ?",
                new String[]{"%" + searchPhrase + "%", "%" + searchPhrase + "%", searchType, searchCat, String.valueOf(searchStore)}, null);
        return c;
    }
    //searches with all text boxes filled in
    //flag type 15 used search results
    public Cursor searchNameTypeCategoryStoreWood(String searchPhrase, String searchType, String catCol1, String searchCat1, String catCol2, String searchCat2, int searchStore){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                        "WHERE (product_name LIKE ? OR brand LIKE ?) AND type = ? AND " +catCol1 + " = ? AND " + catCol2 + " = ? AND store_id = ?",
                new String[]{"%" + searchPhrase + "%", "%" + searchPhrase + "%", searchType, searchCat1, searchCat2, String.valueOf(searchStore)}, null);
        return c;
    }

    public long addRow(String proId,
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
                                String stone1,
                                String lam1,
                                String vinyl1,
                                String tile1){
        SQLiteDatabase writeDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id", proId);
        contentValues.put("product_name", name);
        contentValues.put("color", color);
        contentValues.put("size_sq_ft", Double.parseDouble(size));
        contentValues.put("price", Double.parseDouble(price));
        contentValues.put("brand", brand);
        contentValues.put("type", floor);
        contentValues.put("store_id", String.valueOf(store));
        contentValues.put("qty", String.valueOf(qty));
        contentValues.put("wood_type", wood1);
        contentValues.put("wood_species", wood2);
        contentValues.put("stone_material", stone1);
        contentValues.put("water_resistant", lam1);
        contentValues.put("water_proof", vinyl1);
        contentValues.put("tile_material", tile1);
        long res = writeDB.insert("stock_table", null,contentValues);

        return res;

    }

    long deleteProduct(String prod_ID, String store_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("stock_table", "product_id = ? AND store_id = ?", new String[] {prod_ID, String.valueOf(store_id)});
    }
    long updateProduct(String proId,
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
                       String stone1,
                       String lam1,
                       String vinyl1,
                       String tile1,
                       int stk_k){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id", proId);
        contentValues.put("product_name", name);
        contentValues.put("color", color);
        contentValues.put("size_sq_ft", Double.parseDouble(size));
        contentValues.put("price", Double.parseDouble(price));
        contentValues.put("brand", brand);
        contentValues.put("type", floor);
        contentValues.put("store_id", String.valueOf(store));
        contentValues.put("qty", String.valueOf(qty));
        contentValues.put("wood_type", wood1);
        contentValues.put("wood_species", wood2);
        contentValues.put("stone_material", stone1);
        contentValues.put("water_resistant", lam1);
        contentValues.put("water_proof", vinyl1);
        contentValues.put("tile_material", tile1);
        long res = db.update("stock_table", contentValues,"stock_key = ? ", new String[] {String.valueOf(stk_k)});
        return res;

    }

//do not use
    private Cursor searchQ1Internal(String col, String uInput){
        SQLiteDatabase readDB = this.getReadableDatabase();
        if(readDB == null){
            return null;
        }
        Cursor c = readDB.rawQuery("SELECT * FROM stock_table " +
                "WHERE " + col + " = ?", new String[] {String.valueOf(uInput)}, null);

        return c;
    }

}




