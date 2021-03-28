package com.rafay.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.rafay.framework.GameObject;
import com.rafay.framework.KeyInput;
import com.rafay.framework.ObjectId;
import com.rafay.framework.Texture;
import com.rafay.objects.Block;
import com.rafay.objects.Coin;
import com.rafay.objects.Flag;
import com.rafay.objects.Player;


public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = -8610632058446347862L;

	
	private boolean running = false;
	private Thread thread;

	
	public static int WIDTH, HEIGHT;
	 
	private int score = 0;
//	private GameObject player, coin;

	public BufferedImage level = null, cloud = null, tree = null;
	//Object
	Handler handler;
	Camera cam;
	private HUD hud;
	static Texture text;
	
	public static int LEVEL = 1;

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		text = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); //loads image
		cloud = loader.loadImage("/cloud.png"); //loads clouds
		tree = loader.loadImage("/tree.png");   // load tree
		cam = new Camera(0,0);
		handler = new Handler(cam);
		hud = new HUD();
		
		
		handler.LoadImageLevel(level);
	    //handler.addObject(new Player(10,500,handler, ObjectId.Player));
		
		//handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
	}
	


	public synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
   
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}	
		}
		
	}
	
	

	private void tick() {
		
	handler.tick();
	hud.tick();
	for (int i  = 0; i < handler.object.size();i++) {
		if (handler.object.get(i).getId() == ObjectId.Player) {
			cam.tick(handler.object.get(i));
		}
	}
	
	}
	
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
		   this.createBufferStrategy(3);
		   return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////////////////
		
		
		//Draw 
		g.setColor(new Color(25,191,224));
		g.fillRect(0, 0,getWidth(),getHeight());
		
		hud.render(g);
		g2d.translate(cam.getX(), cam.getY()); //start camera
		for (int xx = 0; xx < cloud.getWidth() * 8;xx += cloud.getWidth())
		       g.drawImage(cloud, xx, 50,this);
		
		for (int xx = 0; xx < tree.getWidth() * 8;xx += tree.getWidth())
		       g.drawImage(tree, xx, 235,this);
		
//		if (Player.isCollision(coin)){
//			score++;
//		}
		
//		g2d.setColor(Color.WHITE);
//		g2d.drawString("Score: "+ score , 40,40);
	    
		handler.render(g);
		
		
       g2d.translate(cam.getX(),-cam.getY()); // end camera
		////////////////////////////

       
		g.dispose();
		bs.show();
		
	}
 
	public static int clamp(int health,int min, int max) {
		if (health >= max) {
			return health = max;
		}
		else if (health <= min) {
			return health = min;
		}else {
		return health;
		}
	}
	
	public static Texture getInstance() {
		return text;
	}
	public static void main(String args[]) {
		new Window(800,600,"Platform Game", new Game());
	}
	
}
