# coding=utf-8


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x, next=None):
        self.val = x
        self.next = next


class DoubleNode(object):
    def __init__(self, key, val, pre=None, next=None):
        self.key = key
        self.val = val
        self.pre = pre
        self.next = next


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class TrieNode(object):
    def __init__(self, end=False):
        self.children = []
        for i in range(26):
            self.children.append(None)
        self.end = end

    def set_end(self):
        self.end = True

    @property
    def is_end(self):
        return self.end


class RandomNode(object):
    def __init__(self, val, next, random):
        self.val = val
        self.next = next
        self.random = random


class GraphNode(object):
    def __init__(self, val, neighbors):
        self.val = val
        self.neighbors = neighbors


class QuadTreeNode(object):
    def __init__(self, val, is_leaf, top_left,
                 top_right, bottom_left, bottom_right):
        self.val = val
        self.is_leaf = is_leaf
        self.top_left = top_left
        self.top_right = top_right
        self.bottom_left = bottom_left
        self.bottom_right = bottom_right

