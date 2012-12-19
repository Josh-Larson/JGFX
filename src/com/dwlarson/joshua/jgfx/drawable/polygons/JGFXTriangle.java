package com.dwlarson.joshua.jgfx.drawable.polygons;

import org.lwjgl.opengl.GL11;

import com.dwlarson.joshua.jgfx.JGFX;
import com.dwlarson.joshua.jgfx.JGFXColor;
import com.dwlarson.joshua.jgfx.JGFXException;

public class JGFXTriangle implements IJGFXDrawable {
	private boolean initialized;
	private int id;
	private boolean twoDim = false;
	private JGFXPoint [] points = new JGFXPoint[3];
	private JGFXColor color;
	
	public JGFXTriangle(JGFXPoint [] points, JGFXColor color) throws JGFXException {
		initialized = false;
		this.color = color;
		setPosition(points);
		id = JGFX.getNextID();
		initialized = true;
	}
	
	public JGFXTriangle(JGFXPoint [] points) throws JGFXException {
		this(points, new JGFXColor(255, 255, 255));
	}
	
	public JGFXTriangle(int centerX, int centerY, int side, JGFXColor color) {
		initialized = false;
		this.color = color;
		setPosition(centerX, centerY, side);
		id = JGFX.getNextID();
		initialized = true;
	}
	
	public JGFXTriangle(int centerX, int centerY, int side) {
		this(centerX, centerY, side, new JGFXColor(255, 255, 255));
	}
	
	public void setPosition(int centerX, int centerY, int side) {
		float r = 0.5f * side / 0.8660254038f;
		points[0] = new JGFXPoint(centerX, centerY + r);
		points[1] = new JGFXPoint(centerX + 0.8660254038f * r, centerY - .5f * r);
		points[2] = new JGFXPoint(centerX - 0.8660254038f * r, centerY - .5f * r);
	}
	
	public void setPosition(JGFXPoint [] points) throws JGFXException {
		if (points.length < 3) return;
		for (int i = 0; i < 3; i++) {
			if (i == 0) twoDim = points[i].is2Dimensional();
			if (i >  0 && points[i].is2Dimensional() != twoDim)
				throw new JGFXException("Not All Points Specified are in same Dimension.");
			this.points[i] = points[i];
		}
	}
	
	/**
	 * Returns the ID of the line. Used by JGFX to identify
	 * seperate objects
	 */
	public int getID() {
		return 0;
	}
	
	/**
	 * Draws the triangle on the screen in 2D or 3D
	 */
	public void draw() {
		color.setColor();
		GL11.glBegin(GL11.GL_TRIANGLES);
		if (twoDim) {
			GL11.glVertex2f(points[0].getX(), points[0].getY());
			GL11.glVertex2f(points[1].getX(), points[1].getY());
			GL11.glVertex2f(points[2].getX(), points[2].getY());
		} else {
			GL11.glVertex3f(points[0].getX(), points[0].getY(), points[0].getZ());
			GL11.glVertex3f(points[1].getX(), points[1].getY(), points[1].getZ());
			GL11.glVertex3f(points[2].getX(), points[2].getY(), points[2].getZ());
		}
		GL11.glEnd();
	}
	
	/**
	 * Draws the wireframe of the object in a green color
	 */
	public void drawWireframe() {
		new JGFXColor(50, 200, 50).setColor();
		GL11.glBegin(GL11.GL_LINES);
		if (twoDim) {
			GL11.glVertex2f(points[0].getX(), points[0].getY());
			GL11.glVertex2f(points[1].getX(), points[1].getY());
			
			GL11.glVertex2f(points[1].getX(), points[1].getY());
			GL11.glVertex2f(points[2].getX(), points[2].getY());
			
			GL11.glVertex2f(points[2].getX(), points[2].getY());
			GL11.glVertex2f(points[0].getX(), points[0].getY());
		} else {
			GL11.glVertex3f(points[0].getX(), points[0].getY(), points[0].getZ());
			GL11.glVertex3f(points[1].getX(), points[1].getY(), points[1].getZ());
			
			GL11.glVertex3f(points[1].getX(), points[1].getY(), points[1].getZ());
			GL11.glVertex3f(points[2].getX(), points[2].getY(), points[2].getZ());
			
			GL11.glVertex3f(points[2].getX(), points[2].getY(), points[2].getZ());
			GL11.glVertex3f(points[0].getX(), points[0].getY(), points[0].getZ());
		}
		GL11.glEnd();
	}
	
	public boolean is2Dimensional() {
		return twoDim;
	}
}
