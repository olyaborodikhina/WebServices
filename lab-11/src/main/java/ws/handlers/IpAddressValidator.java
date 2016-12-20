package ws.handlers;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.sound.midi.SysexMessage;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.01.14
 */
public class IpAddressValidator implements SOAPHandler<SOAPMessageContext> {
    private final QName addressHeaderName = new QName("http://hello.ws/", "ip");


    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isRequest){
            try {
                SOAPMessage message = context.getMessage();
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                if(header == null){
                    header = envelope.getHeader();
                }

              // SOAPHeaderElement headerElement = header.addHeaderElement(new QName("http://hello.ws/", "ip"));

                java.util.Iterator iterator = header.extractAllHeaderElements();
                while (iterator.hasNext()){
                  SOAPHeaderElement headerElement = (SOAPHeaderElement) iterator.next();
                    System.out.println(headerElement.getTextContent());
                }

            }
            catch (SOAPException e) {
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
