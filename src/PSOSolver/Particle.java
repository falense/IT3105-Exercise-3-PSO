package PSOSolver;

import java.util.Random;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import Problems.AbstractProblem;
import Topology.AbstractTopology;

public class Particle {
	private final AbstractTopology topology;
	private final AbstractProblem problem;
	private Vector position;
	private double positionFitness;
	private Vector bestPosition;
	private double bestPositionFitness;
	private Vector velocity;
	private double globalAttraction;
	private double localAttraction;
	private double inertiaWeight;
	public Particle(AbstractProblem problem, AbstractTopology topology){
		this.topology = topology;
		this.problem = problem;
		int vectorSize = problem.getVectorSize();
		position = new Vector(vectorSize);
		bestPosition = position;
		velocity = new Vector(vectorSize);
		Random r = new Random();
		double scaleFactor = problem.maxVectorValue() - problem.minVectorValue();
		for (int index = 0; index < vectorSize; index++){
			position.set(index, r.nextDouble()*scaleFactor-problem.minVectorValue());
			velocity.set(index, (r.nextDouble()-0.5)*10);
		}
		System.out.println("New particle: ");
		System.out.println(position.toString());
		System.out.println(velocity.toString());
	}
	private Vector getBestPositionGlobal(){
		Particle [] neighbours = topology.getNeighbours(this);
		Vector bestNeighbourPosition = null;
		double bestNeighbourPositionValue = Double.MAX_VALUE;
		for (Particle p: neighbours){
			if (p.bestPositionFitness < bestNeighbourPositionValue){
				bestNeighbourPositionValue = p.bestPositionFitness;
				bestNeighbourPosition = p.bestPosition;
			}
		}
		return bestNeighbourPosition;
	}
	
	void updateVelocity() {
		Random r = new Random();
		r.nextDouble();
		Vector part1 = (new Vector(velocity)).multiply(inertiaWeight);
		Vector part2 = VectorMath.sub(bestPosition,position).multiply(localAttraction*r.nextDouble());
		Vector part3 = VectorMath.sub(getBestPositionGlobal(),position).multiply(globalAttraction*r.nextDouble());
		Vector newVelocity = VectorMath.add(part1,VectorMath.add(part2,part3));
		double absSpeed = VectorMath.euclidianDistance(newVelocity,new Vector(velocity.size()));
		if (absSpeed > 10){
			newVelocity.multiply(10.0/absSpeed);
		}
		velocity = newVelocity;
		//System.out.println(part1.toString() + " + " + part2.toString() + " + " + part3.toString());
	}
	void updatePosition() {
		position = VectorMath.add(position, velocity);
	}
	public Vector getPosition(){
		return position;
	}
	void step(){
		updateVelocity();
		updatePosition();
		evaluatePosition();
	}
	private void evaluatePosition(){
		positionFitness = problem.evaluate(position);
		if (positionFitness < bestPositionFitness){
			bestPositionFitness = positionFitness;
			bestPosition = position;
		}
	}
	public void setAttraction(double localAttraction, double globalAttraction) {
		this.localAttraction = localAttraction;
		this.globalAttraction = globalAttraction;
		
	}
	public void setIntertiaWeight(double inertiaWeight){
		this.inertiaWeight = inertiaWeight;
	}
	public double getFitness(){
		return positionFitness;
	}
	public String toString(){
		return "Pos: " + position.toString() + ", Vel: " + velocity.toString() + ", Fitness " + positionFitness;
	}
}
