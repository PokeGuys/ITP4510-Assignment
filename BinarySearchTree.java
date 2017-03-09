class BinaryNode {
	Object data;
	LinkedList pages;
	BinaryNode left;
	BinaryNode right;

	public BinaryNode(Object d) {
		data = d;
		left = right = null;
	}
	public Object getData() {
		return data;
	}
	public String getPages() {
		return pages;
	}
	public void insertPage(int currentPage) {
		pages.insertInorder(currentPage);
	}
} // class BinaryNode

public class BinarySearchTree {

	private BinaryNode root;

	public BinarySearchTree() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean search(Object x) {
		if (search(root, x) != null)
			return true;
		else
			return false;
	}

	private BinaryNode search(BinaryNode t, Object x) {
		if (t == null)
			return null;
		if (((Comparable)x).compareTo(t.data) < 0)
			return search(t.left, x);
		else if (((Comparable)x).compareTo(t.data) > 0)
				return search(t.right, x);
			 else
				return t;
	}

	public void insert(Object x) {
		root = insertSubtree(root, x);
	}

	private BinaryNode insertSubtree(BinaryNode t, Object x) {
		if (t == null)
			t = new BinaryNode(x);
		else if (((Comparable)x).compareTo(t.data) < 0)
			t.left = insertSubtree(t.left, x);
		else //if (((Comparable)x).compareTo(t.data) > 0)
			t.right = insertSubtree(t.right, x);
		return t;
	}

	private void visit(BinaryNode t) {
		System.out.print(t.data + "\t\t\t\t: " + t.getPages());
	}

	public void preorder() {
		preorderSubtree(root);
		System.out.println();
	}

	private void preorderSubtree(BinaryNode t) {
		if (t == null) return;
		visit(t);
		preorderSubtree(t.left);
		preorderSubtree(t.right);
	}

	public void inorder() {
		inorderSubtree(root);
		System.out.println();
	}

	private void inorderSubtree(BinaryNode t) {
		if (t == null) return;
		inorderSubtree(t.left);
		visit(t);
		inorderSubtree(t.right);
	}

	public void postorder() {
		postorderSubtree(root);
		System.out.println();
	}

	private void postorderSubtree(BinaryNode t) {
		if (t == null) return;
		postorderSubtree(t.left);
		postorderSubtree(t.right);
		visit(t);
	}

	public Object getMin() {
		return findMin(root).data;
	}

	private BinaryNode findMin(BinaryNode t) {
		if (t == null)
			return null;
		else if (t.left == null)
				return t;
			 else
				return findMin(t.left);
	}

	public Object getMax() {
		return findMax(root).data;
	}

	private BinaryNode findMax(BinaryNode t) {
		if (t == null)
			return null;
		else if (t.right == null)
				return t;
			 else
				return findMax(t.right);
	}

	public void delete(Object x) {
		root = deleteSubtree(root, x);
	}

	private BinaryNode deleteSubtree(BinaryNode t, Object x) {
		BinaryNode temp, child;

		if (t == null) return null;
		if (((Comparable)x).compareTo(t.data) < 0)
			t.left = deleteSubtree(t.left, x);
		else if (((Comparable)x).compareTo(t.data) > 0)
			t.right = deleteSubtree(t.right, x);
		else if (t.left != null && t.right != null) {
			/* has 2 leaves */
			t.data = findMin(t.right).data;
			t.right = deleteSubtree(t.right, t.data);
		}
		else
			t = (t.left != null) ? t.left : t.right;

		return t;
	}

	public int size() {
		return sizeSubtree(root);
	}

	private int sizeSubtree(BinaryNode t) {
		if (t == null) return 0;
		return sizeSubtree(t.left) + sizeSubtree(t.right) + 1;
	}

	public int height() {
		return heightSubtree(root);
	}

	private int heightSubtree(BinaryNode t) {
		if (t == null) return -1;
		int h = heightSubtree(t.left);
		int k = heightSubtree(t.right);
		return h > k ? h + 1 : k + 1;
	}

} // class BinarySearchTree
