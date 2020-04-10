package geom;
import java.util.Comparator;
public class O2PointComparator implements Comparator<Point2D> {

	@Override
	public int compare(Point2D o1, Point2D o2) {
		Point2D o = new Point2D(0,0);
		if(Math.abs(o.distancePoint(o1)-o.distancePoint(o2)) <1e-7) return 0;
		else if(o.distancePoint(o1)<o.distancePoint(o2)) return -1;
		else return 1;
	}
}
