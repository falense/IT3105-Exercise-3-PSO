package Problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import PSOSolver.Particles.AbstractParticle;
import PSOSolver.Particles.KnapsackParticle;
import PSOSolver.Topology.AbstractTopology;

public class KnapsackWeightValueProblem extends AbstractKnapsackProblem {
	
	@Override
	public double evaluate(Vector v) {
		Vector weightVector = VectorMath.elementMultiplication(v, packageWeights);
		Vector valueVector = VectorMath.elementMultiplication(v, packageValues);
		double weightSum = weightVector.sum();
		double valueSum = valueVector.sum();
		if (weightSum > 1000){
			valueSum = 0;
		}
		return -valueSum;
		
		
	}
	
	@Override
	public int getVectorSize() {
		return packageValues.size();
	}
	public KnapsackWeightValueProblem(){
		loadPackages();

		particleCount = 200;
		localAttraction = 1.8;
		globalAttraction = 1.0;
		iterationsCutoff = 1000;
		maxIterations = 1000;
	}
	
	@Override
	public AbstractParticle generateParticle(AbstractProblem problem,
			AbstractTopology topology, int particleIndex) {
		return new KnapsackParticle(problem, topology, particleIndex);
	}
}
