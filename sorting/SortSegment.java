package sorting;

import java.util.Comparator;

import geom.Point2D;

public class SortSegment<E> implements ISort<E> {
	protected int segment_idx;
	protected int num_segment;
	private enum directionSort {Ascending, Descending};
	private directionSort mode= directionSort.Descending;
	
	public directionSort getMode() {
		return mode;
	}
	public void setMode(directionSort mode) {
		this.mode = mode;
	}
	public SortSegment(directionSort mode) {
		this.mode = mode;
	}
	
	public SortSegment() {
		this.mode = directionSort.Ascending;
	}
	
	public SortSegment(int segment_idx, int num_segment) {
		this.segment_idx = segment_idx;
		this.num_segment = num_segment;
	}
	
	@Override
	public void sort(E[] array, Comparator<E> comparator) {
				int current;
		        int walker;
		        E temp;
		        current = segment_idx + num_segment;
		        while(current < array.length){
		            temp = array[current];
		            walker = current - num_segment;
		            if(this.mode == directionSort.Ascending) {
		            while((walker >= 0) && (comparator.compare(temp, array[walker])<0) ){
		                array[walker + num_segment] = array[walker]; //shift to right
		                walker -= num_segment;
		            }}
		            else {
		            	while((walker >= 0) && (comparator.compare(temp, array[walker])>0) ){
				                array[walker + num_segment] = array[walker]; //shift to right
				                walker -= num_segment;}
		            }
		            
		            array[walker + num_segment] = temp;
		            current += num_segment;
		        }
		    }
		
	}


