package simplexMethod;

import java.awt.Point;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
//     double a[][]= {  {0,0,-2,0,0,0,0},
//    		          {1,-2,2,1,0,0,-8},
//    		          {-1,1,1,0,1,0,4 } ,
//    		          {2,-1,4,0,0,1,10 }, 
//    		           };
//	int b[]= {-1,2,3,4,5};
//	solver s=new solver(a,b,4,7);
//	s.solve(20);
//	a=s.getA();
//	b=s.getB();
//	for(int i=0; i<7; i++) {
//		System.out.print(a[0][i] +"  ");
//	}
//	System.out.println("  ");
//	
//	for(int i=0; i<4; i++) {
//		System.out.print(a[i][6] +"  ");
//	}
//	
//	

		ArrayList<Integer> ar =new ArrayList<>();
		ar.add(1);
		ar.add(3);
		ar.add(3);
		
		ArrayList<Integer> ar1 =new ArrayList<>();
		ar1.add(3);
		ar1.add(1);		
		ar1.add(3);

		MuscleGroup m =new MuscleGroup(3,ar);
		MuscleGroup m1 =new MuscleGroup(3,ar1);
		
		
		Point p[]=new Point[3];
		p[0]=new Point(1,1);
		p[1]=new Point(1,1);
		p[2]=new Point(1,1);
		
		MuscleGroup mg [] = {m,m1};
		WorkOutPlan w =new WorkOutPlan();
		double[] ans=w.getPlan(p, mg);
		System.out.println("===================================================");
		
		for(int i=0; i<ans.length; i++) {
			System.out.println(ans[i]);
		}
		
		
	
	}
}
