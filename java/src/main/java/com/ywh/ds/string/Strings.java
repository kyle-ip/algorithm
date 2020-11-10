package com.ywh.ds.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串搜索：单模式串匹配算法。
 *
 * @author ywh
 * @since 2020/11/4/004
 */
public class Strings {

    /**
     * BF 算法
     * Time (Worst): O(n*m), Space: O(1)
     *
     * @return
     */
    public int bf(String s, String p) {
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                // 匹配失败，i 退回 j 个，再向后移动一位。
                // s: d a b d e f        d a b d e f
                //          ↑       =>       ↑
                // p:   a b c                a b c
                //          ↑                ↑
                i = i - j + 1;
                j = 0;
            }
        }
        // 如果匹配则返回 s 中 p 首字母第一次出现的位置，否则返回 -1。
        return j == p.length() ? i - j + 1 : -1;
    }

    /**
     * RK 算法：
     * BF 算法的优化，假设模式串长度为 m，主串长度为 n，在主串中有 n-m+1 个长度为 m 的子串，使用哈希算法暴力对比这些子串。
     * 由于只需要扫描一遍主串就能计算出所有子串的哈希值，且模式串哈希值与每个子串哈希值之间的比较的时间复杂度、共需比较 n-m+1 个子串，因此整体时间复杂度 O(n)。
     *
     * Time: O(n), Space: O(1)
     * @param s
     * @param p
     * @return
     */
    public int rk(String s, String p) {
        int sLen = s.length(), pLen = p.length(), sHash = -1, pHash = hash(p, 0, p.length());
        for (int i = 0; i < sLen - pLen + 1; i++) {
            // 首次计算 s 子串的 hash。
            if (sHash == -1) {
                sHash = hash(s, i, i + pLen);
            }
            // 否则由上一步的子串 hash 计算当前子串 hash。
            else {
                // a b c d e
                //                [  head  ]
                // hash(a, c) == a * 26 * 26 + b * 26 + c * 1;
                // hash(b, d) ==               b * 26 * 26 + c * 26 + d * 1
                //                                                   [ tail ]
                // hash(a, c) == (hash(a, c) - head) * 26 + tail
                int head = (s.charAt(i - 1) - 'a') * POW26[pLen - 1];
                int tail = s.charAt(i + pLen - 1) - 'a';
                sHash = (sHash - head) * 26 + tail;
            }
            if (sHash == pHash) {
                return i;
            }
        }
        return -1;
    }

    private static final int[] POW26 = {1, 26, 676, 17576, 456976, 11881376};

    /**
     * 哈希函数：
     * 假设要匹配的字符串的字符集中只包含 K 个字符，用一个 K 进制数来表示一个子串，再转化成十进制数作为子串的哈希值。
     *      比如要处理的字符串只包含 a~z 就用二十六进制来表示一个字符串，再转换为十进制：
     *      hash("cba") == 'c' * 26 * 26 + 'b' * 26 + 'a' * 1 == 2 * 26 * 26 + 1 * 26 + 0 * 1 == 1378
     *      这种哈希算法使得在主串中相邻两个子串的哈希值的计算公式有关系（计算公式有交集，因此可以使用 s[i-1] 快速算出 s[i]）。
     *      其中 26 的乘方事先存储再 POW26，后续通过查表的方式优化。
     * @param s
     * @param start
     * @param end
     * @return
     */
    private int hash(String s, int start, int end) {
        String subString = s.substring(start, end);
        int ret = 0;
        for (int i = subString.length() - 1; i >= 0; i--) {
            ret += POW26[subString.length() - 1 - i] * (subString.charAt(i) - 'a');
        }
        return ret;
    }

    /**
     * BM 算法：把搜索过程视为模式串在主串中滑动。
     * 坏字符规则：从模式串的末尾往前匹配，当发现某个字符没法匹配的时候，把这个没有匹配的字符称作坏字符（主串中的字符）。
     *      在发现坏字符时，如果坏字符在模式串中不存在，直接将模式串滑动到坏字符后面。
     *          s: a b c a c a b d c         s: a b c a c a b d c
     *                 ↑ (bad)         =>                 ↑
     *          p: a b d                     p:       a b d（滑到坏字符 c 的后面，从尾开始匹配）
     *                 ↑                                  ↑
     *      如果坏字符在模式串中也存在，则通过滑动把模式串中坏字符出现的位置与之对齐。
     *          s: a b c a c a b d c         s: a b c a c a b d c
     *                       ↑ (bad)   =>                     ↑
     *          p:       a b d               p:           a b d（与坏字符 a 对齐，从尾开始匹配）
     *                       ↑                                ↑
     *      当发生不匹配时把坏字符对应的模式串中的字符下标记作 si。
     *      如果坏字符在模式串中存在，则把这个坏字符在模式串中的下标记作 xi。
     *      如果坏字符在模式串中不存在，则 xi 为 -1。因此模式串往后移动的位数等于 si - xi。
     *      如果坏字符在模式串里多处出现，在计算 xi 时选择最靠后的，这样不会让模式串滑动过多、导致本来可能匹配的情况被滑动略过。
     *
     * 好后缀规则：当模式串从后往前滑动一定位数后才遇到坏字符（在此前是匹配），则认为坏字符后面的是好后缀（b c，记作 {u}）。
     *      s: a b c a c a b c b c
     *                 (bad) ↑ (good) {u}       此时拿 {u} 在模式串中查找，如果找到另一个与 {u} 相匹配的子串 {u*}，
     *      p:       a b b c a b c              则就将模式串滑动到子串 {u*} 与主串中 {u} 对齐的位置。
     *                       ↑
     *
     *     如果在模式串中找不到另一个等于 {u} 的子串，当模式串滑动到前缀与主串中 {u} 的后缀有部分重合（并且重合的部分相等）的时候，就有可能会存在完全匹配的情况。
     *     s: [... {u} ...]
     *     p:     [{v} ..]
     *              ↑ 重合    {v} 是 {u} 的子串
     *
     *     因此还要考察好后缀的后缀子串是否存在跟模式串的前缀子串匹配的。
     *     某个字符串 s 的后缀子串即最后一个字符与 s 对齐的子串，前缀子串即起始字符跟 s 对齐的子串，比如 abc 的后缀子串有 c、bc，前缀子串有 a，ab。
     *     从好后缀的后缀子串中找一个最长的并且能跟模式串的前缀子串匹配的记作 {v}，然后将模式串滑动到下面的位置：
     *     s: [... {u{v}} ...]
     *     p:       [{v} ...]
     *                ↑ 好后缀后缀子串与模式串前缀重合的为位置。
     *
     * 优化：可以将模式串中的每个字符及其下标都存到散列表中，快速找到坏字符在模式串的位置下标（避免在模式串中线性查找）。
     * 此处假设字符串的字符集不是很大，每个字符长度是 1 字节，用大小为 256 的数组记录每个字符在模式串中出现的位置。
     * 数组的下标对应字符的 ASCII 码值，数组中存储字符在模式串中出现的位置。
     *
     * @param s
     * @param p
     * @return
     */
    public int bm(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        // ========== 构建坏字符哈希表 ==========
        // 记录模式串中每个字符最后出现的位置（p.charAt(i) 的 ASCII 值）。
        int[] bc = new int[256];
        for (int i = 0; i < 256; ++i) {
            bc[i] = -1;
        }
        for (int i = 0; i < p.length(); ++i) {
            bc[p.charAt(i)] = i;
        }

        // ========== 构建好后缀匹配规则 ==========
        int[] suffix = new int[pLen];
        boolean[] prefix = new boolean[pLen];
        for (int i = 0; i < pLen; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < pLen - 1; ++i) {
            //  与 p[0, tLen-1] 求公共后缀子串。
            int j = i, k = 0;
            while (j >= 0 && p.charAt(j) == p.charAt(pLen - 1 - k)) {
                --j;
                ++k;
                // j+1 表示公共后缀子串在 p[0, i] 中的起始下标。
                suffix[k] = j + 1;
            }
            if (j == -1) {
                // 如果公共后缀子串也是模式串的前缀子串。
                prefix[k] = true;
            }
        }

        // ========== 开始匹配 ==========
        // j 表示主串与模式串匹配的第一个字符。
        int i = 0;
        while (i <= sLen - pLen) {
            int j;
            // 模式串从后往前匹配。
            for (j = pLen - 1; j >= 0; --j) {
                // 坏字符对应模式串中的下标是 j
                if (s.charAt(i + j) != p.charAt(j)) {
                    break;
                }
            }
            if (j < 0) {
                // 匹配成功，返回主串与模式串第一个匹配的字符的位置。
                return i;
            }
            int x = j - bc[s.charAt(i + j)], y = 0;
            // 如果存在好后缀：
            if (j < pLen - 1) {
                int k = pLen - 1 - j;
                if (suffix[k] != -1) {
                    y = j - suffix[k] + 1;
                } else {
                    y = pLen;
                    for (int r = j + 2; r <= pLen - 1; ++r) {
                        if (prefix[pLen - r]) {
                            y = r;
                        }
                    }
                }
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    /**
     * KMP 算法：类似 BM 算法，但关注的是“坏字符”和“好前缀”。
     *      s: a b a b a e a b a c
     *              (good) ↑ (bad)
     *      p: a b a b a c d
     *                     ↑
     * @param s
     * @param p
     * @return
     */
    public int kmp(String s, String p) {

        /*
           ========== 生成 next 数组 ==========
           next[i] 表示对于 p[0:i+1] 这段长度为 i + 1 的子串，使得“长度为 k 的前缀 = 长度为 k 的后缀”的 k 的最大值（小于子串长度）。
           （后面简称“长度为 i 的子串的前后缀最大值”，再简称“前后缀最大值”），比如对于 ababacd：
                i   子串长度       子串      next[i]     最长前后缀
                0      1      a                 0            -
                1      2      a b               0            -
                2      3      a b a             1            a
                3      4      a b a b           2           ab
                4      5      a b a b a         3           aba
                5      6      a b a b a c       0            -
                6      7      a b a b a c d     0            -
        */
        int[] next = new int[p.length() + 1];
        // 其中 now 表示 next[i-1]，即长度为 i 的子串的后缀最大值。
        // 假设已知 next[0]、next[1] ... next[i-1] 已知，求 next[i]。
        for (int i = 1, now = 0; i < p.length();) {
            // now 的字符与 i 的字符相同，则最长前后缀长度可扩展 1 位。
            // 比如 [a] [b] [a] [b] [a] [c]      =>      [a] [b] [a] [b] [a] [c]
            //              now      i                               now      i
            // 最长前后缀长度扩展到 3，两个下标后移。
            if (p.charAt(i) == p.charAt(now)) {
                now++;
                next[i++] = now;
            }
            // now 的字符与 i 的字符不同。
            else {
                // now 不为 0 则后退一位，并更新为当前下标表示的子串的前后缀最大值。
                // 比如 [a] [b] [a] [b] [a] [c]      =>      [a] [b] [a] [b] [a] [c]      =>      [a] [b] [a] [b] [a] [c]
                //                  now      i                       now          i                   now              i
                // 退 1 位到 2，再取长度为 3 的前后缀最大值（即 1），跳到该位置。
                if (now != 0) {
                    // now = next[now - 1] 的含义是需要一直回退直到 p[now] == p[i] 为止，才能向右扩展。
                    now = next[now - 1];
                }
                // now 为 0（退无可退），则前后缀最大值不变。
                // 比如 [a] [b] [a] [b] [a] [c]      =>      [a] [b] [a] [b] [a] [c]
                //      now i                                now     i
                else {
                    next[++i] = now;
                }
            }
        }

        // ========== 开始匹配 ==========
        int i = 0, j = 0;
        while (i < s.length()) {
            // 如果主串和模式串当前字符相等，则两个下标后移。
            // s: a b b c c           s: a b b c c
            //    i             =>         i
            // p: a b a               p: a b a
            //    j                        j
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                // 否则当模式串下标不为 0 时，从 next 数组取值。
                // s: a b b c c           s: a b b c c
                //        i         =>           i
                // p: a b a               p: a b a          j 回退到 长度为 j 的子串的前后缀最大值（即 1，开始位置）。
                //        j                    j            可以退到这个位置是因为除了 2 这里不匹配以外，此前都是匹配的。
                //
                if (j != 0) {
                    j = next[j - 1];
                }
                // 模式串下标为 0，则主串下标继续后移。
                // s: a b b c c           s: a b b c c
                //        i         =>             i
                // p: a b a               p: a b a
                //    j                      j              不匹配，但 j 为 0（退无可退），i 后移、继续比对下一个。
                else {
                    i++;
                }
            }
            if (j == p.length()) {
                return i - j;
            }
        }
        return -1;
    }

    /**
     *
     * @param p
     * @return
     */
    private List<Integer> buildNext(String p) {
        List<Integer> next = new ArrayList<>(p.length());
        next.add(0);
        for (int i = 1, now = 0; i < p.length();) {
            if (p.charAt(now) == p.charAt(i)) {
                i++;
                next.add(++now);
            } else {
                if (now != 0) {
                    now = next.get(now - 1);
                } else {
                    next.add(0);
                    i++;
                }
            }
        }
        return next;
    }
}
