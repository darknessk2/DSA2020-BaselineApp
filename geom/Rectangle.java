package geom;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Arrays;
import java.util.Collections;

import dsa2020.WorkingPanel;
import geom.Graph.GraphMode;

public class Rectangle extends GeomObject {
	public enum RectangleMode{
		LINE,
		SCATTER
	};
	protected Viewport viewport = null;
	private Point2D[] points = null;
	
	public Point2D[] getPoints() {
		return points;
	}

	public void setPoints(Point2D[] points) {
		this.points = points;
	}
	
/*	public Point2D[] updatePoint(Point2D[] pL) {
		this.points = Arrays.copyOf(pL, pL.length);
		return points;
	}*/
	
	private RectangleMode mode = RectangleMode.LINE;
	private static int lengthOfRec;
	private static int widthOfRec;
	
	public Rectangle(Point2D[] points, double xMin, double xMax, double yMin,double yMax){
		 this.viewport = new Viewport(xMin, xMax, yMin, yMax);
		 this.points = points;
		 }
	
	private void copyPoints(Point2D[] points){
		 this.points = points;
		 Double DoubleX[] = null; //= points[0].getX();
		 Double DoubleY[] = null; //= point[0].getY();
		 
		 //update viewport
		 this.viewport = new Viewport(points[0].getX(), points[0].getX(), points[0].getY(), points[0].getY());
		 for(int idx=0; idx < points.length; idx++)
		 {this.viewport.addPoint(points[idx]);
//		 addX(points.length, DoubleX, points[idx].getX());
//		 addX(points.length, DoubleY, points[idx].getY());
		 }
//		 printRect(DoubleX, DoubleY, points.length);
		 
		 
		 }

	@Override
	public void draw(Graphics g, SpaceMapping mapper) {
		// TODO Auto-generated method stub
		System.out.println("CALLL TO RECTANGLE");
		Graphics2D g2 = (Graphics2D) g;

	if(this.mode == RectangleMode.LINE){
		if(this.points == null) {
			return;}
		System.out.println("Working.state: "+WorkingPanel.state);

		
		//ADD POINT TO VIEWPORT
		for(int i =0; i<points.length; i++)
		{	System.out.println(points[i]);
			viewport.addPoint(points[i]);}
		
		//UPDATE LIST POINT AND DRAW REC CONTAIN ALL POINT
		int x = (int)viewport.getxMin();
		if(points[0].getX()!=0) 
			{System.out.println(points[0].getX());
			this.viewport.setxMin(points[0].getX());
			for(int i = 0; i<points.length; i++) {
			if(points[i].getX()<viewport.getxMin()) this.viewport.setxMin(points[i].getX());
			x = (int)viewport.getxMin();}}
		
		System.out.println("X: "+x);
		
		int y = (int)viewport.getyMin();
		if(points[0].getY()!=0) 
		{System.out.println(points[0].getY());
		this.viewport.setyMin(points[0].getY());
		for(int i = 0; i<points.length; i++) {
			if(points[i].getY()<viewport.getyMin()) this.viewport.setyMin(points[i].getY());
		y = (int)viewport.getyMin();}}
		
		System.out.println("Y: "+y);
		
		for(int i =0; i<points.length; i++) {
			if(points[i].getX()>viewport.getxMax()) this.viewport.setxMax(points[i].getX());
			if(points[i].getY()>viewport.getyMax()) this.viewport.setyMax(points[i].getY());
		}
		
		int width = (int)(viewport.getxMax()-viewport.getxMin());
		int height = (int)(viewport.getyMax()-viewport.getyMin());
		System.out.println("Width: "+width);
		System.out.println("Height: "+height);
	

		g2.setColor(this.edgeColor);
		Stroke style = new BasicStroke(this.line_weight);
		g2.setStroke(style);
		g2.drawRect(x, y, width, height);

		
	}
		
	}

}
