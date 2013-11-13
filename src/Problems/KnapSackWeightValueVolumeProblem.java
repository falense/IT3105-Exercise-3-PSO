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
import PSOSolver.Particles.KnapSackParticle;
import PSOSolver.Particles.Particle;
import PSOSolver.Topology.AbstractTopology;


public class KnapSackWeightValueVolumeProblem extends AbstractKnapSackProblem {
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
			valueSum -= (weightSum-1000);
		}
		if (volumeSum > 1000){
			valueSum -= (volumeSum-1000);
		}
		return -valueSum;
	}
	
	@Override
	public int getVectorSize() {
		return packageValues.size();
	}
	public KnapSackWeightValueVolumeProblem(){
		loadPackages();
		packageVolumes = new Vector(packageValues.size());
		Random r = new Random();
		for (int index = 0; index < packageValues.size(); index++){
			packageVolumes.set(index, r.nextInt(4)+1);
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
	@Override
	public AbstractParticle generateParticle(AbstractProblem problem,
			AbstractTopology topology, int particleIndex) {
		return new KnapSackParticle(problem, topology, particleIndex);
	}
}
