package Problems;

import LinearAlgebra.Vector;
import PSOSolver.Particles.AbstractParticle;
import PSOSolver.Topology.AbstractTopology;

public abstract class AbstractProblem {

	private int seed = 0;
	protected double localAttraction = 1.8;
	protected double globalAttraction = 1.0;
	protected int iterationsCutoff = 100;
	protected int particleCount = 100;
	protected double inertiaWeight = 1.0; 
	protected int iteration = 0;
	protected int maxIterations = 1000;
	
	public double getLocalAttraction(){
		return localAttraction;
	}
	public double getGlobalAttraction(){
		return globalAttraction;
	}
	public int getIterationsCutoff(){
		return iterationsCutoff;
	}
	public int getParticleCount(){
		return particleCount;
	}
	public void setIteration(int iteration){
		this.iteration = iteration;
	}
	public int getMaxIterations(){
		return maxIterations;
	}
	
	protected boolean variableInertia = false;
	public void setVariableInertia(boolean v){
		variableInertia = v;
	}
	public double getInertiaWeight(){
		if (variableInertia)
			return 1.0 - 0.6 * (iteration/maxIterations);
		else
			return inertiaWeight;
	}
	public abstract double evaluate(Vector v);
	public abstract boolean isKnapSack();
	public int getSeed(){
		return seed++;
	}
	public abstract int getVectorSize();
	public abstract double maxVectorValue();
	public abstract double minVectorValue();
	public abstract AbstractParticle generateParticle(AbstractProblem problem, AbstractTopology topology, int i);
}
