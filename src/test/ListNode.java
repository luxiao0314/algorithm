package test;

public class ListNode {

    int head;
    ListNode next;

    public ListNode(int head, ListNode next) {
        this.head = head;
        this.next = next;
    }

    public ListNode(int head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "head=" + head +
                ", next=" + next +
                '}';
    }
}
