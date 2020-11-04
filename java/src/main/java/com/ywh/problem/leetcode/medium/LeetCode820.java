package com.ywh.problem.leetcode.medium;

import com.ywh.model.TrieNode;

import java.util.*;

/**
 * 单词的压缩编码
 * [哈希表] [Trie]
 *
 * @author ywh
 * @since 2020/9/9/009
 */
public class LeetCode820 {

    /**
     * 存储后缀：
     * 1. 如果 x 是 y 的后缀，则 x 不需要考虑（在编码 y 的时候就把 x 编码）；
     * 2. 如果 y 不在任何单词 x 的后缀中出现，则 y 一定是编码字符串的一部分；
     * 3. 目标是保留所有不是其他单词后缀的单词，最后结果即这些单词长度（+1）的综合
     *
     * wi = words[i].length()
     * Time: O(∑wi), Space: O(S*∑wi)        每个单词有 wi 个后缀，对于每个后缀，查询其是否在集合中时需要进行 O(wi) 的哈希值计算。
     *
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        // 把所有单词放入一个集合中，再遍历单词数组
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word: words) {
            // 对于当前单词的每个字符，如果（从该字符开始到最后）已经出现在集合中，则从集合中剔除。
            // 比如集合中已存在 time、me，则遍历到 time 的“me”时，从集合中移除 me，因为 me 是 time 的后缀。
            // 注意“后缀”为真子集，因此字符下标从 1 开始，不包含自身。
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }
        // 最后统计集合中剔除后缀后幸存的单词长度（+1）之和，则为最终结果。
        int ret = 0;
        for (String word: set) {
            ret += word.length() + 1;
        }
        return ret;
    }

    /**
     * 字典树：
     * 目标仍是保留所有不是其他单词后缀的单词。
     * 找到是否不同的单词具有相同的后缀，可以将其反序后插入字典树中（比如“time”和“me”，可以把“emit”和“em”插入字典树）。
     * 字典树的叶子节点表示没有后缀的单词，统计叶子节点代表的单词长度（+1）的和即为答案
     *
     * wi = words[i].length()
     * Time: O(∑(wi^2)), Space: O(∑wi)
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding2(String[] words) {

        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap<>(words.length);

        // 构建前缀树，对于每个单词，把节点与其在单词数组中的下标放入哈希表中。
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // 每个单词从 root 开始
            TrieNode cur = root;
            for (int j = word.length() - 1; j >= 0; j--) {
                int idx = word.charAt(j) - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                    cur.count++;
                }
                cur = cur.children[idx];
            }
            cur.end = true;
            nodes.put(cur, i);
        }
        int ret = 0;
        for (TrieNode node : nodes.keySet()) {
            if (node.count == 0) {
                ret += 1 + words[nodes.get(node)].length();
            }
        }
        return ret;
    }
}
