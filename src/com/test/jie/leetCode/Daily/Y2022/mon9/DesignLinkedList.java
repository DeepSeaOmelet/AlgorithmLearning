package com.test.jie.leetCode.Daily.Y2022.mon9;

/**
 * 2022/09/23
 * 707. 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * <p>
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 */
public class DesignLinkedList {
    public static void main(String[] args) {

    }

}

//双链表
class MyLinkedList {
    class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }
    }

    //head和tail只是指针，不是真正的链表
    Node head = new Node(-1);
    Node tail = new Node(-1);
    int size = 0;

    public MyLinkedList() {
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        Node node = getNode(index);
        return node == null ? -1 : node.val;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        //head.next.prev:  head->next node<-next
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        size++;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index <= 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            Node cur = getNode(index);
            Node node = new Node(val);
            node.next = cur;
            node.prev = cur.prev;
            cur.prev.next = node;
            cur.prev = node;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        Node node = getNode(index);
        if (node != null) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            size--;
        }
    }

    Node getNode(int index) {
        if (index > size - 1) return null;
        boolean isLeft = size / 2 > index;
        if (!isLeft) {
            index = size - 1 - index;
        }
        //注意空指针
        for (Node cur = isLeft ? head.next : tail.prev; cur != tail && cur != head; cur = isLeft ? cur.next : cur.prev) {
            if (index-- == 0) return cur;
        }
        return null;
    }
}