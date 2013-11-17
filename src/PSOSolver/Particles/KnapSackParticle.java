package PSOSolver.Particles;

import java.util.Random;

import LinearAlgebra.Vector;
import LinearAlgebra.VectorMath;
import PSOSolver.Topology.AbstractTopology;
import Problems.AbstractProblem;

public class KnapSackParticle extends AbstractParticle {

	public KnapSackParticle(AbstractProblem problem, AbstractTopology topology, int particleIndex) {
		super(problem, topology, particleIndex);
	}
	@Override
	protected void initializeParticle(){
		Random r = new Random(problem.getSeed());
		/*double fitness = problem.evaluate(position);
		int index = -1;
		while(fitness < 1.0){
			index = r.nextInt(problem.getVectorSize());
			position.set(index, 1.0);
			fitness = problem.evaluate(position);
			System.out.println(fitness);
		}
		position.set(index,0.0);*/
		int numFlips = 4;
		for (int i = 0; i < numFlips; i++)
			position.set(r.nextInt(problem.getVectorSize()), 1.0);
		
	}
	void updateVelocity() {
			Random r = new Random(problem.getSeed());
			r.nextDouble();
			Vector part1 = (new Vector(velocity)).multiply(problem.getInertiaWeight());
			Vector part2 = VectorMath.sub(bestPosition,position).multiply(problem.getLocalAttraction()*r.nextDouble());
			Vector part3 = VectorMath.sub(getBestPositionGlobal(),position).multiply(problem.getGlobalAttraction()*r.nextDouble());
			Vector newVelocity = VectorMath.add(part1,VectorMath.add(part2,part3));
			double absSpeed = Math.sqrt(VectorMath.euclidianDistance(newVelocity,new Vector(velocity.size())));
			
			for (int index = 0; index < velocity.size(); index++){
				if (velocity.get(index) > 5.0)
					velocity.set(index, 5.0);
				if (velocity.get(index) < -5.0){
					velocity.set(index, -5.0);
				}
			}
			velocity = newVelocity;
			//System.out.println(part1.toString() + " + " + part2.toString() + " + " + part3.toString());
		}
	private double sigmoid(double v){
		double t = 1.0 + Math.exp(-v);
		return 1.0/t;
	}
	void updatePosition() {
		Random r = new Random();
		int c = 0;
		for (int index = 0; index < position.size(); index++){
			double old = position.get(index);
			if (r.nextDouble() < sigmoid(velocity.get(index))){
				position.set(index, 1);
			}
			else{
				position.set(index, 0);
			}
			if (Math.abs(old-position.get(index)) > 0.5){
				c++;
				if (c > 4) break;
			}
		}
		
	}
}
