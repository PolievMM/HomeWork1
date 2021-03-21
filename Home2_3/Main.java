import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        System.out.println("First task");


        String[] wordsMas = {"Java", "Mother", "Cat", "Java", "Dog", "Cat", "Java", "Russia", "World", "Programming", "Phone", "Russia"};

        System.out.println("All words of massive: " + Arrays.toString(wordsMas));

        Set<String> uniqWords = new HashSet<>();
        uniqWords.addAll(Arrays.asList(wordsMas));
        System.out.println("Uniq words of massive: " + uniqWords);

        HashMap<String, Integer> mapCollect = new HashMap<>();
        for (String eachWordMas : wordsMas) {
            mapCollect.put(eachWordMas, mapCollect.getOrDefault(eachWordMas, 0) + 1);
        }
        System.out.println("Words' frequency: " + mapCollect);

        System.out.println("Second task");
        PhonePages lookNumber = new PhonePages();

        lookNumber.addNote("David Beckham", "8-917-555-43-18");
        lookNumber.addNote("Zinedine Zidan", "8-900-000-00-00");
        lookNumber.addNote("Roberto Carlos", "8-925-340-10-20");
        lookNumber.addNote("Zinedine Zidan", "8-999-500-16-43");
        lookNumber.addNote("David Beckham", "8-916-787-47-31");
        System.out.println("Phones: " + lookNumber.get("David Beckham"));


    }
}