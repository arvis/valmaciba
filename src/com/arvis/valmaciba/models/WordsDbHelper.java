package com.arvis.valmaciba.models;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.UserDictionary.Words;

/**
 * @author nekrod
 *
 */
public class WordsDbHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_WORDS= "words";
	public static final String COLUMN_ID = "id";

	public static final String TABLE_STATISTICS= "statistics";
	private static String DB_PATH = "/databases/";
	//private static String DB_PATH = "/data/data/com.arvis.valmaciba/databases/";
	
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "wordlearn.sqlite";
    private File dBPath;
    private final Context context;
    
    private static final String DATABASE_CREATE = "create table "
    	      + TABLE_WORDS + "(" + "id integer primary key autoincrement, "
    	      + "word_lv text not null, word_en text not null);";    
	
	 public WordsDbHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        this.context=context;
	        
	        dBPath=context.getDatabasePath(DATABASE_NAME);
	        //DB_PATH=context.getFilesDir().getPath()+DB_PATH;
	        
	        // TODO: implement db creation to ensure that people have data
	        // http://stackoverflow.com/questions/513084/how-to-ship-an-android-application-with-a-database

			
/*			
			SQLiteDatabase db = this.getWritableDatabase();
			//dummy data insert, just for testing
			ContentValues values = new ContentValues();
			values.put("word_lv", "nosaukums");
			values.put("word_en", "title");
			
			long id=db.insert(WordsDbHelper.TABLE_WORDS, null, values);
			db.close();
*/	        
	        
	    }	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//database.execSQL(DATABASE_CREATE);
		 //db.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
	}


	  /**
	     * Creates a empty database on the system and rewrites it with your own database.
	     * */
	    public void createDb() throws IOException{
	 
	    	boolean dbExist = checkDbExists();
	 
        	try {
		    	if(dbExist){
		    		//FIXME: for now, just to test
		    		copyDataBase();
		    	}else{
		 
		    		//By calling this method and empty database will be created into the default system path
		               //of your application so we are gonna be able to overwrite that database with our database.
		        	this.getReadableDatabase();
	    			copyDataBase();
		    	}
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}

	 
	    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDbExists(){
    	SQLiteDatabase checkDB = null;
    	try{
    		
    		checkDB = SQLiteDatabase.openDatabase(dBPath.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
    	}catch(SQLiteException e){
    		//database does't exist yet.
    	}
 
    	if(checkDB != null){
    		checkDB.close();
    	}
    	return checkDB != null ? true : false;
    }
	 	    
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
    	
    	InputStream myInput = context.getAssets().open(DATABASE_NAME);
    	OutputStream myOutput = new FileOutputStream(dBPath.getAbsoluteFile());
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }	
	
	
	public List<String> getAllWords(){
		
		SQLiteDatabase db = this.getReadableDatabase();
		List<String> result=new ArrayList<String>();
		
		String selectQuery = "SELECT  * FROM words";
        Cursor cursor = db.rawQuery(selectQuery, null);		

        if (cursor.moveToFirst()) {
        	do {
	        	//String row=cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2);
	        	String row=cursor.getString(1);
	            result.add(row);
	        } while (cursor.moveToNext());
        }
        
        db.close();
		
		return result;
	}
	
	
	public List<Word> getWords(String[] wordIds){
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  * FROM words"
			    + " WHERE id IN (?,?,?,?)";
		
        Cursor cursor = db.rawQuery(selectQuery, wordIds);		
		List<Word> result=new ArrayList<Word>();

        if (cursor.moveToFirst()) {
        	do {
	        	//String row=cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2);
        		Word row=new Word();
	        	//String row=cursor.getString(1);
        		row.setId(Integer.parseInt(cursor.getString(0)));
        		row.setWordLV(cursor.getString(1));
        		row.setWordEN(cursor.getString(2));
	            result.add(row);
	        } while (cursor.moveToNext());
        }
        db.close();
		return result;
	}
	
	
	
}
