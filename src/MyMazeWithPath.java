// Breath-first  traversal for the path.  
import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

public class MyMazeWithPath
{       
    private InputGraphicMaze maze; // to hold maze
    private int R, C; // to hold row and column size chosen by user
    private int[][] V;// to hold positional representation of rows and columns after maze is created

    public MyMazeWithPath() 
    {       
        // an R rows x C columns maze
        maze = new InputGraphicMaze();//create new maze
        R=maze.Rows(); C=maze.Cols(); //get row and column size chosen by user
        V=new int[R+1][C+1]; //I believe this is to store whether a cell(point) hase been found yet

        //set all values to zero OF VISITED
        for (int i=1; i<=R; i++)
           for (int j=1; j<=C; j++) V[i][j]=0;
        // Path holds the cells of the path
        LinkedList<Point> Path = new LinkedList<Point>();
        //Create the path
        CreatePath(maze, 1, 1, R, C, Path);
        // show the path in the maze
        System.out.println("test =" );
        maze.showPath(Path);
    }

    public boolean CreatePath(
            InputGraphicMaze maze, //maze to be processed
            int srow, int scol, //starting row and column, [1,1]
            int erow, int ecol, //ending row and column, [R, C]
            LinkedList<Point> L) //a linked list of type point that will store the path
    {
        int r=srow, c=scol, //set another int set with start row and column
                //grab values of maze size from maze object
        R=maze.Rows(), C=maze.Cols(); int size=R*C+1;

        //linear array of points with size equal to number of cells + 1
        //I'm thinking this is to store the parents!
        Point[] P=new Point[size];


        boolean done=false;
        V[srow][scol]=1;
        int scell=(srow-1)*C+scol; //int to specify first cell as 1??

        //set parent of start cell to [0,0] which is off grid, this is the end of path
        P[scell]=new Point(0,0); //set scell to point [0,0] in linear array

        Point u=new Point(r, c); //point at start cell [r,c] == [1,1] iterator

        //create queue and add
        LinkedList<Point> Q=new LinkedList<Point>(); Q.add(u);//.add() adds element to end of list

        while ((Q.size()!=0)&&(!done))//if still in queue or found end. if queue hits zero oop!!
        {             
           u=Q.remove(); //remove first element of queue and set it to u
           //.remove() removes and returns
            // first instance from list (lowest index occurrence)
            //.remove() returns first element if no argument past

            //r and c now move to u[x,y]
           r=(int) u.getX(); c=(int) u.getY();

           if ((r==erow)&&(c==ecol)) done=true; //oh snap!

           else {
              if ((r>1)&&(V[r-1][c]!=1)&&(maze.can_go(r, c,'U'))) {//check up
                  V[r-1][c]=1; //makes cell as 'found'if ((r==erow)&&(c==ecol)) done=true;
                  P[(r-2)*C+c]=u;//sets parent of 'Up' cell to u
                  Q.add(new Point(r-1, c)); //adds 'up' cell to queue as Point
                  }
              if ((c<C)&&(V[r][c+1]!=1)&&(maze.can_go(r, c,'R'))) {//check right
                  V[r][c+1]=1;
                  P[(r-1)*C+c+1]=u;
                  Q.add(new Point(r, c+1));
              }
              if ((r<R)&&(V[r+1][c]!=1)&&(maze.can_go(r, c, 'D'))) {//check down
                  V[r+1][c]=1;
                  P[r*C+c]=u;
                  Q.add(new Point(r+1, c));}
              if ((c>1)&&(V[r][c-1]!=1)&&(maze.can_go(r, c, 'L'))) {//check left
                  V[r][c-1]=1;
                  P[(r-1)*C+c-1]=u;
                  Q.add(new Point(r, c-1));
              }
           }           
        } //end of while


        /**
         * walk pointer u back from end cell to start cell along parent path set by algorithm
         * populating L along the way. L stores the best path through maze
         */
        while (!u.equals(P[scell])) 
        {         
           r=(int) u.getX(); c=(int) u.getY();
           L.addFirst(u); u=P[(r-1)*C+c]; 
        }
        return done;
    }
 
    public static void main(String[] args) {new MyMazeWithPath();}
}