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
