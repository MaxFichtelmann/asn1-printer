package de.fichtelmax.asn1;

/*
 * #%L
 * asn1-printer
 * %%
 * Copyright (C) 2013 Max Fichtelmann
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


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
