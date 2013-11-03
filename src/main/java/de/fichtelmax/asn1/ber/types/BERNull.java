package de.fichtelmax.asn1.ber.types;

import de.fichtelmax.asn1.ber.BERObject;

public class BERNull extends BERObject
{
    
    public BERNull( byte[] length, byte[] value )
    {
        super( new byte[] { 0x05 }, length, value );
    }
    
    @Override
    public String getName()
    {
        return "NULL";
    }
}
