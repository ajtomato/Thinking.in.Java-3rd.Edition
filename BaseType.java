class BaseType {

    protected int fField = 1;

    {
        final int constInt = 5;
        System.out.println("BaseType instance initializer: " + fField + " => " + constInt);
        fField = constInt;
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