package com.ywh.ds.trie;

/**
 * 字典树
 * 利用字符串之间的公共前缀，将重复的前缀合并在一起（通过 endOfWord 标识该节点可作为字符串的结尾）。
 * 适合用于多模式前缀匹配，不适合用于精确查找：
 * 字符串中包含的字符集不能太大。否则存储空间可能就会浪费很多。优化则要付出牺牲查询、插入效率的代价。
 * 要求字符串的前缀重合比较多，否则空间消耗会变大很多。
 * 通过指针串起来的数据块不连续，对缓存并不友好，性能上会打个折扣。
 *
 * @author ywh
 * @since 2020/9/9/009
 */
public class Trie {

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * 插入一个单词（非空字符串、只含小写字母）
     * 缩点优化：对只有一个子节点的节点（而且不是一个串的结束节点），可以将此节点与子节点合并，节省内存开销。
     * 比如           -                   -
     *              /                   /
     *             H                   H
     *            /                   /
     *           E          =>       E
     *          / \                 / \
     *         L   R               L  RE
     *        / \   \             / \
     *       P   L   E          LO   P
     *            \
     *             O
     *
     * Time: O(k)
     *
     * @param word
     */
    public void insert(String word) {
        TrieNode cur = root;

        // 迭代逐层添加 word 中的字符。
        for (int i = 0; i < word.length(); i++) {

            // 把字符映射到 [0, 25] 的数值：int idx = c - 'a';
            // 反映射：char c = (char) (idx + 'a');
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
        }
        cur.end = true;
    }

    /**
     * 搜索一个单词是否存在于前缀树中。
     *
     * Time: O(k)
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode node = searchEndNode(word);
        // 要求可以找到词尾节点，且该节点可作为词尾（完全匹配）。
        // 比如树上只有“hello”，搜索“he”时只能找到正确的前缀，“e”不能作为词尾，因此找不到。
        return node != null && node.end;
    }

    /**
     * 判断前缀树中是否存在一个包含给定前缀的单词。
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return searchEndNode(prefix) != null;
    }

    /**
     *
     * @param str
     * @return
     */
    private TrieNode searchEndNode(String str) {
        TrieNode cur = root;
        for (int i = 0; i < str.length() && cur != null; i++) {
            cur = cur.children[str.charAt(i) - 'a'];
        }
        return cur;
    }
}