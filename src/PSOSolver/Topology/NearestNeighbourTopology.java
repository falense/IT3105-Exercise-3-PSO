package PSOSolver.Topology;

import LinearAlgebra.VectorMath;
import PSOSolver.Particles.AbstractParticle;
import PSOSolver.Particles.Particle;

public class NearestNeighbourTopology extends AbstractTopology {
	private int neighbourCount;
	public NearestNeighbourTopology(int neighbourCount) {
		this.neighbourCount = neighbourCount;
	}

	@Override
	public AbstractParticle[] getNeighbours(AbstractParticle me) {
		AbstractParticle [] neighbours = new AbstractParticle[neighbourCount];
		for (int i = 0; i < neighbourCount; i++){
			if (i == 0){
				neighbours[i] = getClosestParticle(me, 0);
			}
			else{
				double minDistance = VectorMath.euclidianDistance(me.getPosition(), neighbours[i-1].getPosition());
				neighbours[i] = getClosestParticle(me, minDistance);
			}
		}
		return neighbours;
	}
	private AbstractParticle getClosestParticle(AbstractParticle me, double minDistance){
		AbstractParticle closest = null;
		double closestDistance = Double.MAX_VALUE;
		for (AbstractParticle p: particles){
			double distance = VectorMath.euclidianDistance(me.getPosition(), p.getPosition());
			if (distance >= minDistance && closestDistance > distance){
				closestDistance = distance;
				closest = p;
			}
		}
		return closest;
	}

}
