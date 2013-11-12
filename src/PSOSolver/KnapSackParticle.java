package PSOSolver;

import java.util.Random;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import Problems.AbstractProblem;
import Topology.AbstractTopology;

public class KnapSackParticle extends Particle {

	public KnapSackParticle(AbstractProblem problem, AbstractTopology topology, int particleIndex) {
		super(problem, topology, particleIndex);
	}
	@Override
	protected void initializeParticle(){
		double fitness = problem.evaluate(position);
		Random r = new Random();
		int index = -1;
		while(fitness != 0){
			index = r.nextInt()%problem.getVectorSize();
			position.set(index, 1.0);
			fitness = problem.evaluate(position);
		}
		position.set(index,0.0);
	}
	void updateVelocity() {
			Random r = new Random();
			r.nextDouble();
			Vector part1 = (new Vector(velocity)).multiply(inertiaWeight);
			Vector part2 = VectorMath.sub(bestPosition,position).multiply(localAttraction*r.nextDouble());
			Vector part3 = VectorMath.sub(getBestPositionGlobal(),position).multiply(globalAttraction*r.nextDouble());
			Vector newVelocity = VectorMath.add(part1,VectorMath.add(part2,part3));
			double absSpeed = Math.sqrt(VectorMath.euclidianDistance(newVelocity,new Vector(velocity.size())));
			
			for (int index = 0; index < velocity.size(); index++){
				if (velocity.get(index) > 1.0)
					velocity.set(index, 1.0);
				if (velocity.get(index) < 0.0){
					velocity.set(index, 0.0);
				}
			}
			velocity = newVelocity;
			//System.out.println(part1.toString() + " + " + part2.toString() + " + " + part3.toString());
		}
	private double sigmoid(double v, double k, double id){
		double t = 1.0 + Math.exp(v);
		return 1.0/t;
	}
	void updatePosition() {
		Random r = new Random();
		for (int index = 0; index < position.size(); index++){
			if (r.nextDouble() < sigmoid(velocity.get(index),iteration,index*particleIndex)){
				position.set(index, 0);
			}
			else{
				position.set(index, 1);
			}
		}
		
	}
}
