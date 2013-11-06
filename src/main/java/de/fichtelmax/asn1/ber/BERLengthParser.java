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


import java.util.Arrays;

public class BERLengthParser
{
    public BERLength parseLength( byte[] encoded, int index )
    {
        byte primary = encoded[index];
        
        boolean indefinite = primary == 0x80;
        boolean multiByte = !indefinite && (primary & 0x80) == 0x80;
        
        if ( indefinite )
        {
            // NYI
            throw new UnsupportedOperationException();
        }
        else if ( multiByte )
        {
            int numBytes = primary & 0x7f;
            int length = 0;
            for ( int i = 0; i < numBytes; i++ )
            {
                byte next = encoded[index + 1 + i];
                length <<= 8;
                length += next & 0xff;
            }
            return new BERLength( length, Arrays.copyOfRange( encoded, index, index + 1 + numBytes ) );
        }
        else
        {
            return new BERLength( primary, primary );
        }
    }
}
