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

public class EndOfContent extends BERObject
{
    
    public EndOfContent( byte[] length, byte[] value )
    {
        super( 0x00, length, value );
    }
    
    @Override
    public String toString()
    {
        return "EOC";
    }
}
