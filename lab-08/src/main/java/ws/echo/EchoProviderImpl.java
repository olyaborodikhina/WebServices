package ws.echo;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.SOAPPart1_1Impl;
import com.sun.xml.internal.messaging.saaj.soap.ver1_2.SOAPFactory1_2Impl;
import com.sun.xml.internal.messaging.saaj.soap.ver1_2.SOAPMessageFactory1_2Impl;
import com.sun.xml.internal.ws.message.jaxb.JAXBMessage;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stax.StAXSource;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
@WebServiceProvider(serviceName = "EchoService", targetNamespace = "http://echo.ws/")
@ServiceMode(Service.Mode.MESSAGE)
public class EchoProviderImpl implements Provider<SOAPMessage> {

    /**
     * Request class
     */
    @XmlRootElement(name = "echo", namespace = "http://echo.ws/")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class EchoRequest {
        @XmlElement(name = "arg0")
        public String message;
        @XmlElement(name = "arg1")
        public int count;
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

    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        try {
            JAXBContext context = JAXBContext.newInstance(EchoRequest.class, EchoResponse.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            NodeList params = request.getSOAPBody().getChildNodes().item(0).getChildNodes();
            String message = params.item(0).getNodeValue();
            int count = Integer.parseInt(params.item(1).getNodeValue());


//            final StringBuilder resultBuilder = new StringBuilder();
//            for(int i = 0; i < echoRequest.count; i++){
//                resultBuilder.append(echoRequest.message);
//            }
//            EchoResponse response = new EchoResponse();
//            response.message = resultBuilder.toString();
            return SOAPMessageFactory1_2Impl.newInstance().createMessage();
        } catch (Exception e) {
            return null;
        }
    }
}
