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

public enum FiboPoint{
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FIVE(5),
    EIGHT(8),
    THIRTEEN(13),
    TWENTY_ONE(21),
    THIRTY_FOUR(34);

    private int intValue;

    FiboPoint(int intValue){
        this.intValue = intValue;
    }

    public int intValue(){
        return this.intValue;
    }

    @Override
    public String toString(){
        return intValue()+"";
    }

    public static FiboPoint valueOf(int value){
        switch(value){
            case 0: return FiboPoint.ZERO;
            case 1: return FiboPoint.ONE;
            case 2: return FiboPoint.TWO;
            case 3: return FiboPoint.THREE;
            case 5: return FiboPoint.FIVE;
            case 8: return FiboPoint.EIGHT;
            case 13: return FiboPoint.THIRTEEN;
            case 21: return FiboPoint.TWENTY_ONE;
            case 34: return FiboPoint.THIRTY_FOUR;
            default: return null;
        }
    }

    public static FiboPoint getPrevious(FiboPoint point){
        FiboPoint previous = ZERO;
        int max = -1;
        for(FiboPoint p:FiboPoint.values()){
            if(p.intValue>max && p.intValue<point.intValue){
                previous = p;
            }
        }
        return previous;
    }

}
