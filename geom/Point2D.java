package geom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Point2D extends GeomObject {
	public static int POINT_HALF_SIZE = 2;
	private double x, y;
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Point2D() {
		this.x = 0;
		this.y = 0;
	}
	public Point2D(Point2D p) {
		x = p.x;
		y = p.y;
	}
	public Point2D Clone(){
	   return new Point2D(this.x, this.y);}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public String toString() {//return String.format("(%.1f, %.1f)",x,y);
	return String.format("P(%6.2f, %6.2f)", this.x, this.y);}
	
	public static Point2D[] linear(int N, double a, double b, double xMin,double xMax){
			Point2D[] list = new Point2D[N];
			double step = (xMax - xMin)/(N-1); //xMax inclusive
			double x = xMin;
			for(int idx=0; idx < N; idx++){
			double y = a*x + b;
			list[idx] = new Point2D(x,y);
			 x += step;}
			
			return list;
			}
	
	public static Point2D[] linearVer(int N, double a, double b, double yMin,double yMax){
		Point2D[] list = new Point2D[N];
		double step = (yMax - yMin)/(N-1); //xMax inclusive
		double y = yMin;
		for(int idy=0; idy < N; idy++){
		list[idy] = new Point2D(0,y);
		 y += step;}
		
		return list;
		}
	
	public static Point2D[] linearVerVal(int N, double a, double b, double yMin,double yMax){
		Point2D[] list = new Point2D[N];
		double step = (yMax - yMin)/(N-1); //xMax inclusive
		double y = yMin;
		for(int idy=0; idy < N; idy++){
		list[idy] = new Point2D(-0.015,y);
		 y += step;}
		
		return list;
		}
	
	public static Point2D[] generateList(int n, double from, double to){
        Point2D[] pointList = new Point2D[n];
        for (int i = 0; i < n; i++) {
             from = -10;
             to = 10;

            double rand_x = (Math.random() * ((to - from) + 1)) + from;
            double rand_y = (Math.random() * ((to - from) + 1)) + from;
            pointList[i] = new Point2D(rand_x,rand_y);

        }
        return pointList;
    }

	
	public static double distanceAB(Point2D a, Point2D b) {
		double distance;
		distance = Math.sqrt(Math.pow(b.getX() - a.getX(),2) + Math.pow(b.getY()-a.getY(), 2));
//		System.out.printf("Distance of 2 point is: %f\n",distance);
		return distance;
	}
	 public double distancePoint( Point2D a){
	        return distanceAB(a,this);
	    }
	 
	@Override
	public void draw(Graphics g, SpaceMapping mapper) {
//	 int x = (int) getX();
//	 int y = (int) getY();
//	g.drawOval(x,y, 4, 4);
		Graphics2D g2 = (Graphics2D) g;
		Point2D point = mapper.logic2Device(this.getX(), this.getY());
		
		int x = (int)point.getX() - POINT_HALF_SIZE;
		int y = (int)point.getY() - POINT_HALF_SIZE;
		g2.setColor(this.faceColor);
//		g2.fillRect(x, y, 20, 20);
		g2.fillOval(x, y, 5, 5);
	}
	
	
	public void draw2(Graphics g, SpaceMapping mapper) {
			Graphics2D g2 = (Graphics2D) g;
			Point2D point = mapper.logic2Device(this.getX(), this.getY());
			int x = (int)point.getX() - POINT_HALF_SIZE;
			int y = (int)point.getY() - POINT_HALF_SIZE;
			g2.setColor(this.faceColor);
			g2.fillRect(x, y, 1, 15);
//			g2.fillOval(x, y, 5, 5);
		}
	public void draw3(Graphics g, SpaceMapping mapper) {
		Graphics2D g2 = (Graphics2D) g;
		Point2D point = mapper.logic2Device(this.getX(), this.getY());
		int x = (int)point.getX() - POINT_HALF_SIZE;
		int y = (int)point.getY() - POINT_HALF_SIZE;
		g2.setColor(this.faceColor);
		g2.fillRect(x, y, 15, 1);

	}
	

}
