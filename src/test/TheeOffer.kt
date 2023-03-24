package test

import java.util.*


/**
 * @Description
 * @Author lux
 * @Date 2021/12/21 3:28 下午
 * @Version
 */
object TheeOffer {

    @JvmStatic
    fun main(args: Array<String>) {
        val preorder = intArrayOf(3, 9, 8, 5, 4, 10, 20, 15, 7)
        val inorder = intArrayOf(4, 5, 8, 10, 9, 3, 15, 20, 7)
        val result = buildTree3(preorder, inorder)
        println(result)
    }
}

//重建二叉树
/*************************************************************************************************************************/

/**
 * 知识点：

前序遍历列表：第一个元素永远是 【根节点 (root)】
中序遍历列表：根节点 (root)【左边】的所有元素都在根节点的【左分支】，【右边】的所有元素都在根节点的【右分支】
算法思路：

通过【前序遍历列表】确定【根节点 (root)】
将【中序遍历列表】的节点分割成【左分支节点】和【右分支节点】
递归寻找【左分支节点】中的【根节点 (left child)】和 【右分支节点】中的【根节点 (right child)】
 */

//递归算法1

//例如输入前序遍历序列preorder = [3, 9, 8, 5, 4, 10, 20, 15, 7]和inorder = [4, 5, 8, 10, 9, 3, 15, 20, 7]
//preorder前序遍历结果,inorder中序遍历结果
//前序排列顺序为 根-左-右，中序排列为左-根-右。
//        3
//       / \
//      9  20
//     /  /  \
//    8  15   7
//   / \
//  5  10
// /
//4
fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    //重建一开始是真的有点绕啊，但是其实理清了也还好，无非就是通过每次传进去的两个数组来构建新的二叉树
    //三部曲试一下吧
    //首先是啥时候结束
    //当传入的数组长度为0的时候就该结束了
    val length = preorder.size
    if (length == 0) return null
    //应该通过传进来的两个数组，根据他们之间的关系，来构建子树，并把新的参数传递下去
    //那么现在传进来了两个数组，一个是前序遍历的数组，那么首位必定是根节点，先拿下
    val rootValue = preorder[0] //前序根节点 3
    //有了数组的根节点以后我们自然需要的是左右子树，根据中序遍历的特点，只要找到了根节点，那么他两边自然就是他的左右子树了
    //那么先在中序遍历的数组中找到根节点,并把它的坐标保存起来
    var rootIndex = 0   //中序根节点索引,为5
    for (i in 0 until length) {
        if (inorder[i] == rootValue) {
            rootIndex = i
            break
        }
    }
    //至此我们已经找到了根和左右子树，那么设置一下根节点的左右节点，自然就是递归后返回的左右子树的根节点
    //先把我们的根节点创建出来
    val root = TreeNode(rootValue)
    //查找前序中左子树; 查找中序中左子树 一层一层查找
    root.left = buildTree(preorder.copyOfRange(1, rootIndex + 1), inorder.copyOfRange(0, rootIndex))
    //查找前序中右子树; 查找中序中右子树
    root.right = buildTree(preorder.copyOfRange(1 + rootIndex, length), inorder.copyOfRange(rootIndex + 1, length))
    //至于传入的数组如何分割可以自己通过测试用例来分析，这里不赘述了
    //至此节点创建，左右节点的设置均已完成，返回root

    println("root:  $root")
    return root
}

//////////////////////////////////////////////////////////////////////////////////////////////

//迭代法  preorder = [3, 9, 8, 5, 4, 10, 20, 15, 7]和inorder = [4, 5, 8, 10, 9, 3, 15, 20, 7]
//前序遍历，从根节点root开始，只要有左子节点，就一直会往左下方走，直到最左下角。 而中序遍历，是从最左下角往上（示例中的4-5-8-9-3），如果碰到节点有右子节点，则会转向（示例中的8-10）。
//因此，代码中的if块是用前序数组一直构建左子树，如果碰到了inorder[inorderIndex]，表示到了左下角，这时就需要往上走并处理右子树，也就是while代码块。
//        3
//       / \
//      9  20
//     /  /  \
//    8  15   7
//   / \
//  5  10
// /
//4
fun buildTree3(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty()) {
        return null
    }
    val root = TreeNode(preorder[0])
    val stack: Deque<TreeNode> = LinkedList()
    stack.push(root)
    var inorderIndex = 0
    for (i in 1 until preorder.size) {
        val preorderVal = preorder[i]
        var node = stack.peek()
        //即左子树遍历完了
        if (node.root != inorder[inorderIndex]) {
            node.left = TreeNode(preorderVal)
            stack.push(node.left)
        } else {
            //即pop出所有左子树,将剩下数据添加到右子树
            while (!stack.isEmpty() && stack.peek().root == inorder[inorderIndex]) {
                node = stack.pop()
                inorderIndex++
            }
            node.right = TreeNode(preorderVal)
            stack.push(node.right)
        }
    }
    return root
}

//递归算法2
////////////////////////////////////////////////////////////////////////////////////
var map = HashMap<Int, Int>() //标记中序遍历
var preorder: IntArray = intArrayOf() //保留的先序遍历，方便递归时依据索引查看先序遍历的值
fun buildTree2(preorders: IntArray, inorder: IntArray): TreeNode? {
    preorder = preorders
    //将中序遍历的值及索引放在map中，方便递归时获取左子树与右子树的数量及其根的索引
    for (i in inorder.indices) {
        map[inorder[i]] = i
    }
    //三个索引分别为
    //当前根的的索引
    //递归树的左边界，即数组左边界
    //递归树的右边界，即数组右边界
    return recur(0, 0, inorder.size - 1)
}

fun recur(pre_root: Int, in_left: Int, in_right: Int): TreeNode? {
    if (in_left > in_right) return null // 相等的话就是自己
    val root = TreeNode(preorder[pre_root]) //获取root节点
    val idx = map[preorder[pre_root]]!! //获取在中序遍历中根节点所在索引，以方便获取左子树的数量
    //左子树的根的索引为先序中的根节点+1
    //递归左子树的左边界为原来的中序in_left
    //递归左子树的右边界为中序中的根节点索引-1
    root.left = recur(pre_root + 1, in_left, idx - 1)
    //右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
    //递归右子树的左边界为中序中当前根节点+1
    //递归右子树的右边界为中序中原来右子树的边界
    root.right = recur(pre_root + (idx - in_left) + 1, idx + 1, in_right)
    return root
}