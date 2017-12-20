package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.lib.Animation;

public class Player {

	private double x;
	private double y;
	
	private double gravity = 0.5;
	private final double MAX_SPEED = 10;
	
	private double velX = 0;
	private double velY = 0;
	
	private boolean goingRight = false;
	private boolean goingLeft = false;
	private boolean dead = false;
	
	private Rectangle sýnýr;
	
	Animation animationCoolManRight;
	Animation animationCoolManLeft;
	Animation animationNinjaRight;
	Animation animationNinjaLeft;
	Animation animationVampireRight;
	Animation animationVampireLeft;
	Animation animationHackerRight;
	Animation animationHackerLeft;
	
	//private BufferedImage[] player = new BufferedImage[8];
	private BufferedImage[] player = new BufferedImage[52];
	
	public Player(double x, double y, Game game){
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		//old SpriteSheet
		/*for(int i = 0; i < 8; i++){
			player[i] = ss.grabImage(i + 1, 1, 32, 64);
		}*/
		
		//New SpriteSheet
		
		for(int i = 0; i < 13; i++){
			player[i] = ss.grabImage(i + 1, 1, 32, 64);
		}
		for(int i = 13; i < 26; i++){
			player[i] = ss.grabImage(i - 12, 3, 32, 64);
		}
		for(int i = 26; i < 39; i++){
			player[i] = ss.grabImage(i - 25, 5, 32, 64);
		}
		for(int i = 39; i < 52; i++){
			player[i] = ss.grabImage(i - 38, 7, 32, 64);
		}
		
		//player[0]
		animationCoolManRight = new Animation(3, player[7], player[8], player[9], player[10], player[11]
				   ,player[12]);
		animationCoolManLeft = new Animation(3, player[1], player[2], player[3], player[4], player[5]
				   , player[6]);
		
		//player[13]
		animationNinjaRight = new Animation(3, player[20], player[21], player[22], player[23], player[24]
				   ,player[25]);
		animationNinjaLeft = new Animation(3, player[14], player[15], player[16], player[17], player[18]
				   ,player[19]);
		
		//player[26]
		animationVampireRight = new Animation(3, player[33], player[34], player[35], player[36], player[37]
				   ,player[38]);
		animationVampireLeft = new Animation(3, player[27], player[28], player[29], player[30], player[31]
				   ,player[32]);
		
		//player[39]
		animationHackerRight = new Animation(3, player[46], player[47], player[48], player[49], player[50]
				   ,player[51]);
		animationHackerLeft = new Animation(3, player[40], player[41], player[42], player[43], player[44]
				   ,player[45]);
	}
	
	public void tick(){
		sýnýr = new Rectangle((int)x, (int)y, 32, 64);
		if(!dead){
			x+=velX;
			y+=velY;

			if(y > 420){
				Game.game.setJumping(false);
			}
			
			if(x <= 0)
				x = 0;
			if(x >= 1280 -18)
				x = 1280 -18;
			if(y <= 0)
				y = 0;
			if(y >= 480 - 48)
				y=480 - 48;
			
			if(Game.game.isFalling() || Game.game.isJumping()){
				velY += gravity;
				
				if (velY > MAX_SPEED)
					velY = MAX_SPEED;
			}
			
			if(Game.game.isWalking() && goingRight){
				if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[0]){
					animationHackerRight.runAnimation();
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[1]){
					animationNinjaRight.runAnimation();
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[2]){
					animationCoolManRight.runAnimation();
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[3]){
					animationVampireRight.runAnimation();
				}
			}else if(Game.game.isWalking() && goingLeft){
				if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[0]){
					animationHackerLeft.runAnimation();
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[1]){
					animationNinjaLeft.runAnimation();	
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[2]){
					animationCoolManLeft.runAnimation();
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[3]){
					animationVampireLeft.runAnimation();
				}
			}
		}
		
	}
	
	public void render(Graphics g){
		if(dead){
			
		}else if(!dead){
			if(!Game.game.isWalking()){
				if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[0]){
					g.drawImage(player[39], (int)x, (int)y, null);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[1]){
					g.drawImage(player[13], (int)x, (int)y, null);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[2]){
					g.drawImage(player[0], (int)x, (int)y, null);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[3]){
					g.drawImage(player[26], (int)x, (int)y, null);
				}
			}else if(Game.game.isWalking() && goingRight){
				if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[0]){
					animationHackerRight.drawAnimation(g, x, y, 0);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[1]){
					animationNinjaRight.drawAnimation(g, x, y, 0);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[2]){
					animationCoolManRight.drawAnimation(g, x, y, 0);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[3]){
					animationVampireRight.drawAnimation(g, x, y, 0);
				}
			}else if(Game.game.isWalking() && goingLeft){
				if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[0]){
					animationHackerLeft.drawAnimation(g, x, y, 0);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[1]){
					animationNinjaLeft.drawAnimation(g, x, y, 0);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[2]){
					animationCoolManLeft.drawAnimation(g, x, y, 0);
				}else if(Game.game.getCharacterSelection().getCharactersAnimationCheck()[3]){
					animationVampireLeft.drawAnimation(g, x, y, 0);
				}
			}
		}	
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setVelX(double velX){
		this.velX = velX;
	}
	public void setVelY(double velY){
		this.velY = velY;
	}
	public Rectangle getSýnýr(){
		return sýnýr;
	}
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setGoingRight(boolean b){
		this.goingRight = b;
	}
	public void setGoingLeft(boolean b){
		this.goingLeft = b;
	}
	public boolean getGoingRight(){
		return this.goingRight;
	}
	public boolean getGoingLeft(){
		return this.goingLeft;
	}
	public Animation getAnimationNinjaRight(){
		return this.animationNinjaRight;
	}
	public void setDead(boolean b){
		this.dead = b;
	}
	public boolean getDead(){
		return this.dead;
	}
}
