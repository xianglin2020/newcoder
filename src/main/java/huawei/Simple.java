package huawei;


import org.junit.jupiter.api.Test;

import java.util.Scanner;

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
}
