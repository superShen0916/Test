package readlog;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 活动类型枚举类
 * @Author: shenpeng
 * @Date: 2018/12/26
 */
public enum ActivityType {

    normalBlock(0, "normalBlock"),

    eliteBlock(1, "eliteBlock"),

    nightBlock(2, "nightBlock"),

    towerBlock(3, "towerBlock"),

    expBlock(4, "expBlock"),

    favorBlock(5, "favorBlock"),

    goldBlock(6, "goldBlock"),

    equipBlock(7, "equipBlock"),

    arena(8, "arena"),

    saitamaTask(9, "saitamaTask"),

    unionTask(10, "unionTask"),

    unionHelp(11, "unionHelp"),

    unionExplore(12, "unionExplore"),

    ;

    private int type;

    private String name;

    private static Map<Integer, String> map = Maps.newHashMap();

    static {
        Arrays.stream(ActivityType.values()).forEach(type -> map.put(type.type, type.name));
    }

    ActivityType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type) {
        return map.get(type);
    }
}
