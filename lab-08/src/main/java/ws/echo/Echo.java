package ws.echo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Holder;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */

@WebService(name = "Echo",
        targetNamespace = "http://echo.ws/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Echo {
    @WebMethod(
            action = "echo"
    )
    void echo(@WebParam(name = "message",mode = WebParam.Mode.INOUT,partName = "message") Holder<String> var1,
              @WebParam(name = "count",partName = "count") int var2);

}
