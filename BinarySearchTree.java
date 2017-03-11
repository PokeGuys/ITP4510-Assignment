class BinaryNode {
  Object data;
  BinaryNode left;
  BinaryNode right;
  LinkedList lines;

  public BinaryNode(Object d) {
    data = d;
    left = right = null;
    lines = new LinkedList();
  }

  public Object getData() {
    return data;
  }

  public String getLines() {
    return lines.toString();
  }
}

public class BinarySearchTree {
  private BinaryNode root;

  public BinarySearchTree() {
    root = null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public boolean search(Object x) {
    if (search(root, x) != null) {
      return true;
    } else {
      return false;
    }
  }

  private BinaryNode search(BinaryNode t, Object x) {
    if (t == null) return null;
    if (((Comparable)x).compareTo(t.getData()) < 0) {
      return search(t.left, x);
    } else if (((Comparable)x).compareTo(t.getData()) > 0) {
      return search(t.right, x);
    }
    return t;
  }

  public void insert(Object x) {
    root = insertSubtree(root, x);
  }

  private BinaryNode insertSubtree(BinaryNode t, Object x) {
    if (t == null) {
      t = new BinaryNode(x);
    } else if (((Comparable)x).compareTo(t.getData()) < 0) {
      t.left = insertSubtree(t.left, x);
    } else {
      t.right = insertSubtree(t.right, x);
    }
    return t;
  }

  public void insertLines(Object x, int current) {
    BinaryNode target = search(root, x);
    target.lines.insertInorder(current);
  }

  public void delete(Object x) {
    root = deleteSubtree(root, x);
  }

  private BinaryNode deleteSubtree(BinaryNode t, Object x) {
    if (t == null) return null;
    if (((Comparable)x).compareTo(t.getData()) < 0) {
      t.left = deleteSubtree(t.left, x);
    } else if (((Comparable)x).compareTo(t.getData()) > 0) {
      t.right = deleteSubtree(t.right, x);
    } else if (t.left != null && t.right != null) {
      t.data = findMin(t.right).getData();
      t.right = deleteSubtree(t.right, t.data);
    } else {
      t = (t.left != null) ? t.left : t.right;
    }
    return t;
  }

  private void visit(BinaryNode t) {
    System.out.printf("%-50s : %s\n", t.getData(), t.getLines());
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
    return findMin(root).getData();
  }

  private BinaryNode findMin(BinaryNode t) {
    if (t == null) return t;
    return (t.left == null ? t : findMin(t.left));
  }

  public Object getMax() {
    return findMin(root).getData();
  }

  private BinaryNode findMax(BinaryNode t) {
    if (t == null) return t;
    return (t.right == null ? t : findMax(t.right));
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
    int left = heightSubtree(t.left);
    int right = heightSubtree(t.right);
    return (left > right ? left + 1 : right + 1);
  }
}
