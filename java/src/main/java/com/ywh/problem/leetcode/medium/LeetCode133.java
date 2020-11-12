package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 图的深拷贝
 * [图] [DFS] [BFS]
 *
 * @author ywh
 * @since 27/11/2019
 */
public class LeetCode133 {

    /**
     * 存储节点与复制节点的映射关系
     */
    private final Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

    /**
     * 图的遍历：深度优先或广度优先；深度优先一般递归实现（栈），广度优先则使用队列实现，此处使用深度优先实现
     * 递归结束条件实际上有两个，其一是空节点（没有邻接点），其二是存在于哈希表（表示邻接关系已建立，直接添加即可）
     * 假设图共有 E 条边，则时间复杂度取决于 E；除了递归栈还使用了一个容量固定为节点数的辅助哈希表
     *
     * Time: O(E), Space: O(n)
     *
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        // 从一个非空节点开始，把当前访问的节点复制并存储映射关系
        UndirectedGraphNode copy = new UndirectedGraphNode(node.val);
        map.put(node, copy);

        // 遍历当前节点的邻接点列表（先递归、后循环，因此是深度优先 DFS）
        for (UndirectedGraphNode neighbor : node.neighbors) {

            // 如果哈希表中存在这个表的邻接点：表示这条路径已被访问过、映射关系已建立；
            // 取出邻接点的复制节点、添加到复制节点的邻接点列表中
            if (map.containsKey(neighbor)) {
                copy.neighbors.add(map.get(neighbor));
            }
            // 否则：以邻接点递归，添加到复制节点的邻接点列表
            else {
                copy.neighbors.add(cloneGraph(neighbor));
            }
        }

        // 最终返回复制节点即可
        return copy;
    }

    public static class UndirectedGraphNode {

        public int val;

        public List<UndirectedGraphNode> neighbors;

        public UndirectedGraphNode(int x) {
            val = x;
            neighbors = new LinkedList<>();
        }
    }
}
