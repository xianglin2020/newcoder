package huawei;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class Entry {
    @Test
    void hj9() throws IOException {
        Set<Character> set = new LinkedHashSet<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            char[] chars = line.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                set.add(chars[i]);
            }
            for (final Character c : set) {
                System.out.print(c);
            }
        }
    }
}
