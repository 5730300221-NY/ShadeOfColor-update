package render;

import java.awt.Graphics2D;

public class GameBackground implements IRenderable {
	
	public GameBackground(){
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(Resource.ScreenBG,0,0,null);
		
	}

	@Override
	public boolean IsVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}
			
}
