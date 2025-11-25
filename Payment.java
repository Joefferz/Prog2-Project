interface Payment {
    public final double STUDENT_FEE = 5;
    public final double EXTERNAL_MEMBER_FEE = 10;

    abstract double calculate(String membership);
}