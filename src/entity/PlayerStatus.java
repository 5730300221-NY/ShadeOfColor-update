package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import render.IRenderable;

public class PlayerStatus implements IRenderable {
	private Sheep sheep = new Sheep();
	private static boolean win;
	private static int roundstate;
	
	public PlayerStatus() {
		this.win=false;
		this.roundstate=16;
	}
	
	public static  boolean isWin() {
		return win;
	}
	
	public Sheep getSheep() {
		return sheep;
	}

	public void setSheep(Sheep sheep) {
		this.sheep = sheep;
	}

	public static int getRoundstate() {
		return roundstate;
	}

	public static void setRoundstate(int roundstate) {
		PlayerStatus.roundstate = roundstate;
	}

	public static void setWin(boolean win) {
		PlayerStatus.win = win;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setFont(new Font("Tahoma", Font.BOLD, 40));
		g2d.setColor(Color.WHITE);
		g2d.drawString(sheep.getScore() + "", 1080, 70);
		g2d.drawString("Round : "+this.getRoundstate() + "", 550, 70);

	}

	@Override
	public boolean IsVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}
}
