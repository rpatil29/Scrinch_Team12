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

package org.scrinch.model;

import junit.framework.*;

public class ModelTestSuite extends TestCase {
    
    public ModelTestSuite(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("ModelTestSuite");
        suite.addTestSuite(ItemTest.class);
        suite.addTestSuite(ItemToolkitTest.class);
        suite.addTestSuite(ProjectFactoryTest.class);
        suite.addTestSuite(ProjectTest.class);
        suite.addTestSuite(ScrinchEnvToolkitTest.class);
        suite.addTestSuite(SlowDownTest.class);
        suite.addTestSuite(SprintFactoryTest.class);
        suite.addTestSuite(SprintTest.class);
        suite.addTestSuite(TargetFactoryTest.class);
        return suite;
    }
    
}
