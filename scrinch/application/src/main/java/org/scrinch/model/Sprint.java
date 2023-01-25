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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;
import org.scrinch.model.Item.StatusOrEvaluationChangeListener;
import org.scrinch.model.castor.DailyNotes;
import org.scrinch.model.castor.PlanningReport;
import org.scrinch.model.castor.ReviewReport;

public class Sprint extends ItemSet implements Comparable<Sprint>, StatusOrEvaluationChangeListener{

    public void changeOccured(Item item) {
        notifyItemsChanged();
    }

    public interface SprintListener {

        public void realVelocityChanged(Sprint sprint);

        public void datesChanged(Sprint sprint);

        public void workChanged(Sprint sprint);

        public void itemsChanged(Sprint sprint);
    }
    private Date startDate;
    private Date endDate;
    private double baseVelocity = 12.0; // fiboPoint by day
    private Collection<Member> memberList = new TreeSet<Member>();
    private List<SlowDown> slowDownList = new Vector();
    private List<SprintListener> sprintListeners = new Vector();
    private List<Date> workingDates = new Vector();
    private String planningMeetingReport;
    private String reviewMeetingReport;
    private String dailyMeetingsNotes;

    private final ScrinchEnvToolkit toolkit;

    public Sprint(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
        setStartDate(toolkit.getCurrentDate());
    }

    public int compareTo(Sprint o) {
        return o.startDate.compareTo(startDate);
    }

    public boolean isTopical() {
        return (!toolkit.getCurrentDate().after(endDate));
    }

    public void addSprintListener(SprintListener listener) {
        this.sprintListeners.add(listener);
    }

    public void removeSprintListener(SprintListener listener) {
        this.sprintListeners.remove(listener);
    }

    private void notifyRealVelocityChanged() {
        for (int i = 0; i < sprintListeners.size(); i++) {
            this.sprintListeners.get(i).realVelocityChanged(this);
        }
    }

    private void notifyDatesChanged() {
        for (int i = 0; i < sprintListeners.size(); i++) {
            this.sprintListeners.get(i).realVelocityChanged(this);
            this.sprintListeners.get(i).datesChanged(this);
        }
    }

    private void notifyWorkChanged() {
        for (int i = 0; i < sprintListeners.size(); i++) {
            this.sprintListeners.get(i).workChanged(this);
        }
    }

    private void notifyItemsChanged() {
        for (int i = 0; i < sprintListeners.size(); i++) {
            this.sprintListeners.get(i).itemsChanged(this);
            this.sprintListeners.get(i).workChanged(this);
        }
        this.toolkit.dataChanged();
    }

    public void addItems(List<Item> items) throws ScrinchException {
        for (Item item : items) {
            doAddItem(item);
        }
        notifyItemsChanged();
    }

    private void doAddItem(Item item){
        super.addItem(item);
        item.addSprint(this);
        item.addStatusOrEvaluationChangeListener(this);
    }
    
    @Override
    public void addItem(Item item) {
        doAddItem(item);
        notifyItemsChanged();
    }

    @Override
    public void removeItem(Item item, boolean itemWillBeMoved) throws ScrinchException {
        super.removeItem(item, itemWillBeMoved);
        item.removeSprint(this);
        item.removeStatusOrEvaluationChangeListener(this);
        notifyItemsChanged();
    }

    public double getBaseVelocity() {
        return baseVelocity;
    }

    public void setBaseVelocity(double baseVelocity) {
        this.baseVelocity = baseVelocity;
        notifyRealVelocityChanged();
        this.toolkit.dataChanged();
    }

    public double getPossibleVelocity() {
        double possibleVelocity = baseVelocity;
        if (!memberList.isEmpty()) {
            possibleVelocity = baseVelocity * getCorrectedBusinessDaysCountWithSlowDowns() / getBusinessDaysCount();
        }
        return possibleVelocity;
    }

    protected double getCorrectedBusinessDaysCountWithSlowDowns() {
        double correctedBusinessDaysCount = (memberList.size() * getBusinessDaysCount() - getGlobalSlowDown()) / memberList.size();
        return correctedBusinessDaysCount;
    }

    public boolean isRunning() {
        Date today = toolkit.getCurrentDate();
        return !today.before(getStartDate()) && !today.after(getEndDate());
    }

    private double getPastBusinessDaysCount() {
        double daysCount = -1;
        if (isRunning()) {
            daysCount = (double) toolkit.getBusinessDaysCount(
                    startDate, toolkit.getCurrentDate());
        }
        return daysCount;
    }

    public double getResultingVelocity() throws ScrinchException {
        return getResultingVelocity(false);
    }

    public double getResultingVelocity(boolean correctedAccordingToSlowDowns) throws ScrinchException {
        double resultingVelocity = 0;
        if (!isTopical() || isRunning()) {
            double businessDays = correctedAccordingToSlowDowns ? getCorrectedBusinessDaysCountWithSlowDowns() : getBusinessDaysCount();
            resultingVelocity = getResultingWorkInFiboPoints() / businessDays;
        } else {
            throw new ScrinchException("No resulting velocity available : sprint " + getTitle() + " is not yet opened");
        }
        if (isRunning()) {
            // computed in proportion with the number of days past
            resultingVelocity = resultingVelocity / getPastBusinessDaysCount() * getBusinessDaysCount();
        }
        return (double) (Math.round(resultingVelocity * 10) / 10.0);
    }

    public double getResultingWorkInFiboPoints() {
        double resultingWork = 0;
        for (Item item : itemList) {
            Item.Visa lastVisa = item.getLastDoneVisaIfTaskDoneInSprint(this);
            if (lastVisa != null) {
                resultingWork += item.getEvaluation().intValue();
            }
        }
        return resultingWork;
    }

    public double getGlobalSlowDown() {
        double globalSlowDown = 0;
        for (int i = 0; i < slowDownList.size(); i++) {
            globalSlowDown += slowDownList.get(i).getCumulativeDayCount();
        }
        return globalSlowDown;
    }

    public void addSlowDown(SlowDown slowDown) {
        getSlowDownList().add(slowDown);
        notifyRealVelocityChanged();
        this.toolkit.dataChanged();
    }

    public void removeSlowDown(SlowDown slowDown) {
        getSlowDownList().remove(slowDown);
        notifyRealVelocityChanged();
        this.toolkit.dataChanged();
    }

    @Override
    public void rankChanged(Item item) {
        super.rankChanged(item);
        notifyWorkChanged();
        this.toolkit.dataChanged();
    }

    public double getAvailableResourcesInFiboPoints() {
        return getBusinessDaysCount() * getPossibleVelocity();
    }

    public double getOverloadedResourcesCount() {
        return (double) ItemToolkit.getActiveItemEvaluationCount(getItemList()) - getAvailableResourcesInFiboPoints();
    }

    public Date getStartDate() {
        return startDate;
    }

    public final void setStartDate(Date startDate) {
        this.startDate = toolkit.getNextWeekDayIfNotWeekDay(startDate);
        this.endDate = toolkit.getLatestDateAdjustedIfNotWeekDay(this.startDate, this.endDate);
        resetDefaultDates();
        notifyDatesChanged();
        this.toolkit.dataChanged();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = toolkit.getLatestDateAdjustedIfNotWeekDay(this.startDate, endDate);
        resetDefaultDates();
        notifyDatesChanged();
        this.toolkit.dataChanged();
    }

    private void resetDefaultDates() {
        this.workingDates.clear();
        Calendar calendar = toolkit.getPreparedCalendarInstance();
        calendar.setTime(this.startDate);
        this.workingDates.add(calendar.getTime());
        while (calendar.getTime().before(this.endDate)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            while (!toolkit.isWeekDay(calendar.getTime())) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            this.workingDates.add(calendar.getTime());
        }
    }

    public double getBusinessDaysCount() {
        return workingDates.size();
    }

    public Collection<Member> getMembers() {
        return memberList;
    }

    public void addMember(Member resource) {
        this.memberList.add(resource);
        notifyRealVelocityChanged();
        this.toolkit.dataChanged();
    }

    public void removeMember(Member resource) {
        this.memberList.remove(resource);
        notifyRealVelocityChanged();
        this.toolkit.dataChanged();
    }

    public void removeAllMembers() {
        this.memberList.clear();
        notifyRealVelocityChanged();
        this.toolkit.dataChanged();
    }

    public void replaceExistingMemberList(Collection<Member> memberList) {
        removeAllMembers();
        addAllMembers(memberList);
        this.toolkit.dataChanged();
    }

    public void addAllMembers(Collection<Member> memberList) {
        for (Member member:memberList) {
            this.addMember(member);
        }
        notifyRealVelocityChanged();
        this.toolkit.dataChanged();
    }

    public List<SlowDown> getSlowDownList() {
        return slowDownList;
    }

    public void setSlowDownList(List<SlowDown> slowDownList) {
        this.slowDownList = slowDownList;
        for (SlowDown slowDown : slowDownList) {
            slowDown.setSprint(this);
        }
        notifyRealVelocityChanged();
        this.toolkit.dataChanged();
    }

    public List<Date> getWorkingDates() {
        return this.workingDates;
    }

    public void setPlanningMeetingReport(String report) {
        this.planningMeetingReport = report;
        this.toolkit.dataChanged();
    }

    public void setReviewMeetingReport(String report) {
        this.reviewMeetingReport = report;
        this.toolkit.dataChanged();
    }

    public void setDailyMeetingsNotes(String report) {
        this.dailyMeetingsNotes = report;
        this.toolkit.dataChanged();
    }

    public String getPlanningMeetingReport() {
        return this.planningMeetingReport;
    }

    public String getReviewMeetingReport() {
        return this.reviewMeetingReport;
    }

    public String getDailyMeetingsNotes() {
        return this.dailyMeetingsNotes;
    }

    public boolean hasItem(String key) {
        for (Item item:this.itemList) {
            if (key.equals(item.getKey())) {
                return true;
            }
        }
        return false;
    }

    public org.scrinch.model.castor.Sprint toCastor() throws UnsupportedEncodingException {
        org.scrinch.model.castor.Sprint cSprint = new org.scrinch.model.castor.Sprint();
        cSprint.setName(this.getTitle());
        cSprint.setStartingDate(new org.exolab.castor.types.Date(this.getStartDate()));
        cSprint.setEndDate(new org.exolab.castor.types.Date(this.getEndDate()));
        cSprint.setVelocity(this.getBaseVelocity());
        org.scrinch.model.castor.SprintTeam cTeam = new org.scrinch.model.castor.SprintTeam();
        cSprint.setSprintTeam(cTeam);
        for (Member member : getMembers()) {
            cTeam.addMemberLink(member.getNickname());
        }

        org.scrinch.model.castor.Todo cTodo = new org.scrinch.model.castor.Todo();
        cSprint.setTodo(cTodo);
        for (Item item:this.itemList) {
            cTodo.addItemLink(item.getKey());
        }
        org.scrinch.model.castor.SlowDowns cSlowdowns = new org.scrinch.model.castor.SlowDowns();
        cSprint.setSlowDowns(cSlowdowns);
        for (SlowDown slowDown:this.getSlowDownList()) {
            cSlowdowns.addSlowDown(slowDown.toCastor());
        }

        if (planningMeetingReport != null) {
            PlanningReport cPlanningReport = new PlanningReport();
            cPlanningReport.setEncoding(ScrinchEnvToolkit.DEFAULT_REPORT_ENCODING);
            String encodedText = URLEncoder.encode(planningMeetingReport,
                    ScrinchEnvToolkit.DEFAULT_REPORT_ENCODING);
            cPlanningReport.setContent(encodedText);
            cSprint.setPlanningReport(cPlanningReport);
        }

        if (dailyMeetingsNotes != null) {
            DailyNotes cDailyNotes = new DailyNotes();
            cDailyNotes.setEncoding(ScrinchEnvToolkit.DEFAULT_REPORT_ENCODING);
            String encodedText = URLEncoder.encode(dailyMeetingsNotes,
                    ScrinchEnvToolkit.DEFAULT_REPORT_ENCODING);
            cDailyNotes.setContent(encodedText);
            cSprint.setDailyNotes(cDailyNotes);
        }

        if (reviewMeetingReport != null) {
            ReviewReport cReviewReport = new ReviewReport();
            cReviewReport.setEncoding(ScrinchEnvToolkit.DEFAULT_REPORT_ENCODING);
            String encodedText = URLEncoder.encode(reviewMeetingReport,
                    ScrinchEnvToolkit.DEFAULT_REPORT_ENCODING);
            cReviewReport.setContent(encodedText);
            cSprint.setReviewReport(cReviewReport);
        }

        return cSprint;
    }

    public static Sprint fromCastor(org.scrinch.model.castor.Sprint cSprint, ScrinchEnvToolkit toolkit) throws ScrinchException,
            UnsupportedEncodingException {
        Sprint sprint = new Sprint(toolkit);
        sprint.setTitle(cSprint.getName());
        sprint.setStartDate(cSprint.getStartingDate().toDate());
        sprint.setEndDate(cSprint.getEndDate().toDate());
        sprint.setBaseVelocity(cSprint.getVelocity());

        List<SlowDown> slowDowns = new Vector<SlowDown>();
        for (int i = 0; i < cSprint.getSlowDowns().getSlowDownCount(); i++) {
            org.scrinch.model.castor.SlowDown cSlowDown = cSprint.getSlowDowns().getSlowDown(i);
            slowDowns.add(SlowDown.fromCastor(cSlowDown, toolkit));
        }
        sprint.setSlowDownList(slowDowns);

        org.scrinch.model.castor.Todo cTodo = cSprint.getTodo();
        for (int i = 0; i < cTodo.getItemLinkCount(); i++) {
            String itemKey = cTodo.getItemLink(i);
            Item item = ItemToolkit.findItem(toolkit.getItemFactory().getItemList(), itemKey);
            if (item != null) {
                sprint.addItem(item);
            } else {
                ScrinchEnvToolkit.logger.log(Level.WARNING, "Item #" + itemKey + " does not exist");
            }
        }

        org.scrinch.model.castor.Team cTeam = cSprint.getTeam();
        org.scrinch.model.castor.SprintTeam cSprintTeam = cSprint.getSprintTeam();
        if (cTeam != null) {
            // team struct is deprecated (replaced by SprintTeam), 
            // this parsing has been let for compatibility purposes
            for (int i = 0; i < cTeam.getMemberLinkCount(); i++) {
                Member member = toolkit.getMemberFactory().findMemberWithNickname(cTeam.getMemberLink(i));
                sprint.addMember(member);
                ScrinchEnvToolkit.logger.log(Level.WARNING, "(deprecated team struct used)" + member);
            }
        } else if (cSprintTeam != null) {
            for (int i = 0; i < cSprintTeam.getMemberLinkCount(); i++) {
                Member member = toolkit.getMemberFactory().findMemberWithNickname(cSprintTeam.getMemberLink(i));
                sprint.addMember(member);
            }
        }

        if (cSprint.getPlanningReport() != null) {
            sprint.setPlanningMeetingReport(URLDecoder.decode(cSprint.getPlanningReport().getContent(),
                    cSprint.getPlanningReport().getEncoding()));
        }

        if (cSprint.getDailyNotes() != null) {
            sprint.setDailyMeetingsNotes(URLDecoder.decode(cSprint.getDailyNotes().getContent(),
                    cSprint.getDailyNotes().getEncoding()));
        }

        if (cSprint.getReviewReport() != null) {
            sprint.setReviewMeetingReport(URLDecoder.decode(cSprint.getReviewReport().getContent(),
                    cSprint.getReviewReport().getEncoding()));
        }

        return sprint;
    }
}
