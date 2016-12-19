package ws.echo.client;


import ws.echo.Echo;
import ws.echo.EchoImplService;

import javax.xml.ws.Holder;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 09.01.14
 */
public class EchoRpcClient {
    public static void main(String[] args) throws Exception {
        Holder<String> holder = new Holder<String>();
        holder.value = "ay";
        EchoImplService echoImplService = new EchoImplService();
        Echo echo = echoImplService.getEchoImplPort();
        echo.echo(holder,2);
        System.out.println(holder.value);
    }
}
