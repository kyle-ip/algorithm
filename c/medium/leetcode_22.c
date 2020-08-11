/**
 * Created by ywh on 10/08/2020.
 */
#include <stdlib.h>
#include <string.h>

#define MAX 5000

/**
 *
 * @param ret
 * @param returnSize
 * @param str
 * @param left
 * @param right
 */
void generate(char ** ret, int* returnSize, char * str, int left, int right) {
    if (left == 0 && right == 0) {
        strcpy(ret[(*returnSize)++], str);
        return;
    }
    char *nextStr = (char *) malloc(strlen(str) + 2);
    if (left > 0) {
        char ca[2] = {'('};
        strcpy(nextStr, str);
        strcat(nextStr, ca);
        generate(ret, returnSize, nextStr, left - 1, right);
    }
    if (right > left) {
        char ca[2] = {')'};
        strcpy(nextStr, str);
        strcat(nextStr, ca);
        generate(ret, returnSize, nextStr, left, right - 1);
    }
}

/**
 * 括号的合法排列
 *
 * Time: O(4^n / sqrt(n)), Space: O(n)
 *
 * @param n
 * @param returnSize
 * @return
 */
char ** generateParenthesis(int n, int* returnSize){
    *returnSize = 0;
    if (n <= 0) {
        return NULL;
    }
    char **ret = (char **) malloc(sizeof(char *) * MAX);
    for (int i = 0; i < MAX; ++i) {
        memset(ret[i] = malloc(n * 2 + 1), 0, n * 2 + 1);
    }
    generate(ret, returnSize, "", n, n);
    return ret;
}