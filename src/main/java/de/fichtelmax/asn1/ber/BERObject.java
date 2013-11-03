package de.fichtelmax.asn1.ber;

import javax.xml.bind.DatatypeConverter;

public class BERObject
{
    private byte[] type;
    private byte[] length;
    private byte[] value;
    
    public BERObject( int type, byte[] length, byte[] value )
    {
        this( new byte[] { (byte) type }, length, value );
    }
    
    public BERObject( byte[] type, byte[] length, byte[] value )
    {
        this.type = type;
        this.length = length;
        this.value = value;
    }
    
    public byte[] getType()
    {
        return type;
    }
    
    public byte[] getValue()
    {
        return value;
    }
    
    public byte[] getLength()
    {
        return length;
    }
    
    public byte[] getEncoded()
    {
        byte[] data = new byte[type.length + length.length + value.length];
        System.arraycopy( type, 0, data, 0, type.length );
        System.arraycopy( length, 0, data, type.length, length.length );
        System.arraycopy( value, 0, data, type.length + length.length, value.length );
        
        return data;
    }
    
    public String getName()
    {
        return "UNSPECIFIED";
    }
    
    public String getValueString()
    {
        return DatatypeConverter.printBase64Binary( value );
    }
    
    @Override
    public String toString()
    {
        return String.format( "%s [%s]", getName(), getValueString() );
    }
}
