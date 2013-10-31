package de.fichtelmax.asn1.ber;

public class BERLengthParser
{
    public BERLength parseLength( byte[] encoded, int index )
    {
        byte primary = encoded[index];
        
        boolean indefinite = primary == 0x80;
        boolean multiByte = !indefinite && (primary & 0x80) == 0x80;
        
        if ( indefinite || multiByte )
        {
            // NYI
            throw new UnsupportedOperationException();
        }
        else
        {
            return new BERLength( (int) primary, primary );
        }
    }
}
