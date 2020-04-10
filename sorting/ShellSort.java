package sorting;

import java.util.Comparator;

import geom.Point2D;
import geom.PointComparator;

public class ShellSort<E> implements ISort<E> {
	private enum directionSort {Ascending, Descending};
	private directionSort mode = directionSort.Ascending;
	
	public directionSort getMode() {
		return mode;
	}
	public void setMode(directionSort mode) {
		this.mode = mode;
	}
	public ShellSort(directionSort mode) {
		this.mode = mode;
	}
	
	public ShellSort() {
		this.mode = directionSort.Ascending;
	}
protected int[] num_segment; //= {1, 3, 7};
public ShellSort(int[] num) {
	this.num_segment = num;
}
	@Override
	public void sort(E[] array, Comparator<E> comparator) {
	int k = 0;
	int segment_idx = 0;
	SortSegment<E> sortAlg;
	
		for(k = num_segment.length - 1; k > 0; k--){
            for(segment_idx = 0; segment_idx < k; segment_idx++)
            {sortAlg = new SortSegment<>(segment_idx,k);
            sortAlg.sort(array, comparator);}
            
            
        }       
	}
	
}
