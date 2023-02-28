package exercises.linkedlists;

import java.util.HashSet;
import java.util.Set;

public class LinkedLists {

     // Definition for singly-linked list.
     public static class ListNode {
         public int val;
         public ListNode next;
         ListNode() {}
         public ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    /**
     * Lists are sorted in increasing order and order should be preserved
     * @param list1
     * @param list2
     * @return
     */
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

    public ListNode middleNode(ListNode head) {
         if (head.next == null) return head;
         int count = 0;
         ListNode cNode = head;
         while(cNode != null) {
             count++;
             cNode = cNode.next;
         }
         int target = count / 2;
         count = 0;
         cNode = head;
         while(true) {
             if (target == count) return cNode;
             count++;
             cNode = cNode.next;
         }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode cNode = head;
        Set<ListNode> nodes = new HashSet<>();
        nodes.add(cNode);
        while(cNode.next != null) {
            if (!nodes.contains(cNode.next)) {
                nodes.add(cNode.next);
                cNode = cNode.next;
                continue;
            }
            return cNode.next;
        }
        return null;
        // interesting solution by having two moving pointers
        // if we have a cycle then somewhere they should intersect definitely
//        public ListNode detectCycle(ListNode head) {
//            ListNode slow = head, fast = head;
//            while (fast != null && fast.next != null) {
//                slow = slow.next;
//                fast = fast.next.next;
//                if (slow == fast) break;
//            }
//            if (fast == null || fast.next == null) return null;
//            while (head != slow) {
//                head = head.next;
//                slow = slow.next;
//            }
//            return head;
//        }
    }
}
