/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bdmyers
 */
public class AVLTreeTest {
		
		public AVLTreeTest() {
		}

		@Test
		public void testIsEmpty() {
				AVLTree t = new AVLTree(); 
				assertTrue(t.isEmpty());
		}

		@Test
		public void testInsertBalancedOrder() {
				AVLTree t = new AVLTree(); 
				t.insert(50);
				assertEquals(t.getHeight(), 1);
				t.insert(25);
				assertEquals(t.getHeight(), 2);
				t.insert(75);
				assertEquals(t.getHeight(), 2);
				t.insert(12);
				assertEquals(t.getHeight(), 3);
				t.insert(37);
				assertEquals(t.getHeight(), 3);
				t.insert(62);
				assertEquals(t.getHeight(), 3);
				t.insert(87);
				t.checkBalance();
				t.checkIsBST();
				assertEquals(t.getHeight(), 3);
		}
		
		@Test
		public void testInsertDecreasing() {
				AVLTree t = new AVLTree(); 
				t.insert(75);
				assertEquals(t.getHeight(), 1);
				t.insert(50);
				assertEquals(t.getHeight(), 2);
				t.insert(25);
				t.checkBalance();
				t.checkIsBST();
				assertEquals(t.getHeight(), 2);
				assertEquals(t.getRoot().getData(), 50);
		}

		@Test
		public void testInsertIncreasing() {
				AVLTree t = new AVLTree(); 
				t.insert(25);
				assertEquals(t.getHeight(), 1);
				t.insert(50);
				assertEquals(t.getHeight(), 2);
				t.insert(75);
				assertEquals(t.getHeight(), 2);
				int[] ex = {50,25,75};
				assertEquals(TreeNode.bulkInsert(ex), t.getRoot());
				t.insert(100);
				assertEquals(t.getHeight(), 3);
				assertEquals(t.getRoot().getData(), 50);
				t.insert(125);
				t.checkBalance();
				t.checkIsBST();
				int[] ex2 = {50,25,100,75,125};
				assertEquals(TreeNode.bulkInsert(ex2), t.getRoot());
				assertEquals(t.getHeight(), 3);
		}

		@Test
		public void testInsertSimpleRotateLeftRight() {
				AVLTree t = new AVLTree(); 
				t.insert(50);
				assertEquals(t.getHeight(), 1);
				t.insert(25);
				assertEquals(t.getHeight(), 2);
				t.insert(30);
				t.checkBalance();
				t.checkIsBST();
				assertEquals(t.getHeight(), 2);
				int[] ex = {30,25,50};
				assertEquals(TreeNode.bulkInsert(ex), t.getRoot());
		}
		
		@Test
		public void testInsertSimpleRotateRightLeft() {
				AVLTree t = new AVLTree(); 
				t.insert(50);
				assertEquals(t.getHeight(), 1);
				t.insert(75);
				assertEquals(t.getHeight(), 2);
				t.insert(60);
				t.checkBalance();
				t.checkIsBST();
				assertEquals(t.getHeight(), 2);
				int[] ex = {60,50,75};
				assertEquals(TreeNode.bulkInsert(ex), t.getRoot());
				assertEquals(t.getRoot().getData(), 60);
				assertEquals(t.getRoot().getLeft().getData(), 50);
				assertEquals(t.getRoot().getRight().getData(), 75);
		}
		
		@Test
		public void testInsertComplexRotateLeftRight() {
				AVLTree t = new AVLTree(); 
				t.insert(44);
				t.insert(17);
				t.insert(78);
				t.insert(32);
				t.insert(50);
				t.insert(88);
				t.insert(48);
				t.insert(62);
				t.insert(54);
				t.checkBalance();
				t.checkIsBST();
				int[] ex = {44,17,62,32,50,78,48,54,88};
				assertEquals(TreeNode.bulkInsert(ex), t.getRoot());
		}


		@Test
		public void testRemoveRoot1() {
				AVLTree t = new AVLTree(); 
				t.insert(44);
				t.remove(44);
				assertTrue(t.isEmpty());
				t.checkBalance();
				t.checkIsBST();
		}
		@Test
		public void testRemoveRoot2() {
				AVLTree t = new AVLTree(); 
				t.insert(50);
				t.remove(25);
				t.remove(50);
				assertTrue(t.isEmpty());
				t.checkBalance();
				t.checkIsBST();
		}
		@Test
		public void testRemoveRoot3() {
				AVLTree t = new AVLTree(); 
				t.insert(50);
				t.insert(25);
				t.insert(75);
				t.remove(50);
				assertTrue(t.getRoot().getData()==25 || t.getRoot().getData()==75);
				t.checkBalance();
				t.checkIsBST();
		}
		@Test 
		public void testRemoveComplex() {
			AVLTree t = new AVLTree();
				t.insert(44);
				t.insert(17);
				t.insert(62);
				t.insert(32);
				t.insert(50);
				t.insert(78);
				t.insert(48);
				t.insert(54);
				t.insert(88);
				t.remove(32);
				t.checkBalance();
				t.checkIsBST();
				assertEquals(t.getHeight(), 4);
				int[] ex = {62,44,78,17,50,88,48,54};
				assertEquals(TreeNode.bulkInsert(ex), t.getRoot());
		}

		@Test 
		public void testRemoveComplex2() {
			AVLTree t = new AVLTree();
			t.insert(100);
			t.insert(50);
			t.insert(200);
			t.insert(30);
			t.insert(75);
			t.insert(150);
			t.insert(250);
			t.insert(60);
			t.insert(125);
			t.insert(175);
			t.insert(300);
			t.insert(160);
			t.checkBalance();
			t.checkIsBST();
			t.getRoot().printTree();
			t.remove(300);
			t.getRoot().printTree();
			t.checkBalance();
			t.checkIsBST();
			assertEquals(t.getHeight(), 4);
			int[] ex = {100,50,175,75,150,200,30,60,125,160,250};
			assertEquals(TreeNode.bulkInsert(ex), t.getRoot());
		}
		
}
