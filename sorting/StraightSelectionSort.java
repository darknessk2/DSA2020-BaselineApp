package sorting;
import java.util.Comparator;

public class StraightSelectionSort<E> implements ISort<E>{
	private enum directionSort {Ascending, Descending};
	private directionSort mode = directionSort.Ascending;
	
	public directionSort getMode() {
		return mode;
	}
	public void setMode(directionSort mode) {
		this.mode = mode;
	}
	public StraightSelectionSort(directionSort mode) {
		this.mode = mode;
	}
	
	public StraightSelectionSort() {
		this.mode = directionSort.Ascending;
		
	}
	@Override
	public void sort(E[] array, Comparator<E> comparator) {
		// TODO Auto-generated method stub
		   int current, smallest, walker;
	       
	        current = 0; 
	        while(current < array.length - 1){
	            smallest = current;
	            walker = current + 1;
	            while(walker < array.length){
	            	if(this.mode == directionSort.Ascending)
	                {if(comparator.compare(array[walker], array[smallest])< 0 )
	                		{smallest = walker;}}
	            	else {
	            		if(comparator.compare(array[walker], array[smallest])> 0 )
                		smallest = walker;
	            	}
	            walker += 1;
	            }
	            if(smallest != current){
	                //swap:
	                E temp = array[smallest];
	                array[smallest] = array[current];
	                array[current] = temp;
	            }
	            current += 1;
	        }
	    }	
	

}


