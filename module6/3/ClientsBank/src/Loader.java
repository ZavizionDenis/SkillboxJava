import TypeBankClients.Clients;
import TypeBankClients.Individual;
import TypeBankClients.Juridical;
import TypeBankClients.Physical;

import java.util.Locale;
import java.util.Random;

public class Loader
{
    private static final long TIME_DELAY = 1000;
    private static final int ACCOUNT_COUNT = 1000;
    private static Clients [] accounts = new Clients[ACCOUNT_COUNT];
    private static final String [] OPERATIONS = {"GET_BALANCE", "FILL_BALANCE", "WITHDRAW_BALANCE", "TRANSFER_FUNDS"};

    public static void main(String[] args) {
        generateAccountsPool();

        for (; ;) {
            try {
                Thread.currentThread().sleep(TIME_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int nextClientIndex = new Random().nextInt(accounts.length);
            Clients nextClient = accounts [nextClientIndex];

            int nextOperation = new Random().nextInt(OPERATIONS.length);

            if (OPERATIONS[nextOperation].equals("GET_BALANCE")) {
                System.out.printf("\nОПЕРАЦИЯ ЗАПРОСА БАЛАНСА:\n====================\nТип клиента: %s%nНомер клиента: %d%n", nextClient.getType(), nextClient.getClientNumber());
                System.out.printf(Locale.ENGLISH,"Баланс счета: %.2f%n====================%n", nextClient.getBalance());
            }

            else if (OPERATIONS[nextOperation].equals("FILL_BALANCE")) {
                System.out.printf("\nОПЕРАЦИЯ ЗАЧИСЛЕНИЯ СРЕДСТВ:\n====================\nТип клиента: %s%nНомер клиента: %d%n", nextClient.getType(), nextClient.getClientNumber());
                nextClient.fillBalance(generateAmount());
            }

            else if (OPERATIONS[nextOperation].equals("WITHDRAW_BALANCE")) {
                System.out.printf("\nОПЕРАЦИЯ СНЯТИЯ СРЕДСТВ:\n====================\nТип клиента: %s%nНомер клиента: %d%n", nextClient.getType(), nextClient.getClientNumber());
                nextClient.withdrawalBalance(generateAmount());
            }

            else if (OPERATIONS[nextOperation].equals("TRANSFER_FUNDS")) {
                Clients secondNextClient = accounts [new Random().nextInt(accounts.length)];
                System.out.printf("\nОПЕРАЦИЯ ПЕРЕВОДА СРЕДСТВ:\n" +
                                  "====================\n" +
                                  "Тип клиента отправителя: %s%n" +
                                  "Номер клиента отправителя: %d%n" +
                                  "Тип клиента получателя: %s%n" +
                                  "Номер клиента получателя: %d%n",
                                  nextClient.getType(), nextClient.getClientNumber(), secondNextClient.getType(), secondNextClient.getClientNumber());

                nextClient.transferFunds(secondNextClient, generateAmount());
            }
        }
    }
//=====================================================================================================================
    private static void generateAccountsPool () {
        for (int i = 0; i < accounts.length; i++) {
            int typeAccount = new Random().nextInt(3);
            if (typeAccount == 0) {
                accounts[i] = new Physical(generateAccountNumber(), generateAccountBalance());
            }
            else if (typeAccount == 1) {
                accounts[i] = new Juridical(generateAccountNumber(), generateAccountBalance());
            }
            else if (typeAccount == 2) {
                accounts[i] = new Individual(generateAccountNumber(), generateAccountBalance());
            }
        }
    }

    private static int generateAccountNumber () {
        return (int) Math.round(1_000_000 + 1_000_000 * Math.random());
    }

    private static double generateAccountBalance () {
        return 10_000 + 100_000 * Math.random();
    }

    private static double generateAmount () {
        return 100 + 10_000 * Math.random();
    }
}
