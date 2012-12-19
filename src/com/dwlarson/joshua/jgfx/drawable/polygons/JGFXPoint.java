package com.dwlarson.joshua.jgfx.drawable.polygons;

import org.lwjgl.opengl.GL11;

import com.dwlarson.joshua.jgfx.JGFX;
import com.dwlarson.joshua.jgfx.JGFXColor;

public class JGFXPoint implements IJGFXDrawable {
	private int id;
	private float x;
	private float y;
	private float z;
	private JGFXColor color;
	private boolean twoDim;
	
	public JGFXPoint(float x, float y, JGFXColor color) {
		this.id = JGFX.getNextID();
		this.color = color;
		this.x = x;
		this.y = y;
		this.z = 0;
		this.twoDim = true;
	}
	
	public JGFXPoint(float x, float y, float z, JGFXColor color) {
		this.id = JGFX.getNextID();
		this.color = color;
		this.x = x;
		this.y = y;
		this.z = z;
		this.twoDim = false;
	}
	
	public JGFXPoint(float x, float y) {
		this(x, y, new JGFXColor(255, 255, 255));
	}
	
	public JGFXPoint(float x, float y, float z) {
		this(x, y, z, new JGFXColor(255, 255, 255));
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void draw() {
		color.setColor();
		GL11.glBegin(GL11.GL_POINT);
		if (twoDim)
			GL11.glVertex2f(x, y);
		else
			GL11.glVertex3f(x, y, z);
		GL11.glEnd();
	}
	
	public void drawWireframe() {
		draw();
	}
	
	/**
	 * Returns the ID of the line. Used by JGFX to identify
	 * seperate objects
	 */
	public int getID() {
		return id;
	}
	
	public boolean is2Dimensional() {
		return twoDim;
	}
}
