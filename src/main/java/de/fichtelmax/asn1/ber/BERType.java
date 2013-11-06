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

public class BERType
{
    private byte[]  type;
    private boolean constructed;
    
    public BERType( boolean constructed, byte... type )
    {
        this.constructed = constructed;
        this.type = type;
    }
    
    public byte[] getType()
    {
        return type;
    }
    
    public boolean isConstructed()
    {
        return constructed;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode( type );
        return result;
    }
    
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        BERType other = (BERType) obj;
        if ( !Arrays.equals( type, other.type ) )
            return false;
        return true;
    }
}
