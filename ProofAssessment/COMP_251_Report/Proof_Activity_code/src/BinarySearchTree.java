public class BinarySearchTree {

    public static Node createNewNode (int val){
        Node newNode = new Node(val);
        newNode.val=val;
        newNode.right=null;
        newNode.left=null;
        return newNode;
    }

    public static Node insertNode (int val, Node root){
        //if root is null, set the new node as root
        if(root == null){
            return createNewNode(val);
         //root is not null, and root data < new data
        } else if (root.val < val){
            root.right = insertNode(val,root.right);
         //root is not null, and root data > new data
        } else if (root.val > val){
            root.left = insertNode(val, root.left);
        }
        return root;
    }

    //test if insertion works correctly
    public static void inorderTraverse (Node root){
        if (root == null){
            return;
        } else {
            inorderTraverse(root.left);
            System.out.println(root.val);
            inorderTraverse(root.right);
        }
    }



}
