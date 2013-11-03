package de.fichtelmax.asn1.ber;

import java.util.Arrays;

public class BERLengthParser
{
    public BERLength parseLength( byte[] encoded, int index )
    {
        byte primary = encoded[index];
        
        boolean indefinite = primary == 0x80;
        boolean multiByte = !indefinite && (primary & 0x80) == 0x80;
        
        if ( indefinite )
        {
            // NYI
            throw new UnsupportedOperationException();
        }
        else if ( multiByte )
        {
            int numBytes = primary & 0x7f;
            int length = 0;
            for ( int i = 0; i < numBytes; i++ )
            {
                byte next = encoded[index + 1 + i];
                length <<= 8;
                length += next & 0xff;
            }
            return new BERLength( length, Arrays.copyOfRange( encoded, index, index + 1 + numBytes ) );
        }
        else
        {
            return new BERLength( primary, primary );
        }
    }
}
