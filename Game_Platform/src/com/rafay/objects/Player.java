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
import com.rafay.window.Camera;
import com.rafay.window.Game;
import com.rafay.window.HUD;
import com.rafay.window.Handler;

public class Player extends GameObject{
	
	private float width = 32, height = 64;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	private boolean Alive;
	//private int facing = 1;
	// 1 = right 
	// -1 = left
	private Handler handler;
	private HUD hud;
	private Camera cam;

	Texture text = Game.getInstance();
	
	private Animations playerWalkRight, playerWalkLeft;
//	int score = 0;
	public Player(float x, float y,Handler handler,HUD hud,Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		this.hud = hud;
		playerWalkRight = new Animations(5,text.player[1],text.player[2],text.player[3],text.player[4],text.player[5],text.player[6]);
		playerWalkLeft = new Animations(5,text.player[8],text.player[9],text.player[10],text.player[11],text.player[12],text.player[13]);
	}


	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		if (velX < 0) facing = -1;
		else if(velX > 0)
			   facing = 1;
		
		if(falling || jumping) 
		  velY += gravity;
		  
		  if(velY > MAX_SPEED) {
			  velY = MAX_SPEED;
		  }
		  Collision(object);
		  
		  playerWalkRight.runAnimation();
		  playerWalkLeft.runAnimation();
		}
		
	

	
	private void Collision(LinkedList<GameObject>object) {
		for(int i = 0; i< handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)
			  {
		     	if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() + 35;
					velY = 0;
				}
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}else {
					falling = true;
				}
				//Right
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
							
			       }
				
				//Left
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + 32;

				}
				
				
			}else if (tempObject.getId() == ObjectId.Flag) {
				//switch level
				if (getBounds().intersects(tempObject.getBounds())) {
				handler.switchLevel();
				}
			}else if (tempObject.getId() == ObjectId.Enemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					hud.setHealth(hud.getHealth()- 1);
//					if (hud.setHealth(hud.getHealth() == 0)){
//						handler.removeObject(tempObject);
						
				}
				
			} else if (tempObject.getId() == ObjectId.Coin) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
//					hud.setHealth(hud.getHealth() - 10);
				    hud.setScore(hud.getScore()+ 1);
				    

              
			       
				}
				}
			
		     }	
			}
	    
		
			
	

	public void render(Graphics g) {
		// TODO Auto-generated method stub
//		g.setColor(Color.blue);
//		g.drawRect((int)x,(int) y, 32, 32);
		 //player jumping
//		g.setColor(Color.WHITE);
//		g.setFont(new Font("Broadway", Font.BOLD,20));
//		g.drawString("Score: "+ score , 40,40);		
		
		
		if (jumping) {
			if (facing == 1)
				g.drawImage(text.player_jump[2],(int)x,(int)y,48,64, null);
			else if (facing == -1)
				g.drawImage(text.player_jump[3],(int)x,(int)y,48,64, null);
		}else {
			//player running
			if (velX != 0) {
				if ( facing == 1) {
				playerWalkRight.drawAnimations(g,(int)x,(int) y, 48, 64);
				}else if (facing == -1)
					playerWalkLeft.drawAnimations(g,(int)x,(int) y, 48, 64);
			}else 
				//player standing 
				if (facing == 1)
			g.drawImage(text.player[0],(int)x,(int) y,48,64, null);
				else if (facing == -1)
				g.drawImage(text.player[7],(int)x,(int) y,48,64, null);
			
			}
		}

		
    public boolean isAlive() {
    	return Alive;
    }
	


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
	

}
