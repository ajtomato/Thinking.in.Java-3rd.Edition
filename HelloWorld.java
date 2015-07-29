class HelloWorld {

    private int fField = 9;

    void Method() {
        System.out.println("A method is called. Field: " + fField);
    }

    static void TestPrimitiveTypes() {
        boolean b1 = false;
        Boolean b2 = new Boolean(b1);
        System.out.println("boolean: " + b2.FALSE + ", " + b2.TRUE);

        char c1 = 'c';
        Character c2 = new Character(c1);
        System.out.println("char: " + (int)c2.MIN_VALUE + ", " + (int)c2.MAX_VALUE);

        byte b3 = 0;
        Byte b4 = new Byte(b3);
        System.out.println("byte: " + b4.MIN_VALUE + ", " + b4.MAX_VALUE);

        short s1 = 0;
        Short s2 = new Short(s1);
        System.out.println("short: " + s2.MIN_VALUE + ", " + s2.MAX_VALUE);

        int i1 = 0;
        Integer i2 = new Integer(i1);
        System.out.println("int: " + i2.MIN_VALUE + ", " + i2.MAX_VALUE);

        long l1 = 0;
        Long l2 = new Long(l1);
        System.out.println("long: " + l2.MIN_VALUE + ", " + l2.MAX_VALUE);

        float f1 = 0.0f;
        Float f2 = new Float(f1);
        System.out.println("float: " + f2.MIN_VALUE + ", " + f2.MAX_VALUE);

        double d1 = 0.0;
        Double d2 = new Double(d1);
        System.out.println("double: " + d2.MIN_VALUE + ", " + d2.MAX_VALUE);
    }

    static void TestHighPrecisionNumbers() {
        java.math.BigInteger i = new java.math.BigInteger("0");
        System.out.println("BigInteger => int " + i.ZERO);

        java.math.BigDecimal d = new java.math.BigDecimal("0");
        System.out.println("BigDecimal => float " + d.ZERO);
    }

    static void TestFieldAndMethod() {
        HelloWorld h = new HelloWorld();
        h.Method();
    }

    public static void main(String[] args) {
        // System.out.println("Hello World");
        TestFieldAndMethod();
    }

}
