package PSOSolver;

import Problems.AbstractProblem;
import Problems.CircleProblem1D;
import Problems.CircleProblem2D;
import Problems.KnapSackWeightProblem;
import Problems.KnapSackWeightValueProblem;
import Topology.AbstractTopology;
import Topology.FullyConnectedTopology;
import Topology.NearestNeighbourTopology;

public class ParticleManager {
	private Particle []particles;
	private double localAttraction = 2.0;
	private double globalAttraction = 2.0;
	private double inertiaWeight = 0.5;
	public ParticleManager(int numParticles,AbstractProblem problem, AbstractTopology topology){
		particles = new Particle[numParticles];
		for (int i = 0; i < numParticles; i++){
			particles[i] = new Particle(problem,topology);
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
		for (int iteration = 0; iteration < 5000; iteration++){
			step();
			System.out.println("Iteration: " + iteration);
			double globalBestFitness = getGlobalBestFitness();
			System.out.println("Global best fitness: " + globalBestFitness);
			if (globalBestFitness < 0.0001){
				System.out.println("Fitness goal reached, aborting");
				break;
				
			}
		}
	}
	private double getGlobalBestFitness(){
		double bestFitness = Double.MAX_VALUE;
		Particle b = null;
		for (Particle p: particles){
			if (p.getFitness() < bestFitness){
				bestFitness = p.getFitness();
				b = p;
			}
		}
		System.out.print(b.getPosition().toString());
		return bestFitness;
	}
	public static void main(String [] args){
		//CircleProblem1D prob = new CircleProblem1D();
		CircleProblem2D prob = new CircleProblem2D();
		//KnapSackWeightValueProblem prob = new KnapSackWeightValueProblem();
		//FullyConnectedTopology top = new FullyConnectedTopology();
		NearestNeighbourTopology top = new NearestNeighbourTopology(7);
		ParticleManager pm = new ParticleManager(10,prob, top);
		pm.solve();
	}
}
