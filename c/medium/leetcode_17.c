//
// Created by ywh on 2020/8/3.
//
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

const char *mapping[10] = {"", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz",};

/**
 *
 * @param str
 * @param c
 * @return
 */
char *sadd(char *str, char c) {
    char ca[2];
    sprintf(ca, "%c", c);
    char *ret = (char *) malloc(strlen(str) + 2);
    strcpy(ret, str);
    strcat(ret, ca);
    return ret;
}

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
    // char -> int -> str: '2' -> "abc", '3' -> "def"...
    const char *chars = mapping[(int) digits[idx] - 48];
    for (int i = 0; i < strlen(chars); i++) {
        // str + c -> str: "ab" + 'b' -> "abb"
        char ca[2];
        sprintf(ca, "%c", chars[i]);
        char *nextStr = (char *) malloc(strlen(str) + 2);
        strcpy(nextStr, str);
        strcat(nextStr, ca);
        combinations(digits, idx + 1, nextStr, ret, retSize);
    }
}

/**
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
        ret[i] = malloc(len + 1);
        memset(ret[i], 0, len + 1);
    }
    combinations(digits, 0, "", ret, returnSize);
    return ret;
}