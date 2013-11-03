package de.fichtelmax.asn1.ber.types;

import de.fichtelmax.asn1.ber.BERObject;

public class BitString extends BERObject
{
    
    public BitString( byte[] length, byte[] value )
    {
        super( 0x03, length, value );
    }
    
    @Override
    public String getName()
    {
        return "BIT STRING";
    }
}
