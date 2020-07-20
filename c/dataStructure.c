//
// Created by ywh on 2020/7/20.
//
#include "dataStructure.h"

static int cmp(const void *a, const void *b) {
    return ((struct Object *) a)->val - ((struct Object *) b)->val;
}