package de.fichtelmax.asn1.ber.types;

import de.fichtelmax.asn1.ber.BERObject;

public class ObjectIdentifier extends BERObject
{
    private String oid;
    
    public ObjectIdentifier( byte[] length, byte[] value )
    {
        super( new byte[] { 0x06 }, length, value );
        this.oid = parseOidString();
    }
    
    @Override
    public String getName()
    {
        return "OBJECT IDENTIFIER";
    }
    
    @Override
    public String getValueString()
    {
        return oid;
    }
    
    public String getOid()
    {
        return oid;
    }
    
    // NYI: support for really large oid branches (> 72057594037927808)
    private String parseOidString()
    {
        StringBuilder builder = new StringBuilder();
        
        long value = 0;
        boolean first = true;
        for ( byte b : this.getValue() )
        {
            // due to no unsigned byte type
            int uByte = b & 0xff;
            
            value += uByte & 0x7f;
            // the MSB marks the end of value
            if ( (uByte & 0x80) == 0 )
            {
                if ( first )
                {
                    first = false;
                    int i = uByte / 40;
                    i = i <= 2 ? i : 2;
                    
                    builder.append( i );
                    value -= 40 * i;
                }
                builder.append( '.' );
                builder.append( value );
                value = 0;
            }
            else
            {
                // move the previous obtained value onward
                value <<= 7;
            }
        }
        
        return builder.toString();
    }
}
