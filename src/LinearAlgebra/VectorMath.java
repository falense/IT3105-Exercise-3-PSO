package LinearAlgebra;

public class VectorMath {
	public static Vector add(Vector v1, Vector v2){
		if (v1.size() != v2.size()){
			System.err.println("Vector addition sizes doesnt match");
			return null;
		}
		int N = v1.size();
		Vector returnValue = new Vector(N);
		for (int index = 0; index < N; index++){
			double t = v1.get(index) + v2.get(index);
			returnValue.set(index,t);
		}
		return returnValue;
	}
	public static Vector sub(final Vector v1,final Vector v2){
		Vector v2_temp = new Vector(v2);
		v2_temp.multiply(-1);
		return add(v1,v2_temp);
		
	}
	public static double euclidianDistance(final Vector v1, final Vector v2){
		double r = 0;
		for (int index = 0; index < v1.size(); index++){
			double a = v1.get(index);
			double b = v2.get(index);
			r += Math.pow(a-b, 2);
		}
		return r;
	}
	public static Vector elementMultiplication( final Vector v1, final Vector v2){
		Vector r = new Vector(v1.size());
		for (int index = 0; index < v1.size(); index++){
			r.set(index, v1.get(index)*v2.get(index));
		}
		return r;
	}
}
