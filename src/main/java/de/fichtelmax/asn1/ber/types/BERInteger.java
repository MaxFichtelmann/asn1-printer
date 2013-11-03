package de.fichtelmax.asn1.ber.types;

import de.fichtelmax.asn1.ASN1Primitive;
import de.fichtelmax.asn1.ber.BERObject;

public class BERInteger extends BERObject implements ASN1Primitive
{
    private long intValue;
    
    public BERInteger( byte[] length, byte[] value )
    {
        super( new byte[] { 0x02 }, length, value );
        this.intValue = parse( value );
    }
    
    public long getIntValue()
    {
        return intValue;
    }
    
    @Override
    public String getName()
    {
        return "INTEGER";
    }
    
    @Override
    public String getValueString()
    {
        return Long.toString( intValue );
    }
    
    private long parse( byte[] value )
    {
        long result = 0;
        
        boolean first = true;
        for ( byte b : value )
        {
            if ( first )
            {
                first = false;
                result += b;
            }
            else
            {
                result <<= 8;
                result += (b & 0xFF);
            }
        }
        return result;
        
    }
}