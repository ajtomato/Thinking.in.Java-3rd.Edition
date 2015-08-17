class InnerType {

    private int fField = 3;

    private class PrivateInterfaceType implements InterfaceType {

        public void interfaceMethod() {
            System.out.println("InnerType.PrivateInterfaceType.interfaceMethod");
        }

    }

    InterfaceType getInterfaceType() {
        return new PrivateInterfaceType();
    }

    InterfaceType localInnerClass() {

        class LocalInnerClass implements InterfaceType {

            public void interfaceMethod() {
                System.out.println("InnerType.localInnerClass.LocalInnerClass");
            }

        }

        return new LocalInnerClass();
    }

    InterfaceType anonymousInnerClass() {

        return new InterfaceType() {

            public void interfaceMethod() {
                System.out.println("InnerType.anonymousInnerClass.InterfaceType");
            }

        };

    }

    HelloWorld anonymousInnerClassWithArgument() {

        return new HelloWorld("InnerClass") {

            HelloWorld method(int fField) {
                System.out.println("InnerType.anonymousInnerClassWithArgument.HelloWorld");
                return this;
            }

        };
    }

    InterfaceType innerClassWithInitializer(final String s) {

        return new InterfaceType() {

            private String fStr = s;

            {
                System.out.println("InnerType.innerClassWithInitializer.InterfaceType.instanceInitialization: " + fStr);
            }

            public void interfaceMethod() {
                System.out.println("InnerType.innerClassWithInitializer.InterfaceType.interfaceMethod: " + s + ", " + fField);
                InnerType me = InnerType.this;
            }

        };

    }
    
}

class MultiNestingAccess {

    private void method1() {}

    class Nest1 {

        private void method2() {}

        class Nest2 {

            void method3() {
                method2();
                method1();
            }

        }

    }

}

class InheritInner extends MultiNestingAccess.Nest1 {

    InheritInner(MultiNestingAccess m) {
        m.super();
    }
    
}