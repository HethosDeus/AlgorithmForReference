/**DSW Algorithm for balancings an existing BST.
*  Time Complexity: O(n)
*  Space Complexity: O(1)
*/

public void balance() {

    	Node gParent = null;
    	Node parent = root;
    	Node leftChild;
    	 
    	while (null != parent) {
    		leftChild = parent.left;
    		if (null != leftChild) {
    			gParent = rotateRight(gParent, parent, leftChild);
    			parent = leftChild;
    		} else {
    			gParent = parent;
	    			parent = parent.right;
    		}
    	}
    	createPerfectBST();
	}
   
	private Node rotateRight(Node grandParent, Node parent, Node leftChild) {
		if (null != grandParent) {
			grandParent.right = leftChild;
		} else {
			root = leftChild;
		}
		
		parent.left = leftChild.right;
		leftChild.right = parent;
		
		return grandParent;
	}
	
	private void rotateLeft(Node grandParent, Node parent, Node rightChild) {
		if (null != grandParent) {
			grandParent.right = rightChild;
		} else {
			root = rightChild;
		}
		parent.right = rightChild.left;
		rightChild.left = parent;
	}

	 
	private void makeRotations(int bound) {
		Node gParent = null;
		Node parent = root;
		Node child = root.right;
	
		for (; bound > 0; bound--) {
			try {
				if (null != child) {
					rotateLeft(gParent, parent, child);
					gParent = child;
					parent = gParent.right;
					child = parent.right;
				} else {
					break;
				}
			} catch (NullPointerException convenient) {
				break;
			}
		}
	}
		
	private void createPerfectBST() {
		int n = 0;
		for (Node tmp = root; null != tmp; tmp = tmp.right) {
			n++;
		}
    	  
		int m = greatestPowerOf2LessThanN(n + 1) - 1;
		makeRotations(n - m);
    	 
		while (m > 1) {
			makeRotations(m /= 2);
		}
	}
		
	private int greatestPowerOf2LessThanN(int n) {
		int x = MSB(n);//MSB
		return (1 << x);//2^x
		}
    	 
    /*return the index of most significant set bit: index of
     * least significant bit is 0
   	 */
	public int MSB(int n) {
		int ndx = 0;
		while (1 < n) {
			n = (n >> 1);
			ndx++;
		}
		return ndx;
   	}
