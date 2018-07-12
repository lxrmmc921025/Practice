public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int la = length(headA);
        int lb = length(headB);
        
        if (la < lb) {
            //cannot use swap, because pass by value
            ListNode tmp = headA;
            headA = headB;
            headB = tmp;
        }
        
        int len = Math.abs(la - lb);
        for (int i = 0; i < len; i++) {
            headA = headA.next;
        }
        
        for (int i = 0; i < Math.min(la, lb); i++) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    
    private int length(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}