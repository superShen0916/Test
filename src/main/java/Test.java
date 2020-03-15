
/**
 * Copyright (c) 2018 playcrab.All rights reserved.
 */

/**
 * 
 *
 * @date 2018-05-08
 * @author shenpeng
 */
public class Test {

    static class Node {

        int value;

        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    static Node root = new Node(0);

    public static void main(String[] args) {
        Node node = root;
        for (int i = 0; i < 10; i++) {
            node.next = new Node(i);
            node = node.next;
        }

        node = root.next.next;
        System.out.println(node.value);
        move(node);
        move(node);
        System.out.println(node.value);
    }

    public static void move(Node node) {
        node = node.next;
        node = node.next;
        System.out.println(node.value);
    }
}
