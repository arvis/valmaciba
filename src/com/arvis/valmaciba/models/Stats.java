package com.arvis.valmaciba.models;

import android.R;
import android.content.Context;
import android.content.SharedPreferences;

public class Stats {
	private int correctAnswers=0;
	private int wrongAnswers=0;
	private int totalAnswers=0;
	private Context context;
	SharedPreferences sharedPref;
	
	public Stats(Context context){
		this.context=context;
		//TODO: make ini file in string
		sharedPref = context.getSharedPreferences("com.arvis.words_stats.ini", Context.MODE_PRIVATE);		
		setInitialValues();
	}
	
	/**
	 * get initial values for statistics
	 * */
	private void setInitialValues(){
		correctAnswers = sharedPref.getInt("correct_answers" , correctAnswers);
		wrongAnswers = sharedPref.getInt("wrong_answers" , wrongAnswers);
		
	}
	
	private void writeToPrefs(String key, int value){
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(key,value);
		editor.commit();
	}
	
	
	public int getCorrectAnswers() {
		return correctAnswers;
	}
	
	public void addCorrectAnswers() {
		this.correctAnswers ++;
		writeToPrefs("correct_answers",this.correctAnswers);
	}
	public int getTotalAnswers() {
		return this.correctAnswers+this.wrongAnswers;
	}
	public void addTotalAnswers() {
		this.totalAnswers += totalAnswers;
	}
	
	public int getWrongAnswers() {
		return wrongAnswers;
	}
	public void addWrongAnswers() {
		this.wrongAnswers ++;
		
		this.addTotalAnswers();
		writeToPrefs("wrong_answers",this.wrongAnswers);
		
	}
	
	

}
