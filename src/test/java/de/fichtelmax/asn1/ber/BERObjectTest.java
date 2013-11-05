package de.fichtelmax.asn1.ber;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.util.Arrays;
import org.junit.Test;

public class BERObjectTest
{
    BERObject cut;
    
    @Test
    public void toStringShouldUseCompleteASN1()
    {
        // not really ASN1/TLV - should not be neccessary for data object
        byte[] type = new byte[] { 0x01 };
        byte[] length = new byte[] { 0x02 };
        byte[] value = new byte[] { 0x03 };
        
        cut = new BERObject( type, length, value );
        
        assertThat( cut.toString(), containsString( DatatypeConverter.printBase64Binary( Arrays.concatenate( type, length, value ) ) ) );
    }
}
