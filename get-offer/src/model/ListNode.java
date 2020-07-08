package model;

/**
 * @author xiakai
 * @create 2020-05-27 22:35
 */
public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(){};
    public ListNode(int x) { val = x; }


    public static ListNode build(int... arr) {
        if (arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }
        return head;
    }
}
