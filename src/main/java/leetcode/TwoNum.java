package leetcode;

/**
 * @Description:
 * 
 *               给出两个 非空
 *               的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 *               如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 *               您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *               示例：
 *
 *               输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *               输出：7 -> 0 -> 8
 *               原因：342 + 465 = 807
 *
 *               来源：力扣（LeetCode）
 *               链接：https://leetcode-cn.com/problems/add-two-numbers
 *               著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: shenpeng
 * @Date: 2019-12-27
 */
public class TwoNum {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int length1 = getListLength(l1);
        int length2 = getListLength(l2);

        if (length1 > length2) {
            expendList(l2, length1 - length2);
        } else {
            expendList(l1, length2 - length1);
        }
        //        ListNode finNode = combineTwoList(null, l1, l2, 0);
        //        return getReverseNode(finNode);
        return combineTwoList(null, l1, l2, 0);
    }

    /**
     * 联结两个链表
     * 
     * @param [next, l1, l2, addVal]
     * @return leetcode.ListNode
     * @Author: shenpeng
     * @Date: 2019-12-28
     */
    public ListNode combineTwoList(ListNode next, ListNode l1, ListNode l2, int addVal) {
        int sum = l1.val + l2.val + addVal;
        ListNode newNode;
        if (sum >= 10) {
            newNode = new ListNode(sum - 10);
            addVal = 1;
        } else {
            newNode = new ListNode(sum);
            addVal = 0;
        }

        if (next == null) {
            if (l1.next != null && l2.next != null) {
                combineTwoList(newNode, l1.next, l2.next, addVal);
                return newNode;
            } else {
                if (addVal == 1) {
                    ListNode finNode = new ListNode(1);
                    newNode.next = finNode;
                }
                return newNode;
            }
        } else {
            next.next = newNode;
            if (l1.next != null && l2.next != null) {
                combineTwoList(newNode, l1.next, l2.next, addVal);
            } else {
                if (addVal == 1) {
                    ListNode finNode = new ListNode(1);
                    newNode.next = finNode;
                }
            }
            return next;
        }

        //        newNode.next = next;
        //        if (l1.next != null && l2.next != null) {
        //            return combineTwoList(newNode, l1.next, l2.next, addVal);
        //        } else {
        //            return newNode;
        //        }
    }

    //    /**
    //     * 联结两个链表，获得一个反过来的最终链表
    //     *
    //     * @param [next, l1, l2, addVal]
    //     * @return leetcode.ListNode
    //     * @Author: shenpeng
    //     * @Date: 2019-12-28 
    //     */
    //    public ListNode combineTwoList(ListNode next, ListNode l1, ListNode l2, int addVal) {
    //        int sum = l1.val + l2.val + addVal;
    //        ListNode newNode;
    //        if (sum >= 10) {
    //            newNode = new ListNode(sum - 10);
    //            addVal = 1;
    //        } else {
    //            newNode = new ListNode(l1.val + l2.val);
    //            addVal = 0;
    //        }
    //        newNode.next = next;
    //        if (l1.next != null && l2.next != null) {
    //            return combineTwoList(newNode, l1.next, l2.next, addVal);
    //        } else {
    //            return newNode;
    //        }
    //    }

    /**
     * 将连标倒序
     * 
     * @param [finNode]
     * @return leetcode.ListNode
     * @Author: shenpeng
     * @Date: 2019-12-28
     */
    public ListNode getReverseNode(ListNode finNode) {
        ListNode nextNode = new ListNode(finNode.val);
        ListNode preNode;
        while (finNode.next != null) {
            preNode = new ListNode(finNode.next.val);
            preNode.next = nextNode;
            finNode = finNode.next;
            nextNode = preNode;
        }
        return nextNode;
    }

    /**
     * 获取列表长度
     * 
     * @param [listNode]
     * @return int
     * @Author: shenpeng
     * @Date: 2019-12-27
     */
    public int getListLength(ListNode listNode) {
        int length = 0;
        while (listNode != null) {
            length++;
            listNode = listNode.next;
        }
        return length;
    }

    /**
     * 将列表后面扩展n个结点
     * 
     * @param [listNode, n]
     * @return void
     * @Author: shenpeng
     * @Date: 2019-12-27
     */
    public void expendList(ListNode listNode, int n) {
        ListNode listNodeTemp = listNode;
        while (listNodeTemp.next != null) {
            listNodeTemp = listNodeTemp.next;
        }
        for (int i = 0; i < n; i++) {
            ListNode newNode = new ListNode(0);
            listNodeTemp.next = newNode;
            listNodeTemp = newNode;
        }
    }

    //----------------------新解法-------------------------
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int sum;
        int addVal = 0;
        while (l1 != null || l2 != null || addVal != 0) {
            sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + addVal;
            if (sum >= 10) {
                cursor.next = new ListNode(sum - 10);
                addVal = 1;
            } else {
                cursor.next = new ListNode(sum);
                addVal = 0;
            }
            cursor = cursor.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return root.next;
    }

}

class ListNode {

    int val;

    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
