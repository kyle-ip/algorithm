package com.ywh.ds;

import com.ywh.model.AcNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AC 自动机
 *
 * @author ywh
 * @since 2020/11/4/004
 */
public class AcAutomaton {

    private AcNode root;

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
    }

    private void buildFailurePointer() {
        //                -             root 的失败指针指向 null。
        //             /  |  \
        //            a   b   c         沿 a b c 遍历到 c 时，最长可匹配后缀子串为 b c，
        //           /    |   ↑         其失败指针指向最长匹配后缀子串对应的模式串的前缀的最后一个节点。
        //          b +-> c---+         由于可以逐层依次来求解每个节点的失败指针，失败指针的构建是一个按层遍历树的过程。
        //         /  |   |
        //        c --+   d
        //       /        ↑
        //      d --------+
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        // 从根节点开始逐层处理。
        queue.add(root);
        while (!queue.isEmpty()) {
            // 弹出一个节点。
            AcNode p = queue.remove();
            // 遍历并处理该节点的所有非空孩子节点。
            for (int i = 0; i < 26; i++) {
                AcNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                // 如果 p 是 root 节点，则孩子节点的失败指针指向它。
                //     +--> -
                //     |   / \
                //     +--a
                if (p == root) {
                    pc.fail = p;
                } else {
                    // 否则取 p 的失败指针。
                    AcNode q = p.fail;
                    while (q != null) {
                        // 如果失败指针指向的节点不为空，则取相应失败孩子节点。
                        // 处理孩子节点的失败指针。
                        //                -
                        //             /  |  \
                        //            a   b   c
                        //           /    |
                        //          b +-> q
                        //         /  |   |
                        //        p --+   qc
                        //       /        ↑
                        //      pc -------+
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        // 然后沿着失败节点向上处理。
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }
}
