package model;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private GameCell[][] board;
    private int mouseInX, mouseInY;
    private int score;
    private boolean isTestMode;
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
    public Game(boolean isTestMode) {
        this.isTestMode = isTestMode;
        board = new GameCell[BOARD_DIMENSION_X][BOARD_DIMENSION_Y];
        createBoard();
        mouseInX = 0;
        mouseInY = 0;
        this.score = 0;

        // Revelar la primera casilla y sumar puntos
        if (board[0][0] instanceof PointsCell) {
            PointsCell startCell = (PointsCell) board[0][0];
            startCell.discovered();
            score += startCell.getPoints();
        }
    }

    /**
     * Métod que actualiza el puntaje del jugador
     * la clase PlusCell y MinusCell lo necesitan para su correcto funcionamiento
     * @param points
     */
    public void updateScore(int points) {
        this.score += points;
        System.out.println("Puntuación actual: " + this.score);
    }

    /**
     * Métod que crea el tablero y lo llena con las casillas correspondientes
     */
    private void createBoard() {
        for (int i = 0; i < BOARD_DIMENSION_X; i++) {
            for (int j = 0; j < BOARD_DIMENSION_Y; j++) {
                if (i == CHEESE_IN_X && j == CHEESE_IN_Y) {
                    board[i][j] = new EndGameCell(EndGamecellType.Cheese);
                } else {
                    board[i][j] = new PointsCell();
                }
            }
        }

        Random randomCell = new Random();

        // Colocar el gato
        int catInX, catInY;
        do {
            catInX = randomCell.nextInt(BOARD_DIMENSION_X);
            catInY = randomCell.nextInt(BOARD_DIMENSION_Y);
        } while ((catInX == 0 && catInY == 0) || (catInX == CHEESE_IN_X && catInY == CHEESE_IN_Y));
        board[catInX][catInY] = new EndGameCell(EndGamecellType.Cat);

        // Colocar la casilla ++
        int plusInX, plusInY;
        do {
            plusInX = randomCell.nextInt(BOARD_DIMENSION_X);
            plusInY = randomCell.nextInt(BOARD_DIMENSION_Y);
        } while ((plusInX == 0 && plusInY == 0) || (plusInX == CHEESE_IN_X && plusInY == CHEESE_IN_Y) || (plusInX == catInX && plusInY == catInY));
        board[plusInX][plusInY] = new PlusCell();

        // Colocar la casilla --
        int minusInX, minusInY;
        do {
            minusInX = randomCell.nextInt(BOARD_DIMENSION_X);
            minusInY = randomCell.nextInt(BOARD_DIMENSION_Y);
        } while ((minusInX == 0 && minusInY == 0) || (minusInX == CHEESE_IN_X && minusInY == CHEESE_IN_Y) ||
                (minusInX == catInX && minusInY == catInY) || (minusInX == plusInX && minusInY == plusInY));
        board[minusInX][minusInY] = new MinusCell();
    }

    /**
     * Métod que muestra el tablero en modo test
     * necesita al setTestMode para funcionar
     */
    public void showTestBoard() {
        for (int i = 0; i < BOARD_DIMENSION_X; i++) {
            for (int j = 0; j < BOARD_DIMENSION_Y; j++) {
                board[i][j].setVisible();
            }
        }
        printBoard();
    }

    /**
     * Métod que pone el tablero en modo test
     * esto activa la visibilidad de las casillas
     * recibe un valor booleano como parámetro
     * este métod lo activa y showTestBoard lo muestra
     * @param isTestMode
     */
    public void setTestMode(boolean isTestMode) {
        this.isTestMode = isTestMode;
        for (int i = 0; i < BOARD_DIMENSION_X; i++) {
            for (int j = 0; j < BOARD_DIMENSION_Y; j++) {
                if (isTestMode) {
                    board[i][j].setVisible();
                } else {
                    if (board[i][j] instanceof EndGameCell) {
                        EndGameCell cell = (EndGameCell) board[i][j];
                        if (cell.getType() == EndGamecellType.Cheese) {
                            cell.setVisible();
                        }
                    }
                }
            }
        }
    }

    /**
     * Métod que verifica si el ratón está encerrado
     * devuelve un valor booleano
     * @return
     */
    public boolean isPlayerTrapped() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newX = mouseInX + dir[0];
            int newY = mouseInY + dir[1];
            if (newX >= 0 && newX < BOARD_DIMENSION_X && newY >= 0 && newY < BOARD_DIMENSION_Y) {
                if (!board[newX][newY].isDiscovered()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Métod que inicia el juego
     * Bucle principal del juego que maneja la entrada
     * del jugador y la progresión del juego.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();
            if (isPlayerTrapped()) {
                System.out.println("¡Estás encerrado! Game Over.");
                System.exit(0); // Cerrar el juego si queda atrapado
            }
            System.out.print("Mueve al ratón (WASD): ");
            char move = scanner.next().toUpperCase().charAt(0);

            // El juego se acaba si cae en las cassillas de fin de juego
            if (movePlayer(move, scanner)) {
                if (board[mouseInX][mouseInY] instanceof EndGameCell) {
                    EndGameCell cell = (EndGameCell) board[mouseInX][mouseInY];
                    if (cell.getType() == EndGamecellType.Cheese) {
                        System.out.println("¡Ganaste! Puntaje final: " + score);
                        System.exit(0); // Cerrar el juego
                    } else if (cell.getType() == EndGamecellType.Cat) {
                        System.out.println("¡Fuiste a molestar al gato! Game Over.");
                        System.exit(0); // Cerrar el juego
                    }
                }
            }
        }
    }

    /**
     * Métod que controla los movimientos del ratón
     * según la dirección que se le pase y las casillas en las que caiga
     * @param direction
     * @param scanner
     * @return
     */
    public boolean movePlayer(char direction, Scanner scanner) {
        int newX = mouseInX, newY = mouseInY;

        // Determinar nueva posición según la dirección
        switch (direction) {
            case 'W': newX--; break; // Arriba
            case 'S': newX++; break; // Abajo
            case 'A': newY--; break; // Izquierda
            case 'D': newY++; break; // Derecha
            default: return false;
        }

        // Verificar que la nueva posición esté dentro de los límites del tablero
        if (newX < 0 || newX >= BOARD_DIMENSION_X || newY < 0 || newY >= BOARD_DIMENSION_Y) {
            System.out.println("No puedes salir de los límites del tablero.");
            return false;
        }

        // Verificar que no se retroceda a una casilla ya visitada
        if (board[newX][newY].isDiscovered() && !(board[newX][newY] instanceof EndGameCell)) {
            System.out.println("No puedes retroceder a una casilla por la que ya pasaste.");
            return false;
        }

        // Casilla a la que se dirige
        GameCell targetCell = board[newX][newY];

        // Si es de puntos
        if (targetCell instanceof PointsCell) {
            PointsCell cell = (PointsCell) targetCell;
            if (!cell.isDiscovered()) {
                score += cell.getPoints(); // Sumar puntos
                cell.discovered();
            }
        }
        // Si es de ++
        else if (targetCell instanceof PlusCell) {
            PlusCell cell = (PlusCell) targetCell;
            cell.discovered(scanner, this); // Pregunta y suma puntos si acierta
        }
        // Si es de --
        else if (targetCell instanceof MinusCell) {
            MinusCell cell = (MinusCell) targetCell;
            cell.discovered(scanner, this); // Pregunta y resta puntos si falla
        }
        // Si es de fin de juego (queso o gato)
        else if (targetCell instanceof EndGameCell) {
            EndGameCell cell = (EndGameCell) targetCell;
            if (cell.getType() == EndGamecellType.Cheese) {
                System.out.println("¡Ganaste! Puntaje final: " + score);
                System.exit(0); // Fin del juego (victoria)
            } else if (cell.getType() == EndGamecellType.Cat) {
                System.out.println("¡Fuiste a molestar al gato! Game Over.");
                System.exit(0); // Fin del juego (derrota)
            }
        }

        // Marcar la casilla anterior como visitada
        board[mouseInX][mouseInY].setDiscovered();

        // Actualizar posición del ratón
        mouseInX = newX;
        mouseInY = newY;
        return true;
    }

    /**
     * Métod que imprime el estado actual del tablero
     */
    private void printBoard() {
        for (int i = 0; i < BOARD_DIMENSION_X; i++) {
            for (int j = 0; j < BOARD_DIMENSION_Y; j++) {
                if (i == mouseInX && j == mouseInY) {
                    System.out.print("MM "); // Posición del ratón
                } else if (isTestMode) {
                    // // Modo test: mostrar el contenido real de la celda
                    if (board[i][j] instanceof PointsCell) {
                        PointsCell cell = (PointsCell) board[i][j];
                        System.out.print(cell.getPoints() + " "); // Mostrar 10, 20 o 30
                    } else if (board[i][j] instanceof PlusCell) {
                        System.out.print("++ "); // Show ++
                    } else if (board[i][j] instanceof MinusCell) {
                        System.out.print("-- "); // Show --
                    } else if (board[i][j] instanceof EndGameCell) {
                        EndGameCell cell = (EndGameCell) board[i][j];
                        System.out.print(cell.getType().getSymbol() + " "); // Mostrar CC o CH
                    }
                } else {
                    // Modo de juego: muestra 00 si no se ha descubierto, o ·. si se ha descubierto
                    if (board[i][j].isDiscovered()) {
                        System.out.print("·. ");
                    } else if (board[i][j] instanceof EndGameCell) {
                        EndGameCell cell = (EndGameCell) board[i][j];
                        if (cell.getType() == EndGamecellType.Cheese) {
                            System.out.print("CH "); // Mostrar CH para queso
                        } else {
                            System.out.print("00 "); // Mostrar 00 para gato
                        }
                    } else {
                        System.out.print("00 ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Your current score is: " + score);
    }
}
