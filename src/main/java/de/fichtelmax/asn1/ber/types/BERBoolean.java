package de.fichtelmax.asn1.ber.types;

import de.fichtelmax.asn1.ber.BERObject;

public class BERBoolean extends BERObject
{
    private boolean berTrue;
    
    public BERBoolean( byte[] length, byte[] value )
    {
        super( new byte[] { 0x01 }, length, value );
        this.berTrue = value[0] == 0xFF;
    }
    
    public boolean isBerTrue()
    {
        return berTrue;
    }
    
    @Override
    public String getName()
    {
        return "BOOLEAN";
    }
    
    @Override
    public String getValueString()
    {
        return Boolean.toString( berTrue );
    }
}
