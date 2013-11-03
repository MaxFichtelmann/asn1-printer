package de.fichtelmax.asn1.ber.types;

import de.fichtelmax.asn1.ber.BERObject;

public class EndOfContent extends BERObject
{
    
    public EndOfContent( byte[] length, byte[] value )
    {
        super( 0x00, length, value );
    }
    
    @Override
    public String toString()
    {
        return "EOC";
    }
}
