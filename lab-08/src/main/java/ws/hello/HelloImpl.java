package ws.hello;

import javax.jws.WebService;


/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
@WebService(endpointInterface = "ws.hello.Hello")
public class HelloImpl implements Hello {
    @Override
    public String sayHello(String  name) throws HelloException{
        return name+= ", Hello!";
    }

}
