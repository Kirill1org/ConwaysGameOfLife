package SPbGUT.koromyslov;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;



public class Main {

    public static final int HEIGHT = 70; //heigh of array
    public static final int WIDTH = 120; //width of array

    public static void main(String[] args) throws InterruptedException {

        //Draw a field
        StdDraw.setCanvasSize(1200, 700);
        StdDraw.setXscale(1, 1199);
        StdDraw.setYscale(1, 699);

        //Create of arrays
        int a[][] = new int[HEIGHT][WIDTH];
        int b[][] = new int[HEIGHT][WIDTH];

        //Fill the boundaries of the array
        fillBorder(a);
        fillBorder(b);

        //Fill the array with live cells randomly
        fillRandom(a);

        //Create Glider
        //fillGlider(a);

        //Show filled array
        show(a);

        //Launch of life
        Life(a, b);
    }


    public static void show(int a[][]) {

        StdDraw.setPenColor(Color.BLUE);

        for (int y = 1; y < HEIGHT - 1; y++) {

            for (int x = 1; x < WIDTH - 1; x++) {

                if (a[y][x] == 1) StdDraw.rectangle(x * 10, y * 10, 5, 5);


            }
        }


    }

    public static void fillBorder(int a[][]) {
        StdDraw.setPenColor(Color.black);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if ((x == 0) | (y == 0)) {
                    a[y][x] = 9;
                }
                if ((y == HEIGHT - 1) | (x == WIDTH - 1)) a[y][x] = 9;
            }
        }
    }

    public static void fillRandom(int a[][]) {

        for (int k = 0; k < 500; k++) {
            a[1 + (int) (Math.random() * 69)][1 + (int) (Math.random() * 119)] = 1;
        }

    }

    public static void fillGlider(int a[][]) {
        for (int y = 1; y < HEIGHT - 1; y++) {
            for (int x = 1; x < WIDTH - 1; x++) {

                a[2][1] = 1;
                a[3][2] = 1;
                a[3][3] = 1;
                a[2][3] = 1;
                a[1][3] = 1;

            }

        }


    }

    public static int findNeighbour(int a[][], int m, int n) {
        int k = 0;

        if (a[m - 1][n - 1] == 1) k++;
        if (a[m - 1][n] == 1) k++;
        if (a[m - 1][n + 1] == 1) k++;
        if (a[m][n - 1] == 1) k++;
        if (a[m][n + 1] == 1) k++;
        if (a[m + 1][n] == 1) k++;
        if (a[m + 1][n - 1] == 1) k++;
        if (a[m + 1][n + 1] == 1) k++;

        return k;
    }

    public static void newPopulate(int a[][], int a1[][]) {

        for (int y = 1; y < HEIGHT - 1; y++) {
            for (int x = 1; x < WIDTH - 1; x++) {
                if ((a[y][x] == 1)
                        && ((findNeighbour(a, y, x) == 2)
                        | (findNeighbour(a, y, x) == 3)))
                {
                    a1[y][x] = 1;
                }
                if ((a[y][x] == 0) && (findNeighbour(a, y, x) == 3))
                {
                    a1[y][x] = 1;
                }
            }

        }
        show(a1);
    }


    public static void clearArray(int a[][]) {

        for (int y = 1; y < HEIGHT - 1; y++) {
            for (int x = 1; x < WIDTH - 1; x++) {
                a[y][x] = 0;
            }

        }
    }


    public static void Life(int a[][], int b[][]) throws InterruptedException {

        for (int i = 0; i < 500; i++) {

            if ((i % 2) != 0) {
                newPopulate(a, b);
                clearArray(a);
                StdDraw.clear(Color.WHITE);
            }
            if (i % 2 == 0) {
                newPopulate(b, a);
                clearArray(b);
                StdDraw.clear(Color.WHITE);
            }
        }
    }


}
