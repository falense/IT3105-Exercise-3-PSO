package Problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;

public abstract class AbstractKnapSackProblem extends AbstractProblem {
	class KnapSackPackage{
		public final double value;
		public final double weight;
		public KnapSackPackage(double value, double weight) {
			this.value = value;
			this.weight = weight;
		}
	}
	
	
	@Override
	public double maxVectorValue() {
		// TODO Auto-generated method stub
		return 1.0;
	}

	@Override
	public double minVectorValue() {
		// TODO Auto-generated method stub
		return 0.0;
	}
		

	public boolean isKnapSack(){
		return true;
	}
	
	protected Vector packageWeights;
	protected Vector packageValues;
	protected void loadPackages(){
	    try {
			BufferedReader br = new BufferedReader(new FileReader("pso-packages.txt"));
			String line = br.readLine();
			LinkedList<KnapSackPackage> listPackages = new LinkedList<KnapSackPackage>();
	        while (line != null) {
	        	String linePackage[] = line.split(",");
	        	double value = Double.parseDouble(linePackage[0]);
	        	double weight = Double.parseDouble(linePackage[1]);
	        	KnapSackPackage p = new KnapSackPackage(value, weight);
	        	listPackages.add(p);
	            line = br.readLine();
	            //if (listPackages.size() > 100) break;
	        }
	        packageWeights = new Vector(listPackages.size());
	        packageValues = new Vector(listPackages.size());
	        for (int index = 0; index < listPackages.size(); index++){
	        	packageWeights.set(index, listPackages.get(index).weight);
	        	packageValues.set(index,listPackages.get(index).value);
	        }
	        br.close();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}
	public double getValue(Vector v){

		Vector valueVector = VectorMath.elementMultiplication(v, packageValues);
		double valueSum = valueVector.sum();
		return valueSum;
	}
	public double getWeight(Vector v){

		Vector weightVector = VectorMath.elementMultiplication(v, packageWeights);
		double weightSum = weightVector.sum();
		return weightSum;
	}
	public void setAttraction(double local, double global){
		this.localAttraction = local;
		this.globalAttraction = global;
	}
}
