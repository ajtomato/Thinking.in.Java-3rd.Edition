class ConcreteType extends AbstractType {

    @Override
    void abstractMethod() {
        System.out.println("ConcreteType.abstractMethod");
    }

}

class ConcreteMethodType extends NoAbstractMethodAbstractType {
}