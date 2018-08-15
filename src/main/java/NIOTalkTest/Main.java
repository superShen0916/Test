package NIOTalkTest;

import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
    	Scanner in=new Scanner(System.in);
        MinaClient client=new MinaClient();
        client.connect();
        String msg;
        	msg=in.nextLine();
        	client.sendMsg2Server(msg);
    }
}