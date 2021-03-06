
package ws.hello;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "HelloException", targetNamespace = "http://hello.ws/")
public class HelloException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private HelloException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public HelloException_Exception(String message, HelloException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public HelloException_Exception(String message, HelloException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ws.hello.HelloException
     */
    public HelloException getFaultInfo() {
        return faultInfo;
    }

}
