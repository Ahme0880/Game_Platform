package com.rafay.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animations{
	
	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	private Boolean playAnimation = false;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	public Animations(int speed, BufferedImage... args) {
		this.speed = speed;
		images = new BufferedImage[args.length];
		for (int i = 0; i < args.length; i++) {
			images[i] = args[i];		
		}
		frames  = args.length;
		startAnimation();
	}
		public void runAnimation() {
			index++;
			if(index > speed && this.playAnimation) {
				index = 0;
				nextFrame();
				
			}
			
		}
		
		public void startAnimation() {
			this.playAnimation = true;
		}
		
		public void stopAnimation() {
			this.playAnimation = false;
		}
		private void nextFrame() {
			for (int i = 0; i < frames; i++) {
				if (count == i)
				    currentImg = images[i];
			}
			count++;
			
			if (count > frames)   //code repeats animation,once it has gone through
				count = 0;
		}
		
		public void drawAnimations(Graphics g, int x, int y, int i) {
			g.drawImage(currentImg, x, y, null);
			
		}
		
		public void drawAnimations(Graphics g, int x, int y, int scaleX, int scaleY) {
			g.drawImage(currentImg, x, y,scaleX , scaleY, null);
			
		}
	

}
