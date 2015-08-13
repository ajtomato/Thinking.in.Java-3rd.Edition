class InnerType {

    private class PrivateInterfaceType implements InterfaceType {

        public void interfaceMethod() {
            System.out.println("InnerType.PrivateInterfaceType.interfaceMethod");
        }

    }

    InterfaceType getInterfaceType() {
        return new PrivateInterfaceType();
    }
    
}