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
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class MemberFactory implements Member.Listener{

    private List<Member> members = new Vector();
    private final ScrinchEnvToolkit toolkit;

    protected MemberFactory(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
    }

    public void memberSortablePropertiesChanged(Member member){
        Collections.sort(members);
    }
    
    public Member findMemberWithNickname(String nickName){
        Member member = null;
        for(int i=0; i<members.size(); i++){
            Member currentMember = members.get(i);
            if(currentMember.getNickname().equals(nickName)){
                member = currentMember;
                break;
            }
        }
        return member;
    }
    
    public Member getScrumMaster(){
        return members.get(0);
    }

    public static String getMembersAsString(Collection<Member> members){
        StringBuilder membersList = new StringBuilder();
        for(Member member:members){
            if( membersList.length()>0 ){
                membersList.append(",");
            }
            membersList.append(member.getNickname());
        }
        return membersList.toString();
    }

    public static String getMembersAsHTMLvList(Collection<Member> members){
        StringBuilder builder = new StringBuilder("<HTML>");
        for(Member member:members){
            if(builder.length()>6){
                builder.append("<BR>");
            }
            builder.append(member.getNickname());
        }
        builder.append("</HTML>");
        return builder.toString();
    }

    public List<Member> getMembersFromString(String memberList){
        StringTokenizer tokenizer = new StringTokenizer(memberList,",");
        List<Member> members = new Vector();
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();

            Member member = findMemberWithNickname(token);
            if(member!=null){
                members.add(member);
            }
        }
        return members;
    }

    public String getMembersAsString(){
        String membersAsString = "";
        for(int i=0; i<members.size(); i++){
            membersAsString += members.get(i).getNickname()+",";
        }
        if(membersAsString.length()>0){
            membersAsString = membersAsString.substring(0,membersAsString.length()-1);
        }
        return membersAsString;
    }

    public Member createMember(){
        Member member = new Member("User"+(members.size()+1));
        member.addListener(this);
        members.add(member);
        Collections.sort(members);
        this.toolkit.dataChanged();
        return member;
    }

    public void loadFromCastor(org.scrinch.model.castor.Member[] castorMembers){
        for(int i=0 ; i<castorMembers.length; i++){
            Member member = Member.fromCastor(castorMembers[i]);
            member.addListener(this);
            members.add(member);
        }
        Collections.sort(members);
    }

    public org.scrinch.model.castor.Members toCastor(){
        org.scrinch.model.castor.Members cMembers = new org.scrinch.model.castor.Members();

        for(Member member : members){
            cMembers.addMember(member.toCastor());
        }
        return cMembers;
    }

    public List<Member> getMemberList(){
        return members;
    }

    public List<Member> getActiveMemberList(){
        List<Member> activeMembers = new ArrayList<Member>();
        for(Member member:members){
            if(member.isActive()){
                activeMembers.add(member);
            }
        }
        return activeMembers;
    }

    public void dispose(Member member){
        members.remove(member);
        this.toolkit.dataChanged();
    }

    public void disposeAll(){
        for(Member member : members){
            member.removeListener(this);
        }
        members.clear();
        this.toolkit.dataChanged();
    }

    public void memberChanged(Member member) {
        this.toolkit.dataChanged();
    }
}
