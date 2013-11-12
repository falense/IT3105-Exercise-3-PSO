package PSOSolver;

import Problems.AbstractProblem;
import Problems.CircleProblem2D;
import Topology.AbstractTopology;
import Topology.NearestNeighbourTopology;

public class ParticleManager {
	private Particle []particles;
	private double localAttraction = 0.0;
	private double globalAttraction = 1.0;
	private double inertiaWeight = 1;
	private ParticleManagerGUI PMGUI;
	public ParticleManager(int numParticles,AbstractProblem problem, AbstractTopology topology){
		PMGUI = new ParticleManagerGUI();
		PMGUI.setup();
		particles = new Particle[numParticles];
		for (int i = 0; i < numParticles; i++){
			particles[i] = problem.generateParticle(problem,topology,i);
			particles[i].setAttraction(localAttraction, globalAttraction);
			particles[i].setIntertiaWeight(inertiaWeight);
		}
		topology.setParticles(particles);
	}
	public void step(){
		for (Particle p: particles){
			p.step();
			System.out.println(p.toString());
		}
		//System.out.println();
	}
	
	public void solve(){
		for (int iteration = 0; iteration < 1000; iteration++){
			step();
			System.out.println("Iteration: " + iteration);
			double globalBestFitness = getGlobalBestFitness();
			PMGUI.addValue(0,globalBestFitness);
			System.out.println("Global best fitness: " + globalBestFitness);
			if (false && globalBestFitness < 0.0001){
				System.out.println("Fitness goal reached, aborting");
				break;
				
			}
		}
	}
	private double getGlobalBestFitness(){
		double bestFitness = Double.MAX_VALUE;
		Particle b = null;
		for (Particle p: particles){
			//System.out.println(p.getBestPositionValue());
			if (p.getBestPositionValue() < bestFitness){
				bestFitness = p.getBestPositionValue();
				b = p;
			}
		}
		return bestFitness;
	}
	public static void main(String [] args){
		//CircleProblem1D prob = new CircleProblem1D();
		CircleProblem2D prob = new CircleProblem2D();
		//KnapSackWeightValueProblem prob = new KnapSackWeightValueProblem();
		//FullyConnectedTopology top = new FullyConnectedTopology();
		NearestNeighbourTopology top = new NearestNeighbourTopology(1);
		ParticleManager pm = new ParticleManager(3,prob, top);
		pm.solve();
	}
}
