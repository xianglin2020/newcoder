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

    @Test
    void hj18() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int A, B, C, D, E, O, S;
        A = B = C = D = E = O = S = 0;
        while ((line = reader.readLine()) != null) {
            A = B = C = D = E = O = S = 0;


            var split = line.split("~");
            var ip = split[0];
            var mask = split[1];

            if (ip.startsWith("0.") || ip.startsWith("127.")) {
                continue;
            }
            if (mask.startsWith("0") || mask.endsWith("255")) {
                O++;
                continue;
            } else {
                var split1 = mask.split("\\.");
                boolean one = true;
                boolean err = false;
                for (final String s : split1) {
                    String binaryString = Integer.toBinaryString(Integer.parseInt(s));
                    for (final char c : binaryString.toCharArray()) {
                        if (one) {
                            if (c == '0') {
                                one = false;
                            }
                        } else {
                            if (c == '1') {
                                err = true;
                                break;
                            }
                        }
                    }
                    if (err) {
                        break;
                    }
                }
                if (err) {
                    O++;
                    continue;
                }
            }

            // IP 分类
            StringBuilder IP = new StringBuilder();
            String[] split1 = ip.split("\\.");
            for (String s : split1) {
                int len = s.length();
                if (len == 1) {
                    s = "00" + s;
                } else if (len == 2) {
                    s = "0" + s;
                }
                IP.append(s);
            }
            ip = IP.toString();
            if (ip.compareTo("001000000000") >= 0 && ip.compareTo("126255255255") <= 0) {
                A++;
            } else if (ip.compareTo("128000000000") >= 0 && ip.compareTo("191255255255") <= 0) {
                B++;
            } else if (ip.compareTo("192000000000") >= 0 && ip.compareTo("223255255255") <= 0) {
                C++;
            } else if (ip.compareTo("224000000000") >= 0 && ip.compareTo("239255255255") <= 0) {
                D++;
            } else if (ip.compareTo("240000000000") >= 0 && ip.compareTo("255255255255") <= 0) {
                E++;
            } else {
                O++;
            }

            if (ip.compareTo("010000000000") >= 0 && ip.compareTo("010255255255") <= 0
                || ip.compareTo("172016000000000") >= 0 && ip.compareTo("172031255255") <= 0
                || ip.compareTo("1921680000000") >= 0 && ip.compareTo("192168255255") <= 0) {
                S++;
            }
        }
        System.out.printf("%d %d %d %d %d %d %d\n", A, B, C, D, E, O, S);
    }
}
