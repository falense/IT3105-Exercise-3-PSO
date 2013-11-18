package Problems;

import PSOSolver.Particles.Particle;
import PSOSolver.Topology.AbstractTopology;

public abstract class CircleProblem extends AbstractProblem {
	
	public boolean isKnapSack(){
		return false;
	}
	@Override
	public double maxVectorValue() {
		return 100.0;
	}
	
	
	@Override
	public double minVectorValue() {
		return -100.0;
	}
	

	@Override
	public Particle generateParticle(AbstractProblem problem,
			AbstractTopology topology, int particleIndex) {
		return new Particle(problem, topology, particleIndex);
	}
}
