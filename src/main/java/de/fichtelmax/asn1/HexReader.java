package de.fichtelmax.asn1;

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


public class HexReader
{
    public byte[] toBytes( String hexString )
    {
        if ( !verify( hexString ) )
        {
            throw new IllegalArgumentException( "only hex characters and whitespaces are allowed!" );
        }
        
        String cleanedUp = removeWhitespaces( hexString );
        
        if ( cleanedUp.length() % 2 != 0 )
        {
            throw new IllegalArgumentException( "An even number of hex chars is expected!" );
        }
        
        String separated = separate( cleanedUp );
        
        String[] hexPairs = separated.split( " " );
        byte[] bytes = new byte[hexPairs.length];
        
        for ( int i = 0; i < hexPairs.length; i++ )
        {
            // Byte.parseByte does not work here (i.e. 0xFF is too large for signed byte)
            bytes[i] = (byte) Integer.parseInt( hexPairs[i], 16 );
        }
        return bytes;
    }
    
    private String separate( String cleanedUp )
    {
        return cleanedUp.replaceAll( "(..)", "$1 " );
    }
    
    private String removeWhitespaces( String hexString )
    {
        return hexString.replaceAll( "\\s", "" );
    }
    
    private boolean verify( String s )
    {
        return s.matches( "^[0-9a-fA-F\\s]*$" );
    }
}
