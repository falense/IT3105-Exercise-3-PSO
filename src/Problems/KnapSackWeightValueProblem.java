package Problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import PSOSolver.KnapSackParticle;
import PSOSolver.Particle;
import Topology.AbstractTopology;
class KnapSackPackage{
	public final double value;
	public final double weight;
	public KnapSackPackage(double value, double weight) {
		this.value = value;
		this.weight = weight;
	}
}
public class KnapSackWeightValueProblem extends AbstractProblem {
	private Vector packageWeights;
	private Vector packageValues;
	private void loadPackages(){
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
	            if (listPackages.size() > 100) break;
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
	@Override
	public double evaluate(Vector v) {
		Vector weightVector = VectorMath.elementMultiplication(v, packageWeights);
		Vector valueVector = VectorMath.elementMultiplication(v, packageValues);
		double weightSum = weightVector.sum();
		double valueSum = valueVector.sum();
		if (weightSum > 1000){
			return 0;
		}
		
		return valueSum;
	}
	
	@Override
	public int getVectorSize() {
		return packageValues.size();
	}
	public KnapSackWeightValueProblem(){
		loadPackages();
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
	@Override
	public Particle generateParticle(AbstractProblem problem,
			AbstractTopology topology, int particleIndex) {
		return new KnapSackParticle(problem, topology, particleIndex);
	}
}
