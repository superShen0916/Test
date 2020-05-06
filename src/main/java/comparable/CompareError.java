package comparable;

import com.google.common.collect.Maps;

import java.util.*;

/**
 * @Description: 线上自定义comparable的一个报错
 * @Author: shenpeng
 * @Date: 2019-10-23
 */
public class CompareError {

    public static void main(String[] args) {
        //sortList是线上玩家成就背包里的数据，key是成就徽章id，value是徽章获取时间
        String s = "A04S14U01=1564470055475, A04S01U06=1564477138883, A04S11U01=1564555656906, A01S30U01=1564814491383, A02S03U01=1567243722125, A01S12U01=1567243723201, A01S19U01=1567243724129, A01S21U01=1567243726368, A01S28U01=1567306939345, A02S04U01=1567848445084, A01S25U01=1567878817594, A02S10U01=1567878818480, A01S31U03=1567878930712, A03S10U01=1568282831744, A01S23U02=1568282832830, A01S11U03=1568451428666, A03S01U02=1568451432458, A03S03U02=1568451433595, A04S03U05=1568451436624, A02S06U02=1569577574082, A01S32U02=1569897326352, A01S18U03=1569897327384, A03S04U04=1569988120348, A03S02U05=1569988121433, A01S20U01=1570697179561, A01S24U01=1570697180636, A02S05U02=1570697181690, A01S22U01=1570697184760, A04S05U06=1570697191131, A01S29U03=1570697192030, A01S03U03=1570867431834, A01S26U03=1571084897659, A02S01U02=1571198541973, A04S08U01=1571198543952, A01S02U03=1571397051647, A03S08U01=1571447967347, A03S09U01=1571447968316, A01S08U04=1571456441346, A01S01U02=1571456442462, A03S07U01=1571456445842, A04S10U02=1571456448157, A02S08U03=1571488612077, A02S09U02=1571488615179, A04S12U03=1571488616189, A01S27U03=1571488617335, A01S36U03=1571488618303, A02S02U02=1571716765705, A01S35U03=1571716766775, A01S37U03=1571716767865, A04S06U05=1571716770089, A01S07U03=1571716771111, A01S17U03=1571716772573, A01S34U03=1571816962392";
        List<String> listTemp = Arrays.asList(s.split("=|,"));
        System.out.println(listTemp.size());
        Map<String, Long> achPics = Maps.newHashMap();
        for (int i = 0; i < listTemp.size(); i += 2) {
            achPics.put(listTemp.get(i), Long.valueOf(listTemp.get(i + 1)));
        }
        List<Map.Entry<String, Long>> sortList = new ArrayList<>(achPics.entrySet());
        Collections.sort(sortList, new Comparator<Map.Entry<String, Long>>() {

            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                System.out.println(o2.getValue() + "  " + o1.getValue() + "   "
                        + (o2.getValue() - o1.getValue()));

                //会报 Comparison method violates its general contract!
                return (int) (o2.getValue() - o1.getValue());

                //考虑比较对象为null的情况同样会报错
                //                if (o1 == null || o2 == null) {
                //                    return 0;
                //                } else {
                //                    return (int) (o2.getValue() - o1.getValue());
                //                }
                //                //                                } else if (o2.getValue().equals(o1.getValue())) {
                //                //                                    return 0;
                //                //                                } else if (o2.getValue() > (o1.getValue())) {
                //                //                                    return  (int) (o2.getValue() - o1.getValue());
                //                //                                } else if (o2.getValue() < (o1.getValue())) {
                //                //                                    return  (int) (o2.getValue() - o1.getValue());
                //                //                                } else {
                //                //                                    return 0;
                //                //                                }

                //返回0/1/-1就不会报错
                //                if (o1 == null || o2 == null) {
                //                    return 0;
                //                } else if (o2.getValue().equals(o1.getValue())) {
                //                    return 0;
                //                } else if (o2.getValue() > (o1.getValue())) {
                //                    return 1;
                //                } else if (o2.getValue() < (o1.getValue())) {
                //                    return -1;
                //                } else {
                //                    return 0;
                //                }
            }
        });
    }

}
