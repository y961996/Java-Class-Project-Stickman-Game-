package com.game.src.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends MouseAdapter implements MouseListener{
	
	Game game;
	CharacterSelection cs;	
	
	public MouseInput(){
		
	}
	
	public MouseInput(Game game){
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {

		int mouseX = e.getX();
		int mouseY = e.getY();

		//start Button
		if(Game.state == Game.STATE.GAME){
			if(mouseX >= 570 && mouseX <= 770 && mouseY >= 350 && mouseY <= 400 && Game.game.getPlayer().getDead()){
				Game.state = Game.STATE.MENU;
				Game.game.setNewPlayer();
				Game.game.addNewEnemy(Game.game.getLevel1Enemy1());
				Game.game.setEnemyArray();
				Game.game.setNewSword();
				Game.game.setCharacterSelection();
				Game.game.setController();
			}
		}else if(Game.state == Game.STATE.MENU){
			if(mouseX >= Game.WIDTH - 32 && mouseX <= Game.WIDTH + 75 && mouseY >= 150 && mouseY <= 195){
				Game.state= Game.STATE.CHARACTER_SELECTION;
			}
			else if(mouseX >= Game.WIDTH - 42 && mouseX <= Game.WIDTH + 80 && mouseY >= 225 && mouseY <= 270){
				Game.state= Game.STATE.OPTIONS;
			}
			else if(mouseX >= Game.WIDTH - 27 && mouseX <= Game.WIDTH + 67 && mouseY >= 300 && mouseY <= 345){
				System.exit(1);
			}
		}else if(Game.state == Game.STATE.CHARACTER_SELECTION){
			if(mouseX >= 750 && mouseX <= 880 && mouseY >= 100 && mouseY <= 230){
				Game.game.getCharacterSelection().setBackSword(Game.game.getCharacterSelection().getBackSword1());
				Game.game.getCharacterSelection().setSwordsBool(0, true);
				Game.game.getCharacterSelection().setSwordsBool(1, false);
			}
			if(mouseX >= 900 && mouseX <= 1030 && mouseY >= 100 && mouseY <= 230){
				Game.game.getCharacterSelection().setBackSword(Game.game.getCharacterSelection().getBackSword2());
				Game.game.getCharacterSelection().setSwordsBool(0, false);
				Game.game.getCharacterSelection().setSwordsBool(1, true);
			}
			if(mouseY >= 450 && mouseY <= 482){
				if(mouseX >= 90 && mouseX <= 140){
					if(Game.game.getCharacterSelection().getCurrentCharacterAnimation().equals(Game.game.getCharacterSelection().getHackerAnimation())){
						Game.game.getCharacterSelection().setCurrentCharacterAnimation(
								Game.game.getCharacterSelection().getVampireAnimation());
						Game.game.getCharacterSelection().setCharactersAnimationCheck(0, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(1, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(2, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(3, true);
						Game.game.getCharacterSelection().setCurrentName(Game.game.getCharacterSelection().getVampireName());
					}else if(Game.game.getCharacterSelection().getCurrentCharacterAnimation().equals(Game.game.getCharacterSelection().getNinjaAnimation())){
						Game.game.getCharacterSelection().setCurrentCharacterAnimation(
								Game.game.getCharacterSelection().getHackerAnimation());
						Game.game.getCharacterSelection().setCharactersAnimationCheck(0, true);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(1, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(2, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(3, false);
						Game.game.getCharacterSelection().setCurrentName(Game.game.getCharacterSelection().getHackerName());
					}else if(Game.game.getCharacterSelection().getCurrentCharacterAnimation().equals(Game.game.getCharacterSelection().getNoBodyAnimation())){
						Game.game.getCharacterSelection().setCurrentCharacterAnimation(
								Game.game.getCharacterSelection().getNinjaAnimation());
						Game.game.getCharacterSelection().setCharactersAnimationCheck(0, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(1, true);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(2, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(3, false);
						Game.game.getCharacterSelection().setCurrentName(Game.game.getCharacterSelection().getNinjaName());
					}else if(Game.game.getCharacterSelection().getCurrentCharacterAnimation().equals(Game.game.getCharacterSelection().getVampireAnimation())){
						Game.game.getCharacterSelection().setCurrentCharacterAnimation(
								Game.game.getCharacterSelection().getNoBodyAnimation());
						Game.game.getCharacterSelection().setCharactersAnimationCheck(0, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(1, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(2, true);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(3, false);
						Game.game.getCharacterSelection().setCurrentName(Game.game.getCharacterSelection().getNoBodyName());
					}
					
					
				}else if(mouseX >= 190 && mouseX <= 240){
					
					if(Game.game.getCharacterSelection().getCurrentCharacterAnimation().equals(Game.game.getCharacterSelection().getHackerAnimation())){
						Game.game.getCharacterSelection().setCurrentCharacterAnimation(
								Game.game.getCharacterSelection().getNinjaAnimation());
						Game.game.getCharacterSelection().setCharactersAnimationCheck(0, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(1, true);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(2, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(3, false);
						Game.game.getCharacterSelection().setCurrentName(Game.game.getCharacterSelection().getNinjaName());
					}else if(Game.game.getCharacterSelection().getCurrentCharacterAnimation().equals(Game.game.getCharacterSelection().getNinjaAnimation())){
						Game.game.getCharacterSelection().setCurrentCharacterAnimation(
								Game.game.getCharacterSelection().getNoBodyAnimation());
						Game.game.getCharacterSelection().setCharactersAnimationCheck(0, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(1, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(2, true);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(3, false);
						Game.game.getCharacterSelection().setCurrentName(Game.game.getCharacterSelection().getNoBodyName());
					}else if(Game.game.getCharacterSelection().getCurrentCharacterAnimation().equals(Game.game.getCharacterSelection().getNoBodyAnimation())){
						Game.game.getCharacterSelection().setCurrentCharacterAnimation(
								Game.game.getCharacterSelection().getVampireAnimation());
						Game.game.getCharacterSelection().setCharactersAnimationCheck(0, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(1, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(2, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(3, true);
						Game.game.getCharacterSelection().setCurrentName(Game.game.getCharacterSelection().getVampireName());
					}else if(Game.game.getCharacterSelection().getCurrentCharacterAnimation().equals(Game.game.getCharacterSelection().getVampireAnimation())){
						Game.game.getCharacterSelection().setCurrentCharacterAnimation(
								Game.game.getCharacterSelection().getHackerAnimation());
						Game.game.getCharacterSelection().setCharactersAnimationCheck(0, true);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(1, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(2, false);
						Game.game.getCharacterSelection().setCharactersAnimationCheck(3, false);
						Game.game.getCharacterSelection().setCurrentName(Game.game.getCharacterSelection().getHackerName());
					}
				}
			}else if(mouseX >= 1050 && mouseX <= 1144 && mouseY >= 400 && mouseY <= 442){
				if(Game.game.getCharacterSelection().getSwords()[0] || Game.game.getCharacterSelection().getSwords()[1]){
					Game.state = Game.STATE.GAME;
				}else{
					Game.game.getCharacterSelection().chooseWeaponClicked = true;
				}
			}else if(mouseX >= 780 && mouseX <= 989 && mouseY >= 400 && mouseY <= 442){
				Game.state = Game.STATE.MENU;
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		//super.mouseMoved(e);
		int mouseX = e.getX();
		int mouseY = e.getY();

		//start Button
		if(Game.state == Game.STATE.MENU){
			if(mouseX >= Game.WIDTH - 32 && mouseX <= Game.WIDTH + 75 && mouseY >= 150 && mouseY <= 195){
				Game.game.getMenu().setStartButton(Game.game.getMenu().getStartButton2Img());
			}
			else if(mouseX >= Game.WIDTH - 42 && mouseX <= Game.WIDTH + 80 && mouseY >= 225 && mouseY <= 270){
				Game.game.getMenu().setOptionsButton(Game.game.getMenu().getOptionsButton2Img());
			}
			else if(mouseX >= Game.WIDTH - 27 && mouseX <= Game.WIDTH + 67 && mouseY >= 300 && mouseY <= 345){
				Game.game.getMenu().setQuitButton(Game.game.getMenu().getQuitButton2Img());
			}
			else{
				Game.game.getMenu().setStartButton(Game.game.getMenu().getStartButtonImg());
				Game.game.getMenu().setOptionsButton(Game.game.getMenu().getOptionsButtonImg());
				Game.game.getMenu().setQuitButton(Game.game.getMenu().getQuitButtonImg());
			}
		}else if(Game.state == Game.STATE.CHARACTER_SELECTION){
			if(mouseX >= 1050 && mouseX <= 1144 && mouseY >= 400 && mouseY <= 442){
				Game.game.getCharacterSelection().setStartbutton(Game.game.getCharacterSelection().getStartButton2());
			}else{
				Game.game.getCharacterSelection().setStartbutton(Game.game.getCharacterSelection().getStartButton1());
			}
			
			if(mouseX >= 780 && mouseX <= 989 && mouseY >= 400 && mouseY <= 442){
				Game.game.getCharacterSelection().setBackMenuButton(Game.game.getCharacterSelection().getBackMenuButton2());
			}else{
				Game.game.getCharacterSelection().setBackMenuButton(Game.game.getCharacterSelection().getBackMenuButton1());
			}
		}else if(!(Game.state == Game.STATE.MENU)){
			Game.game.getMenu().setStartButton(Game.game.getMenu().getStartButtonImg());
			Game.game.getMenu().setOptionsButton(Game.game.getMenu().getOptionsButtonImg());
			Game.game.getMenu().setQuitButton(Game.game.getMenu().getQuitButtonImg());
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

}
