/**
/* Created by ywh on 30/07/2020.
 */

#include <stdlib.h>
#include <string.h>

static char *convert(char *s, int numRows) {
    if (numRows <= 1) {
        return s;
    }
    int len = strlen(s);
    char *new_str = malloc(len + 1);
    char *p = new_str;
    int row = 0;
    for (row = 0; row < numRows; row++) {
        int interval1 = numRows + (numRows - 2) - row * 2;
        int interval2 = 2 * row;
        int flag = 0;
        int i = row;
        while (i < len) {
            *p++ = s[i];
            int delta = 0;
            do {
                delta = flag == 0 ? interval1 : interval2;
                flag = !flag;
            } while (delta == 0);
            i += delta;
        }
    }

    new_str[len] = '\0';
    return new_str;
}