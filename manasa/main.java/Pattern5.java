public class Pattern5 {
    public static void main(String[] args) {
        int rows = 4;

        // Upper part
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= rows; j++) {
                if (j == i || j == (rows - i + 1))
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
}
