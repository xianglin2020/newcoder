package huawei;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                    if (!isD){
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
}
