//
// Created by ywh on 2020/8/3.
//
#include <stdlib.h>
#include <string.h>

const char *mapping[10] = {"", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz",};

/**
 *
 * @param digits
 * @param idx
 * @param str
 * @param p
 */
void combinations(const char *digits, int idx, char *str, char **ret, int *retSize) {
    if (idx == strlen(digits)) {
        strcpy(ret[(*retSize)++], str);
        return;
    }
    const char *chars = mapping[digits[idx] - '0'];
    for (int i = 0; i < strlen(chars); i++) {
        char ca[2] = {chars[i]};
        char *nextStr = (char *) malloc(strlen(str) + 2);
        strcpy(nextStr, str);
        strcat(nextStr, ca);
        combinations(digits, idx + 1, nextStr, ret, retSize);
    }
}

/**
 * 电话号码对应的字母组合
 *
 * @param digits
 * @param returnSize
 * @return
 */
char **letterCombinations(char *digits, int *returnSize) {
    int size = 1, empty = 0;
    size_t len = strlen(digits);
    const int letterLength[10] = {0, 1, 3, 3, 3, 3, 3, 4, 3, 4,};
    *returnSize = 0;
    for (int i = 0; i < len; i++) {
        int index = digits[i] - '0';
        if (letterLength[index] > 0) {
            empty = 1;
            size *= letterLength[index];
        }
    }
    if (!empty) {
        return NULL;
    }
    char **ret = (char **) malloc(sizeof(char *) * size);
    for (int i = 0; i < size; ++i) {
        memset(ret[i] = malloc(len + 1), 0, len + 1);   // ret[i] = malloc(len + 1);
    }
    combinations(digits, 0, "", ret, returnSize);
    return ret;
}