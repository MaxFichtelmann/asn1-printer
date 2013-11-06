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
