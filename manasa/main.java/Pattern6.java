public class Pattern6 {
    public static void main(String[] args) {
        int rows = 5;

        // Upper part
        for (int i = 0; i < rows; i++) {
            System.out.print("*");
            for (int j = rows -1; j > i; j--) {
                System.out.print(" ");
            }
            System.out.println("*");
        }

        // Lower part
        for (int i = 1; i < rows; i++) {
            System.out.print("*");
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
    }
}

