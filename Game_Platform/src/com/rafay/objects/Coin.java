package com.rafay.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.rafay.framework.GameObject;
import com.rafay.framework.ObjectId;
import com.rafay.framework.Texture;
import com.rafay.window.Animations;
import com.rafay.window.Game;
import com.rafay.window.Handler;

public class Coin extends GameObject{

	Texture text = Game.getInstance();
	
	private Animations coin;
	private Handler handler;
	private float width = 32, height = 32;
     int score = 0;
	public Coin(float x, float y,int type,ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
//		falling = false;
        coin = new Animations(5,text.block[2],text.block[3],text.block[4], text.block[5],text.block[6]);
		// TODO Auto-generated constructor stub
	
	}


	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
	
		coin.runAnimation();
	}
	
	
	

	public void render(Graphics g) {
//		g.setColor(Color.yellow);
//		g.drawRect(( int)x, (int)y , 32,32);
		coin.drawAnimations(g,( int)x, (int)y , 32,32);


	   
	}
	
	
		
	

	public Rectangle getBounds() {
	return new Rectangle((int)x, (int)y, 32,32);
	}


	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int)x +(width/2)-((width/2)/2)), (int) ((int)y + ( height/2)),(int) width/2,(int) height/2);
		}


}
