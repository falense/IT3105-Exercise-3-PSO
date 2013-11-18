package Problems;

import LinearAlgebra.Vector;

public class CircleProblemND extends CircleProblem {
		private final int N;
		public CircleProblemND(int N){
			localAttraction = 1.8;
			globalAttraction = 1.6;
			inertiaWeight = 1.0;
			iterationsCutoff = 1000;
			this.N = N;
			particleCount = 10*N;
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
			return N;
		}




	}
