package de.fichtelmax.asn1.ber.types;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.junit.Test;

import de.fichtelmax.asn1.ber.BERObject;

public class BERSequenceTest
{
    BERSequence cut;
    
    @Test
    public void create() throws IOException
    {
        DERUTF8String string1 = new DERUTF8String( "some string" );
        DERUTF8String string2 = new DERUTF8String( "some other string" );
        
        byte[] bcEncoded = new DERSequence( new ASN1Encodable[] { string1, string2 } ).getEncoded();
        
        cut = new BERSequence( new byte[] { bcEncoded[1] }, Arrays.copyOfRange( bcEncoded, 2, bcEncoded.length ) );
        
        assertThat( cut.getEncoded(), is( equalTo( bcEncoded ) ) );
        
        Iterator<BERObject> iterator = cut.iterator();
        
        assertThat( iterator.next().getEncoded(), is( equalTo( string1.getEncoded() ) ) );
        assertThat( iterator.next().getEncoded(), is( equalTo( string2.getEncoded() ) ) );
        assertThat( iterator.hasNext(), is( false ) );
    }
}
