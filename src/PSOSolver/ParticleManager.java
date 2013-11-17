package PSOSolver;

import LinearAlgebra.Vector;
import PSOSolver.Particles.AbstractParticle;
import PSOSolver.Topology.AbstractTopology;
import PSOSolver.Topology.FullyConnectedTopology;
import Problems.AbstractKnapSackProblem;
import Problems.AbstractProblem;
import Problems.KnapSackWeightValueVolumeProblem;

public class ParticleManager {
	private AbstractParticle []particles;
	private ParticleManagerGUI PMGUI;
	private AbstractProblem problem;
	public ParticleManager(AbstractProblem problem, AbstractTopology topology){
		this.problem = problem;
		int numParticles = problem.getParticleCount();
		
		PMGUI = new ParticleManagerGUI();
		PMGUI.setup();
		
		particles = new AbstractParticle[numParticles];
		for (int i = 0; i < numParticles; i++){
			particles[i] = problem.generateParticle(problem,topology,i);
		}
		topology.setParticles(particles);
	}
	public void step(){
		for (AbstractParticle p: particles){
			p.step();
			//System.out.println(p.toString());
		}
		//System.out.println();
	}
	
	public void solve(){
		double lastIterationBestFitness = 0.0;
		int iterationBestFitnessChanged = 0;
		for (int iteration = 0; iteration < problem.getMaxIterations(); iteration++){
			problem.setIteration(iteration);
			step();
			System.out.println("Iteration: " + iteration);
			Vector globalBestPosition = getGlobalBestPosition();
			double globalBestFitness = problem.evaluate(globalBestPosition);
			PMGUI.addValue(globalBestFitness);
			System.out.println("Global best fitness: " + globalBestFitness);
			System.out.println("Global best position sum: " + globalBestPosition.sum());
			if (problem.isKnapSack()){
				AbstractKnapSackProblem kp = (AbstractKnapSackProblem)problem;
				System.out.println("Global best position weight: " + kp.getWeight(globalBestPosition));
				System.out.println("Global best position value: " + kp.getValue(globalBestPosition));
			}
			double avgFitness = getAverageFitness();
			System.out.println("Average fitness: " + avgFitness);
			
			if (!problem.isKnapSack() && globalBestFitness < 0.0001){
				System.out.println("Fitness goal reached, aborting");
				break;
				
			}
			
			
			if (Math.pow(lastIterationBestFitness-globalBestFitness,2) > 0.0001 ){
				lastIterationBestFitness = globalBestFitness;
				iterationBestFitnessChanged = iteration;
			}
			if (iteration-iterationBestFitnessChanged > problem.getIterationsCutoff()){
				System.out.println("Fitness has not changed for " + (iteration-iterationBestFitnessChanged) + " iterations, aborting.");
				break;
			}
			
		}
	}
	private double getAverageFitness() {
		double totalFitness = 0.0;
		for (AbstractParticle p: particles){
			totalFitness += p.getPositionFitness();
		}
		return totalFitness/particles.length;
	}
	private Vector getGlobalBestPosition(){
		double bestFitness = Double.MAX_VALUE;
		AbstractParticle b = null;
		for (AbstractParticle p: particles){
			//System.out.println(p.getBestPositionValue());
			if (p.getBestPositionValue() < bestFitness){
				bestFitness = p.getBestPositionValue();
				b = p;
			}
		}
		return b.getBestPosition();
	}
	public static void main(String [] args){
		//CircleProblem1D prob = new CircleProblem1D();
		//CircleProblem2D prob = new CircleProblem2D();
		KnapSackWeightValueVolumeProblem prob = new KnapSackWeightValueVolumeProblem();
		FullyConnectedTopology top = new FullyConnectedTopology();
		//NearestNeighbourTopology top = new NearestNeighbourTopology(10);
		ParticleManager pm = new ParticleManager(prob, top);
		pm.solve();
	}
}
