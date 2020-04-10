/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geom;

import geom.Point2D;

/**
 *
 * @author LTSACH
 */
public class Sorting {  
    /*
    Algorithm: Insertion Sort
    -----------------------
    Time complexity:
    pass        #comparison
    -----------------------
    1:          1
    2:          2
    3:          3
    ...
    N-1:        N-1
    Total: 1 + 2+ 3 + ... + (N-1) = [(N-1)N]/2
        = 0.5*N^2 - 0.5*N
    
    How to report the complexity?
        O(0.5*N^2 - 0.5*N) : right or wrong? =>WRONG!
        SHOULD WRITE: O(N^2), just keep maximum magnitude
    
    Space complexity: O(N)
    Reasons:
        * Store N elements in input array
        * Store other temporary variables (constant size)
    => Total = O(N) + C = O(N)
    */
    public static void insertion_sort(Point2D[] points) {
        int current;
        int walker;
        Point2D temp;
        current = 1;
        while(current < points.length){
            temp = points[current];
            walker = current - 1;
            while((walker >= 0) && (temp.getX() < points[walker].getX()) ){
                points[walker + 1] = points[walker]; //shift to right
                walker -= 1;
            }
            points[walker + 1] = temp;
            current += 1;
        }
    }
    
    public static void insertion_sort_demo() {
        int N = 10;
        Point2D[] points = Point2D.generateList(N, -10, 20);
        
        //Print points
        System.out.println("DEMO FOR INSERTION SORT:"); 
        System.out.println(new String(new char[80]).replace('\0', '='));
        System.out.println("Unsorted list points:"); 
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            String line = String.format("%3d | %s", idx, points[idx]);
            System.out.println(line); 
        }
        //Sort: insertion sort
        insertion_sort(points);
        
        //Print point
        System.out.println("");
        System.out.println("Sorted list of points (sorted by x-cooridinates, ascending)"); 
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            String line = String.format("%3d | %s", idx, points[idx]);
            System.out.println(line); 
        }
    }
    
    /*
    Sort segement k:
    
    */
    public static void sort_segment(Point2D[] points, int segment_idx, int num_segment){
        int current;
        int walker;
        Point2D temp;
        current = segment_idx + num_segment;
        while(current < points.length){
            temp = points[current];
            walker = current - num_segment;
            while((walker >= 0) && (temp.getX() < points[walker].getX()) ){
                points[walker + num_segment] = points[walker]; //shift to right
                walker -= num_segment;
            }
            points[walker + num_segment] = temp;
            current += num_segment;
        }
    }
    /*
    shell_sort
    -----------
    num_segments: 
         + The first must be 1, for examples: [1,3,7]
    */
    public static void shell_sort(Point2D[] points, int[] num_segment){
        for(int k=num_segment.length - 1; k > 0; k--){
            for(int segment_idx = 0; segment_idx < k; segment_idx++)
                sort_segment(points, segment_idx, k);
        }
    }
    public static void shell_sort_demo(){
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
        shell_sort(points, num_segment);
        
        //Print points
        System.out.println("Sorted list of points:"); 
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            String line = String.format("%3d | %s", idx, points[idx]);
            System.out.println(line); 
        }
    }
    public static void sort_segment_demo(){
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
        sort_segment(points, segment_idx, num_segment);
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
    
    public static void straight_selection_sort(Point2D[] points){
        int current, smallest, walker;
        
        current = 0; 
        while(current < points.length - 1){
            smallest = current;
            walker = current + 1;
            while(walker < points.length){
                if(points[walker].getX() < points[smallest].getX()){
                    smallest = walker;
                }
                walker += 1;
            }
            if(smallest != current){
                //swap:
                Point2D temp = points[smallest];
                points[smallest] = points[current];
                points[current] = temp;
            }
            current += 1;
        }
    }
    public static void straight_selection_sort_demo(){
        int N = 10;
        Point2D[] points = Point2D.generateList(N, -10, 20);
        //Print points
        System.out.println("DEMO FOR STRAIGHT SELECTION SORT:"); 
        System.out.println(new String(new char[80]).replace('\0', '='));
        System.out.println("Unsorted list of points:"); 
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            String line = String.format("%3d | %s", idx, points[idx]);
            System.out.println(line); 
        }
        
        //Sort
        straight_selection_sort(points);
        
        //Print points
        System.out.println("Sorted list of points:"); 
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            String line = String.format("%3d | %s", idx, points[idx]);
            System.out.println(line); 
        }
    }
    
    public static void bubble_sort(Point2D[] points){
        int current, walker;
        boolean flag;
        
        current = 0; 
        flag = false;
        while((current < points.length-1) && (flag == false)){
            walker = points.length - 1; //start from the last and backward
            flag = true; //for testing if the input already in ascending order
            while(walker > current){
                if(points[walker].getX() < points[walker-1].getX()){
                    flag = false;
                    //swap:
                    Point2D temp = points[walker];
                    points[walker] = points[walker-1];
                    points[walker-1] = temp;
                }
                walker -= 1;
            }
            current += 1;
        }
    }
    public static void bubble_sort_demo(){
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
        bubble_sort(points);
        
        //Print points
        System.out.println("Sorted list of points:"); 
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            String line = String.format("%3d | %s", idx, points[idx]);
            System.out.println(line); 
        }
    }
    
    public static void main(String[] args) {
        bubble_sort_demo();
        straight_selection_sort_demo();
        insertion_sort_demo();
        sort_segment_demo();
        shell_sort_demo();
    }
    
}
