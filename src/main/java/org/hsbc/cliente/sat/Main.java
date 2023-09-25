package org.hsbc.cliente.sat;
import mx.gob.descargamasivaterceros.Autenticacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Main {

    final static String urlAutentica = "https://cfdidescargamasivasolicitud.clouda.sat.gob.mx/Autenticacion/Autenticacion.svc";
    final static String urlAutenticaAction = "http://DescargaMasivaTerceros.gob.mx/IAutenticacion/Autentica";

    // PFX's password
    final static char[] pwdPFX = "contraseña".toCharArray();
    static X509Certificate certificate = null;
    static PrivateKey privateKey = null;
    static String token = null;
    public static void main(String[] args) throws Exception{
        char espacio = '\u0020';
        String filePath = "assets/certificado.pfx";
        File filePFX = new File(filePath);

        // Get certificate and private key from PFX file

        certificate = getCertificate(filePFX);
        privateKey = getPrivateKey(filePFX);
        String token = "WRAP access_token=\"" + decodeValue(getToken()) + "\"";

        System.out.println("Instancias de filePFX " + filePFX);
        System.out.println("Hello world!" + espacio + pwdPFX);
        System.out.println("Obtener certificado: " + certificate);
        System.out.println("Obtener llave privada: " + privateKey);
        System.out.println("Obtener token: " + token);
    }
    /**
     * Get a certificate through a pfx file
     *
     * @param file
     * @return
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     */
    public static X509Certificate getCertificate(File file) throws
            KeyStoreException,
            IOException,
            CertificateException,
            NoSuchAlgorithmException
    {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(file), pwdPFX);
        String alias = ks.aliases().nextElement();

        return (X509Certificate) ks.getCertificate(alias);
    }

    /**
     * Get a private key through a pfx file
     *
     * @param file
     *
     * @return
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws UnrecoverableKeyException
     */
    public static PrivateKey getPrivateKey(File file) throws
            KeyStoreException,
            IOException,
            CertificateException,
            NoSuchAlgorithmException,
            UnrecoverableKeyException
    {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(file), pwdPFX);
        String alias = ks.aliases().nextElement();

        return (PrivateKey) ks.getKey(alias, pwdPFX);
    }

    /**
     * Get XML response through SAT's web service and extract token from it
     *
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws InvalidKeyException
     * @throws CertificateEncodingException
     */
    public static String getToken()
            throws IOException,
            NoSuchAlgorithmException,
            SignatureException,
            InvalidKeyException,
            CertificateEncodingException {
        Authentication authentication = new Authentication(urlAutentica, urlAutenticaAction);
        authentication.generate(certificate, privateKey);

        return authentication.send(null);
    }

    public static String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}