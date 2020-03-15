package clone;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-03-14
 */

class Head implements Cloneable {

    int high;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Head(int high) {
        this.high = high;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }
}

public class Person implements Cloneable {

    private static final long serialVersionUID = 120904406677219163L;

    Head head;

    public Person() {
        head = new Head(2);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        p.head = (Head) head.clone();
        return p;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person();
        Person p2 = (Person) p1.clone();
        p1.head.setHigh(1);
        System.out.println(p2.head.high);
    }
}
