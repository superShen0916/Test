import com.playcrab.common.config.RuntimeCfg;
import com.playcrab.common.dataconfig.DataConfigService;
import com.playcrab.kos.common.dataconfig.NormalBlock;

public class MyClient {

    public static void main(String[] args) {
        System.out.println("java.home : " + System.getProperty("java.home"));

        System.out.println("java.class.version : " + System.getProperty("java.class.version"));

        System.out.println("java.class.path : " + System.getProperty("java.class.path"));

        System.out.println("java.library.path : " + System.getProperty("java.library.path"));

        System.out.println("java.io.tmpdir : " + System.getProperty("java.io.tmpdir"));

        System.out.println("java.compiler : " + System.getProperty("java.compiler"));

        System.out.println("java.ext.dirs : " + System.getProperty("java.ext.dirs"));

        System.out.println("user.name : " + System.getProperty("user.name"));

        System.out.println("user.home : " + System.getProperty("user.home"));

        System.out.println("user.dir : " + System.getProperty("user.dir"));
        try {
            //    System.out.println(1 / 0);

            RuntimeCfg.instance = new RuntimeCfg();
            System.out.println(DataConfigService.getDataConfigIdList(NormalBlock.class).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(111);
    }
}
