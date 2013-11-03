package de.fichtelmax.asn1;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintStream;
import java.security.cert.CertificateException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERUTF8String;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.fichtelmax.asn1.ber.BERParser;

public class ASN1PrinterTest
{
    ASN1Printer cut;
    
    @Mock
    PrintStream out;
    
    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks( this );
        
        cut = new ASN1Printer( out, BERParser.DEFAULT );
    }
    
    @Test
    public void printSequence() throws IOException
    {
        String text = "Hello World!";
        String oid = "1.2.3.45.10982345";
        long number = 12345678901l;
        
        DERUTF8String string = new DERUTF8String( text );
        DERObjectIdentifier objectIdentifier = new DERObjectIdentifier( oid );
        DERInteger integer = new DERInteger( number );
        
        DERSet set = new DERSet( new ASN1Encodable[] { objectIdentifier, integer } );
        DERSequence sequence = new DERSequence( new ASN1Encodable[] { string, set } );
        
        cut.print( sequence.getEncoded() );
        
        verify( out ).println( contains( text ) );
        verify( out ).println( contains( oid ) );
        verify( out ).println( contains( Long.toString( number ) ) );
    }
    
    @Test
    public void printX509() throws IOException, CertificateException
    {
        byte[] x509Bytes = IOUtils.toByteArray( ASN1Printer.class.getResourceAsStream( "/certificate.crt" ) );
        
        cut.print( x509Bytes );
        
        verify( out, never() ).println( ASN1Printer.INCOMPLETE_MESSAGE );
    }
    
    @Test
    public void printHalfX509() throws IOException, CertificateException
    {
        byte[] x509Bytes = IOUtils.toByteArray( ASN1Printer.class.getResourceAsStream( "/certificate.crt" ) );
        x509Bytes = Arrays.copyOfRange( x509Bytes, 0, x509Bytes.length / 2 );
        
        cut.print( x509Bytes );
        
        verify( out ).println( ASN1Printer.INCOMPLETE_MESSAGE );
    }
    
}
