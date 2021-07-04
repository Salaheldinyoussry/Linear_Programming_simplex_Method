package simplexMethod;

import java.awt.Point;
import java.util.ArrayList;

public class WorkOutPlan {

	
	public double [] getPlan( Point [] exercise, MuscleGroup [] groups  ) {
          int consNo=groups.length+exercise.length *6+1;
          int varNo=consNo+exercise.length*3;
		double [][] a =new double[consNo][varNo];
		
		
		for(int i=0; i<exercise.length; i++) {         ///  maximization function
			int sum=0;
			for(int j=0; j<groups.length; j++) {
				sum+=groups[j].exerciseVol.get(i);
			}
			a[0][i]=-sum;
				}

		
		
		
		for(int i=0; i<groups.length; i++) {                ///   groups constraint
			ArrayList<Integer> x =groups[i].exerciseVol ;
			for(int j=0; j<x.size(); j++) {
			 a[i+1][j]=x.get(j);
			}
			a[i+1][varNo-1]=groups[i].max;
				
           }

		
		
		int ind=exercise.length*3;
		for(int i=1; i<consNo; i++) {                ///   slack variables
			a[i][ind++]=1;
				}
		
		int row=groups.length+1;
		for(int i=0; i<exercise.length; i++) {           // Exercises constraints
			a[row][i]=1;
			a[row][varNo-1]=exercise[i].y;
			row++;
			
			a[row][i]=1;
			a[row][exercise.length*2+i]=-exercise[i].x;
			a[row][exercise.length+i]=-1;
			
			row++;
			a[row][i]=-1;
			a[row][exercise.length*2+i]=exercise[i].x;
			a[row][exercise.length+i]=1;
			
			
			row++;
			a[row][exercise.length*2+i]=1;
			a[row][varNo-1]=1;
			
			row++;
			a[row][exercise.length+i]=1;
			a[row][varNo-1]=exercise[i].y-exercise[i].x;
			
			
			row++;
			if(exercise[i].y-exercise[i].x!=0) {
			a[row][exercise.length+i]=1/(double)(exercise[i].y-exercise[i].x);}
			else {
				a[row][exercise.length+i]=1/100;	
			}
			a[row][exercise.length*2+i]=-1;
			
			row++;
		

			
			
					
		}
		int[] b =new int[consNo];
		int j=consNo-1;
		for(int i=varNo-2; i>varNo-consNo-1; i--) {
			b[j--]=i;
			
		
		}
		
//		
		for(int i=0; i<consNo; i++) {
			for(int j1=0; j1<varNo; j1++) {
				System.out.print(a[i][j1]+"  ");
			}
			System.out.println(" ");

		}
		System.out.println(" ");
		System.out.println(" ");
//		
		for(int i=0; i<b.length; i++) {
			System.out.println(b[i]);
		
		}
//		
		System.out.println(" ");
		System.out.println(" ");

//		
		solver s=new solver(a, b, consNo, varNo);
		s.solve(100);
		
		a=s.getA();
		for(int i=0; i<consNo; i++) {
			for(int j1=0; j1<varNo; j1++) {
				System.out.print(a[i][j1]+"  ");
			}
			System.out.println(" ");

		}
		System.out.println(" ");
		System.out.println(" ");
		
		
	b=s.getB();
	for(int i=0; i<b.length; i++) {
		System.out.println(b[i]);
	
	}
//		
//
//double [][]aa=s.getA();
//int [] bb=s.getB();

//for(int i=0; i<varNo; i++) {
//System.out.print(aa[0][i] +"  ");
//}
//System.out.println("  ");
//
//for(int i=0; i<consNo; i++) {
//System.out.print(bb[i] +"  ");
//}
		

	
//	for(int i=0; i<exercise.length; i++) {
//		for(int j1=1; j1<b.length; j1++) {
//			if(b[j1]==exercise.length*2+i && a[j1][varNo-1]>0 && a[j1][varNo-1]<1) {
//				ans[i]=0;
//			}
//		}	
//	}
		
	
//	
//	//////////
	for(int it=0; it<100; it++) {
		int col=-1;
		int roww=-1;
		for(int i=0; i<exercise.length; i++) {
			for(int j1=1; j1<b.length; j1++) {
				if(b[j1]==exercise.length*2+i && a[j1][a[0].length-1]>0 && a[j1][a[0].length-1]<1) {
					roww=j1;
					col=exercise.length*2+i;
					break;
				}
			}	
			if(col!=-1)
				break;	
		}
		System.out.println(it +"    row :"+roww +"   col "+col);
		
		if(col==-1) {
		System.out.println("i am out");
			break;
		}
		
	
		
		double aa [][]=new double[a.length+1][a[0].length+1];
		
		for(int i=0; i<a.length; i++) {
			for(int d=0; d<a[0].length-1; d++) {
				aa[i][d]=a[i][d];
			}	
		}
		
		
		for(int i=0; i<a.length; i++) {
			aa[i][a[0].length]=a[i][a[0].length-1];
		}
		
//		aa[aa.length-2][col]=1;
//		aa[aa.length-2][aa[0].length-1]=1;
		
		aa[aa.length-1][col]=-1;
		aa[aa.length-1][aa[0].length-1]=-1;
	
		
		aa[aa.length-1][aa[0].length-2]=1;
//		aa[aa.length-2][aa[0].length-3]=1;
		
		for(int i=0; i<aa[0].length; i++) {
			aa[aa.length-1][i]=aa[aa.length-1][i]+aa[roww][i];
			//aa[aa.length-2][i]=aa[aa.length-2][i]-aa[roww][i];

		}
	
		
		int[] bbb=new int [b.length+1];
		for(int i=0; i<b.length; i++) {
			bbb[i]=b[i];
		}
		bbb[bbb.length-1]=aa[0].length-2;
	//	bbb[bbb.length-2]=aa[0].length-3;
		
		
		
		
		

		
		
		
		
		
		for(int i=0; i<aa.length; i++) {
			for(int j1=0; j1<aa[0].length; j1++) {
				System.out.print(aa[i][j1]+"  ");
			}
			System.out.println(" ");

		}
		System.out.println(" ");
		System.out.println(" ");
		
		
		
		for(int i=0; i<bbb.length; i++) {
			System.out.println(bbb[i]);
		
		}
		
		
		
		////////////////////////////////////
		double temp[][]=new double[aa.length][aa[0].length];
		int temp1[]=new int[bbb.length];
		
		for(int i=0; i<aa.length; i++) {
			for(int j1=0; j1<aa[0].length; j1++) {
                  temp[i][j1]=aa[i][j1];
			}
		}
	
		for(int i=0; i<bbb.length; i++) {
			temp1[i]=bbb[i];
		}
		//////////////
		s=new solver(aa,bbb,aa.length,aa[0].length);
		
		s.solve(100);
		System.out.println("infes : " +s.infeasible);
		
		
	
	
		if(s.infeasible==true) {
			System.out.println("i am in" );
			for(int i=0; i<aa[0].length; i++) {
				//aa[aa.length-1][i]=aa[aa.length-1][i]+aa[roww][i];
			  temp[aa.length-1][i]=0;

			}
			temp[aa.length-1][col]=1;
			temp[aa.length-1][temp[0].length-1]=0;
			for(int i=0; i<aa[0].length; i++) {
				temp[aa.length-1][i]=temp[aa.length-1][i]-temp[roww][i];
			  //temp[aa.length-1][i]=0;

			}
			
			
			

			for(int i=0; i<temp.length; i++) {
				for(int j1=0; j1<temp[0].length; j1++) {
					System.out.print(temp[i][j1]+"  ");
				}
				System.out.println(" ");

			}
			System.out.println(" ");
			System.out.println(" ");
			
			
			
			for(int i=0; i<temp1.length; i++) {
				System.out.println(temp1[i]);
			
			}
			
			
			
			s=new solver(temp,temp1,temp.length,temp[0].length);
			s.solve(100);			

		}
//		

		double [][]hh=s.getA();
		int []jj =s.getB();	
		
		
		
		for(int i=0; i<hh.length; i++) {
			for(int j1=0; j1<hh[0].length; j1++) {
				System.out.print(hh[i][j1]+"  ");
			}
			System.out.println(" ");

		}
		System.out.println(" ");
		System.out.println(" ");
		
		
		
		for(int i=0; i<jj.length; i++) {
			System.out.println(jj[i]);
		
		}
		
		
		a=hh;
		b=jj;
		
	}
	
	
	//////////
//	
//	for(int i=0; i<consNo; i++) {
//		for(int j1=0; j1<varNo; j1++) {
//			System.out.print(a[i][j1]+"  ");
//		}
//		System.out.println(" ");
//
//	}
//	System.out.println(" ");
//	System.out.println(" ");
//	
//	
//	
//	for(int i=0; i<b.length; i++) {
//		System.out.println(b[i]);
//	
//	}
//	
	
	double []ans =new double [exercise.length];
	
	for(int i=1; i<b.length; i++) {
		if(b[i]<exercise.length) {
			ans[b[i]]=a[i][a[0].length-1];
		}
	}
	
	
	return ans;
	}
	
	
}
