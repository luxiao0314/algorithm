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
//
//    println(nums.contentToString())

//    println(getLeastNumbers1(intArrayOf(4, 5, 1, 6, 2, 7, 3, 8), 4).contentToString())

//    println(majorityElement2(intArrayOf(4, 1, 1, 1, 2, 2, 7, 3, 3)))

    println("threeSum: " + threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))

    println("twoSum: " + twoSum(intArrayOf(2, 7, 11, 15), 9).contentToString())
}

//调整数组顺序使奇数位于偶数前面
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

//最小的k个数
/*************************************************************************************************************************/
fun getLeastNumbers1(nums: IntArray, k: Int): IntArray {
    nums.sort()
    return nums.filterIndexed { index, _ -> index < k }.toIntArray()
}

//数组中次数出现超过一半的数字
/*************************************************************************************************************************/
fun moreThanHalfNumSolution(array: IntArray?): Int {
    return if (array == null || array.isEmpty()) {
        0
    } else {
        var num = array[0]
        var count = 1
        for (i in 1 until array.size) {
            if (array[i] == num) {
                count++
            } else {
                count--
            }
            if (count == 0) {
                num = array[i]
                count = 1
            }
        }
        count = 0
        for (i in array.indices) {
            if (array[i] == num) {
                count++
            }
        }
        if (count > array.size / 2) {
            num
        } else {
            0
        }
    }
}

//计算
fun majorityElements(nums: IntArray): Int {
    var x = 0
    var votes = 0
    var count = 0
    for (num in nums) {
        if (votes == 0) x = num
        votes += if (num == x) 1 else -1
    }
    // 验证 x 是否为众数
    for (num in nums) if (num == x) count++
    return if (count > nums.size / 2) x else 0 // 当无众数时返回 0
}

//1、HashMap
fun majorityElement1(nums: IntArray): Int {
    var res = 0
    val map = HashMap<Int, Int>()
    nums.map { i ->
        map[i] = (map[i] ?: 0) + 1
    }
    for (num in nums) {
        if (map[num]!! > nums.size / 2) {
            res = num
        }
    }
    return res
}

//2、数组排序法：将数组 nums 排序，数组中点的元素 一定为众数
fun majorityElement2(nums: IntArray): Int {
    Arrays.sort(nums)
    println(nums.contentToString())
    return nums[nums.size / 2]
}

/**
 * 1.初始化： 票数统计 votes = 0 ， 众数 x；
 * 2.循环： 遍历数组 nums 中的每个数字 num ；
 *      当 票数 votes 等于 0 ，则假设当前数字 num 是众数；
 *      当 num = x 时，票数 votes 自增 1 ；当 num != x 时，票数 votes 自减 1 ；
 * 3.返回值： 返回 x 即可；
 */
//3、最优解：摩尔投票法
fun majorityElement(nums: IntArray): Int {
    //众数 x
    var x = 0
    //票数统计
    var votes = 0
    for (num in nums) {
        if (votes == 0) {
            x = num
        }
        if (num == x) {
            votes += 1
        } else {
            votes -= 1
        }
    }

    return x
}


//数组中重复的数字,只需要找出数组中任意一个重复的数字，因此遍历数组，遇到重复的数字即返回
/*************************************************************************************************************************/
fun findRepeatNumber(nums: IntArray): Int {
    val set: MutableSet<Int> = HashSet()
    var repeat = -1
    for (num in nums) {
        if (!set.add(num)) {
            repeat = num
            break
        }
    }
    return repeat
}

//三数之和
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
//注意：答案中不可以包含重复的三元组。
//https://leetcode-cn.com/problems/3sum/
/*************************************************************************************************************************/
fun threeSum(nums: IntArray): List<List<Int>> { // 总时间复杂度：O(n^2)
    val ans: MutableList<List<Int>> = ArrayList()
    if (nums.size <= 2) return ans
    Arrays.sort(nums) // O(nlogn)

    for (i in nums.indices) { // O(n^2)
        if (nums[i] > 0) break // 第一个数大于 0，后面的数都比它大，肯定不成立了
        if (i > 0 && nums[i] == nums[i - 1]) continue  // 去掉重复情况

        var left = i + 1
        var right = nums.size - 1
        while (left < right) {
            if (nums[left] + nums[right] + nums[i] == 0) {
                ans.add(ArrayList(listOf(nums[i], nums[left], nums[right])))

                // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                left++
                right-- // 首先无论如何先要进行加减操作

                // 2层去重，111333这种情况，只需要算一次，i,j 移动到 最内部 1，3
                while (left < right && nums[left] == nums[left - 1]) left++
                while (left < right && nums[right] == nums[right + 1]) right--
            } else if (nums[left] + nums[right] + nums[i] < 0) {
                left++
            } else {  // nums[left] + nums[right] > target
                right--
            }
        }
    }
    return ans
}

fun threeSum2(nums: IntArray): List<List<Int>> {
    Arrays.sort(nums)
    val lists: MutableSet<List<Int>> = HashSet()
    for (i in nums.indices) {
        var left = i + 1
        var right = nums.size - 1
        while (left < right) {
            if (nums[left] + nums[right] + nums[i] == 0) {
                lists.add(ArrayList(listOf(nums[i], nums[left], nums[right])))
                left++
                right--
            } else if (nums[i] + nums[left] + nums[right] < 0) {
                left++
            } else {
                right--
            }
        }
    }
    return ArrayList(lists)
}

//两数之和 nums = [2,7,11,15], target = 9
//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
/*************************************************************************************************************************/
fun twoSum(nums: IntArray, target: Int): IntArray {
    val n = nums.size
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (nums[i] + nums[j] == target) {
                return intArrayOf(i, j)
            }
        }
    }
    return IntArray(0)
}

fun twoSum2(nums: IntArray, target: Int): IntArray {
    val mutableMap: MutableMap<Int, Int> = HashMap()
    for (i in nums.indices) {
        val key = target - nums[i]
        if (mutableMap.containsKey(key)) {
            return intArrayOf(mutableMap[key]!!, i)
        }
        //放入nums中num和索引
        mutableMap[nums[i]] = i
    }
    return IntArray(0)
}