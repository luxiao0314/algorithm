package test.queue

import kotlin.concurrent.thread

/**
 * @Description
 * @Author lux
 * @Date 2021/11/12 5:52 下午
 * @Version
 */
fun main() {
    val queue = BatchQueue<Int>()
    queue.setProcess {
        println(it.toString())
    }

    thread {
        Thread.sleep(10)
        queue.add(1)
    }
    thread {
        Thread.sleep(2)
        queue.add(2)
    }
    thread {
        Thread.sleep(3)
        queue.add(3)
        queue.add(4)
    }
}