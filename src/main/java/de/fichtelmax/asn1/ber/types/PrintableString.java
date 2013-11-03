package de.fichtelmax.asn1.ber.types;

import de.fichtelmax.asn1.ber.BERObject;

public class PrintableString extends BERObject
{
    private String string;
    
    public PrintableString( byte[] length, byte[] value )
    {
        super( new byte[] { 0x13 }, length, value );
        this.string = new String( value );
    }
    
    public String getString()
    {
        return string;
    }
    
    @Override
    public String getName()
    {
        return "PrintableString";
    }
    
    @Override
    public String getValueString()
    {
        return string;
    }
}
