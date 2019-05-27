package test.readlog;

import java.util.Map;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019-04-27
 */
public class BlockPlayer {

    private String n;

    private String e;

    private String m;

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public void updateN(String blockId, Map<String, Integer> order) {
        if (this.n != null && order.get(blockId) <= order.get(this.n)) {
            return;
        } else {
            this.n = blockId;
        }
    }

    public void updateE(String blockId, Map<String, Integer> order) {
        if (this.e != null && order.get(blockId) <= order.get(this.e)) {
            return;
        } else {
            this.e = blockId;
        }
    }

    public void updateM(String blockId, Map<String, Integer> order) {
        if (this.m != null && order.get(blockId) <= order.get(this.m)) {
            return;
        } else {
            this.m = blockId;
        }
    }
}
