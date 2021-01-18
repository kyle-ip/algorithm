package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 账户合并
 * [DFS] [并查集]
 *
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
 * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 * 示例 1：
 *      输入：
 *          accounts = [
 *              ["John", "johnsmith@mail.com", "john00@mail.com"],
 *              ["John", "johnnybravo@mail.com"],
 *              ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *              ["Mary", "mary@mail.com"]
 *          ]
 *      输出：
 *          [
 *              ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 *              ["John", "johnnybravo@mail.com"],
 *              ["Mary", "mary@mail.com"]
 *          ]
 *      解释：
 *          第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 *          第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 *          可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 *          ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 * 提示：
 *      accounts的长度将在[1，1000]的范围内。
 *      accounts[i]的长度将在[1，10]的范围内。
 *      accounts[i][j]的长度将在[1，30]的范围内。
 * 
 * @author ywh
 * @since 2021/1/18/018
 */
public class LeetCode721 {

    /**
     * TODO 暂时未理解
     *
     * 两个账户需要合并，当且仅当两个账户至少有一个共同的邮箱地址，实质是判断所有的邮箱地址中有哪些邮箱地址必定属于同一人，可使用并查集实现。
     *
     * Time: O(n*log(n)), Space: O(n*log(n))
     *
     * 时间复杂度：O(nlogn)，其中 nn 是不同邮箱地址的数量。
     * 需要遍历所有邮箱地址，在并查集内进行查找和合并操作，对于两个不同的邮箱地址，如果它们的祖先不同则需要进行合并，需要进行 2 次查找和最多 1 次合并。
     * 一共需要进行 2n 次查找和最多 n 次合并，因此时间复杂度是 O(nlogn)。
     * 这里的并查集使用了路径压缩，但是没有使用按秩合并，最坏情况下的时间复杂度是 O(nlogn)，
     * 平均情况下的时间复杂度依然是 O(nα(n))，其中 α 为阿克曼函数的反函数，α(n) 可以认为是一个很小的常数。
     * 整理出题目要求的返回账户的格式时需要对邮箱地址排序，时间复杂度是 O(nlogn)。
     * 其余操作包括遍历所有邮箱地址，在哈希表中记录相应的信息，时间复杂度是 O(n)，在渐进意义下 O(n) 小于 O(nlogn)。
     * 因此总时间复杂度是 O(nlogn)。
     *
     * 空间复杂度：O(n)，其中 nn 是不同邮箱地址的数量。空间复杂度主要取决于哈希表和并查集，每个哈希表存储的邮箱地址的数量为 n，并查集的大小为 n。
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 记录每个邮箱对应的编号和每个邮箱对应的名称。
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                // 同一个邮箱地址在两个哈希表中都只存储一次：
                if (!emailToIndex.containsKey(email)) {
                    // {"johnsmith@mail.com": 0, "john00@mail.com": 1, ...}
                    emailToIndex.put(email, emailsCount++);

                    // {"johnsmith@mail.com": "John", "mary@mail.com": "Mary", ...}
                    emailToName.put(email, account.get(0));
                }
            }
        }

        // 使用并查集进行合并操作：
        // 同一个账户中的邮箱地址属于同一个人，遍历每个账户、对账户中的邮箱地址进行合并操作。
        // 并查集存储每个邮箱地址对应的编号，合并操作是针对编号进行合并。
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            for (int i = 2; i < account.size(); i++) {
                String firstEmail = account.get(1), curEmail = account.get(i);
                uf.union(emailToIndex.get(firstEmail), emailToIndex.get(curEmail));
            }
        }

        // 完成并查集的合并操作之后即可知道合并后有多少个不同的账户。
        // 遍历所有的邮箱地址，通过并查集得到该邮箱地址属于哪个合并后的账户，整理出每个合并后的账户包含哪些邮箱地址。
        // {1: ["john00@mail.com", "john_newyork@mail.com", ...]}
        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = uf.find(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<>());
            account.add(email);
            indexToEmails.put(index, account);
        }

        // 对于每个合并后的账户，需要整理出题目要求的返回账户的格式：
        // 将邮箱地址排序，账户的名称可以通过在哈希表中查找任意一个邮箱对应的名称得到，将名称和排序后的邮箱地址整理成一个账户列表。对所有合并后的账户整理出账户列表。
        List<List<String>> merged = new ArrayList<>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            List<String> account = new ArrayList<>();
            account.add(emailToName.get(emails.get(0)));
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }

    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            parent[find(index2)] = find(index1);
        }

        public int find(int index) {
            if (parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }
}
