package model;
import java.util.Random;
import java.util.Scanner;


public class Game {
    private GameCell [][] board;
    private int mouseInX, mouseInY;
    private int score;
    //Por si el tablero en un futuro no fuera de 4 casillas y/o no fuera cuadrado
    private final int BOARD_DIMENSION_X = 4;
    private final int BOARD_DIMENSION_Y = 4;
    private final int CHEESE_IN_X = 3;
    private final int CHEESE_IN_Y = 3;


    /** Constructor de la clase Game
     *  Genera el tablero y lo llena, inicializa el ratón en las cordenadas 0,0
     *  Inicializa el Score en cero puntos, se asegura de que la primera casilla
     *  funcione como el resto de casillas de puntos.
     */
    public Game (){
        board = new GameCell[BOARD_DIMENSION_X][BOARD_DIMENSION_Y];
        createBoard();
        mouseInX = 0;
        mouseInY = 0;
        score = 0;
        //Luego de crear y llenar el tablero se modifica la primera casilla
        if (board[0][0] instanceof PointsCell) {
            PointsCell startCell = (PointsCell) board[0][0];
            startCell.reveal();
            score += startCell.getPoints();
        }


    }

    /** Metod que crea el tablero de juego
     *  Coloca el queso, rellena con puntos las demás casillas, coloca al gato en una de las casillas,
     *  coloca una casilla de ++, coloca una casilla de --
     */
    private void createBoard(){
        for (int i = 0; i < BOARD_DIMENSION_X; i++){
            for (int j = 0; j < BOARD_DIMENSION_Y; j++){
                //Poner el queso
                if (i == CHEESE_IN_X && j == CHEESE_IN_Y){
                    board[i][j] = new EndGameCell(EndGamecellType.Cheese);
                //Rellenar con casillas de puntos
                }else{
                    board[i][j] = new PointsCell();
                }
            }
        }

        //Poner el gato en cualquier lugar menos al inicio y en la casilla del queso
        Random randomCell = new Random();
        int catInX, catInY;
        do {
            catInX = randomCell.nextInt(BOARD_DIMENSION_X);
            catInY = randomCell.nextInt(BOARD_DIMENSION_Y);
        } while ((catInX==0 && catInY==0) || (catInX==CHEESE_IN_X && catInY==CHEESE_IN_Y));
        board[catInX][catInY]= new EndGameCell(EndGamecellType.Cat);

        // Poner una PlusCell que no caiga en (0,0), en el queso o la casilla del gato
        int plusInX, plusInY;
        do {
            plusInX = randomCell.nextInt(BOARD_DIMENSION_X);
            plusInY = randomCell.nextInt(BOARD_DIMENSION_Y);
        } while ((plusInX == 0 && plusInY == 0) || (plusInX == CHEESE_IN_X && plusInY == CHEESE_IN_Y) || (plusInX == catInX && plusInY == catInY));
        board[plusInX][plusInY] = new PlusCell();

        // Poner una MinusCell que no coincida con las especiales anteriores
        int minusInX, minusInY;
        do {
            minusInX = randomCell.nextInt(BOARD_DIMENSION_X);
            minusInY = randomCell.nextInt(BOARD_DIMENSION_Y);
        } while ((minusInX == 0 && minusInY == 0) || (minusInX == CHEESE_IN_X && minusInY == CHEESE_IN_Y) ||
                (minusInX == catInX && minusInY == catInY) || (minusInX == plusInX && minusInY == plusInY));
        board[minusInX][minusInY] = new MinusCell();
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

        if (newX >= 0 && newX < BOARD_DIMENSION_X && newY >= 0 && newY < BOARD_DIMENSION_Y) {
            //Reaccionar a las casillas de puntos
            if (board[newX][newY] instanceof PointsCell) {
                PointsCell cell = (PointsCell) board[newX][newY];
                if (!cell.isDiscovered()) {
                    score += cell.getPoints();
                    cell.reveal();
                }
            }

            //Reaccionar al gato
            if (board[newX][newY] instanceof EndGameCell) {
                EndGameCell cell = (EndGameCell) board[newX][newY];
                if (cell.getType() == EndGamecellType.Cat) {
                    System.out.println("¡Fuiste a molestar al gato! Game Over.");
                    System.exit(0);
                }
            }

            mouseInX = newX;
            mouseInY = newY;
            return true;
        }
        return false;
    }

    private void printBoard(){
        for (int i = 0; i < BOARD_DIMENSION_X; i++){
            for (int j = 0; j < BOARD_DIMENSION_Y; j++){
                if (i == mouseInX && j == mouseInY){
                    System.out.print("MM ");
                }else{
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Your current score is: " + score);
    }
}
