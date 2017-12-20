package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

	public LinkedList<NinjaStar> stars = new LinkedList<NinjaStar>();
	
	NinjaStar tempStar;
	Game game;
	
	public Controller(){
		
	}
	
	public Controller(Game game){
		this.game = game;
	}
	
	public void tick(){
		for(int i = 0; i < stars.size(); i++){
			tempStar = stars.get(i);
			tempStar.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < stars.size(); i++){
			tempStar = stars.get(i);
			tempStar.render(g);
		}
	}
	
	public void addStar(NinjaStar star){
		stars.add(star);
	}
	
	public void removeStar(NinjaStar star){
		stars.remove(star);
	}
}
