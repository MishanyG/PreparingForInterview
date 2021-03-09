package HomeWork_L2;

public class LinkedList {
    LinkedListNode linkedListNode;

    static class LinkedListNode {
        int data;
        LinkedListNode next;

        LinkedListNode(int d) {
            data = d;
            next = null;
        }
    }

    public static void add(LinkedList linkedList, int data) {
        LinkedListNode newNode = new LinkedListNode(data);
        newNode.next = null;

        if(linkedList.linkedListNode == null) {
            linkedList.linkedListNode = newNode;
        } else {
            LinkedListNode lastNode = linkedList.linkedListNode;

            while(lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }
    }

    public static void printList(LinkedList list) {
        LinkedListNode currentNode = list.linkedListNode;

        while(currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println("\n");
    }

    public static LinkedList removeByKey(LinkedList list, int key) {
        LinkedListNode currentNode = list.linkedListNode, previous = null;

        if(currentNode != null && currentNode.data == key) {
            list.linkedListNode = currentNode.next;
            return list;
        }

        while(currentNode != null && currentNode.data != key) {
            previous = currentNode;
            currentNode = currentNode.next;
        }

        if(currentNode != null) {
            previous.next = currentNode.next;
        }

        if(currentNode == null) {
            System.out.println(key + " key not found!");
        }
        return list;
    }

    public static LinkedList removeByPosition(LinkedList list, int index) {
        LinkedListNode currentNode = list.linkedListNode, previous = null;

        if(index == 0 && currentNode != null) {
            list.linkedListNode = currentNode.next;
            return list;
        }

        int count = 0;

        while(currentNode != null) {
            if(count == index) {
                previous.next = currentNode.next;
                break;
            } else {
                previous = currentNode;
                currentNode = currentNode.next;
                count++;
            }
        }

        if(currentNode == null) {
            System.out.println(index + " position element not found!");
        }
        return list;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        int i = 0;

        do {
            ++i;
            add(list, i);
        } while(i != 10);

        printList(list);
        printList(removeByKey(list, 3));
        printList(removeByKey(list, 5));
        printList(removeByKey(list, 5));
        printList(removeByPosition(list, 8));
        printList(removeByPosition(list, 2));
        printList(removeByPosition(list, 6));
    }
}