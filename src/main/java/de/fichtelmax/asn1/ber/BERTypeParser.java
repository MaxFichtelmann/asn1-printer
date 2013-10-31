package de.fichtelmax.asn1.ber;

public class BERTypeParser
{
    public BERType parseType( byte[] encoded, int index )
    {
        byte primary = encoded[index];
        
        boolean constructed = matchesMask( primary, 0x20 );
        boolean multiByte = matchesMask( primary, 0x1F );
        
        if ( multiByte )
        {
            // TODO
            // NYI
            throw new UnsupportedOperationException();
        }
        else
        {
            return new BERType( constructed, primary );
        }
        
    }
    
    private static boolean matchesMask( byte b, int mask )
    {
        return (b & mask) == mask;
    }
}
