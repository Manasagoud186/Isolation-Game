interface Vehicle {
    void start();
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car Starts");
    }

    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();
    }
}
