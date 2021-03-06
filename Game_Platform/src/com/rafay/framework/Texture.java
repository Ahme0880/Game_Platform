package com.rafay.framework;

import java.awt.image.BufferedImage;

import com.rafay.window.BufferedImageLoader;

public class Texture {
	
	SpriteSheet bs, ps, es;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage enemy_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[8];
	public BufferedImage[] player = new  BufferedImage[14];
	public BufferedImage[] player_jump = new  BufferedImage[6];
    public  BufferedImage[] enemy = new  BufferedImage[12];

	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			enemy_sheet = loader.loadImage("/enemy_sheet.png");


		}catch(Exception e) {
			e.printStackTrace();
		}
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		es = new SpriteSheet(enemy_sheet);

		getTextures();
		
	}
	private void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32); //dirt block
		block[1] = bs.grabImage(2, 1, 32, 32); // grass block
		
		block[2] = bs.grabImage(5, 2, 32, 32); //coin block
		block[3] = bs.grabImage(4, 2, 32, 32); 
		block[4] = bs.grabImage(3, 2, 32, 32);
		block[5] = bs.grabImage(2, 2, 32, 32);
		block[6] = bs.grabImage(1, 2, 32, 32);
		block[7] = bs.grabImage(4,3,32,32);    //null block
		
		
		// player looks right
		player[0] = ps.grabImage(1,1,32,64); // frame for player
		player[1] = ps.grabImage(2,1,32,64); // frame for player
		player[2] = ps.grabImage(3,1,32,64); // frame for player
		player[3] = ps.grabImage(4,1,32,64); // frame for player
		player[4] = ps.grabImage(5,1,32,64); // frame for player
		player[5] = ps.grabImage(6,1,32,64); // frame for player
		player[6] = ps.grabImage(7,1,32,64); // frame for player
		
		
		//player looks left
		player[7] = ps.grabImage(20,1,32,64); // frame for player
		player[8] = ps.grabImage(19,1,32,64); // frame for player
		player[9] = ps.grabImage(18,1,32,64); // frame for player
		player[10] = ps.grabImage(17,1,32,64); // frame for player
		player[11] = ps.grabImage(16,1,32,64); // frame for player
		player[12] = ps.grabImage(15,1,32,64); // frame for player
		player[13] = ps.grabImage(14,1,32,64); // frame for player

	    //player jump
		player_jump[0] = ps.grabImage(8, 2, 32, 64);
		player_jump[1] = ps.grabImage(9, 2, 32, 64);
		player_jump[2] = ps.grabImage(10, 2, 32, 64);
		player_jump[3] = ps.grabImage(11, 2, 32, 64);
		player_jump[4] = ps.grabImage(12, 2, 32, 64);
		player_jump[5] = ps.grabImage(13, 2, 32, 64);
		
		//ememy face forward
		enemy[0] = es.grabImage(2,1,32,32);
		enemy[1] = es.grabImage(3, 1, 32, 32);
		enemy[2] = es.grabImage(4, 1, 32, 32);

		//enemy look right
		enemy[3] = es.grabImage(2,2,32,32);
		enemy[4] = es.grabImage(3, 2, 32, 32);
		enemy[5] = es.grabImage(4, 2, 32, 32);
		
		//enemy look left
		enemy[6] = es.grabImage(2,4,32,32);
		enemy[7] = es.grabImage(3, 4, 32, 32);
		enemy[8] = es.grabImage(4, 4, 32, 32);
		
		//enemy face backward
		enemy[9] = es.grabImage(2,3,32,32);
		enemy[10] = es.grabImage(3, 3, 32, 32);
		enemy[11] = es.grabImage(4, 3, 32, 32);
		
		
		
	}

}
