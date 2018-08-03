package com.fcsdm.sdmserver.mvc.model.dto;

public class League {
	private String team;
	private int point;
	private int win;
	private int draw;
	private int lose;
	private int diff;
	private int goal;
	private int losePoint;
	private int lastWin;
	private int lastDraw;
	private int lastLose;
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public int getLosePoint() {
		return losePoint;
	}
	public void setLosePoint(int losePoint) {
		this.losePoint = losePoint;
	}
	public int getLastWin() {
		return lastWin;
	}
	public void setLastWin(int lastWin) {
		this.lastWin = lastWin;
	}
	
	public int getLastDraw() {
		return lastDraw;
	}
	public void setLastDraw(int lastDraw) {
		this.lastDraw = lastDraw;
	}
	public int getLastLose() {
		return lastLose;
	}
	public void setLastLose(int lastLose) {
		this.lastLose = lastLose;
	}
	
	
}