package com.dwlarson.joshua.jgfx.drawable.polygons;

import org.lwjgl.opengl.GL11;

import com.dwlarson.joshua.jgfx.JGFX;
import com.dwlarson.joshua.jgfx.JGFXColor;
import com.dwlarson.joshua.jgfx.JGFXException;

public class JGFXQuad implements IJGFXDrawable {
	private boolean initialized;
	private int id;
	private boolean twoDim = false;
	private JGFXPoint [] points = new JGFXPoint[4];
	private JGFXColor color;
	
	/**
	 * Creates a 4-point polygon from the points, and the color
	 * @param points 4 Points to plot
	 * @param color Color to draw with
	 * @throws JGFXException Throws if the points aren't valid
	 */
	public JGFXQuad(JGFXPoint [] points, JGFXColor color) throws JGFXException {
		initialized = false;
		this.color = color;
		setPosition(points);
		id = JGFX.getNextID();
		initialized = true;
	}
	
	/**
	 * Creates a 4-point polygon from the points, with the default
	 * color: white.
	 * @param points 4 Points to plot
	 * @throws JGFXException Throws if the points aren't valid
	 */
	public JGFXQuad(JGFXPoint [] points) throws JGFXException {
		this(points, new JGFXColor(255, 255, 255));
	}
	
	public JGFXQuad(int x, int y, int width, int height, JGFXColor color) {
		initialized = false;
		setColor(color);
		setPosition(x, y, width, height);
		id = JGFX.getNextID();
		initialized = true;
	}
	
	public JGFXQuad(int x, int y, int width, int height) {
		this(x, y, width, height, new JGFXColor(255, 255, 255));
	}
	
	public JGFXQuad(int x, int y, int side, JGFXColor color) {
		this(x, y, side, side, color);
	}
	
	public JGFXQuad(int x, int y, int side) {
		this(x, y, side, side, new JGFXColor(255, 255, 255));
	}
	
	public void setColor(JGFXColor color) {
		this.color = color;
	}
	
	public void setPosition(int x, int y, int width, int height) {
		points[0] = new JGFXPoint(x - width/2, y - height/2);
		points[1] = new JGFXPoint(x - width/2, y + height/2);
		points[2] = new JGFXPoint(x + width/2, y + height/2);
		points[3] = new JGFXPoint(x + width/2, y - height/2);
	}
	
	public void setPosition(JGFXPoint [] points) throws JGFXException {
		if (points.length < 4) return;
		for (int i = 0; i < 4; i++) {
			if (i == 0) twoDim = points[i].is2Dimensional();
			if (i >  0 && points[i].is2Dimensional() != twoDim)
				throw new JGFXException("Not All Points Specified are in same Dimension.");
			this.points[i] = points[i];
		}
	}
	
	public void setLocation(int x, int y, int side) {
		setPosition(x, y, side, side);
	}
	
	/**
	 * Returns the ID of the line. Used by JGFX to identify
	 * seperate objects
	 */
	public int getID() {
		return 0;
	}
	
	public void draw() {
		color.setColor();
		GL11.glBegin(GL11.GL_QUADS);
		if (twoDim) {
			GL11.glVertex2f(points[0].getX(), points[0].getY());
			GL11.glVertex2f(points[1].getX(), points[1].getY());
			GL11.glVertex2f(points[2].getX(), points[2].getY());
			GL11.glVertex2f(points[3].getX(), points[3].getY());
		} else {
			GL11.glVertex3f(points[0].getX(), points[0].getY(), points[0].getZ());
			GL11.glVertex3f(points[1].getX(), points[1].getY(), points[1].getZ());
			GL11.glVertex3f(points[2].getX(), points[2].getY(), points[2].getZ());
			GL11.glVertex3f(points[3].getX(), points[3].getY(), points[3].getZ());
		}
		GL11.glEnd();
	}
	
	public void drawWireframe() {
		new JGFXColor(50, 200, 50).setColor();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(points[0].getX(), points[0].getY());
		GL11.glVertex2f(points[1].getX(), points[1].getY());
		
		GL11.glVertex2f(points[1].getX(), points[1].getY());
		GL11.glVertex2f(points[2].getX(), points[2].getY());
		
		GL11.glVertex2f(points[2].getX(), points[2].getY());
		GL11.glVertex2f(points[3].getX(), points[3].getY());
		
		GL11.glVertex2f(points[3].getX(), points[3].getY());
		GL11.glVertex2f(points[0].getX(), points[0].getY());
		GL11.glEnd();
	}
	
	public boolean is2Dimensional() {
		return twoDim;
	}
}
