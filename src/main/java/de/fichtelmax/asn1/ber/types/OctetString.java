package de.fichtelmax.asn1.ber.types;

import de.fichtelmax.asn1.ber.BERObject;

public class OctetString extends BERObject
{
    public OctetString( byte[] length, byte[] value )
    {
        super( 0x04, length, value );
    }
    
    @Override
    public String getName()
    {
        return "OCTET STRING";
    }
}
