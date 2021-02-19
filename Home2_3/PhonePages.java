import jdk.internal.org.objectweb.asm.Handle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PhonePages {

    private HashMap <String, Set<String>> numberBook = new HashMap<>();


    public void addNote (String name, String number) {
        Set<String> nums = numberBook.getOrDefault(name, new HashSet<>());
        nums.add(number);
        numberBook.put (name, nums);
    }

    public Set<String> get (String name) {

        return numberBook.get(name);

    }
}
