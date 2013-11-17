package PSOSolver.Particles;

import java.util.Random;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import PSOSolver.Topology.AbstractTopology;
import Problems.AbstractProblem;

public abstract class AbstractParticle {
	protected final AbstractTopology topology;
	protected final AbstractProblem problem;
	
	protected Vector position;
	protected Vector velocity;
	
	private double positionFitness;
	protected Vector bestPosition;
	protected double bestPositionFitness;
	protected int iteration;
	protected final int particleIndex;
	
	public AbstractParticle(AbstractProblem problem, AbstractTopology topology, int particleIndex){
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
		Random r = new Random(problem.getSeed());
		double scaleFactor = problem.maxVectorValue() - problem.minVectorValue();
		for (int index = 0; index < problem.getVectorSize(); index++){
			position.set(index, r.nextDouble()*scaleFactor-problem.minVectorValue());
			velocity.set(index, (r.nextDouble()-0.5)*10);
		}
	}
	protected Vector getBestPositionGlobal(){
		AbstractParticle[] neighbours = topology.getNeighbours(this);
		Vector bestNeighbourPosition = null;
		double bestNeighbourPositionValue = Double.MAX_VALUE;
		for (AbstractParticle p: neighbours){
			if (p == null) System.err.println("Error nul");
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
		Vector part1 = (new Vector(velocity)).multiply(problem.getInertiaWeight());
		Vector part2 = VectorMath.sub(bestPosition,position).multiply(problem.getLocalAttraction()*r.nextDouble());
		Vector part3 = VectorMath.sub(getBestPositionGlobal(),position).multiply(problem.getGlobalAttraction()*r.nextDouble());
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
	public void step(){
		updateVelocity();
		updatePosition();
		evaluatePosition();
		iteration++;
	}
	private void evaluatePosition(){
		setPositionFitness(problem.evaluate(position));
		if (getPositionFitness() < bestPositionFitness){
			bestPositionFitness = getPositionFitness();
			bestPosition = new Vector(position);
		}
	}
	public double getFitness(){
		return getPositionFitness();
	}
	public String toString(){
		return "Pos: " + position.toString() + ", Vel: " + velocity.toString() + ", Fitness " + getPositionFitness();
	}
	public double getPositionFitness() {
		return positionFitness;
	}
	public void setPositionFitness(double positionFitness) {
		this.positionFitness = positionFitness;
	}
	public Vector getBestPosition() {
		return bestPosition;
	}

}
