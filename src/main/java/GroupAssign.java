
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAssign {

    public static void main(String[] args) {

        Map<Integer, Group> groups;

        groups = simGroupAssign(32, 4, 12, 5, 24, 52);

        System.out.println();
        groups = simGroupAssign(32, 4, 16, 5, 20, 56);

    }

    /**
     *
     * @param gNumTotal 总小组数量
     * @param gNumA A型小组数量
     * @param gNumB B型小组数量
     * @param boundA1 随机A型小组第1个范围
     * @param boundB1 随机B型小组第1个范围
     * @param boundA2B2C1 随机A型B型第2个、C型第3个范围
     * @return 分组结果
     */
    static Map<Integer, Group> simGroupAssign(int gNumTotal, int gNumA, int gNumB, int boundA1,
            int boundB1, int boundA2B2C1) {
        int groupNum = gNumTotal;
        int groupNumA = gNumA;
        int groupNumB = gNumB;
        int groupNumC = groupNum - groupNumA - groupNumB;

        Map<Integer, Group> groups = new HashMap<Integer, Group>();
        //A型小组
        Map<Integer, Group> aGroups = new HashMap<Integer, Group>();
        //B型小组
        Map<Integer, Group> bGroups = new HashMap<Integer, Group>();
        //C型小组
        Map<Integer, Group> cGroups = new HashMap<Integer, Group>();

        //初始化各类型小组
        Group tmpGroup;
        int totalIndex = 0;
        for (int i = 0; i < groupNumA; i++) {
            tmpGroup = new Group("A" + (i + 1), totalIndex + i + 1);
            groups.put(totalIndex + i + 1, tmpGroup);
            aGroups.put(i + 1, tmpGroup);
        }
        totalIndex += groupNumA;
        for (int i = 0; i < groupNumB; i++) {
            tmpGroup = new Group("B" + (i + 1), totalIndex + i + 1);
            groups.put(totalIndex + i + 1, tmpGroup);
            bGroups.put(i + 1, tmpGroup);
        }
        totalIndex += groupNumB;
        for (int i = 0; i < groupNumC; i++) {
            tmpGroup = new Group("C" + (i + 1), totalIndex + i + 1);
            groups.put(totalIndex + i + 1, tmpGroup);
            cGroups.put(i + 1, tmpGroup);
        }

        int total = 128;
        MemberAssign playersAssign = new MemberAssign(128);

        int tmpId;
        int curBound;
        //分配A型小组第1个
        curBound = boundA1;
        for (int i = 0; i < groupNumA; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            aGroups.get(i + 1).addMember(tmpId);
        }
        //分配B型小组第1个
        curBound = boundB1;
        for (int i = 0; i < groupNumB; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            bGroups.get(i + 1).addMember(tmpId);
        }

        curBound = boundA2B2C1;
        //分配B型小组第2个
        for (int i = 0; i < groupNumB; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            bGroups.get(i + 1).addMember(tmpId);
        }
        //分配C型小组第1个
        for (int i = 0; i < groupNumC; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            cGroups.get(i + 1).addMember(tmpId);
        }
        //分配A型小组第2个
        for (int i = 0; i < groupNumA; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            aGroups.get(i + 1).addMember(tmpId);
        }

        curBound = total / 2;
        //分配C型小组第2个
        for (int i = 0; i < groupNumC; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            cGroups.get(i + 1).addMember(tmpId);
        }

        //分配A、B、C型小组第3~4个
        curBound = total;
        for (int i = 0; i < groupNumA; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            aGroups.get(i + 1).addMember(tmpId);
        }
        for (int i = 0; i < groupNumB; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            bGroups.get(i + 1).addMember(tmpId);
        }
        for (int i = 0; i < groupNumC; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            cGroups.get(i + 1).addMember(tmpId);
        }
        for (int i = 0; i < groupNumA; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            aGroups.get(i + 1).addMember(tmpId);
        }
        for (int i = 0; i < groupNumB; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            bGroups.get(i + 1).addMember(tmpId);
        }
        for (int i = 0; i < groupNumC; i++) {
            tmpId = playersAssign.assignWithinBound(curBound);
            cGroups.get(i + 1).addMember(tmpId);
        }

        for (int i = 1; i <= 32; i++) {
            System.out.println(groups.get(i));

        }

        return groups;
    }

}

/**
 * 分组名单
 * 
 * @author playcrab
 *
 */
class Group {

    List<Integer> member = new ArrayList<Integer>();

    String tag;

    int id;

    public int getId() {
        return id;
    }

    public Group(String tag, int id) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    void addMember(int i) {
        member.add(i);
    }

    //用于打印分组结果
    @Override
    public String toString() {
        //		return "{"+id+":" +tag+ member.toString()+"}\t";
        return tag + "\t" + member.toString();
        //		return  member.toString();
    }
}

/**
 * 通用分组算法
 * 
 * @author playcrab
 *
 */
class MemberAssign {

    List<Integer> leftMembers = new ArrayList<Integer>();

    int minId = 1;

    public MemberAssign(int numb) {
        for (int i = 1; i <= numb; i++) {
            leftMembers.add(i);
        }
    }

    /**
     * 在剩余bound范围内member中，随机分出1个
     * 
     * @param bound
     * @return
     */
    public int assignWithinBound(int bound) {
        int rangeNum = 0;
        for (int i = 0; i < leftMembers.size() && leftMembers.get(i) <= bound; i++) {
            rangeNum++;
        }
        int index = (int) Math.floor(Math.random() * rangeNum);
        if (rangeNum == 0) {
            return 0;
        } else {
            //			System.out.println("assignWithinBound ===> "+leftMembers.size()+":"+index);
            int assignedId = leftMembers.get(index);
            minId = minId == assignedId ? assignedId : minId;
            leftMembers.remove(index);
            return assignedId;
        }
    }
}
