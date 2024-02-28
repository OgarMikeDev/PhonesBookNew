import java.util.*;

public class MainThree {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>() {{
           add("89676400942");
           add("89676400942");
           add("89676400942");
        }};

        Long number = Long.parseLong(names.get(0));

        System.out.println(number.getClass().getTypeName());
    }
}
