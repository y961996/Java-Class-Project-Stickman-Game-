package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Menu {

	private BufferedImageLoader loader;
	private BufferedImage stickMan = null; 
	private BufferedImage startButtonImg = null;
	private BufferedImage startButton2Img = null;
	private BufferedImage optionsButtonImg = null;
	private BufferedImage optionsButton2Img = null;
	private BufferedImage quitButtonImg = null;
	private BufferedImage quitButton2Img = null;
	
	private BufferedImage startButton = null;
	private BufferedImage optionsButton = null;
	private BufferedImage quitButton = null;
	
	public Menu(){
		loader = new BufferedImageLoader();
		
		try {
			stickMan = loader.loadImage("/stickman.png");
			startButtonImg = loader.loadImage("/Menu Buttons/start.png");
			startButton2Img = loader.loadImage("/Menu Buttons/start2.png");
			optionsButtonImg = loader.loadImage("/Menu Buttons/options.png");
			optionsButton2Img = loader.loadImage("/Menu Buttons/options2.png");
			quitButtonImg = loader.loadImage("/Menu Buttons/quit.png");
			quitButton2Img = loader.loadImage("/Menu Buttons/quit2.png");
			
			setStartButton(startButtonImg);
			setOptionsButton(optionsButtonImg);
			setQuitButton(quitButtonImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void render(Graphics g){

		g.drawImage(stickMan, Game.WIDTH - 95, 30, stickMan.getWidth(), stickMan.getHeight(), Game.game);
		g.drawImage(startButton, Game.WIDTH - 30, 150, Game.game);
		g.drawImage(optionsButton, Game.WIDTH - 40, 225, Game.game);
		g.drawImage(quitButton, Game.WIDTH - 25, 300, Game.game);
	}
	
	public void tick(){
		
	}
	
	public void setStartButton(BufferedImage img){
		this.startButton = img;
	}
	public void setOptionsButton(BufferedImage img){
		this.optionsButton = img;
	}
	public void setQuitButton(BufferedImage img){
		this.quitButton = img;
	}

	public BufferedImage getStartButtonImg() {
		return startButtonImg;
	}

	public BufferedImage getStartButton2Img() {
		return startButton2Img;
	}

	public BufferedImage getOptionsButtonImg() {
		return optionsButtonImg;
	}

	public BufferedImage getOptionsButton2Img() {
		return optionsButton2Img;
	}

	public BufferedImage getQuitButtonImg() {
		return quitButtonImg;
	}

	public BufferedImage getQuitButton2Img() {
		return quitButton2Img;
	}
}
