package leetcode146

//type LRUCache struct {
//    hash  map[int]*Node
//    head *Node
//}
//
//type Node struct {
//    key, val   int
//    prev, next *Node
//}
//
//func (this *LRUCache) move2Head(node *Node) {
//    if node == this.head {
//        this.head = this.head.prev
//        return
//    }
//    node.prev.next = node.next
//    node.next.prev = node.prev
//
//    node.next = this.head.next
//    node.prev = this.head.prev
//
//    this.head.next = node
//    node.prev = this.head
//}
//
//func Constructor(capacity int) LRUCache {
//    head, node := &Node{}, &Node{}
//    for i := 0; i < capacity-1; i++ {
//        node.next = &Node{prev: node}
//        node = node.next
//    }
//    node.next = head
//    head.prev = node
//    return LRUCache{head: head, hash: make(map[int]*Node)}
//}
//
//func (this *LRUCache) Get(key int) int {
//    if node, ok := this.hash[key]; ok {
//        this.move2Head(node)
//        return node.val
//    }
//    return -1
//}
//
//func (this *LRUCache) Put(key, value int) {
//    var node *Node
//    if _, ok := this.hash[key]; ok {
//        node = this.hash[key]
//    } else {
//        node = this.head
//        delete(this.hash, key)
//    }
//    node.key, node.val = key, value
//    this.hash[key] = node
//    this.move2Head(node)
//}

type LRUCache struct {
    head, tail *Node
    Keys       map[int]*Node
    Cap        int
}

type Node struct {
    Key, Val   int
    Prev, Next *Node
}

func Constructor(capacity int) LRUCache {
    return LRUCache{Keys: make(map[int]*Node), Cap: capacity}
}

func (this *LRUCache) Add(node *Node) {
    node.Prev = nil
    node.Next = this.head
    if this.head != nil {
        this.head.Prev = node
    }
    this.head = node
    if this.tail == nil {
        this.tail = node
        this.tail.Next = nil
    }
}

func (this *LRUCache) Remove(node *Node) {
    if node == this.head {
        this.head = node.Next
        node.Next = nil
        return
    }
    if node == this.tail {
        this.tail = node.Prev
        node.Prev.Next = nil
        node.Prev = nil
        return
    }
    node.Prev.Next = node.Next
    node.Next.Prev = node.Prev
}

func (this *LRUCache) Get(key int) int {
    if node, ok := this.Keys[key]; ok {
        this.Remove(node)
        this.Add(node)
        return node.Val
    }
    return -1
}

func (this *LRUCache) Put(key int, value int) {
    if node, ok := this.Keys[key]; ok {
        node.Val = value
        this.Remove(node)
        this.Add(node)
        return
    } else {
        node = &Node{Key: key, Val: value}
        this.Keys[key] = node
        this.Add(node)
    }
    if len(this.Keys) > this.Cap {
        delete(this.Keys, this.tail.Key)
        this.Remove(this.tail)
    }
}