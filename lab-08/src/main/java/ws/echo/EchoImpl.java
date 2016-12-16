package ws.echo;

import javax.jws.WebService;
import javax.xml.ws.Holder;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
@WebService(endpointInterface = "ws.echo.Echo")
public class EchoImpl implements Echo {
    @Override
    public void echo(Holder<String> message, int count) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++){
            sb.append(message.value);
        }
        message.value = sb.toString();
    }
}
