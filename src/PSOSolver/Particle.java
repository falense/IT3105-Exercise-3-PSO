package PSOSolver;

import java.util.Random;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import Problems.AbstractProblem;
import Topology.AbstractTopology;

public class Particle {
	protected final AbstractTopology topology;
	protected final AbstractProblem problem;
	
	protected double globalAttraction;
	protected double localAttraction;
	protected double inertiaWeight;
	
	protected Vector position;
	protected Vector velocity;
	
	protected double positionFitness;
	protected Vector bestPosition;
	protected double bestPositionFitness;
	protected int iteration;
	protected final int particleIndex;
	
	public Particle(AbstractProblem problem, AbstractTopology topology, int particleIndex){
		this.topology = topology;
		this.problem = problem;
		this.particleIndex = particleIndex;
		int vectorSize = problem.getVectorSize();
		position = new Vector(vectorSize);
		bestPosition = position;
		velocity = new Vector(vectorSize);
		initializeParticle();
		bestPositionFitness = Double.MAX_VALUE;
		iteration = 0;
		evaluatePosition();
	}
	protected void initializeParticle(){
		Random r = new Random();
		double scaleFactor = problem.maxVectorValue() - problem.minVectorValue();
		for (int index = 0; index < problem.getVectorSize(); index++){
			position.set(index, r.nextDouble()*scaleFactor-problem.minVectorValue());
			velocity.set(index, (r.nextDouble()-0.5)*10);
		}
	}
	protected Vector getBestPositionGlobal(){
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
	public double getBestPositionValue(){
		return bestPositionFitness;
	}
	
	void updateVelocity() {
		Random r = new Random();
		r.nextDouble();
		Vector part1 = (new Vector(velocity)).multiply(inertiaWeight);
		Vector part2 = VectorMath.sub(bestPosition,position).multiply(localAttraction*r.nextDouble());
		Vector part3 = VectorMath.sub(getBestPositionGlobal(),position).multiply(globalAttraction*r.nextDouble());
		Vector newVelocity = VectorMath.add(part1,VectorMath.add(part2,part3));
		double absSpeed = Math.sqrt(VectorMath.euclidianDistance(newVelocity,new Vector(velocity.size())));
		if (absSpeed > 10){
			newVelocity.multiply(10.0/absSpeed);
		}
		velocity = newVelocity;
		//System.out.println(part1.toString() + " + " + part2.toString() + " + " + part3.toString());
	}
	void updatePosition() {
		position = VectorMath.add(position, velocity);
		for (int index = 0; index < position.size(); index++){
			if (position.get(index) > problem.maxVectorValue()){
				position.set(index, problem.maxVectorValue());
			}
			if (position.get(index) < problem.minVectorValue()){
				position.set(index, problem.minVectorValue());
			}
		}
	}
	public Vector getPosition(){
		return position;
	}
	void step(){
		updateVelocity();
		updatePosition();
		evaluatePosition();
		iteration++;
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
