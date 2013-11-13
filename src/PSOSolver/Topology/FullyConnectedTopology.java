package PSOSolver.Topology;

import PSOSolver.Particles.AbstractParticle;

public class FullyConnectedTopology extends AbstractTopology {

	public FullyConnectedTopology() {
	}

	@Override
	public AbstractParticle[] getNeighbours(AbstractParticle me) {
		return particles;
	}

}
