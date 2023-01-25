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

public class OriginType extends AbstractType {

    public OriginType(String label){
        super(label);
    }
    
    public static OriginType fromCastor(org.scrinch.model.castor.OriginType castorOriginType, ScrinchEnvToolkit toolkit){
        String label = castorOriginType.getLabel();
        OriginType originType = toolkit.getOriginTypeFactory().getDefaultOriginType();
        if ( ! label.equals(originType.getLabel())){
            originType = new OriginType(label);
            originType.setDescription(castorOriginType.getDescription());
        }
        return originType;
    }

    public org.scrinch.model.castor.OriginType toCastor(){
        org.scrinch.model.castor.OriginType castorOriginType = new org.scrinch.model.castor.OriginType();
        castorOriginType.setLabel(getLabel());
        castorOriginType.setDescription(getDescription());
        return castorOriginType;
    }
}
