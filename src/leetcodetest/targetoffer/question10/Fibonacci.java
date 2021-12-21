package leetcodetest.targetoffer.question10;

/**
 * @Description: 剑指 Offer 10. 斐波那契数列
 * @Author tinytongtong
 * @Date 2020/9/5 3:46 PM
 * @Version
 */
public class Fibonacci {
    /**
     * 递归实现fibonacci数列
     * 存在大量重复计算
     *
     * @param n
     * @return
     */
    private static int fibonacciByRecursion(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacciByRecursion(n - 1) + fibonacciByRecursion(n - 2);
    }

    /**
     * 迭代实现fibonacci数列
     * 从下往上计算
     *
     * @param n
     * @return
     */
    private static int fibonacciByIteration(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int result = 0;
        int index = 2;
        int one = 0;
        int two = 1;
        while (index <= n) {
            result = two + one;
            one = two;
            two = result;
            index++;
        }
        return result;
    }

    //写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）
    public static int fib(int n) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void main(String[] args) {
//        int result = fibonacciByRecursion(9);
//        System.out.println(result);
//
//        int result1 = fibonacciByIteration(9);
//        System.out.println(result1);

        int fib = fib(9);
        System.out.println(fib);
    }
}
