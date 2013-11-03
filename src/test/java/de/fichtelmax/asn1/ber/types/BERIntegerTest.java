package de.fichtelmax.asn1.ber.types;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.bouncycastle.asn1.DERInteger;
import org.junit.Test;

public class BERIntegerTest
{
    BERInteger cut;
    
    @Test
    public void create() throws IOException
    {
        // 3-byte number
        long i = 1234567;
        byte[] bcEncoded = new DERInteger( i ).getEncoded();
        
        cut = new BERInteger( new byte[] { bcEncoded[1] }, Arrays.copyOfRange( bcEncoded, 2, bcEncoded.length ) );
        
        assertThat( cut.getEncoded(), is( equalTo( bcEncoded ) ) );
    }
}
