package model;
import java.util.Scanner;


public class Game {
    private GameCell [][] board;
    private int mouseInX, mouseInY;
    private int score;
    //Por si el tablero en un futuro no fuera de 4 casillas y/o no fuera cuadrado
    private final int HORIZONTAL = 4;
    private final int VERTICAL = 4;
    private final int FINAL_CELL_HORIZONTAL = 3;
    private final int FINAL_CELL_VERTICAL = 3;



    public Game (){
        board = new GameCell[HORIZONTAL][VERTICAL];
        createBoard();
        mouseInX = 0;
        mouseInY = 0;
        score = 0;
    }

    private void createBoard(){
        for (int i = 0; i < HORIZONTAL; i++){
            for (int j = 0; j < VERTICAL; j++){
                if (i == FINAL_CELL_HORIZONTAL && j == FINAL_CELL_VERTICAL){
                    board[i][j] = new EndGameCell();
                }else{
                    board[i][j] = new PointsCell();
                }
            }
        }

    }

    public void play (){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();
            System.out.print("Mueve al ratón (WASD): ");
            char move = scanner.next().toUpperCase().charAt(0);

            if (movePlayer(move)) {
                if (board[mouseInX][mouseInY] instanceof EndGameCell) {
                    System.out.println("¡Ganaste! Puntaje final: " + score);
                    break;
                }
            }
        }
        scanner.close();
    }

    private boolean movePlayer(char move){
        int newX = mouseInX, newY = mouseInY;
        switch (move) {
            case 'W': newX--; break;
            case 'S': newX++; break;
            case 'A': newY--; break;
            case 'D': newY++; break;
            default: return false;
        }

        if (newX >= 0 && newX < 4 && newY >= 0 && newY < 4) {
            if (board[newX][newY] instanceof PointsCell) {
                PointsCell cell = (PointsCell) board[newX][newY];
                if (!cell.isDiscovered()) {
                    score += cell.getPoints();
                    cell.reveal();
                }
            }
            mouseInX = newX;
            mouseInY = newY;
            return true;
        }
        return false;
    }

    private void printBoard(){
        for (int i = 0; i < HORIZONTAL; i++){
            for (int j = 0; j < VERTICAL; j++){
                if (i == mouseInX && j == mouseInY){
                    //board[i][j] = new EndGameCell();
                    System.out.print("MM ");
                }else{
                    //board[i][j] = new PointsCell();
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Your current score is: " + score);
    }
}
