package hotswap;

import java.io.IOException;
import java.nio.file.*;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-06
 */
public enum ClassFileReader {
    INSTANCE;

    private String path;

    public byte[] readClassFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        byte[] data = Files.readAllBytes(path);
        return data;
    }

    public void start(String path) throws IOException {
        this.path = path;
        WatchService watcher = FileSystems.getDefault().newWatchService();
        Path dir = Paths.get(path);
        dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);

        for (;;) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    String fileName = event.context().toString();
                    System.out.println("Path is modified:" + fileName);
                    if (fileName.endsWith(".class")) {
                        String className = fileName.substring(0, fileName.length() - 6);
                        System.out.println("reload className:" + className);
                        byte[] classContent = readClassFile(this.path + "/" + fileName);
                        System.out.println(new String(classContent));
                        try {
                            Class<?> clz = Class.forName(className);
                            HotSwapAgent.reload(clz, classContent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            continue;
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                    }
                }
            }

            if (!key.reset()) {
                break;
            }
        }
    }
}
