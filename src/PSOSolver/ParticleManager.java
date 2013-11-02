package PSOSolver;

import Problems.AbstractProblem;
import Problems.CircleProblem1D;

public class ParticleManager {
	private Particle []particles;
	private double localAttraction = 1.0;
	private double globalAttraction = 1.0;
	public ParticleManager(int numParticles){
		particles = new Particle[numParticles];
		for (int i = 0; i < numParticles; i++){
			particles[i] = new Particle();
		}
		for (Particle p: particles){
			p.setAttraction(localAttraction, globalAttraction);
		}
	}
	public void Solve(AbstractProblem problem){
		
	}
	public static void main(String [] args){
		ParticleManager pm = new ParticleManager(100);
		CircleProblem1D cp1d = new CircleProblem1D();
		pm.Solve(cp1d);
	}
}
