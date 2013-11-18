package PSOSolver;

import PSOSolver.Topology.FullyConnectedTopology;
import PSOSolver.Topology.NearestNeighborTopology;
import Problems.AbstractProblem;
import Problems.CircleProblem1D;
import Problems.CircleProblem2D;
import Problems.CircleProblemND;
import Problems.KnapsackWeightValueProblem;
import Problems.KnapsackWeightValueVolumeProblem;

public class StatisticsRunner {
	ParticleManagerGUI pmgui;
	public StatisticsRunner(){
		pmgui = new ParticleManagerGUI();
	}
	private void makeNewPlot(String applicationName, String figureName){
		pmgui.newPlot(applicationName,figureName);
	}
	public void Task1a(){
		FullyConnectedTopology top = new FullyConnectedTopology();
		CircleProblem1D prob = new CircleProblem1D();
		ParticleManager pm = new ParticleManager(prob, top, false, true);
		pm.solve();
	}
	public void Task1b(){
		
		System.out.println("Task1b:");
		FullyConnectedTopology top = new FullyConnectedTopology();
		CircleProblem1D prob = new CircleProblem1D();
		for (int i = 0; i < 10; i++){
			ParticleManager pm = new ParticleManager(prob, top, false, false);
			pm.solve();
			System.out.println("Run " + i + ": Finished after " + pm.getIterations() +  " iterations with best fitness " + pm.getGlobalBestFitness());
		}
		System.out.println();
	}
	public void Task1c(){
		FullyConnectedTopology top = new FullyConnectedTopology();
		CircleProblem2D prob = new CircleProblem2D();
		prob.setIterationCutoff(1000);
		ParticleManager pm = new ParticleManager(prob, top, false, false);
		pm.solve();
		System.out.println("Task 1c:");
		System.out.println("Finished after " + pm.getIterations() +  " iterations with best fitness " + pm.getGlobalBestFitness());
		System.out.println();
	}

	public void Task2a(){
		NearestNeighborTopology top = new NearestNeighborTopology(3);
		makeNewPlot("Task2a", "Circle problem 1D vs 2D");
		AbstractProblem prob = new CircleProblem1D();
		ParticleManager pm = new ParticleManager(prob, top, true, false);
		pm.solve();
		
		prob = new CircleProblem2D();
		pm = new ParticleManager(prob, top, true, false);
		pm.solve();
	}
	public void Task2b(){
		makeNewPlot("Task2b", "Circle problem 1D vs 2D with varying inertia weight");
		NearestNeighborTopology top = new NearestNeighborTopology(3);
		
		AbstractProblem prob = new CircleProblem1D();
		prob.setVariableInertia(true);
		ParticleManager pm = new ParticleManager(prob, top, true, false);
		pm.solve();
		
		prob = new CircleProblem2D();
		prob.setVariableInertia(true);
		pm = new ParticleManager(prob, top, true, false);
		pm.solve();
	}
	public void Task3a(){
		makeNewPlot("Task3a", "KnapSack weight+value");
		
		FullyConnectedTopology top = new FullyConnectedTopology();
		
		AbstractProblem prob = new KnapsackWeightValueProblem();
		prob.setMaxIterations(500);
		ParticleManager pm = new ParticleManager(prob, top, true, false);
		pm.solve();
	}
	public void Task3b(){

		makeNewPlot("Task3b", "KnapSack weight+value (c1,c2) = (0.0,2.0) / (1.0,1.0) / (2.0/0.0)");
		FullyConnectedTopology top = new FullyConnectedTopology();

		KnapsackWeightValueProblem prob = new KnapsackWeightValueProblem();
		prob.setAttraction(0.0,2.0);
		ParticleManager pm = new ParticleManager(prob, top, true, false);
		pm.solve();

		prob.setAttraction(1.0,1.0);
		pm = new ParticleManager(prob, top, true, false);
		pm.solve();
		
		prob.setAttraction(2.0,0.0);
		pm = new ParticleManager(prob, top, true, false);
		pm.solve();
	}
	public void Task3c(){
		makeNewPlot("Task3c", "KnapSack weight+value vari. inertia (c1,c2) = (0.0,2.0) / (1.0,1.0) / (2.0/0.0)");
		FullyConnectedTopology top = new FullyConnectedTopology();

		KnapsackWeightValueProblem prob = new KnapsackWeightValueProblem();
		prob.setAttraction(0.0,2.0);
		prob.setVariableInertia(true);
		ParticleManager pm = new ParticleManager(prob, top, true, false);
		pm.solve();

		prob.setAttraction(1.0,1.0);
		pm = new ParticleManager(prob, top, true, false);
		pm.solve();
		
		prob.setAttraction(2.0,0.0);
		pm = new ParticleManager(prob, top, true, false);
		pm.solve();
	}
	public void Task4a(){
		makeNewPlot("Task4a", "KnapSack weight+value+volume vari. inertia");
		FullyConnectedTopology top = new FullyConnectedTopology();
		KnapsackWeightValueVolumeProblem prob = new KnapsackWeightValueVolumeProblem();
		prob.setAttraction(1.0,2.0);
		prob.setVariableInertia(true);
		prob.setIterationCutoff(1000);
		prob.setMaxIterations(1000);
		ParticleManager pm = new ParticleManager(prob, top, true, false);
		pm.solve();
	}
	public void Test(){
		//CircleProblemND prob = new CircleProblemND(20);
		KnapsackWeightValueProblem prob = new KnapsackWeightValueProblem();
		//KnapSackWeightValueVolumeProblem prob = new KnapSackWeightValueVolumeProblem();
		prob.setVariableInertia(true);

		
		//CircleProblem2D prob = new CircleProblem2D();
		//NearestNeighbourTopology top = new NearestNeighbourTopology(4);
		FullyConnectedTopology top = new FullyConnectedTopology();
		
		for (int i = 0; i < 10; i++){
			ParticleManager pm = new ParticleManager(prob, top, true, true);
			pm.solve();
		}
		

	}
	public static void main(String [] args){
		StatisticsRunner s = new StatisticsRunner();
		s.Task1a();
		s.Task1b();
		s.Task1c();
		s.Task2a();
		s.Task2b();
		/*s.Task3a();
		s.Task3b();
		s.Task3c();
		s.Task4a();*/
		
		//s.Test();
	}
	
}
