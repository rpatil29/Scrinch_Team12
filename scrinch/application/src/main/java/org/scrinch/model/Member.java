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

import java.util.List;
import java.util.Vector;

public class Member implements Comparable<Member> {

    private boolean active;
    private String nickname;
    private String mobilePhone;
    private String internalPhone;
    private String fullName;

    public interface Listener{
        void memberSortablePropertiesChanged(Member member);
        void memberChanged(Member member);
    }
    
    private List<Listener> listeners = new Vector();
    
    private void fireSortablePropertiesChanged(){
        for(Listener listener : listeners){
            listener.memberSortablePropertiesChanged(this);
        }
    }

    private void fireChange(){
        for(Listener listener : listeners){
            listener.memberChanged(this);
        }
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
    }
    
    public Member(String nickname){
        this.active = true;
        this.nickname = nickname;
    }

    public boolean isActive(){
        return this.active;
    }

    public void setActive(boolean active){
        this.active = active;
        fireSortablePropertiesChanged();
        fireChange();
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
        fireChange();
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
        fireSortablePropertiesChanged();
        fireChange();
    }

    public void setInternalPhone(String internalPhone){
        this.internalPhone = internalPhone;
        fireChange();
    }

    public void setMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
        fireChange();
    }

    public String getFullName(){
        return fullName;
    }

    public String getNickname(){
        return nickname;
    }

    public String getInternalPhone(){
        return internalPhone;
    }

    public String getMobilePhone(){
        return mobilePhone;
    }

    public String toString(){
        return nickname;
    }
    
    public int compareTo(Member anotherMember){
        if(anotherMember.isActive()!=this.isActive()){
            return anotherMember.isActive()?1:-1;
        }
        return getNickname().compareTo(anotherMember.getNickname());
    }
    
    public boolean equals(Object o){
        if( ! (o instanceof Member)){
            return false;
        }
        return (this.compareTo((Member)o)==0);
    }

    public static Member fromCastor(org.scrinch.model.castor.Member castorMember){
        Member member = new Member(castorMember.getNickname());
        member.setActive(castorMember.getActive());
        member.setFullName(castorMember.getFullname());
        member.setInternalPhone(castorMember.getInternalPhone());
        member.setMobilePhone(castorMember.getMobilePhone());
        return member;
    }

    public org.scrinch.model.castor.Member toCastor(){
        org.scrinch.model.castor.Member castorMember = new org.scrinch.model.castor.Member();
        castorMember.setActive(isActive());
        castorMember.setFullname(getFullName());
        castorMember.setInternalPhone(getInternalPhone());
        castorMember.setMobilePhone(getMobilePhone());
        castorMember.setNickname(getNickname());
        return castorMember;
    }
}
