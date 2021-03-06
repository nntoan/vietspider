package org.tanukisoftware.wrapper;

/*
 * Copyright (c) 1999, 2006 Tanuki Software Inc.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of the Java Service Wrapper and associated
 * documentation files (the "Software"), to deal in the Software
 * without  restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sub-license,
 * and/or sell copies of the Software, and to permit persons to
 * whom the Software is furnished to do so, subject to the
 * following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * 
 * Portions of the Software have been derived from source code
 * developed by Silver Egg Technology under the following license:
 * 
 * Copyright (c) 2001 Silver Egg Technology
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sub-license, and/or 
 * sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * WrapperInfo.java is build as part of the build process and should not be
 *  modified.  Any changes to this class should be made to WrapperInfo.java.in
 *
 * @author Leif Mortenson <leif@tanukisoftware.com>
 */
final class WrapperInfo
{
    /** Version of the Wrapper. */
    private static final String   m_version         = "3.2.3";

    /** Date that the Wrapper was built. */
    private static final Calendar m_build           = Calendar.getInstance();
    
    /** Static initializer to create the build calendar from info hardcoded
     *   during the build. */
    static
    {
        Calendar buildDate = Calendar.getInstance();
        Calendar buildTime = Calendar.getInstance();
        try
        {
            buildDate.setTime( new SimpleDateFormat( "yyyyMMdd" ).parse( "20061017" ) );
            buildTime.setTime( new SimpleDateFormat( "HHmm" ).parse( "2319" ) );
            
            m_build.set( buildDate.get( Calendar.YEAR ), 
                        buildDate.get( Calendar.MONTH ), 
                        buildDate.get( Calendar.DATE ),
                        buildTime.get( Calendar.HOUR_OF_DAY ),
                        buildTime.get( Calendar.MINUTE ) );

        }
        catch ( ParseException e )
        {
            System.out.println( "Can not parse build date: " + e.getMessage() );
        }
    }

    /**
     * Returns the version of the Wrapper.
     *
     * @return the version of the Wrapper.
     */
    static String getVersion()
    {
        return m_version;
    }
    
    /**
     * Returns the time that the Wrapper was built.
     *
     * @return The time that the Wrapper was built.
     */
    static String getBuildTime()
    {
        DateFormat df = new SimpleDateFormat( "HH:mm zz MMM d, yyyy" ); 
        return df.format( m_build.getTime() );
    }
    
    /*---------------------------------------------------------------
     * Constructors
     *-------------------------------------------------------------*/
    /**
     * Can not be instantiated.
     */
    private WrapperInfo()
    {
    }
}

