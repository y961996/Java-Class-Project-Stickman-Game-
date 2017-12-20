package com.game.src.main;

import java.awt.Graphics;

public class Camera{
	
	private float x;
	private float y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void tick(Player player){
		/*if(player.getVelX() > 0 && x >= 0){
			x += player.getVelX() / 2;
		}else if(player.getVelX() < 0 && x > 0){
			x += player.getVelX() / 2;
		}*/
		if(x<1920)
			x = (float) (player.getX());
	}
	
	public void render(Graphics g){
		//Shows the camera!!
		/*g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 50, 50);*/
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
