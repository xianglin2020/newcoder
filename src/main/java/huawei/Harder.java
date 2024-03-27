package huawei;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
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

    @Test
    void hj18() throws IOException {

    }

    @Test
    void hj19() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Map<String, Integer> map = new LinkedHashMap<>();
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] split = line.split(" ");
            int index = split[0].lastIndexOf('\\');
            String fName = split[0].substring(index + 1);
            if (fName.length() > 16) {
                fName = fName.substring(fName.length() - 16);
            }
            String key = fName + " " + split[1];
            map.merge(key, 1, Integer::sum);
        }
        int i = 0;
        int size = map.size();

        for (final Map.Entry<String, Integer> entry : map.entrySet()) {
            if (i + 8 < size) {
                i++;
                continue;
            }
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
