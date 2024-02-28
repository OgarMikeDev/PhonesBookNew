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
        List<String> listPhoneNumbers = new ArrayList<>();

        while (!line.matches(command)) {

            //Input line
            System.out.println("Введите номер, имя или команду:");
            line = new Scanner(System.in).nextLine();

            //Check command "LIST"
            if (line.matches(command)) {
                for (Map.Entry<String, List<Long>> entry : phonesBook.entrySet()) {
                    System.out.print(entry.getKey() + " - ");
                    for (Long number : entry.getValue()) {
                        if (entry.getValue().get(0) == number) {
                            System.out.print(number);
                        } else {
                            System.out.print(", ".concat(String.valueOf(number)));
                        }
                    }
                    System.out.println();
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
                        listPhoneNumbers.add(line);
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
                                    "Номер[а], привязанный к нему \"" + entry.getValue() + "\"\n");
                        }
                    }
                }
            }

            //Check phone number
            else if (line.matches(regexForPhoneNumber)) {
                numberLine = Long.parseLong(line);
                Long finalNumberLine = numberLine;

                //Number is not in the list
                if (!(listPhoneNumbers.contains(line))) {
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
                                    listPhoneNumbers.add(String.valueOf(numberLine));
                                    entry.getValue().add(numberLine);
                                    System.out.println("Контакт сохранен!\n");
                                }
                            }
                        }

                        else {
                            List<Long> numbers = new ArrayList<>() {{
                                add(finalNumberLine);
                            }};

                            listPhoneNumbers.add(line);
                            phonesBook.put(nameLine, numbers);
                            System.out.println("Контакт сохранен!\n");
                        }
                    }
                }

                //Number is in the list
                else {
                    for (Map.Entry<String, List<Long>> entry : phonesBook.entrySet()) {
                        if (entry.getValue().contains(numberLine)) {
                            System.out.println("Данный номер уже есть в телефонной книге.\n" +
                                    "Имя, привязанное к нему \"" + entry.getKey() + "\"\n");
                        }
                    }
                }
            }
        }
    }
}
