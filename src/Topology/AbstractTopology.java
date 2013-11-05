package Topology;

import PSOSolver.Particle;


public abstract class AbstractTopology {
	public abstract Particle[] getNeighbours(Particle me);
	public Particle[] particles;
	public AbstractTopology(){
	}
	public void setParticles(Particle [] particles){
		this.particles = particles;
	}
}
