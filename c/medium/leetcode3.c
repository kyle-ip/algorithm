//
// Created by ywh on 2020/7/22.
//
#include <string.h>

#define max(a, b) ( (a)>(b) ? (a):(b) )
#define min(a, b) ( (a)>(b) ? (b):(a) )

/**
 * 无重复字符的最长子串
 *
 * @param s
 * @return
 */
static int lengthOfLongestSubstring(char *s) {
    int maxLen = 0;
    int idxHash[256];
    for (int i = 0; i < 255; ++i) {
        idxHash[i] = -1;
    }
    for (int l = 0, r = 0; r < strlen(s); r++) {
        l = max(idxHash[s[r]] + 1, l);
        maxLen = max(maxLen, r - l + 1);
        idxHash[s[r]] = r;
    }
    return maxLen;
}