class ConcreteType extends AbstractType implements InterfaceType1, InterfaceType2 {

    @Override
    void abstractMethod() {
        System.out.println("ConcreteType.abstractMethod");
    }

    @Override
    public void interfaceMethod1() {
        System.out.println("ConcreteType.interfaceMethod1: " + staticFinalField);
    }

    @Override
    public void interfaceMethod2() {
        System.out.println("ConcreteType.interfaceMethod2");
    }

    private interface NestedPrivateInterface {

    }

    class NestedPrivateInterfaceImpl implements NestedPrivateInterface {

    }

    NestedPrivateInterface createNestedPrivateInterface() {
        return new NestedPrivateInterfaceImpl();
    }

    void setNestedPrivateInterface(NestedPrivateInterface n) {
        
    }

    public static class NestedClass {

        public static void main(String[] args) {
            System.out.println("Nested class does NOT need an object of outer class, since it is static.");
            System.out.println("Nested class can be used to build a testbed.");
        }

    }

}

class ConcreteMethodType extends NoAbstractMethodAbstractType {
}