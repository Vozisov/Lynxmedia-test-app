package com.vozisov.lynxmedia.model;

public class EventsItem{
	private String preview;
	private String coefficient;
	private String time;
	private String place;
	private String title;
	private String article;

	public void setPreview(String preview){
		this.preview = preview;
	}

	public String getPreview(){
		return preview;
	}

	public void setCoefficient(String coefficient){
		this.coefficient = coefficient;
	}

	public String getCoefficient(){
		return coefficient;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setPlace(String place){
		this.place = place;
	}

	public String getPlace(){
		return place;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setArticle(String article){
		this.article = article;
	}

	public String getArticle(){
		return article;
	}

	@Override
 	public String toString(){
		return 
			"EventsItem{" + 
			"preview = '" + preview + '\'' + 
			",coefficient = '" + coefficient + '\'' + 
			",time = '" + time + '\'' + 
			",place = '" + place + '\'' + 
			",title = '" + title + '\'' + 
			",article = '" + article + '\'' + 
			"}";
		}
}
