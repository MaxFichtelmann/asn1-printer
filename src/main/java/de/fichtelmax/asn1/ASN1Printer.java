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


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import de.fichtelmax.asn1.ber.BERObject;
import de.fichtelmax.asn1.ber.BERParser;
import de.fichtelmax.asn1.ber.IncompleteException;
import de.fichtelmax.asn1.ber.types.EndOfContent;

public class ASN1Printer
{
    public static final String  INCOMPLETE_MESSAGE = "incomplete input - cancelled";
    private static final String _4_SPACES          = "    ";
    private PrintStream         out;
    private BERParser           parser;
    
    public ASN1Printer( PrintStream out, BERParser parser )
    {
        this.out = out;
        this.parser = parser;
    }
    
    public void print( byte[] data )
    {
        // boolean incomplete;
        
        BERObject root = parser.parse( data );
        
        Deque<BERPrintable> stack = new LinkedList<BERPrintable>();
        stack.push( new BERPrintable( 0, root ) );
        
        while ( !stack.isEmpty() )
        {
            BERPrintable printable = stack.pop();
            if ( printable.berObject instanceof EndOfContent && printable.berObject.getValue().length == 0 )
            {
                out.println( INCOMPLETE_MESSAGE );
                break;
            }
            else
            {
                out.println( spaces( printable.indent ) + printable.berObject );
            }
            
            if ( printable.berObject instanceof ASN1Constructed )
            {
                ASN1Constructed collection = (ASN1Constructed) printable.berObject;
                
                try
                {
                    List<BERObject> inverse = new ArrayList<BERObject>();
                    for ( BERObject o : collection )
                    {
                        inverse.add( o );
                    }
                    Collections.reverse( inverse );
                    for ( BERObject o : inverse )
                    {
                        stack.push( new BERPrintable( printable.indent + 1, o ) );
                    }
                }
                catch ( IncompleteException e )
                {
                    out.println( "INCOMPLETE" );
                }
            }
        }
    }
    
    private static String spaces( int num )
    {
        StringBuilder builder = new StringBuilder( num );
        for ( int i = 0; i < num; i++ )
        {
            builder.append( _4_SPACES );
        }
        
        return builder.toString();
    }
    
    private static class BERPrintable
    {
        int       indent;
        BERObject berObject;
        
        public BERPrintable( int indent, BERObject berObject )
        {
            this.indent = indent;
            this.berObject = berObject;
        }
    }
}
