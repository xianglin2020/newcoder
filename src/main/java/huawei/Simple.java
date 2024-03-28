package huawei;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Simple {
    @Test
    public void hj1() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            String[] split = line.split(" ");
            int length = split[split.length - 1].length();
            System.out.println(length);
        }
    }

    @Test
    public void hj2() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            char c = in.nextLine().toCharArray()[0];

            int count = 0;
            char[] chars = line.toCharArray();
            for (final char aChar : chars) {
                if (aChar == c || Character.toUpperCase(c) == Character.toUpperCase(aChar)) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    @Test
    void hj4() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            int length = line.length();
            int batch = length / 8;
            int i = 0;
            for (; i < batch; i++) {
                System.out.println(line.substring(i * 8, (i + 1) * 8));
            }
            int left = length - i * 8;
            if (left > 0) {
                StringBuilder builder = new StringBuilder();
                for (int i1 = 0; i1 < 8 - left; i1++) {
                    builder.append('0');
                }
                System.out.println(line.substring(length - left) + builder);
            }
        }
    }

    @Test
    void hj5() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(Integer.parseInt(line.substring(2), 16));
        }
    }

    @Test
    void hj6() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            int n = Integer.parseInt(line);
            int z = 2;

            while (z <= n) {
                if (n % z == 0) {
                    System.out.print(z + " ");
                    n = n / z;
                } else {
                    // 判断本身是不是质数
                    if (z(n)) {
                        System.out.print(n + " ");
                        break;
                    }
                    z = next_z(z);
                }
            }
        }
    }

    private static int next_z(int z) {
        boolean find;
        do {
            z++;
            find = z(z);
        } while (!find);

        return z;
    }

    private static boolean z(int z) {
        boolean find = true;
        for (int i = 2; i <= z / 2; i++) {
            if (z % i == 0) {
                find = false;
                break;
            }
        }
        return find;
    }

    @Test
    void hj8() throws IOException {
        Map<Integer, Integer> map = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            int n = Integer.parseInt(line);
            for (int i = 0; i < n; i++) {
                String[] split = reader.readLine().split(" ");
                Integer index = Integer.parseInt(split[0]);
                Integer value = map.getOrDefault(index, 0);
                map.put(index, value + Integer.parseInt(split[1]));
            }
            for (final Map.Entry<Integer, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            map.clear();
        }
    }

    @Test
    void hj10() throws IOException {
        Set<Character> set = new HashSet<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            char[] chars = line.toCharArray();
            for (final char aChar : chars) {
                if (aChar <= 127) {
                    set.add(aChar);
                }
            }
            System.out.println(set.size());
            set.clear();
        }
    }

    @Test
    void hj13() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(" ");
            for (int i = split.length - 1; i >= 0; i--) {
                System.out.print(split[i] + " ");
            }
        }
    }

    @Test
    void hj15() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            int i = Integer.parseInt(line);
            System.out.println(Integer.bitCount(i));
        }
    }

    @Test
    void hj21() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            char[] chars = line.toCharArray();
            char[] newChars = new char[chars.length];
            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                char newChar = aChar;

                if ('a' <= aChar && aChar <= 'z') {
                    switch (aChar) {
                        case 'a':
                        case 'b':
                        case 'c':
                            newChar = '2';
                            break;
                        case 'd':
                        case 'e':
                        case 'f':
                            newChar = '3';
                            break;
                        case 'g':
                        case 'h':
                        case 'i':
                            newChar = '4';
                            break;
                        case 'j':
                        case 'k':
                        case 'l':
                            newChar = '5';
                            break;
                        case 'm':
                        case 'n':
                        case 'o':
                            newChar = '6';
                            break;
                        case 'p':
                        case 'q':
                        case 'r':
                        case 's':
                            newChar = '7';
                            break;
                        case 't':
                        case 'u':
                        case 'v':
                            newChar = '8';
                            break;
                        default:
                            newChar = '9';
                    }
                } else if ('A' <= aChar && aChar < 'Z') {
                    newChar = (char) (Character.toLowerCase(aChar) + 1);
                } else if (aChar == 'Z') {
                    newChar = 'a';
                }

                newChars[i] = newChar;
            }
            System.out.println(new String(newChars));
        }
    }

    @Test
    void hj22() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n == 0) {
                break;
            }

            int s = 0;
            while (n >= 3) {
                int y = n % 3;

                n = n / 3;
                s += n;

                n += y;
            }
            if (n == 2) {
                s++;
            }
            System.out.println(s);
        }
    }

    @Test
    void hj23() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int[] ints = new int[26];

            String line = scanner.nextLine();
            char[] chars = line.toCharArray();
            for (final char aChar : chars) {
                ints[aChar - 'a']++;
            }

            int min = 20;
            for (final int anInt : ints) {
                if (anInt == 0) {
                    continue;
                }
                if (anInt < min) {
                    min = anInt;
                }
            }

            for (final char aChar : chars) {
                if (ints[aChar - 'a'] > min) {
                    System.out.printf("%c", aChar);
                }
            }
        }
    }
}
