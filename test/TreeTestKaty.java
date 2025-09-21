import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTestKaty {

    @Test
    public void test1() { //adding and removing
        Tree[] b = {};
        Tree[] b2 = {};
        Tree[] b3 = {new Tree(3, b), new Tree(4, b2)};
        Tree t = new Tree(1, b3);
        assertEquals(t.size(), 3);
        t.add(5);
        t.add(6);
        t.add(7);
        t.add(8);
        assertEquals(7, t.size());
        assertTrue(t.contains(1));
        assertTrue(t.contains(3));
        assertTrue(t.contains(4));
        assertTrue(t.contains(5));
        assertTrue(t.contains(6));
        assertTrue(t.contains(7));
        assertTrue(t.contains(8));

        assertFalse(t.remove(2));
        t.remove(5);
        assertFalse(t.contains(5));
        t.remove(1);
        assertFalse(t.contains(1));
        t.remove(3);
        assertFalse(t.contains(3));
        assertEquals(4,  t.size());

        t.remove(6);
        t.remove(7);
        t.remove(4);
        assertEquals(8, t.root);

        t.remove(8);
        assertTrue(t.isEmpty());

        Tree t2 = new Tree();
        assertFalse(t2.remove(1));
        t2. add(1);
        assertEquals(1, t2.root);
        assertEquals(1, t2.size());
        assertEquals(1, t2.average());

        t2.addChild(2, 1);
        assertEquals(1, t2.root);
        assertTrue(t2.contains(2));
        assertEquals(2, t2.size());

        assertEquals(t2.average(), 1.5);
        assertEquals(t.average(), 0);

        t2.add(2);
        assertEquals(2, t2.count(2));
    }

    @Test
    public void test2() {
        Tree t = new Tree();
        t.add(1);
        t.addChild(3,1);
        t.addChild(5,3);
        t.addChild(6,3);
        t.addChild(7,3);
        t.addChild(4,1);
        t.addChild(8,6);

        assertEquals(7, t.size());

        int[] leaves = t.leaves();
        assertEquals(4, leaves.length);

        Arrays.sort(leaves);
        assertEquals(4, leaves[0]);
        assertEquals(5, leaves[1]);
        assertEquals(7, leaves[2]);
        assertEquals(8, leaves[3]);

        Tree t2 = new Tree();
        t2.add(1);
        t2.addChild(3,1);
        t2.addChild(5,3);
        t2.addChild(6,3);
        t2.addChild(7,3);
        t2.addChild(4,1);
        t2.addChild(8,6);
        assertTrue(t.equals(t2));

        int l = t.extractLeaf();
        assertEquals(5, l);
        assertFalse(t.contains(5));
    }
}