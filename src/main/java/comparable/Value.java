package comparable;

import com.playcrab.common.leaderboard.lbs.ScoreLb;
import com.playcrab.kos.common.leaderboard.Leaderboard;
import com.playcrab.kos.gs.enums.LbType;

/**
 * @Description: value class
 * @Author: shenpeng
 * @Date: 2019/1/22
 */
public class Value implements Comparable<Value> {

    int value;

    int extra;

    Value(int value, int extra) {
        this.value = value;
        this.extra = extra;
    }

    @Override
    public int compareTo(Value value1) {
        if (value1.value > this.value) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        ScoreLb hurtLb = Leaderboard.getInstance().getScoreLb(LbType.SPRING_BOSS);
        hurtLb.getSize();

    }

    @Override
    public String toString() {
        return "value: " + value + "   extra: " + extra;
    }
}
