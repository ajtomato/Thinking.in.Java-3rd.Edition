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

}

class ConcreteMethodType extends NoAbstractMethodAbstractType {
}