class A {
    void show() {
        System.out.println("Class A");
    }
}

class B extends A {
    void show() {
        System.out.println("Class B");
    }

    public static void main(String[] args) {
        A obj = new B();
        obj.show();
    }
}
