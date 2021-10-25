package test


/**
 * @Description
 * @Author lux
 * @Date 2021/10/15 5:19 下午
 * @Version
 */
object Test {
    @JvmStatic
    fun main(args: Array<String>) {
//        val nums = intArrayOf(5, 2, 1, 3)
//        //冒泡排序
//        bubbleSort(nums)
//        println(nums.contentToString())
//
//        //二分查找
//        println(search(intArrayOf(5, 2, 1, 3), 1))

        println(minNumber(intArrayOf(3, 30, 34, 5, 9)))
    }

    /**
     * 方法名：bubbleSort 说明：冒泡排序 时间复杂度O(N^2)
     */
    private fun bubbleSort(nums: IntArray) {
        for (i in nums.indices) for (j in i + 1 until nums.size) {
            if (nums[i] > nums[j]) {
//                swap(nums, j, j + 1)
                val temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
            }
        }
    }

    private fun quickSort(nums: IntArray, left: Int, right: Int) {
        if (left > right) return
        val key = nums[left]
        var l = left
        var r = right
        while (l < r) {
            while (nums[r] >= key && l < r) r--
            while (nums[l] <= key && l < r) l++
            if (l < r) swap(nums, r, l)
        }
        nums[left] = nums[r]
        nums[r] = key
        quickSort(nums, left, r - 1)
        quickSort(nums, r + 1, right)
    }

    private fun swap(nums: IntArray, i: Int, j: Int): IntArray? {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
        return nums
    }

    /**
     * 二分查找
     * 过程：
     * 设定左右指针
     * 找出中间位置，并判断该位置值是否等于 target
     * nums[mid] == target 则返回该位置下标
     * nums[mid] > target 则右侧指针移到中间
     * nums[mid] < target 则左侧指针移到中间
     */
    private fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                nums[mid] == target -> return mid
                nums[mid] > target -> right = mid - 1
                else -> left = mid + 1
            }
        }
        return -1
    }

    /**
     * [3,30,34,5,9]
     * 输入一个正整数数组，将它们连接起来排成一个数，输出能排出的所有数字中最小的一个。例如输入数组{32, 321}，则输出这两个能排成的最小数字32132。
     * 规定 排序判断规则 为：

     * 若拼接字符串 x + y > y + xx+y>y+x ，则 xx “大于” yy ；
     * 反之，若 x + y < y + xx+y<y+x ，则 xx “小于” yy ；
     * xx “小于” yy 代表：排序完成后，数组中 xx 应在 yy 左边；“大于” 则反之。
     */
    private fun minNumber(nums: IntArray): String? {
        val list = mutableListOf<String>()
        for (num in nums) {
            list.add(num.toString())
        }
        println(list)
        list.sortWith { o1: String, o2: String -> (o1 + o2).compareTo(o2 + o1) }
        println(list)
        return java.lang.String.join("", list)
    }
}