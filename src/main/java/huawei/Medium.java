package huawei;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Medium {
    @Test
    void hj16() {

    }

    @Test
    void hj17() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int x, y;
        while ((line = reader.readLine()) != null) {
            x = y = 0;
            String[] split = line.split(";");
            for (final String string : split) {
                int length = string.length();
                if (length == 2 || length == 3) {
                    boolean isD = true;
                    char[] chars = string.toCharArray();
                    for (int i = chars.length - 1; i >= 1; i--) {
                        if (chars[i] < '0' || chars[i] > '9') {
                            isD = false;
                            break;
                        }
                    }
                    if (!isD) {
                        continue;
                    }

                    char w = string.charAt(0);
                    int d = Integer.parseInt(string.substring(1));
                    switch (w) {
                        case 'A': {
                            x -= d;
                            break;
                        }
                        case 'D': {
                            x += d;
                            break;
                        }
                        case 'S': {
                            y -= d;
                            break;
                        }
                        case 'W': {
                            y += d;
                            break;
                        }
                    }


                }
            }
            System.out.println(x + "," + y);
        }
    }

    @Test
    void hj20() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            if (checkPwd(line)) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
    }

    private static boolean checkPwd(String line) {
        // 长度
        int length = line.length();
        if (length <= 8) {
            return false;
        }
        // 种类
        int lu = 0;
        int ll = 0;
        int d = 0;
        int o = 0;
        char[] chars = line.toCharArray();
        for (final char aChar : chars) {
            if (Character.isDigit(aChar)) {
                if (d == 0) d++;
            } else if (Character.isUpperCase(aChar)) {
                if (lu == 0) lu++;
            } else if (Character.isLowerCase(aChar)) {
                if (ll == 0) ll++;
            } else {
                if (o == 0) o++;
            }
        }
        if (lu + ll + d + o < 3) {
            return false;
        }

        // 字串
        // len 代表字串长度
        Set<String> set = new HashSet<>();
        for (int len = 3; len <= length / 2; len++) {
            for (int i = 0; i < length - len; i++) {
                String s = line.substring(i, i + len);
                if (!set.add(s)) {
                    return false;
                }
            }
            set.clear();
        }
        return true;
    }

    @Test
    void hj26() {
        class KV {
            final int index;
            final char aChar;

            KV(int index, char aChar) {
                this.index = index;
                this.aChar = aChar;
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // 把特殊字符记录下来
            List<KV> list = new ArrayList<>();

            int length = line.length();
            List<Character> characters = new ArrayList<>(length);
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                if ('A' <= aChar && aChar <= 'Z' || 'a' <= aChar && aChar <= 'z') {
                    characters.add(aChar);
                } else {
                    list.add(new KV(i, aChar));
                }
            }
            // 字符串排序
            characters.sort(Comparator.comparingInt(Character::toLowerCase));
            // 输出字符串
            for (final KV kv : list) {
                characters.add(kv.index, kv.aChar);
            }
            for (final Character character : characters) {
                System.out.printf("%c", character);
            }
        }
    }

    @Test
    void hj27() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(" ");
            int index = Integer.parseInt(split[split.length - 1]);
            String x = split[split.length - 2];

            char[] xChars = x.toCharArray();
            Arrays.sort(xChars);
            // 记录找到的兄弟单词
            List<String> list = new ArrayList<>();
            for (int i = 1; i < split.length - 2; i++) {
                String xx = split[i];
                if (x.length() != xx.length() || x.equals(xx)) {
                    continue;
                }

                char[] xxChars = xx.toCharArray();
                Arrays.sort(xxChars);
                if (Arrays.equals(xChars, xxChars)) {
                    list.add(xx);
                }
            }

            // 输出
            System.out.println(list.size());
            if (list.size() >= index) {
                list.sort(Comparator.naturalOrder());
                System.out.println(list.get(index - 1));
            }
        }
    }

    @Test
    void hj29() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String src = scanner.nextLine();
            String dst = scanner.nextLine();

            char[] srcChars = src.toCharArray();
            for (int i = 0; i < srcChars.length; i++) {
                srcChars[i] = dealEncrypt(srcChars[i]);
            }

            char[] dstChars = dst.toCharArray();
            for (int i = 0; i < dstChars.length; i++) {
                dstChars[i] = dealDecrypt(dstChars[i]);
            }

            System.out.println(new String(srcChars));
            System.out.println(new String(dstChars));
        }
    }

    private static char dealEncrypt(char srcChar) {
        if ('A' <= srcChar && srcChar < 'Z') {
            return (char) (srcChar + 33);
        } else if ('a' <= srcChar && srcChar < 'z') {
            return (char) (srcChar - 31);
        } else if ('Z' == srcChar) {
            return 'a';
        } else if ('z' == srcChar) {
            return 'A';
        } else if ('0' <= srcChar && srcChar < '9') {
            return (char) (srcChar + 1);
        } else if (srcChar == '9') {
            return '0';
        }
        return 0;
    }

    private static char dealDecrypt(char srcChar) {
        if ('A' < srcChar && srcChar <= 'Z') {
            return (char) (srcChar + 31);
        } else if ('a' < srcChar && srcChar <= 'z') {
            return (char) (srcChar - 33);
        } else if ('A' == srcChar) {
            return 'z';
        } else if ('a' == srcChar) {
            return 'Z';
        } else if ('0' < srcChar && srcChar <= '9') {
            return (char) (srcChar - 1);
        } else if (srcChar == '0') {
            return '9';
        }
        return 0;
    }
}
