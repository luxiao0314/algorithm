package test;

import java.util.*;

/**
 * @Description 牛客算法top
 * @Author lux
 * @Date 2023/3/3 3:14 下午
 * @Version
 */
class CodeTest {

    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(5, null);
        ListNode listNode3 = new ListNode(4, listNode4);
        ListNode listNode2 = new ListNode(3, listNode3);
        ListNode listNode1 = new ListNode(2, listNode2);
        ListNode listNode = new ListNode(1, listNode1);

//        System.out.println(reverseBetween(listNode, 2, 4));
//        System.out.println(isPail(listNode));
//        System.out.println(reverseList(listNode).toString());
//        System.out.println(reverseKGroup(listNode, 2).toString());
//        System.out.println(removeNthFromEnd(listNode, 2).toString());
//        System.out.println(maxLength(new int[]{2, 2, 3, 4, 3, 5, 6, 7, 8}));
//        System.out.println(Arrays.toString(mySort(new int[]{1, 2, 10, 4, 5})));
//        System.out.println(GetLeastNumbers_Solution(new int[] {4,5,1,6,2,7,3,8},5));

        TreeNode root = new TreeNode(10);
        TreeNode node12 = new TreeNode(5);
        TreeNode node21 = new TreeNode(3);
        TreeNode node22 = new TreeNode(7);
        node12.left = node21;
        node12.right = node22;

        root.left = new TreeNode(12);
        root.right = node12;

        System.out.println(mirror(root));
//        System.out.println(IsBalanced_Solution(root));
//        System.out.println(lowestCommonAncestor(root, 3,7));
//        System.out.println(sumNumbers(root));
//        System.out.println(LCSS("adabkjd", "djkadj"));
//        System.out.println(inversePairs(new int[] {1,2,3,4,5,6,7,0}));
//        System.out.println(Arrays.toString(solve(8, 3, new int[]{1, 2, 3, 4, 5, 6, 7, 0})));

        //[[10,30],[20,60],[80,100],[150,180]]
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(10, 30));
        intervals.add(new Interval(20, 60));
        intervals.add(new Interval(80, 100));
        intervals.add(new Interval(150, 180));
//        System.out.println(merge(intervals));
    }

    /**
     * 反转链表
     * 双链表求解是把原链表的结点一个个摘掉，每次摘掉的链表都让他成为新的链表的头结点，然后更新新链表。
     * 1, 取出下个链表临时保存
     * 2, 将原链表一个个摘掉
     * 3, 摘掉的节点成为新的节点的头检点
     */
    public static ListNode reverseList(ListNode head) {
        //创建新链表
        ListNode newHead = null;
        while (head != null) {
            //1, 取出下个链表临时保存
            ListNode temp = head.next;
            //2, 把原链表的结点一个个摘掉
            head.next = newHead;
            //3, 每次摘掉的链表都让他成为新的链表的头结点
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    /**
     * 链表内指定区间反转
     * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)。
     * 例如：
     * {1,2,3,4,5},2,4
     * {1,4,3,2,5}
     */
    public static ListNode reverseBetween1(ListNode head, int m, int n) {
        //设置虚拟头节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;

        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        for (int i = 0; i < n - m; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return pre;
    }

    /**
     * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)。
     * 例如：
     * {1,2,3,4,5},2,4
     * {1,4,3,2,5}
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode cur = head;
        //找到m
        for (int i = 1; i < m; i++) {
            //2,3,4,5
            cur = cur.next;
        }
        //从m反转到n
        for (int i = m; i < n; i++) {
            //3,4,5
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = head.next;
            head.next = temp;
        }
        return head;
    }

    /**
     * 冒泡排序
     * 给定一个长度为 n 的数组，请你编写一个函数，返回该数组按升序排序后的结果。
     * 输入：[5,2,3,1,4]
     * 返回值：[1,2,3,4,5]
     */
    public static int[] mySort(int[] arr) {
        // write code here
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 最小的K个数
     * 给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。
     */
    public static List<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : input) {
            list.add(i);
        }
        list.sort((o1, o2) -> o1 - o2);
        return list.subList(0, k);
    }

    /**
     * 链表中的节点每k个一组翻转
     * <p>
     * 1, 获取第k个节点
     * 2, 根据第k个节点做反转
     * 3, 递归
     *
     * @param head ListNode类
     * @param k    int整型
     * @return ListNode类
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        //找到每次翻转的尾部
        ListNode tail = head;
        //遍历k次到尾部,获取k次的链表
        for (int i = 0; i < k; i++) {
            //如果不足k到了链表尾，直接返回，不翻转
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        //翻转时需要的前序和当前节点
        ListNode newNode = null;
        ListNode cur = head;
        //在到达当前段尾节点前
        while (cur != tail) {
            //翻转
            ListNode temp = cur.next;
            cur.next = newNode;
            newNode = cur;
            cur = temp;
        }
        //当前尾指向下一段要翻转的链表
        head.next = reverseKGroup(tail, k);

        return newNode;
    }

    /**
     * 最长无重复子数组
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     * 输入：[2,2,3,4,3,5,6,7,8]
     * 返回值：3
     * 说明：[4,3,5,6,7,8]是最长子数组
     * <p>
     * 可以用一个队列，把元素不停的加入到队列中，如果有相同的元素，就把队首的元素移除，这样我们就可以保证队列中永远都没有重复的元素
     */
    public static int maxLength(int[] arr) {
        Queue<Integer> list = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            while (list.contains(arr[i])) {
                list.poll();
            }
            list.add(arr[i]);
        }
        System.out.println(list);
        return list.size();
    }

    /**
     * 最长公共子序列
     * 给定两个字符串 s1 和 s2，长度为m和n 。求两个字符串最长公共子序列的长度。
     */
    public static int LCS(String s1, String s2) {
        // write code here
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(Arrays.deepToString(dp));
        return dp[s1.length()][s2.length()];
    }

    public static String LCSS(String s1, String s2) {
        // write code here
        String[][] a = new String[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    a[i][j] = "";
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    a[i][j] = a[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    a[i][j] = a[i - 1][j].length() > a[i][j - 1].length() ? a[i - 1][j] : a[i][j - 1];
                }
            }
        }

        System.out.println(Arrays.deepToString(a));
        return Objects.equals(a[s1.length()][s2.length()], "") ? "-1" : a[s1.length()][s2.length()];
    }


    /**
     * 链表中环的入口结点
     * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
     * hash法，记录第一次重复的结点, 当set中包含结点，说明第一次出现重复的结点，即环的入口结点
     */
    public ListNode entryNodeOfLoop(ListNode pHead) {
        // 使用set来记录出现的结点
        HashSet<ListNode> set = new HashSet<>();
        while (pHead != null) {
            // 当set中包含结点，说明第一次出现重复的结点，即环的入口结点
            if (set.contains(pHead)) {
                return pHead;
            }
            // set中加入未重复的结点
            set.add(pHead);
            pHead = pHead.next;
        }
        return null;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
     *
     * @param head ListNode类
     * @param n    int整型
     * @return ListNode类
     * <p>
     * 关键点: p.next = p.next.next;  next.next即跳过了节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // write code here
        int length = 0;
        ListNode p = head;
        ListNode q = head;
        // 获取链表的长度
        while (head != null) {
            length++;
            head = head.next;
        }

        int i = 0;
        while (p != null) {
            i++;
            if (i == length - n) {
                // 删除结点
                p.next = p.next.next;
            }
            p = p.next;
        }
        return q;
    }

    /**
     * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回
     * 计算两个数之和
     *
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve(String s, String t) {
        // write code here
        int num = Integer.parseInt(s) + Integer.parseInt(t);
        return Integer.toString(num);
    }

//    /**
//     * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
//     * 输入：{8,6,10,5,7,9,11}
//     * 返回值：[[8],[10,6],[5,7,9,11]]
//     */
//    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
//
//    }

    /**
     * 输入一棵节点数为 n 二叉树，判断该二叉树是否是平衡二叉树。
     * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
     * 平衡二叉树（Balanced Binary Tree），具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
     *
     * @param root
     * @return
     */
    public static boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;

        System.out.println(root);
        //判断左子树和右子树是否符合规则，且两边深度不能超过2
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right) && Math.abs(deep(root.left) - deep(root.right)) < 2;
    }

    //判断二叉树深度,可以获取最大深度
    public static int deep(TreeNode root) {
        if (root == null) return 0;
        return Math.max(deep(root.left), deep(root.right)) + 1;
    }

    /**
     * 在二叉树中找到两个节点的最近公共祖先
     * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
     */
    public static int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        // write code here
        return helper(root, o1, o2).root;
    }

    public static TreeNode helper(TreeNode root, int o1, int o2) {
        if (root == null || root.root == o1 || root.root == o2)
            return root;
        TreeNode left = helper(root.left, o1, o2);
        TreeNode right = helper(root.right, o1, o2);
        //如果left为空，说明这两个节点在root结点的右子树上，我们只需要返回右子树查找的结果即可
        if (left == null)
            return right;
        //同上
        if (right == null)
            return left;
        //如果left和right都不为空，说明这两个节点一个在root的左子树上一个在root的右子树上，
        //我们只需要返回cur结点即可。
        return root;
    }

//    /**
//     * 输出二叉树的右视图
//     * 请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
//     */
//    public int[] solve (int[] xianxu, int[] zhongxu) {
//        // write code here
//    }

    /**
     * 二叉树根节点到叶子节点的所有路径和
     * 给定一个二叉树的根节点root，该树的节点值都在数字\ 0-9 0−9 之间，每一条从根节点到叶子节点的路径都可以用一个数字表示。
     * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
     * 2.叶子节点是指没有子节点的节点
     * 3.路径只能从父节点到子节点，不能从子节点到父节点
     * 4.总节点数目为n
     * <p>
     * 例如根节点到叶子节点的一条路径是1\to 2\to 31→2→3,那么这条路径就用\ 123 123 来代替。
     * 找出根节点到叶子节点的所有路径表示的数字之和
     */
    public static int sumNumbers(TreeNode root) {
        // write code here

        //如果根节点是空，直接返回0即可
        if (root == null)
            return 0;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> valueStack = new Stack<>();
        int res = 0;

        nodeStack.add(root);
        valueStack.add(root.root);

        while (!nodeStack.isEmpty()) {
            Integer value = valueStack.pop();
            TreeNode treeNode = nodeStack.pop();

            //说明没有子节点
            if (treeNode.left == null && treeNode.right == null) {
                res += value;
            } else {
                if (treeNode.right != null) {
                    nodeStack.add(treeNode.right);
                    //计算路径的root值
                    valueStack.add(value * 10 + treeNode.right.root);
                }

                if (treeNode.left != null) {
                    nodeStack.add(treeNode.left);
                    valueStack.add(value * 10 + treeNode.left.root);
                }
            }
        }

        return res;
    }

    /**
     * 最大数
     *
     * @param nums int整型一维数组
     * @return string字符串
     */
    public String solve(int[] nums) {
        // write code here
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            strings.add(String.valueOf(nums[i]));
        }

        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    /**
     * 寻找峰值
     * 给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
     * 1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
     * 2.假设 nums[-1] = nums[n] = -\infty−∞
     * 3.对于所有有效的 i 都有 nums[i] != nums[i + 1]
     * 4.你可以使用O(logN)的时间复杂度实现此问题吗？
     * <p>
     * 因为题目将数组边界看成最小值，而我们只需要找到其中一个波峰，因此只要不断地往高处走，一定会有波峰。那我们可以每次找一个标杆元素，将数组分成两个区间，每次就较高的一边走，因此也可以用分治来解决，而标杆元素可以选择区间中点。
     *
     * @param nums [2,4,1,2,7,8,4]
     * @return
     */
    public int findPeakElement(int[] nums) {
        // write code here

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            //右边小,左边找
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        //右边峰值,left位左边峰值
        return right;
    }

//    /**
//     * 兑换零钱(一)
//     * 最少货币数
//     * @param arr int整型一维数组 the array
//     * @param aim int整型 the target
//     * @return int整型
//     */
//    public int minMoney (int[] arr, int aim) {
//        // write code here
//    }

    /**
     * 数组中的逆序对
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P mod 1000000007
     */
    public static int inversePairs(int[] array) {
        int p = 1000000007;
        int num = 0;

        for (int i = 0; i < array.length; i++) {
            //j取值递增,即取i后面一个值
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    num++;
                }
            }
        }

        return num % p;
    }

    /**
     * 判断一个链表是否为回文结构
     * 给定一个链表，请判断该链表是否为回文结构。
     * 回文是指该字符串正序逆序完全一致。
     * 首先初始化一个list列表；
     * 遍历链表，将链表中的值转移至list中；
     * 在list中通过比较头尾的值来判断链表是否为回文结构。
     * <p>
     * 或者 将慢指针之后的部分进行反转，再与前半部分进行比较。
     * <p>
     * 输入：{1,2,2,1}
     * 返回值：true
     * 说明：1->2->2->1
     */
    public static boolean isPail(ListNode head) {
        // write code here
        ArrayList<ListNode> listNodes = new ArrayList<>();

        while (head != null) {
            listNodes.add(head);
            head = head.next;
        }

        int start = 0;
        int end = listNodes.size();

        while (start < end) {

            if (listNodes.get(start).head != listNodes.get(end - 1).head) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    /**
     * 输入一颗二叉树的根节点root和一个整数expectNumber，找出二叉树中结点值的和为expectNumber的所有路径。
     * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
     * 2.叶子节点是指没有子节点的节点
     * 3.路径只能从父节点到子节点，不能从子节点到父节点
     * 4.总节点数目为n
     * <p>
     * [[10,5,7],[10,12]]
     * //        10
     * //       / \
     * //      12  5
     * //         /  \
     * //        3   7
     */
    private static final ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    private static LinkedList<Integer> path = new LinkedList<>();

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode noot, int expectNumber) {
        dfs(noot, expectNumber);
        return res;
    }

    public static void dfs(TreeNode root, int tar) {
        if (root == null) {
            return;
        }
        //将root节点放入路径集合
        path.add(root.root);
        //更新目标值，每放入一个节点，目标值应该相应减去对应节点的值，直到目标值为0
        tar -= root.root;
        //如果目标值减到了0 && 左节点为空 && 右节点为空 证明树已遍历完，此路径为目标路径
        if (tar == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }
        // 递归左右子树
        dfs(root.left, tar);
        dfs(root.right, tar);
        //删除当前节点，在回溯过程中，此节点不在新路径上
        path.removeLast();
    }

    /**
     * 不同路径的数目(一)
     * 一个机器人在m×n大小的地图的左上角（起点）。
     * 机器人每次可以向下或向右移动。机器人要到达地图的右下角（终点）。
     * 可以有多少种不同的路径从起点走到终点？
     * <p>
     * 备注：m和n小于等于100,并保证计算结果在int范围内
     */
    public int uniquePaths(int m, int n) {
        // 起点（1，1） 这里为什么是（1，1）呢？其实是一样的，我们上面的方法定义了（0，0）为起点，那么终点就为（m-1，n-1）
        // 这里起点为（1，1）那么终点就为 （m，n）
        if (m == 1 || n == 1)
            return 1;
        // 终点（m，n）
        return uniquePaths(m, n - 1) + uniquePaths(m - 1, n);
    }

    /**
     * 合并区间
     * 输入：[[10,30],[20,60],[80,100],[150,180]]
     * 返回值：[[10,60],[80,100],[150,180]]
     * <p>
     * step 1：既然要求重叠后的区间按照起点位置升序排列，我们就将所有区间按照起点位置先进行排序。使用sort函数进行排序，重载比较方式为比较interval结构的start变量。
     * step 2：排序后的第一个区间一定是起点值最小的区间，我们将其计入返回数组res，然后遍历后续区间。
     * step 3：后续遍历过程中，如果遇到起点值小于res中最后一个区间的末尾值的情况，那一定是重叠，取二者最大末尾值更新res中最后一个区间即可。
     * step 4：如果遇到起点值大于res中最后一个区间的末尾值的情况，那一定没有重叠，后续也不会有这个末尾的重叠区间了，因为后面的起点只会更大，因此可以将它加入res。
     */
    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {

        ArrayList<Interval> resList = new ArrayList<>();

        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                } else {
                    return o1.end - o2.end;
                }
            }
        });

        //添加第一个区间,第一个区间是最小的
        resList.add(intervals.get(0));
        int count = 0;

        for (int i = 1; i < intervals.size(); i++) {
            Interval orgin = intervals.get(i);
            Interval res = resList.get(count);

            if (orgin.start > res.end) {
                resList.add(orgin);
                count++;
            } else {
                resList.remove(count);
                //表示重叠, 取二者最大末尾值更新res中最后一个区间
                resList.add(new Interval(res.start, orgin.end));
            }
        }

        return resList;
    }

    /**
     * 旋转数组
     * 一个长度为nnn的数组，将数组整体循环右移mmm个位置（mmm可能大于nnn）
     * 循环右移即最后mmm个元素放在数组最前面，前n−mn-mn−m个元素依次后移
     * 不能使用额外的数组空间
     */
    /**
     * 旋转数组
     *
     * @param n int整型 数组长度
     * @param m int整型 右移距离
     * @param a int整型一维数组 给定数组
     * @return int整型一维数组
     * <p>
     * //取余，因为每次长度为n的旋转数组相当于没有变化
     * //第一次逆转全部数组元素
     * //第二次只逆转开头m个
     * //第三次只逆转结尾m个
     */
    public static int[] solve(int n, int m, int[] a) {
        // write code here
        m = m % n;

        reverseArray(a, 0, n-1);

        reverseArray(a, 0, m-1);

        reverseArray(a, m, n-1);

        return a;
    }

    private static void reverseArray(int[] a, int start, int end) {
        while (start < end) {
            swap(a, start++ , end--);
        }
    }

    private static void swap(int[] a, int start, int end) {
        int temp = a[start];
        a[start] = a[end];
        a[end] = temp;
    }

    /**
     * 二叉树的镜像
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     *
     * {8,6,10,5,7,9,11}
     * {8,10,6,11,9,7,5}
     */
    public static TreeNode mirror(TreeNode pRoot) {
        // write code here
        if(pRoot == null) return null;

        Stack<TreeNode> treeNodes = new Stack<>();

        treeNodes.add(pRoot);

        while (!treeNodes.isEmpty()) {

            TreeNode pop = treeNodes.pop();

            if (pop.left!=null) treeNodes.add(pop.left);
            if (pop.right!=null) treeNodes.add(pop.right);

            TreeNode temp = pop.left;
            pop.left = pop.right;
            pop.right = temp;
        }

        return pRoot;
    }


}

