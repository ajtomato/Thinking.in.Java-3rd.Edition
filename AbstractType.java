abstract class AbstractType {

    abstract    void abstractMethod();

                void concreteMethod() {
                    System.out.println("AbstractType.concreteMethod");
                }

                public void interfaceMethod3() {
                    System.out.println("AbstractType.interfaceMethod3");
                }

}

abstract class NoAbstractMethodAbstractType {

    void concreteMethod() {
        System.out.println("NoAbstractMethodAbstractType.concreteMethod");
    }
    
}