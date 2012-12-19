package com.dwlarson.joshua.jgfx;

import org.lwjgl.opengl.GL11;

public class JGFXColor {
	private int r;
	private int g;
	private int b;
	
	public JGFXColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void setColor() {
		GL11.glColor3f(r / 255f, g / 255f, b / 255f);
	}
}
