package com.vozisov.lynxmedia.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Article {

	@SerializedName("team1")
	private String team1;

	@SerializedName("team2")
	private String team2;

	@SerializedName("prediction")
	private String prediction;

	@SerializedName("time")
	private String time;

	@SerializedName("tournament")
	private String tournament;

	@SerializedName("place")
	private String place;

	@SerializedName("article")
	private List<ArticleItem> article;

	public void setTeam1(String team1){
		this.team1 = team1;
	}

	public String getTeam1(){
		return team1;
	}

	public void setTeam2(String team2){
		this.team2 = team2;
	}

	public String getTeam2(){
		return team2;
	}

	public void setPrediction(String prediction){
		this.prediction = prediction;
	}

	public String getPrediction(){
		return prediction;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setTournament(String tournament){
		this.tournament = tournament;
	}

	public String getTournament(){
		return tournament;
	}

	public void setPlace(String place){
		this.place = place;
	}

	public String getPlace(){
		return place;
	}

	public void setArticle(List<ArticleItem> article){
		this.article = article;
	}

	public List<ArticleItem> getArticle(){
		return article;
	}

	@Override
 	public String toString(){
		return 
			"Article{" +
			"team1 = '" + team1 + '\'' + 
			",team2 = '" + team2 + '\'' + 
			",prediction = '" + prediction + '\'' + 
			",time = '" + time + '\'' + 
			",tournament = '" + tournament + '\'' + 
			",place = '" + place + '\'' + 
			",article = '" + article + '\'' + 
			"}";
		}
}