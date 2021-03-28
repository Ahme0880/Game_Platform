package com.rafay.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


import com.rafay.framework.GameObject;
import com.rafay.framework.ObjectId;
import com.rafay.framework.Texture;
import com.rafay.window.Animations;
import com.rafay.window.Game;
import com.rafay.window.Handler;

public class Enemy extends GameObject{

	 Texture text = Game.getInstance();
	 float distance, dif_x,dif_y;
	 private int hp = 50;
	 private Handler handler;
	 private Animations enemyfront, enemyright, enemyleft, enemyback;
	 
	 
	 private float width = 32, height = 32;
	public Enemy(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		falling = false;
		enemyfront = new Animations(8, text.enemy[0], text.enemy[1],text.enemy[2]);
		enemyright = new Animations(8, text.enemy[3], text.enemy[4],text.enemy[5]);
		enemyleft= new Animations(8, text.enemy[6], text.enemy[7],text.enemy[8]);
		enemyback = new Animations(8, text.enemy[9], text.enemy[10],text.enemy[11]);
		// TODO Auto-generated constructor stub
	}


	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x+= velX;
		y += velY;
		
		for(int i = 0;i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Player){
				dif_x = x - tempObject.getX() - width;
				dif_y = y - tempObject.getY() - height;
				
			    distance = (float) Math.sqrt((x - tempObject.getX()) * (x - tempObject.getX()) 
			    		+ (y - tempObject.getY()) * (y - tempObject.getY()));
			}
		}	
		
		if (distance < 300) {
		velX = -((1/ distance) * dif_x);
		velY = -((1/distance) * dif_y);
		}else {
			velX = 0;
			velY = 0;
		}

		enemyfront.runAnimation();
		enemyright.runAnimation();
		enemyleft.runAnimation();
		enemyback.runAnimation();
		
		collision(object);
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Player) {
				if (velY == 0 && velX == 0) {
					enemyfront.drawAnimations(g,(int) x,(int) y, 0);
				}else if(Math.abs(tempObject.getX() - x) > Math.abs(tempObject.getY() - y));
				  if (velX >0) {
				enemyright.drawAnimations(g,(int) x,(int) y, 0);
				  }else if (velX < 0) {
					 enemyleft.drawAnimations(g,(int) x,(int) y, 0);
				  }
			    }else if (Math.abs(tempObject.getY() - y) > Math.abs(tempObject.getX() - x)) {
			    	if (velY > 0) {
			    		 enemyfront.drawAnimations(g,(int) x,(int) y, 0);
			    	} else if (velY <0) {
			    		enemyback.drawAnimations(g,(int) x,(int) y, 0);
			    	}
			    }
			}
		}

	private void collision(LinkedList<GameObject>object) {
		for(int i = 0; i< handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
		     	if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() + 32;
		
				
				}
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height;
		
				}	
				//Right
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
							
			       }
				
				//Left
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + 32;

				}
			  }
		}
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int)x +(width/2)-((width/2)/2)), (int) ((int)y + ( height/2)),(int) width/2,(int) height/2);
	}
	
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y + 5,(int) 5,(int) height-10);
	}
	
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int)x+ width -5), (int)y + 5,(int) 5,(int) height - 10);
	}
	
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int)x + (width/2)-((width/2)/2)), (int)y,(int) width /2,(int) height/2);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int)x + (width/2)-((width/2)/2)), (int)y + (int)(height / 2),(int) width /2,(int) height/2);
	}
}
