//
// Created by ywh on 2020/8/7.
//

#include <string.h>
#include <stdbool.h>

bool isValid(char * s){
    if (s == NULL || strlen(s) == 0) {
        return 1;
    }
    char stack[strlen(s)];
    int n = 0;
    while (*s) {
        switch (*s) {
            case '(':
                stack[n++] = ')';
                break;
            case '[':
                stack[n++] = ']';
                break;
            case '{':
                stack[n++] = '}';
                break;
            default:
                if (n == 0 || *s != stack[--n]) {
                    return 0;
                }
        }
        s++;
    }
    return n == 0;
}