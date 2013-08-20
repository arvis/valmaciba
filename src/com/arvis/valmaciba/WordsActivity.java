package com.arvis.valmaciba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import com.actionbarsherlock.app.SherlockActivity;
//import com.actionbarsherlock.view.MenuInflater;
import com.arvis.valmaciba.R;
import com.arvis.valmaciba.models.Stats;
import com.arvis.valmaciba.models.Word;
import com.arvis.valmaciba.models.WordsList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WordsActivity extends SherlockActivity {
	
	private int currentRigthIndex=0;
	private int currentId=0;
	private int correctGuesses=0;
	private int incorrectGuesses=0;
	private WordsList wordsList=new WordsList();

	private ArrayList<Integer> pastAnswers=new ArrayList<Integer>();
	private Stats stats;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_words);
		stats=new Stats(this);
		
		this.newWord();
	}

	@Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        getSupportMenuInflater().inflate(R.menu.words, menu);
        return super.onCreateOptionsMenu(menu);
    }	

	@Override
	  public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_settings:
	    	break;
	    case R.id.action_stats:
	    	Intent intent = new Intent(this, StatsActivity.class);
	    	startActivity(intent);
	    default:
	      break;
	    }

	    return true;
	  } 	
	
	
	
/*	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.words, menu);
		return true;
	}
	
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_settings:
	    	break;
	    case R.id.action_stats:
	    	Intent intent = new Intent(this, StatsActivity.class);
	    	startActivity(intent);
	    default:
	      break;
	    }

	    return true;
	  } 	
*/	
	private void onCorrectAnwser(){
		correctGuesses++;
		stats.addCorrectAnswers();
		
	}
	
	private void onWrongAnswer(){
		incorrectGuesses++;
		stats.addWrongAnswers();
	}
	
	public void onAnswer1(View view) {
		Button btn=(Button)findViewById(R.id.answer1);  
		
		if (currentRigthIndex==0){
			onCorrectAnwser();
			btn.setBackgroundColor(Color.GREEN);
			newWord();
		}
		else {
			onWrongAnswer();
			btn.setBackgroundColor(Color.RED);
		}
	}

	public void onAnswer2(View view) {
		Button btn=(Button)findViewById(R.id.answer2);  

		if (currentRigthIndex==1){
			onCorrectAnwser();
			//btn.getBackground().setColorFilter(Color.GREEN,PorterDuff.Mode.MULTIPLY);
			btn.setBackgroundColor(Color.GREEN);
			newWord();

		}
		else {
			onWrongAnswer();
			//btn.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
			btn.setBackgroundColor(Color.RED);
		}
		
	}

	public void onAnswer3(View view) {
		Button btn=(Button)findViewById(R.id.answer3);  

		if (currentRigthIndex==2){
			onCorrectAnwser();
			btn.setBackgroundColor(Color.GREEN);
			newWord();
		}
		else {
			btn.setBackgroundColor(Color.RED);

			onWrongAnswer();
		}
	}

	public void onAnswer4(View view) {
		Button btn=(Button)findViewById(R.id.answer4);  

		if (currentRigthIndex==3){
			btn.setBackgroundColor(Color.GREEN);

			//TODO: wait for a little
			onCorrectAnwser();
			newWord();
			
		}
		else {
			onWrongAnswer();
			btn.setBackgroundColor(Color.RED);
		}
		
		
	}
	
	private void newWord(){
		
		// set right and wrong answer count
		TextView correctResult=(TextView)findViewById(R.id.correctResult); 
		correctResult.setText(correctGuesses+"");
		TextView incorrectResult=(TextView)findViewById(R.id.incorrectResult); 
		incorrectResult.setText(incorrectGuesses+"");
		
		List<Word>result=wordsList.getWords();
		changeLabels(result);
	}
	
	private void changeLabels(List<Word>wordData){
		
		if (wordData.size()!=4){
			//TODO: implement way to show some error message
			Log.e(null, "No data in words database");
			return;
		}
		
		/*from four answers looking which one will displayed as right
		 * */
		//TODO: better name
		int rigthId= (int) (Math.random() * 4);
		
		//FIXME: better button organisation
		Button button1=(Button)findViewById(R.id.answer1);  
		Button button2=(Button)findViewById(R.id.answer2);  
		Button button3=(Button)findViewById(R.id.answer3);  
		Button button4=(Button)findViewById(R.id.answer4);  
		
		button1.setBackgroundResource(android.R.drawable.btn_default);
		button2.setBackgroundResource(android.R.drawable.btn_default);
		button3.setBackgroundResource(android.R.drawable.btn_default);
		button4.setBackgroundResource(android.R.drawable.btn_default);
		
		//TODO: maybe need more flexible way
		button1.setText(wordData.get(0).getWordLV());
		button2.setText(wordData.get(1).getWordLV());
		button3.setText(wordData.get(2).getWordLV());
		button4.setText(wordData.get(3).getWordLV());
		
		
		currentRigthIndex=rigthId;
		currentId= wordData.get(rigthId).getId();
		//pastAnswers.add()
		
		TextView t=(TextView)findViewById(R.id.wordToCheck); 
		t.setText(wordData.get(rigthId).getWordEN());
		    
		
		
	}
	
	

}
