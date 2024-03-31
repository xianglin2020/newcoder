package od_c;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ODC {
    @Test
    void 地图寻宝() {
        // https://blog.csdn.net/weixin_48157259/article/details/134838278
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var split = line.split(" ");
            var m = Integer.parseInt(split[0]);
            var n = Integer.parseInt(split[1]);
            var k = Integer.parseInt(split[2]);
            // 构造地图，并计算数位之和并比较
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    sum += sum(i, j, k);
                }
            }

            System.out.println(sum);
        }
    }

    private static int sum(int n, int m, int k) {
        int sum = 0;
        var chars = ("" + n + m).toCharArray();
        for (final char aChar : chars) {
            sum = sum + (aChar - '0');
            if (sum > k) {
                return 0;
            }
        }
        return 1;
    }

    @Test
    void 找朋友() {
        // https://www.nowcoder.com/discuss/353150498389827584?sourceSSR=search
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // 处理数据
            int N = Integer.parseInt(scanner.nextLine());
            var line = scanner.nextLine();
            var split = line.split(" ");
            var hs = new int[N];
            for (int i = 0; i < split.length; i++) {
                hs[i] = Integer.parseInt(split[i]);
            }

            // 找朋友
            var res = new int[N];
            for (int i = 0; i < hs.length; i++) {
                for (int j = i + 1; j < hs.length; j++) {
                    if (hs[j] > hs[i]) {
                        res[i] = j;
                    }
                }
                // 没找到
                res[i] = 0;
            }

            // 输出
            for (final int re : res) {
                System.out.printf("%d ", re);
            }
        }
    }

    @Test
    void 数字游戏() {
        System.out.println(Integer.toBinaryString(1000000));
        System.out.println(Integer.toBinaryString(1000064));
        // https://www.nowcoder.com/issue/tutorial?zhuanlanId=MVBqEM&uuid=c9d00eb39b71495f809e2d6bd6191149
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            var bc = Integer.bitCount(n);
            var m = n + 1;
            while (Integer.bitCount(m) != bc) {
                m++;
            }
            System.out.println(m);
        }
    }

    @Test
    void 停车场车辆统计() {
        // https://www.nowcoder.com/discuss/599714824850857984?sourceSSR=search
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var split = line.split(",");
            var cars = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                cars[i] = "1".equals(split[i]) ? 1 : 0;
            }

            // 计算
            var w = 0;
            var c = 0;
            for (final int car : cars) {
                if (car == 0) {
                    if (w > 0) {
                        c++;
                        w = 0;
                    }
                    continue;
                }
                w++;
                if (w == 3) {
                    c++;
                    w = 0;
                }
            }

            if (w > 0) {
                c++;
            }

            System.out.println(c);
        }
    }

    @Test
    void 灰度图() {
        // https://blog.csdn.net/weixin_43389472/article/details/135984378
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var line1 = scanner.nextLine().split(" ");
            var line2 = scanner.nextLine().split(" ");
            int r = Integer.parseInt(line2[0]);
            int c = Integer.parseInt(line2[1]);

            int row = Integer.parseInt(line1[0]);
            int col = Integer.parseInt(line1[1]);

            int n = col * r + c + 1;


            // 构造数组
            for (int i = 2; i < line1.length; i += 2) {
                var value = Integer.parseInt(line1[i]);
                var m = Integer.parseInt(line1[i + 1]);
                if ((n = n - m) <= 0) {
                    System.out.println(value);
                    break;
                }
            }
        }
    }

    @Test
    void 计数器问题() {
        // https://blog.csdn.net/qq_36079986/article/details/121111874
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            var N = scanner.nextInt();
            var sub = 0;

            for (int i = 0; i < N; ) {
                var cal = cal(i);
                if (cal > i) {
                    sub += (cal - i);
                    i = cal;
                } else {
                    i++;
                }
            }

            System.out.println(N - sub);
        }
    }

    private static int cal(int n) {
        var chars = Integer.toString(n).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '4') {
                chars[i] = '5';

                return Integer.parseInt(new String(chars));
            }
        }
        return n;
    }

    @Test
    void 堆内存申请() {
        // https://blog.csdn.net/2301_76848549/article/details/135680378
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = Integer.parseInt(scanner.nextLine());

            var error = false;
            String line;
            // 构造已用的地址空间
            var address = new int[100];
            while ((line = scanner.nextLine()) != null && !line.isEmpty()) {
                var split = line.split(" ");
                var offset = Integer.parseInt(split[0]);
                var limit = Integer.parseInt(split[1]);

                for (int i = 0; i < limit; i++) {
                    var idx = offset + i;

                    if (idx >= address.length || idx < 0) {
                        error = true;
                        break;
                    }

                    if (address[idx] == 1) {
                        error = true;
                        break;
                    }

                    address[idx] = 1;
                }
                if (error) {
                    break;
                }
            }

            if (error) {
                System.out.println(-1);
                break;
            }

            // 申请内存
            var s = -1;
            var match = false;
            for (int i = 0; i < address.length; i++) {
                if (address[i] == 1) {
                    s = -1;
                } else {
                    if (s == -1) {
                        s = i;
                    }
                    // 够分配内存，输出偏移
                    if ((i - s) + 1 == a) {
                        match = true;
                        break;

                    }
                }
            }

            if (match) {
                System.out.println(s);
            } else {
                System.out.println(-1);
            }

        }
    }

    @Test
    void 分月饼() {
        // https://www.nowcoder.com/issue/tutorial?zhuanlanId=MVBqEM&uuid=4b3bacb1e9204ad8be34ea1ba756bf4d
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var split = line.split(" ");
            var m = Integer.parseInt(split[0]);
            var n = Integer.parseInt(split[1]);

            // 分月饼（递归）
            // 记录月饼的分配方案（满足要求的）
            var ns = new int[m];
            var set = new HashSet<String>();

            solve(n, ns, 0, set);

            // 输出结果
            System.out.println(set.size());
            System.out.println(set);
        }
    }

    private static void solve(int n, int[] ns, int i, Set<String> set) {
        // 记录分配方案
        if (i == ns.length - 1) {

            var last = n;
            for (int i1 = 0; i1 < ns.length - 1; i1++) {
                last -= ns[i1];
            }
            ns[i] = last;

            var ints = Arrays.copyOf(ns, ns.length);

            // 判断分配方案是否合理
            Arrays.sort(ints);
            for (int i1 = 0; i1 < ints.length - 1; i1++) {
                if (ints[i1 + 1] - ints[i1] > 3) {
                    return;
                }
            }

            set.add(Arrays.toString(ints));
            return;
        }

        for (int i1 = 1; i1 < n - ns.length + 1; i1++) {

            // 给第 i 个人分配了 i1 个月饼
            var s = 0;
            for (int i2 = 0; i2 < i; i2++) {
                s += ns[i2];
            }
            if (s + i1 + (ns.length - i) > n) {
                break;
            }

            ns[i] = i1;
            // 给下一个人分配月饼
            solve(n, ns, i + 1, set);
        }
    }

    @Test
    void 身高排序() {
        // https://www.nowcoder.com/discuss/373807865130156032
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var split = line.split(" ");
            var H = Integer.parseInt(split[0]);

            var line1 = scanner.nextLine();
            var result = Arrays.stream(line1.split(" "))
                    .map(Integer::parseInt)
                    .sorted((o1, o2) -> {
                        int abs1 = Math.abs(H - o1);
                        int abs2 = Math.abs(H - o2);
                        if (abs1 == abs2) {
                            return o1 - o2;
                        }
                        return abs1 - abs2;
                    })
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        }
    }

    @Test
    void 众数中位数() {
        // https://zhuanlan.zhihu.com/p/645555285
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            // 数字个数统计
            var map = new HashMap<String, Integer>();
            var split = line.split(" ");
            for (final String s : split) {
                map.compute(s, (s1, integer) -> {
                    if (integer == null) {
                        return 1;
                    }
                    return integer + 1;
                });
            }

            // 求最多个数
            int max = 0;
            for (final var entry : map.entrySet()) {
                max = Math.max(entry.getValue(), max);
            }

            // 取众数
            var list = new ArrayList<Integer>();
            for (final var entry : map.entrySet()) {
                if (max == entry.getValue()) {
                    list.add(Integer.parseInt(entry.getKey()));
                }
            }

            // 排序
            list.sort(Comparator.comparingInt(o -> o));
            // 输出中位数
            var size = list.size();
            if (size % 2 == 0) {
                var mid = size / 2;
                System.out.println((list.get(mid) + list.get(mid - 1)) / 2);
            } else {
                System.out.println(list.get(size / 2));
            }
        }
    }

    @Test
    void 会议室占用时间段() {
        // https://www.nowcoder.com/issue/tutorial?zhuanlanId=MVBqEM&uuid=d0c1ac3e97944ccfb51c1b25dd2a6c25
//        int[][] input = new int[][]{{1, 4}, {2, 5}, {7, 9}, {14, 18}};
        int[][] input = new int[][]{{1, 4}, {4, 5}};

        // 记录已使用的时间
        int[] time = new int[24];
        for (final int[] ints : input) {
            for (int i = ints[0]; i < ints[1]; i++) {
                time[i - 1] = 1;
            }
        }

        // 输出
        var list = new ArrayList<int[]>();
        int s = -1;
        for (int i = 0; i < time.length; i++) {
            if (time[i] == 0) {
                if (s != -1) {
                    list.add(new int[]{s + 1, i + 1});
                }
                s = -1;
            } else {
                if (s == -1) {
                    s = i;
                }
            }
        }

        System.out.print("[");
        var iterator = list.iterator();
        while (true) {
            System.out.print(Arrays.toString(iterator.next()));
            if (!iterator.hasNext()) {
                System.out.print("]");
                break;
            }
            System.out.print(", ");
        }
    }

    @Test
    void 掌握的单词个数() {
        // https://www.nowcoder.com/issue/tutorial?zhuanlanId=MVBqEM&uuid=5ea1494b5dc34e04a7e50113cf369d0d
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var N = Integer.parseInt(scanner.nextLine());
            var strings = new String[N];
            for (int i = 0; i < N; i++) {
                var line = scanner.nextLine();
                strings[i] = line;
            }
            // 计算字符个数
            var line = scanner.nextLine();
            var charMap = new HashMap<Character, Integer>();
            var chars = line.toCharArray();
            for (final char aChar : chars) {
                charMap.put(aChar, charMap.getOrDefault(aChar, 0) + 1);
            }

            // 判断是否学会
            int count = 0;
            for (final var string : strings) {
                var map = new HashMap<>(charMap);
                var match = true;
                var chars1 = string.toCharArray();
                for (final char c : chars1) {
                    var left = map.getOrDefault(c, 0);
                    if (left == 0) {
                        // 判断？
                        var i = map.getOrDefault('?', 0);
                        if (i > 0) {
                            map.put('?', i - 1);
                        } else {
                            match = false;
                            break;
                        }
                    } else {
                        map.put(c, left - 1);
                    }
                }

                if (match) {
                    count++;
                }

            }

            // 输出
            System.out.println(count);
        }
    }

    @Test
    void 字符串变换最小字符串() {
        // https://blog.csdn.net/qq_31076523/article/details/134824369
//        var input = "abcdef";
        var input = "bcdefa";

        var chars = input.toCharArray();
        // 判断如果有序
        var sorted = Arrays.copyOf(chars, chars.length);
        Arrays.sort(sorted);
        if (Arrays.compare(chars, sorted) == 0) {
            System.out.println(input);
            return;
        }

        // 依次交换两个字符
        var min = chars;
        label:
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                var newChars = Arrays.copyOf(chars, chars.length);
                newChars[i] = chars[j];
                newChars[j] = chars[i];

                if (Arrays.compare(sorted, newChars) == 0) {
                    min = newChars;
                    break label;
                }

                if (Arrays.compare(min, newChars) > 0) {
                    min = newChars;
                    break;
                }
            }
        }

        System.out.println(min);
    }

    @Test
    void 最大坐标值() {
        // https://www.nowcoder.com/issue/tutorial?zhuanlanId=MVBqEM&uuid=10d20d3849f247c095608b396008e825
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var n = Integer.parseInt(scanner.nextLine());
            var m = Integer.parseInt(scanner.nextLine());
            var line = scanner.nextLine();
            var split = line.split(" ");

            if (split.length != n) {
                System.out.println(12345);
                break;
            }

            if (m < -100 || m > 100) {
                System.out.println(12345);
                break;
            }

            var xm = 0;
            var max = 0;
            for (final String s : split) {
                var x = Integer.parseInt(s);

                if (x < -100 || x > 100) {
                    max = 12345;
                    break;
                }

                if (x == m) {
                    if (x > 0) {
                        x++;
                    } else {
                        x--;
                    }
                }
                xm += x;
                max = Math.max(max, xm);
            }

            System.out.println(max);
        }
    }

    @Test
    void lengthOfLongestSubstring() {
        // https://leetcode.cn/problems/longest-substring-without-repeating-characters/
//        var s = "abcabcbb";
//        var s = "bbbbb";
//        var s = "pwwkew";
        var s = "aabaab!bb";
        var l = 0;

        var max = 0;
        var set = new HashSet<Character>();
        var chars = s.toCharArray();
        for (final char aChar : chars) {
            if (!set.add(aChar)) {
                max = Math.max(set.size(), max);
                while (aChar != chars[l]) {
                    set.remove(chars[l]);
                    l++;
                }
                l++;
            }
        }
        max = Math.max(set.size(), max);

        System.out.println(max);
    }

    @Test
    void 执行时长() {
        // https://blog.csdn.net/qq_31076523/article/details/134829897
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var n = Integer.parseInt(scanner.nextLine());
            var l = Integer.parseInt(scanner.nextLine());

            var line = scanner.nextLine();
            var tasks = Arrays.stream(line.split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            var left = 0;
            var time = 0;

            var i = 0;
            do {
                if (i < tasks.length) {
                    var task = tasks[i];
                    left += task;
                    i++;
                }

                time++;

                left = Math.max(left - n, 0);

            } while (left > 0 || i < tasks.length);

            System.out.println(time);
        }
    }

    @Test
    void 有效子字符串() {
//        var S = "ace";
//        var L = "abcde";
//        var S = "fgh";
//        var L = "abcde";
        var S = "axc";
        var L = "ahbgdc";

        var l = -1;
        var charsS = S.toCharArray();
        var charsL = L.toCharArray();
        for (final char s : charsS) {
            var match = false;
            for (int j = l + 1; j < charsL.length; j++) {
                if (s == charsL[j]) {
                    match = true;
                    l = j;
                    break;
                }
            }
            if (!match) {
                l = -1;
                break;
            }
        }
        System.out.println(l);

    }

    @Test
    void isSubsequence() {
        // https://leetcode.cn/problems/is-subsequence/
        var s = "axc";
        var t = "ahbgdc";

        var res = true;
        var l = -1;
        var charsS = s.toCharArray();
        var charsT = t.toCharArray();
        for (final char c : charsS) {
            var match = false;
            for (int j = l + 1; j < charsT.length; j++) {
                if (c == charsT[j]) {
                    match = true;
                    l = j;
                    break;
                }
            }
            if (!match) {
                res = false;
                break;
            }
        }
        System.out.println(res);
    }

    @Test
    void 字符串拼接() {
        // https://www.nowcoder.com/issue/tutorial?zhuanlanId=MVBqEM&uuid=551623a224074143a51b3296c58833ca

//        var input = "abc 1";
        var input = "dde 2";
        var split = input.split(" ");
        var N = Integer.parseInt(split[1]);
        var m = split[0];
        if (m.isEmpty() || m.length() >= 30) {
            System.out.println(0);
            return;
        }
        if (N <= 0 || N > 5) {
            System.out.println(0);
            return;
        }

        var M = m.toCharArray();
        var indexes = new int[N];
        Arrays.fill(indexes, -1);
        var set = new HashSet<String>();

        solve(M, indexes, 0, set);

        System.out.println(set.size());
        System.out.println(set);
    }

    private static void solve(char[] M, int[] indexes, int index, Set<String> set) {

        if (index == indexes.length) {
            var chars = new char[indexes.length];
            for (int i = 0; i < index; i++) {
                chars[i] = M[indexes[i]];
            }
            // 判断字符不相邻
            var error = false;
            for (int i = 1; i < index; i++) {
                if (chars[i] == chars[i - 1]) {
                    error = true;
                    break;
                }
            }

            if (!error) {
                set.add(Arrays.toString(chars));
            }

            return;
        }


        for (int i = 0; i < M.length; i++) {
            // 判断已选定的字符不能再选
            var skip = false;
            for (int j = 0; j < index; j++) {
                if (indexes[j] == i) {
                    skip = true;
                    break;
                }
            }

            if (skip) {
                continue;
            }

            // 选定 index 处的字符后，继续处理 index + 1 处的字符
            indexes[index] = i;
            solve(M, indexes, index + 1, set);
        }

    }

    @Test
    void _551623a224074143a51b3296c58833ca() {
        var input = "abc 1";
//        var input = "dde 2";
        var split = input.split(" ");
        var N = Integer.parseInt(split[1]);

        // 记录字符个数
        var M = new int[26];
        var chars = split[0].toCharArray();
        for (final char aChar : chars) {
            M[aChar - 'a']++;
        }
        var pre = -1;
        var temp = new char[N];
        var res = solve(M, temp, pre, N);
        System.out.println(res);
    }

    private static int solve(int[] M, char[] temp, int pre, int todo) {
        if (todo == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            int cl = M[i];
            if (cl == 0) {
                continue;
            }
            char c = (char) ('a' + i);
            if (pre != -1 && temp[pre] == c) {
                continue;
            }

            var cur = pre + 1;
            temp[cur] = c;
            M[i]--;
            res += solve(M, temp, cur, todo - 1);
            M[i]++;
        }
        return res;
    }

    @Test
    void 分披萨() {
        // http://www.chinasem.cn/article/750484
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // 准备数据
            var N = Integer.parseInt(scanner.nextLine());
            var piz = new int[N];
            // 确定吃货第一块选择的披萨
            var max = 0;
            var maxIndex = 0;
            for (int i = 0; i < N; i++) {
                piz[i] = Integer.parseInt(scanner.nextLine());
                if (piz[i] > max) {
                    max = piz[i];
                    maxIndex = i;
                }
            }

            var isCh = false;
            var set = new HashSet<String>();
            var ch = new int[N / 2 + 1];
            Arrays.fill(ch, -1);
            ch[0] = maxIndex;
            var l = maxIndex - 1;
            var r = maxIndex + 1;
            solve(piz, ch, 1, isCh, l, r, set, N - 1);

            // 从所有类型中找最大值
            System.out.println(set);

        }
    }

    private static void solve(int[] piz, int[] ch, int index, boolean isCh, int l, int r, Set<String> set, int left) {
        if (left == 0) {
            var ints = Arrays.copyOf(ch, ch.length);
            set.add(Arrays.toString(ints));
            return;
        }


        if (isCh) {
            ch[index] = l;
            l--;
            if (l < 0) {
                l = piz.length - 1;
            }
            solve(piz, ch, index + 1, false, l, r, set, left - 1);

            l++;
            if (l == piz.length) {
                l = 0;
            }
            ch[index] = r;
            r++;
            if (r == piz.length) {
                r = 0;
            }
            solve(piz, ch, index + 1, false, l, r, set, left - 1);
        } else {
            if (piz[l] > piz[r]) {
                if (l == 0) {
                    l = piz.length - 1;
                } else {
                    l--;
                }
            } else {
                if (r == piz.length - 1) {
                    r = 0;
                } else {
                    r++;
                }
            }
            solve(piz, ch, index, true, l, r, set, left - 1);
        }
    }

    @Test
    void 最大社交距离() {
        var seatNum = 10;
        var seatOrLeave = new int[]{1, 1, 1, 1, -4, 1};
        var seats = new int[seatNum];

        int lastIndex = 0;
        for (int i = 0; i < seatOrLeave.length; i++) {
            if (i == 0) {
                seats[0] = 1;
                continue;
            }
            if (i == 1) {
                seats[seats.length - 1] = 1;
                continue;
            }
            if (seatOrLeave[i] == 1) {
                // 选座、入座
                var index = select(seats);
                seats[index] = 1;
                lastIndex = index;
                continue;
            }
            if (seatOrLeave[i] < 0) {
                // 离开
                seats[-seatOrLeave[i]] = 0;
            }
        }

        System.out.println(lastIndex);
    }

    private static int select(int[] seats) {
        return -1;
    }

    @Test
    void 模拟目录管理() {
        var scanner = new Scanner(System.in);
        var file = new File("/");
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var split = line.split(" ");
            var cmd = split[0];
            switch (cmd) {
                case "mkdir": {
                    new File(file, split[1]).mkdir();
                    break;
                }
                case "cd": {
                    file = new File(file, split[1]);
                    break;
                }
                case "pwd": {
                    System.out.println(file.getAbsolutePath());
                    break;
                }
            }
        }
    }

    @Test
    void 火星文计算() {
        // x#y = 2*x+3*y+4
        // x$y = 3*x+y+2
        // $ 的优先级高于 #
        var input = "7#6$5#12";
        var split = input.split("#");
        var res = Integer.parseInt(split[0]);
        for (int i = 1; i < split.length; i++) {
            var split1 = split[i].split("\\$");
            var res1 = Integer.parseInt(split1[0]);
            if (split1.length > 1) {
                for (int j = 1; j < split1.length; j++) {
                    res1 += cal(res1, Integer.parseInt(split1[j]));
                }
            }
            res += cal1(res, res1);
        }

        System.out.println(res);
    }

    private static int cal1(int x, int y) {
        return 2 * x + 3 * y + 4;
    }


    private static int cal(int x, int y) {
        return 3 * x + y + 2;
    }
}
