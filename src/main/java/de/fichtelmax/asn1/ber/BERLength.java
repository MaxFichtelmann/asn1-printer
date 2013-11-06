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


public class BERLength
{
    byte[]  lengthBytes;
    boolean indefinite;
    int     length;
    
    public BERLength( int length, byte... lengthBytes )
    {
        this.lengthBytes = lengthBytes;
        this.length = length;
    }
    
    public BERLength( boolean indefinite )
    {
        this.indefinite = indefinite;
    }
    
    public byte[] getLengthBytes()
    {
        return lengthBytes;
    }
    
    public boolean isIndefinite()
    {
        return indefinite;
    }
    
    public int getLength()
    {
        return length;
    }
}
