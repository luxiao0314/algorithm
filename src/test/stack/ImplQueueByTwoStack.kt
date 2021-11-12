package test.stack

import java.util.*
import kotlin.jvm.JvmStatic

/**
 * @Description: https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 剑指 Offer 09. 用两个栈实现队列
 * @Author tinytongtong
 * @Date 2020/9/4 6:05 PM
 * @Version
 */
object ImplQueueByTwoStack {
    @JvmStatic
    fun main(args: Array<String>) {
        val obj = CQueue()
        obj.appendTail(1)
        obj.appendTail(2)
        obj.appendTail(3)
        println(obj)
        println(obj.deleteHead())
        println(obj)
        println(obj.deleteHead())
        obj.appendTail(4)
        obj.appendTail(5)
        obj.appendTail(6)
        println(obj)
        println(obj.deleteHead())
        println(obj)
        println(obj.deleteHead())
        println(obj)
        println(obj.deleteHead())
        println(obj)
        println(obj.deleteHead())
        // 无数据了
        println(obj)
        println(obj.deleteHead())
        println(obj)
    }

    /**
     * 实现思路：
     * ①stackAdd用来添加
     * ②stackRemove用来获取
     * ③如果stackRemove为空时，就把stackAdd中的元素依次出栈再加入到stackRemove中，然后从stackRemove中获取元素。
     */
    private class CQueue {
        private val stackAdd: Stack<Int> = Stack()
        private val stackRemove: Stack<Int> = Stack()
        fun appendTail(value: Int) {
            stackAdd.add(value)
        }

        //取出栈先进后出数据添加到新的栈,就是先进先出了
        fun deleteHead(): Int {
            if (stackRemove.isEmpty()) {
                while (!stackAdd.isEmpty()) {
                    stackRemove.add(stackAdd.pop())
                }
            }
            return if (!stackRemove.isEmpty()) stackRemove.pop() else -1
        }

        override fun toString(): String {
            return "CQueue{" +
                    "stackAdd=" + stackAdd +
                    ", stackRemove=" + stackRemove +
                    '}'
        }

    }
}