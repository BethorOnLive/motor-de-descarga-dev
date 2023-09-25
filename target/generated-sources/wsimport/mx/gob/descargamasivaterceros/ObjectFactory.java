
package mx.gob.descargamasivaterceros;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.descargamasivaterceros package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AutenticaResponseAutenticaResult_QNAME = new QName("http://DescargaMasivaTerceros.gob.mx", "AutenticaResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.descargamasivaterceros
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Autentica }
     * 
     */
    public Autentica createAutentica() {
        return new Autentica();
    }

    /**
     * Create an instance of {@link AutenticaResponse }
     * 
     */
    public AutenticaResponse createAutenticaResponse() {
        return new AutenticaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://DescargaMasivaTerceros.gob.mx", name = "AutenticaResult", scope = AutenticaResponse.class)
    public JAXBElement<String> createAutenticaResponseAutenticaResult(String value) {
        return new JAXBElement<String>(_AutenticaResponseAutenticaResult_QNAME, String.class, AutenticaResponse.class, value);
    }

}
