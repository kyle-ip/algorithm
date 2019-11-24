package com.ywh.util;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ywh
 * @since 23/11/2019
 */
public class AssertUtil {


    private static <T> String list2String(List<T> list) {
        if (list == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        for (T e : list) {
            if (e == null) sb.append("null, ");
            else sb.append(e).append(", ");
        }
        if (sb.length() >= 2) sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public static <T> void assert2DimIntListEquals(List<List<T>> expecteds, List<List<T>> actuals) {
        if (expecteds == null || actuals == null) {
            throw new AssertionError("In expecteds and actuals, one if null but another is not null.");
        }

        assertEquals(expecteds.size(), actuals.size());
        expecteds.sort(Comparator.comparing(a -> {
            if (a == null || a.size() == 0) {
                return "";
            }
            return a.size() * 10 + a.get(0).toString();
        }));
        actuals.sort(Comparator.comparing(a -> {
            if (a == null || a.size() == 0) {
                return "";
            }
            return a.size() * 10 + a.get(0).toString();
        }));

        for (int i = 0; i < expecteds.size(); i++) {
            List<T> expected = expecteds.get(i);
            List<T> actual = actuals.get(i);
            assertEquals(expected, actual);
        }
    }

    public static <T> void assertIntListEquals(List<T> expecteds, List<T> actuals) {
        if (expecteds == null && actuals == null) {
            return;
        }

        if (expecteds == null || actuals == null) {
            throw new AssertionError("In expecteds and actuals, one if null but another is not null.");
        }
        assertEquals(expecteds.size(), actuals.size());
        for (int i = 0; i < expecteds.size(); i++) {
            T expected = expecteds.get(i);
            T actual = actuals.get(i);
            assertEquals(expected, actual);
        }
    }
}
