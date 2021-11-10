package test

import java.util.*


/**
 * @Description
 * @Author lux
 * @Date 2021/11/10 2:59 下午
 * @Version
 */
fun main() {
    val nums = intArrayOf(2, 5, 1, 4)

//    reOrderArray3(nums)
//    println(nums.contentToString())

    val listNode = ListNode(2)
    val listNode1 = ListNode(3)
    listNode1.next = ListNode(4)
    listNode.next = listNode1
    println(reverseList1(listNode))

}

//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
/*************************************************************************************************************************/

//用空间换时间做法，就是新建一个数组，copy一份，先计算出奇数的个数，也就是能够知道第一个偶数应该放在数组的哪一个位置，然后再遍历一次，依次放到对应的位置即可。
fun reOrderArray1(nums: IntArray) {
    // 奇数个数
    var oddCnt = 0
    for (x in nums) if (x % 2 == 1) oddCnt++
    val copy = nums.clone()
    var i = 0
    var j = oddCnt
    for (num in copy) {
        if (num % 2 == 1) {
            nums[i++] = num
        } else {
            nums[j++] = num
        }
    }
}

//时间复杂度为O(n^2),类似冒泡，将找到的奇数不断往前面冒泡，直到前面排好奇数的位置。
fun reOrderArray2(array: IntArray) {
    // 已经摆好的奇数个数
    var numOfOdd = 0
    for (i in array.indices) {
        if (array[i] % 2 == 1) {
            var j = i
            // 往前面冒泡
            while (j > numOfOdd) {
                val tmp = array[j]
                array[j] = array[j - 1]
                array[j - 1] = tmp
                j--
            }
            numOfOdd++
        }
    }
}

//通过添加奇数到一个集合,偶数到一个集合,两个再合并
fun reOrderArrays3(array: IntArray) {
    val ji = mutableListOf<Int>()
    val ou = mutableListOf<Int>()
    // 已经摆好的奇数个数
    array.map {
        if (it % 2 == 1) {
            ji.add(it)
        } else {
            ou.add(it)
        }
    }
    ji.addAll(ou)
    println("nums: $ji}")
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

