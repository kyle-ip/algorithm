//
// Created by ywh on 2020/7/21.
//
#include <string.h>
#include <stdlib.h>

/**
 * 字符串的最长公共前缀
 *
 * @param strs
 * @param strsSize
 * @return
 */
static char *longestCommonPrefix(char **strs, int strsSize) {
    if (strsSize == 0) {
        return "";
    }
    char *first = strs[0], *ret;
    for (int i = 0; i < strlen(first); ++i) {
        for (int j = 1; j < strsSize; ++j) {
            if (strlen(strs[j]) <= i || strs[j][i] != first[i]) {
                ret = malloc((i + 1) * sizeof(char));
                strncpy(ret, first, i);
                ret[i] = '\0';
                return ret;
            }
        }
    }

    return first;
}
