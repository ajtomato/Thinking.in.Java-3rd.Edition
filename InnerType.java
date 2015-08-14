class InnerType {

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
    
}