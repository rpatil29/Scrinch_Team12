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

import junit.framework.TestCase;

public class TargetFactoryTest extends TestCase {
    
    public TargetFactoryTest(String testName) {
        super(testName);
    }

    private ScrinchEnvToolkit toolkit;

    @Override
    public void setUp(){
        toolkit = new ScrinchEnvToolkit();
        Target target = toolkit.getTargetFactory().createTarget();
        target.setActive(true);
        target = toolkit.getTargetFactory().createTarget();
        target.setActive(true);
        target = toolkit.getTargetFactory().createTarget();
        target.setActive(false);
    }
    
    @Override
    public void tearDown(){
        toolkit.getTargetFactory().disposeAll();
    }
    
    public void testFindActiveTargets() {
        System.out.println("TargetFactory.findActiveTargets");
        assertEquals(2, toolkit.getTargetFactory().findActiveTargets().size());
    }

    public void testFindTargetWithLabel() {
        System.out.println("TargetFactory.findTargetWithLabel");
        Target target = toolkit.getTargetFactory().getTargetList().get(0);
        target.setLabel("label1");
        assertEquals(target, toolkit.getTargetFactory().findTargetWithLabel("label1"));
    }
}
