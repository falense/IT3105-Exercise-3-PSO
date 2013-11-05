package Problems;

import LinearAlgebra.Vector;

public class CircleProblem2D extends CircleProblem {

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
