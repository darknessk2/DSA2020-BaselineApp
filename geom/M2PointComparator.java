package geom;

import java.util.Comparator;

public class M2PointComparator implements Comparator<Point2D>{
	private Point2D[] points;
	double comX = 0;
    double comY = 0;
    Point2D centerPoint;
	public M2PointComparator(Point2D[]points) {
		this.points = points;
		this.centerPoint = comPoint();
		System.out.println(centerPoint);
	}

	@Override
	public int compare(Point2D o1, Point2D o2) {
		// TODO Auto-generated method stub
		if(Math.abs(centerPoint.distancePoint(o1)-centerPoint.distancePoint(o2))<1e-7) return 0;
		else if(centerPoint.distancePoint(o1)<centerPoint.distancePoint(o2)) return -1;
		else return 0;
	}
	public Point2D comPoint() {
		for (int i = 0; i < points.length ; i++) {
	        comX += points[i].getX();
	        comY += points[i].getY();
	    }
	    comX /= points.length;
	    comY /= points.length;
	    Point2D centerP;
	    centerP = new Point2D(comX, comY);
	    return centerP;
	}
	
}
