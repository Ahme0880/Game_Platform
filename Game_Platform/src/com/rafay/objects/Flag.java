package com.rafay.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.rafay.framework.GameObject;
import com.rafay.framework.ObjectId;

public class Flag extends GameObject{


	public Flag(int x, int y, ObjectId id) {
		// TODO Auto-generated constructor stub
		super(x, y, id);

	}


	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		g.fillRect((int)x,(int)y,16,16);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,32,32);
	}

}

