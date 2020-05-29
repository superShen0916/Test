/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-16
 */
public class TestData {

    String name;

    String gender;

    public TestData(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public TestData(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }
}
