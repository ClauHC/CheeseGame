# 🐭 **Juego del Queso y el Ratón** 🧀

¿Habías oido del **Juego del Ratón y el Queso**? No, pues bienvenido a el. En este juego, guiarás a un ratón a través de un tablero lleno de sorpresas para encontrar el queso, evitando peligros, resolviendo desafíos y probando tu suerte. ¡El objetivo es acumular la mayor cantidad de puntos posible!

---

## 🎮 **¿Cómo se juga?**
El objetivo es simple: **mueve al ratón (🐭)** por un tablero tipo damero avanzando una casilla a la vez para hasta llegar al queso (🧀), debes evitar al gato que estará escondido (🐱) y descubrir casillas especiales que te harán ganar o perder puntos. También debes evitar quedar atrapado en tus propios pasos.

### Características principales:
- **Tablero dinámico**: Generado aleatoriamente en cada partida.
- **Casillas especiales**:
    - **+ +** : Responde preguntas para ganar puntos.
    - **- -** : Acierta el número o pierde puntos.
- **Casillas de puntos**: Acumula puntos moviéndote por el tablero.
- **Casilla del gato**: Tu solo no vayas a molestar al michi mientras duerme.
- **Modo test**: Actívalo para ver el tablero completo (ideal para depuración).

---

## 🧩 **Diseño del Juego**

### Estructura de Clases
- **Clase abstracta `GameCell`**:
    - Define atributos comunes como `content` (contenido visual) e `isDiscovered` (si la celda ha sido descubierta).
    - Proporciona métodos base como `setDiscovered()` y `toString()`.

- **Interfaz `Questionable`**:
    - Define métodos esenciales para celdas con preguntas: `getQuestion()` y `submitAnswer()`.

- **Jerarquía de celdas**:
    - **`PointsCell`**: Celda normal que otorga puntos al ser descubierta.
    - **`EndGameCell`**: Celda de fin de juego (queso 🧀 o gato 🐱).
    - **`PlusCell`**: Celda con preguntas que otorgan puntos si se aciertan.
    - **`MinusCell`**: Celda con preguntas que restan puntos si se fallan.

### Lógica del Tablero
- **Generación aleatoria**:
    - El tablero se crea con celdas especiales (➕, ➖, 🐱, 🧀) en posiciones aleatorias.
    - Se asegura que las celdas especiales no se solapen usando métodos de validación.

- **Validación de movimientos**:
    - Se comprueban los límites del tablero y si las celdas ya han sido descubiertas.

- **Detección de fin de juego**:
    - El juego termina si el ratón encuentra el queso, es atrapado por el gato, o no tiene movimientos válidos.

### Interacción con el Usuario
- **Clase `Main`**:
    - Gestiona la entrada/salida: lectura de movimientos (W/A/S/D) y respuestas a preguntas.
    - Separa la lógica del juego (clases del modelo) de la interacción con el usuario.

- **Mensajes intuitivos**:
    - Se muestra la puntuación acumulada después de cada movimiento.
    - Mensajes detallados al finalizar el juego (victoria o derrota).

---

## 🛠️ **Proceso de Creación**

1. **Diseño de Clases**:
    - Se definieron las clases base (`GameCell`, `PointsCell`, `EndGameCell`, etc.) para representar las celdas del tablero.
    - Se implementó la interfaz `Questionable` para manejar preguntas en celdas especiales.

2. **Lógica del Tablero**:
    - Se desarrolló un sistema para generar el tablero de manera aleatoria, asegurando que las celdas especiales no se solapen.
    - Se implementó la validación de movimientos y la detección de fin de juego.

3. **Interacción con el Usuario**:
    - Se creó la clase `Main` para manejar la interacción con el jugador, mostrando mensajes claros y solicitando movimientos o respuestas.

---

## 🎯 **Objetivo del Juego**
- **Encuentra el queso (🧀)**: Guía al ratón (🐭) a través del tablero para alcanzar el queso.
- **Acumula puntos**: Responde preguntas en celdas especiales para sumar puntos.
- **Evita al gato (🐱)**: Si el ratón cae en la celda del gato, ¡pierdes!

---

## 🚀 **¡Comienza a Jugar!**
Ejecuta el programa y sigue las instrucciones en pantalla. ¡Buena suerte y que encuentres el queso antes de que el gato te atrape! 🐭🧀


# Diagrama de Clases

| **Clase**          | **Tipo**          | **Atributos**                                                                 | **Métodos**                                                                                   | **Relaciones**                                                               |
|---------------------|-------------------|------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| **Main**           | Clase            | `scanner: Scanner`                                                           | `main(args: String[]): void`                                                                  | Usa **Game** para ejecutar el programa.                                      |
| **Game**           | Clase            | `board: GameCell[][]`<br>`mouseInX: int`<br>`mouseInY: int`<br>`score: int`<br>`isTestMode: boolean` | `Game(isTestMode: boolean)`<br>`updateScore(points: int): void`<br>`createBoard(): void`<br>`showTestBoard(): void`<br>`setTestMode(isTestMode: boolean): void`<br>`isPlayerTrapped(): boolean`<br>`play(): void`<br>`movePlayer(direction: char, scanner: Scanner): boolean`<br>`printBoard(): void` | Contiene un arreglo de **GameCell** (y sus subclases) para crear el tablero. |
| **GameCell**       | Clase Abstracta  | `isDiscovered: boolean`<br>`content: String`<br>`isVisible: boolean`<br>`type: EndGamecellType` | `GameCell(content: String)`<br>`isDiscovered(): boolean`<br>`setDiscovered(): void`<br>`setVisible(): void`<br>`toString(): String` | Extendida por **PointsCell**, **EndGameCell**, **PlusCell**, **MinusCell**.  |
| **PointsCell**     | Subclase         | `points: int`                                                                | `PointsCell()`<br>`getPoints(): int`<br>`discovered(): void`                                   | Extiende **GameCell**.                                                       |
| **EndGameCell**    | Subclase         | `type: EndGamecellType`                                                      | `EndGameCell(type: EndGamecellType)`<br>`getType(): EndGamecellType`                          | Extiende **GameCell**.                                                       |
| **PlusCell**       | Subclase         | `question: String`<br>`options: String[]`<br>`correctAnswer: String`         | `PlusCell()`<br>`getQuestion(): String`<br>`submitAnswer(answer: String): boolean`<br>`reveal(scanner: Scanner, game: Game): void` | Extiende **GameCell** e implementa **Questionable**.                         |
| **MinusCell**      | Subclase         | `question: String`<br>`options: String[]`<br>`correctAnswer: String`         | `MinusCell()`<br>`getQuestion(): String`<br>`submitAnswer(answer: String): boolean`<br>`reveal(scanner: Scanner, game: Game): void` | Extiende **GameCell** e implementa **Questionable**.                         |
| **Questionable**   | Interfaz         | -                                                                            | `getQuestion(): String`<br>`submitAnswer(answer: String): boolean`                            | Implementada por **PlusCell** y **MinusCell**.                               |
| **EndGamecellType**| Enum             | `Cheese`<br>`Cat`                                                            | `getSymbol(): String`                                                                         | Utilizado por **EndGameCell** para definir el tipo de celda.                 |