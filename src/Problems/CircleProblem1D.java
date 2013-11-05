package Problems;

import LinearAlgebra.Vector;



public class CircleProblem1D extends CircleProblem {

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
