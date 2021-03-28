package com.ywh.problem.leetcode.medium;

import java.util.LinkedList;

/**
 * 简化路径
 * [字符串] [栈]
 * 
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
 * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
 * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 * 示例 1：
 *      输入：path = "/home/"
 *      输出："/home"
 *      解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 *      输入：path = "/../"
 *      输出："/"
 *      解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 * 示例 3：
 *      输入：path = "/home//foo/"
 *      输出："/home/foo"
 *      解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 *      输入：path = "/a/./b/../../c/"
 *      输出："/c"
 * 提示：
 *      1 <= path.length <= 3000
 *      path 由英文字母，数字，'.'，'/' 或 '_' 组成。
 *      path 是一个有效的 Unix 风格绝对路径。
 *
 * @author ywh
 * @since 2020/9/8/008
 */
public class LeetCode71 {

    /**
     * 栈解法（实际上用的是单链表，避免用栈最后还要倒序输出）
     * Time: O(n), Space: O(n)
     *
     * @param path
     * @return
     */
    public String simplifyPath2(String path) {
        LinkedList<String> list = new LinkedList<>();

        // 以 / 把路径字符串划分为多个子串。
        for (String item : path.split("/")) {



            // .. 表示上一级路径，（如果栈非空则）从栈中弹出元素。
            if ("..".equals(item)) {
                if (!list.isEmpty()) {
                    list.removeLast();
                }
            }
            // 非空路径，且非当前路径，入栈。
            else if (!"".equals(item) && !".".equals(item)) {
                list.add(item);
            }
        }
        return "/" + String.join("/", list);
    }


    /**
     * 有限状态机，假设不存在错误，共四种状态：读到一个点、读到两个点、读到分隔符号、读到普通字符串
     *
     * Time: O(n), Space: O(n)
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0 || path.charAt(0) != '/') {
            return path;
        }
        Context context = new Context();
        // 第一个字符为分隔符，再逐个字符读取后续字符（状态流转）
        State state = State.SEPRA;
        for (int i = 1; i < path.length(); i++) {
            state = state.next(path.charAt(i), context);
        }
        // 收尾工作，如果最后状态是 .. 则去掉
        if (state == State.STR) {
            context.flush();
        } else if (state == State.DOT2) {
            context.removeLast();
        }
        // 组装结果
        return context.getValidStrings();
    }

    /**
     * 有限状态机：每次读取一个字符，根据当前字符及当前状态，决定下一步的状态应该是什么。
     *
     */
    private enum State {

        /* . */
        DOT1 {
            @Override
            State next(char c, Context context) {
                switch (c) {
                    case '.':
                        return DOT2;
                    case '/':
                        return SEPRA;
                    default:
                        context.addChar('.').addChar(c);
                        return STR;
                }
            }
        },
        /* .. */
        DOT2 {
            @Override
            State next(char c, Context context) {
                // ../ 回到上一级目录
                if (c == '/') {
                    context.removeLast();
                    return SEPRA;
                }
                // ..x
                context.addChar('.').addChar('.').addChar(c);
                return STR;
            }
        },
        /* / */
        SEPRA {
            @Override
            State next(char c, Context context) {
                switch (c) {
                    // //，跳过
                    case '/':
                        return SEPRA;
                    // /.，
                    case '.':
                        return DOT1;
                    default:
                        context.addChar(c);
                        return STR;
                }
            }
        },
        /* x */
        STR {
            @Override
            State next(char c, Context context) {
                // x/
                if (c == '/') {
                    context.flush();
                    return SEPRA;
                }
                context.addChar(c);
                return STR;
            }
        };

        abstract State next(char c, Context context);
    }

    /**
     * 上下文（记录中间状态）
     */
    private static class Context {

        // 存放合法的字符串，拼接最终结果
        private final LinkedList<String> stringList = new LinkedList<>();

        // 字符缓冲区
        private StringBuilder buffer = new StringBuilder();

        public Context addChar(char c) {
            buffer.append(c);
            return this;
        }

        public void removeLast() {
            if (stringList.size() > 0) {
                stringList.removeLast();
            }
        }

        /**
         *
         */
        public void flush() {
            if (buffer.length() <= 0) {
                return;
            }
            stringList.add(buffer.toString());
            buffer = new StringBuilder();
        }

        /**
         * 拼接最终结果
         * @return
         */
        public String getValidStrings() {
            return "/" + String.join("/", stringList);
        }
    }
}
