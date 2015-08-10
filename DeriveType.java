class DeriveType extends BaseType {

    private int fField = 3;
    private final int fFinalField;

    {
        final int constInt = 7;
        System.out.println("DeriveType instance initializer: " + fField + " => " + constInt);
        fField = constInt;
    }

    DeriveType(int field) {
        super(field);
        fFinalField = field;
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

    // [COMPILE ERROR]
    //  final method cannot be overriden
    //  @Override
    //  void finalMethod() {
    //  }
}