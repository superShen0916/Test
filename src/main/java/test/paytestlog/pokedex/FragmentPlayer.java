package test.paytestlog.pokedex;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 玩家昵称和他身上的碎片
 * @Author: shenpeng
 * @Date: 2019-05-08
 */
public class FragmentPlayer {

    private String name = "";

    Map<String, Integer> fragments = Maps.newHashMap();

    FragmentPlayer(String name) {
        this.name = name;
    }

    public void addFragment(String fragId, int count) {
        fragments.put(fragId, fragments.getOrDefault(fragId, 0) + count);
    }

    public void reduceFragment(String fragId, int count) {
        fragments.put(fragId, fragments.getOrDefault(fragId, 0) - count);
    }

    @Override
    public String toString() {
        return name + ":           " + fragments.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getFragments() {
        return fragments;
    }

    public void setFragments(Map<String, Integer> fragments) {
        this.fragments = fragments;
    }
}
