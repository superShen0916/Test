package compleablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-15
 */
public class TestCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f = future.thenAcceptBoth(CompletableFuture.completedFuture(10),
                (x, y) -> System.out.println(x * y));
        f.get();
        //        System.out.println(f.get());
    }
}
