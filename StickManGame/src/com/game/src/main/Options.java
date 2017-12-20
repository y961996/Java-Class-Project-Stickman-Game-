package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Options {
	
	private BufferedImage options = null;
	private BufferedImage buy = null;
	private BufferedImage unlock = null;
	private BufferedImage esc = null;
	private BufferedImage space = null;
	private BufferedImage arrows = null;
	
	public Options(){
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			options = loader.loadImage("/options/options.png");
			buy = loader.loadImage("/options/buy.png");
			unlock = loader.loadImage("/options/unlock.png");
			esc = loader.loadImage("/options/esc.png");
			space = loader.loadImage("/options/space.png");
			arrows = loader.loadImage("/options/arrows.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g){
		
		g.drawImage(Game.game.getOptionsBackground(), 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, Game.game);
		
		g.drawImage(options, 50, 0, Game.game);
		
		g.drawImage(arrows, 80, 200, arrows.getWidth(), arrows.getHeight(), Game.game);
		g.drawImage(space, 80, 320, space.getWidth(), space.getHeight(), Game.game);
		g.drawImage(esc, 200, 400, esc.getWidth(), esc.getHeight(), Game.game);
		
		g.drawImage(unlock, 830, 20, unlock.getWidth(), unlock.getHeight(), Game.game);
		g.drawImage(buy, 950, 70, buy.getWidth(), buy.getHeight(), Game.game);
	}
}
