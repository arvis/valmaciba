package com.arvis.valmaciba.models;

public class Word {
	private int id;
	private String wordLV;
	private String wordEN;
	
	public Word(){
		
	}
	public Word(String wordLV, String wordEN){
		this.wordLV=wordLV;
		this.wordEN=wordEN;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWordLV() {
		return wordLV;
	}
	public void setWordLV(String wordLV) {
		this.wordLV = wordLV;
	}
	public String getWordEN() {
		return wordEN;
	}
	public void setWordEN(String wordEN) {
		this.wordEN = wordEN;
	}
	

}
