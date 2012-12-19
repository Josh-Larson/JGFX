package com.dwlarson.joshua.jgfx;

import java.util.Date;
import java.util.Vector;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import com.dwlarson.joshua.jgfx.drawable.polygons.IJGFXDrawable;

public class JGFX {
	
	/** Total Amount of Objects created */
	private static int objectsAmount = 0;
	private static boolean initialized = false;
	private static boolean isWireframe = false;
	private static Vector <IJGFXDrawable> objects = new Vector<IJGFXDrawable>();
	
	protected static boolean clearEveryFrame = true;
	protected static int frames = 0;
	protected static long lastFrame = 0;
	protected static int fps = 0;
	
	/**
	 * Initializes the render world
	 * @param title The title of the window
	 * @param width Width in pixels of the window
	 * @param height Height in pixels of the world
	 * @throws JGFXException Throws if the class is already initialized
	 * @throws LWJGLException If the window itself cannot be created
	 */
	public static void init(String title, int width, int height) throws JGFXException, LWJGLException {
		if (initialized)
			throw new JGFXException("JGFX Already Initialized");
		
		// Initialize Display
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setTitle(title);
		Display.create();
		
		// Initialize OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		initialized = true;
	}
	
	/**
	 * Frees up all memory, and ends rendering
	 */
	public static void destroy() {
		Display.destroy();
	}
	
	/**
	 * Adds the object to the render world
	 * @param obj Object to add
	 */
	public static void addObject(IJGFXDrawable obj) {
		if (objectExists(obj) != -1) 
			objects.set(objectExists(obj), obj);
		else
			objects.add(obj);
	}
	
	/**
	 * Removes the object from the render world
	 * @param obj Object to remove
	 */
	public static void removeObject(IJGFXDrawable obj) {
		if (objectExists(obj) != -1)
			objects.remove(objectExists(obj));
	}
	
	/**
	 * Returns true if the window is supposed to close
	 */
	public static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
	
	/**
	 * Renders the world
	 */
	public static void render() {
		if (JGFX.clearEveryFrame)
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		for (IJGFXDrawable obj : objects) {
			if (isWireframe)
				obj.drawWireframe();
			else
				obj.draw();
		}
		Display.update();
		if (new Date().getTime() - lastFrame >= 1000) {
			fps = frames;
			frames = 0;
			lastFrame = new Date().getTime();
		}
		frames++;
	}
	
	/**
	 * Sets the class to render using a wireframe of the objects
	 * @param wire True - wireframe enabled, False - wireframe disabled
	 */
	public static void setWireframe(boolean wire) {
		isWireframe = wire;
	}
	
	/**
	 * Returns the current frames per second the program
	 * is running at
	 */
	public static int getFPS() {
		return fps;
	}
	
	/**
	 * Gets the next ID for a drawable class to use, and
	 * increments the ID for the next class to call
	 */
	public static int getNextID() {
		return objectsAmount++;
	}
	
	/**
	 * Determines if the object specified in the arguments
	 * exists or not
	 * @return True if the object exists
	 */
	private static int objectExists(IJGFXDrawable object) {
		int id = -1;
		for (IJGFXDrawable obj : objects) {
			id++;
			if (obj.getID() == object.getID())
				return id;
		}
		return -1;
	}
}
