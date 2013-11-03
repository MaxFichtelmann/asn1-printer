package de.fichtelmax.asn1.ber;

import de.fichtelmax.asn1.ber.types.ASN1Native;

public class BERObjectFactory
{
    public BERObject create( byte[] type, byte[] length, byte[] value )
    {
        ASN1Native asn1Native = ASN1Native.create( type );
        
        if ( asn1Native != null )
            return asn1Native.build( length, value );
        else
            return new BERObject( type, length, value );
    }
}
