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
	private AbstractTopology topology;
	private boolean debug;
	private int iteration;
	private double globalBestFitness;
	public void createParticles(){
		int numParticles = problem.getParticleCount();
		particles = new AbstractParticle[numParticles];
		for (int i = 0; i < numParticles; i++){
			particles[i] = problem.generateParticle(problem,topology,i);
		}
		topology.setParticles(particles);
	}
	public ParticleManager(AbstractProblem problem, AbstractTopology topology, boolean gui, boolean debug){
		this.problem = problem;
		this.topology = topology;
		createParticles();
		if (gui){
			PMGUI = new ParticleManagerGUI();
			PMGUI.setup();
		}
		else{
			PMGUI = null;
		}
		this.debug = debug;
	}
	public void step(){
		for (AbstractParticle p: particles){
			p.step();
			//System.out.println(p.toString());
		}
		//System.out.println();
	}
	public void println(String output){
		if (debug) System.out.println(output);
	}
	public void solve(){
		double lastIterationBestFitness = 0.0;
		int iterationBestFitnessChanged = 0;
		for (iteration = 0; iteration < problem.getMaxIterations(); iteration++){
			problem.setIteration(iteration);
			step();
			Vector globalBestPosition = getGlobalBestPosition();
			globalBestFitness = problem.evaluate(globalBestPosition);
			double avgFitness = getAverageFitness();
			if (PMGUI != null) PMGUI.addValue(globalBestFitness);
			
			println("Iteration: " + iteration);
			println("Global best fitness: " + globalBestFitness);
			println("Global best position sum: " + globalBestPosition.sum());
			if (problem.isKnapSack()){
				AbstractKnapSackProblem kp = (AbstractKnapSackProblem)problem;
				println("Global best position weight: " + kp.getWeight(globalBestPosition));
				println("Global best position value: " + kp.getValue(globalBestPosition));
			}
			println("Average fitness: " + avgFitness);
			
			if (!problem.isKnapSack() && globalBestFitness < 0.0001){
				println("Fitness goal reached, aborting");
				break;
				
			}
			
			
			if (Math.pow(lastIterationBestFitness-globalBestFitness,2) > 0.0001 ){
				lastIterationBestFitness = globalBestFitness;
				iterationBestFitnessChanged = iteration;
			}
			if (iteration-iterationBestFitnessChanged > problem.getIterationsCutoff()){
				println("Fitness has not changed for " + (iteration-iterationBestFitnessChanged) + " iterations, aborting.");
				break;
			}
			
		}
	}
	public double getGlobalBestFitness(){
		return globalBestFitness;
	}
	public int getIterations(){
		return iteration;
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
		ParticleManager pm = new ParticleManager(prob, top,true,true);
		pm.solve();
	}
}
