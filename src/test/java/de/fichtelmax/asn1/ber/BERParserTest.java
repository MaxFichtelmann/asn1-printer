package de.fichtelmax.asn1.ber;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DERUTF8String;
import org.junit.Before;
import org.junit.Test;

public class BERParserTest
{
    BERParser cut;
    
    @Before
    public void setup()
    {
        // assume friendly subparsers
        cut = new BERParser( new BERTypeParser(), new BERLengthParser(), new BERObjectFactory() );
    }
    
    @Test
    public void utf8String() throws IOException
    {
        String text = "something";
        DERUTF8String utf8String = new DERUTF8String( text );
        
        BERObject berObject = cut.parse( utf8String.getEncoded() );
        
        assertThat( berObject.getValue(), is( equalTo( text.getBytes( "UTF-8" ) ) ) );
    }
    
    @Test
    public void objectIdentifier() throws IOException
    {
        String oidString = "1.2.3.15.300";
        
        byte[] bcEncoded = new DERObjectIdentifier( oidString ).getEncoded();
        
        BERObject berObject = cut.parse( bcEncoded );
        
        byte[] bcValue = Arrays.copyOfRange( bcEncoded, 2, bcEncoded.length );
        assertThat( berObject.getValue(), is( equalTo( bcValue ) ) );
    }
}
