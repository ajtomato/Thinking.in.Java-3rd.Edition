abstract class AbstractType {

    abstract    void abstractMethod();

                void concreteMethod() {
                    System.out.println("AbstractType.concreteMethod");
                }

}

abstract class NoAbstractMethodAbstractType {

    void concreteMethod() {
        System.out.println("NoAbstractMethodAbstractType.concreteMethod");
    }
    
}