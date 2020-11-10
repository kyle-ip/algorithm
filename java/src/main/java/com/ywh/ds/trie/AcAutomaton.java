package com.ywh.ds.trie;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AC 自动机
 *
 * @author ywh
 * @since 2020/11/4/004
 */
public class AcAutomaton {

    private final AcNode root;

    public AcAutomaton() {
        this.root = new AcNode();
    }

    public void insert(String word) {
        AcNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new AcNode();
            }
            cur = cur.children[idx];
        }
        cur.end = true;
        cur.length = word.length();
    }

    private void buildFailurePointer() {
        //                              在 AC 自动机中，每条路径都视为一个模式串，每个节点都有一个失配指针。
        //                -             root 的失配指针指向 null。
        //             /  |  \
        //            a   b   c         沿 a b c 遍历到 c 时，最长可匹配后缀子串为 b c，
        //           /    |   ↑         其失配指针指向最长匹配后缀子串对应的模式串（另一条路径 b c d）的前缀（b c）的最后一个节点（c）。
        //          b +-> c---+
        //         /  |   |             可以逐层依次来求解每个节点的失配指针，失配指针的构建是一个按层遍历树的过程。
        //        c --+   d
        //       /        ↑
        //      d --------+
        Queue<AcNode> queue = new LinkedList<>();
        // root 的失配指针指向自己。
        root.fail = null;
        // 从根节点开始逐层处理。
        queue.add(root);
        while (!queue.isEmpty()) {
            // 弹出一个节点，遍历并处理该节点的所有非空孩子节点。
            AcNode p = queue.poll();
            for (int i = 0; i < 26; i++) {
                AcNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                // 如果 p 是 root 节点，则孩子节点的失配指针指向它。
                //     +--> -
                //     |   / \
                //     +- a
                if (p == root) {
                    pc.fail = p;
                } else {
                    // 否则取 p 的失配指针。
                    AcNode q = p.fail;
                    while (q != null) {
                        // 如果失配指针指向的节点不为空，则取相应失配孩子节点，处理孩子节点的失配指针。
                        //                -
                        //             /  |  \
                        //            a   b   c
                        //           /    |
                        //          b +-> c (q)         p 的子节点 pc，p 的失配指针指向 q 。
                        //         /  |   |             pc 对应的字符在 q 的子节点 qc 中也能找到，则 pc 指向 qc。
                        //    (p) c --+   d (qc)        如果 q 的子节点中没有找到，则 q = p->fail，继续向上查找。
                        //       /        ↑             直到 q == root，如果没有找到相同字符的子节点，则 pc 的失配指针指向 root。
                        // (pc) d --------+
                        AcNode qc = q.children[i];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        // 然后沿着失配节点向上处理。
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
            // 以 ["abcd", "bcd", "c"] 词库构建的 AC 自动机如下：
            //         +----> - <----+
            //         |   /  ↑  \   |
            //         +- a   b   c -+
            //           /    |   ↑  |
            //          b +-> c --+  |
            //         /  |   |      |
            //        c --+   d -----+
            //       /        ↑
            //      d --------+
        }
    }

    /**
     * 在 AC 自动机上匹配主串。
     *
     * @param text
     */
    public void match(String text) {
        int n = text.length();
        AcNode p = root;
        // AC 自动机从指针 p == root 开始，假设模式串是 b，主串是 a。
        // 如果 p 指向节点存在等于 b[i] 的子节点 x，则更新 p 指向 x。通过失配指针检测一系列失配指针为结尾的路径是否是模式串。
        // 如果 p 指向节点没有等于 b[i] 的子节点，则让 p = p->fail，继续指向这两步。
        for (int i = 0; i < n; ++i) {
            int idx = text.charAt(i) - 'a';
            while (p.children[idx] == null && p != root) {
                // 失配指针发挥作用的地方。
                p = p.fail;
            }
            p = p.children[idx];
            // 如果没有匹配的，从 root 开始重新匹配。
            if (p == null) {
                p = root;
            }
            AcNode tmp = p;
            while (tmp != root) {
                // 匹配到模式串的结尾。
                if (tmp.end) {
                    int pos = i - tmp.length + 1;
                    System.out.println("匹配起始下标 " + pos + "，长度 " + tmp.length);
//                    return true;
                }
                tmp = tmp.fail;
            }
        }
//        return false;
    }
    public static void match(String text, String[] patterns) {
        AcAutomaton acAutomaton = new AcAutomaton();
        for (String pattern: patterns) {
            acAutomaton.insert(pattern);
        }
        acAutomaton.buildFailurePointer();
        acAutomaton.match(text);
    }

    public static void main(String[] args) {
        String[] patterns = {"at", "art", "oars", "soar"};
        String text = "soarsoarat";
        match(text, patterns);
    }
}
