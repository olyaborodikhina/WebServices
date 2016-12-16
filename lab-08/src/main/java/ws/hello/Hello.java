package ws.hello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */

@WebService(name = "HelloService",targetNamespace = "http://hello.ws/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Hello {
    @WebMethod(action = "sayHello")
    String sayHello(@WebParam(name = "name") String var1) throws HelloException;

}
