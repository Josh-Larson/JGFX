package com.dwlarson.joshua.jgfx.drawable.io;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.dwlarson.joshua.jgfx.JGFX;
import com.dwlarson.joshua.jgfx.drawable.polygons.IJGFXDrawable;

public class JGFXImage implements IJGFXDrawable {
	
	private int id;
	private float x;
	private float y;
	private float z;
	private boolean twoDim;
	private String location = "";
	private Image img = null;
	
	public JGFXImage(String url) {
		setImage(url);
		this.x = 0;
		this.y = 0;
		this.z = 0;
		twoDim = true;
		id = JGFX.getNextID();
	}
	
	public JGFXImage(float x, float y, String url) {
		setImage(url);
		this.x = x;
		this.y = y;
		this.z = 0;
		twoDim = true;
		id = JGFX.getNextID();
	}
	
	public JGFXImage(float x, float y, float z, String url) {
		setImage(url);
		this.x = x;
		this.y = y;
		this.z = z;
		twoDim = false;
		id = JGFX.getNextID();
	}
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0;
		twoDim = true;
	}
	
	public void setLocation(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		twoDim = false;
	}
	
	public void setImage(String url) {
		location = url;
		try {
			img = ImageIO.read(new URL(location));
		} catch (IOException e) {
			return;
		}
	}
	
	public int getID() {
		return 0;
	}
	
	public void draw() {
		if (img != null) {
			
		}
	}
	
	public void drawWireframe() {
		draw();
	}
	
	public boolean is2Dimensional() {
		return twoDim;
	}
	
	
}
