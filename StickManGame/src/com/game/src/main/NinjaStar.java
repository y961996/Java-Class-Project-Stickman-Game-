package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NinjaStar extends Game{

	private static final long serialVersionUID = 1L;
	private double x ;
	private double y;
	private double speed;
	private int rate;
	
	public ArrayList<NinjaStar> stars = new ArrayList<NinjaStar>();
	
	private Rectangle s�n�r;
	
	private BufferedImage star;
	
	public NinjaStar(double x, double y, double speed, int rate, Game game){
		this.x  = x;
		this.y = y;
		this.speed = speed;
		this.rate = rate;
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		star = ss.grabImage(1, 17, 32, 32);
	}
	
	public void tick(){
		s�n�r = new Rectangle((int)x, (int)y, 32, 32);
		
		x += speed;
		if(s�n�r.intersects(Game.game.getPlayer().getS�n�r())){
			Game.game.getPlayer().setDead(true);
		}
		
	}
	
	public void render(Graphics g){
		g.drawImage(star, (int)x, (int)y, null);
	}
	
	public int getRate(){
		return this.rate;
	}
}
