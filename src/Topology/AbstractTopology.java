package Topology;

import PSOSolver.Particle;


public abstract class AbstractTopology {
	public abstract Particle[] getNeighbours(Particle me);
}
