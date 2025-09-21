
// Node is only used inside the LinkedList class, so we define it in the same file;
// there can only be one public class in a file, but there can also be non-public classes.
class Node {
    int item;
    Node next;
    Node(int item) {
        this.item = item;
    }
}


public class LinkedListMultiSet extends MultiSet {

    // a linked list initially is empty
    private Node front;
    private int size;


    public void add(int item) {
        Node newNode =  new Node(item);
        newNode.next = front;
        front = newNode;
        size += 1;
    }

    public void remove(int item) {
        Node curr = this.front;
        Node prev = null;

        while (curr != null) {
            if (curr.item == item) {

                this.size -= 1;

                if (prev != null) {
                    prev.next = curr.next;
                }

                else {
                    this.front = curr.next;
                }

                return;
            }

            prev = curr;
            curr = curr.next;
        }
    }

    public boolean contains(int item) {
        Node curr = this.front;

        while (curr != null) {
            if (curr.item == item) {
                return true;
            }

            curr = curr.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return this.front == null;
    }


    public int count(int item) {
        int num_seen = 0;
        Node curr = this.front;

        while (curr != null) {
            if (curr.item == item) {
                num_seen++;
            }

            curr = curr.next;
        }

        return num_seen;
    }

    public int size() {
        return this.size;
    }
}
