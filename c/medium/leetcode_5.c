//
// Created by ywh on 2020/7/22.
//
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#define max(a, b) ((a) > (b)? (a): (b))

/**
 * O(n^2), Space: O(n)
 *
 * @param s
 * @return
 */
char *longestPalindrome(char *s) {
    if (s == NULL || strlen(s) == 0) {
        return "";
    }
    size_t n = strlen(s);
    int start = 0, maxLen = 0;
    int dp[n][n];
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
            if (i == j) {
                dp[i][j] = 1;
            } else if (i + 1 == j) {
                dp[i][j] = s[i] == s[j];
            } else {
                dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1];
            }
            if (dp[i][j] && j - i + 1 > maxLen) {
                maxLen = j - i + 1;
                start = i;
            }
        }
    }
    char *ret = malloc(maxLen * sizeof(char));
    int i;
    for (i = 0; start <= maxLen;) {
        ret[i++] = s[start++];
    }
    ret[i] = '\0';
    return ret;
}

/**
 *
 * @param s
 * @return
 */
char *longestPalindrome2(char *s) {
    int left, right, maxLen = 0, start = 0, idx = 0;
    while (s[idx]) {
        right = idx;
        left = idx - 1;
        while (s[right] == s[idx]) {
            right++;
        }
        idx = right;
        for (; left >= 0 && s[right] && s[right] == s[left]; left--, right++);
        if (right - left - 1 > maxLen) {
            start = left + 1;
            maxLen = right - left - 1;
        }
    }
    char *ret = (char *) malloc(maxLen + 1);
    for (int i = 0; i < maxLen; ++i) {
        ret[i] = s[start + i];
    }
    ret[maxLen] = '\0';
    return ret;
}

/**
 *
 * @param s
 * @param left
 * @param right
 * @return
 */
int expand(char *s, int left, int right) {
    for (; left >= 0 && s[right] && s[left] == s[right]; left--, right++);
    return right - left - 1;
}

/**
 *
 * @param s
 * @return
 */
char *longestPalindrome3(char *s) {
    if (s == NULL || strlen(s) == 0) {
        return "";
    }
    int start = 0, maxLen = 0, curLen;
    for (int i = 0; i < strlen(s); ++i) {
        curLen = max(expand(s, i, i), expand(s, i, i + 1));
        if (curLen > maxLen) {
            maxLen = curLen;
            start = i - (curLen - 1) / 2;
        }
    }
    char *ret = (char *) malloc(maxLen + 1);
    for (int i = 0; i < maxLen; ++i) {
        ret[i] = s[start + i];
    }
    ret[maxLen] = '\0';
    return ret;
}

int main() {
    printf("%s", longestPalindrome3("babad"));
    return 0;
}