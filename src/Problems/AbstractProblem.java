package Problems;

import LinearAlgebra.Vector;

public abstract class AbstractProblem {
	public abstract double evaluate(Vector v);

	public abstract int getVectorSize();
	public abstract double maxVectorValue();
	public abstract double minVectorValue();
}
