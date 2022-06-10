package com.qs.demo.data;

public class RBTree<T extends Comparable<T>, D> {
    private RBNode<T, D> root;

    class RBNode<T extends Comparable<T>, D> {
        private boolean red = true;
        private T key;
        private D data;
        private RBNode<T, D> parent;
        private RBNode<T, D> left;
        private RBNode<T, D> right;

        public RBNode(boolean red, T key, D data, RBNode<T, D> parent, RBNode<T, D> left, RBNode<T, D> right) {
            this.red = red;
            this.key = key;
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed(boolean red) {
            this.red = red;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public D getData() {
            return data;
        }

        public void setData(D data) {
            this.data = data;
        }

        public RBNode<T, D> getParent() {
            return parent;
        }

        public void setParent(RBNode<T, D> parent) {
            this.parent = parent;
        }

        public RBNode<T, D> getLeft() {
            return left;
        }

        public void setLeft(RBNode<T, D> left) {
            this.left = left;
        }

        public RBNode<T, D> getRight() {
            return right;
        }

        public void setRight(RBNode<T, D> right) {
            this.right = right;
        }
    }

    public void put(T key, D data) {
        int compare = 0;
        RBNode<T, D> x = this.root;
        RBNode<T, D> y = null;
        while (x != null) {
            y = x;
            compare = key.compareTo(x.key);
            if (compare == 0) {
                x.data = data;
                return;
            }
            if (compare < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        RBNode<T, D> node = new RBNode<>(true, key, data, null, null, null);
        node.parent = y;
        if (y != null) {
            if (compare < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        } else {
            this.root = node;
        }
        balanceInsertion(node);
    }

    private void balanceInsertion(RBNode<T, D> node) {
        RBNode<T, D> parent, gparent;
        while ((parent = node.parent) != null && parent.red) {
            gparent = parent.parent;
            if (gparent.left == parent) {
                RBNode<T, D> uncle = gparent.right;
                if (uncle.red) {
                    gparent.setRed(true);
                    uncle.setRed(false);
                    parent.setRed(false);
                }
            } else {
                if (parent.right == node) {
                    leftRotate(parent);
                    RBNode<T, D> temp = node;
                    node = parent;
                    parent = temp;
                }
                parent.setRed(false);
                gparent.setRed(true);
                rightRotate(gparent);
            }

        }
    }

    private void leftRotate(RBNode<T, D> node) {
        RBNode<T, D> right = node.right;
        if (right.left != null) {
            right.left.parent = node;
        }
        node.right = right.left;
        right.left = node;
        right.parent = node.parent;
        if (right.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = right;
            } else {
                node.parent.right = right;
            }
        } else {
            this.root = right;
        }
        node.parent = right;
    }

    private void rightRotate(RBNode<T, D> node) {
        RBNode<T, D> left = node.left;
        if (left.right != null) {
            left.right.parent = node;
        }
        left.parent = node.parent;
        node.left = left.right;
        left.right = node;
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = left;
            } else {
                node.parent.right = left;
            }
        } else {
            this.root = left;
        }
        node.parent = left;
    }
}
