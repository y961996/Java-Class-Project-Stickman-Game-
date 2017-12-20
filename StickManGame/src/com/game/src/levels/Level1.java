package com.game.src.levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.game.src.main.Camera;
import com.game.src.main.Controller;
import com.game.src.main.Enemy;
import com.game.src.main.Game;
import com.game.src.main.NinjaStar;
import com.game.src.main.Player;
import com.game.src.main.Weapon;

public class Level1 extends Game{
	
	private static final long serialVersionUID = 1L;

	private Player player;
	private Weapon sword;
	private ArrayList<Enemy> enemies;
	private Camera camera;
	private Controller c;
	
	public Level1(BufferedImage background){
		c = new Controller(this);
		player = new Player(0,0,this);
		sword = new Weapon(Game.game.getPlayer().getX() + 15,Game.game.getPlayer().getY() - 23, this);
		camera = new Camera(0, 0);
	}
	
	public void render(Graphics g){
		player.render(g);
		sword.render(g);
		c.render(g);
		camera.render(g);
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).render(g);
		}
	}
	
	public void tick(){
		player.tick();
		sword.tick();
		camera.tick(player);
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i) != null){
				enemies.get(i).tick();
				rateTimer++;
				if(rateTimer >= 200 && !enemies.get(i).isDead()){
					if(enemies.get(i).getGoingRight()) c.addStar(new NinjaStar(enemies.get(i).getX(), enemies.get(i).getY(), -5, 40, this));
					if(enemies.get(i).getGoingLeft()) c.addStar(new NinjaStar(enemies.get(i).getX(), enemies.get(i).getY(), 5, 40, this));
					rateTimer = 0;
				}
			}
		}
	}
	
	public void addEnemy(Enemy e){
		enemies.add(e);
	}
	
	public void removeEnemy(){
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).isDead()){
				enemies.remove(enemies.get(i));
			}
		}
	}
}
