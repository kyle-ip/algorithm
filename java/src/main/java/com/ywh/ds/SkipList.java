package com.ywh.ds;

import com.ywh.model.SkipListNode;

/**
 * 跳表
 * 跳表的插入、删除、查找、迭代输出有序序列等操作时间复杂度都与红黑树一样；
 * 另外还支持高效的区间查找：以 O(log(n)) 的时间复杂度定位区间的起点，然后在原始链表中顺序往后遍历。
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class SkipList {

    private int levelCount = 1;

    /**
     * 带头链表
     */
    private final SkipListNode head = new SkipListNode();

    /**
     * 查找节点
     *
     * 时间复杂度：
     * 已知在单链表中查找时间复杂度为 O(n)，每两个节点抽出一个作为上一级索引的节点，则第 k 级索引节点数是 k-1 级索引节点数的一半，即 n/(2^k)。
     * 一般最高级索引有 2 个节点，则包含原始链表（第 0 层），整个跳表高度为 h == log(n)。
     * 假设查找时每层需要遍历 m 个节点，则查找操作时间复杂度为 O(m*log(n))。
     * 关于 m：假设要查找数据 x，在第 k 级索引中，遍历到 y 节点后，发现 x 大于 y，小于后面的节点 z；
     * 所以通过 y 的 down 指针，从第 k 级索引下降到第 k-1 级索引。在第 k-1 级索引中，y 和 z 之间只有 3 个节点（包含 y 和 z）；
     * 因此在 K-1 级索引中最多只需要遍历 3 个节点，以此类推每一级索引都最多只需要遍历 3 个节点：
     *            1             2
     *      k    [x]    ->     [y]    ->     [z]
     *            ↓             ↓             ↓
     *      k-1  [x] -> [ ] -> [y] -> [ ] -> [z] ->
     *                          3
     * 以上推理可得知 m 为常数 3，因此查找操作时间复杂度为 O(log(n))。
     *
     * 空间复杂度：
     * 使用了额外的多级索引，设原始链表的大小为 n，每 p 个节点取一个作为上一级索引。
     * 则索引的总节点数为以 n/p 为首项，1/p 为公比的等比数列（p 为偶数末项为 2，否则为 1），因此空间复杂度为 O(n)。
     * （在实际开发中链表中存储对象可能很大，而索引节点只需要存储关键值和几个指针，所以当对象比索引节点大很多时，索引占用的额外空间可以忽略）
     *
     * Time: O(log(n)), Space: O(n)
     *
     * @param value
     * @return
     */
    public SkipListNode find(int value) {
        SkipListNode p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    /**
     * 插入节点
     * 已知在单链表中插入时间复杂度为 O(1)，如果需要先定位到待插入的位置，总的时间复杂度为 O(log(n))。
     * 而对于跳表而言插入操作也是同理（不修改索引）。
     *
     * Time: O(log(n))
     *
     * @param value
     */
    public void insert(int value) {
        int level = randomLevel();
        SkipListNode newNode = new SkipListNode();
        newNode.data = value;
        newNode.maxLevel = level;
        SkipListNode[] update = new SkipListNode[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        // record every level largest value which smaller than insert value in update[]
        SkipListNode p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            // use update save node in search path
            update[i] = p;
        }

        // in search path node next node become new node forwards(next)
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        // update node height
        if (levelCount < level) {
            levelCount = level;
        }
    }

    /**
     * 删除节点
     *
     * 如果待删除的节点在索引中也存在，还需要把索引中的节点一并删除。
     * 单链表中的删除操作需要拿到要删除节点的前驱节点，通过指针操作完成删除。
     * 所以在查找待删除节点时，一定要获取前驱节点（可使用双向链表优化）。
     *
     * @param value
     */
    public void delete(int value) {
        SkipListNode[] update = new SkipListNode[levelCount];
        SkipListNode p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount>1&&head.forwards[levelCount]==null){
            levelCount--;
        }

    }

    // 理论来讲，
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...

    /**
     * 动态更新：如果插入时不修改索引，则当两个索引节点之间数据非常多时，会退化成单链表。
     * 这里引入随机函数，用于决定新插入的节点插入到哪级索引中。
     *
     * 因为每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
     * 一级索引中元素个数应该占原始数据的 50%，二级索引 25%，三级索引 12.5%，一直到最顶层。
     * 本方法随机生成 1 ~ MAX_LEVEL（50% 概率返回 1，25% 的概率返回 2...）
     *
     * @return
     */
    private int randomLevel() {
        int level = 1;
        while (Math.random() < SkipListNode.SKIPLIST_P && level < SkipListNode.MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    public void printAll() {
        SkipListNode p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }
}
