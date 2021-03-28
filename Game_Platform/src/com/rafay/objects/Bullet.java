package com.rafay.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.rafay.framework.GameObject;
import com.rafay.framework.ObjectId;
import com.rafay.window.Handler;

public class Bullet extends GameObject{
	private Handler handler;


	public Bullet(float x, float y, ObjectId id, int velX, Handler handler) {
		super(x, y, id);
		this.velX = velX;
		this.handler = handler;

		// TODO Auto-generated constructor stub
	}


	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x += velX;
		Collision(object);
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y,16,16);
		
	}

	
	private void Collision(LinkedList<GameObject>object) {
		for(int i = 0; i< handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
				}
		}
		}
			
		}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,16,16);
	}

}
