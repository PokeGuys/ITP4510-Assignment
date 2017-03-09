class ListNode {

	private Object data;
	private ListNode next;

	public ListNode(Object o) { data = o; next = null; }
	public ListNode(Object o, ListNode nextNode)
		{ data = o; next = nextNode; }

	public Object getData() { return data; }
	public void setData(Object o) { data = o; }
	public ListNode getNext() { return next; }
	public void setNext(ListNode next) { this.next = next; }
} // class ListNode

class EmptyListException extends RuntimeException {
	public EmptyListException ()
		{ super("List is empty"); }
} // class EmptyListException


class LinkedList {

	private ListNode head;
	private ListNode tail;

	public LinkedList() { head = tail = null; }

	public boolean isEmpty() { return head == null; }

	public void addToHead(Object item) {
		if (isEmpty())
			head = tail = new ListNode(item);
		else
			head = new ListNode(item, head);
	}

	public void addToTail(Object item) {
		if (isEmpty())
			head = tail = new ListNode(item);
		else {
			tail.setNext(new ListNode(item));
			tail = tail.getNext();
		}
	}

	public Object removeFromHead() throws EmptyListException {
		Object item = null;
		if (isEmpty())
			throw new EmptyListException();
		item = head.getData();
		if (head == tail)
			head = tail = null;
		else
			head = head.getNext();
		return item;
	}

	public Object removeFromTail() throws EmptyListException {
		Object item = null;
		if (isEmpty())
			throw new EmptyListException();
		item = tail.getData();
		if (head == tail)
			head = tail = null;
		else {
			ListNode current = head;
			while (current.getNext() != tail)
				current = current.getNext();
			tail = current;
			current.setNext(null);
		}
		return item;
	}

	public void insertInorder(Object item) {
		if (isEmpty()) {
			head = tail = new ListNode(item);
		} else {
			if (((Comparable)head.data).compareto(item) > 0) {
				addToHead(item);
			} else if (((Comparable)tail.data).compareto(item) < 0) {
				addToTail(item);
			} else {
				ListNode current = head;
				while (current.next != null) {
					
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
		return s + "]";
	}

} // class LinkedList
