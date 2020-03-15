package paytest;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 23日注册且24日没登录的玩家
 * @Author: shenpeng
 * @Date: 2019-04-29
 */
public class LogPlayer {

    private int level = 1;

    /**
     * rid
     */
    private String r;

    /**
     * 普通关卡最高id
     */
    private String m;

    /**
     * 精英关卡最高id
     */
    private String e;

    /**
     * 游戏总时长
     */
    private long t;

    private Map<Integer, Long> l_t = Maps.newHashMap();

    private List<String> ts;

    //    private static Pattern p0 = Pattern.compile("\\{(\\{(.*)\\})*");
    //
    //    public static void main(String[] args) {
    //        String str = "asdad{{asda{s}sdda";
    //        Matcher m = p0.matcher(str);
    //        System.out.println(m.find());
    //    }

    @Override
    public String toString() {
        return String.format("%s   %s   %s    %s   %s   %s   \n", r, m, e, t, ts.toString(),
                l_t.toString());
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

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public Map<Integer, Long> getL_t() {
        return l_t;
    }

    public void setL_t(Map<Integer, Long> l_t) {
        this.l_t = l_t;
    }

    public List<String> getTs() {
        return ts;
    }

    public void setTs(List<String> ts) {
        this.ts = ts;
    }

    public void addTs(String taskId) {
        this.ts.add(taskId);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
