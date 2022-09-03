import java.util.Scanner;

class Node {
    int val;
    int height;
    int rank;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
        height = 1;
        left = null;
        right = null;
        rank = 1;
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
            root.rank = 1 + getCount(root.left) + getCount(root.right);
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
        root.rank = 1 + getCount(root.left) + getCount(root.right);
        leftNode.height = 1 + Math.max(getHeight(leftNode.left), getHeight(leftNode.right));
        leftNode.rank = 1 + getCount(leftNode.left) + getCount(leftNode.right);
        return leftNode;
    }

    Node leftRotate(Node root) {
        Node leftNode = root.right;
        Node rightOfLeftNode = leftNode.left;
        leftNode.left = root;
        root.right = rightOfLeftNode;
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        root.rank = 1 + getCount(root.left) + getCount(root.right);
        leftNode.height = 1 + Math.max(getHeight(leftNode.left), getHeight(leftNode.right));
        leftNode.rank = 1 + getCount(leftNode.left) + getCount(leftNode.right);
        return leftNode;
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

    int getCount(Node root) {
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

}

class Problem9 {

    public static void main(String[] args) {

        AVlTrre tree = new AVlTrre();
        Scanner input = new Scanner(System.in);

        System.out.println("");
        int ordersCount = input.nextInt();
        String str = "";
        String userInput;
        input.nextLine();

        for (var i = 0; i < ordersCount; i++) {
            userInput = input.nextLine();
            try {
                Integer order = Integer.valueOf(userInput.split(" ")[0]);
                Integer value = Integer.valueOf(userInput.split(" ")[1]);

                if (order == 1) {
                    tree.insert(tree.getRoot(), value);
                }

                if (order == 2) {
                    Node targetNode = tree.search(tree.getRoot(), value);
                    if (targetNode != null)
                        str += " " + String.valueOf(tree.getIndex(targetNode));
                    else
                        str += " -1";
                }

            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

        }

        input.close();
        System.out.println(str);

    }

}