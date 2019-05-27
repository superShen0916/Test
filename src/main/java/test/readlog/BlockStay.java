package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.playcrab.kos.common.dataconfig.NormalBlock;
import com.playcrab.kos.common.utils.KOSDataConfigService;

import net.gamebean.referencedlibraries.commons.lang.StringUtils;

/**
 * @Description: 每日普通精英首领的驻留情况
 * @Author: shenpeng
 * @Date: 2019-04-27
 */
public class BlockStay {

    private final static String path0 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-11-29.log";

    private final static String path1 = "/Volumes/macwin/action/cangjingge/BlockResult_2018-11-29.log";

    private final static String path2 = "/Volumes/macwin/action/cangjingge/BlockResult_2018-11-30.log";

    private final static String pathw = "/Users/playcrab/Desktop/log/blockStay-11-19.log";

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        String str = "";
        try {
            Map<String, BlockPlayer> info = Maps.newHashMap();
            String[] log;
            Map<String, Integer> order = Maps.newHashMap();
            Set<String> todayM = Sets.newLinkedHashSet();
            Set<String> todayN = Sets.newLinkedHashSet();
            Set<String> todayE = Sets.newLinkedHashSet();
            Map<String, Integer> MC = Maps.newHashMap();
            Map<String, Integer> NC = Maps.newHashMap();
            Map<String, Integer> EC = Maps.newHashMap();

            List<String> blockIds = KOSDataConfigService.getDataConfigIdList(NormalBlock.class);
            for (String id : blockIds) {
                order.put(id, blockIds.indexOf(id));
            }
            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                /*
                testcb|201|2018-11-29 23:59:50|testcb#30484_201|0|0|M01|M01P03|1|3
                testcb|201|2018-11-29 23:59:50|testcb#18783_201|0|1|EM03|EM03P05|1|3
                testcb|201|2018-11-29 23:59:50|testcb#29324_201|0|1|EM02|EM02P03|1|3
                testcb|201|2018-11-29 23:59:50|testcb#26757_201|0|2|NM01|NM01P01|1|3
                * */
                log = StringUtils.split(str, '|');
                if ("1".equals(log[8])) {
                    if (log[7].startsWith("M")) {
                        todayM.add(log[7]);
                        if (info.containsKey(log[0])) {
                            info.get(log[0]).updateM(log[7], order);
                        } else {
                            BlockPlayer blockPlayer = new BlockPlayer();
                            blockPlayer.setM(log[7]);
                            info.put(log[0], blockPlayer);
                        }
                    } else if (log[7].startsWith("N")) {
                        todayN.add(log[7]);
                        if (info.containsKey(log[0])) {
                            info.get(log[0]).updateN(log[7], order);
                        } else {
                            BlockPlayer blockPlayer = new BlockPlayer();
                            blockPlayer.setN(log[7]);
                            info.put(log[0], blockPlayer);
                        }
                    } else if (log[7].startsWith("E")) {
                        todayE.add(log[7]);
                        if (info.containsKey(log[0])) {
                            info.get(log[0]).updateE(log[7], order);
                        } else {
                            BlockPlayer blockPlayer = new BlockPlayer();
                            blockPlayer.setE(log[7]);
                            info.put(log[0], blockPlayer);
                        }
                    }
                }
            }
            br.close();
            System.out.println("==========" + info.size());
            for (String bId : todayM) {
                int count = 0;
                for (BlockPlayer pr : info.values()) {
                    if (bId.equals(pr.getM())) {
                        count++;
                    }
                }
                MC.put(bId, count);
            }
            for (String bId : todayN) {
                int count = 0;
                for (BlockPlayer pr : info.values()) {
                    if (bId.equals(pr.getN())) {
                        count++;
                    }
                }
                NC.put(bId, count);
            }
            for (String bId : todayE) {
                int count = 0;
                for (BlockPlayer pr : info.values()) {
                    if (bId.equals(pr.getE())) {
                        count++;
                    }
                }
                EC.put(bId, count);
            }

            int totalM = 0;
            int totalN = 0;
            int totalE = 0;
            for (BlockPlayer pr : info.values()) {
                if (pr.getM() != null) {
                    totalM++;
                }
                if (pr.getN() != null) {
                    totalN++;
                }
                if (pr.getE() != null) {
                    totalE++;
                }
            }

            //
            System.out.println("----" + totalM);
            System.out.println("----" + totalN);
            System.out.println("----" + totalE);
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathw));

            for (String bId : todayM) {
                bw.write(bId + "      " + totalM + "      " + 1.0 * MC.get(bId) / totalM + "\n");
                bw.flush();
                totalM -= MC.get(bId);
            }
            bw.write("\n\n\n\n\n");
            for (String bId : todayN) {
                bw.write(bId + "      " + totalN + "      " + 1.0 * NC.get(bId) / totalN + "\n");
                bw.flush();
                totalN -= NC.get(bId);
            }
            bw.write("\n\n\n\n\n");
            for (String bId : todayE) {
                bw.write(bId + "      " + totalE + "      " + 1.0 * EC.get(bId) / totalE + "\n");
                bw.flush();
                totalE -= EC.get(bId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--- " + str);
        }

    }
}
