package test.stack

import java.util.*
import kotlin.jvm.JvmStatic

/**
 * @Description: https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 剑指 Offer 09. 用两个栈实现队列 :取出栈先进后出数据添加到新的栈,就是先进先出
 * @Author tinytongtong
 * @Date 2020/9/4 6:05 PM
 * @Version
 */
object ImplQueueByTwoStack {
    @JvmStatic
    fun main(args: Array<String>) {
        val obj = CQueue()
        obj.add(1)
        obj.add(2)
        obj.add(3)
        println(obj)
        println(obj.pop())
        println(obj)
        println(obj.pop())
        obj.add(4)
        obj.add(5)
        obj.add(6)
        println(obj)
        println(obj.pop())
        println(obj)
        println(obj.pop())
        println(obj)
        println(obj.pop())
        println(obj)
        println(obj.pop())
        // 无数据了
        println(obj)
        println(obj.pop())
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
        fun add(value: Int) {
            stackAdd.add(value)
        }

        //取出栈先进后出数据添加到新的栈,就是先进先出
        fun pop(): Int {
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