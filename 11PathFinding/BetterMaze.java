import java.util.*;
import java.io.*;

public class BetterMaze{
    private class Node{
        private int xCoord,yCoord;
        private Node lastNode;

        public Node(int x, int y,Node l){
            xCoord = x;
            yCoord = y;
            lastNode = l;
        }

        public int getX(){
            return xCoord;
        }

        public int getY(){
            return yCoord;
        }

        public Node getLast(){
            return lastNode;
        }

        public String toString(){
            return "X: " + xCoord + "  , Y: " + yCoord;
        }

    }

    private char[][] maze;
    private int[]    solution;
    private int      startRow,startCol,endRow,endCol;
    private Frontier<Node> placesToGo;
    private boolean  animate;//default to false

   /**return a COPY of solution.
     *This should be : [x1,y1,x2,y2,x3,y3...]
     *the coordinates of the solution from start to end.
     *Precondition : one of the solveXXX methods has already been 
     * called (solveBFS OR solveDFS OR solveAStar)
     *(otherwise an empty array is returned)
     *Postcondition:  the correct solution is in the returned array
    **/
   public int[] solutionCoordinates(){
    /** IMPLEMENT THIS **/      
    return new int[1];
}

private ArrayList<Node> getNeighbors(Node current){
    ArrayList<Node> neighbors = new ArrayList<Node>();
    int y = current.getY();
    int x = current.getX();
    if (y - 1 >= 0 && maze[y - 1][x] != '.' && maze[y - 1][x] != '#') {
        neighbors.add(new Node(x,y - 1,current));
    }if (y + 1 < maze[0].length && maze[y + 1][x] != '.' && maze[y + 1][x] != '#') {
        neighbors.add(new Node(x,y + 1,current));
    }if (x - 1 >= 0 && maze[y][x - 1] != '.' && maze[y][x - 1] != '#') {
        neighbors.add(new Node(x - 1,y,current));
    }if (x + 1 < maze.length && maze[y][x + 1] != '.' && maze[y][x + 1] != '#') {
        neighbors.add(new Node(x + 1,y,current));
    }
    return neighbors;
}


    /**initialize the frontier as a queue and call solve
    **/
    public boolean solveBFS(){  
        placesToGo = new FrontierQueue<Node>();
        Node current =  new Node(startRow,startCol,null);
        placesToGo.add(current);
        // while(current.getX() != endCol && current.getY() != endRow){

        // }

        return true;
    }


   /**initialize the frontier as a stack and call solve
    */ 
   public boolean solveDFS(){  
    /** IMPLEMENT THIS **/  
    return false;
}    

   /**Search for the end of the maze using the frontier. 
      Keep going until you find a solution or run out of elements on the frontier.
    **/
      private boolean solve(){  
        /** IMPLEMENT THIS **/  
        return false;
    }    

    /**mutator for the animate variable  **/
    public void setAnimate(boolean b){  
        animate = b;
    }    


    public BetterMaze(String filename){
        animate = false;
        int maxc = 0;
        int maxr = 0;
        startRow = -1;
        startCol = -1;
    //read the whole maze into a single string first
        String ans = "";
        try{
            Scanner in = new Scanner(new File(filename));

        //keep reading next line
            while(in.hasNext()){
                String line = in.nextLine();
                if(maxr == 0){
            //calculate width of the maze
                    maxc = line.length();
                }
        //every new line add 1 to the height of the maze
                maxr++;
                ans += line;
            }
        }
        catch(Exception e){
            System.out.println("File: " + filename + " could not be opened.");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(maxr+" "+maxc);
        maze = new char[maxr][maxc];
        for(int i = 0; i < ans.length(); i++){
            char c = ans.charAt(i);
            maze[i / maxc][i % maxc] = c;
            if(c == 'S'){
                startCol = i % maxc;
                startRow = i / maxc;
            }else if (c == 'E') {
                endCol = i % maxc;
                endRow = i / maxc;
            }
        }
    }







    private static final String CLEAR_SCREEN =  "\033[2J";
    private static final String HIDE_CURSOR =  "\033[?25l";
    private static final String SHOW_CURSOR =  "\033[?25h";
    private String go(int x,int y){
        return ("\033[" + x + ";" + y + "H");
    }
    private String color(int foreground,int background){
        return ("\033[0;" + foreground + ";" + background + "m");
    }

    public void clearTerminal(){
        System.out.println(CLEAR_SCREEN);
    }

    public void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }


    public String toString(){
        int maxr = maze.length;
        int maxc = maze[0].length;
        String ans = "";
        if(animate){
            ans = "Solving a maze that is " + maxr + " by " + maxc + "\n";
        }
        for(int i = 0; i < maxc * maxr; i++){
            if(i % maxc == 0 && i != 0){
                ans += color(37,40) + "\n";
            }
            char c =  maze[i / maxc][i % maxc];
            if(c == '#'){
                ans += color(38,47)+c;
            }else{
                ans += color(33,40)+c;
            }
        }
    //nice animation string
        if(animate){
            return HIDE_CURSOR + go(0,0) + ans + color(37,40) +"\n"+ SHOW_CURSOR + color(37,40);
        }else{
            return ans + color(37,40) + "\n";
        }
    } 
    



    
    

}