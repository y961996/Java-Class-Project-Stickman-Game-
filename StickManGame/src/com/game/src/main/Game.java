package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public final String TITLE = "2D STICKMAN GAME";
	
	private Random random = new Random();
	
	public int timer = 0;
	public int rateTimer = 0;
	public static int menuBackgroundTimer = 0;
	public byte movingTimer = 0;
	
	private boolean running = false;
	private boolean falling = true;
	private boolean jumping = false;
	private boolean walking = false;
	private boolean charging = false;
	public boolean gameEsc = false;
	private boolean[] keyPressed = {false, false, false};
	private Thread thread;
	//private Camera camera;
	private static Clip clip;

	private BufferedImage startUpImage = null;
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage optionsBackground = null;
	private BufferedImage menuBackground = null;
	private BufferedImage menuBackground1 = null;
	private BufferedImage menuBackground2 = null;
	private BufferedImage menuBackground3 = null;
	private BufferedImage menuBackground4 = null;
	private BufferedImage menuBackground5 = null;
	private BufferedImage menuBackground6 = null;
	private BufferedImage menuBackground7 = null;
	private BufferedImage menuBackground8 = null;
	
	private Controller c;
	private Player p;
	//private Enemy enemy;
	private Weapon sword;
	public static Game game;
	private Menu menu;
	private CharacterSelection cs;
	private Options options;
	private ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
	public Enemy Level1Enemy1;
	public Enemy Level1Enemy2;
	public Enemy Level1Enemy3;
	public Enemy Level1Enemy4;
	public Enemy Level1Enemy5;
	
	public enum STATE{
		MENU,
		GAME,
		CHARACTER_SELECTION,
		OPTIONS,
		LEVEL1,
		LEVEL2
	};
	
	public static STATE state = STATE.MENU;
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			startUpImage = loader.loadImage("/Backgrounds/Background001.png");
			spriteSheet = loader.loadImage("/cursor_SpriteSheet/SpriteSheetActual.png");
			background = loader.loadImage("/Backgrounds/background2.png");
			optionsBackground = loader.loadImage("/Backgrounds/Background002.png");
			menuBackground = loader.loadImage("/Backgrounds/BG2 Loop/1.png");
			menuBackground1 = loader.loadImage("/Backgrounds/BG2 Loop/3.png");
			menuBackground2 = loader.loadImage("/Backgrounds/BG2 Loop/4.png");
			menuBackground3 = loader.loadImage("/Backgrounds/BG2 Loop/5.png");
			menuBackground4 = loader.loadImage("/Backgrounds/BG2 Loop/6.png");
			menuBackground5 = loader.loadImage("/Backgrounds/BG2 Loop/7.png");
			menuBackground6 = loader.loadImage("/Backgrounds/BG2 Loop/8.png");
			menuBackground7 = loader.loadImage("/Backgrounds/BG2 Loop/9.png");
			menuBackground8 = loader.loadImage("/Backgrounds/BG2 Loop/10.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//camera = new Camera(0, 0);
		p = new Player(0, HEIGHT * SCALE - 108 , this);
		c = new Controller(this);
		Level1Enemy1 = new Enemy(Game.WIDTH * Game.SCALE - 100, Game.HEIGHT * Game.SCALE - 54, game);
		enemyArray.add(Level1Enemy1);
		sword = new Weapon(Game.game.getPlayer().getX() + 15,Game.game.getPlayer().getY() - 23,this);
		menu = new Menu();
		cs = new CharacterSelection();
		options = new Options();
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		this.addMouseMotionListener(new MouseInput(this));
	}
	
	private synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
		//Background Music StartUp
		if(Game.state == STATE.MENU || Game.state == STATE.OPTIONS){
			try {
				clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sounds/sound1.wav"));
				clip.open(inputStream);
				clip.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void stop(){
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run(){  //Game Engine (Kinda)
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " Ticks, Fps" + frames);
				updates = 0;
				frames= 0;
			}
			
		}
		stop();
	}
	
	private void tick(){
		if(timer < Game.WIDTH * Game.SCALE + 10){
			p.getAnimationNinjaRight().runAnimation();
		}
		if(state == STATE.GAME){
			clip.stop();
			p.tick();
			c.tick();
			//camera.tick(p);
			for(int i = 0; i < enemyArray.size(); i++){
					enemyArray.get(i).tick();
					rateTimer++;
					if(rateTimer >= + (random.nextInt(500) + 75) && !enemyArray.get(i).isDead()){
						if(enemyArray.get(i).getGoingRight()) c.addStar(new NinjaStar(enemyArray.get(i).getX(), enemyArray.get(i).getY(), -7, 40, this));
						if(enemyArray.get(i).getGoingLeft()) c.addStar(new NinjaStar(enemyArray.get(i).getX(), enemyArray.get(i).getY(), 7, 40, this));
						rateTimer = 0;
					}		
			}
			sword.tick();
		}else if(state == STATE.CHARACTER_SELECTION){
			cs.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();  //Bütün çizilecek þeyler burda çiziliyor.
		//Graphics2D g2d = (Graphics2D) g;
		
		
		
		if(state == STATE.GAME){
			
			//Collision control
			///////System.out.println("Player: " + p.getSýnýr() + " ,Enemy:" + enemy.getSýnýr());
			
			// background.getWidth kullanarak resmi geniþ bir þekilde yayabiliyoruz.Yapmamýz gerekn resim ile 
			// kamerayý ayný anda hareket ettirebilmek ve ben resmi nasýl hereket ettirebilceðimizi bilmiyorum.
			// Bunu araþtýr.
			g.drawImage(background, -(int) 0, 0, 1920, getHeight(), this);
			
			Font font = new Font("SansSerif", Font.BOLD, 15);
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString("Press ESC to go to menu.", 20, 20);
			g.drawString("Press SPACE to move the sword.", 20, 40);
			
			//g2d.translate(camera.getX(), camera.getY());
			
			//camera.render(g2d);
			for(int i = 0; i < enemyArray.size(); i++){
				enemyArray.get(i).render(g);
			}
			p.render(g);
			c.render(g);
			if(!p.getDead()){
				sword.render(g);
			}
			if(gameEsc){
				
			}
			if(p.getDead()){
				g.setColor(Color.RED);
				Font gameOverFont = new Font("SansSerif", Font.BOLD, 70);
				g.setFont(gameOverFont);
				g.drawString("Game Over", 475, 165);
				
				g.setFont(new Font("SansSerif", Font.BOLD, 30));

				g.setColor(Color.GRAY);
				g.fillRoundRect(570, 350, 200, 50, 35, 35);
				g.setColor(Color.BLACK);
				g.drawString("Go To Menu", 580, 385);
			}
			
			//g2d.translate(-camera.getX(), -camera.getY());
			
		}else if(state == STATE.MENU){
			if(menuBackgroundTimer < 50){
				g.drawImage(menuBackground, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
			}else if(menuBackgroundTimer < 100){
				g.drawImage(menuBackground1, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
			}else if(menuBackgroundTimer < 150){
				g.drawImage(menuBackground2, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
			}else if(menuBackgroundTimer < 200){
				g.drawImage(menuBackground3, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
			}else if(menuBackgroundTimer < 250){
				g.drawImage(menuBackground4, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
			}else if(menuBackgroundTimer < 300){
				g.drawImage(menuBackground5, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
			}else if(menuBackgroundTimer < 350){
				g.drawImage(menuBackground6, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
			}else if(menuBackgroundTimer < 400){
				g.drawImage(menuBackground7, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
			}else{
				g.drawImage(menuBackground8, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
				menuBackgroundTimer++;
				if(menuBackgroundTimer > 450){
					menuBackgroundTimer = 0;
				}
			}
			menu.render(g);
		}else if(state == STATE.OPTIONS){
			options.render(g);
		}else if(Game.state == Game.STATE.CHARACTER_SELECTION){
			cs.render(g);
		}
		
		//Loading Screen
		if(timer < Game.WIDTH * Game.SCALE + 10){
			timer+=3;
			movingTimer++;
			
			g.drawImage(startUpImage, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, this);
			
			if(movingTimer > 20){
				movingTimer = 0;
			}
			g.setColor(Color.GRAY);
			g.setFont(new Font("Courier New", Font.BOLD, 35));
			g.drawString("Loading...", 570, 180);
			g.setColor(Color.WHITE);
			g.fillRect(0, 400, timer, 20);
			g.setColor(Color.BLUE);
			g.drawRect(0, 400, Game.WIDTH * Game.SCALE + 10, 20);
			p.getAnimationNinjaRight().drawAnimation(g, timer - 10, 380, 0);
		}
		
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(state == STATE.GAME){
			if(key == KeyEvent.VK_RIGHT){
				p.setGoingLeft(false);
				p.setGoingRight(true);
				p.setVelX(5);
				keyPressed[0] = true;
				keyPressed[1] = false;
				walking = true;
			}else if(key == KeyEvent.VK_LEFT){
				p.setGoingRight(false);
				p.setGoingLeft(true);
				p.setVelX(-5);
				keyPressed[1] = true;
				keyPressed[0] = false;
				walking = true;
			}else if(key == KeyEvent.VK_UP && !jumping){
				p.setVelY(-7);
				keyPressed[2] = true;
				jumping = true;
			}else if(key == KeyEvent.VK_ESCAPE){
				state = STATE.MENU;
				Game.game.setNewPlayer();
				Game.game.addNewEnemy(Level1Enemy1);
				Game.game.setEnemyArray();
				Game.game.setNewSword();
				Game.game.setCharacterSelection();
				Game.game.setController();
			}else if(key == KeyEvent.VK_SPACE){
				charging= true;
			}
		}else if(state == STATE.OPTIONS){
			if(key == KeyEvent.VK_ESCAPE){
				state = STATE.MENU;
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(state == STATE.GAME){
			if(key == KeyEvent.VK_RIGHT){
				keyPressed[0] = false;
				walking = false;
			}else if(key == KeyEvent.VK_LEFT){
				p.setGoingLeft(false);
				p.setGoingRight(true);
				keyPressed[1] = false;
				walking = false;
			}else if(key == KeyEvent.VK_UP){
				keyPressed[2] = false;
			}else if(key == KeyEvent.VK_SPACE){
				charging = false;
				//c.addStar(new NinjaStar(p.getX(), p.getY(),5, 40, this));
			}
			
			if(!keyPressed[0] && !keyPressed[1]){
				p.setVelX(0);
			}else if(!keyPressed[2]){
				p.setVelY(0);
			}
		}
	}
	
	public static void main(String[] args) {
		game = new Game();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image cursor = toolkit.getImage("res/cursor_SpriteSheet/cursor.png");
		Point cursorHotSpot = new Point(0, 0);
		Cursor customCursor = toolkit.createCustomCursor(cursor, cursorHotSpot, "cursor");
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setCursor(customCursor);
		
		JFrame frame = new JFrame(game.TITLE);  // Ekran oluþtruma
		
		if(cursor != null){
			frame.setCursor(customCursor);
			
		}
		//frame.getRootPane().setCursor(customCursor);
		
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();  // Oyunu baþlatma
		
		
	}
	
	////////////
	/// Getters And Setters
	////////////
	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public ArrayList<Enemy> getEnemyArray(){
		return enemyArray;
	}
	
	public void setEnemyArray(){
		for(int i = 0; i < enemyArray.size(); i++){
			enemyArray.remove(enemyArray.get(i));
		}
	}
	
	public boolean isCharging(){
		return charging;
	}
	
	public Menu getMenu(){
		return menu;
	}
	
	public CharacterSelection getCharacterSelection(){
		return cs;
	}
	
	public BufferedImage getOptionsBackground(){
		return this.optionsBackground;
	}
	
	public void setNewPlayer(){
		this.p = new Player(0, HEIGHT * SCALE - 108 , this);;
	}

	public void addNewEnemy(Enemy e){
		this.enemyArray.add(new Enemy(WIDTH * SCALE - 100, HEIGHT * SCALE - 54, this));
	}
	
	public void setNewSword(){
		this.sword = new Weapon(Game.game.getPlayer().getX() + 15,Game.game.getPlayer().getY() - 23,this);;
	}
	
	public void setCharacterSelection(){
		this.cs = new CharacterSelection();
	}
	
	public void setController(){
		this.c = new Controller();
	}
	public Enemy getLevel1Enemy1(){
		return this.Level1Enemy1;
	}
	public Enemy getLevel1Enemy2(){
		return this.Level1Enemy2;
	}
	public Enemy getLevel1Enemy3(){
		return this.Level1Enemy3;
	}
	public Enemy getLevel1Enemy4(){
		return this.Level1Enemy4;
	}
	public Enemy getLevel1Enemy5(){
		return this.Level1Enemy5;
	}
	
}
