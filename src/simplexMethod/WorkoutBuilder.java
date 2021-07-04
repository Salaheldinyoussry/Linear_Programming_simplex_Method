package simplexMethod;

public class WorkoutBuilder {
	int maxIter=500;

	
	public int[] getPlan(int muscleGroupNo , int constraints[][]) {
		
	    int consNo=constraints[0].length+constraints.length;
        int varNo=constraints.length-1+consNo;
        double a[][]=new double[consNo][varNo];
        
		for(int i=0; i<constraints.length-1; i++) {
			int sum=0;
			for(int j=0; j<muscleGroupNo; j++) {
				sum+=constraints[i][j];
			}
			a[0][i]=-sum;
		}
        
        
	   int row=1;
	  
	   
	   for(int i=0; i<constraints.length-1; i++) {   ///     exer. cons.
		   a[row][i]=1;
		   a[row][varNo-1]=1;
		   row++;

		   }
	   
	   for(int i1=0; i1<constraints[0].length; i1++) { 
	   for(int i=0; i<constraints.length-1; i++) {
	    ///     muscle && time . cons.
		   a[row][i]=constraints[i][i1];
		   } 
	   row++;
	   }
	   
	   
	   for(int i=0; i<constraints[0].length; i++) {   ///    numeric . cons.
		   a[constraints.length+i][a[0].length-1]=constraints[constraints.length-1][i];
		   } 
	   
	
	   
	   
		int ind=constraints.length-1;
		for(int i=1; i<consNo; i++) {                ///   slack variables
			a[i][ind++]=1;
				}
		
		
		
		

		
		
		int b[]=new int[a.length];
		int temp=constraints.length-1;
		for(int i=1; i<b.length; i++) {
			b[i]=temp++;
		}
			

		solver s=new solver(a,b,a.length,a[0].length);
		s.solve(maxIter);
		a=s.getA();
		b=s.getB();
		
		
		
		
		
		for(int it=0; it<100; it++) {
			int lrow=-1;
			for(int i=1; i<a.length; i++ ) {
				if(b[i]<constraints.length-1 && a[i][a[0].length-1]<1 && a[i][a[0].length-1]>0  ) {
				lrow=i;	
				break;
				}
		
				
			}
			if(lrow==-1) {
				//System.out.println("wee");
				break;
			}
			int lcol=-1;

			for(int i=0; i<a[0].length; i++) {
				if(a[lrow][i]==1) {
					lcol=i;
					break;
				}
			}
			
	          // System.out.println("row: "+lrow +"  col:  "+lcol);
	
		
		
		double aa[][]=new double[a.length+1][a[0].length+1];
		
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[0].length-1; j++) {
				aa[i][j]=a[i][j];
			}
		}
//		
		for(int i=0; i<a.length; i++) {
			aa[i][aa[0].length-1]=a[i][a[0].length-1];
		}
		
		aa[aa.length-1][lcol]=-1;
		aa[aa.length-1][aa[0].length-1]=-1	;	
		aa[aa.length-1][aa[0].length-2]=1;	
		
		int bb[]=new int[aa.length];
		for(int i=0; i<b.length; i++) {
			bb[i]=b[i];
		}
		bb[bb.length-1]=aa[0].length-2;
	
		
		for(int i=0; i<aa[0].length; i++) {
			aa[aa.length-1][i]=aa[aa.length-1][i]+aa[lrow][i];
		}
		
		

		
		double aaa[][]=new double [aa.length][aa[0].length];
		for(int i=0; i<aa.length; i++) {
			for(int j=0; j<aa[0].length; j++) {
				aaa[i][j]=aa[i][j];
			}
		}
		int bbb[]=new int[bb.length];
		for(int i=0; i<bb.length; i++) {
			bbb[i]=bb[i];
		}

		
		solver s1=new solver(aaa,bbb,aaa.length,aaa[0].length);
		s1.solve(maxIter);
		
		
		if(s1.infeasible) {
			aa[aa.length-1][aa[0].length-1]=0;
			for(int i=0; i<a[0].length; i++) {
				aa[aa.length-1][i]=0;
			}	
			
			aa[aa.length-1][lcol]=1;
			aa[aa.length-1][aa[0].length-1]=0	;	
			aa[aa.length-1][aa[0].length-2]=1;	

			for(int i=0; i<aa[0].length; i++) {
				aa[aa.length-1][i]=aa[aa.length-1][i]-aa[lrow][i];
			}
			
			double a4[][]=new double [aa.length][aa[0].length];
			for(int i=0; i<aa.length; i++) {
				for(int j=0; j<aa[0].length; j++) {
					a4[i][j]=aa[i][j];
				}
			}
			int b4[]=new int[bb.length];
			for(int i=0; i<bb.length; i++) {
				b4[i]=bb[i];
			}
						solver ss=new solver(a4,b4,a4.length,a4[0].length);
				ss.solve(maxIter);
			double [][]a5=ss.getA();
			int[]b5=ss.getB();	

			
			a=ss.getA();
			b=ss.getB();	
			

		}
		else {

			a=s1.getA();
			b=s1.getB();			
		}

		
		}
		
		
//		
//	
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[0].length; j++) {
				System.out.print(a[i][j]+"   ");
			}
			System.out.println("   ");

		}
		
		
		
		
		for(int i=0; i<b.length; i++) {
		
			System.out.println(b[i]);

		}
		
			int ans []=new int[constraints.length-1];

//		
		for(int i=1; i<b.length; i++) {
			if(b[i]<constraints.length-1) {
				ans[b[i]]=(int)(a[i][a[0].length-1]);
			}
		}
		
		return ans;
		
	}
	
	
}
