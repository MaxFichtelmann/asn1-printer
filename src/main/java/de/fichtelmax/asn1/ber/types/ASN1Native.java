package de.fichtelmax.asn1.ber.types;

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


import java.lang.reflect.Constructor;
import java.util.Arrays;

import de.fichtelmax.asn1.ber.BERObject;

public enum ASN1Native
{
    EOC( EndOfContent.class, 0x00 ),
    
    BOOLEAN( BERBoolean.class, 0x01 ),
    
    INTEGER( BERInteger.class, 0x02 ),
    
    BIT_STRING( BitString.class, 0x03 ),
    
    OCTET_STRING( OctetString.class, 0x04 ),
    
    NULL( BERNull.class, 0x05 ),
    
    OID( ObjectIdentifier.class, 0x06 ),
    
    PRINTABLE_STRING( PrintableString.class, 0x13 ),
    
    UTF8STRING( UTF8String.class, 0x0C ),
    
    SEQUENCE( BERSequence.class, 0x30 ),
    
    SET( BERSet.class, 0x31 ),
    
    ;
    
    private byte[]                     asn1Type;
    private Class<? extends BERObject> javaType;
    
    private ASN1Native( Class<? extends BERObject> javaType, int asn1Type )
    {
        this.javaType = javaType;
        this.asn1Type = new byte[] { (byte) asn1Type };
    }
    
    private ASN1Native( Class<? extends BERObject> javaType, byte... asn1Type )
    {
        this.javaType = javaType;
        this.asn1Type = asn1Type;
    }
    
    public BERObject build( byte[] lengthBytes, byte[] value )
    {
        try
        {
            Constructor<? extends BERObject> constructor = javaType.getConstructor( byte[].class, byte[].class );
            return constructor.newInstance( lengthBytes, value );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( e );
        }
    }
    
    public static ASN1Native create( byte[] type )
    {
        for ( ASN1Native t : ASN1Native.values() )
        {
            if ( Arrays.equals( type, t.asn1Type ) )
            {
                return t;
            }
        }
        return null;
    }
}