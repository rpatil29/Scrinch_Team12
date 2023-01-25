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

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;

public class Item implements Comparable, Target.Listener, Undoable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static class Visa{
        public Member member;
        public Date date;
        public Status status;
        public String comment;

        public Visa(Status status, Member member, Date date, String comment){
            this.member = member;
            this.date = date;
            this.status = status;
            this.comment = comment;
        }
        
        public Visa(Status status, Member member, Date date){
            this(status, member, date, null);
        }

        @Override
        public String toString(){
            String nickname = this.member!=null?", " + this.member.getNickname() : "";
            String dateStr = this.date!=null?", " + sdf.format(this.date) : "";
            String displayableComment = this.comment!=null?", (" + this.comment +")" : "";
            return this.status + nickname + dateStr + displayableComment;
        }
        
        public org.scrinch.model.castor.Visa toCastor(){
            org.scrinch.model.castor.Visa cVisa = new org.scrinch.model.castor.Visa();
            if(this.date!=null){
                cVisa.setDate(new org.exolab.castor.types.Date(this.date));
            }
            if(this.member!=null){
                cVisa.setMemberNickname(member.getNickname());
            }
            if(this.comment!=null){
                cVisa.setComment(this.comment);
            }
            cVisa.setStatus(this.status.toString());
            return cVisa;
        }

        public static Visa fromCastor(org.scrinch.model.castor.Visa cVisa, ScrinchEnvToolkit toolkit){
            Visa visa = new Visa(Item.Status.valueOfLabel(cVisa.getStatus()),
                                 toolkit.getMemberFactory().findMemberWithNickname(cVisa.getMemberNickname()),
                                 cVisa.getDate()!=null?cVisa.getDate().toDate():null,
                                 cVisa.getComment());

            return visa;
        }
    }

    public interface AnyChangeListener {
        public void changeOccured(Item item);
    };

    public interface StatusOrEvaluationChangeListener {
        public void changeOccured(Item item);
    };

     private enum GlobalStatus{
        WORK_DONE,
        WORK_TO_BE_DONE,
        INACTIVE
    };
    
    public enum Status {
        STANDBY(  "STBY",  1, Color.LIGHT_GRAY,         GlobalStatus.INACTIVE),
        DELAYED(  "DLAY",  1, new Color(100, 110, 120), GlobalStatus.WORK_TO_BE_DONE),
        EVALUATED("EVAL",  2, new Color(234, 117, 157), GlobalStatus.WORK_TO_BE_DONE),
        DONE(     "DONE",  3, new Color(103, 151, 220), GlobalStatus.WORK_DONE),
        NOT_OK(   "NOK",   2, new Color(212, 74, 56),   GlobalStatus.WORK_TO_BE_DONE),
        OK(       "OK",    4, new Color(28, 192, 82),   GlobalStatus.WORK_DONE),
        CANCELLED("CNL",   6, new Color(233, 220, 82),  GlobalStatus.INACTIVE),
        POSTPONED("PP",    5, new Color(208, 90, 197),  GlobalStatus.INACTIVE);

        private final String label;
        private final int mainOrderValue;
        private final Color colorCode;
        private final GlobalStatus globalStatus;

        public static Status[] getConcreteStatuses(){
            return new Status[]{
                STANDBY,
                EVALUATED,
                DONE,
                NOT_OK,
                OK,
                CANCELLED,
                POSTPONED
            };
        }
        
        public boolean isWorkDone(){
            return globalStatus.equals(GlobalStatus.WORK_DONE);
        }
        
        public boolean isWorkToBeDone(){
            return globalStatus.equals(GlobalStatus.WORK_TO_BE_DONE);
        }
        
        public boolean isActive(){
            return !globalStatus.equals(GlobalStatus.INACTIVE);
        }
        
        Status(String label, int mainOrderValue, Color colorCode, GlobalStatus globalStatus){
          this.label = label;
          this.mainOrderValue = mainOrderValue;
          this.colorCode = colorCode;
          this.globalStatus =  globalStatus;
        }

        @Override
        public String toString(){
          return label;
        }

        public int getOrderComparison(Status s){
            return mainOrderValue-s.mainOrderValue;
        }

        public Color getColorCode(){
            return colorCode;
        }

        public static Status valueOfLabel(String typeLabel){
            switch (typeLabel) {
                case "STBY":
                    return STANDBY;
                case "DELAY":
                    return DELAYED;
                case "EVAL":
                    return EVALUATED;
                case "DONE":
                    return DONE;
                case "NOK":
                    return NOT_OK;
                case "OK":
                    return OK;
                case "CNL":
                    return CANCELLED;
                case "PP":
                    return POSTPONED;
                default:
                    return null;
            }
        }
    };

    private FiboPoint evaluation = FiboPoint.ONE;
    private FiboPoint businessValue = FiboPoint.THIRTY_FOUR;
    private ProjectItemSet projectItemSet;
    private String title;
    private String description;
    private OriginType originType;
    private WorkType workType;
    private List<AnyChangeListener> anyChangeListeners = new ArrayList<>();
    private List<StatusOrEvaluationChangeListener> statusOrEvaluationChangeListeners = new ArrayList<>();
    private Stack<Visa> visas = new Stack();
    private String key;
    private Set<Sprint> relatedSprints = new HashSet();
    private Target target;
    private Date firstVisaDate;
    private boolean notificationsActive = true;

    private static long nextId;
    
    protected static long getNextId(){
        return nextId;
    }
    
    protected static void resetNextId(){
        nextId = 0L;
    }

    private final ScrinchEnvToolkit toolkit;

    public Item(ScrinchEnvToolkit toolkit) {
        this(""+Long.toString(nextId), toolkit);
        visas.add(new Visa(Status.STANDBY, null, null));
    }

    public Item(String key, ScrinchEnvToolkit toolkit){
        setKey(key);
        this.toolkit = toolkit;
        this.workType = toolkit.getWorkTypeFactory().getDefaultWorkType();
        this.originType = toolkit.getOriginTypeFactory().getDefaultOriginType();
    }
    
    protected void releaseAllResources(){
        if(this.target!=null){
            this.target.removeListener(this);
        }
    }

    public Date getFirstVisaDate(){
        return firstVisaDate;
    }

    public String getKey() {
        return key;
    }

    protected final void setKey(String key) {
        this.key = key;
        if(Long.parseLong(key)>=nextId){
            nextId = Long.parseLong(key)+1;
        }
    }

    @Override
    public void targetChanged(Target target){
        notifyAnyChangeListeners();
    }
    
    @Override
    public void memberSortablePropertiesChanged(Target target){
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        if( this.target!=target ){
            storeUndoableState();
            if (this.target!=null){
                this.target.removeListener(this);
            }

            if (target!=null){
                target.addListener(this);
            }

            this.target = target;
            notifyAnyChangeListeners();
        }
    }

    private void setNotificationsActive(boolean active){
        this.notificationsActive = active;
    }
    
    private void storeUndoableState(){
        if(this.notificationsActive){
            UndoManager.getInstance().storeUndoableState(this, toCastor());
        }
    }
    
    public void addAnyChangeListener(AnyChangeListener listener){
        this.anyChangeListeners.add(listener);
    }

    public void removeAnyChangeListener(AnyChangeListener listener){
        this.anyChangeListeners.remove(listener);
    }

    private void notifyAnyChangeListeners(){
        if(this.notificationsActive){
            this.toolkit.dataChanged();
            for (AnyChangeListener anyChangeListener : anyChangeListeners) {
                anyChangeListener.changeOccured(this);
            }
        }
    }

    public void addStatusOrEvaluationChangeListener(StatusOrEvaluationChangeListener listener){
        this.statusOrEvaluationChangeListeners.add(listener);
    }

    public void removeStatusOrEvaluationChangeListener(StatusOrEvaluationChangeListener listener){
        this.statusOrEvaluationChangeListeners.remove(listener);
    }

    private void notifyStatusOrEvaluationChangeListeners(){
        if(this.notificationsActive){
            for (StatusOrEvaluationChangeListener statusOrEvaluationChangeListener : statusOrEvaluationChangeListeners) {
                statusOrEvaluationChangeListener.changeOccured(this);
            }
        }
    }

    public OriginType getOriginType() {
        return originType;
    }

    public void setOriginType(OriginType originType) {
        if( this.originType!=originType ){
            storeUndoableState();
            this.originType = originType;
            notifyAnyChangeListeners();
        }
    }

    public void setEvaluation(FiboPoint evaluation) {
        if( this.evaluation!=evaluation && evaluation!=null ){
            storeUndoableState();
            this.evaluation = evaluation;
            notifyAnyChangeListeners();
        }
        notifyStatusOrEvaluationChangeListeners();
    }

    public FiboPoint getEvaluation(){
        return evaluation;
    }

    public void setBusinessValue(FiboPoint businessValue) {
        if( this.businessValue!=businessValue && businessValue!=null ){
            storeUndoableState();
            this.businessValue = businessValue;
            notifyAnyChangeListeners();
        }
    }

    public FiboPoint getBusinessValue(){
        return businessValue;
    }

    public double getRank(){
        if( evaluation.intValue()==0 ){
            return businessValue.intValue();
        }else{
            return (double)businessValue.intValue()/(double)evaluation.intValue();
        }
    }

    public ProjectItemSet getProjectItemSet() {
        return projectItemSet;
    }

    public void setProjectItemSet(ProjectItemSet projectItemSet){
        ProjectItemSet oldProjectItemSet = this.projectItemSet;
        this.projectItemSet = null;
        if( oldProjectItemSet!=null && oldProjectItemSet.containsItem(this) ){
            try{
                oldProjectItemSet.removeItem(this, true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(projectItemSet!=null){ //we test the new projectItemSet
            projectItemSet.addItem(this);
            this.projectItemSet = projectItemSet;
        }
        notifyAnyChangeListeners();
    }

    public boolean isActive(){
        return getStatus().isActive();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        storeUndoableState();
        this.title = title;
        notifyAnyChangeListeners();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        storeUndoableState();
        this.description = description;
        notifyAnyChangeListeners();
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        if( this.workType!=workType ){
            storeUndoableState();
            this.workType = workType;
            notifyAnyChangeListeners();
        }
    }

    public Set<Sprint> getRelatedSprints(){
        return relatedSprints;
    }

    public void addSprint(Sprint sprint){
        relatedSprints.add(sprint);
        notifyAnyChangeListeners();
    }

    public boolean isOrphan(){
        return getProjectItemSet()==null;
    }

    public void removeSprint(Sprint sprint){
        relatedSprints.remove(sprint);
        notifyAnyChangeListeners();
    }

    public static boolean isStatusValid(Status status, Stack<Visa> currentVisas) {
        boolean statusValid = false;
        try{
            statusValid = isStatusValid(status,currentVisas,(Date)null);
        } catch(ScrinchException se){
        }
        return statusValid;
    }

    public static boolean isStatusValid(Status status, Stack<Visa> currentVisas, Sprint sprint) {
        boolean statusValid = false;
        try{
            if(sprint==null){
                statusValid = isStatusValid(status,currentVisas,(Date)null);
            } else {
                statusValid = isStatusValid(status,currentVisas,sprint.getEndDate());
            }
        } catch(ScrinchException se){
        }
        return statusValid;
    }

    private static boolean isStatusValid(Status status, Stack<Visa> currentVisas, Date date) throws ScrinchException {
        boolean validVisa = false;

        if(currentVisas.empty()){
            return status.equals(Status.STANDBY);
        }

        Visa currentVisa = currentVisas.peek();
        if(date!=null && currentVisa.date!=null && currentVisa.date.after(date)){
            throw new ScrinchException("No visa older than the last one can be added");
        }

        Status peekStatus = currentVisa.status;
        if( !peekStatus.equals(Status.CANCELLED) && !peekStatus.equals(Status.POSTPONED) ){
            if( (status.equals(Status.CANCELLED) || status.equals(Status.POSTPONED))
                && !peekStatus.equals(Status.OK)){
              validVisa = true;
            } else if( peekStatus.equals(Status.DONE) ){
                validVisa = (status.equals(Status.NOT_OK) || status.equals(Status.OK));
            } else if( peekStatus.equals(Status.NOT_OK) ){
                validVisa = status.equals(Status.DONE);
            } else if( peekStatus.equals(Status.OK) ){
                validVisa = false;
            } else if ( peekStatus.equals(Status.STANDBY)){
                validVisa = status.equals(Status.EVALUATED);
            } else {
                validVisa = (status.getOrderComparison(peekStatus)==1);
            }
        }
        return validVisa;
    }

    public void addVisa(Visa visa) throws ScrinchException {
        storeUndoableState();
        if( !isStatusValid(visa.status, visas, visa.date) ) {
            throw new ScrinchException("Visa cannot be added (former visas not filled in for item #"+this.key+")");
        }
        if(visa.date!=null && (firstVisaDate==null || firstVisaDate.after(visa.date))){
            firstVisaDate = visa.date;
        }
        visas.push(visa);
        notifyAnyChangeListeners();
        notifyStatusOrEvaluationChangeListeners();
    }

    public Visa getLastDoneVisaIfTaskDoneInSprint(Sprint sprint){
        Visa lastDoneVisaIfTaskDoneInSprint = null;
        if(getStatus(sprint).equals(Status.DONE)){
            for (Visa currentVisa:visas){
                if(currentVisa.date!=null && sprint.getEndDate().compareTo(currentVisa.date)>=0) {
                    lastDoneVisaIfTaskDoneInSprint = currentVisa;
                }
            }
        }else if(getStatus(sprint).equals(Status.OK)){
            Visa previousVisa;
            for (Visa currentVisa:visas){
                previousVisa = currentVisa;
                if(currentVisa.date!=null && sprint.getEndDate().compareTo(currentVisa.date)>=0) {
                    lastDoneVisaIfTaskDoneInSprint = previousVisa;
                }
            }
        }
        return lastDoneVisaIfTaskDoneInSprint;
    }

    public Stack<Visa> getVisasHistory(){
        return visas;
    }

    public void removeLastVisa(){
        storeUndoableState();
        this.visas.pop();
        notifyAnyChangeListeners();
        notifyStatusOrEvaluationChangeListeners();
    }

    public Status getNextValidStatus(){
        Status toReturn = getStatus();
        for(Status status : Status.values()){
            if(status.getOrderComparison(toReturn)>0){
                if(isStatusValid(status, visas)){
                    toReturn = status;
                    break;
                }
            }
        }
        return toReturn;
    }

    public Status getStatus(){
        return getStatus(null);
    }

    public Status getStatus(Sprint sprint){
        Status currentStatus = Item.Status.STANDBY;
        if(sprint==null){
            if(!visas.empty()){
                Visa visa = visas.peek();
                currentStatus = visa.status;
            }
        } else {
            for (Visa visa:visas){
                if(visa.date!=null && sprint.getEndDate().compareTo(visa.date)>=0) {
                    currentStatus = visa.status;
                }
            }
            if(currentStatus.equals(Status.EVALUATED) && isAffectedToALaterSprintThan(sprint)){
                currentStatus = Status.DELAYED;
            }
        }
        return currentStatus;
    }

    public boolean isAffectedToALaterSprintThan(Sprint sprint){
        boolean affectedToALaterSprint = false;
        for(Sprint currentSprint : this.relatedSprints){
            if(currentSprint.getStartDate().after(sprint.getStartDate())){
                affectedToALaterSprint = true;
                break;
            }
        }
        return affectedToALaterSprint;
    }
    
    public int compareTo(Object o, Object parameter) {
        int scale = 0;
        Sprint sprint = null;
        if( parameter instanceof Sprint ){
            sprint = (Sprint)parameter;
        }
        if(o instanceof Item){
            Item otherItem = (Item)o;
            scale = getStatus(sprint).getOrderComparison(otherItem.getStatus(sprint));
            if (scale==0){
                scale = new Double(otherItem.getRank()).compareTo(getRank());
            }
            if(scale==0){
                scale = otherItem.getBusinessValue().compareTo(getBusinessValue());
            }
            if(scale==0){
                scale = String.valueOf(title).compareTo(String.valueOf(otherItem.title));
            }
            if(scale==0){
                scale = String.valueOf(key).compareTo(String.valueOf(otherItem.key));
            }
        }
        return scale;
    }

    @Override
    public int compareTo(Object o) {
        return compareTo(o, null);
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Item){
            return (this.compareTo(o)==0);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString(){
        return "#"+getKey()+" "+getTitle()+" : "+getDescription()+"\n";
    }
    
    public org.scrinch.model.castor.Item toCastor(){
        org.scrinch.model.castor.Item cItem = new org.scrinch.model.castor.Item();
        cItem.setBusinessValue(this.businessValue.intValue());
        cItem.setDescription(this.description);
        cItem.setId(this.key);
        if(this.originType!=null){
            cItem.setRequestType(this.originType.toString());
        }
        cItem.setTitle(this.title);
        cItem.setWork(this.evaluation.intValue());
        if(this.workType!=null){
            cItem.setWorkType(this.workType.toString());
        }
        if(this.target!=null){
            cItem.setTarget(this.target.toString());
        }
        org.scrinch.model.castor.Visas castVisas = new org.scrinch.model.castor.Visas();
        for (int i = 0; i < this.visas.size(); i++) {
            Visa visa = this.visas.elementAt(i);
            castVisas.addVisa(visa.toCastor());
        }
        cItem.setVisas(castVisas);
        return cItem;
    }
    
    public static Item fromCastor(org.scrinch.model.castor.Item cItem, ScrinchEnvToolkit toolkit) throws ScrinchException{
        Item item = null;
        if(cItem!=null){
            item = new Item(cItem.getId(), toolkit);
            restore(item,cItem, toolkit);
        }
        return item;
    }
    
    @Override
    public void restore(Object formerState) throws ClassCastException, ScrinchException{
        restore(this,(org.scrinch.model.castor.Item)formerState, toolkit);
    }

    private static void restore(Item item, org.scrinch.model.castor.Item cItem, ScrinchEnvToolkit toolkit)throws ScrinchException{
        try{
            if(item!=null && cItem!=null){
                item.setNotificationsActive(false);
                item.setBusinessValue(FiboPoint.valueOf(cItem.getBusinessValue()));
                //suppress multiples spaces char and carriage returns from the description
                item.setDescription(cItem.getDescription()!=null?cItem.getDescription().replaceAll("\\s*\\s"," "):"");
                item.setEvaluation(FiboPoint.valueOf(cItem.getWork()));
                item.setTitle(cItem.getTitle());
                item.setWorkType(toolkit.getWorkTypeFactory().findWorkTypeWithLabel(cItem.getWorkType()));
                item.setOriginType(toolkit.getOriginTypeFactory().findOriginTypeWithLabel(cItem.getRequestType()));
                item.setTarget(toolkit.getTargetFactory().findTargetWithLabel(cItem.getTarget()));

                item.getVisasHistory().clear();
                org.scrinch.model.castor.Visas cVisas = cItem.getVisas();
                for (int i = 0; i < cVisas.getVisaCount(); i++) {
                    org.scrinch.model.castor.Visa cVisa = cVisas.getVisa(i);
                    item.addVisa(Item.Visa.fromCastor(cVisa, toolkit));
                }
                item.setNotificationsActive(true);
            }
        }catch(Exception e){
            throw new ScrinchException("Item "+cItem.getId()+" could not be properly restored: "+e.getMessage());
        }
    }
}