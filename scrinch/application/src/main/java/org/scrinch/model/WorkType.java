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

public class WorkType extends AbstractType {

    protected WorkType(String label) {
        super(label);
    }

    public static WorkType fromCastor(org.scrinch.model.castor.WorkType castorWorkType, ScrinchEnvToolkit toolkit) {
        String label = castorWorkType.getLabel();
        WorkType workType = toolkit.getWorkTypeFactory().getDefaultWorkType();
        if (!label.equals(workType.getLabel())) {
            workType = new WorkType(label);
            workType.setDescription(castorWorkType.getDescription());
        }
        return workType;
    }

    public org.scrinch.model.castor.WorkType toCastor() {
        org.scrinch.model.castor.WorkType castorWorkType = new org.scrinch.model.castor.WorkType();
        castorWorkType.setLabel(getLabel());
        castorWorkType.setDescription(getDescription());
        return castorWorkType;
    }
}
