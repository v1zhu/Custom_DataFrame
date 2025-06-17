public class BST<I extends Comparable<I>, T>{

    class BSTNode {
        private I index;
        private T data;
        private BSTNode left;
        private BSTNode right;

        /**
         * Default constructor. Sets all instance variables to be null.
         */
        public BSTNode() {
            this.index = null;
            this.data = null;
            this.left = null;
            this.right = null;

        }

        /**
         * Constructor. Sets data and index to be _data and _index respectively.
         */
        public BSTNode(I _index, T _data) {
            // TODO
            this.index = _index;
            this.data = _data;
            this.left = null;
            this.right = null;
        }

        /**
         * Returns the index stored in this node.
         */
        public I getIndex() {
            // TODO
            return index;
        }

        /**
         * Returns the data stored in this node.
         */
        public T getData() {
            // TODO
            return data;
        }

        /**
         * Updates the data in this node to the specified value.
         */
        public void setData(T d) {
            this.data = d;
        }

        /**
         * Returns a string representation of the node, indicating its index and data.
         */
        public String toString() {
            // TODO
            StringBuilder sb = new StringBuilder();
            sb.append("index:").append("\t").append(this.index).append(",").append("\t");
            sb.append("data:").append("\t").append(this.data).append("\n");
            return sb.toString();
        }
    }


    private BSTNode root;
    private int size;

    /**
     * Constructor. Initializes an empty BST with root set to null and size set to 0.
     */
    public BST() {
        // TODO
        root = null;
        size = 0;
    }


    /**
     * Performs an in-order traversal of the BST and records indices and data values.
     */
    private String inOrderTraversal(BSTNode node) {
        // TODO
        if (node == null) {
            return "";
        }
        return inOrderTraversal(node.left)
                + node.toString() +
                inOrderTraversal(node.right);

    }

    /**
     * Returns a string representation of the entire BST using in-order traversal.
     */
    public String toString() {
        // TODO
        StringBuilder sb = new StringBuilder();
        sb.append("In-order Traversal of the BST ...\n");
        sb.append("==================\n");
        sb.append(inOrderTraversal(root));
        return sb.toString();
    }

    /**
     * Returns the size of the BST, i.e., the number of valid nodes.
     */
    public int getSize() {
        // TODO
        return size;
    }



    /**
     * Adds a new node with the specified index and data to the BST.
     */

    public void addNode(I _index, T _data) {
        // TODO
        root = add(root, _index, _data);
    }
    private BSTNode add(BSTNode node, I _index, T _data) {
        if (node == null) {
            size++;
            return new BSTNode(_index, _data);
        }
        int compare = _index.compareTo(node.getIndex());
        if (compare < 0) {
            node.left = add(node.left, _index, _data);
        }
        else if (compare > 0) {
            node.right = add(node.right, _index, _data);
        }
        //assume no duplicate index
        return node;
    }

    /**
     * Searches for a node with the specified index in the BST.
     */
    public BSTNode searchNode(I _index) {
        // TODO
        return search(root, _index);
    }

    private BSTNode search(BSTNode node, I _index) {
        if (node == null) {
            return null;
        }
        int compare = _index.compareTo(node.getIndex());
        if (compare == 0) {
            return node;
        }
        else if (compare < 0) {
            return search(node.left, _index);
        }
        else {
            return search(node.right, _index);
        }

    }

    /**
     * Removes a node with the specified index from the BST.
     */
    public void removeNode(I _index) throws IllegalArgumentException {
        // TODO
        if (searchNode(_index) == null) {
            throw new IllegalArgumentException("removeNode(I _index):" +
                    " No node with an index "+ _index + " in the BST");
        }
        root = remove(root, _index);
        size--;
    }

    private BSTNode remove(BSTNode node, I _index) {
        int compare = _index.compareTo(node.getIndex());
        if (compare < 0) {
            node.left = remove(node.left, _index);
        }
        else if (compare > 0) {
            node.right = remove(node.right, _index);
        }
        else {
            if (node.left == null && node.right == null) {
                return null;
            }
            else if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return node.left;
            }
            else {
                BSTNode success = successor(node.right);
                node.setData(success.getData());
                node.index = success.getIndex();
                node.right = remove(node.right, success.getIndex());

            }
        }
        return node;

    }

    private BSTNode successor(BSTNode node) {
        if (node.left == null) {
            return node;
        }
        else {
            return successor(node.left);
        }
    }


    /**
     * Updates a node's data with a new value, given its index.
     */
    public void updateNode(I _index, T _newData) throws IllegalArgumentException {
        // TODO
        if (searchNode(_index) == null) {
            throw new IllegalArgumentException("updateNode(I _index, T _newData): No node with an index "+ _index + " in the BST");
        }
        BSTNode temp = update(root, _index, _newData);
    }

    private BSTNode update(BSTNode node,I _index, T _newData) {
        int compare = _index.compareTo(node.getIndex());
        if (compare < 0) {
            node.left = update(node.left, _index, _newData);
        }
        else if (compare > 0) {
            node.right = update(node.right, _index, _newData);
        }
        else {
            node.index = _index;
            node.setData(_newData);
        }
        return node;
    }

    
/************************************ GRADING CODE (DO NOT MODIFY) ************************************ */
    /**
     * Performs a pre-order traversal of the BST.
     */
    private void preOrderTraversal(BSTNode node, int[] idx, String[] arr, boolean dataFlag) {
        // DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        if(node == null)
            return;

        if(dataFlag)
            arr[idx[0]] = String.valueOf(node.getData());
        else
            arr[idx[0]] = String.valueOf(node.getIndex());
        idx[0]++;
        
        preOrderTraversal(node.left, idx, arr, dataFlag);
        preOrderTraversal(node.right, idx, arr, dataFlag);
    }

    /**
     * Returns an array of data values in pre-order traversal order.
     * @return A String array containing the data values of all nodes in pre-order order
     */
    public String[] getDataArray() {
        /// DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        String[] dataArr = new String[size];
        preOrderTraversal(this.root, new int[1], dataArr, true);
        return dataArr;
    }

    /**
     * Returns an array of index values in pre-order traversal order.
     * @return A String array containing the index values of all nodes in pre-order order
     */
    public String[] getIndexArray() {
        // DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        String[] indexArr = new String[size];
        preOrderTraversal(this.root, new int[1], indexArr, false);
        return indexArr;
    }

/****************************************************************************************************** */

}
