import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private static final String VALIDATOR = "^(?<name>.+)\\s(?<surename>.+)\\s(?<email>.+)\\s(?<phone>.+)$";
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        Matcher matcher = Pattern.compile(VALIDATOR).matcher(data);
        validation(matcher);

        String[] components = data.split("\\s+");
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        if (storage.size() > 0) {
            storage.values().forEach(System.out::println);
        }
        else {
            throw new NullPointerException("List is empty.");
        }
    }

    public void removeCustomer(String name)
    {
        if (storage.containsKey(name)) {
            storage.remove(name);
        }
        else {
            throw new NullPointerException("Record not found.");
        }
    }

    public int getCount()
    {
        return storage.size();
    }

    private void validation (Matcher matcher) {
        final String PHONE_VALIDATOR = "\\+[\\d]{11}";
        final String EMAIL_VALIDATOR = "[a-zA-Z0-9]{1,}" + "((\\.|_|-){0,1}[a-zA-Z0-9]{1,})*" + "@" +
                "[a-zA-Z0-9]{1,}" + "((\\.|_|-){0,1}[a-zA-Z0-9]{1,})*" + "\\.[a-zA-Z]{2,}";
        final String NAME_VALIDATOR = "[а-яА-Я]+";

        if (matcher.find()) {

            if (!matcher.group("phone").matches(PHONE_VALIDATOR)) {
                throw new IllegalArgumentException("PhoneNumber illegal format.");
            }

            if (!matcher.group("email").matches(EMAIL_VALIDATOR)) {
                throw new IllegalArgumentException("Email illegal format.");
            }

            if (!matcher.group("name").matches(NAME_VALIDATOR)) {
                throw new IllegalArgumentException("Name illegal format.");
            }

            if (!matcher.group("surename").matches(NAME_VALIDATOR)) {
                throw new IllegalArgumentException("Surename illegal format.");
            }
        }
        else {
            throw new IllegalArgumentException("Wrong string format");
        }
    }
}