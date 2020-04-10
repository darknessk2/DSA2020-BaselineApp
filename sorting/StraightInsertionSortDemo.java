package sorting;
import geom.M2PointComparator;
import geom.O2PointComparator;
import geom.Point2D;
import geom.PointComparator;

public class StraightInsertionSortDemo {
//	private enum directionSort {Ascending, Descending};
	//private directionSort mode = Descending;
	public static void demo() {
//		allSortDemo();
//		SegmentSortDemo();
//		ShellSortDemo();
//		SelectionDemo();
//		BubbleSortDemo();
		InsertionDemo();
//		question2();

	}
	public static void InsertionDemo() {
		int N = 30;
		Point2D[] points = Point2D.generateList(N, -10, 20);

		//Print points
		System.out.println("DEMO FOR INSERTION SORT:");
		System.out.println(new String(new char[80]).replace('\0', '='));
		System.out.println("Unsorted list points:");
		System.out.println(new String(new char[80]).replace('\0', '='));
		for(int idx=0; idx < N; idx++){
			String line = String.format("%3d | %s", idx, points[idx]);
			System.out.println(line);
 }
 
 	//Sort: insertion sort
		
 	StraightInsertionSort<Point2D> sortAlg = new StraightInsertionSort<>();
 	//TEST M2PointComparator and O2PointComparator
// 	sortAlg.sort(points, new M2PointComparator(points));
 	
 	//CHANGE DIRECTION MODE
 	sortAlg.setMode(StraightInsertionSort.directionSort.Descending);
	sortAlg.sort(points, new PointComparator());

 	
 	//Print point
 	System.out.println("");
 	System.out.println("Sorted list of points (sorted by x-cooridinates, "+sortAlg.getMode()+")");
 	System.out.println(new String(new char[80]).replace('\0', '='));
 	for(int idx=0; idx < N; idx++){
 		String line = String.format("%3d | %s", idx, points[idx]);
 		System.out.println(line);}
	}
	public static void SelectionDemo() {
		int N = 10;
        Point2D[] points1 = Point2D.generateList(N, -10, 20);
        //Print points
        System.out.println("DEMO FOR STRAIGHT SELECTION SORT:"); 
        System.out.println(new String(new char[80]).replace('\0', '='));
        System.out.println("Unsorted list of points:"); 
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            String line = String.format("%3d | %s", idx, points1[idx]);
            System.out.println(line); 
        }
        
        //Sort
        StraightSelectionSort<Point2D> sortAlg = new StraightSelectionSort<>();
        sortAlg.sort(points1, new PointComparator());
        
        //Print points
        System.out.println("Sorted list of points:(sorted by x-cooridinates, "+sortAlg.getMode()+")"); 
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            String line = String.format("%3d | %s", idx, points1[idx]);
            System.out.println(line); 
        }
	}
	public static void question2() {
		StraightInsertionSort<Point2D> sortAlg = new StraightInsertionSort<>();
		Point2D[] points = SortingEval.timeit(sortAlg, 10, 10);
		System.out.println("Straight Insertion Sort: Time measurement");
		System.out.println("Size\tTime (msec)");
		System.out.println(new String(new char[40]).replace('\0', '-'));
		for(int i = 0; i < points.length; i++) {
			String line = String.format("%.0f\t%f", points[i].getX(), points[i].getY());
			System.out.println(line);
		}
	}
	public static void BubbleSortDemo() {
		 int N = 10;
	        Point2D[] points = Point2D.generateList(N, -10, 20);
	        //Print points
	        System.out.println("DEMO FOR BUBBLE SORT:"); 
	        System.out.println(new String(new char[80]).replace('\0', '='));
	        System.out.println("Unsorted list of points:"); 
	        System.out.println(new String(new char[80]).replace('\0', '-'));
	        for(int idx=0; idx < N; idx++){
	            String line = String.format("%3d | %s", idx, points[idx]);
	            System.out.println(line); 
	        }
	        
	        //Sort
	        BubbleSort<Point2D> sortAlg = new BubbleSort<>();
	        sortAlg.sort(points, new PointComparator());
	        
	        //Print points
	        System.out.println("Sorted list of points:(sorted by x-cooridinates, "+sortAlg.getMode()+")"); 
	        System.out.println(new String(new char[80]).replace('\0', '-'));
	        for(int idx=0; idx < N; idx++){
	            String line = String.format("%3d | %s", idx, points[idx]);
	            System.out.println(line); 
	        }
	}
	public static void ShellSortDemo() {
		        int N = 20;
		        Point2D[] points = Point2D.generateList(N, -10, 20);
		        //Print points
		        System.out.println("DEMO FOR SHELL SORT:"); 
		        System.out.println(new String(new char[80]).replace('\0', '='));
		        System.out.println("Unsorted list of points:"); 
		        System.out.println(new String(new char[80]).replace('\0', '-'));
		        for(int idx=0; idx < N; idx++){
		            String line = String.format("%3d | %s", idx, points[idx]);
		            System.out.println(line); 
		        }
		        //Sort
		        int[] num_segment = {1, 3, 7};
		      //  shell_sort(points, num_segment);
		      //  System.out.println("Length: "+num_segment.length);
		        ShellSort<Point2D> sortAlg = new ShellSort<>(num_segment);
		        sortAlg.sort(points, new PointComparator());
		        
		        
		        //Print points
		        System.out.println("Sorted list of points:"); 
		        System.out.println(new String(new char[80]).replace('\0', '-'));
		        for(int idx=0; idx < N; idx++){
		            String line = String.format("%3d | %s", idx, points[idx]);
		            System.out.println(line); 
		        }
		    
	}
	public static void SegmentSortDemo() {
		 int N = 10;
	        Point2D[] points = Point2D.generateList(N, -10, 20);
	        
	        //Print points
	        System.out.println("DEMO FOR SORTING A SEGMENT (USED IN SHELL SORT):"); 
	        System.out.println(new String(new char[80]).replace('\0', '='));
	        System.out.println("Unsorted list of points:"); 
	        System.out.println(new String(new char[80]).replace('\0', '-'));
	        for(int idx=0; idx < N; idx++){
	            String line = String.format("%3d | %s", idx, points[idx]);
	            System.out.println(line); 
	        }
	        //Sort a segment
	        //Test the sorting for 1 segement: segment_idx in num_segment 
	        int num_segment = 3;
	        int segment_idx = 2; //valid = 0,1,2
	        SortSegment<Point2D> sortAlg = new SortSegment<>(num_segment,segment_idx);
	        sortAlg.sort(points, new PointComparator());
	        
//	        sort_segment(points, segment_idx, num_segment);
	        
	        //Print points
	        System.out.println("Partially sorted list by using sort_segement:"); 
	        System.out.println(new String(new char[80]).replace('\0', '-'));
	        for(int idx=0; idx < N; idx++){
	            int idx2segment_idx = idx%num_segment;
	            
	            String line;
	            if(idx2segment_idx == segment_idx)
	                line = String.format("%3d | %3d || %s", idx, segment_idx, points[idx]);
	            else
	                line = String.format("%3d | %3s || %s", idx, " ", points[idx]);
	            System.out.println(line); 
	        }
	}
	public static void allSortDemo() {
		int[] num_segments = {1, 3, 7};
		ISort[] algorithms = {
		 new StraightInsertionSort<Point2D>(),
		 new ShellSort<Point2D>(num_segments),
		 new StraightSelectionSort<Point2D>(),
		 new BubbleSort<Point2D>()
		 };
		int N = 10;
		 for(int aIdx=0; aIdx < algorithms.length; aIdx++){
		 Point2D[] points = Point2D.generateList(N, -20, 20);
		 //If you want to sort ...
		 algorithms[aIdx].sort(points, new PointComparator()); //do sorting
		 System.out.println("Sort of type:"+aIdx);
		 for(int idx=0; idx < N; idx++){
	            String line = String.format("%3d | %s", idx, points[idx]);
	            System.out.println(line); 
	        }
		 //If you want to time it ...
		 System.out.println("Time evaluate of type:"+aIdx);
		 Point2D[] time = SortingEval.timeit(algorithms[aIdx], 500, 100);
		 
		 System.out.println("Straight Insertion Sort: Time measurement");
			System.out.println("Size\tTime (msec)");
			System.out.println(new String(new char[40]).replace('\0', '-'));
			for(int i = 0; i < time.length; i++) {
				String line = String.format("%.0f\t%f", time[i].getX(), time[i].getY());
				System.out.println(line);
			}
		 //here: more code for other purpose.
	}
	
}
}