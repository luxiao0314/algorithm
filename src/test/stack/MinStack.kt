package test.stack

import java.util.*
import kotlin.jvm.JvmStatic

/**
 * @Description: https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 * 剑指 Offer 30. 包含min函数的栈,定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * @Date 2020/9/8 10:42 PM
 * @Version
 */

/**
 * Stack和list类似,继承Vector,push就是顺序添加数据,pop是list.get(len-1)获取最后面的值
 * Stack和list都是顺序添加,Stack的先进后出是pop和peek都是从后面取elementAt(len - 1)
 */
class MinStack {
    var stack: Stack<Int> = Stack()
    var stackMin: Stack<Int> = Stack()
    fun push(x: Int) {
        stack.push(x)
        if (stackMin.isEmpty()) {
            stackMin.push(x)
        } else {
            val num = stackMin.peek()
            if (x < num) {
                stackMin.push(x)
            } else {
                stackMin.push(num)
            }
        }
    }

    fun pop() {
        stack.pop()
        stackMin.pop()
    }

    fun top(): Int {
        return stack.peek()
    }

    fun min(): Int {
        return stackMin.peek()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val minStack = MinStack()
            minStack.push(-2)
            minStack.push(0)
            minStack.push(-3)
            println(minStack.min())
//            minStack.pop()
            println(minStack.top())
//            println(minStack.min())
        }
    }

}