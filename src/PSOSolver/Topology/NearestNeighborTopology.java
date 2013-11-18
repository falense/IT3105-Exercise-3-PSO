package PSOSolver.Topology;

import java.util.Arrays;

import LinearAlgebra.VectorMath;
import PSOSolver.Particles.AbstractParticle;

public class NearestNeighborTopology extends AbstractTopology {
	class DistanceParticle implements Comparable<DistanceParticle>{
		public final Double distance;
		public final AbstractParticle particle;
		public DistanceParticle(double distance, AbstractParticle particle){
			this.distance = distance;
			this.particle = particle;
		}
		public int compareTo(DistanceParticle p){
			return distance.compareTo(p.distance);
		}
	}
	private int neighbourCount;
	public NearestNeighborTopology(int neighbourCount) {
		this.neighbourCount = neighbourCount;
	}

	@Override
	public AbstractParticle[] getNeighbours(AbstractParticle me) {
		DistanceParticle [] dlist = new DistanceParticle[particles.length];
		for (int i = 0; i < particles.length; i++){
			dlist[i] = new DistanceParticle(VectorMath.euclidianDistance(me.getPosition(), particles[i].getPosition()), particles[i]);
		}
		Arrays.sort(dlist);
		AbstractParticle [] neighbours = new AbstractParticle[neighbourCount];
		for (int i = 0; i < neighbourCount; i++){
			neighbours[i] = dlist[1+i].particle;
			//System.err.println(dlist[1+i].distance + " " + dlist[particles.length-1-i].distance);
		}
		return neighbours;
	}

}
