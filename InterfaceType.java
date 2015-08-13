interface InterfaceType1 {

    // Fields are implicitly static and final.
    int staticFinalField = 9;

    // The default access level is public.
    void interfaceMethod1();

}

interface InterfaceType2 {

    void interfaceMethod2();

    void interfaceMethod3();

}

interface InterfaceType3 extends InterfaceType1, InterfaceType2 {

    void interfaceMethod4();

}