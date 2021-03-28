package com.rafay.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.rafay.framework.GameObject;
import com.rafay.framework.ObjectId;
import com.rafay.framework.Texture;
import com.rafay.window.Game;

public class Block extends GameObject{

	Texture text = Game.getInstance();
	private int type;
	public Block(float x, float y,int type,ObjectId id) {
		super(x, y, id);
		this.type = type;
		// TODO Auto-generated constructor stub
	}


	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}


	public void render(Graphics g) {
		
	    if( type == 0) {   //dirt block
	    	g.drawImage(text.block[0],(int) x,(int) y, null);
	    }
	    if( type == 1) {   //grass block
	    	g.drawImage(text.block[1],(int) x,(int) y, null);
	    }
		
	}



	public Rectangle getBounds() {
	return new Rectangle((int)x, (int)y, 32,32);
	}


}
