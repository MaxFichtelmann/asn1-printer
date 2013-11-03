package de.fichtelmax.asn1.ber.types;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.bouncycastle.asn1.DERObjectIdentifier;
import org.junit.Test;

public class ObjectIdentifierTest
{
    ObjectIdentifier cut;
    
    @Test
    public void create() throws IOException
    {
        String oidString = "1.3.14.25.314001";
        DERObjectIdentifier bcOid = new DERObjectIdentifier( oidString );
        
        byte[] encoded = bcOid.getEncoded();
        byte[] bcOidValue = Arrays.copyOfRange( encoded, 2, encoded.length );
        
        cut = new ObjectIdentifier( new byte[] { encoded[1] }, bcOidValue );
        
        assertThat( cut.getOid(), is( equalTo( oidString ) ) );
    }
}
