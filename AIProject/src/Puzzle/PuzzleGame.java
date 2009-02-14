package Puzzle;

import java.awt.Point;
import java.util.Vector;

public class PuzzleGame {

    public PuzzleNode getCurrSolutionNode() {
        return currSolutionNode;
    }

    public void setCurrSolutionNode(PuzzleNode currSolutionNode) {
        this.currSolutionNode = currSolutionNode;
    }

    public static enum Algorithm {

        AStar, IDAStar
    }
    private PuzzleNode root;
    private final int gameWidth = 15;
    private int nodesSearched = 0; /*The number of nodes we searched so far*/

    /* we keep the previous positions and insure we never get there again.
    This keeps us from moving the same tile back and forth ,and forces the moves taken 
    to advance towrds the goal. Each time we do binary search on it */
    public static Vector<PuzzleNode> prevPos = new Vector<PuzzleNode>();
    /*In our tree of puzzle States this will hold the solution puzzle Node-State*/
    private PuzzleNode currSolutionNode = null;
    private static int[][][] manhatanDistance;
    private PuzzleNode goalNode;

    public PuzzleGame(int[][] puzzle, Point spaceCell) throws Exception {

        if ((puzzle.length != gameWidth) | (puzzle[0].length != gameWidth)) {
            throw new Exception("InValid Game Width != 15");
        }
        this.root = new PuzzleNode(puzzle, null, spaceCell);
        int[][] goalPuzzle = {
            {0, 1, 2, 3},
            {4, 5, 6, 7},
            {8, 9, 10, 11},
            {12, 13, 14, 15}
        };
        goalNode = new PuzzleNode(goalPuzzle, null, new java.awt.Point(0, 0));
        initializeManhatanDistance();
    }

    public void solveGame(Algorithm algo) {

        switch (algo) {
            case AStar:
                System.out.println("Solving With AStar");
                solveGameAStar();
                break;

            case IDAStar:
                System.out.println("Solving With IDAStar");
                solveGameIDAStar();
                break;

            default:
                System.out.println("Algorithm Not Found");
                break;
        }
    }

    private void solveGameAStar() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void solveGameIDAStar() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isDone(PuzzleNode currNode) {
        if (currNode.compareTo(goalNode) == 0) {
            return true;
        }
        return false;
    }

    public void initializeManhatanDistance() {
        manhatanDistance = new int[gameWidth][4][4];
        for (int value = 0; value < gameWidth; value++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    manhatanDistance[value][i][j] = Math.abs(value / 4 - i) + Math.abs(value % 4 - j);
                }
            }
        }
    }

    public static int getManahtanDistance(PuzzleNode puzzleNode) {
        int distance = 0;
        int[][] puzzle = puzzleNode.getPuzzle();
        int[] puzzleRaw;
        for (int i = 0; i < puzzle.length; i++) {
            puzzleRaw = puzzle[i];
            for (int j = 0; j < puzzleRaw.length; j++) {
                distance += manhatanDistance[puzzle[i][j] - 1][i][j];
            }
        }
        return distance;
    }
}
