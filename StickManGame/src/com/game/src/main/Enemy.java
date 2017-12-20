package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.game.src.main.lib.Animation;

public class Enemy {
	
	private double x;
	private double y;
	private double velX = 4;
	
	private boolean goingRight = false;
	private boolean goingLeft = true;
	private boolean dead = false;
	
	Animation enemyRight;
	Animation enemyLeft;
	
	Controller c = new Controller(Game.game);
	
	//private BufferedImage enemy;
	private BufferedImage[] enemies = new BufferedImage[13];
	private BufferedImage grave = null;
	
	private Rectangle sýnýr;
	
	public Enemy(double x, double y, Game game){
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		BufferedImageLoader loader = new BufferedImageLoader();
		//enemy = ss.grabImage(1, 5, 32, 64);
		
		//New SpriteSheet
		
		for(int i = 0; i < 13; i++){
			enemies[i] = ss.grabImage(i + 1, 9, 32, 64);
		}
		
		try {
			grave = loader.loadImage("/animationStuff/grave.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		enemyRight = new Animation(3, enemies[1], enemies[2], enemies[3], enemies[4], enemies[5],
				enemies[6]);
		enemyLeft = new Animation(3, enemies[7], enemies[8], enemies[9], enemies[10], enemies[11],
				enemies[12]);
		
	}
	
	public void tick(){
		sýnýr = new Rectangle((int)x, (int)y + 16, 32, 48);//Collision box for enemy.
		for(int i = 0; i < Game.game.getEnemyArray().size(); i++){
			if(!dead){
				if(x >= 0 && x <= Game.WIDTH * Game.SCALE - 20){
					if(goingRight){
						x -= velX;
						enemyRight.runAnimation();
						if(x == 0){
							goingRight = false;
							goingLeft = true;
						}else if(Game.game.getPlayer().getSýnýr().intersects(Game.game.getEnemyArray().get(i).sýnýr) && !Game.game.getPlayer().getDead()){
							goingRight = false;
							goingLeft = true;
						}
					}else if(goingLeft){
						x += velX;
						enemyLeft.runAnimation();
						if(x == Game.WIDTH * Game.SCALE - 32){
							goingLeft = false;
							goingRight = true;
						}else if(Game.game.getPlayer().getSýnýr().intersects(Game.game.getEnemyArray().get(i).sýnýr) && !Game.game.getPlayer().getDead()){
							goingLeft = false;
							goingRight = true;
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics g){
		//g.drawImage(enemy, (int)x, (int)y, null);
		
		//New SpriteSheet
		if(!dead){
			if(goingRight){
				enemyRight.drawAnimation(g, x, y, 0);
			}else if(goingLeft){
				enemyLeft.drawAnimation(g, x, y, 0);
			}
		}else{
			g.drawImage(grave, (int)x, (int)y, Game.game);
		}
		
		
		//Collision box control.!!!
		//g.drawRect((int)x, (int)y + 16, 32, 48);
	}
	
	public Rectangle getSýnýr(){
		return sýnýr;
	}
	public void setVelX(double velX){
		this.velX = velX;
	}
	public boolean isDead(){
		return this.dead;
	}
	public void setDead(boolean dead){
		this.dead = dead;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public boolean getGoingRight(){
		return this.goingRight;
	}
	public boolean getGoingLeft(){
		return this.goingLeft;
	}
}
