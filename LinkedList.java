class ListNode {
  private Object data;
  private ListNode next;

  public ListNode(Object d) {
    data = d;
    next = null;
  }
  public ListNode(Object d, ListNode next) {
    data = d;
    this.next = next;
  }
  public Object getData() {
    return data;
  }
  public void setData(Object d) {
    data = d;
  }
  public ListNode getNext() {
    return next;
  }
  public void setNext(ListNode next) {
    this.next = next;
  }
}

class EmptyListException extends RuntimeException {
  public EmptyListException() {
    super("List is empty");
  }
}

class LinkedList {
  private ListNode head;
  private ListNode tail;

  public LinkedList() {
    head = tail = null;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void addToHead(Object item) {
    if (isEmpty()) {
      head = tail = new ListNode(item);
    } else {
      head = new ListNode(item, head);
    }
  }

  public void addToTail(Object item) {
    if (isEmpty()) {
      head = tail = new ListNode(item);
    } else {
      tail.setNext(new ListNode(item));
      tail = tail.getNext();
    }
  }

  public Object removeFromHead() throws EmptyListException {
    Object item;
    if (isEmpty()) {
      throw new EmptyListException();
    }
    item = head.getData();
    if (head == tail) {
      head = tail = null;
    } else {
      head = head.getNext();
    }
    return item;
  }

  public Object removeFromTail() throws EmptyListException {
    Object item;
    if (isEmpty()) {
      throw new EmptyListException();
    }
    item = tail.getData();
    if (head == tail) {
      head = tail = null;
    } else {
      ListNode current = head;
      while (current != tail) {
        current = current.getNext();
      }
      tail = current;
      current.setNext(null);
    }
    return item;
  }

  public void insertInorder(Object item) {
    if (isEmpty()) {
      head = tail = new ListNode(item);
    } else {
      if ((int)head.getData() > (int)item) {
        addToHead(item);
      } else if ((int)tail.getData() < (int)item) {
        addToTail(item);
      } else {
        ListNode current = head;
        while (current.getNext() != null) {
          if ((int)current.getData() >= (int)item) {
            ListNode newItem = new ListNode(item);
            newItem.setNext(current.getNext());
            current = newItem;
            return;
          }
          current = current.getNext();
        }
      }
    }
  }

  public String toString() {
    String s = "[ ";
    ListNode current = head;
    while (current != null) {
      s += current.getData() + " ";
      current = current.getNext();
    }
    return s += "]";
  }
}
