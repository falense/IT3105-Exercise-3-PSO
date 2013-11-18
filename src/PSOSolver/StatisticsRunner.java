package PSOSolver;

import PSOSolver.Topology.FullyConnectedTopology;
import PSOSolver.Topology.NearestNeighbourTopology;
import Problems.AbstractProblem;
import Problems.CircleProblem1D;
import Problems.CircleProblem2D;
import Problems.CircleProblemND;
import Problems.KnapSackWeightValueProblem;
import Problems.KnapSackWeightValueVolumeProblem;

public class StatisticsRunner {
	ParticleManagerGUI pmgui;
	public StatisticsRunner(){
		pmgui = new ParticleManagerGUI();
	}
	private void makeNewPlot(String applicationName, String figureName){
		pmgui.newPlot(applicationName,figureName);
	}
	public void Task1b(){
		makeNewPlot("Task1b", "Prob: CP1D Top: FC");
		
		FullyConnectedTopology top = new FullyConnectedTopology();
		CircleProblem1D prob = new CircleProblem1D();
		for (int i = 0; i < 10; i++){
			ParticleManager pm = new ParticleManager(prob, top);
			pm.solve();
		}
	}
	public void Task1c(){
		FullyConnectedTopology top = new FullyConnectedTopology();
		CircleProblem2D prob = new CircleProblem2D();
		ParticleManager pm = new ParticleManager(prob, top);
		pm.solve();
	}

	public void Task2a(){
		FullyConnectedTopology top = new FullyConnectedTopology();
		
		AbstractProblem prob = new CircleProblem2D();
		ParticleManager pm = new ParticleManager(prob, top);
		pm.solve();
		
		prob = new CircleProblem2D();
		pm = new ParticleManager(prob, top);
		pm.solve();
	}
	public void Task2b(){
		FullyConnectedTopology top = new FullyConnectedTopology();
		
		AbstractProblem prob = new CircleProblem2D();
		prob.setVariableInertia(true);
		ParticleManager pm = new ParticleManager(prob, top);
		pm.solve();
		
		prob = new CircleProblem2D();
		prob.setVariableInertia(true);
		pm = new ParticleManager(prob, top);
		pm.solve();
	}
	public void Task3a(){
		FullyConnectedTopology top = new FullyConnectedTopology();
		
		AbstractProblem prob = new KnapSackWeightValueProblem();
		ParticleManager pm = new ParticleManager(prob, top);
		pm.solve();
	}
	public void Task3b(){
		FullyConnectedTopology top = new FullyConnectedTopology();

		KnapSackWeightValueProblem prob = new KnapSackWeightValueProblem();
		prob.setAttraction(1.0,2.0);
		ParticleManager pm = new ParticleManager(prob, top);
		pm.solve();

		prob = new KnapSackWeightValueProblem();
		prob.setAttraction(1.5,1.5);
		pm = new ParticleManager(prob, top);
		pm.solve();
		
		prob = new KnapSackWeightValueProblem();
		prob.setAttraction(2.0,1.0);
		pm = new ParticleManager(prob, top);
		pm.solve();
	}
	public void Task3c(){

		FullyConnectedTopology top = new FullyConnectedTopology();
		
		KnapSackWeightValueProblem prob = new KnapSackWeightValueProblem();
		prob.setAttraction(1.0,2.0);
		prob.setVariableInertia(true);
		ParticleManager pm = new ParticleManager(prob, top);
		pm.solve();

		prob = new KnapSackWeightValueProblem();
		prob.setAttraction(1.5,1.5);
		prob.setVariableInertia(true);
		pm = new ParticleManager(prob, top);
		pm.solve();
		
		prob = new KnapSackWeightValueProblem();
		prob.setAttraction(2.0,1.0);
		prob.setVariableInertia(true);
		pm = new ParticleManager(prob, top);
		pm.solve();
	}
	public void Task4a(){
		FullyConnectedTopology top = new FullyConnectedTopology();
		KnapSackWeightValueVolumeProblem prob = new KnapSackWeightValueVolumeProblem();
		prob.setAttraction(1.0,2.0);
		prob.setVariableInertia(true);
		ParticleManager pm = new ParticleManager(prob, top);
		pm.solve();
	}
	public void Test(){
		CircleProblemND prob = new CircleProblemND(20);
		//KnapSackWeightValueProblem prob = new KnapSackWeightValueProblem();
		//KnapSackWeightValueVolumeProblem prob = new KnapSackWeightValueVolumeProblem();
	
		//CircleProblem2D prob = new CircleProblem2D();
		//NearestNeighbourTopology top = new NearestNeighbourTopology(4);
		FullyConnectedTopology top = new FullyConnectedTopology();
		
		for (int i = 0; i < 10; i++){
			ParticleManager pm = new ParticleManager(prob, top);
			pm.solve();
		}
		

	}
	public static void main(String [] args){
		StatisticsRunner s = new StatisticsRunner();
		s.Test();
		
	}
	
}
