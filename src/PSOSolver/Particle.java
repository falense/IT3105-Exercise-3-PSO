package PSOSolver;

import Position.AbstractPosition;
import Topology.AbstractTopology;
import Velocity.AbstractVelocity;

public class Particle {
	private AbstractTopology topology;
	private AbstractPosition position;
	private AbstractPosition bestPosition;
	private AbstractVelocity velocity;
	public Particle(){
		
	}
	void updateVelocity() {
	}
	void updatePosition() {
		
	}
	public AbstractPosition getPosition(){
		return position;
	}
	public void setAttraction(double localAttraction, double globalAttraction) {
		// TODO Auto-generated method stub
		
	}
}
