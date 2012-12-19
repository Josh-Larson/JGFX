package com.dwlarson.joshua.jgfx;

import java.util.Vector;

import com.dwlarson.joshua.jgfx.drawable.polygons.JGFXQuad;
import com.dwlarson.joshua.jgfx.drawable.polygons.JGFXTriangle;

public class JGFXObject {
	/** Unique ID of this object */
	private int id;
	
	private Vector <JGFXTriangle> triangles = new Vector<JGFXTriangle>();
	private Vector <JGFXQuad>     quads     = new Vector<JGFXQuad>();
	
	
	
	/**
	 * Creates a blank object
	 */
	public JGFXObject() {
		id = JGFX.getNextID();
	}
	
	/**
	 * Draws the object using the static class GL11
	 */
	public void draw() {
		
	}
	
	public int getID() {
		return id;
	}
}
