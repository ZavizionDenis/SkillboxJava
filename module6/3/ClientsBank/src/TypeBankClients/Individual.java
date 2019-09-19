package TypeBankClients;

import java.util.Locale;

public class Individual extends Clients {

    public Individual(int accountNumber, double accountBalance) {
        this.clientAccountNumber = accountNumber;
        this.clientAccountBalance = accountBalance;
        this.clientAccountType = "ИП";
    }

    @Override
    public void fillBalance(double amount) {
        System.out.printf(Locale.ENGLISH,"Баланс на начало операции: %.2f%n", getBalance());
        double commision;
        if (amount < 1000.00) {
            commision = amount / 100;
            clientAccountBalance += (amount - commision);
        }
        else {
            commision = amount / 200;
            clientAccountBalance += (amount - commision);
        }
        System.out.printf(Locale.ENGLISH, "Сумма зачисления: %.2f, комиссия за пополнение: %.2f, остаток счета: %.2f, операция выполнена успешно.%n====================%n", amount, commision, getBalance());
    }

    @Override
    public void withdrawalBalance(double amount) {
        if (amount <= clientAccountBalance) {
            System.out.printf(Locale.ENGLISH,"Баланс на начало операции: %.2f%n", getBalance());
            this.clientAccountBalance -= amount;
            System.out.printf(Locale.ENGLISH, "Сумма снятия: %.2f, остаток счета: %.2f, операция выполнена успешно.%n====================%n", amount, getBalance());
        }
        else {
            System.out.printf(Locale.ENGLISH, "Сумма снятия: %.2f больше баланса счета: %.2f, операция не возможна.%n====================%n", amount, getBalance());
        }
    }
}
