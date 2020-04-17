package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import geom.Point2D;
import list.MyArrayList.MyListIterator;

public class ArrayListDemo {
	public static void main(String[] args) {
		int N = 5;
		Point2D[] points = Point2D.generateList(N, -10, 20);
		
		
	//	List<Point2D> listNum = new ArrayList<>();
	//	List<Point2D> listNum = new MyArrayList<>();
		List<Point2D> listNum = new SLinkedList<>();
		
		for(int idx = 0; idx < points.length; idx++) 
		{listNum.add(points[idx]);}
		Point2D testPoint = new Point2D(4,5);
		double radius = 10;
		
		//GET SUB LIST 
/*		System.out.println("List original:");
		for (int idx = 0; idx < listNum.size(); idx++) {
			System.out.println(listNum.get(idx));
		}
		
		List<Point2D> subListNum = listNum.subList(1, 3);
		System.out.println("Sublist from original list");
		for (int idx = 0; idx < subListNum.size(); idx++) {
			System.out.println(subListNum.get(idx));
		}
*/		
		
		removeHittedPoints(listNum, testPoint, radius);
//		addEandtraverseList();
//		fig5SatisfyCondition();
//		timeBenchMark();
		
	}
	public static void addEandtraverseList() {
	//	List<String> list = new ArrayList<>();
	//	List<String> list = new MyArrayList<>();
		List<String> list = new SLinkedList<>();
		//List<String> list = new LinkedList<>(); List<String> list = new Vector<>();
				for (int idx = 0; idx < 10; idx++) {
					list.add("" + idx);
				}

				// (1)Print elements - Use Index, travel forward
				System.out.printf("%-32s", "Go forward, use index:");
				for (int idx = 0; idx < list.size(); idx++) {
					System.out.printf("%s ", list.get(idx));
				}
				System.out.println();

				// (2)Print elements - Use Index, travel backward
				System.out.printf("%-32s", "Go backward, use index:");
				for (int idx = list.size() - 1; idx >= 0; idx--) {
					System.out.printf("%s ", list.get(idx));
				}
				System.out.println();

				// (3)Print elements - Use Iterator, travel forward
				System.out.printf("%-32s", "Go forward, use Iterator:");
				Iterator<String> it = list.iterator();
				while (it.hasNext()) {
					String item = it.next();
					System.out.printf("%s ", item);
				}
				System.out.println();

				// (4)Print elements - Use ListIterator, travel forward
				System.out.printf("%-32s", "Go forward, use ListIterator:");
				ListIterator<String> fwd = list.listIterator();
				while (fwd.hasNext()) {
					String item = fwd.next();
					System.out.printf("%s ", item);
				}
				
				System.out.println();

				// (5)Print elements - Use ListIterator, travel backward
				System.out.printf("%-32s", "Go backward, use ListIterator:");
				ListIterator<String> bwd = list.listIterator(list.size());
				while (bwd.hasPrevious()) {
					String item = bwd.previous();
					System.out.printf("%s ", item);
				}
				System.out.println();
	}
	public static void fig5SatisfyCondition() {
		//Testing different type of list
//		List<Integer> list = new ArrayList<>();
//		List<Integer> list = new MyArrayList<>();
		List<Integer> list = new SLinkedList<>();
		
		// Add elements
		for (int idx = 0; idx < 10; idx++) {
			list.add(idx);
		}

		// (1)Print elements - Use Index, travel forward
		System.out.printf("%-25s", "Before modification:");
		for (int idx = 0; idx < list.size(); idx++) {
			System.out.printf("%s ", list.get(idx));
		}
		System.out.println();
		// (2)Remove odd numbers
		ListIterator<Integer> it = list.listIterator();
		while (it.hasNext()) {
			int item = it.next();
//			Integer item = it.next();
			if (item % 2 != 0) {
				it.remove();
				//System.out.println("Odd");
			}
				
			else {
				//System.out.println("Even");
				it.set(item * 10);
				}
		}
				
		// (3) Print after changing
		System.out.printf("%-25s", "After modification:");
		it = list.listIterator();
		while (it.hasNext()) {
			System.out.printf("%s ", it.next());
		}
		System.out.println();
		
/*Can you use a for-loop to identify the data elements by their
index and then remove or replace the elements as above? - NO, 
because using the remove(int index) will accept index of object to be removed,
and after removing, the order would change, which means it pass the event element 
into the odd position leads to the even element can not be check condition
*/
/*		for(int idx = 0; idx < list.size(); idx++) {
			int items = list.get(idx);
			if(items % 2 !=0) list.remove(idx);
			
			else list.set(idx, items*10);
		}
		System.out.printf("%-25s", "After modification:");
		for(int idx = 0; idx < list.size();idx++) {
			System.out.printf("%s ", list.get(idx));
		}
		System.out.println(); */
	}
	public static void removeHittedPoints(List<Point2D> list, Point2D testPoint, double radius) {
		System.out.printf("%-25s\n", "Before modification:");
		for (int idx = 0; idx < list.size(); idx++) {
			System.out.println(list.get(idx));
		}
		ListIterator<Point2D> it = list.listIterator();
		while (it.hasNext()) {
			Point2D item = it.next();
			if(testPoint.distancePoint(item)<radius) 
				it.remove();
		}
		System.out.printf("%-25s\n", "After modification:");
		it = list.listIterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	public static void timeBenchMark() {
		double exeGetArray = 0, exeAddArray = 0, exeAddEndArray = 0;
		double exeGetVec = 0, exeAddVec = 0, exeAddEndVec = 0;
		double exeGetLink = 0, exeAddLink = 0, exeAddEndLink = 0;
		double exeGetMyArray = 0, exeAddMyArray = 0, exeAddEndMyArray = 0;
		double exeGetSingle = 0, exeAddSingle = 0, exeAddEndSingle = 0;
		List<Integer> listArrayList = new ArrayList<>();
		for (int idx = 0; idx < 1000; idx++) {
			listArrayList.add(idx);
		}
		
		List<Integer> listVector = new Vector<>();
		for (int idx = 0; idx < 1000; idx++) {
			listVector.add(idx);
		}
		
		List<Integer> listLinkedList = new LinkedList<>();
		for (int idx = 0; idx < 1000; idx++) {
			listLinkedList.add(idx);
		}
		List<Integer> listMyArrayList = new MyArrayList<>();
		for (int idx = 0; idx < 1000; idx++) {
			listMyArrayList.add(idx);
		}
		List<Integer> listSingleList = new SLinkedList<>();
		for (int idx = 0; idx < 1000; idx++) {
			listSingleList.add(idx);
		}
		
		//GET ELEMENT AT INDEX
		double times = 1000;
		for(int i=0; i<times; i++) {
			Random rand = new Random();
	        int rand_num = rand.nextInt(1000); 
	        System.out.println("random: "+rand_num);
	        
	        double timeElapsed=0;
	        long startTime = System.nanoTime();
	        listArrayList.get(rand_num);
	        long endTime = System.nanoTime();
	        timeElapsed += (double)(endTime - startTime);
	        System.out.println("timeElapsed: "+ timeElapsed);
	        exeGetArray += timeElapsed;
	        System.out.println("exeGetArray: "+ exeGetArray);
	        
	        double timeElapsedV=0; 
	        long startTimeV = System.nanoTime();
	        listVector.get(rand_num);
	        long endTimeV = System.nanoTime();
	        timeElapsedV += (double)(endTimeV - startTimeV);
	        System.out.println("timeElapsedV: "+ timeElapsedV);
	        exeGetVec+= timeElapsedV;
	        System.out.println("exeGetVec: "+ exeGetVec);
	        
	        double timeElapsedL=0; 
	        long startTimeL = System.nanoTime();
	        listLinkedList.get(rand_num); 
	        long endTimeL = System.nanoTime();
	        timeElapsedL += (double)(endTimeL - startTimeL);
	        System.out.println("timeElapsedL: "+ timeElapsedL);
	        exeGetLink += timeElapsedL;
	        System.out.println("exeGetVecL: "+ exeGetLink);
	        
	        double timeElapsedM=0; 
	        long startTimeM = System.nanoTime();
	        listMyArrayList.get(rand_num); 
	        long endTimeM = System.nanoTime();
	        timeElapsedM += (double)(endTimeM - startTimeM);
	        System.out.println("timeElapsedM: "+ timeElapsedM);
	        exeGetMyArray += timeElapsedM;
	        System.out.println("exeGetVecL: "+ exeGetMyArray);
	        
	        double timeElapsedS=0; 
	        long startTimeS = System.nanoTime();
	        listSingleList.get(rand_num); 
	        long endTimeS = System.nanoTime();
	        timeElapsedS += (double)(endTimeS - startTimeS);
	        System.out.println("timeElapsedS: "+ timeElapsedS);
	        exeGetSingle += timeElapsedS;
	        System.out.println("exeGetSingle: "+ exeGetSingle);
		}
		double averageTimeGetArray = (exeGetArray/times)*1e-6;
		double averageTimeGetVector = (exeGetVec/times)*1e-6;
		double averageTimeGetLink = (exeGetLink/times)*1e-6;
		double averageTimeGetMyArray = (exeGetMyArray)*1e-6;
		double averageTimeGetSingle = (exeGetSingle)*1e-6;
		System.out.println("Time of getting index using ArrayList: "+averageTimeGetArray);
		System.out.println("Time of getting Vector: "+averageTimeGetVector);
		System.out.println("Time of getting LinkedList: "+averageTimeGetLink);
		System.out.println("Time of getting MyArrayList: "+averageTimeGetMyArray);
		System.out.println("Time of getting SingleList: "+averageTimeGetSingle);
		
//		double times = 1000;
		//ADD ELEMENT AT INDEX
		for(int i=0; i<times; i++) {
			int step = 500;
			Random rand = new Random();
	        int rand_num = rand.nextInt(1000); 
	        System.out.println("random: "+rand_num);
	        
	        double timeElapsed=0;
	        long startTime = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listArrayList.add(0, rand_num);
	        long endTime = System.nanoTime();
	        timeElapsed += (double)(endTime - startTime);
	        exeAddArray += timeElapsed;
	        
	        double timeElapsedV=0; 
	        long startTimeV = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listVector.add(0,rand_num);
	        long endTimeV = System.nanoTime();
	        timeElapsedV += (double)(endTimeV - startTimeV);
	        exeAddVec+= timeElapsedV;
	        
	        double timeElapsedL=0; 
	        long startTimeL = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listLinkedList.add(0, rand_num); 
	        long endTimeL = System.nanoTime();
	        timeElapsedL += (double)(endTimeL - startTimeL);
	        exeAddLink += timeElapsedL;
	        
	        double timeElapsedM=0; 
	        long startTimeM = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listMyArrayList.add(0, rand_num);
	        long endTimeM = System.nanoTime();
	        timeElapsedM += (double)(endTimeM - startTimeM);
	        exeAddMyArray += timeElapsedM;
	        
	        double timeElapsedS=0; 
	        long startTimeS = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listSingleList.add(0, rand_num);
	        long endTimeS = System.nanoTime();
	        timeElapsedS += (double)(endTimeS - startTimeS);
	        exeAddSingle += timeElapsedS;
	        
	        listArrayList.clear();
	        listVector.clear();
	        listLinkedList.clear();
	        listMyArrayList.clear();
	        listSingleList.clear();
		}
		double averageTimeAddArray = (exeAddArray/times)*1e-6;
		double averageTimeAddVector = (exeAddVec/times)*1e-6;
		double averageTimeAddLink = (exeAddLink/times)*1e-6;
		double averageTimeAddMyArray = (exeAddMyArray/times)*1e-6;
		double averageTimeAddSingle = (exeAddSingle/times)*1e-6;
		System.out.println("Time of adding index using ArrayList: "+averageTimeAddArray);
		System.out.println("Time of adding Vector: "+averageTimeAddVector);
		System.out.println("Time of adding LinkedList: "+averageTimeAddLink);
		System.out.println("Time of adding MyArray: "+averageTimeAddMyArray);
		System.out.println("Time of adding Single: "+averageTimeAddSingle);
		
		

//		double times = 1000;
		//GET ELEMENT TO THE END OF LIST
	for(int i=0; i<times; i++) {
			int step = 500;
			Random rand = new Random();
	        int rand_num = rand.nextInt(1000); 
	        System.out.println("random: "+rand_num);
	        
	        double timeElapsed=0;
	        long startTime = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listArrayList.add(rand_num);
	        long endTime = System.nanoTime();
	        timeElapsed += (double)(endTime - startTime);
	        exeAddEndArray += timeElapsed;
	        
	        double timeElapsedV=0; 
	        long startTimeV = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listVector.add(rand_num);
	        long endTimeV = System.nanoTime();
	        timeElapsedV += (double)(endTimeV - startTimeV);
	        exeAddEndVec+= timeElapsedV;
	        
	        double timeElapsedL=0; 
	        long startTimeL = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listLinkedList.add(rand_num); 
	        long endTimeL = System.nanoTime();
	        timeElapsedL += (double)(endTimeL - startTimeL);
	        exeAddEndLink += timeElapsedL;
	       
	        
	        double timeElapsedM=0; 
	        long startTimeM = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listMyArrayList.add(rand_num); 
	        long endTimeM = System.nanoTime();
	        timeElapsedM += (double)(endTimeM - startTimeM);
	        exeAddEndMyArray += timeElapsedM;
	        
	        double timeElapsedS=0; 
	        long startTimeS = System.nanoTime();
	        for(int k=0; k<step;k++)
	        listSingleList.add(rand_num); 
	        long endTimeS = System.nanoTime();
	        timeElapsedS += (double)(endTimeS - startTimeS);
	        exeAddEndSingle += timeElapsedS;
	        
	        listArrayList.clear();
	        listVector.clear();
	        listLinkedList.clear();
	        listMyArrayList.clear();
	        listSingleList.clear();
		}

		double averageTimeAddEndArray = (exeAddEndArray/times)*1e-6;
		double averageTimeAddEndVector = (exeAddEndVec/times)*1e-6;
		double averageTimeAddEndLink = (exeAddEndLink/times)*1e-6;
		double averageTimeAddEndMyArray = (exeAddEndMyArray/times)*1e-6;
		double averageTimeAddEndSingle = (exeAddEndSingle/times)*1e-6;
		System.out.println("Time of adding to end ArrayList: "+averageTimeAddEndArray);
		System.out.println("Time of adding to end Vector: "+averageTimeAddEndVector);
		System.out.println("Time of adding to end LinkedList: "+averageTimeAddEndLink);
		System.out.println("Time of adding to end MyArrayList: "+averageTimeAddEndMyArray);
		System.out.println("Time of adding to end SingleList: "+averageTimeAddEndSingle);
		
		System.out.println("Implement\tget(int index)\tadd(int index,E element) add(int index)");
		System.out.printf("Array List\t%.5f\t\t%.5f\t\t\t%.5f\n",averageTimeGetArray,averageTimeAddArray, averageTimeAddEndArray);
		System.out.printf("Vector List\t%.5f\t\t%.5f\t\t\t%.5f\n",averageTimeGetVector,averageTimeAddVector, averageTimeAddEndVector);
		System.out.printf("Linked List\t%.5f\t\t%.5f\t\t\t%.5f\n",averageTimeGetLink,averageTimeAddLink, averageTimeAddEndLink);
		System.out.printf("MyArray List\t%.5f\t\t%.5f\t\t%.5f\n",averageTimeGetMyArray,averageTimeAddMyArray, averageTimeAddEndMyArray);
		System.out.printf("Single List\t%.5f\t\t%.5f\t\t\t%.5f\n",averageTimeGetSingle,averageTimeAddSingle, averageTimeAddEndSingle);

	}
}
