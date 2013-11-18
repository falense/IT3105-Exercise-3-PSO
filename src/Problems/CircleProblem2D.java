package Problems;

import LinearAlgebra.Vector;
import PSOSolver.Particles.Particle;
import PSOSolver.Topology.AbstractTopology;

public class CircleProblem2D extends CircleProblem {
	public CircleProblem2D(){
		particleCount = 10;
		localAttraction = 1.8;
		globalAttraction = 1.6;
		inertiaWeight = 1.0;
		iterationsCutoff = 20;
	}
	@Override
	public double evaluate(Vector v) {
		double fitness = 0;
		for (int index = 0; index < v.size(); index++){
			fitness += Math.pow(v.get(index),2);
		}
		return fitness;
	}

	@Override
	public int getVectorSize() {
		return 2;
	}


}
