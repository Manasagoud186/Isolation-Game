public class MultipleExceptionsDemo {
    public static void main(String[] args) {
        String[] data = {"10", "0", "abc"}; // will cause NumberFormatException for "abc"

        try {
            // 1. ArithmeticException (division by zero)
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);
            int res = a / b; // division by zero -> ArithmeticException
            System.out.println("Result: " + res);

            // 2. ArrayIndexOutOfBoundsException
            System.out.println("Accessing element 5: " + data[5]);

            // 3. NumberFormatException (for "abc")
            int c = Integer.parseInt(data[2]);
            System.out.println("Parsed: " + c);

        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: " + e.getMessage());
        } catch (Exception e) { // fallback for any other exceptions
            System.out.println("Caught other Exception: " + e);
        } finally {
            System.out.println("Finally block executes always.");
        }

        // Alternative: multi-catch (since Java 7) for similar handling
        try {
            int x = Integer.parseInt("k"); // NumberFormatException
        } catch (ArithmeticException | NumberFormatException e) {
            System.out.println("Multi-catch handled: " + e);
        }
    }
}
