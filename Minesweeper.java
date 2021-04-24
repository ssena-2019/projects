import java.util.Random;

/*
 * This program generates a minesweeper board
 */

public class Minesweeper {

    boolean[][] mineField; // board with mines marked as true
    int rows; // number of rows of the board
    int cols; // number of columns of the board
    int numMines;

    /*
     * Class Constructor
     * 
     * @param int rows Number of rows of the board
     * 
     * @param int cols Number of columns of the board
     * 
     * @param int numMines Number of mines to be placed on the board
     * 
     */
    Minesweeper(int rows, int cols, int numMines) {
        this.cols = cols;
        this.rows = rows;
        this.numMines = numMines;
        mineField = new boolean[rows][cols];
        generateBoard();
    }

    /*
     * Generates the mineField
     * 
     * @param int numMines Number of mines to be placed on the mineField
     * 
     * @return boolean[][] Game board with mines placed at random position
     */
    public void generateBoard() {
       Random r = new Random();
        
        int k = 0;
        while(k < numMines){
            int x = r.nextInt(rows) + 0;
            int y = r.nextInt(cols) + 0;
            if(mineField[x][y] == false){
                mineField[x][y] = true;
                k++;
            }
        }
    }

    /*
     * Creates the game
     * 
     * @return int[][] The board with cell values computed
     */
    public int[][] generateClues() {
        int[][] arr = new int [rows][cols];
            for(int i = 0; i < rows; i++) {
              for(int j = 0; j < cols; j++) {
                  //System.out.print(countMines(i, j) + "\t");
                  arr[i][j] = countMines(i, j);
              }
              //System.out.println();
          }
        return arr; 
    }

    /*
     * counts the number of adjacent mines to a given cell position
     * 
     * @param int r row position
     * 
     * @param int c column position
     * 
     * @return int number of mines in the surrounding cells
     */
    public int countMines(int r, int c) {
        int minMax[] = {0, 0, 0, 0};
		   
		   if(r == 0) {
			   minMax[0] = r;
			   minMax[1] = r + 1;
		   }else if(r == mineField.length - 1) {
			   minMax[0] = r - 1;
			   minMax[1] = r;
		   }else {
			   minMax[0] = r - 1;
			   minMax[1] = r + 1;
		   }
		   
		   if(c == 0) {
			   minMax[2] = c;
			   minMax[3] = c + 1;
		   }else if(c == mineField.length - 1) {
			   minMax[2] = c - 1;
			   minMax[3] = c;
		   }else {
			   minMax[2] = c - 1;
			   minMax[3] = c + 1;
		   }
		
	      int count = 0;
	      
	      if(mineField[r][c] == false) {
		      for(int i = minMax[0]; i <= minMax[1]; i++) {
		    	  for(int j = minMax[2]; j <= minMax[3]; j++) {
		    		  if(mineField[i][j] == true && (i != r || j != c)) {
		    			  count++;
		    		  }
		    	  }
		      }
	      }else {
	    	  return -1;
	      }
    
	      return count;
    }

    /*
     * prints the game mine is represented by *
     * 
     * @param int[][] game The game board
     */
    public static void printClues(int[][] clues) {
        for(int i = 0; i < clues.length; i++) {
            for(int j = 0; j < clues.length; j++) {
                if(clues[i][j] == -1) {
                    System.out.print("*" + "\t");
                }else {
                    System.out.print(clues[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Minesweeper object = new Minesweeper(3, 3, 2);
        int[][] clues2 = object.generateClues();
        printClues(clues2);
    }

}
