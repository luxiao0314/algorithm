package test.stack

import java.util.*
import kotlin.jvm.JvmStatic

/**
 * @Description: 用两个队列实现栈
 * @Author tinytongtong
 * @Date 2020/9/4 6:28 PM
 * @Version
 */
object ImplStackByTwoQueue {
    @JvmStatic
    fun main(args: Array<String>) {
//        CStack stack = new CStack();
        val stack = CStack1()
        stack.add(1)
        stack.add(2)
        stack.add(3)
        stack.add(4)
        println(stack)
        println(stack.pop())
        println(stack.pop())
        stack.add(5)
        stack.add(6)
        println(stack)
        println(stack.pop())
        println(stack)
    }

    /**
     * 一个队列为空，一个不为空
     * add时相关不为空的队列添加；
     * pop数据时，先把有数据的队列中的元素依次读出来，放入到队列中，最后的一个元素就是需要pop的数据
     */
    private class CStack {
        var queue1: Queue<Int> = LinkedList()
        var queue2: Queue<Int> = LinkedList()
        fun add(`val`: Int) {
            if (!queue1.isEmpty()) {
                queue1.add(`val`)
            } else {
                queue2.add(`val`)
            }
        }

        fun pop(): Int {
            if (queue1.isEmpty() && queue2.isEmpty()) {
                return -1
            }
            return if (queue1.isEmpty()) {
                while (queue2.size > 1) {
                    queue1.add(queue2.poll())
                }
                if (!queue2.isEmpty()) queue2.poll() else -1
            } else {
                while (queue1.size > 1) {
                    queue2.add(queue1.poll())
                }
                if (!queue1.isEmpty()) queue1.poll() else -1
            }
        }

        override fun toString(): String {
            return "CStack{" +
                    "queue1=" + queue1 +
                    ", queue2=" + queue2 +
                    '}'
        }

    }

    /**
     * add过程:
     * ①先把数据swap到另一个空的队列。
     * ②将数据add到空的队列。
     * ③将swap的数据swap到新添加的数据上面。
     * remove过程：从不为空的队列中获取元素。
     */
    private class CStack1 {
        var queue1: Queue<Int> = LinkedList()
        var queue2: Queue<Int> = LinkedList()
        fun add(`val`: Int) {
            val empty: Queue<Int>
            val notEmpty: Queue<Int>
            if (queue1.isEmpty()) {
                empty = queue1
                notEmpty = queue2
            } else {
                empty = queue2
                notEmpty = queue1
            }
            // 1、swap
            while (!notEmpty.isEmpty()) {
                empty.add(notEmpty.poll())
            }
            // 2、add
            notEmpty.add(`val`)
            // 3、swap back
            while (!empty.isEmpty()) {
                notEmpty.add(empty.poll())
            }
        }

        fun pop(): Int {
            if (queue1.isEmpty() && queue2.isEmpty()) {
                return -1
            }
            return if (queue1.isEmpty()) {
                queue2.poll()
            } else {
                queue1.poll()
            }
        }

        override fun toString(): String {
            return "CStack1{" +
                    "queue1=" + queue1 +
                    ", queue2=" + queue2 +
                    '}'
        }

    }
}