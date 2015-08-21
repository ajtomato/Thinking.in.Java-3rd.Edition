class DemoException extends Exception {

    DemoException() {
        super();
    }

    DemoException(String msg) {
        super(msg);
    }

    DemoException(Exception e) {
        super(e);
    }

    void detail() {
        System.out.println("You don't have enough information in the current context to fix the problem, "
                + "so you hand the problem out to a higher context where someone is qualified to make the proper decision.");
    }

    @Override
    public String getMessage() {
        return ("Exception[Demo]: " + super.getMessage());
    }

}