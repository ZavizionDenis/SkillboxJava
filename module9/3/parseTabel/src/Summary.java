public class Summary {
    private double income;
    private double withdraw;

    public Summary(double income, double withdraw) {
        this.income = income;
        this.withdraw = withdraw;
    }

    public static Summary merge(Summary s1, Summary s2) {
        return new Summary(s1.income + s2.income, s1.withdraw + s2.withdraw);
    }

    public static Summary fromTransaction(Transaction t) {
        return new Summary(t.getMoneyIn(), t.getMoneyOut());
    }

    public double getIncome() {
        return income;
    }

    public double getWithdraw() {
        return withdraw;
    }
}
