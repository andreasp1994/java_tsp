package javatsp_sa;

import javatsp.TSP;

public class Edge implements Comparable<Edge>{

	private int city1;
	private int city2;
	private double distance;
	
	public Edge(int c1, int c2, TSP tsp){
		city1 = c1;
		city2 = c2;
		distance = tsp.cost(city1, city2);
	}
	
	/**************************************
	 * 
	 * Experimenting with overlaping edges
	 * 
	 **************************************/
	private Edge overlaps;
	public boolean overlaps (){
		return overlaps != null;
	}
	
	public Edge getOverlapping(){
		return overlaps;
	}
	
	public void setOverlapping(Edge e){
		overlaps = e;
	}
	/*
	 * ***************************************
	 */
	
	public double getDistance(){
		return distance;
	}

	public int getCity1(){
		return city1;
	}
	
	public int getCity2(){
		return city2;
	}
	
	@Override
	public int compareTo(Edge edge2) {
		if (getDistance() < edge2.getDistance()) return -1;
		else if (getDistance() == edge2.getDistance()) return 0;
		else return 1;
	}
}
