package de.fichtelmax.asn1.ber;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

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
        cut = new BERParser( new BERTypeParser(), new BERLengthParser() );
    }
    
    @Test
    public void utf8String() throws IOException
    {
        String text = "something";
        DERUTF8String utf8String = new DERUTF8String( text );
        
        BERObject berObject = cut.parse( utf8String.getEncoded() );
        
        assertThat( berObject.getValue(), is( equalTo( text.getBytes( "UTF-8" ) ) ) );
    }
}
