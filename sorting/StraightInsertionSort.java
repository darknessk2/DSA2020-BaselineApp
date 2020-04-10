package sorting;
import java.util.Comparator;

import geom.Graph.GraphMode;
public class StraightInsertionSort<E> implements ISort<E>{
	public enum directionSort {Ascending, Descending};
	
	private directionSort mode = directionSort.Ascending;
	
	public directionSort getMode() {
		return mode;
	}
	public void setMode(directionSort mode) {
		this.mode = mode;
	}
	public StraightInsertionSort(directionSort mode) {
		this.mode = mode;
	}
	
	public StraightInsertionSort() {
		this.mode = directionSort.Ascending;
	}
	/* 
	 Method: Straigh Insertion Sort
	 Objective: sort and print list's items in ascending order on screen
	 
	 Space complexity: O(n)
	 Reasons:
	  + O(n): to store array of n elements
	  + constant: for temporary variables, for example: current, walker, temp
	  + Total: O(n) + c = O(n)
	  
	  Time Complexity: O(n^2)
	  Reasons: 
	  + pass 1 has 1 comparator, pass 2 has 2 comparators,...pass N-1 has N-1 comparators
	  + Total: 1 + 2+ 3 + ... + (N-1) = [(N-1)N]/2 = 0.5*N^2 - 0.5*N
	  + constant: for other operations: assignments, addition, relational operations
	*/
	@Override
	public void sort(E[] array, Comparator<E> comparator){
	if(this.mode == directionSort.Ascending) {
		int current, walker;
		E temp;
		current = 1;
		while(current < array.length){
			temp = array[current];
			walker = current - 1;
			while((walker >= 0) && comparator.compare(temp, array[walker])< 0 ){
				array[walker + 1] = array[walker]; //shift to right
				walker -= 1;
			}
		array[walker + 1] = temp;
		current += 1;}
 }
	else if (this.mode == directionSort.Descending) {
	int current, walker;
	E temp;
	current = 1;
	while(current < array.length){
		temp = array[current];
		walker = current - 1;
		while((walker >= 0) && comparator.compare(temp, array[walker])> 0 ){
			array[walker + 1] = array[walker]; //shift to right
			walker -= 1;
		}
	array[walker + 1] = temp;
	current += 1;}}
	}
 }