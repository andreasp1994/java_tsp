package javatsp_ga;

import java.util.ArrayList;
import java.util.Collections;

import javatsp.TSP;

public class Tour implements Comparable<Tour> {

	int [] tour;
	double fitness ;
	TSP cities;
	int size;
	
	public Tour(int size){
		this.size	=	size;
		cities 		= 	null;
		fitness		= 	0;
		tour 		= 	new int[size];
		
		//initialize tour;
		for (int i = 0;i<size;i++) { tour[i] = -1; }
	}
	
	public Tour(TSP tsp){
		size 		= tsp.size();
		cities 		= tsp;
		fitness 	= 0;
		
		tour = new int[tsp.size()];
		
		//Create an array list of the city indices
		ArrayList<Integer> cityIndices = new ArrayList<Integer>();
		for (int i=0;i<tsp.size();i++){ cityIndices.add(i); }
		
		//Generate random tour;
		Collections.shuffle(cityIndices);
		for(int i = 0;i<tsp.size();i++){ tour[i] = cityIndices.get(i);}
		
		fitness =  size/cities.cost(tour);
	}
	
	private Tour(Tour tour){
		size		=	tour.size();
		cities		= 	tour.getTSPObject();
		fitness 	= 	tour.getFitness();
		
		this.tour = tour.getIntArray();
	}
	
	private TSP getTSPObject(){
		return cities;
	}
	
	public double getFitness(){
		fitness =  size/cities.cost(tour);
		return fitness;
	}
	
	public double getDistance(){
		return cities.cost(tour);
	}
	
	public void setCity(int index, int city){
		tour[index] = city;
		fitness =  size/cities.cost(tour);
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
	
	public Tour clone(){
		return new Tour(this);
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
		if (fitness > arg0.getFitness()){
			return 1;
		} else if (fitness == arg0.getFitness()){
			return 0;
		}
		return -1;
	}
	
	public void setIntArray(int[] tour){
		this.tour = tour;
	}
	
	
}
