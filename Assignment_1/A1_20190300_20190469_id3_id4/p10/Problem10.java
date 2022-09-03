import java.util.Scanner;

class Node {
    int val;
    int height;
    int rank;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
        this.height = 1;
        this.left = null;
        this.right = null;
        this.rank = 1;
    }
}

class AVlTrre {

    private Node root;

    AVlTrre() {
        this.root = null;
    }

    Node getRoot() {
        return this.root;
    }

    Node insert(Node root, int val) {
        if (this.root == null) {
            root = new Node(val);
            this.root = root;
            return root;
        } else {
            if (root == null) {
                root = new Node(val);
                return root;
            } else if (val < root.val) {
                root.left = insert(root.left, val);
            } else if (val > root.val) {
                root.right = insert(root.right, val);
            }
            root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
            root.rank = 1 + getRank(root.left) + getRank(root.right);
            int bFactor = getBFactor(root);
            if (bFactor > 1 && val < root.left.val) {
                return rightRotate(root);
            }
            if (bFactor > 1 && val > root.left.val) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
            if (bFactor < -1 && val > root.right.val) {
                return leftRotate(root);
            }
            if (bFactor < -1 && val < root.right.val) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
            return root;
        }
    }

    Node rightRotate(Node root) {
        Node leftNode = root.left;
        Node rightOfLeftNode = leftNode.right;
        leftNode.right = root;
        root.left = rightOfLeftNode;
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        root.rank = 1 + getRank(root.left) + getRank(root.right);
        leftNode.height = 1 + Math.max(getHeight(leftNode.left), getHeight(leftNode.right));
        leftNode.rank = 1 + getRank(leftNode.left) + getRank(leftNode.right);
        return leftNode;
    }

    Node leftRotate(Node root) {
        Node leftNode = root.right;
        Node rightOfLeftNode = leftNode.left;
        leftNode.left = root;
        root.right = rightOfLeftNode;
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        root.rank = 1 + getRank(root.left) + getRank(root.right);
        leftNode.height = 1 + Math.max(getHeight(leftNode.left), getHeight(leftNode.right));
        leftNode.rank = 1 + getRank(leftNode.left) + getRank(leftNode.right);
        return leftNode;
    }

    Node minValueNode(Node node) {
        Node root = node;
        while (root.left != null)
            root = root.left;

        return root;
    }

    Node deleteNode(Node root, int val) {

        if (root == null)
            return root;

        if (val < root.val)
            root.left = deleteNode(root.left, val);

        else if (val > root.val)
            root.right = deleteNode(root.right, val);

        else {

            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {

                Node temp = minValueNode(root.right);

                root.val = temp.val;

                root.right = deleteNode(root.right, temp.val);
            }
        }

        if (root == null)
            return root;

        root.height = max(getHeight(root.left), getHeight(root.right)) + 1;

        int balance = getBFactor(root);

        if (balance > 1 && getBFactor(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBFactor(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBFactor(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node search(Node root, int val) {
        if (root == null) {
            return root;
        }
        if (val == root.val) {
            return root;
        } else if (val < root.val) {
            return search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }

    int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    int getRank(Node root) {
        if (root == null) {
            return 0;
        }
        return root.rank;
    }

    int getBFactor(Node root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.left) - getHeight(root.right);
    }

    int getIndex(Node root) {
        if (root.left != null)
            return 1 + root.left.rank;

        return root.rank;

    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node getParent(Node root, Node node) {
        if (root.left.val == node.val || root.right.val == node.val) {
            return root;
        }
        if (node.val == root.val) {
            return root;
        } else if (node.val < root.val) {
            return getParent(root.left, node);
        } else {
            return getParent(root.right, node);
        }
    }

}

class Problem10 {

    public static void main(String[] args) {

        AVlTrre tree = new AVlTrre();
        Scanner input = new Scanner(System.in);

        System.out.println("");
        String str = "\noutput\n";
        String userInput = "";
        Integer order = 0;
        Integer value = 0;

        while (order != -1) {
            userInput = input.nextLine();
            try {
                if (userInput.split(" ").length > 1) {
                    if (userInput.split(" ")[0] != null)
                        order = Integer.valueOf(userInput.split(" ")[0]);
                    if (userInput.split(" ")[1] != null)
                        value = Integer.valueOf(userInput.split(" ")[1]);
                } else {
                    order = Integer.valueOf(userInput) == -1 ? -1 : 0;
                }
                if (order == 1) {
                    tree.insert(tree.getRoot(), value);
                }

                if (order == 2) {
                    Node targetNode = tree.search(tree.getRoot(), value);
                    if (targetNode != null) {
                        Node parent = tree.getParent(tree.getRoot(), targetNode);
                        tree.deleteNode(tree.getRoot(), value);
                        parent.rank = 1 + tree.getRank(parent.left) + tree.getRank(parent.right);

                    }

                }

                if (order == 3) {
                    Node targetNode = tree.search(tree.getRoot(), value);
                    if (targetNode != null)
                        str += String.valueOf(tree.getIndex(targetNode)) + "\n";
                    else
                        str += "-1\n";
                }

                if (order == 4) {
                    Node targetNode = tree.search(tree.getRoot(), value);
                    if (targetNode != null)
                        str += String.valueOf(1 + tree.getIndex(targetNode)) + "\n";
                    else
                        str += "-1\n";
                }

            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

        }

        input.close();
        System.out.println(str);

    }

}