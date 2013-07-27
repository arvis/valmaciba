package com.arvis.valmaciba;

import java.io.IOException;
import java.util.List;

import com.arvis.valmaciba.R;
import com.arvis.valmaciba.models.WordsDbHelper;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WordsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_words);
		
		this.setupStage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.words, menu);
		return true;
	}
	public void onAnswer1(View view) {
		
		Button button1=(Button)findViewById(R.id.answer1);  
		button1.setText("First answer"+System.currentTimeMillis());

		TextView t=(TextView)findViewById(R.id.wordToCheck); 
		t.setText("Precious!"+System.currentTimeMillis());
		
	}

	public void onAnswer2(View view) {
		System.out.println(view.getId());
		
	}

	public void onAnswer3(View view) {
		System.out.println(view.getId());
	}

	public void onAnswer4(View view) {
		System.out.println(view.getId());
	}
	
	private void setupStage(){
		// get random number
		// change labels
		
		WordsDbHelper data=new WordsDbHelper(this);
		try {
			data.createDb();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int[] wordIds={1,2,3,4};
		List<String>result=data.getAllWords();
		
		changeLabels(result);
		
	}
	
	private void changeLabels(List<String>result){
		
		if (result.size()==0){
			//TODO: implement way to show some error message
			Log.e(null, "No data in words database");
			return;
		}
		
		
		TextView t=(TextView)findViewById(R.id.wordToCheck); 
		t.setText(result.get(0));
		    
		Button button1=(Button)findViewById(R.id.answer1);  
		Button button2=(Button)findViewById(R.id.answer2);  
		Button button3=(Button)findViewById(R.id.answer3);  
		Button button4=(Button)findViewById(R.id.answer4);  
		
	}
	
	

}
