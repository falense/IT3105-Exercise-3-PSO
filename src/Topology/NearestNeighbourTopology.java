package Topology;

import LinearAlgebra.VectorMath;
import PSOSolver.Particle;

public class NearestNeighbourTopology extends AbstractTopology {
	private int neighbourCount;
	public NearestNeighbourTopology(int neighbourCount) {
		this.neighbourCount = neighbourCount;
	}

	@Override
	public Particle[] getNeighbours(Particle me) {
		Particle [] neighbours = new Particle[neighbourCount];
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
	private Particle getClosestParticle(Particle reference, double minDistance){
		Particle closest = null;
		double closestDistance = Double.MAX_VALUE;
		for (Particle p: particles){
			double distance = VectorMath.euclidianDistance(reference.getPosition(), p.getPosition());
			if (distance > minDistance && closestDistance > distance){
				closestDistance = distance;
				closest = p;
			}
		}
		return closest;
	}

}
