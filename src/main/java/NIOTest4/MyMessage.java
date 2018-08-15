package NIOTest4;

import java.io.Serializable;

public class MyMessage implements Serializable {

    /****/
    private static final long serialVersionUID = 5570201892267872279L;
    // private Date date;// 时间
    private int command;// 指令
    private byte[] contents;// 内容

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public int length() {
        return contents.length;
    }

    // public Date getDate() {
    // return date;
    // }
    //
    // public void setDate(Date date) {
    // this.date = date;
    // }
}