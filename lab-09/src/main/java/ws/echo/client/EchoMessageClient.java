package ws.echo.client;

import org.xml.sax.InputSource;
import ws.echo.EchoImplService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.ByteArrayInputStream;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 09.01.14
 */
public class EchoMessageClient {
    public static void main(String[] args) throws Exception {
        String requestPayLoadText = "<ns2:echo xmlns:ns2 = 'http://echo.ws/'>" +
                "<arg0> John </arg0>"+
                "<arg1>25</arg1>" +
                "</ns2:echo>";

        Source requestPayload = new SAXSource(
                new InputSource(
                        new ByteArrayInputStream(requestPayLoadText.getBytes())));
        EchoImplService echoImplService = new EchoImplService();
        Dispatch<Source> dispatch = echoImplService.createDispatch(
                new QName("http://echo.ws/" , "EchoImplPort"),
                Source.class,
                Service.Mode.PAYLOAD);
        Source responsePayload = dispatch.invoke(requestPayload);
       //достать и вывести сообщение
    }

    /**
     * Request class
     */
    @XmlRootElement(name = "echo", namespace = "http://echo.ws/")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class EchoRequest {
        @XmlElement(name = "arg0")
        protected String message;
        @XmlElement(name = "arg1")
        protected int count;
    }

    /**
     * Response class
     */
    @XmlRootElement(name = "echoResponse", namespace = "http://echo.ws/")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class EchoResponse {
        @XmlElement(name = "arg0")
        protected String message;
    }
}
