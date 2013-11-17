package Problems;

import LinearAlgebra.Vector;



public class CircleProblem1D extends CircleProblem {
	public CircleProblem1D(){
		particleCount = 3;
		localAttraction = 0.5;
		globalAttraction = 1.6;
		iterationsCutoff = 20;
		inertiaWeight = 1.0;
	}
	@Override
	public double evaluate(Vector v) {
		if (v.size() != 1){
			System.err.println("Invalid vector length for evaluation in CircleProblem1D");
			return Double.MAX_VALUE;
		}
		double r = v.get(0);
		return r*r;
	}

	@Override
	public int getVectorSize() {
		return 1;
	}



}
