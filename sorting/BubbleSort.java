package sorting;

import java.util.Comparator;

import geom.Point2D;

public class BubbleSort<E> implements ISort<E> {
	private enum directionSort {Ascending, Descending};
	private directionSort mode = directionSort.Ascending;
	
	public directionSort getMode() {
		return mode;
	}
	public void setMode(directionSort mode) {
		this.mode = mode;
	}
	public BubbleSort(directionSort mode) {
		this.mode = mode;
	}
	
	public BubbleSort() {
		this.mode = directionSort.Ascending;
	}
	@Override
	public void sort(E[] array, Comparator<E> comparator) {
		// TODO Auto-generated method stub
		int current, walker;
        boolean flag;
        
        current = 0; 
        flag = false;
        while((current < array.length-1) && (flag == false)){
            walker = array.length - 1; //start from the last and backward
            flag = true; //for testing if the input already in ascending order
            while(walker > current){
            if(this.mode == directionSort.Ascending) {
                if(comparator.compare(array[walker],array[walker-1])<0){
                    flag = false;
                    //swap:
                    E temp = array[walker];
                    array[walker] = array[walker-1];
                    array[walker-1] = temp;
                }
             }else if(this.mode == directionSort.Descending) {
            	 if(comparator.compare(array[walker],array[walker-1])>0){
                     flag = false;
                     //swap:
                     E temp = array[walker];
                     array[walker] = array[walker-1];
                     array[walker-1] = temp;
                }
             }
            walker -= 1;
                
            }
            current += 1;
        }
	}
}
