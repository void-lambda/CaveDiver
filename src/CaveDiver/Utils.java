package CaveDiver.src.CaveDiver.src;

import java.util.Scanner;

public class Utils {
    public static final Scanner scanner = new Scanner(System.in);

    // Clear Screen (Console)
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Text typewriter
    public static void displayText(String text, int delayMs) {
        for (int i=0; i<text.length(); i++) {
            System.out.print(text.charAt(i));
            System.out.flush();
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println();
    }
}
