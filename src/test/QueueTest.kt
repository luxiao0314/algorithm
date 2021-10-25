package test

import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicInteger

/**
 * @Description
 * @Author lux
 * @Date 2021/10/18 11:20 上午
 * @Version
 */


internal class DataItem(private val number: Int) {

    private var flag = false

    fun show() {
        println(number.toString() + ": " + if (flag) "Urgency" else "Common")
    }

    fun isUrgency(): Boolean {
        return flag
    }

    companion object {
        val count = AtomicInteger(0)
    }

    init {
        flag = number % 3 == 0
        count.incrementAndGet()
    }
}

internal class Receiver(private val queue: BlockingDeque<DataItem>) : Thread() {
    override fun run() {
        var item: DataItem
        try {
            while (true) {
                item = queue.takeLast()
                item.show()
                DataItem.count.decrementAndGet()
            }
        } catch (ie: InterruptedException) {
            println("Receiver finished")
        }
    }
}

object BlockingDequeDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val queue: BlockingDeque<DataItem> = LinkedBlockingDeque(20)
        try {
            for (i in 0..9) {
                val item = DataItem(i)
                if (item.isUrgency()) {
                    queue.putLast(item)
                } else {
                    queue.putFirst(item)
                }
            }
        } catch (ie: InterruptedException) {
            println("Interrupted")
        }
        val receiver = Thread(Receiver(queue))
        receiver.start()
        while (DataItem.count.get() > 0) {
            try {
                Thread.sleep(10)
            } catch (e: InterruptedException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
        receiver.interrupt()
        println("Main finished")
    }
}