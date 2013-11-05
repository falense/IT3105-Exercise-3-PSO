package Topology;

import PSOSolver.Particle;

public class FullyConnectedTopology extends AbstractTopology {

	public FullyConnectedTopology() {
	}

	@Override
	public Particle[] getNeighbours(Particle me) {
		return particles;
	}

}
