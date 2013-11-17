package LinearAlgebra;

import javax.swing.Spring;

import PSOSolver.ParticleManagerGUI;

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
			System.err.println("Index out of bounds when getting element from vector, vector length: " + data.length + " index: " + index);
			return -1;
		}
		return data[index];
	}
	public void set(int index, double value){
		if (index < 0 || index >= size()){
			System.err.println("Index out of bounds when setting element from vector, vector length: " + data.length + " index: " + index);
			return;
		}
		data[index] = value;
	}
	public Vector normalize(){
		double sum = sum();
		for (int index = 0; index < size(); index++){
			data[index] = data[index]/sum;
		}
		return this;
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

	public static void main(String[] args) {
		Vector v = new Vector(10);
		for (int i = 0; i < 10; i++){
			v.set(i, 1);
		}
		System.out.println(v.toString());
		v.multiply(5);
		System.out.println(v.toString());
		v.multiply(-1);
		System.out.println(v.toString());
		System.out.println("Sum: " + v.sum());
		System.out.println(new Vector(v).toString());
		
		
		
		Vector v2 = new Vector(10);
		for (int i = 0; i < 10; i++){
			v2.set(i,i);
			v.set(i,1);
		}
		System.out.println(v.toString());
		System.out.println(v2.toString());
		System.out.println(VectorMath.add(v, v2).toString());
		System.out.println("v-v2" + VectorMath.sub(v,v2).toString());
		System.out.println("v2-v" + VectorMath.sub(v2,v).toString());
		
		double s = v2.sum();
		System.out.println(v2.multiply(1.0/s));
		System.out.println("Sum: " + v2.sum());
		
	}
}
