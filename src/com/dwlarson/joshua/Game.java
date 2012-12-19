package com.dwlarson.joshua;

import java.util.Date;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.dwlarson.joshua.jgfx.JGFX;
import com.dwlarson.joshua.jgfx.JGFXColor;
import com.dwlarson.joshua.jgfx.drawable.polygons.JGFXPoint;
import com.dwlarson.joshua.jgfx.drawable.polygons.JGFXQuad;
import com.dwlarson.joshua.jgfx.drawable.polygons.JGFXTriangle;

public class Game {
	public void start() throws Exception {
		JGFX.init("Josh's MMORPG", 480, 640);
		//JGFX.setWireframe(true);
		//JGFXQuad rect = new JGFXQuad(200, 200, 30);
		JGFXTriangle tri = new JGFXTriangle(200, 200, 30);
		JGFXPoint pt  = new JGFXPoint(225, 225, new JGFXColor(255, 0, 0));
		JGFX.addObject(tri);
		while (!JGFX.isCloseRequested()) {
			//rect.setPosition(Mouse.getX() + Mouse.getDX(), Mouse.getY() + Mouse.getDY(), 30);
			tri.setPosition(Mouse.getX() + Mouse.getDX(), Mouse.getY() + Mouse.getDY(), 30);
			pt.setPosition(Mouse.getX() + Mouse.getDX(), Mouse.getY() + Mouse.getDY() + 40);
			JGFX.render();
			
			//try { Thread.sleep(1000 / 40); } catch (InterruptedException e) {}
		}
		
		JGFX.destroy();
	}
	
	public static void main(String [] args) throws Exception {
		Game g = new Game();
		g.start();
	}
}
