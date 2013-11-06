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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.fichtelmax.asn1.ber.BERParser;

public class Main
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
        StringBuilder builder = new StringBuilder();
        
        String line = null;
        while ( (line = input.readLine()) != null )
        {
            builder.append( line );
        }
        
        System.out.println( "Input was:" );
        System.out.println( builder.toString() );
        
        System.out.println( "=============================" );
        System.out.println( "parsing..." );
        
        HexReader hexReader = new HexReader();
        byte[] data = hexReader.toBytes( builder.toString() );
        
        ASN1Printer printer = new ASN1Printer( System.out, BERParser.DEFAULT );
        
        printer.print( data );
    }
}
