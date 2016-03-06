package javatsp_sa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javatsp.TSP;
import linkedlist.IntLinkedList;
import linkedlist.Node;

public class Tour implements Comparable<Tour> {

	int [] tour;
	TSP cities;
	int size;
	
	public Tour(int size){
		this.size	=	size;
		cities 		= 	null;
		tour 		= 	new int[size];
		
		//initialize tour;
		for (int i = 0;i<size;i++) { tour[i] = -1; }
	}
	
	public Tour(TSP tsp){
		size 		= tsp.size();
		cities 		= tsp;
		
		tour = new int[tsp.size()];
		
		//Create an array list of the city indices
		ArrayList<Integer> cityIndices = new ArrayList<Integer>();
		for (int i=0;i<tsp.size();i++){ cityIndices.add(i); }
		
		//Generate random tour;
		Collections.shuffle(cityIndices);
		for(int i = 0;i<tsp.size();i++){ tour[i] = cityIndices.get(i);}
		
	}
	
	public Tour(Tour tour){
		size		=	tour.size();
		cities		= 	tour.getTSPObject();
		
		this.tour = Arrays.copyOf(tour.getIntArray(),size);
	}
	
	private TSP getTSPObject(){
		return cities;
	}
	
	public double getDistance(){
		return cities.cost(tour);
	}
	
	public void setCity(int index, int city){
		tour[index] = city;
	}
	
	public int getCity(int index){
		return tour[index];
	}
	
	public int[] getIntArray(){
		return tour;
	}
	
	public Tour subTour(int start, int end){
		Tour subtour = new Tour(end - start);
		for (int i = 0;i<size;i++){
			if (i >= start && i < end) subtour.setCity(i-start, tour[i]);
		}
		return subtour;
	}
	
	public int size(){
		return size;
	}
	
	public boolean containsCity(int city){
		for(int i = 0;i<size;i++){
			if (tour[i]==city) return true;
		}
		return false;
	}
	
	public void connectCities(int city1, int city2){
		IntLinkedList linkedList = new IntLinkedList(tour);
		
		
		int index1 = linkedList.indexOf(city1);
		int index2 = linkedList.indexOf(city2);
		
		//swap cities to tackle duplicates
		if (index2 < index1) {
			int temp = index1;
			index1 = index2;
			index2 = temp;
		}
		
		//get neessary noes fore redordering
		Node c1 = linkedList.getNode(index1);
		Node c2 = linkedList.getNode(index2);
		Node prec1 = linkedList.getNode(index1-1);
		Node postc1 = linkedList.getNode(index1+1);
		Node postc2 = linkedList.getNode(index2+1);
		
		//reorder linked list
		c2.setNext(c1);
		if(prec1 != null) prec1.setNext(postc1);
		else linkedList.setHead(postc1);
		if(postc2 != null) c1.setNext(postc2);
		else c1.setNext(null);
		
		tour = linkedList.toArray();	
		
	}

	@Override
	public String toString() {
		String out = "";
		for (int city : tour){
			out += String.valueOf(city)+"-";
		}
		out += "END";
		return out;
	}

	@Override
	public int compareTo(Tour arg0) {
		if (this.getDistance() > arg0.getDistance()){
			return 1;
		} else if (this.getDistance() == arg0.getDistance()){
			return 0;
		}
		return -1;
	}
	
	public void setIntArray(int[] tour){
		this.tour = tour;
	}
	
	public void toFile(String filename){
		int[] x = this.getIntArray();
		BufferedWriter outputWriter = null;
		  try {
			outputWriter = new BufferedWriter(new FileWriter(filename));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  for (int i = 0; i < x.length; i++) {
		    // Maybe:
		    try {
		    	 // Or:
			    outputWriter.write(Integer.toString(x[i]));
			    outputWriter.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		  }
		  try {
			outputWriter.flush();
			outputWriter.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
	}
	
}