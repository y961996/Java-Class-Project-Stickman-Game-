package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.game.src.main.lib.Animation;

public class Weapon {

	private double x;
	private double y;
	
	private Rectangle sýnýr;
	
	Animation animationRight;
	Animation animationLeft;
	Animation animation2Right;
	Animation animation2Left;
	
	private BufferedImage[] swordAnimationRight = new BufferedImage[6];
	private BufferedImage[] swordAnimationLeft = new BufferedImage[6];
	private BufferedImage[] sword2AnimationRight = new BufferedImage[6];
	private BufferedImage[] sword2AnimationLeft= new BufferedImage[6];
	
	public Weapon(double x, double y, Game game){
		this.x = x;
		this.y = y;
		
		BufferedImageLoader loader = new BufferedImageLoader();

		try {
			swordAnimationRight[0] = loader.loadImage("/swords/1/1.png");
			swordAnimationRight[1] = loader.loadImage("/swords/1/2.png");
			swordAnimationRight[2] = loader.loadImage("/swords/1/3.png");
			swordAnimationRight[3] = loader.loadImage("/swords/1/4.png");
			swordAnimationRight[4] = loader.loadImage("/swords/1/5.png");
			swordAnimationRight[5] = loader.loadImage("/swords/1/6.png");
			swordAnimationLeft[0] = loader.loadImage("/swords/1/7.png");
			swordAnimationLeft[1] = loader.loadImage("/swords/1/8.png");
			swordAnimationLeft[2] = loader.loadImage("/swords/1/9.png");
			swordAnimationLeft[3] = loader.loadImage("/swords/1/10.png");
			swordAnimationLeft[4] = loader.loadImage("/swords/1/11.png");
			swordAnimationLeft[5] = loader.loadImage("/swords/1/12.png");
			sword2AnimationRight[0] = loader.loadImage("/swords/2/1.png");
			sword2AnimationRight[1] = loader.loadImage("/swords/2/2.png");
			sword2AnimationRight[2] = loader.loadImage("/swords/2/3.png");
			sword2AnimationRight[3] = loader.loadImage("/swords/2/4.png");
			sword2AnimationRight[4] = loader.loadImage("/swords/2/5.png");
			sword2AnimationRight[5] = loader.loadImage("/swords/2/6.png");
			sword2AnimationLeft[0] = loader.loadImage("/swords/2/7.png");
			sword2AnimationLeft[1] = loader.loadImage("/swords/2/8.png");
			sword2AnimationLeft[2] = loader.loadImage("/swords/2/9.png");
			sword2AnimationLeft[3] = loader.loadImage("/swords/2/10.png");
			sword2AnimationLeft[4] = loader.loadImage("/swords/2/11.png");
			sword2AnimationLeft[5] = loader.loadImage("/swords/2/12.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		animationRight = new Animation(5, swordAnimationRight[0], swordAnimationRight[1], swordAnimationRight[2], swordAnimationRight[3], swordAnimationRight[4], swordAnimationRight[5]);
		animationLeft = new Animation(5, swordAnimationLeft[0], swordAnimationLeft[1], swordAnimationLeft[2], swordAnimationLeft[3],swordAnimationLeft[4], swordAnimationLeft[5]);
		animation2Right = new Animation(5, sword2AnimationRight[0], sword2AnimationRight[1], sword2AnimationRight[2], sword2AnimationRight[3], sword2AnimationRight[4], sword2AnimationRight[5]);
		animation2Left = new Animation(5, sword2AnimationLeft[0], sword2AnimationLeft[1], sword2AnimationLeft[2], sword2AnimationLeft[3], sword2AnimationLeft[4], sword2AnimationLeft[5]);
	}
	
	public void tick(){
		sýnýr = new Rectangle((int)x, (int)y, 32, 32);
		
		if(Game.game.getPlayer().getGoingRight() && Game.game.isWalking()){
			x = Game.game.getPlayer().getX() + 23;
			y = Game.game.getPlayer().getY() + 3;
		}else if(Game.game.getPlayer().getGoingLeft() && Game.game.isWalking()){
			x = Game.game.getPlayer().getX() - 23;
			y = Game.game.getPlayer().getY() + 7;
		}else{
			x = Game.game.getPlayer().getX() + 23;
			y = Game.game.getPlayer().getY() + 3;
		}
		
		for(int i = 0; i < Game.game.getEnemyArray().size(); i++){
			if(Game.game.getEnemyArray().get(i) != null && sýnýr.intersects(Game.game.getEnemyArray().get(i).getSýnýr()) && Game.game.isCharging()){
				//Game.game.setEnemy(null);
				Game.game.getEnemyArray().get(i).setDead(true);
			}
		}
		
		
		if(Game.game.isCharging() && Game.game.getPlayer().getGoingRight()){
			if(Game.game.getCharacterSelection().getSwords()[1]){
				animationRight.runAnimation();
			}else if(Game.game.getCharacterSelection().getSwords()[0]){
				animation2Right.runAnimation();
			}
			
		}else if(Game.game.isCharging() && Game.game.getPlayer().getGoingLeft()){
			if(Game.game.getCharacterSelection().getSwords()[1]){
				animationLeft.runAnimation();
			}else if(Game.game.getCharacterSelection().getSwords()[0]){
				animation2Left.runAnimation();
			}
		}
	}
	
	public void render(Graphics g){
		if(Game.game.getCharacterSelection().getSwords()[1]){
			if(!Game.game.isCharging() && Game.game.getPlayer().getGoingRight()){
				g.drawImage(swordAnimationRight[0], (int)x - 6, (int)y - 6, null);
			}else if(!Game.game.isCharging() && Game.game.getPlayer().getGoingLeft()){
				g.drawImage(swordAnimationLeft[0], (int)x - 24, (int)y - 9, null);
			}else if(Game.game.isCharging() && Game.game.getPlayer().getGoingRight()){
				animationRight.drawAnimation(g, x - 6, y - 6, 0);
			}else if(Game.game.isCharging() && Game.game.getPlayer().getGoingLeft()){
				animationLeft.drawAnimation(g, x - 24, y - 9, 0);
			}else{
				g.drawImage(swordAnimationRight[0], (int)x - 6, (int)y - 6, null);
			}
		}else if(Game.game.getCharacterSelection().getSwords()[0]){
			if(!Game.game.isCharging() && Game.game.getPlayer().getGoingRight()){
				g.drawImage(sword2AnimationRight[0], (int)x - 6, (int)y - 12, null);
			}else if(!Game.game.isCharging() && Game.game.getPlayer().getGoingLeft()){
				g.drawImage(sword2AnimationLeft[0], (int)x - 24, (int)y - 12, null);
			}else if(Game.game.isCharging() && Game.game.getPlayer().getGoingRight()){
				animation2Right.drawAnimation(g, x - 6, y - 12, 0);
			}else if(Game.game.isCharging() && Game.game.getPlayer().getGoingLeft()){
				animation2Left.drawAnimation(g, x - 24, y - 12, 0);
			}else{
				g.drawImage(sword2AnimationRight[0], (int)x - 6, (int)y - 6, null);
			}
		}
	}
}
