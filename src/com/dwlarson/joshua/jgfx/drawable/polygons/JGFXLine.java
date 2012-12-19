package com.dwlarson.joshua.jgfx.drawable.polygons;

import org.lwjgl.opengl.GL11;

import com.dwlarson.joshua.jgfx.JGFX;
import com.dwlarson.joshua.jgfx.JGFXColor;
import com.dwlarson.joshua.jgfx.JGFXException;

public class JGFXLine implements IJGFXDrawable {
	
	private boolean initialized;
	private int id;
	private boolean twoDim = false;
	private JGFXPoint [] points = new JGFXPoint[2];
	private JGFXColor color;
	
	/**
	 * Creates a 2D or 3D line with 2 points, and a color
	 * @param points 2 Points to use
	 * @param color Color to draw with
	 * @throws JGFXException Throws if points are invalid
	 */
	public JGFXLine(JGFXPoint [] points, JGFXColor color) throws JGFXException {
		initialized = false;
		this.color = color;
		if (points.length < 2) return;
		for (int i = 0; i < 2; i++) {
			if (i == 0) twoDim = points[i].is2Dimensional();
			if (i == 1 && points[i].is2Dimensional() != twoDim)
				throw new JGFXException("Not All Points Specified are in same Dimension.");
			this.points[i] = points[i];
		}
		id = JGFX.getNextID();
		initialized = true;
	}
	
	/**
	 * Creates a 2D or 3D line with 2 points, and the default color:
	 * white.
	 * @param points 2 Points to use
	 * @throws JGFXException
	 */
	public JGFXLine(JGFXPoint [] points) throws JGFXException {
		this(points, new JGFXColor(255, 255, 255));
	}
	
	/**
	 * Creates a line from the arguments, and the color
	 * @param x1 Starting X coordinate
	 * @param y1 Starting Y coordinate
	 * @param x2 Ending X coordinate
	 * @param y2 Ending Y coordinate
	 * @param color Color to draw with
	 */
	public JGFXLine(float x1, float y1, float x2, float y2, JGFXColor color) {
		initialized = false;
		this.color = color;
		points[0] = new JGFXPoint(x1, y1);
		points[1] = new JGFXPoint(x2, y2);
		twoDim = true;
		id = JGFX.getNextID();
		initialized = true;
	}
	
	/**
	 * Creates a line with the points specified, and the
	 * default color: white.
	 * @param x1 Starting X coordinate
	 * @param y1 Starting Y coordinate
	 * @param x2 Ending X coordinate
	 * @param y2 Ending Y coordinate
	 */
	public JGFXLine(float x1, float y1, float x2, float y2) {
		this(x1, y1, x2, y2, new JGFXColor(255, 255, 255));
	}
	
	/**
	 * Creates a 3D line with the points specified, and the color
	 * @param x1 Starting X coordinate
	 * @param y1 Starting Y coordinate
	 * @param z1 Starting Z coordinate
	 * @param x2 Ending X coordinate
	 * @param y2 Ending Y coordinate
	 * @param z2 Ending Z coordinate
	 * @param color Color to draw with
	 */
	public JGFXLine(float x1, float y1, float z1, float x2, float y2, float z2, JGFXColor color) {
		initialized = false;
		this.color = color;
		points[0] = new JGFXPoint(x1, y1, z1);
		points[1] = new JGFXPoint(x2, y2, z2);
		twoDim = false;
		id = JGFX.getNextID();
		initialized = true;
	}
	
	/**
	 * Creates a 3D line with the points specified, and the
	 * default color: white.
	 * @param x1 Starting X coordinate
	 * @param y1 Starting Y coordinate
	 * @param z1 Starting Z coordinate
	 * @param x2 Ending X coordinate
	 * @param y2 Ending Y coordinate
	 * @param z2 Ending Z coordinate
	 */
	public JGFXLine(float x1, float y1, float z1, float x2, float y2, float z2) {
		this(x1, y1, z1, x2, y2, z2, new JGFXColor(255, 255, 255));
	}
	
	/**
	 * Returns the ID of the line. Used by JGFX to identify
	 * seperate objects
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Draws the line on the screen, in either 2D or 3D
	 */
	public void draw() {
		color.setColor();
		GL11.glBegin(GL11.GL_LINE);
		if (twoDim) {
			GL11.glVertex2f(points[0].getX(), points[0].getY());
			GL11.glVertex2f(points[1].getX(), points[1].getY());
		} else {
			GL11.glVertex3f(points[0].getX(), points[0].getY(), points[0].getZ());
			GL11.glVertex3f(points[1].getX(), points[1].getY(), points[1].getZ());
		}
		GL11.glEnd();
	}
	
	/**
	 * Draws the wireframe of the line in a green color
	 */
	public void drawWireframe() {
		new JGFXColor(50, 200, 50).setColor();
		GL11.glBegin(GL11.GL_LINE);
		if (twoDim) {
			GL11.glVertex2f(points[0].getX(), points[0].getY());
			GL11.glVertex2f(points[1].getX(), points[1].getY());
		} else {
			GL11.glVertex3f(points[0].getX(), points[0].getY(), points[0].getZ());
			GL11.glVertex3f(points[1].getX(), points[1].getY(), points[1].getZ());
		}
		GL11.glEnd();
	}
	
	/**
	 * Returns if the line is 2 Dimensional
	 */
	public boolean is2Dimensional() {
		return twoDim;
	}

}
