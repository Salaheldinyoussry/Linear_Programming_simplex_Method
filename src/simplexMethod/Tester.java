package simplexMethod;

public class Tester {

	public static void main(String[] args) {

		int a[][]= {
				
				{19,2,3},
				{1,6,3},
				{1,2,1},
				{14,14,15}
				};

		WorkoutBuilder w =new WorkoutBuilder();

		int ans[]=w.getPlan(3, a);
		
		System.out.println(".......................................");

		for(int i=0; i<ans.length; i++) {
			System.out.println(ans[i]);
		}
		
		
		
	}

}
