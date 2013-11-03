package de.fichtelmax.asn1;

public class HexReader
{
    public byte[] toBytes( String hexString )
    {
        if ( !verify( hexString ) )
        {
            throw new IllegalArgumentException( "only hex characters and whitespaces are allowed!" );
        }
        
        String cleanedUp = removeWhitespaces( hexString );
        
        if ( cleanedUp.length() % 2 != 0 )
        {
            throw new IllegalArgumentException( "An even number of hex chars is expected!" );
        }
        
        String separated = separate( cleanedUp );
        
        String[] hexPairs = separated.split( " " );
        byte[] bytes = new byte[hexPairs.length];
        
        for ( int i = 0; i < hexPairs.length; i++ )
        {
            // Byte.parseByte does not work here (i.e. 0xFF is too large for signed byte)
            bytes[i] = (byte) Integer.parseInt( hexPairs[i], 16 );
        }
        return bytes;
    }
    
    private String separate( String cleanedUp )
    {
        return cleanedUp.replaceAll( "(..)", "$1 " );
    }
    
    private String removeWhitespaces( String hexString )
    {
        return hexString.replaceAll( "\\s", "" );
    }
    
    private boolean verify( String s )
    {
        return s.matches( "^[0-9a-fA-F\\s]*$" );
    }
}
