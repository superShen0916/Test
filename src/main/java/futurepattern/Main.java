package futurepattern;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.requset("name");
        System.out.println("请求完毕");
        System.out.println(data.getResult());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(data.getResult());
    }
}
