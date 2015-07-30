// import java.lang.*;      It is brought in automatically.
import java.util.*;

class HelloWorld {

    private int fField = 9;

    static boolean b1;
    static char c1;
    static byte b3;
    static short s1;
    static int i1;
    static long l1;
    static float f1;
    static double d1;

    void Method() {
        System.out.println("A method is called. Field: " + fField);
    }

    static void TestPrimitiveTypes() {
        // [COMPILE ERROR]
        //  The variables of primitive type in the method must be initialized.
        //  The fields of primitive type are initialized as the default value.
        // boolean b1;
        Boolean b2 = new Boolean(b1);
        System.out.println("[boolean]: default - " + b1 + ", false - " + b2.FALSE + ", true - " + b2.TRUE);
        
        Character c2 = new Character(c1);
        System.out.println("[char]: default - " + (int)c1 + ", min - " + (int)c2.MIN_VALUE + ", max - " + (int)c2.MAX_VALUE);
        
        Byte b4 = new Byte(b3);
        System.out.println("[byte]: default - " + b3 + ", min - " + b4.MIN_VALUE + ", max - " + b4.MAX_VALUE);
        
        Short s2 = new Short(s1);
        System.out.println("[short]: default - " + s1 + ", min - " + s2.MIN_VALUE + ", max - " + s2.MAX_VALUE);

        Integer i2 = new Integer(i1);
        System.out.println("[int]: default - " + i1 + ", min - " + i2.MIN_VALUE + ", max - " + i2.MAX_VALUE);

        Long l2 = new Long(l1);
        System.out.println("[long]: default - " + l1 + ", min - " + l2.MIN_VALUE + ", max - " + l2.MAX_VALUE);

        Float f2 = new Float(f1);
        System.out.println("[float]: default - " + f1 + ", min - " + f2.MIN_VALUE + ", max - " + f2.MAX_VALUE);

        Double d2 = new Double(d1);
        System.out.println("[double]: default - " + d1 + ", min - " + d2.MIN_VALUE + ", max - " + d2.MAX_VALUE);
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
        TestPrimitiveTypes();
    }

}
