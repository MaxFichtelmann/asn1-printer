package de.fichtelmax.asn1.ber.types;

import java.nio.charset.Charset;

import de.fichtelmax.asn1.ber.BERObject;

public class UTF8String extends BERObject
{
    private String string;
    
    public UTF8String( byte[] length, byte[] value )
    {
        super( new byte[] { 0x0C }, length, value );
        this.string = new String( value, Charset.forName( "UTF-8" ) );
    }
    
    public String getString()
    {
        return string;
    }
    
    @Override
    public String getName()
    {
        return "UTF8String";
    }
    
    @Override
    public String getValueString()
    {
        return string;
    }
}
