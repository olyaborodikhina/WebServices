package ws.hello;

/**
 * Created by omsk17 on 12/19/2016.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import javax.xml.bind.annotation.XmlElement;

        import javax.xml.bind.annotation.XmlAccessType;
        import javax.xml.bind.annotation.XmlAccessorType;
        import javax.xml.bind.annotation.XmlElement;
        import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "sayHelloResponse",
        propOrder = {"_return"}
)
public class SayHelloResponse {
    @XmlElement(
            name = "return"
    )
    protected String _return;

    public SayHelloResponse() {
    }

    public String getReturn() {
        return this._return;
    }

    public void setReturn(String var1) {
        this._return = var1;
    }
}
