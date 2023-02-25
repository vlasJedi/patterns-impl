package exercises.linkedlists;

public class LinkedLists {

     // Definition for singly-linked list.
     public static class ListNode {
         public int val;
         public ListNode next;
         ListNode() {}
         public ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
         if (list1 == null) return list2;
         if (list2 == null) return list1;
         ListNode res = list2;
         ListNode prevList2Node = null;
         while(list1 != null) {
             if (list2 == null) {
                 prevList2Node.next = list1;
                 break;
             }
             while(list2 != null) {
                 if (list1.val <= list2.val) {
                     if (prevList2Node == null) {
                         res = list1;
                     } else {
                         prevList2Node.next = list1;
                     }
                     prevList2Node = list1;
                     ListNode nextList1Node = list1.next;
                     list1.next = list2;
                     list1 = nextList1Node;
                     break;
                 }
                 prevList2Node = list2;
                 list2 = list2.next;
             }
         }
         return res;
    }

    public ListNode reverseList(ListNode head) {
         if (head == null || head.next == null) return head;
         ListNode res = head;
         while(res.next != null) res = res.next;
         reverseLinked(head);
         head.next = null;
         return res;
    }

    public ListNode reverseLinked(ListNode head) {
         if (head.next == null) return head;
         ListNode child = reverseLinked(head.next);
         child.next = head;
         return head;
    }
}
