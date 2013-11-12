package LinearAlgebra;

import javax.swing.Spring;

public class Vector {
	private double [] data;
	
	public Vector(int N){
		data = new double[N];

		for (int index = 0; index < size(); index++){
			data[index] = 0;
		}
	}

	public int size() {
		return data.length;
	}
	
	public double get(int index) {
		if (index < 0 || index >= size()){
			System.err.print("Index out of bounds when getting element from vector");
			return -1;
		}
		return data[index];
	}
	public void set(int index, double value){
		if (index < 0 || index >= size()){
			System.err.print("Index out of bounds when getting element from vector");
			return;
		}
		data[index] = value;
	}
	public Vector multiply(double value){
		for (int index = 0; index < size(); index++){
			data[index] = data[index]*value;
		}
		return this;
	}
	public Vector equals(Vector v){
		if (size() != v.size()){
			System.err.println("Vector size differs when setting vectors equal");
			return null;
		}
		for (int index = 0; index < size(); index++){
			data[index] = v.data[index];
		}
		return this;
	}
	public void copy(Vector v){
		equals(v);
	}
	public Vector(Vector v){
		data = new double[v.size()];
		copy(v);
	}
	public String toString(){
		String r = "(";
		for (int index = 0; index < size(); index++){
			r += data[index] + " ";
		}
		r += ")";
		return r;
	}
	public double sum(){
		double sum = 0;
		for (int index = 0; index < size(); index++){
			sum += data[index];
		}
		return sum;
	}
	
}
