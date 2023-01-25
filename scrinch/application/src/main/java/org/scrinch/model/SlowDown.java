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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SlowDown {

    private Collection<Member> memberList = new ArrayList<Member>();
    private String description;
    private double dayCount;
    private Sprint sprint;

    public SlowDown() {
    }

    public void setSprintAndAddToSprint(Sprint sprint){
        setSprint(sprint);
        this.sprint.addSlowDown(this);
    }

    public void setSprint(Sprint sprint){
        this.sprint = sprint;
    }

    public Sprint getSprint(){
        return sprint;
    }

    public void addMember(Member member){
        memberList.add(member);
    }

    public void removeMember(Member member){
        memberList.remove(member);
    }

    public void setMembers(Collection<Member> memberList){
        this.memberList = memberList;
    }

    public Collection<Member> getMembers(){
        return memberList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDayCount() {
        return dayCount;
    }

    public void setDayCount(double dayCount) {
        this.dayCount = dayCount;
    }

    public double getCumulativeDayCount(){
        return dayCount*memberList.size();
    }

    public org.scrinch.model.castor.SlowDown toCastor(){
        org.scrinch.model.castor.SlowDown cSlowDown = new org.scrinch.model.castor.SlowDown();
        cSlowDown.setCost(getDayCount());
        cSlowDown.setDescription(getDescription());
        for(Member member:getMembers()){
            cSlowDown.addMemberLink(member.getNickname());
        }
        return cSlowDown;
    }

    public static SlowDown fromCastor(org.scrinch.model.castor.SlowDown cSlowDown, ScrinchEnvToolkit toolkit){
        SlowDown slowDown = new SlowDown();
        slowDown.setDayCount(cSlowDown.getCost());
        slowDown.setDescription(cSlowDown.getDescription());

        for(int i=0; i<cSlowDown.getMemberLinkCount(); i++){
            slowDown.addMember(toolkit.getMemberFactory().findMemberWithNickname(cSlowDown.getMemberLink(i)));
        }
        return slowDown;
    }
}
