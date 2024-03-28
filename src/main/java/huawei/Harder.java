package huawei;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int A, B, C, D, E, O, S;
        A = B = C = D = E = O = S = 0;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            // 忽略计数
            if (line.startsWith("0.") || line.startsWith("127.")) {
                continue;
            }
            String[] split = line.split("~");
            String ip = split[0];
            String mask = split[1];
            // 判断是否合法 IP
            if (notIP(ip)) {
                O++;
                continue;
            }
            // 判断子网掩码是否合法
            if (!checkMask(mask)) {
                O++;
                continue;
            }
            // IP 分类
            String[] split1 = ip.split("\\.");
            int seg1 = Integer.parseInt(split1[0]);
            if (seg1 >= 1 && seg1 <= 126) {
                A++;
            } else if (seg1 >= 128 && seg1 <= 191) {
                B++;
            } else if (seg1 >= 192 && seg1 <= 223) {
                C++;
            } else if (seg1 >= 224 && seg1 <= 239) {
                D++;
            } else if (seg1 >= 240 && seg1 <= 255) {
                E++;
            } else {
                O++;
            }

            // 判断内网IP
            int seg2 = Integer.parseInt(split1[1]);
            if (seg1 == 10) {
                S++;
            } else if (seg1 == 172 && (seg2 >= 16 && seg2 <= 31)) {
                S++;
            } else if (seg1 == 192 && seg2 == 168) {
                S++;
            }
        }
        System.out.printf("%d %d %d %d %d %d %d\n", A, B, C, D, E, O, S);
    }

    private static boolean checkMask(String mask) {
        if ("255.255.255.255".equals(mask) || "0.0.0.0".equals(mask)) {
            return false;
        }
        if (notIP(mask)) {
            return false;
        }
        String[] split = mask.split("\\.");
        boolean one = true;
        for (final String seg : split) {
            // Integer.toBinaryString 并不会有 8 位，要补齐
            String binaryString = Integer.toBinaryString(Integer.parseInt(seg));
            char[] chars = new char[]{'0', '0', '0', '0', '0', '0', '0', '0'};
            binaryString.getChars(0, binaryString.length(), chars, 8 - binaryString.length());
            for (final char aChar : chars) {
                if (one) {
                    if (aChar == '0') {
                        one = false;
                    }
                } else {
                    if (aChar == '1') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean notIP(String ip) {
        String[] split = ip.split("\\.");
        if (split.length != 4) {
            return true;
        }
        for (final String seg : split) {
            if (seg.isEmpty()) {
                return true;
            }
            int d = Integer.parseInt(seg);
            if (d < 0 || d > 255) {
                return true;
            }
        }
        return false;
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

    @Test
    void hj25() {
        class Index {
            int index;
            String i;
        }

        class Res {
            int r;
            final List<Index> list = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String SI = scanner.nextLine();
            String SR = scanner.nextLine();

            String[] splitI = SI.split(" ");
            String[] splitR = SR.split(" ");
            int[] intsR = new int[splitR.length - 1];
            for (int i = 1; i < splitR.length; i++) {
                intsR[i - 1] = Integer.parseInt(splitR[i]);
            }
            // 去重排序
            intsR = IntStream.of(intsR).distinct().sorted().toArray();

            int count = 0;
            List<Res> resList = new ArrayList<>();
            for (final int r : intsR) {
                Res res = new Res();
                res.r = r;
                for (int i1 = 1; i1 < splitI.length; i1++) {
                    if (splitI[i1].contains(String.valueOf(r))) {
                        Index index = new Index();
                        index.index = i1 - 1;
                        index.i = splitI[i1];
                        res.list.add(index);
                    }
                }
                if (!res.list.isEmpty()) {
                    resList.add(res);
                    count += 2;
                    count += res.list.size() * 2;
                }
            }

            // 输出结果
            System.out.printf("%d ", count);
            for (final Res res : resList) {
                System.out.printf("%d %d ", res.r, res.list.size());
                for (final Index index : res.list) {
                    System.out.printf("%d %s ", index.index, index.i);
                }
            }
        }
    }
}
