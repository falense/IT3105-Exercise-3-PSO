package Problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import PSOSolver.Particles.AbstractParticle;
import PSOSolver.Particles.KnapSackParticle;
import PSOSolver.Topology.AbstractTopology;

public class KnapSackWeightValueProblem extends AbstractKnapSackProblem {
	
	private double sigmoid(double v){
		double t = 1.0 + Math.exp(-v);
		return 1.0/t;
	}
	@Override
	public double evaluate(Vector v) {
		
		Vector weightVector = VectorMath.elementMultiplication(v, packageWeights);
		double weightSum = weightVector.sum();
		if (weightSum > 1000){
			//valueSum -= (weightSum-1000);
			return 0;
		}
		
		Vector valueVector = VectorMath.elementMultiplication(v, packageValues);
		double valueSum = valueVector.sum();
		/*double valueToWeightContrib = 2*sigmoid((valueSum/weightSum)-1)-1;
		double weightContrib = 2*sigmoid(-(weightSum-1000)/200.0)-1;
		double valueContrib = 2*sigmoid((valueSum-1000)/250)-1;
		return -(valueToWeightContrib+weightContrib+valueContrib)*100.0;*/
		
/*
		Vector weightValueVector = VectorMath.elementDivision(packageValues, packageWeights).normalize().multiply(100);
		double sum = VectorMath.elementMultiplication(v, weightValueVector).sum();
		return -sum;
		*/
		return -valueSum;
		
		
	}
	
	@Override
	public int getVectorSize() {
		return packageValues.size();
	}
	public KnapSackWeightValueProblem(){
		loadPackages();

		particleCount = 50;
		localAttraction = 1.0;
		globalAttraction = 0.6;
		iterationsCutoff = 1000;
	}
	@Override
	public double getInertiaWeight(){
		return 1.0 - 0.6 * (iteration/1000.0);
	}
	@Override
	public AbstractParticle generateParticle(AbstractProblem problem,
			AbstractTopology topology, int particleIndex) {
		return new KnapSackParticle(problem, topology, particleIndex);
	}
}
