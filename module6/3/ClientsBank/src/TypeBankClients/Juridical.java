package TypeBankClients;

import java.util.Locale;

public class Juridical extends Clients {

    public Juridical(int clientAccountNumber, double clientAccountBalance) {
        super(clientAccountNumber, clientAccountBalance);
    }

    @Override
    public String getType() {
        return "Юр. лицо";
    }

    @Override
    public void fillBalance(double amount) {
        System.out.printf(Locale.ENGLISH,"Баланс на начало операции: %.2f%n", getBalance());
        clientAccountBalance += amount;
        System.out.printf(Locale.ENGLISH, "Сумма зачисления: %.2f, остаток счета: %.2f, операция выполнена успешно.%n====================%n", amount, getBalance());
    }

    @Override
    public boolean withdrawalBalance(double amount) {
        boolean isMonyEnough;
        double commision = amount / 100;
        if (amount + commision <= clientAccountBalance) {
            isMonyEnough = true;
            System.out.printf(Locale.ENGLISH,"Баланс на начало операции: %.2f%n", getBalance());
            clientAccountBalance -= (amount + commision);
            System.out.printf(Locale.ENGLISH, "Сумма снятия: %.2f, комиссия за снятие: %.2f, остаток счета: %.2f, операция выполнена успешно.%n====================%n", amount, commision, getBalance());
        }
        else {
            isMonyEnough = false;
            System.out.printf(Locale.ENGLISH, "Сумма снятия: %.2f больше баланса счета: %.2f, операция не возможна.%n====================%n", amount, getBalance());
        }
        return isMonyEnough;
    }
}
