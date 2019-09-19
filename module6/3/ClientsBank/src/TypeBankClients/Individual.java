package TypeBankClients;

import java.util.Locale;

public class Individual extends Clients {

    private static final double MIN_COMMISION_RATE = 0.005;
    private static final double MAX_COMMISION_RATE = 0.01;

    public Individual(int clientAccountNumber, double clientAccountBalance) {
        super(clientAccountNumber, clientAccountBalance);
    }

    @Override
    public String getType() {
        return "ИП";
    }

    @Override
    public void fillBalance(double amount) {
        System.out.printf(Locale.ENGLISH,"Баланс на начало операции: %.2f%n", getBalance());
        double commision;
        if (amount < 1000.00) {
            commision = amount * MAX_COMMISION_RATE;
            clientAccountBalance += (amount - commision);
        }
        else {
            commision = amount * MIN_COMMISION_RATE;
            clientAccountBalance += (amount - commision);
        }
        System.out.printf(Locale.ENGLISH, "Сумма зачисления: %.2f, комиссия за пополнение: %.2f, остаток счета: %.2f, операция выполнена успешно.%n====================%n", amount, commision, getBalance());
    }

    @Override
    public boolean withdrawalBalance(double amount) {
        boolean isMonyEnough;
        if (amount <= clientAccountBalance) {
            isMonyEnough = true;
            System.out.printf(Locale.ENGLISH,"Баланс на начало операции: %.2f%n", getBalance());
            this.clientAccountBalance -= amount;
            System.out.printf(Locale.ENGLISH, "Сумма снятия: %.2f, остаток счета: %.2f, операция выполнена успешно.%n====================%n", amount, getBalance());
        }
        else {
            isMonyEnough = false;
            System.out.printf(Locale.ENGLISH, "Сумма снятия: %.2f больше баланса счета: %.2f, операция не возможна.%n====================%n", amount, getBalance());
        }
        return isMonyEnough;
    }
}