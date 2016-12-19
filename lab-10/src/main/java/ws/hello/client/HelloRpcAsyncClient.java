package ws.hello.client;


import ws.hello.HelloException;
import ws.hello.HelloImplService;
import ws.hello.HelloService;
import ws.hello.SayHelloResponse;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.util.concurrent.*;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 09.01.14
 */
public class HelloRpcAsyncClient {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int initialPoolSize = 1;
        int maxPoolSize = 5;

        final Executor executor = new ThreadPoolExecutor(initialPoolSize, maxPoolSize, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(maxPoolSize));

        HelloImplService service = new HelloImplService();
        service.setExecutor(executor);

        HelloService hello = service.getHelloImplPort();

        Response<String> response = hello.sayHelloAsync("John");
        while (!response.isDone()) {
            Thread.sleep(500L);
        }
        System.out.println(response.get());

    }
}
