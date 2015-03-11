package simplex;
public class Simplex {
static double[][]A=new double[][]{{1,2,1,0,0,2},{3,5,0,-1,1,15},{1,1,0,0,0,0},{-3,-5,0,1,0,-15}};
    
    public static void main(String[] args)
    {   
        display();
        boolean phaseOne=false;
        if(phaseOne)
        {
            if(phaseOne())
                phaseTwo();
        }
        else
        {
            phaseTwo();
        }
    }
    static boolean phaseOne()
   {
       while(!checkIfDone())
            {
            int in=findIncoming();
            int out=findOutGoing(in);
            if(out==-1)
            {
                System.out.println("Infeasible Solution");
                System.exit(0);
            }
            rowOps(out,in);
            display();
            }
            if(A[A.length-1][A[0].length-1]>=0)
            {
                System.out.println("Deleting -w Row");
                deleteRow();
                return true;
            }
            else
            {
                System.out.println("Not Feasible");
                return false;
            }
        }
   
   
    static boolean phaseTwo()
   {
        while(!checkIfDone())
        {
            int in=findIncoming();
            int out=findOutGoing(in);
            if(out==-1)
            {
                System.out.println("Infeasible Solution");
                return false;
            }
            rowOps(out,in);
            display();    
        }
        return true;
   }
   static int findIncoming()/*Returns Incoming Collumn Value*/
    {
        int row=A.length-1;          //total rows-1
        int col=A[0].length;         //total collumns
        int current=0;
        double max=A[row][current];
        for(int i=0;i<col-1;i++)
        {
            if(A[row][i]<0 && A[row][i]<max)
            {
                max=A[row][i];
                current=i;
            }
        }
        System.out.println("Incoming collumn "+current);
        return current;
        
    }
    static int findOutGoing(int in)/*Returns Outgoing Row Value*/
    {
        double min=999;
        int colb=A[0].length-1;
        int cola=in;
        int row=A.length;
        double b,a,ratio;
        int ret=-1;
        for(int i=0;i<row-2;i++)
        {
           b=A[i][colb];
           a=A[i][cola];
           ratio=b/a;
           if(ratio>0 && ratio<min)
           {
               min=ratio;
               ret=i;
           }
        }
        System.out.println("Outgoing row "+ret);
        return ret;
   }
   static void rowOps(int a,int b)
   {
       int row=A.length;
       int col=A[0].length;
       double ratio;
       ratio=A[a][b];
        for(int j=0;j<col;j++)
        {
            A[a][j]/=ratio;
        }
       display();
       double val;
       for(int i=0;i<row;i++)
       {
           if(a!=i)
           {
                val=A[i][b];
                for(int j=0;j<col;j++)
                {
                A[i][j]+=(-val)*A[a][j];
                }
           }
           display();
       }
    }
   static boolean checkIfDone()
   {
       int row=A.length-1;
       int col=A[0].length;
       for(int i=0;i<col-2;i++)
       {
           if(A[row][i]<0)
               return false;
       }
       return true;
    }
   static void display()
   {
       int a=A.length;
       int b=A[0].length;
       for(int i=0;i<a;i++)
       {
            for(int j=0;j<b;j++)
            {
                System.out.print(" "+A[i][j]);
            }
            System.out.println();
       }
       System.out.println();
    }
   static void deleteRow()
   {
        double neo[][] = new double[A.length-1][A[0].length];
        int REMOVE_ROW =A.length-1;
        int p = 0;
        for( int i = 0; i < A.length; ++i)
        {
            if ( i == REMOVE_ROW)
                continue;
            int q = 0;
            for( int j = 0; j < A[0].length; ++j)
            {
                neo[p][q] = A[i][j];
                ++q;
            }
            ++p;
        }
        A=neo;
   }  
 }