// import java.lang.*;      It is brought in automatically.
import java.util.*;
import com.gmail.ajtomato.Package;

public class HelloWorld implements java.io.Serializable {

    // Specifying initialization
    private int fField = 9;
    private int fField1 = testRandom();
    private transient String fField2 = "Hello world";

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
        return "HelloWorld.toString: " + fField + ", " + fField1 + ", " + fField2;
    }

    private void writeObject(java.io.ObjectOutputStream str) throws java.io.IOException {
        str.defaultWriteObject();
        str.writeObject(fField2);
    }

    private void readObject(java.io.ObjectInputStream str) throws java.io.IOException, ClassNotFoundException {
        str.defaultReadObject();
        fField2 = (String)str.readObject();
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

    // There must be "throws", if an exception is thrown out of a method.
    static void testThrowException() throws DemoException {
        throw new DemoException("Hello");
    }

    static void testThrowExceptionChain(int c) throws DemoException {
        switch (c) {
            case 0:
                throw new DemoException(new NullPointerException());
            case 1:
                DemoException e = new DemoException("Hello");
                e.initCause(new NullPointerException());
                throw e;
        }
    }

    static void testException() throws Throwable {
        try {
            testThrowExceptionChain(1);
        } catch (DemoException e) {
            e.detail();
            e.printStackTrace();
            System.err.println(e);
            // fillInStackTrace returns Throwable
            throw e.fillInStackTrace();
        } finally {
            System.out.println("finally is always called.");
        }
    }

    static void testRTTI() {
        Class c1 = int.class;
        try {
            Class c2 = Class.forName("HelloWorld");
            Object o1 = c2.newInstance();
            if (o1 instanceof HelloWorld) {
                System.out.println("o1 is an object of HelloWorld");
            }
            if (c2.isInstance(o1)) {
                System.out.println("o1 is an object of HelloWorld");
            }
            Object o2 = new DeriveType(3);
            if (o2.getClass() == BaseType.class) {
                System.out.println("o2 is an object of BaseType");
            }
            if (o2.getClass() == DeriveType.class) {
                System.out.println("o2 is an object of DeriveType");
            }
            if (o2 instanceof BaseType) {
                System.out.println("o2 is an instance of BaseType");
            }
            System.out.println("Class Name: " + o2.getClass().getName() + ", Interface: " + o2.getClass().isInterface());
            Class[] ifs = o2.getClass().getInterfaces();
        } catch (ClassNotFoundException e) {
            System.err.println("HelloWorld.class is NOT found.");
        } catch (InstantiationException e) {
            System.err.println("Class.newInstance cannot create an object of HelloWorld.");
        } catch (IllegalAccessException e) {
            System.err.println("Class.newInstance cannot access the constructor of HelloWorld.");
        }
    }

    static void testReflection() {
        Class c = HelloWorld.class;
        java.lang.reflect.Constructor[] constructors = c.getConstructors();
        java.lang.reflect.Method[] methods = c.getMethods();
        java.lang.reflect.Field[] fields = c.getFields();
    }

    static class Item implements Comparable<Item> {

        Item(int field) {
            fField = field;
        }

        int fField;

        @Override
        public int compareTo(Item rhs) {
            if (fField < rhs.fField) {
                return -1;
            } else if (fField == rhs.fField) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return Integer.toString(fField);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object rhs) {
            return super.equals(rhs);
        }

    }

    static class ItemComparator implements Comparator<Item> {

        @Override
        public int compare(Item lhs, Item rhs) {
            if (lhs.fField < rhs.fField) {
                return -1;
            } else if (lhs.fField == rhs.fField) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    static void testArray() {
        HelloWorld[] a1;
        a1 = new HelloWorld[] { new HelloWorld(), new HelloWorld() };
        HelloWorld[] a2 = new HelloWorld[5];
        HelloWorld[] a3 = { new HelloWorld(), new HelloWorld() };

        int[] a4 = new int[5];
        Arrays.fill(a4, 1, 3, 7);
        for (int i = 0; i < a4.length; ++ i) {
            System.out.println(a4[i]);
        }
        int[] a5 = new int[8];
        System.arraycopy(a4, 2, a5, 1, 3);
        for (int i = 0; i < a5.length; ++ i) {
            System.out.println(a5[i]);
        }
        System.out.println("Arrays.equal: " + Arrays.equals(a4, a5));


        Item[] items = new Item[9];
        for (int i = 0; i < items.length; ++ i) {
            items[i] = new HelloWorld.Item(items.length - i);
        }
        Arrays.sort(items);
        Arrays.sort(items, new ItemComparator());
        for (int i = 0; i < items.length; ++ i) {
            System.out.println(items[i].fField);
        }
        Item it = new Item(2);
        System.out.println("binarySearch: " + Arrays.binarySearch(items, it) + ", " +  Arrays.binarySearch(items, it, new ItemComparator()));
    }

    static void testContainer() {
        // There are two types of container, including Collection and Map.

        // Collection
        Collection<Item> arrayList = new ArrayList<Item>();
        Collection<Item> linkedList = new LinkedList<Item>();
        Collection<Item> hashSet = new HashSet<Item>();
        Collection<Item> treeSet = new TreeSet<Item>();
        for (int i = 0; i < 5; ++ i) {
            arrayList.add(new Item(i));
            hashSet.add(new Item(i));
        }
        System.out.println(arrayList);
        System.out.println(hashSet);
        for (Iterator<Item> it = arrayList.iterator(); it.hasNext(); ) {
            Item i = it.next();
            System.out.println(i);
        }

        // Map
        Map<String, Item> hashMap = new HashMap<String, Item>();
        Map<String, Item> treeMap = new TreeMap<String, Item>();
        Map<String, Item> linkedHashMap = new LinkedHashMap<String, Item>();
        Map<String, Item> weakHashMap = new WeakHashMap<String, Item>();
        Map<String, Item> identityHashMap = new IdentityHashMap<String, Item>();
        hashMap.put("hello", new Item(0));
        hashMap.put("world", new Item(1));
        if (hashMap.containsKey("hello")) {
            System.out.println(hashMap.get("hello"));
        }
        System.out.println(hashMap.keySet());
        System.out.println(hashMap.values());
    }

    static void testFile() {
        // java.io.File is used as a file manager without accessing files.
        java.io.File path = new java.io.File(".");
        String[] files = path.list();
        System.out.println("All files: ");
        for (int i = 0; i < files.length; ++ i) {
            System.out.println(files[i]);
        }

        java.io.File tmpDir = new java.io.File("tmp");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        tmpDir.delete();
    }

    static void testInputOutput() {
        try {
            // Read from console
            java.io.BufferedReader consoleBr = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            String s = consoleBr.readLine();
            System.out.println(s);

            // Write to file
            java.io.PrintWriter out1 = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.FileWriter("tmp.log")));
            out1.println(s);
            out1.close();

            // Read characters from string
            java.io.StringReader sr = new java.io.StringReader("hello world");
            int c;
            while ((c = sr.read()) != -1) {
                System.out.println((char)c);
            }

            // Read memory
            try {
                // ByteArrayInputStream: string => bytes.
                java.io.DataInputStream in0 = new java.io.DataInputStream(new java.io.BufferedInputStream(new java.io.FileInputStream("tmp.log")));
                while (true) {
                    System.out.println((char)in0.readByte());
                }
            } catch (java.io.EOFException e) {
                System.out.println(e);
            }

            // Write memory
            try {
                java.io.DataOutputStream out2 = new java.io.DataOutputStream(new java.io.BufferedOutputStream(new java.io.FileOutputStream("tmp.log")));
                out2.writeDouble(3.14);
                out2.writeUTF("Hello world");
                out2.close();

                java.io.DataInputStream in1 = new java.io.DataInputStream(new java.io.BufferedInputStream(new java.io.FileInputStream("tmp.log")));
                System.out.println(in1.readDouble());
                System.out.println(in1.readUTF());
            } catch (java.io.EOFException e) {
                System.out.println(e);
            }

            // RandomAccessFile
            java.io.RandomAccessFile rf = new java.io.RandomAccessFile("tmp.log", "r");
            System.out.println(rf.readDouble());
            System.out.println(rf.readUTF());
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }

    static void testRedirectStandardIo() {
        try {
            java.io.BufferedInputStream in = new java.io.BufferedInputStream(new java.io.FileInputStream("tmp.log"));
            java.io.PrintStream out = new java.io.PrintStream(new java.io.BufferedOutputStream(new java.io.FileOutputStream("tmp.log")));
            System.setIn(in);
            System.setOut(out);
            System.setErr(out);
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e);
        }
    }

    static void testSerializable() {
        try {
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream("tmp.out"));
            out.writeObject("HelloHell\n");
            HelloWorld h = new HelloWorld();
            System.out.println(h);
            out.writeObject(h);
            out.close();

            java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.FileInputStream("tmp.out"));
            String s = (String)in.readObject();
            System.out.println(s);
            h = (HelloWorld)in.readObject();
            System.out.println(h);
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e);
        } catch (java.io.IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    static void testMultithread() {
        for (int i  = 0 ; i < 5; ++ i) {
            try {
                new Multithread(i).join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            
        }
        System.out.println("All threads have been started");
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

    public static void main(String[] args) throws Throwable {
        for (int i = 0; i < args.length; ++ i) {
            System.out.println(args[i]);
        }
        
        testMultithread();
    }

}
