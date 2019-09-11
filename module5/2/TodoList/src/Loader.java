import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    private static ArrayList <String> todoList = new ArrayList<>();

    private static final String HELP = "Подсказка:" +
            "\n========================================\n" +
            "LIST - вывести список дел\n" +
            "ADD Какое-то дело - добавить новый пункт\n" +
            "ADD 4 Какое-то дело на четвёртом месте - внести новый пункт на указанное место\n" +
            "EDIT 3 Новое название дела - заменить существующий пункт на новый\n" +
            "DELETE 7 - удалить указанный пункт\n" +
            "EXIT - Завершение программы" +
            "\n========================================\n";

    public static void main(String[] args) {

        Pattern patTodoListElement = Pattern.compile("(?<comm>DELETE|LIST|ADD|EDIT)\\s*(?<index>\\d+)?\\s?(?<todo>[a-zA-Z].*)?");

       for (; ;) {
           System.out.println(HELP + "\nВведите команду и текст:");
           String command = new Scanner(System.in).nextLine().trim();

           Matcher matcher = patTodoListElement.matcher(command);

           if (command.startsWith("EXIT")) {
                break;
           }

           else if (command.startsWith("LIST")) {
                printTodoList();
           }

           else if (command.startsWith("ADD")) {
               Integer todoListElementIndex = null;
               String todoElement = "";
               if (matcher.find()) {
                   todoListElementIndex = getIndex(matcher);
                   todoElement = getMessage(matcher);
               }

               addTodoListElement(todoListElementIndex, todoElement);
           }

           else if (command.startsWith("EDIT")) {
               Integer todoListElementIndex = null;
               String todoElement = "";
               if (matcher.find()) {
                   todoListElementIndex = getIndex(matcher);
                   todoElement = getMessage(matcher);
               }
               editTodoListElement(todoListElementIndex, todoElement);
           }

           else if (command.startsWith("DELETE")) {
               Integer todoListElementIndex = null;
               if (matcher.find()) {
                   todoListElementIndex = getIndex(matcher);
               }
               deleteElementTodoList(todoListElementIndex);
           }

           else {
               System.out.println("Команда не распознана.");
           }
       }
    }

//=====================================================================================================================
    private static Integer getIndex (Matcher matcher) {
        Integer todoListElementIndex = null;
        if (matcher.group("index") != null) {
            todoListElementIndex = Integer.parseInt(matcher.group("index"));
        }
        return todoListElementIndex;
    }

    private static String getMessage (Matcher matcher) {
        String todoElement = "";
        if (matcher.group("todo") != null) {
            todoElement = matcher.group("todo").trim();
        }
        return todoElement;
    }

    private static void printTodoList () {
        if (todoList.size() == 0) {
            System.out.println("Список дел пуст.");
        }
        else {
            for (int index = 0; index < todoList.size(); index++) {
                System.out.println(index + " " + todoList.get(index));
            }
        }

    }

    private static void deleteElementTodoList (int todoListElementIndex) {
        if (todoListElementIndex < 0 || todoListElementIndex > todoList.size()-1) {
            System.out.println("указанный номер дела отсутсвует в списке.");
        }
        else {
            todoList.remove(todoListElementIndex);
        }
    }

    private static void addTodoListElement (Integer todoListElementIndex, String todoElement) {
        if (todoListElementIndex == null || (todoListElementIndex < 0 || todoListElementIndex > todoList.size()-1)) {
            System.out.println("Не корректный номер дела, запись будет добавлена в конец списка.");
            todoListElementIndex = todoList.size();
        }
        if (!todoElement.equals("")) {
            todoList.add(todoListElementIndex, todoElement);
        }
        else {
            System.out.println("Не указано содержание дела, записано не будет");
        }
    }

    private static void editTodoListElement (Integer todoListElementIndex, String todoElement) {
        if (todoListElementIndex == null) {
            System.out.println("Не указан номер дела, изменение не возможно.");
        }
        else if (todoListElementIndex < 0 || todoListElementIndex > todoList.size()-1) {
            System.out.println("Указанный номер дела не найден.");
        }
        else {
            todoList.set(todoListElementIndex, todoElement);;
        }
    }
}
