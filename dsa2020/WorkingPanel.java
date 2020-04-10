package dsa2020;
import geom.Axis;
import geom.GeomObject;
import geom.Graph;
import geom.Point2D;
import geom.Rectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import geom.SpaceMapping;

import sorting.BubbleSort;
import sorting.ISort;
import sorting.ShellSort;
import geom.PointComparator;
import sorting.SortingEval;
import sorting.StraightInsertionSort;
import sorting.StraightSelectionSort;

/**
 *
 * @author LTSACH
 */

/*
 * ShellSort is best one, and BubbleSort the worst
  Bubble Sort 
Time Complexity: O(n^2)
Reasons: 
+ The first pass (starting from current = 0) need to do (n-1) comparisons, second pass need to do (n-2) comparisons, 
(n-3) comparisons will be done in third pass and so on..to nth pass (where current = n-1) have 1 comparison.
+ Total: (n-1) + (n-2) + (n-3) +...+ 3 + 2 + 1 = n.(n-1)/2
+ constant: for other operations: assignments, subtraction, relational operations

Shell Sort
Time Complexity: O(n1.25)
To be specific, given that sorting array have 4 element, length of array is 4. 
Shell Sort will sort faster than Bubble Sort because (41.25 = 5.656 which is smaller than 42 = 16)
As larger as n elements is, shell sort will work much faster than bubble sort.

  */
public class WorkingPanel extends JPanel
        implements MouseMotionListener, MouseListener,
        ItemListener,
        ActionListener, ComponentListener        
{
	boolean clickGraphornot = false;
	boolean clickRecornot = false;
	public static Point2D[] points = new Point2D[0];
	public static int state;
	
	int[] num_segments = {1, 3, 7};
	ISort[] alg = {
	 new StraightInsertionSort<Point2D>(),
	 new ShellSort<Point2D>(num_segments),
	 new StraightSelectionSort<Point2D>(),
	 new BubbleSort<Point2D>()
	 };

	public void addPoint(Point2D[] points, double x, double y) {
		Point2D[] newList = Arrays.copyOf(points, points.length+1);
		newList[newList.length-1] = new Point2D(x,y);
		System.out.println("New Point is added: ("+x+","+y);
		updatePoint(newList);
		
	}
	public Point2D[] updatePoint(Point2D[] pL) {
		points = Arrays.copyOf(pL, pL.length);
		return points;
		
	}
	public static Point2D[] getPoints() {
		return points;
	}

	public void setPoints(Point2D[] points) {
		this.points = points;
	}
	Axis axis = new Axis(0,1,0,1);
//	Graph graph;
	Graph sin, quad;
	SpaceMapping spaceMapping = new SpaceMapping();
	GeomObject geom2;
    public WorkingPanel(){
    	this.addComponentListener(this);
        this.setBorder(BorderFactory.createEtchedBorder()); 
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        
      // Sorting API for some algorithms 
       int[] num_segments = {1, 3, 7};
	ISort[] alg = {
	 new StraightInsertionSort<Point2D>(),
	 new ShellSort<Point2D>(num_segments),
	 new StraightSelectionSort<Point2D>(),
	 new BubbleSort<Point2D>()
	 };
    }
	
//	 for(int aIdx=0; aIdx < alg.length; aIdx++){
//	 Point2D[] points = Point2D.generateList(100, -20, 20);
	 
	 //If you want to sort ...
//	 alg[aIdx].sort(points, new PointComparator()); //do sorting
	 
	 //If you want to time it ...
//	 Point2D[] time = SortingEval.timeit(alg[aIdx], 500, 100);
	 //here: more code for other purpose.
	  
//	 } 
    @Override
    public void mouseDragged(MouseEvent e) {
    	if(this.state!=2) addPoint(points,e.getX(),e.getY());

    	Point2D logPoint = this.spaceMapping.device2Logic(e.getX(), e.getY());
    	String message = String.format("mouseDragged: Device(x,y)=(%d,%d);Logic(x,y)=(%6.2f, %6.2f)", e.getX(), e.getY(), 
    			logPoint.getX(),logPoint.getY());
    	MainFrame.infoPanel.println(message);
    	
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    	Point2D logPoint = this.spaceMapping.device2Logic(e.getX(), e.getY());
    	String message = String.format("mouseMoved: Device(x,y)=(%d,%d); Logic(x,y)=(%6.2f, %6.2f)", e.getX(), e.getY(), 
    			logPoint.getX(),logPoint.getY());
    	MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if((e.getClickCount() == 2) && !e.isConsumed()){
            String message = String.format("Mouse Double Clicked: (x,y)=(%d,%d)", e.getX(), e.getY());
            MainFrame.infoPanel.println(message);
            e.consume();
        }
        else{
            String message = String.format("Mouse Clicked: (x,y)=(%d,%d)", e.getX(), e.getY());

            MainFrame.infoPanel.println(message);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        String message = String.format("mousePressed: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
        if(this.state!=2) addPoint(points,e.getX(),e.getY());
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String message = String.format("mouseReleased: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String message = String.format("mouseEntered: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String message = String.format("mouseExited: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        updateItem(state);
        if(state == ItemEvent.SELECTED){
        	System.out.println(state);
            MainFrame.infoPanel.println("Selected");
            MainFrame.btnSelect.setText("Selecting");
        }
        else{
        	System.out.println(state);
            MainFrame.infoPanel.println("DeSelected");
            MainFrame.btnSelect.setText("Drawing");
        }
    }
    public int updateItem(int s) {
    	state = s;
    	return state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == MainFrame.btnCircle){
            MainFrame.infoPanel.println("action: draw Circle");
            
        }
        else if(e.getSource() == MainFrame.btnRect){
            MainFrame.infoPanel.println("action: draw Rect");
            clickRecornot = true;
            clickGraphornot =false;
            repaint();
        }
        else if(e.getSource() == MainFrame.btnGraph){
            MainFrame.infoPanel.println("action: draw Graph");
 //           clickGraphornot = true;
 //           clickRecornot = false;
 //           geom2 = Graph.quadratic(1, 0, 0, 0, 1, 0.1);
            
            
            Color[] color = {Color.red, Color.green, Color.blue, Color.cyan};
	        
	        for(int aIdx=0; aIdx < alg.length; aIdx++){
	        Point2D[] time = SortingEval.timeit(alg[aIdx], 500, 500);
	        System.out.println("Alg at position:"+aIdx);
	        Graph graph = new Graph(time, color[aIdx]);
	        graph.setMode(Graph.GraphMode.SCATTER);
	        this.axis.addGraph(graph, color[aIdx], 1);
	        }
			        
	        //update viewport
	        this.spaceMapping.updateLogViewPort(this.axis.getViewPort());
	        this.repaint();
		
	        //    sin = Graph.sin(2,-100,100,0.05);
            //    quad = Graph.quadratic(1,0,0,-100,100,0.05);
	        //   repaint();
        }
        else{
            
        }
    }

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		Dimension size = this.getSize();
		int xGap = 50, yGap = 20;
		this.spaceMapping.updateDevViewPort(xGap, size.width-2*xGap, yGap,
		size.height-2*yGap);
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		axis.draw (g, spaceMapping);
/*        if (sin != null) {
            MainFrame.infoPanel.println("Action: Draw Sin Graph");
            sin.draw(g, spaceMapping);
        }
        if (quad != null) {
            MainFrame.infoPanel.println("Action: Draw Quadratic Graph");
            quad.draw(g,spaceMapping);
        }
        if (sin == null && quad == null) {
            MainFrame.infoPanel.println("Action: Cannot Draw ");
        }
*/ 
		
		//DRAW A POINT
//		Point2D point = new Point2D(0.5,0.5);
//		point.draw(g, spaceMapping);

		//DRAW A GRAPH WITH GIVEN POINT
		//State equal is when user click button to deselecting point and start drawing
		if((state==2)&&(clickGraphornot == true))geom2.draw(g, spaceMapping);
		
		//DRAW DIRECTLY WITHOUT CLICK BUTTON
//		Graph.sin(1, 0, 1, 0.5).draw(g, spaceMapping);
//		Graph.quadratic(1, 0, 0, 0, 1, 0.1).draw(g, spaceMapping);
		
		
		//DRAW RECTANGLE AFTER SELECTING POINT FROM DRAG AND PRESS POINT
		if((state==2)&&(clickRecornot == true)&&(points.length!=0))
		{GeomObject geom1= new Rectangle(getPoints(), 0, 0, 0, 0);
		geom1.draw(g, spaceMapping);} 
	}
}