package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.game.src.main.lib.Animation;

public class CharacterSelection {

	private Game game;

	private Clip clip;
	
	public boolean chooseWeaponClicked = false;
	private boolean[] charactersAnimationCheck = {true, false, false, false};
	private boolean[] swords = {false,false};

	//1,2,3 Hacker 4,5,6 Ninja 7,8,9 NoBody 10,11 Vampire
	private BufferedImage[] characters = new BufferedImage[12];
	private BufferedImage[] weapons = new BufferedImage[7];
	private BufferedImage backSword = null;
	private BufferedImage backSword1 = null;
	private BufferedImage backSword2 = null;
	private BufferedImage temp_backSword = null;
	private BufferedImage lock = null;
	private BufferedImage background = null;
	private BufferedImage rightArrow = null;
	private BufferedImage leftArrow = null;
	private BufferedImage startButton1 = null;
	private BufferedImage startButton2 = null;
	private BufferedImage startButton = null;
	private BufferedImage backMenuButton1 = null;
	private BufferedImage backMenuButton2 = null;
	private BufferedImage backMenuButton = null;
	private BufferedImage selectYourCharacter = null;
	private BufferedImage selectYourSword = null;
	private BufferedImage nameNinja = null;
	private BufferedImage nameNoBody = null;
	private BufferedImage nameHacker = null;
	private BufferedImage nameVampire = null;
	private BufferedImage currentName = null;
	
	private Animation currentCharacterAnimation;
	private Animation hackerAnimation;
	private Animation ninjaAnimation;
	private Animation noBodyAnimation;
	private Animation vampireAnimation;
	
	private BufferedImageLoader loader = new BufferedImageLoader();
	
	public CharacterSelection(){
		try {
			characters[0] = loader.loadImage("/Char Options/Characters/HackOPT1.png");
			characters[1] = loader.loadImage("/Char Options/Characters/HackOPT2.png");
			characters[2] = loader.loadImage("/Char Options/Characters/HackOPT3.png");
			characters[3] = loader.loadImage("/Char Options/Characters/NinjaOPT1.png");
			characters[4] = loader.loadImage("/Char Options/Characters/NinjaOPT2.png");
			characters[5] = loader.loadImage("/Char Options/Characters/NinjaOPT3.png");
			characters[6] = loader.loadImage("/Char Options/Characters/NoBodyOPT1.png");
			characters[7] = loader.loadImage("/Char Options/Characters/NoBodyOPT2.png");
			characters[8] = loader.loadImage("/Char Options/Characters/NoBodyOPT3.png");
			characters[9] = loader.loadImage("/Char Options/Characters/VampireOPT1.png");
			characters[10] = loader.loadImage("/Char Options/Characters/VampireOPT2.png");
			characters[11] = loader.loadImage("/Char Options/Characters/VampireOPT3.png");
			
			hackerAnimation = new Animation(10, characters[0], characters[1], characters[2]);
			ninjaAnimation = new Animation(10, characters[3], characters[4], characters[5]);
			noBodyAnimation = new Animation(10, characters[6], characters[7], characters[8]);
			vampireAnimation = new Animation(10, characters[9], characters[10], characters[11]);

			setCurrentCharacterAnimation(hackerAnimation);
			
			nameNinja = loader.loadImage("/Char Options/charNames/button_ninja.png");
			nameHacker = loader.loadImage("/Char Options/charNames/button_hacker.png");
			nameNoBody = loader.loadImage("/Char Options/charNames/button_mr-nobody.png");
			nameVampire = loader.loadImage("/Char Options/charNames/button_vampire.png");
			
			setCurrentName(nameHacker);
			
			lock = loader.loadImage("/Char Options/swords/lock.png");
			temp_backSword = loader.loadImage("/Char Options/swords/back_empty.png");
			backSword1 = loader.loadImage("/Char Options/swords/back1.png");
			backSword2 = loader.loadImage("/Char Options/swords/back2.png");
			
			setBackSword(temp_backSword);
			
			weapons[0] = loader.loadImage("/Char Options/swords/s1.png");
			weapons[2] = loader.loadImage("/Char Options/swords/1.png");
			weapons[3] = loader.loadImage("/Char Options/swords/2.png");
			weapons[4] = loader.loadImage("/Char Options/swords/3.png");
			weapons[5] = loader.loadImage("/Char Options/swords/4.png");
			weapons[6] = loader.loadImage("/Char Options/swords/5.png");
			background = loader.loadImage("/Backgrounds/bg2.png");
			rightArrow = loader.loadImage("/Char Options/rightarrow.png");
			leftArrow = loader.loadImage("/Char Options/leftarrow.png");
			startButton1 = loader.loadImage("/Char Options/startButton1.png");
			startButton2 = loader.loadImage("/Char Options/startButton2.png");
			startButton = startButton1;
			backMenuButton1 = loader.loadImage("/Char Options/backMenuButton1.png");
			backMenuButton2 = loader.loadImage("/Char Options/backMenuButton2.png");
			backMenuButton = backMenuButton1;
			
			selectYourCharacter = loader.loadImage("/Char Options/selectCharacter.png");
			selectYourSword = loader.loadImage("/Char Options/selectSword.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sounds/Error.wav"));
			clip.open(inputStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void tick(){
		currentCharacterAnimation.runAnimation();
	}
	public void render(Graphics g){
		
		g.drawImage(background, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, game);
		
		Font font1 = new Font("SansSerif", Font.BOLD, 30);
		g.setFont(font1);
		
		//Character drawing here..
		if(currentCharacterAnimation.equals(noBodyAnimation)){
			g.drawImage(backSword, 0, 30, backSword.getWidth(), backSword.getHeight(), game);
			currentCharacterAnimation.drawAnimation(g, -25, -5, 0);
		}else{
			g.drawImage(backSword, -10, 0, backSword.getWidth(), backSword.getHeight(), game);
			currentCharacterAnimation.drawAnimation(g, -70, -70, 0);
		}
		
		g.drawImage(selectYourCharacter, 230, 30, game);

		g.drawImage(rightArrow, 190, 450, rightArrow.getWidth(), rightArrow.getHeight(), game);
		g.drawImage(leftArrow, 90, 450, leftArrow.getWidth(), leftArrow.getHeight(), game);
		
		g.drawImage(currentName, 300, 440, game);
		
		g.drawImage(selectYourSword, 750, 30, game);
		
		g.drawRect(750, 100, 130, 130);
		g.drawRect(900, 100, 130, 130);
		g.drawRect(1050, 100, 130, 130);
		g.drawRect(750, 250, 130, 130);
		g.drawRect(900, 250, 130, 130);
		g.drawRect(1050, 250, 130, 130);
		
		g.drawImage(weapons[4], 775, 120, weapons[4].getWidth(), weapons[4].getHeight(), game);
		g.drawImage(weapons[2], 920, 120, weapons[2].getWidth(), weapons[2].getHeight(), game);
		g.drawImage(weapons[3], 1070, 120, weapons[3].getWidth(), weapons[3].getHeight(), game);
		g.drawImage(lock, 1050, 100, 130, 130, game);
		g.drawImage(weapons[0], 780, 280, weapons[0].getWidth(), weapons[0].getHeight(), game);
		g.drawImage(lock, 750, 250, 130, 130, game);
		g.drawImage(weapons[5], 920, 270, weapons[5].getWidth(), weapons[5].getHeight(), game);
		g.drawImage(lock, 900, 250, 130, 130, game);
		g.drawImage(weapons[6], 1070, 270, weapons[6].getWidth(), weapons[6].getHeight(), game);
		g.drawImage(lock, 1050, 250, 130, 130, game);
		
		g.drawImage(backMenuButton, 780, 400, backMenuButton.getWidth(), backMenuButton.getHeight(), game);
		g.drawImage(startButton, 1050, 400, startButton.getWidth(), startButton.getHeight(), game);
		
		if(chooseWeaponClicked){
			clip.start();
			g.setColor(Color.RED);
			g.drawString("Please Choose A Weapon!", 250, 250);
		}
	}
	
	public BufferedImage[] getCharacters(){
		return this.characters;
	}
	public void setCurrentCharacterAnimation(Animation anim){
		this.currentCharacterAnimation = anim;
	}
	public Animation getCurrentCharacterAnimation(){
		return this.currentCharacterAnimation;
	}
	public void setStartbutton(BufferedImage img){
		this.startButton = img;
	}
	public BufferedImage getStartButton1(){
		return startButton1;
	}
	public BufferedImage getStartButton2(){
		return startButton2;
	}
	public void setBackMenuButton(BufferedImage img){
		this.backMenuButton = img;
	}
	public BufferedImage getBackMenuButton1(){
		return this.backMenuButton1;
	}
	public BufferedImage getBackMenuButton2(){
		return this.backMenuButton2;
	}
	public boolean[] getCharactersAnimationCheck(){
		return this.charactersAnimationCheck;
	}
	public boolean setCharactersAnimationCheck(int index, boolean value){
		return this.charactersAnimationCheck[index] = value;
	}
	public Animation getHackerAnimation(){
		return this.hackerAnimation;
	}
	public Animation getNinjaAnimation(){
		return this.ninjaAnimation;
	}
	public Animation getNoBodyAnimation(){
		return this.noBodyAnimation;
	}
	public Animation getVampireAnimation(){
		return this.vampireAnimation;
	}
	public void setBackSword(BufferedImage img){
		this.backSword = img;
	}
	public BufferedImage getBackSword1(){
		return this.backSword1;
	}
	public BufferedImage getBackSword2(){
		return this.backSword2;
	}
	public boolean[] getSwords(){
		return this.swords;
	}
	public void setSwordsBool(int index, boolean b){
		this.swords[index] = b;
	}
	public void setCurrentName(BufferedImage i){
		this.currentName = i;
	}
	public BufferedImage getNinjaName(){
		return this.nameNinja;
	}
	public BufferedImage getHackerName(){
		return this.nameHacker;
	}
	public BufferedImage getNoBodyName(){
		return this.nameNoBody;
	}
	public BufferedImage getVampireName(){
		return this.nameVampire;
	}
}
