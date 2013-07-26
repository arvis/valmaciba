package com.arvis.valmaciba.models;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author nekrod
 *
 */
public class WordsDbHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_WORDS= "words";
	public static final String COLUMN_ID = "id";

	public static final String TABLE_STATISTICS= "statistics";
	
	
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "learnwords.db";	
    
    private static final String DATABASE_CREATE = "create table "
    	      + TABLE_WORDS + "(" + "id integer primary key autoincrement, "
    	      + "word_lv text not null, word_en text not null);";    
	
	 public WordsDbHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);

			SQLiteDatabase db = this.getWritableDatabase();
			
			//dummy data insert, just for testing
			ContentValues values = new ContentValues();
			values.put("word_lv", "nosaukums");
			values.put("word_en", "title");
			
			long id=db.insert(WordsDbHelper.TABLE_WORDS, null, values);
			db.close();
	        
	        
	    }	
	
	/* (non-Javadoc)
	 * @see Database creation
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//database.execSQL(DATABASE_CREATE);
		 db.execSQL(DATABASE_CREATE);

	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
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
	
	
	public List<String> getWords(int[] idList){
		List<String> result=new ArrayList<String>();
		return result;
	}
	
	
	
}
