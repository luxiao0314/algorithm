package test


/**
 * @Description
 * @Author lux
 * @Date 2021/11/10 2:59 下午
 * @Version
 */
fun main() {
    val nums = intArrayOf(2, 5, 1, 4)

    reOrderArray3(nums)

    println(nums.contentToString())
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
fun reOrderArray3(array: IntArray) {
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