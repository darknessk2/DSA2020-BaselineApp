package geom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Arrays;
import java.util.Collections;

public class Graph extends GeomObject {

	public enum GraphMode {LINE, SCATTER};
	protected Viewport viewport = null;
	public Viewport getViewport() {
		return viewport;
	}
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	//SETTER AND GETTER Of Mode
	public GraphMode getMode() {
		return mode;
	}
	public void setMode(GraphMode mode) {
		this.mode = mode;
	}

	private Point2D[] points = null;
	
	public Point2D[] getPoints() {
		return points;
	}
	public void setPoints(Point2D[] points) {
		this.points = points;
	}

	private GraphMode mode = GraphMode.LINE;

//setter and getter for mode
	public Graph(Point2D[] points, double xMin, double xMax, double yMin,double yMax){
		 this.viewport = new Viewport(xMin, xMax, yMin, yMax);
		 this.points = points;
		 }
	public Graph(Point2D[] points, double xMin, double xMax, double yMin,double yMax, Color color){
		 this.viewport = new Viewport(xMin, xMax, yMin, yMax);
		 this.points = points;
		 this.edgeColor = color;
		 }
		 
	private void copyPoints(Point2D[] points){
		 this.points = points;
		 Double DoubleX[] = null;
		 Double DoubleY[] = null; 
		 //update viewport
		 this.viewport = new Viewport(points[0].getX(), points[0].getX(), points[0].getY(), points[0].getY());
		 for(int idx=0; idx < points.length; idx++)
		 {this.viewport.addPoint(points[idx]);
//		 addX(points.length, DoubleX, points[idx].getX());
//		 addX(points.length, DoubleY, points[idx].getY());
		 }
//		 printRect(DoubleX, DoubleY, points.length);
}
	
	//Add point to draw Rectangle contains all points
	public static double[] addX(int n, Double arr[], double x ) {
		double newarr[] = new double[n+1];
		for (int i = 0; i< n ; i++) 
			newarr[i] = arr[i];
		newarr[n] = x;
		return newarr;
	}
	public static void printRect(Double X[],Double Y[], int n) {
		Double Xmax = Collections.max(Arrays.asList(X));
		Double Xmin = Collections.min(Arrays.asList(X));
		
		Double Ymax = Collections.max(Arrays.asList(Y));
		Double Ymin = Collections.min(Arrays.asList(Y));
		System.out.println("{"+Xmin + ","+Ymin+"}");
		System.out.println("{"+Xmin + ","+Ymax+"}");
		System.out.println("{"+Xmax + ","+Ymin+"}");
		System.out.println("{"+Xmax + ","+Ymax+"}");
	}
	
	public Graph(Point2D[] points){copyPoints(points); }
	public Graph(Point2D[] points, Color color){
			 copyPoints(points);
			 this.edgeColor = color;}

		 public static Graph sin(double a, double xMin, double xMax, double step){
			 int N = (int)((xMax - xMin)/step) + 1;
				Point2D[] points = new Point2D[N];
				double x = Math.toRadians(xMin);
				
				
				for(int i = 0; i < N; i++) {
					double y = Math.sin(a*x);
					points[i] = new Point2D(x,y);
					x+=step;
				}
				return new Graph (points);}
		 
	 public static Graph quadratic(double a, double b, double c, double xMin, double xMax, double step){
		 System.out.println("TRY TO DRAW QUADRATIC");
			int N = (int)((xMax - xMin)/step) + 1;
			Point2D[] points = new Point2D[N];
			double x = xMin;
			for(int idx = 0; idx < N; idx++){
			double y = a*x*x + b*x + c;
			points[idx] = new Point2D(x, y);
			System.out.println("Points in Qua"+points[idx]);
			 x += step;
			}
			return new Graph(points);}

	@Override
	public void draw(Graphics g, SpaceMapping mapper) {
	Graphics2D g2 = (Graphics2D) g;

	if(this.mode == GraphMode.LINE){
	if(this.points == null) return;
	
	//ADD POINT TO VIEWPORT
	for(int i =0; i<points.length; i++)
	{	System.out.println(points[i]);
		viewport.addPoint(points[i]);}
	
	int[] x = new int[this.points.length];
	int[] y = new int[this.points.length];
	System.out.println("Point length:"+points.length);
	
	for(int idx=0; idx < this.points.length; idx++){
		System.out.println("Point: "+points[idx]);
	Point2D devPoint = mapper.logic2Device(this.points[idx]);
	System.out.println("DevPoint: "+devPoint);
	x[idx] = (int)devPoint.getX();
	y[idx] = (int)devPoint.getY();
	System.out.println("X="+x[idx]+"\tY="+y[idx]);
	}
	
	g2.setColor(this.edgeColor);
	Stroke style = new BasicStroke(this.line_weight);
	g2.setStroke(style);
	g2.drawPolyline(x, y, x.length);
}
	else if(this.mode == GraphMode.SCATTER){
	for(int idx=0; idx < this.points.length; idx++){
		this.points[idx].setFaceColor(this.edgeColor);
		this.points[idx].draw(g, mapper);}
	
}
	else{
	throw new UnsupportedOperationException("Not supported yet.");}
}

}
