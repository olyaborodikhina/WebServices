package ws.handlers;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.01.14
 */
public class IpAddressInjector implements SOAPHandler<SOAPMessageContext> {
    private final QName addressHeaderName = new QName("http://hello.ws/", "ip");

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(isRequest){
            try {
                SOAPMessage message = context.getMessage();
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                if(header == null){
                    header = envelope.addHeader();
                }
                SOAPHeaderElement headerElement = header.addHeaderElement(new QName("http://hello.ws/", "ip"));
                headerElement .addTextNode(InetAddress.getLocalHost().getHostAddress());
                headerElement.addTextNode("hghghghhghg");
                headerElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                message.saveChanges();
                message.writeTo(System.out);
                System.out.println();


            } catch (SOAPException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }
}
