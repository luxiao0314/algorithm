package test


/**
 * @Description
 * @Author lux
 * @Date 2021/12/16 11:24 上午
 * @Version
 */
object StringTest {

    @JvmStatic
    fun main(args: Array<String>) {
        println("leftRotateString1: " + leftRotateString1("abcXYZdef", 3))
        println("permutation: " + permutation("abc").contentToString())
    }
}

//对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
/*******************************************************************************************/
private fun leftRotateString(s: String, n: Int): String {
    return s.substring(n) + s.substring(0, n)
}

private fun leftRotateString1(s: String, n: Int): String {
    val left = StringBuilder()
    val right = StringBuilder()
    s.forEachIndexed { index, c ->
        if (index >= n) {
            right.append(c)
        } else {
            left.append(c)
        }
    }

    return right.toString() + left.toString()
}

//给定一个句子，将句子中的单词进行翻转，注意，单词内部的字符顺序不改变，改变的是单词与单词之间的顺序，比如“I am a coder.”,翻转之后变成“coder. a am I”。
/*******************************************************************************************/
fun reverseSentence(str: String?): String? {
    if (str != null && str.isNotEmpty() && str != "   ") {
        val strs = str.split(" ".toRegex()).toTypedArray()
        val stringBuilder = StringBuilder()
        for (i in strs.indices.reversed()) {
            stringBuilder.append(strs[i])
            stringBuilder.append(if (i == 0) "" else " ")
        }
        return stringBuilder.toString()
    }
    return str
}

//字符串的排列
//https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
//输入一个字符串，打印出该字符串中字符的所有排列。
//你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//给定一个字符串，输出所有不同字符组合
/*******************************************************************************************/
var chars: CharArray = charArrayOf()
val lists = HashSet<String>()
//"abc"
fun permutation(s: String): Array<String> {
    chars = s.toCharArray()
    dfs("", BooleanArray(s.length))
    return lists.toTypedArray()
}

fun dfs(s: String, visited: BooleanArray) {
    println(s)
    if (s.length == chars.size) {
        lists.add(s)
        return
    }
    for (i in chars.indices) {
        //取出重复元素
        if (visited[i]) continue

        visited[i] = true
        dfs(s + chars[i].toString(), visited)
        visited[i] = false
    }
}

////////////////////////////////////////////////////////////////////

//为了让递归函数添加结果方便，定义到函数之外，这样无需带到递归函数的参数列表中
var list: MutableList<String> = ArrayList()

//同；但是其赋值依赖c，定义声明分开
var c: CharArray = charArrayOf()
fun permutation2(s: String): Array<String> {
    c = s.toCharArray()
    //从第一层开始递归
    dfs(0)
    //将字符串数组ArrayList转化为String类型数组
    return list.toTypedArray()
}

private fun dfs(x: Int) {
    //当递归函数到达第三层，就返回，因为此时第二第三个位置已经发生了交换
    if (x == c.size - 1) {
        //将字符数组转换为字符串
        list.add(String(c))
        return
    }
    //为了防止同一层递归出现重复元素
    val set = HashSet<Char>()
    //这里就很巧妙了,第一层可以是a,b,c那么就有三种情况，这里i = x,正巧dfs(0)，正好i = 0开始
    // 当第二层只有两种情况，dfs(1）i = 1开始

    for (i in x until c.size) {
        //发生剪枝，当包含这个元素的时候，直接跳过
        if (set.contains(c[i])) {
            continue
        }
        set.add(c[i])
        //交换元素，这里很是巧妙，当在第二层dfs(1),x = 1,那么i = 1或者 2， 不是交换1和1，要就是交换1和2
        swap(i, x)
        //进入下一层递归
        dfs(x + 1)
        //返回时交换回来，这样保证到达第1层的时候，一直都是abc。这里捋顺一下，开始一直都是abc，那么第一位置总共就3个交换
        //分别是a与a交换，这个就相当于 x = 0, i = 0;
        //     a与b交换            x = 0, i = 1;
        //     a与c交换            x = 0, i = 2;
        //就相当于上图中开始的三条路径
        //第一个元素固定后，每个引出两条路径,
        //     b与b交换            x = 1, i = 1;
        //     b与c交换            x = 1, i = 2;
        //所以，结合上图，在每条路径上标注上i的值，就会非常容易好理解了
        swap(i, x)
    }
}

private fun swap(i: Int, x: Int) {
    val temp = c[i]
    c[i] = c[x]
    c[x] = temp
}
