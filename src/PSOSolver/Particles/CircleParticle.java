package PSOSolver.Particles;

import java.util.Random;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import PSOSolver.Topology.AbstractTopology;
import Problems.AbstractProblem;

public class CircleParticle extends AbstractParticle{

	public CircleParticle(AbstractProblem problem, AbstractTopology topology,
			int particleIndex) {
		super(problem, topology, particleIndex);
	}
}
