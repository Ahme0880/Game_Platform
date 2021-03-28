package com.rafay.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	private static int score = 0;
	private static float health = 100;
	private static float greenVal = 255;
	
	
	public void tick() {
		health = Game.clamp((int)health, 0, 100);
		greenVal = health * 2;
		}
	
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Broadway", Font.BOLD,20));
		g.drawString("Score: "+ score , 40,40);
		
		g.setColor(Color.red);
		g.fillRect(7, 50, 200, 30);
		g.setColor(new Color(100,(int) greenVal, 0));
		g.fillRect(7, 50,(int)health *2 , 30);
	}

	public void setScore(int score) {
		this.score = score; 
	}
	
	public int getScore() {
		return score;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float f) {
		this.health = f;
	}


}
