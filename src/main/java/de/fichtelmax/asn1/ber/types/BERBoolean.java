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

public class BERBoolean extends BERObject
{
    private boolean berTrue;
    
    public BERBoolean( byte[] length, byte[] value )
    {
        super( new byte[] { 0x01 }, length, value );
        this.berTrue = value[0] == 0xFF;
    }
    
    public boolean isBerTrue()
    {
        return berTrue;
    }
    
    @Override
    public String getName()
    {
        return "BOOLEAN";
    }
    
    @Override
    public String getValueString()
    {
        return Boolean.toString( berTrue );
    }
}
