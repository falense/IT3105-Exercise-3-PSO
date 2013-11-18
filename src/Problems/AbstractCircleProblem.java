package Problems;

import PSOSolver.Particles.CircleParticle;
import PSOSolver.Topology.AbstractTopology;

public abstract class AbstractCircleProblem extends AbstractProblem {
	
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
	public CircleParticle generateParticle(AbstractProblem problem,
			AbstractTopology topology, int particleIndex) {
		return new CircleParticle(problem, topology, particleIndex);
	}
}
