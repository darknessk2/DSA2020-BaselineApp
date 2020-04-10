package geom;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GeomObject {
	protected Color edgeColor;
	
	protected Color faceColor;
	
	protected int line_weight = 1;
	public GeomObject() {
		this.faceColor = Color.RED;
		this.edgeColor = new Color(20,200,20);
	}
	
	
	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Color getFaceColor() {
		return faceColor;
	}

	public void setFaceColor(Color faceColor) {
		this.faceColor = faceColor;
	}

	public int getLine_weight() {
		return line_weight;
	}

	public void setLine_weight(int line_weight) {
		this.line_weight = line_weight;
	}

	public abstract void draw(Graphics g, SpaceMapping mapper);
}
