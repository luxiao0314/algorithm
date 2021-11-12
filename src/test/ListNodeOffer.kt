package test

import org.w3c.dom.NodeList
import java.util.*
import kotlin.collections.HashSet


/**
 * @Description
 * @Author lux
 * @Date 2021/11/10 2:59 下午
 * @Version
 */
fun main() {

    val listNode = ListNode(2)
    val listNode1 = ListNode(3)
    val listNode2 = ListNode(4)
    val listNode3 = ListNode(4)

    listNode.next = listNode1
    listNode1.next = listNode2
    listNode2.next = listNode3

//    println(reverseList1(listNode))

//    println(findFirstCommonNode(listNode, ListNode(3, listNode1)))

    println(deleteDuplication(listNode))
}

//输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
/*************************************************************************************************************************/

data class ListNode(var head: Int, var next: ListNode? = null)

//先把元素里面的元素从头到尾遍历取出放在栈里面，然后再把栈的元素取出来放在ArraList里面。主要利用了栈的先进后出的规则，这样就可以实现倒序的功能
//压栈,FILO,现金后出
fun listFromTailToHead1(listNode: ListNode?): ArrayList<Int> {
    var listNode: ListNode? = listNode
    val stack = Stack<Int>()
    while (listNode != null) {
        stack.push(listNode.head)
        listNode = listNode.next
    }
    val results = ArrayList<Int>()
    while (!stack.isEmpty()) {
        results.add(stack.pop())
    }
    return results
}

//前面我们能想到栈，那么我们何必自己实现呢？其实 "方法的调用过程，就是一个天然的栈调用的过程",FILO
//只需要判断当前节点是不是为空，为空则不输出，不为空则递归下一个节点，对下一个节点处理之后，把结果使用ArrayList.addAll()加到结果中，再把自身加到结果中
fun listFromTailToHead2(listNode: ListNode?): ArrayList<Int> {
    val results = ArrayList<Int>()
    if (listNode != null) {
        // 对后面的元素进行处理
        results.addAll(listFromTailToHead2(listNode.next))
        // 最后添加自身
        results.add(listNode.head)
    }
    return results
}

//往集合第一个添加
fun listFromTailToHead3(listNode: ListNode?): ArrayList<Int> {
    var listNode: ListNode? = listNode
    val results = ArrayList<Int>()
    while (listNode != null) {
        results.add(0, listNode.head)
        listNode = listNode.next
    }
    return results
}

//输入一个链表，反转链表后，输出新链表的表头。
/*************************************************************************************************************************/

//首先，使用循环解答，不断把指向下一个的指针，指向前面的。假设链表是1->2->3->4，那么执行一次循环里面的内容,直到head==null的时候，返回first即可
fun reverseList1(head: ListNode?): ListNode? {
    var head = head
    var first: ListNode? = null
    while (head != null) {
        val temp = head.next
        head.next = first
        first = head
        head = temp
    }
    return first
}

//头插法，也就是先初始化一个listNode,初始化为0->null；然后遍历链表，不断将元素插入到0的后面。
// 假设链表是1->2->3->4. 0->1->null 0->2->1->null 0->3->2->1->null 0->4->3->2->1->null 然后取出listnode.next即可。
fun reverseList2(head: ListNode?): ListNode? {
    var head = head
    val listnode = ListNode(1)
    while (head != null) {
        val next = head.next
        head.next = listnode.next
        listnode.next = head
        head = next
    }
    return listnode.next
}

//输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
/*************************************************************************************************************************/

//第一种做法，直接依赖于HashSet，遍历第一个链表的时候，将所有的节点，添加到hashset中，遍历第二个链表的时候直接判断是否包含即可，属于空间换时间的做法。
fun findFirstCommonNode(pHead1: ListNode?, pHead2: ListNode?): ListNode? {
    //创建集合set
    var pHead1 = pHead1
    var pHead2 = pHead2
    val set: MutableSet<ListNode> = HashSet()
    while (pHead1 != null) {
        set.add(pHead1)
        pHead1 = pHead1.next
    }
    while (pHead2 != null) {
        if (set.contains(pHead2)) return pHead2
        pHead2 = pHead2.next
    }
    return null
}


//在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
/*************************************************************************************************************************/

//第一种做法是借助额外的空间，使用了HashMap,先遍历一次，将里面的元素以及是否重复，记录下来，key是出现的元素，value是重复标记
//再次遍历LinkedHashMap里面的元素，取出value不为-1的元素，也就是出现一次的元素，拼接成为链表。
fun deleteDuplication(pHead: ListNode?): ListNode? {
    var pHead = pHead
    val maps: HashMap<Int, Int> = HashMap()
    if (pHead != null) {
        // 遍历添加到set中
        while (pHead != null) {
            if (!maps.containsKey(pHead.head)) {
                maps[pHead.head] = 1
            } else {
                maps[pHead.head] = -1
            }
            pHead = pHead.next
        }
        val listNode = ListNode(-1)
        var temp: ListNode? = listNode
        maps.entries.map {
            if (it.value == 1) {
                temp?.next = ListNode(it.key)
                temp = temp?.next
            }
        }

        return listNode.next
    }
    return null
}

//插入到linkedList的头部
//fun push(newData: Int) {
//    //构建要插入的节点
//    val newNode = ListNode(newData)
//    //新节点的next指向现在的head节点
//    newNode.next = head;
//    //现有的head节点指向新的节点,next和head头尾对应
//    head = newNode;
//}