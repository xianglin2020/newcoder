package huawei;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Harder {
    @Test
    public void hj3() throws IOException {
        TreeSet<Integer> set = new TreeSet<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            var line = reader.readLine();
            if (line == null || line.isBlank()) {
                break;
            }
            int N = Integer.parseInt(line);
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(reader.readLine());
                set.add(n);
            }
            for (final Integer i : set) {
                System.out.println(i);
            }
            set.clear();
        }
    }
}
