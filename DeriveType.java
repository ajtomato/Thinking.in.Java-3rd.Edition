class DeriveType extends BaseType {

    DeriveType() {
        System.out.println("DeriveType.Constructor");
    }

    @Override
    public void protectedMethod() {
        System.out.println("DeriveType.protectedMethod");
    }
}