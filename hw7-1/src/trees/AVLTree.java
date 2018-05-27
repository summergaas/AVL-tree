package trees;

import java.util.Stack;


public class AVLTree {
	private TreeNode root;             // first node of tree
	
	public AVLTree() { root = TreeNode.getExternalNode(); }   // constructor; no nodes in tree yet
	public void clear() { root = TreeNode.getExternalNode(); }
	  
	protected TreeNode getRoot() { return root; }
	
	public boolean isEmpty() { return root.isEmpty(); }
	
	public boolean find(int key) {
		if (root.isEmpty()) return false;
		Stack<TreeNode> s = root.findPath(key);
		return !TreeNode.isExternal(s.pop());
	}

	public int getHeight() { 
        if (root.isEmpty()) return -1;
		else return root.getHeight();
    }
	
	/* Insert the given data into this tree. Leaves the tree balanced */
	public void insert(int data) {
		// insert the data into the tree rooted at root
		// and update the root in case it got changed
		root = root.insert(data);
		// get the path from the inserted node to the root
		Stack<TreeNode> s = root.findPath(data);
		// balance the tree according to the AVL height property
		// and update the root in case it got changed
		root = AVLTree.balance(s);
	}

	/* Remove the given data from this tree. Leaves the tree balanced */
    public void remove(int id) {

        TreeNode idNode = root.find0(id);        
        if (!idNode.equals(TreeNode.getExternalNode()) && !idNode.equals(root)){        
        Stack<TreeNode> s = root.findPath(root.getData());
        TreeNode idChild = root;
        TreeNode idParent = root;
        if (idNode.getLeft() != TreeNode.getExternalNode() || idNode.getRight() != TreeNode.getExternalNode()){

                     if (idNode.getLeft() != TreeNode.getExternalNode()){
                            idChild = idNode.getLeft();//use this path if id has children
                     }
                     else {
                         idChild = idNode.getRight();
                     }
                    root = root.removeA(id);
                    s = root.findPath(idChild.getData());            
        }
        else if (idNode.getLeft() == TreeNode.getExternalNode() && idNode.getRight() == TreeNode.getExternalNode()){
                    while (!idParent.getLeft().equals(idNode) && !idParent.getRight().equals(idNode)){ //DeMorgan's Law, baby.
                        
                           if (idParent.getData() > id){
                               idParent=idParent.getLeft();
                           }
                           else if (idParent.getData()< id){
                               idParent=idParent.getRight();
                           }
                     }
                     root = root.removeA(id);
                     s = root.findPath(idParent.getData());                        
        }
        //compare id to root and check from the root to the opposite side as well?        
        //remove the data from the tree, and update root
       
                //get the path from the greatest extremity to the root -- maybe need to do this more than once?
        //balance the tree according ot he AVL height property and update root
        	root = AVLTree.balance(s);
    }
        else if(idNode.equals(root)){
            root=root.removeA(id);
        }
    }

	/* Throws an assertion error if this AVLTree is not a valid binary search tree */
	public void checkIsBST() {
		assert(root.isBST());
	}

	/* Throws an assertion error if the height balance property is violated anywhere
	   in this AVLTree */
	public void checkBalance() {
			checkBalanceHelper(root);
	}

	private static void checkBalanceHelper(TreeNode n) {
		if (!TreeNode.isExternal(n)) {
				assert(Math.abs(n.getLeft().getHeight()-n.getRight().getHeight()) <= 1);
				checkBalanceHelper(n.getLeft());
				checkBalanceHelper(n.getRight());
		}
	}
    
    private static TreeNode balance(Stack<TreeNode> path) {
		TreeNode n = null;
		boolean alreadyRebalanced = false;
		boolean alreadyAdjustParent = false;
		while(!path.isEmpty()) {
			if (alreadyRebalanced && !alreadyAdjustParent) {
				// if we just rebalanced but haven't set the parent of
				// the rebalanced nodes, then do so
				TreeNode parent = path.pop();
				if (parent.getData() > n.getData()) {
					parent.setLeft(n);
				} else {
					parent.setRight(n);
				}
				alreadyAdjustParent = true;
				n = parent;
			} else {
				// usually, just look at the next node in the path
				n = path.pop();
			}
			int hleft = n.getLeft().getHeight();
			int hright = n.getRight().getHeight();
			if (alreadyRebalanced) {
					// if we already did the tri-node restructuring then
					// the balanced property should be true here
					assert(Math.abs(hleft-hright) <= 1);
			} else {
					if (hleft - hright > 1) {  // left too tall
							if (n.getLeft().getLeft().getHeight() >= n.getLeft().getRight().getHeight()) {
									n = n.rotateRight();
							} else {
									n = n.doubleRotateLeftThenRight();
							}
							alreadyRebalanced = true;
					} else if (hright - hleft > 1) { // right too tall
							if (n.getRight().getRight().getHeight() >= n.getRight().getLeft().getHeight()) {
									n = n.rotateLeft();
							} else {
									n = n.doubleRotateRightThenLeft();
							}
							alreadyRebalanced = true;
					}
			}
		}

		// if we rebalanced the root of the tree, then t now points to the new root
		// otherwise t points to the existing root
		return n;
    }

}
