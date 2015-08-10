class BaseType {

    protected int fField = 1;

    {
        System.out.println("BaseType instance initializer: " + fField + " => " + 5);
        fField = 5;
    }

    BaseType(int field) {
        System.out.println("BaseType Constructor: " + fField + " => " + field);
        fField = field;
    }

    protected void protectedMethod() {
        System.out.println("protectedMethod provides package access");
    }

    void dispose() {
        System.out.println("BaseType.dispose");
    }
}