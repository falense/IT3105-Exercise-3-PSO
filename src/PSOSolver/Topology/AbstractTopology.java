package PSOSolver.Topology;

import PSOSolver.Particles.AbstractParticle;
import PSOSolver.Particles.CircleParticle;


public abstract class AbstractTopology {
	public abstract AbstractParticle[] getNeighbours(AbstractParticle abstractParticle);
	public AbstractParticle[] particles;
	public AbstractTopology(){
	}
	public void setParticles(AbstractParticle[] particles2){
		this.particles = particles2;
	}
}
