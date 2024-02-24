import java.util.*;

public class MainProba {
    public static void main(String[] args) {
        //List

        String line = "";
        String command = "LIST";

        String regexForName = "[a-zA-Zа-я-А-Я]+";
        String regexForPhoneNumber = "[0-9]+";

        String nameLine = "";
        Long numberLine = 0l;

        Map<String, List<Long>> phonesBook = new TreeMap<>();

        while (!line.matches(command)) {

            //Input line
            System.out.println("Введите номер, имя или команду:");
            line = new Scanner(System.in).nextLine();

            //Check command "LIST"
            if (line.matches(command)) {
                for (Map.Entry<String, List<Long>> entry : phonesBook.entrySet()) {
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            }

            //Check name
            else if (line.matches(regexForName)) {
                nameLine = line;

                //Name is not in the list
                if (!phonesBook.containsKey(line)) {
                    System.out.println(
                            "Такого имени в телефонной книге нет.\n" +
                                    "Введите номер телефона для абонента \"" + line + "\":");
                    line = new Scanner(System.in).nextLine();
                    if (line.matches(regexForPhoneNumber)) {
                        numberLine = Long.parseLong(line);
                        Long finalNumberLine = numberLine;
                        List<Long> numbers = new ArrayList<>() {{
                            add(finalNumberLine);
                        }};
                        phonesBook.put(nameLine, numbers);
                        System.out.println("Контакт сохранен!\n");
                    }
                }

                //Name is in the list
                else {
                    for (Map.Entry<String, List<Long>> entry : phonesBook.entrySet()) {
                        if (entry.getKey().equals(nameLine)) {
                            System.out.println("Данное имя уже есть в телефонной книге.\n" +
                                    "Номер, привязанный к нему \"" + entry.getValue() + "\"\n");
                        }
                    }
                }
            }

            //Check phone number
            else if (line.matches(regexForPhoneNumber)) {
                numberLine = Long.parseLong(line);
                Long finalNumberLine = numberLine;

                //Number is not in the list
                if (!phonesBook.containsValue(numberLine)) {
                    System.out.println(
                            "Такого номера нет в телефонной книге.\n" +
                                    "Введите имя абонента для номера \"" + numberLine + "\"");
                    line = new Scanner(System.in).nextLine();

                    //Input name for number
                    if (line.matches(regexForName)) {
                        nameLine = line;

                        if (phonesBook.containsKey(nameLine)) {
                            for (Map.Entry<String, List<Long>> entry : phonesBook.entrySet()) {
                                if (entry.getKey().equals(nameLine)) {
                                    System.out.println("Вы хотите ещё добавить номер телефона \"" + numberLine + "\"\n" +
                                            "к имени \"" + nameLine + "\"");
                                    entry.getValue().add(numberLine);
                                    System.out.println("Контакт сохранен!\n");
                                }
                            }
                        }

                        else {
                            List<Long> numbers = new ArrayList<>() {{
                                add(finalNumberLine);
                            }};

                            phonesBook.put(nameLine, numbers);
                            System.out.println("Контакт сохранен!\n");
                        }
                    }
                }

                //Number is in the list
                else {
                    for (Map.Entry<String, List<Long>> entry : phonesBook.entrySet()) {
                        if (entry.getValue().equals(numberLine)) {
                            System.out.println("Данный номер уже есть в телефонной книге.\n" +
                                    "Имя, привязанное к нему \"" + entry.getKey() + "\"\n");
                        }
                    }
                }
            }
        }
    }
}
