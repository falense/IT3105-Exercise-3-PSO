package Problems;

import LinearAlgebra.Vector;
import PSOSolver.Particles.AbstractParticle;
import PSOSolver.Topology.AbstractTopology;

public abstract class AbstractProblem {
	public abstract double evaluate(Vector v);

	public abstract int getVectorSize();
	public abstract double maxVectorValue();
	public abstract double minVectorValue();
	public abstract AbstractParticle generateParticle(AbstractProblem problem, AbstractTopology topology, int i);
}
