package leetcodetest.targetoffer.question24;

import java.util.Stack;

/**
 * @Description: https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 * 剑指 Offer 24. 反转链表
 * @Author tinytongtong
 * @Date 2020/9/8 10:48 AM
 * @Version
 */
public class ReverseListNode {
    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode ReverseList(ListNode head) {
        //新链表
        ListNode newHead = null;
        while (head != null) {
            //先保存访问的节点的下一个节点，保存起来
            //留着下一步访问的
            ListNode temp = head.next;
            //每次访问的原链表节点都会成为新链表的头结点，
            //其实就是把新链表挂到访问的原链表节点的后面就行了
            head.next = newHead;
            //更新新链表
            newHead = head;
            //重新赋值，继续访问
            head = temp;
        }
        //返回新链表
        return newHead;
    }

    /**
     * 迭代实现
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    private static ListNode reverseListByIteration(ListNode head) {
        //pre指针：用来指向反转后的节点，初始化为null
        ListNode pre = null;
        //当前节点指针
        ListNode cur = head;
        //循环迭代
        while(cur!=null){
            //Cur_next 节点，永远指向当前节点cur的下一个节点
            ListNode Cur_next = cur.next;
            //反转的关键：当前的节点指向其前一个节点(注意这不是双向链表，没有前驱指针)
            cur.next = pre;
            //更新pre
            pre = cur;
            //更新当前节点指针
            cur = Cur_next ;
        }
        //为什么返回pre？因为pre是反转之后的头节点
        return pre;
    }

    /**
     * 递归实现
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param pHead
     * @return
     */
    private static ListNode reverseListByRecursive(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode pRoot = pHead;
        while (pRoot.next != null) {
            pRoot = pRoot.next;
        }
        ListNode pPrev = reverseListByRecursiveCore(pHead);
        return pRoot;
    }

    private static ListNode reverseListByRecursiveCore(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode pPrev = reverseListByRecursiveCore(pHead.next);
        // 防止形成环
        pHead.next = null;
        if (pPrev != null) {
            pPrev.next = pHead;
        }
        return pHead;
    }

    /**
     * 用栈实现
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * 采用栈先进后出方式,反转即倒排
     *
     * @param pHead
     * @return
     */
    private static ListNode reverseListByStack(ListNode pHead) {
        while (pHead == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (pHead != null) {
            stack.push(new ListNode(pHead.val));
            pHead = pHead.next;
        }
        ListNode pResult = new ListNode(0);
        ListNode pNode = pResult;
        while (!stack.isEmpty()) {
            pNode.next = stack.pop();
            pNode = pNode.next;
        }
        return pResult.next;
    }

    public static void main(String[] args) {
        ListNode pHead = new ListNode(1);
        ListNode pNode2 = new ListNode(2);
        ListNode pNode3 = new ListNode(3);
        ListNode pNode4 = new ListNode(4);
        ListNode pNode5 = new ListNode(5);
        pHead.next = pNode2;
        pNode2.next = pNode3;
        pNode3.next = pNode4;
        pNode4.next = pNode5;
//        System.out.println(pHead);

//        System.out.println(reverseListByStack(pHead));
//        System.out.println(ReverseList(pHead));
//        System.out.println(reverseListByRecursive(pHead));
    }
}
