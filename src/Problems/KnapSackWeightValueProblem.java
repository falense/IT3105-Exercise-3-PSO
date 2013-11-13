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
	@Override
	public double evaluate(Vector v) {
		Vector weightVector = VectorMath.elementMultiplication(v, packageWeights);
		Vector valueVector = VectorMath.elementMultiplication(v, packageValues);
		double weightSum = weightVector.sum();
		double valueSum = valueVector.sum();
		if (weightSum > 1000){
			valueSum -= (weightSum-1000);
		}
		
		return -valueSum;
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
	public AbstractParticle generateParticle(AbstractProblem problem,
			AbstractTopology topology, int particleIndex) {
		return new KnapSackParticle(problem, topology, particleIndex);
	}
}
