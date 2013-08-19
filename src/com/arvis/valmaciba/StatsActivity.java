package com.arvis.valmaciba;

import com.arvis.valmaciba.models.Stats;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StatsActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats);
		
		showStatsValues();
		
	}
	
	private void showStatsValues(){
		
		Stats stats= new Stats(this);
		
		TextView correctAnswers=(TextView)findViewById(R.id.correct_value); 
		String correct=Integer.toString(stats.getCorrectAnswers()); 
		correctAnswers.setText(correct);

		TextView wrongAnswers=(TextView)findViewById(R.id.wrong_value); 
		String incorrect=Integer.toString(stats.getWrongAnswers());
		wrongAnswers.setText(incorrect);
		
	}
	
	
	
	
}
