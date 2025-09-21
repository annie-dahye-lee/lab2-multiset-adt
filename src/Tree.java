public class Tree {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how you might split up the work to get the Tree and TreeMultiSet
    // implemented.

    // no private access modifiers at the moment, might add them later
    boolean empty;
    public int root;
    public Tree[] subtrees;
    public int subtreesLength;

    /**
     * Initializer for empty tree
     */
    public Tree() {
        this.empty = true;
    }

    /**
     * Initializer for non-empty tree
     * @param root value of root
     * @param subtrees array of subtrees
     */
    public Tree(int root, Tree[] subtrees) {
        this.empty = false;
        this.root = root;
        this.subtrees = new Tree[100000]; //fixed size arrays in Java, using a large array to avoid needing other adts
        this.subtreesLength = subtrees.length;
        for (int i = 0; i < subtrees.length; i++) {
            this.subtrees[i] = subtrees[i];
        }
    }

    /**
     * check if tree is empty
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * check size of entire tree
     */
    public int size() {
        if (empty) {
            return 0;
        } else {
            int size = 1;
            for (int i = 0; i < subtreesLength; i++) {
                size += subtrees[i].size();
            }
            return size;
        }
    }

    /**
     * check number of times the item appears in the entire tree
     * @param item the item being counted
     */
    public int count(int item) {
        if (empty) {
            return 0;
        } else {
            int num = 0;
            if (this.root == item) {
                num += 1;
            }
            for (int i = 0; i < subtreesLength; i++) {
                num += subtrees[i].count(item);
            }
            return num;
        }
    }

    /**
     * return a string representation of this tree
     */
    public String toString() {
        // this may or may not be broken
        // call recursive helper
        return this.stringHelper(0);
    }

    /**
     * return an indented string representation of this tree
     * @param depth the specified indentation level
     */
    public String stringHelper(int depth) {
        if (this.empty) {
            return "";
        } else {
            StringBuilder s = new StringBuilder();
            s.append(" ".repeat(Math.max(0, depth)));
            s.append(this.root).append("\n");
            for (int i = 0; i < this.subtreesLength; i++) {
                s.append(this.subtrees[i].stringHelper(depth + 1));
            }
            return s.toString();
        }
    }

    /**
     * return the average of all values in tree
     */
    public double average() {
        if (this.empty) {
            return 0;
        } else {
            int[] a = this.averageHelper();
            return (double)a[0] / (double)a[1];
        }
    }
    /**
     * recursive helper for getting the average of values in a tree
     * returns 2 values; sum of values in the tree, and the size of the tree
     */
    public int[] averageHelper() {
        if (this.empty) {
            return new int[]{0, 0};
        } else {
            int total = this.root;
            int size = 1;
            for (int i = 0; i < this.subtreesLength; i++) {
                int[] s =  this.subtrees[i].averageHelper();
                total += s[0];
                size += s[1];
            }
            return new int[]{total, size};
        }
    }

    /**
     * check if this tree equals another tree
     * @param other the other tree
     */
    public boolean equals(Tree other) {
        if (empty && other.isEmpty()) {
            return true;
        } else if (empty || other.isEmpty()) {
            return false;
        } else {
            if (this.root != other.root) {
                return false;
            }
            if (this.subtreesLength != other.subtreesLength) {
                return false;
            } else {
                for (int i  = 0; i < this.subtreesLength; i++) {
                    if (!this.subtrees[i].equals(other.subtrees[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    /**
     * check if tree contains a certain item
     * @param item the item being searched for
     */
    public boolean contains(int item) {
        if (empty) {
            return false;
        } if (this.root == item) {
            return true;
        } else {
            for (int i = 0; i < subtreesLength; i++) {
                if (subtrees[i].contains(item)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * return an array of all the leaf items in the tree
     */
    public int[] leaves() {
        if (this.empty) {
            return new int[]{};
        } else if (this.subtreesLength == 0) {
            return new int[]{this.root};
        } else {
            int[] l = new int[1000000];
            int index = 0;
            for (int i = 0; i < this.subtreesLength; i++) {
                int[] sl = this.subtrees[i].leaves();
                for (int slItem: sl) {
                    l[index] = slItem;
                    index += 1;
                }
            }
            int[] leaves = new int[index];
            for (int i = 0; i < index; i++) {
                leaves[i] = l[i];
            }
            return leaves;
        }
    }

    /**
     * remove a certain item from the tree
     * @param item the item being removed
     */
    public boolean remove(int item) {
        if  (empty) {
            return false;
        } else if (this.root == item) {
            this.removeRoot();
            return true;
        } else {
            for (int i = 0; i < subtreesLength; i++) {
                boolean deleted = subtrees[i].remove(item);
                if (deleted && subtrees[i].isEmpty()) {
                    this.removeHelper(i);
                    this.subtreesLength -= 1;
                    return true;
                } else if (deleted) {
                    this.subtreesLength -= 1;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * helper for removing a subtree from the array of subtrees
     * @param index the index of the subtree being removed
     */
    public void removeHelper(int index) {
        for (int i = index; i < subtreesLength; i++) {
            subtrees[i] = subtrees[i + 1];
        }
    }

    /**
     * remove the root of the tree
     */
    public void removeRoot() {
        if (this.subtreesLength == 0) {
            this.empty = true;
        } else {
            Tree chosenSubtree = this.subtrees[subtreesLength - 1];
            this.root = chosenSubtree.root;
            for (int i = 0; i < chosenSubtree.subtreesLength; i++) {
                this.subtrees[i - 1 + this.subtreesLength] = chosenSubtree.subtrees[i];
            }
            this.subtreesLength += chosenSubtree.subtreesLength;
        }
        this.subtreesLength -= 1;
    }

    /**
     * remove and return the leftmost leaf
     */
    public int extractLeaf() {
        if (this.subtreesLength == 0) {
            int oldRoot = this.root;
            this.empty = true;
            return oldRoot;
        } else {
            int leaf = this.subtrees[0].extractLeaf();
            if (this.subtrees[0].isEmpty()) {
                this.removeHelper(0);
                this.subtreesLength -= 1;
            }
            return leaf;
        }
    }

    /**
     * add an item to the tree
     * @param item the item being added
     */
    public void add(int item) {
        if (empty) {
            this.root = item;
            this.subtrees = new Tree[100000];
            this.subtreesLength = 0;
            this.empty = false;
        } else if (this.subtreesLength == 0) {
            Tree[] newSubtrees = {};
            this.subtrees[0] = new Tree(item, newSubtrees);
            this.subtreesLength = 1;
        } else {
            Tree[] newSubtrees = {};
            Tree newTree = new Tree(item, newSubtrees);
            if ((int)(Math.random() * 3) == 0) {
                this.subtrees[subtreesLength] = newTree;
            } else {
                int subtree_index = (int)(Math.random() * this.subtreesLength);
                addHelper(subtree_index, newTree);
            }
            this.subtreesLength += 1;
        }
    }

    /**
     * add a subtree to the tree at the specific index
     * @param index the specified indentation level
     * @param newTree new tree being added
     */
    public void addHelper(int index, Tree newTree) {
        for (int i = this.subtreesLength; i > index; i--) {
            this.subtrees[i] = this.subtrees[i - 1];
        }
        this.subtrees[index] = newTree;
    }

    /**
     * add an item to the tree as the child of a specific parent
     * if successful return true; if parent not in tree, return false
     * @param item the item being added
     * @param parent the chosen parent of the item
     */
    public boolean addChild(int item, int parent) {
        if (this.isEmpty()) {
            return false;
        } else if (this.root == parent) {
            Tree[] s = {};
            this.subtrees[subtreesLength] = new Tree(item, s);
            subtreesLength += 1;
            return true;
        } else {
            for (int i = 0; i < subtreesLength; i++) {
                if (subtrees[i].addChild(item, parent)) {
                    return true;
                }
            }
            return false;
        }
    }
}
