package com.rafay.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.LinkedList;

import com.rafay.framework.GameObject;
import com.rafay.framework.ObjectId;
import com.rafay.objects.Block;
import com.rafay.objects.Coin;
import com.rafay.objects.Enemy;
import com.rafay.objects.Flag;
import com.rafay.objects.Player;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;
	
	private Camera cam;
	private HUD hud;
	
	private BufferedImage level2 = null;
	
	public Handler(Camera cam) {
		this.cam = cam;
		hud = new HUD();
		BufferedImageLoader loader = new BufferedImageLoader();
		level2 = loader.loadImage("/level2.png"); //loads image
	}
	
	public void tick() {
		for(int i = 0; i< object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i< object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("width, height:"+ w + ", "+ h);
		
		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff ;
				int green = (pixel >> 8) & 0xff ;
				int blue = (pixel) & 0xff;
//				System.out.println(red);
//				System.out.println(green);
				
				if (red == 255 && green == 255 && blue == 255) addObject(new Block(xx *32, yy *32,0, ObjectId.Block));
				if (red == 128 && green == 128 && blue == 128) addObject(new Block(xx *32, yy *32,1, ObjectId.Block));
				if (red == 0 && green ==  0 && blue == 255)  addObject(new Player(xx *32,yy *32,this,hud, cam,ObjectId.Player));
				if (red == 178 && green ==  0 && blue == 255)  addObject(new Enemy(xx *32,yy *32,ObjectId.Enemy,this));
				if (red == 0 && green ==  255 && blue == 0) addObject(new Coin(xx *32,yy *32,2,ObjectId.Coin,this));
				if (red == 255 && green ==  216 && blue == 0) addObject(new Flag(xx *32,yy *32, ObjectId.Flag));	
			}
		}
	}
	
	public void switchLevel() {
		clearLevel();
		cam.setX(0);
	
		switch(Game.LEVEL){
		//level 1
		case 1:
			LoadImageLevel(level2);
			break;
		}

		Game.LEVEL++;
	}

	private void clearLevel() {
		object.clear();
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
//	public void createLevel() {
//		for(int yy = 0; yy < Game.HEIGHT +32;  yy += 32) {
//		   addObject(new Block(0,yy, ObjectId.Block));
//        
//	    for(int xx = 0; xx < Game.WIDTH * 2;  xx+= 32) 
//		    addObject(new Block(xx,Game.HEIGHT -32, ObjectId.Block));
//	    
//	    for(int xx = 200; xx< 600 ; xx+= 32) 
//		    addObject(new Block(xx,400, ObjectId.Block));
//		   
//		
//	}
  }

