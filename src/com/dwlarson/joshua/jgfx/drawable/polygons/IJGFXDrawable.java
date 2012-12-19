package com.dwlarson.joshua.jgfx.drawable.polygons;

public interface IJGFXDrawable {
	public int getID();
	public void draw();
	public void drawWireframe();
	public boolean is2Dimensional();
}
