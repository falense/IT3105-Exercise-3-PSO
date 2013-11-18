package Problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import PSOSolver.Particles.AbstractParticle;
import PSOSolver.Particles.KnapsackParticle;
import PSOSolver.Particles.CircleParticle;
import PSOSolver.Topology.AbstractTopology;


public class KnapsackWeightValueVolumeProblem extends AbstractKnapsackProblem {
	private Vector packageVolumes;
	
	@Override
	public double evaluate(Vector v) {
		Vector weightVector = VectorMath.elementMultiplication(v, packageWeights);
		Vector valueVector = VectorMath.elementMultiplication(v, packageValues);
		Vector volumeVector = VectorMath.elementMultiplication(v, packageVolumes);
		double weightSum = weightVector.sum();
		double valueSum = valueVector.sum();
		double volumeSum = volumeVector.sum();
		if (weightSum > 1000){
			valueSum = 0;
		}
		if (volumeSum > 1000){
			valueSum = 0;
		}
		return -valueSum;
	}
	
	@Override
	public int getVectorSize() {
		return packageValues.size();
	}
	public KnapsackWeightValueVolumeProblem(){
		loadPackages();
		packageVolumes = new Vector(packageValues.size());
		Random r = new Random(getSeed());
		for (int index = 0; index < packageValues.size(); index++){
			packageVolumes.set(index, r.nextInt(4)+1);
		}
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
