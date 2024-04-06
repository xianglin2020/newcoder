package top101;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://www.nowcoder.com/exam/oj?page=1&tab=%E7%AE%97%E6%B3%95%E7%AF%87&topicId=295">面试必刷TOP101</a>
 */
public class Main {

    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    @Test
    void bm2() {
//        var head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//
//        int m = 2;
//        int n = 4;

        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        int m = 1;
        int n = 2;

        var listNode = reverseBetween(head, m, n);
        while (listNode != null) {
            System.out.printf("%d -> ", listNode.val);
            listNode = listNode.next;
        }
        System.out.println("NULL");
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @param m    int整型
     * @param n    int整型
     * @return ListNode类
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }

        // 使用 leftNode 记录左边链表的最后一个节点
        ListNode leftNode = null;
        ListNode cur = head;
        for (int i = 1; i < m; i++) {
            leftNode = cur;
            cur = cur.next;
        }

        // 只需要执行 left 次反转操作
        int left = n - m + 1;

        // 三指针反转链表
        ListNode prev = null;
        ListNode next;
        while (cur != null && left > 0) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            left--;
        }

        // 拼接左边链表后反转后的链表
        if (leftNode != null) {
            leftNode.next = prev;
        } else {
            head = prev;
        }

        if (prev != null && cur != null) {
            // 找到反转后的链表的最后一个节点，和右边链表拼接
            ListNode rightNode = prev;
            while (rightNode.next != null) {
                rightNode = rightNode.next;
            }
            rightNode.next = cur;
        }

        return head;
    }

    @Test
    void bm4() {
//        var node1 = new ListNode(1);
//        node1.next = new ListNode(3);
//        node1.next.next = new ListNode(5);
//
//        var node2 = new ListNode(2);
//        node2.next = new ListNode(4);
//        node2.next.next = new ListNode(6);

        var node1 = new ListNode(-1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(4);

        var node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        var listNode = Merge(node1, node2);
        while (listNode != null) {
            System.out.printf("%d -> ", listNode.val);
            listNode = listNode.next;
        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param pHead1 ListNode类
     * @param pHead2 ListNode类
     * @return ListNode类
     */
    public ListNode Merge(ListNode pHead1, ListNode pHead2) {
        // write code here
        if (pHead1 == null) {
            return pHead2;
        }
        if (pHead2 == null) {
            return pHead1;
        }


        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode node = result;

        while (pHead1 != null && pHead2 != null) {
            if (pHead1.val < pHead2.val) {
                node.next = pHead1;
                pHead1 = pHead1.next;
            } else {
                node.next = pHead2;
                pHead2 = pHead2.next;
            }
            node = node.next;
        }
        if (pHead1 == null) {
            node.next = pHead2;
        } else {
            node.next = pHead1;
        }

        return result.next;
    }

    @Test
    void bm10() {
//        ListNode p1 = new ListNode(1);
//        p1.next = new ListNode(2);
//        p1.next.next = new ListNode(3);
//        var next = new ListNode(6);
//        p1.next.next.next = next;
//        p1.next.next.next.next = new ListNode(7);
//
//        ListNode p2 = new ListNode(4);
//        p2.next = new ListNode(5);
//        p2.next.next = next;

        var p1 = new ListNode(1);
        p1.next = new ListNode(2);
        p1.next.next = new ListNode(3);
        p1.next.next.next = new ListNode(4);
        p1.next.next.next.next = new ListNode(5);

        var listNode = FindFirstCommonNode(p1, null);
        while (listNode != null) {
            System.out.printf("%d -> ", listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        return null;
    }

    @Test
    void bm11() {
        ListNode h1 = new ListNode(9);
        h1.next = new ListNode(3);
        h1.next.next = new ListNode(7);

        ListNode h2 = new ListNode(6);
        h2.next = new ListNode(3);
        var listNode = addInList(h1, h2);
        while (listNode != null) {
            System.out.printf("%d -> ", listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode addInList(ListNode head1, ListNode head2) {
        // write code here
        String s1 = getString(head1);
        String s2 = getString(head2);
        if (s1 == null) {
            return head2;
        }
        if (s2 == null) {
            return head1;
        }
        if (s1.length() > s2.length()) {
            return buildResult(s1, s2);
        } else {
            return buildResult(s2, s1);
        }
    }

    private String getString(ListNode head) {
        if (head == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        ListNode ln = head;
        while (ln != null) {
            sb.append(ln.val);
            ln = ln.next;
        }
        return sb.toString();
    }

    private ListNode buildResult(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        boolean carry = false;

        StringBuilder resString = new StringBuilder();
        for (int i = 1; i <= chars1.length; i++) {
            char c1 = chars1[chars1.length - i];
            int c2i = chars2.length - i;
            int sum = c1 - '0';
            if (c2i >= 0) {
                sum = sum + (chars2[c2i] - '0');
            }

            if (carry) {
                sum += 1;
                carry = false;
            }

            if (sum > 9) {
                carry = true;
                sum = sum - 10;
            }
            resString.append(sum);
        }
        if (carry) {
            resString.append(1);
        }

        ListNode res = new ListNode(-1);
        ListNode ln = res;

        for (int i = resString.length() - 1; i >= 0; i--) {
            ln.next = new ListNode(resString.charAt(i) - '0');
            ln = ln.next;
        }

        return res.next;
    }

    @Test
    void bm14() {
        // {2,1,3,5,6,4,7}
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        int[] ints = new int[]{2, 1, 3, 5, 6, 4, 7};
        for (final int anInt : ints) {
            p.next = new ListNode(anInt);
            p = p.next;
        }
        var listNode = oddEvenList(dummy.next);
        while (listNode != null) {
            System.out.printf("%d -> ", listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // write code here
        ListNode odd = new ListNode(1);
        ListNode even = new ListNode(2);
        ListNode lno = odd;
        ListNode lne = even;

        boolean nextOdd = true;
        while (head != null) {
            ListNode t = head;
            if (nextOdd) {
                lno.next = t;
                lno = lno.next;
            } else {
                lne.next = t;
                lne = lne.next;
            }
            nextOdd = !nextOdd;
            head = head.next;
            t.next = null;
        }

        lno.next = even.next;
        return odd.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p = dummy.next;
        while (p.next != null) {

            ListNode p1 = p.next;
            ListNode p2 = p1.next;
            while (p2 != null && p2.val == p1.val) {
                p2 = p2.next;
            }

            p.next = p2;

        }


        return dummy.next;
    }
}
