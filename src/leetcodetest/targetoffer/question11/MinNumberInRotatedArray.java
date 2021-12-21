package leetcodetest.targetoffer.question11;

/**
 * @Description: https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * 剑指 Offer 11. 旋转数组的最小数字
 * @Author tinytongtong
 * @Date 2020/9/5 6:23 PM
 * @Version
 */
public class MinNumberInRotatedArray {

    private static int minArray2(int[] numbers) {
        if (numbers == null) {
            return -1;
        }
        int index1 = 0;
        int index2 = numbers.length - 1;
        int indexMid = index1;
        while (numbers[index1] >= numbers[index2]) {
            if (index2 - index1 == 1) {
                indexMid = index2;
                break;
            }
            indexMid = (index1 + index2) / 2;
            // 如果index1、index2和indexMid三个位置的数据相等，则只能顺序查找了
            if (numbers[index1] == numbers[index2] && numbers[index2] == numbers[indexMid]) {
                return minInOrder(numbers, index1, index2);
            }

            if (numbers[indexMid] >= numbers[index1]) {
                index1 = indexMid;
            } else if (numbers[indexMid] <= numbers[index2]) {
                index2 = indexMid;
            }
        }
        return numbers[indexMid];
    }

    private static int minInOrder(int[] numbers, int start, int end) {
        int result = numbers[start];
        for (int i = start + 1; i <= end; i++) {
            if (result > numbers[i]) {
                result = numbers[i];
            }
        }
        return result;
    }

    /**
     * 二分法
     *
     * 首先，创建两个指针 leftleft, rightright 分别指向 numbersnumbers 首尾数字，然后计算出两指针之间的中间索引值 middlemiddle，然后我们会遇到以下三种情况：
     *
     * middlemiddle > rightright ：代表最小值一定在 middlemiddle 右侧，所以 leftleft 移到 middle + 1middle+1 的位置。
     * middlemiddle < rightright ：代表最小值一定在 middlemiddle 左侧或者就是 middlemiddle，所以 rightright 移到 middlemiddle 的位置。
     * middlemiddle 既不大于 leftleft 指针的值，也不小于 rightright 指针的值，代表着 middlemiddle 可能等于 leftleft 指针的值，或者 rightright 指针的值，我们这时候只能让 rightright 指针递减，来一个一个找最小值了。
     *
     * 原数组是递增的，经过旋转以后会把一部分小数据转移到后面去
     * @param numbers
     * @return
     */
    public static int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] < numbers[high]) {
                high = mid;
            } else if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{3, 4, 5, 1, 2};
        System.out.println(minArray(arrays));

//        int[] arrays1 = new int[]{2, 2, 2, 0, 1};
//        System.out.println(minArray(arrays1));
//
//        int[] arrays2 = new int[]{1, 0, 1, 1, 1};
//        System.out.println(minArray(arrays2));
//
//        int[] arrays3 = new int[]{1, 1, 1, 0, 1};
//        System.out.println(minArray(arrays3));
    }
}
