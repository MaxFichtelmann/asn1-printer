package de.fichtelmax.asn1.ber;

/*
 * #%L
 * asn1-printer
 * %%
 * Copyright (C) 2013 Max Fichtelmann
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


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
        return DatatypeConverter.printBase64Binary( getEncoded() );
    }
    
    @Override
    public String toString()
    {
        return String.format( "%s [%s]", getName(), getValueString() );
    }
}
