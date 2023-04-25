import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class IslandSurvivalGame {
    static int health = 100;
    static int Hunger = 0;
    static int Thirst = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int day = 1;
        int playerX = 5;
        int playerY = 5;

        char[][] island = new char[10][10];
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                island[i][j] = '.';
            }
        }
        island[playerY][playerX] = 'P';

        System.out.println("Bienvenido al juego de supervivencia en una isla.");
        System.out.println("Tu objetivo es sobrevivir el mayor número de días posible.");
        System.out.println("Usa las teclas WASD para moverte.");

        // Temporizador para eventos aleatorios
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int event = random.nextInt(5);
                if (event == 0) {
                    System.out.println("¡Ha llovido! Tu sed disminuye en 30 puntos.");
                    Thirst -= 30;
                    if (Thirst < 0) {
                        Thirst = 0;
                    }
                } else if (event == 1) {
                    System.out.println("¡Un animal salvaje te ha atacado! Pierdes 30 puntos de salud.");
                    health -= 30;
                } else if (event == 2) {
                    System.out.println("¡Has encontrado una fuente de agua! Tu sed disminuye en 50 puntos.");
                    Thirst -= 50;
                    if (Thirst < 0) {
                        Thirst = 0;
                    }
                } else if (event == 3) {
                    System.out.println("¡Has encontrado un árbol frutal! Tu hambre disminuye en 50 puntos.");
                    Hunger -= 50;
                    if (Hunger < 0) {
                        Hunger = 0;
                    }
                }
            }
        }, 0, 5000);

        while (health > 0) {
            System.out.println("Día " + day + ".");
            System.out.println("Salud: " + health + ".");
            System.out.println("Hambre: " + Hunger + ".");
            System.out.println("Sed: " + Thirst + ".");

            // Mostrar la isla
            for (int i = 0; i < island.length; i++) {
                for (int j = 0; j < island[i].length; j++) {
                    System.out.print(island[i][j]);
                }
                System.out.println();
            }

            // Mover al jugador
            char move = scanner.nextLine().charAt(0);
            island[playerY][playerX] = '.';
            if (move == 'w') playerY--;
            else if (move == 's') playerY++;
            else if (move == 'd') playerX++;
            else if (move == 'a') playerX--;

            if(playerX<0) playerX=0;
            else if(playerX>=island[0].length) playerX=island[0].length-1;

            if(playerY<0) playerY=0;
            else if(playerY>=island.length) playerY=island.length-1;

            island[playerY][playerX] = 'P';

            Hunger +=10;
            Thirst +=10;

            if (Hunger >= 100 || Thirst >= 100) {
                health -= 20;
            }

            day++;
        }

        timer.cancel();
        System.out.println("Has sobrevivido " + (day -1) + " días.");
    }
}