class DeriveType extends BaseType {

    private int fField = 3;

    {
        System.out.println("DeriveType instance initializer: " + fField + " => " + 7);
        fField = 7;
    }

    DeriveType(int field) {
        super(field);
        System.out.println("DeriveType.Constructor: " + fField + " => " + field);
        fField = field;
    }

    @Override
    public void protectedMethod() {
        System.out.println("DeriveType.protectedMethod");
    }

    @Override
    void dispose() {
        System.out.println("DeriveType.dispose");
        super.dispose();
    }
}