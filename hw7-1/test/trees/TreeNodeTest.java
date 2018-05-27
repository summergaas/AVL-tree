/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bdmyers
 */
public class TreeNodeTest {


    //create the trees that I'm expecting using bulkInsert
		@Test
                public void removeA(){
			int[] treeVals = {12, 24, 64, 15, 2000, 89, 0, 23, 18, 1, 1000, 5, 9};
			TreeNode root = TreeNode.bulkInsert(treeVals);
                        assertEquals(root, root.removeA(99)); //value not in tree
                        assertFalse(root.find0(9).isEmpty());//before removing leaf 9
                        root = root.removeA(9); //leaf
                        assertTrue(root.find0(9).isEmpty()); //shows that leaf 9 is gone
                        int[] ex1 = {12, 24, 64, 15, 2000, 89, 0, 23, 18, 1, 1000, 5};
                        assertEquals(TreeNode.bulkInsert(ex1), root);
                        assertFalse(root.find0(12).isEmpty());//before removing root 12
                        assertEquals(root.getData(), 12); //shows that 12 is the root
                        root = root.removeA(12); //root 
                        assertTrue(root.find0(12).isEmpty()); //shows that root 12 is gone
                        assertEquals(root.getData(), 5); //shows that 5 is the new root
                        int[] ex2 = {5, 24, 64, 15, 2000, 89, 0, 23, 18, 1, 1000};
                        assertEquals(TreeNode.bulkInsert(ex2), root);
                        assertFalse(root.find0(64).isEmpty());//before removing internal node 64 (node 64 has no child on the left, but does have a child on the right.)
                        assertEquals(root.find0(64).getRight(), root.find0(2000)); //shows that 2000 is the right child of 64
                        root = root.removeA(64); //no left child
                        assertTrue(root.find0(64).isEmpty()); //shows that internalnode 64 is gone
                        int[] ex3 = {5, 24, 15, 2000, 89, 0, 23, 18, 1, 1000};                        
                        assertEquals(TreeNode.bulkInsert(ex3), root);
                        assertEquals(root.find0(24).getRight(), root.find0(2000));    //shows that 2000 is now the right child of 24, and that 24 is in the tree
                        assertEquals(root.find0(24).getLeft(), root.find0(15)); //shows that 15 is the left child of 24
                        root = root.removeA(24); //two children
                        assertTrue(root.find0(24).isEmpty()); //shows that node 24 is gone                        
                        int[] ex4 = {5, 23, 15, 2000, 89, 0, 18, 1, 1000};                        
                        assertEquals(TreeNode.bulkInsert(ex4), root);
                        root = root.removeA(2000); //no right child
                        int[] ex5 = {5, 23, 15, 89, 0, 18, 1, 1000};                        
                        assertEquals(TreeNode.bulkInsert(ex5), root);                             
                        root= root.insert(3);
                        root = root.insert(2);
                        root = root.removeA(1);
                        int[] ex6 = {5, 23, 15, 18, 89, 0, 1000, 3, 2};                        
                        assertEquals(TreeNode.bulkInsert(ex6), root);
                        root = root.removeA(5);
                        root = root.removeA(23);
                        root = root.removeA(15);
                        root = root.removeA(18);
                        root = root.removeA(89);
                        root = root.removeA(0);
                        root = root.removeA(1000);
                        root = root.removeA(3);
                        root = root.removeA(2);
                        assertTrue(root.isEmpty());
                    //Still Don't trust my removeB - trying a different tree
                    //Second tree helped me to catch the problem of when the max node of the left tree is the node directly left of the node being removed
                    //Second tree was necessary
                    int[] r2 = {14, 10, 8, 12, 20, 18, 22, 16, 19, 21, 23, 6, 7, 11, 13};
                    TreeNode root2 = TreeNode.bulkInsert(r2);
                    root2 = root2.removeA(10);
                    int[] r2ex = {14, 8, 12, 20, 18, 22, 16, 19, 21, 23, 6, 7, 11, 13};
                    assertEquals(TreeNode.bulkInsert(r2ex), root2);
                    root2 = root2.removeA(8);
                    int[] r2ex2 = {14, 20, 18, 22, 16, 19, 21, 23, 7, 6, 12, 11, 13};
                    assertEquals(TreeNode.bulkInsert(r2ex2), root2);
                        root2 = root2.removeA(14);
                        root2 = root2.removeA(20);
                        root2 = root2.removeA(18);
                        root2 = root2.removeA(22);
                        root2 = root2.removeA(16);
                        root2 = root2.removeA(19);
                        root2 = root2.removeA(21);
                        root2 = root2.removeA(23);
                        root2 = root2.removeA(7);
                        root2 = root2.removeA(6);
                        root2 = root2.removeA(12);
                        root2 = root2.removeA(13);
                        root2 = root2.insert(15);
                        root2 = root2.insert(18);
                        root2 = root2.removeA(11);
                        root2 = root2.removeA(15);
                        root2 = root2.removeA(18);                        
                        assertTrue(root2.isEmpty());
                    
                }
                
//get in the habit of assigning the new root of the tree to something

		@Test
                public void removeB(){
			int[] treeVals = {12, 24, 64, 15, 2000, 89, 0, 23, 18, 1, 1000, 5, 9};
			TreeNode root = TreeNode.bulkInsert(treeVals);
                        assertEquals(root, root.removeB(99)); //value not in tree
                        assertFalse(root.find0(9).isEmpty());//before removing leaf 9
                        root = root.removeB(9); //leaf
                        assertTrue(root.find0(9).isEmpty()); //shows that leaf 9 is gone
                        int[] ex1 = {12, 24, 64, 15, 2000, 89, 0, 23, 18, 1, 1000, 5};
                        assertEquals(TreeNode.bulkInsert(ex1), root);
                        assertFalse(root.find0(12).isEmpty());//before removing root 12
                        assertEquals(root.getData(), 12); //shows that 12 is the root
                        root = root.removeB(12); //root 
                        assertTrue(root.find0(12).isEmpty()); //shows that root 12 is gone
                        assertEquals(root.getData(), 5); //shows that 5 is the new root
                        int[] ex2 = {5, 24, 64, 15, 2000, 89, 0, 23, 18, 1, 1000};
                        assertEquals(TreeNode.bulkInsert(ex2), root);
                        assertFalse(root.find0(64).isEmpty());//before removing internal node 64 (node 64 has no child on the left, but does have a child on the right.)
                        assertEquals(root.find0(64).getRight(), root.find0(2000)); //shows that 2000 is the right child of 64
                        root = root.removeB(64); //no left child
                        assertTrue(root.find0(64).isEmpty()); //shows that internalnode 64 is gone
                        int[] ex3 = {5, 24, 15, 2000, 89, 0, 23, 18, 1, 1000};                        
                        assertEquals(TreeNode.bulkInsert(ex3), root);
                        assertEquals(root.find0(24).getRight(), root.find0(2000));    //shows that 2000 is now the right child of 24, and that 24 is in the tree
                        assertEquals(root.find0(24).getLeft(), root.find0(15)); //shows that 15 is the left child of 24
                        root = root.removeB(24); //two children
                        assertTrue(root.find0(24).isEmpty()); //shows that node 24 is gone                        
                        int[] ex4 = {5, 23, 15, 2000, 89, 0, 18, 1, 1000};                        
                        assertEquals(TreeNode.bulkInsert(ex4), root);
                        root = root.removeB(2000); //no right child
                        int[] ex5 = {5, 23, 15, 89, 0, 18, 1, 1000};                        
                        assertEquals(TreeNode.bulkInsert(ex5), root);                             
                        root= root.insert(3);
                        root = root.insert(2);
                        root = root.removeB(1);
                        int[] ex6 = {5, 23, 15, 18, 89, 0, 1000, 3, 2};                        
                        assertEquals(TreeNode.bulkInsert(ex6), root);
                        root = root.removeB(5);
                        root = root.removeB(23);
                        root = root.removeB(15);
                        root = root.removeB(18);
                        root = root.removeB(89);
                        root = root.removeB(0);
                        root = root.removeB(1000);
                        root = root.removeB(3);
                        root = root.removeB(2);
                        assertTrue(root.isEmpty());
                    //Still Don't trust my removeB - trying a different tree
                    //Second tree helped me to catch the problem of when the max node of the left tree is the node directly left of the node being removed
                    //Second tree was necessary
                    int[] r2 = {14, 10, 8, 12, 20, 18, 22, 16, 19, 21, 23, 6, 7, 11, 13};
                    TreeNode root2 = TreeNode.bulkInsert(r2);
                    root2 = root2.removeB(10);
                    int[] r2ex = {14, 8, 12, 20, 18, 22, 16, 19, 21, 23, 6, 7, 11, 13};
                    assertEquals(TreeNode.bulkInsert(r2ex), root2);
                    root2 = root2.removeB(8);
                    int[] r2ex2 = {14, 20, 18, 22, 16, 19, 21, 23, 7, 6, 12, 11, 13};
                    assertEquals(TreeNode.bulkInsert(r2ex2), root2);
                        root2 = root2.removeB(14);
                        root2 = root2.removeB(20);
                        root2 = root2.removeB(18);
                        root2 = root2.removeB(22);
                        root2 = root2.removeB(16);
                        root2 = root2.removeB(19);
                        root2 = root2.removeB(21);
                        root2 = root2.removeB(23);
                        root2 = root2.removeB(7);
                        root2 = root2.removeB(6);
                        root2 = root2.removeB(12);
                        root2 = root2.removeB(13);
                        root2 = root2.insert(15);
                        root2 = root2.insert(18);
                        root2 = root2.removeB(11);
                        root2 = root2.removeB(15);
                        root2 = root2.removeB(18);
                        assertTrue(root2.isEmpty());
                  
                }


		
		@Test
		public void testSize() {
				TreeNode t = new TreeNode(10);
				assertEquals(1, t.size());
				t.setLeft(new TreeNode(20));
				assertEquals(2, t.size());
		}

		@Test
		public void testInsert() {
			TreeNode n = new TreeNode(13);
			n.setRight(new TreeNode(20));
			n.getRight().setRight(new TreeNode(25));
			n.setLeft(new TreeNode(3));
			int[] ex = {13,20,3,25};
			assertEquals(n, TreeNode.bulkInsert(ex));
		}
                
		@Test
		public void testKeyRange() {
			int[] ex = {100,50,150,25,75,125,175,60,79};
			TreeNode root = TreeNode.bulkInsert(ex);
			Iterator<Integer> iter = root.keysInRange(54, 127);
			
			assertTrue(iter.hasNext());
			assertEquals((Integer)60, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)75, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)79, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)100, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)125, iter.next());
		}
		
		@Test
		public void testKeyRange2() {
			int[] ex = {100,50,150,25,75,125,175,60,79};
			TreeNode root = TreeNode.bulkInsert(ex);
			Iterator<Integer> iter = root.keysInRange(50, 100);
			
			assertTrue(iter.hasNext());
			assertEquals((Integer)50, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)60, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)75, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)79, iter.next());
		}
	
		@Test
		public void testKeyRange3() {
			int[] ex = {100,50,150,25,75,125,175,60,79};
			TreeNode root = TreeNode.bulkInsert(ex);
			Iterator<Integer> iter = root.keysInRange(100, 250);
			
			assertTrue(iter.hasNext());
			assertEquals((Integer)100, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)125, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)150, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)175, iter.next());
        }
		
}
