package su.keiwu.com.personalcontentlibrary.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kei on 11/19/15.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "library.db";
    private static final int SCHEMA_VERSION = 1;
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String IMAGE_NAME = "image_name";
    public static final String CONTENT = "content";
    public static final String LIBRARY_TABLE = "library_table";

    public static DBHelper dbHelperInstance = null;

    public static DBHelper getInstance(Context context) {
        if (dbHelperInstance == null) {
            dbHelperInstance = new DBHelper(context.getApplicationContext());
        }
        return (dbHelperInstance);
    }

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE library_table (id INTEGER UNIQUE PRIMARY KEY, title TEXT, image_name TEXT, content TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertArticle(ContentValues cv){
        getReadableDatabase().insert(LIBRARY_TABLE, ID, cv);
        query();

    }

    public Cursor query(){
        String query = String.format("SELECT * FROM %s", LIBRARY_TABLE);

        Cursor result = getReadableDatabase().rawQuery(query, null);

        return result;

//        while (result.moveToNext()) {
//            String id = result.getString(0);
//            String title = result.getString(1);
//            String image_path = result.getString(2);
//            String content= result.getString(3);
//
//            Log.d("Keiwuquery", id + " " + title + " " + image_path + " " + content);
//        }
    }

}
