package httpclient.httpasyncclient;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-26
 */
public class HttpTest {

    //    public static void main(String[] args) {
    //        CloseableHttpAsyncClient httpClient = AsynHttpClientFactory.getCloseableHttpAsyncClient();
    //        final CountDownLatch latch = new CountDownLatch(urls.length);
    //        for(final HttpGet request: urls){
    //
    //            Future<HttpResponse> future =  httpClient.execute(request,  new FutureCallback(){
    //                @Override
    //                public void completed(Object obj) {
    //                    final HttpResponse response = (HttpResponse)obj;
    //                    latch.countDown();
    //                    PlatformInfo platformInfo =  map.get(request.getURI().toString());
    //                    if( response.getStatusLine().getStatusCode() == 200){
    //                        if(platformInfo != null){
    //                            platformInfo.setStatus(PlatformStatus.SUCCESS.getStatus());
    //                        }
    //                    }else{
    //                        if(platformInfo != null){
    //                            platformInfo.setStatus(PlatformStatus.ERROR.getStatus());
    //                        }
    //                    }
    //
    //                }
    //
    //                @Override
    //                public void failed(Exception excptn) {
    //                    latch.countDown();
    //                    PlatformInfo platformInfo =  map.get(request.getURI().toString());
    //                    if(platformInfo != null){
    //                        platformInfo.setStatus(PlatformStatus.ERROR.getStatus());
    //                    }
    //                }
    //
    //                @Override
    //                public void cancelled() {
    //                    latch.countDown();
    //                    PlatformInfo platformInfo =  map.get(request.getURI().toString());
    //                    if(platformInfo != null){
    //                        platformInfo.setStatus(PlatformStatus.ERROR.getStatus());
    //                    }
    //                }
    //
    //            });
    //           /* HttpResponse response = future.get();
    //            PlatformInfo platformInfo =  map.get(request.getURI().toString());
    //            if(response.getStatusLine().getStatusCode() == 200){
    //                if(platformInfo != null){
    //                    platformInfo.setStatus(PlatformStatus.SUCCESS.getStatus());
    //                }
    //            };*/
    //            request.releaseConnection();
    //        }
    //
    //        try {
    //            latch.await();
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //            logger.error("定时任务出错："+ex.getMessage());
    //        }
    //       /*
    //        finally{
    //            try {
    //                httpClient.close();
    //            }
    //            catch (IOException ex) {
    //                logger.error("定时任务出错："+ex.getMessage());
    //            }
    //        }*/
    //        /*try {
    //            httpClient.close();
    //        }
    //        catch (IOException ex) {
    //            logger.error("定时任务出错："+ex.getMessage());
    //        }*/
    //    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable() {

            @Override
            public Object call() throws Exception {
                System.out.println(1);
                Thread.sleep(10000);
                return 2;
            }
        });
        System.out.println(1);
        new Thread(task).start();
        System.out.println(task.get());
        System.out.println(3);
    }
}
