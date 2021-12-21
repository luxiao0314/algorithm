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
        println(leftRotateString1("abcXYZdef", 3))
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
}