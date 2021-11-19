package test.queue

import kotlin.jvm.JvmOverloads
import java.util.concurrent.LinkedBlockingQueue
import java.lang.InterruptedException
import java.lang.Exception
import kotlin.concurrent.thread

/**
 * @Description queue
 * @Author lucio
 * @Email xiao.lu@magicwindow.cn
 * @Date 12/03/2018 11:48 PM
 * @Version 1.0.0
 */
class BatchQueue<T> @JvmOverloads constructor(  // 设置队列处理长度
    private val handleLength: Int = DEFAULT_COUNT
) {
    // 阻塞队列
    var queue = LinkedBlockingQueue<T>()

    // 回调接口
    private var process: QueueProcess<T>? = null

    // 往队列添加数据
    fun add(t: T) {
        try {
            queue.put(t)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun start() {
        thread {
            while (!Thread.currentThread().isInterrupted) {
                try {
                    // 从队列拿出并移除队列头部的元素
                    val t = queue.take() //如果取不到数据,就阻塞线程
                    process?.processData(t)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Thread.currentThread().interrupt()
                }
            }
        }
    }

    fun setProcess(process: QueueProcess<T>?) {
        this.process = process
    }

    companion object {
        // 默认队列处理长度
        private const val DEFAULT_COUNT = 30
    }
    /**
     * 可以设置队列的处理的间隔时间和处理长度
     *
     * @param handleLength
     */
    /**
     * 设置默认的队列处理时间和数量
     */
    init {
        start()
    }
}