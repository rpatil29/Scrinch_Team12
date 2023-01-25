/*
 Scrinch is a stand-alone Swing application that helps managing your
 projects based on Agile principles.
 Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluekiger,
 Christian Lebaudy, Jean-Marc Borer

 Scrinch is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Scrinch is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.scrinch.ostools;

import java.io.IOException;
import java.util.logging.Level;
import org.scrinch.model.ScrinchEnvToolkit;


public class Runtime {
    
    public static void pdfOpen(String path) throws IOException{
       String os = System.getProperty("os.name").toLowerCase();
       if(os.indexOf("win")>=0){
           java.lang.Runtime.getRuntime().exec("rundll32 SHELL32.DLL, ShellExec_RunDLL " + path);
       }else if(os.indexOf("mac")>=0){
           java.lang.Runtime.getRuntime().exec("open " + path);           
       }else{
            ScrinchEnvToolkit.logger.log(Level.WARNING, "Pdf File Opening System is unsupported on this platform : " + os
                              + "\nPlease tell developers if you need it.");
       }
    }
    
}
