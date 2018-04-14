package com.vozisov.lynxmedia.model;

import com.google.gson.annotations.SerializedName;

public class ArticleItem{

	@SerializedName("header")
	private String header;

	@SerializedName("text")
	private String text;

	public void setHeader(String header){
		this.header = header;
	}

	public String getHeader(){
		return header;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"ArticleItem{" + 
			"header = '" + header + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}