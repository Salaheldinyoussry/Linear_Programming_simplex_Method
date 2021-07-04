package simplexMethod;

public class solver {

	double eps=0.000001;
	double a[][];
	int b[];
	int n;
	int m;
	boolean infeasible=false;
	//  m  -> constraints
	//  n - > variables
	public solver(double a[][] ,int b[], int m ,int n){
		
		this.a=a;
		this.n=n;
		this.m=m;
		this.b=b;
	}

   private int  getCol () {
      int ind=0;
      double max=0;
	   for(int i=0; i<n; i++) {
    	  if(a[0][i]<max) {
    		  max=a[0][i];
    		  ind=i;
    	  }
    	   }
	   
	   if(max==0) {
		   return -1;
	   }
	   
	   return ind;
	   
   }
   
   private int  dualGetCol (int row) {
	      int ind=-1;
	      double min=999999999;
		   for(int i=0; i<n-1; i++) {
	    	  if(a[row][i]<0) {
			   	double x= Math.abs(a[0][i] /a[row][i]); 
			   		if(x<min) {
	    		  min=x;
	    		  ind=i;}
	    	  }
	    	   }
		   
		
		   
		   return ind;
		   
	   }
   
   
   private int  getRow (int col) {
	      int ind=0;
	      double min=999999999;
		   for(int i=1; i<m; i++) {
			   	if(a[i][col] >0   ) {
			   		double x= a[i][n-1] /a[i][col]; 
			   		if(x<min ) {
			   			ind =i;
			   			min=x;
			   		}
			   	}
	    	   
		   
		   }
		 	   return ind;
		   
	   }
   
   
   private int  dualGetRow () {
	      int ind=-1;
	      double min=0;
		   for(int i=1; i<m; i++) {
			   	if(a[i][n-1] <0   ) {
			   		double x= a[i][n-1] ; 
			   		if(x<min ) {
			   			ind =i;
			   			min=x;
			   		}
			   	}
	    	   
		   
		   }
		 	   return ind;
		   
	   }
   
   
   
   public void solve(int maxIter) {
	   
		

	   for(int it=0; it<maxIter; it++) {
		   
		  int row =dualGetRow();
		  if(row==-1) {
			  break;
		  }
           int col=	dualGetCol(row);

           if(col==-1) {
               infeasible=true;
//               System.out.println("killme");

        	   break;
 		  }
           
           double x=a[row][col];
           b[row]=col;
           
           for(int i=0; i<n; i++) {
        	   a[row][i]/=x;
        	   if(Math.abs(a[row][i])<eps) {
        		   a[row][i]=0;
        	   }
           }
           
           for(int i=0; i<m; i++) {
        	   if(i!=row) {
        	   double factor = 0 - a[i][col]/a[row][col];
        	   for(int j=0; j<n; j++) {
        		   a[i][j]+=(a[row][j]*factor);
        		   if(Math.abs(a[i][j])<eps) {
            		   a[i][j]=0;
            	   }    
        	   
        	   }
        	   }
           }


           
//           for(int i=0; i<a.length; i++) {
//   			for(int j1=0; j1<a[0].length; j1++) {
//   				System.out.print(a[i][j1]+"  ");
//   			}
//   			System.out.println(" ");
//
//   		}
//   		System.out.println(" ");
//   		System.out.println(" ");
//   		
//   		
//   		
//   		for(int i=0; i<b.length; i++) {
//   			System.out.println(b[i]);
//   		
//   		}
   	   
   	
	   }
	   
		
	   
	 
	   if(!infeasible) {
	   for(int it=0; it<maxIter; it++) {

           int col=	getCol();
           if(col==-1) {
        	   break;}
           int row=getRow(col);
           
           double x=a[row][col];
           b[row]=col;
           
           for(int i=0; i<n; i++) {
        	   a[row][i]/=x;
        	   if(Math.abs(a[row][i])<eps) {
        		   a[row][i]=0;
        	   }
           }
           
           for(int i=0; i<m; i++) {
        	   if(i!=row) {
        	   double factor = 0 - a[i][col]/a[row][col];
        	   for(int j=0; j<n; j++) {
        		   a[i][j]+=(a[row][j]*factor);
        		   if(Math.abs(a[i][j])<eps) {
            		   a[i][j]=0;
            	   }
        		    }
        	   }
           }
           
		  
	  }}
   
   }
   
   public double [][] getA() {
	   return this.a;
   }
   
   public int [] getB() {
	   return this.b;
   }
   
   



}
