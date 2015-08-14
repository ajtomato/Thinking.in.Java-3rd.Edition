// import java.lang.*;      It is brought in automatically.
import java.util.*;
import com.gmail.ajtomato.Package;

public class HelloWorld {

    // Specifying initialization
    private int fField = 9;
    private int fField1 = testRandom();

    // Non-static instance initialization
    {
        fField = testRandom();
    }

    static boolean b1;
    static char c1;
    static byte b3;
    static short s1;
    static int i1 = testRandom();
    static long l1;
    static float f1;
    static double d1;

    static {
        System.out.println("static initialization is called after the static fields are initialized");
    }

    HelloWorld() {
        System.out.println("default constructor");
    }

    HelloWorld(String s) {
        this();
        System.out.println(s);
    }

    HelloWorld method(int fField) {
        this.fField = fField;
        System.out.println("fField: " + this.fField);
        return this;
    }

    public void finalize() {
        if (fField != 0) {
            System.out.println("finalize is used for two purpose: \n"
                    + "(1) cleanup memory allocated in native methods;\n"
                    + "(2) check the terminate condition (some resources should be release manually, once object is not needed. finalize can detect which are released.)");
        }
    }

    public String toString() {
        return "HelloWorld.toString";
    }

    static void testPrimitiveTypes() {
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

    static void testHighPrecisionNumbers() {
        java.math.BigInteger i = new java.math.BigInteger("0");
        System.out.println("BigInteger => int " + i.ZERO);

        java.math.BigDecimal d = new java.math.BigDecimal("0");
        System.out.println("BigDecimal => float " + d.ZERO);
    }

    static void testFieldAndMethod() {
        HelloWorld h = new HelloWorld();
        h.method(3);
    }

    static int testRandom() {
        Random r = new Random();
        System.out.println("Random (1 - 100): " + (r.nextInt(100) + 1));
        System.out.println("Random (0.0 - 1.0): " + r.nextFloat());
        return r.nextInt(100);
    }

    static void testEqualsOperator() {
        Integer i1 = new Integer(5);
        Integer i2 = new Integer(i1);
        System.out.println("== means refer to the same object: " + (i1 != i2));
        System.out.println("equals means the value of two objects are same: " + i1.equals(i2));
        System.out.println("The default behavior of equals for defined class is same as ==. It should be overriden.");
    }

    static void testUnsignedRightShift() {
        System.out.println("Right Shift: " + ((-1) >> 2) + ", Unsigned Right Shift: " + ((-1) >>> 2));
    }

    static void testLabel() {
        outer:
        for (int i = 0; i < 5; ++ i) {
            for (int j = 0; j < 3; ++ j) {
                if (i * j == 4) {
                    break outer;
                } else if (i + j == 3) {
                    continue outer;
                }
                System.out.println("INNER: " + j);
            }
            System.out.println("OUTER: " + i);
        }
    }

    static void testThis() {
        HelloWorld hw = new HelloWorld("Goodbye");
        hw.method(1);
    }

    static void testFinalize() {
        new HelloWorld();
        System.gc();
    }

    static void testPackage() {
        Package b = new Package();
    }

    static void testProtected() {
        BaseType b = new BaseType(9);
        b.protectedMethod();
    }

    static void testToString() {
        System.out.println(new HelloWorld());
    }

    static void testInheritance() {
        BaseType b = new DeriveType(9);
        b.method();
        b.dispose();
    }

    static void testFinalReference() {
        final BaseType b = new DeriveType(9);
        // [COMPILE ERROR]
        //  Final reference cannot point to others.
        // b = new DeriveType(11);
        b.dispose();
    }

    static void testFinalArgument(final String s) {
        // [COMPILE ERROR]
        // s = new String("Hello world");
        System.out.println("Final arguments cannot point to others");
    }

    static void testAbstractType() {
        AbstractType a = new ConcreteType();
        a.abstractMethod();

        NoAbstractMethodAbstractType n = new ConcreteMethodType();
        n.concreteMethod();
    }

    static void testNestedPrivateInterface() {
        ConcreteType c = new ConcreteType();
        // [COMPILE ERROR]
        //  The nested private interface is invisible outside parent.
        //  ConcreteType.NestedPrivateInterface i = c.createNestedPrivateInterface();

        // If the nested private interface is not referred directly, it can be used directly.
        c.setNestedPrivateInterface(c.createNestedPrivateInterface());
    }

    static void testInnerType() {
        InnerType i = new InnerType();
        InterfaceType it = i.innerClassWithInitializer("Hello");
        it.interfaceMethod();

        ConcreteType c = new ConcreteType();
        ConcreteType.NestedPrivateInterfaceImpl n = c.new NestedPrivateInterfaceImpl();
    }

    static void testMultiNestingInnerClass() {
        MultiNestingAccess a = new MultiNestingAccess();
        MultiNestingAccess.Nest1 b = a.new Nest1();
        MultiNestingAccess.Nest1.Nest2 c = b.new Nest2();
        c.method3();
    }

    public static final int CONST_VALUE = 101;

    /**
     * Test comment document.
     * @param i     The first parameter.
     * @param j     The second parameter.
     * @param k     The third parameter.
     * @return      The return value.
     * @exception   None
     */
    public static int testCommentDocument(int i, int j, int k) {
        return (i + j + k);
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; ++ i) {
            System.out.println(args[i]);
        }
        
        testMultiNestingInnerClass();
    }

}
