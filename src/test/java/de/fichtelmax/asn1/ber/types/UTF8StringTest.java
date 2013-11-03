package de.fichtelmax.asn1.ber.types;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.bouncycastle.asn1.DERUTF8String;
import org.junit.Test;

public class UTF8StringTest
{
    UTF8String cut;
    
    @Test
    public void create() throws IOException
    {
        String string = "Test string including strange chars ä ® €";
        DERUTF8String bcString = new DERUTF8String( string );
        
        byte[] bcEncoded = bcString.getEncoded();
        byte[] bcValue = Arrays.copyOfRange( bcEncoded, 2, bcEncoded.length );
        
        cut = new UTF8String( new byte[] { bcEncoded[1] }, bcValue );
        
        assertThat( cut.getString(), is( equalTo( string ) ) );
    }
}
