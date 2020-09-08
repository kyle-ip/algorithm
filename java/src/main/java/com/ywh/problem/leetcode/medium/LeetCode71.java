package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 简化路径
 * [字符串] [栈]
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
        LinkedList<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else if (!item.isEmpty() && !item.equals(".")) {
                stack.add(item);
            }
        }
        return "/" + String.join("/", stack);
    }


    /**
     * 有限状态机，假设不存在错误，共四种状态：读到一个点、读到两个点、读到分隔符号、读到普通字符串
     * TODO 暂时未理解
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
