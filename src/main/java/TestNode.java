
/**
 * Copyright (c) 2018 playcrab.All rights reserved.
 */

/**
 * 测试引用传递，move()方法虽然接受的是node(1)的引用，但并没有改变它的值，只是改变了方法里局部变量的值
 *
 * @date 2018-05-08
 * @author shenpeng
 */
public class TestNode {

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
        System.out.println(node.value);//1
        move(node);//3
        move(node);//3
        System.out.println(node.value);//1
    }

    public static void move(Node node) {
        node = node.next;
        node = node.next;
        System.out.println(node.value);
    }
}
