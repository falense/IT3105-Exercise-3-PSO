package Problems;

import PSOSolver.Particles.Particle;
import PSOSolver.Topology.AbstractTopology;

public abstract class CircleProblem extends AbstractProblem {
	@Override
	public double maxVectorValue() {
		return 100;
	}

	@Override
	public double minVectorValue() {
		return -100;
	}
	

	@Override
	public Particle generateParticle(AbstractProblem problem,
			AbstractTopology topology, int particleIndex) {
		return new Particle(problem, topology, particleIndex);
	}
}
