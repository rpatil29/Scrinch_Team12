/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class Sprint.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Sprint implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _velocity.
     */
    private double _velocity;

    /**
     * keeps track of state for field: _velocity
     */
    private boolean _has_velocity;

    /**
     * Field _startingDate.
     */
    private org.exolab.castor.types.Date _startingDate;

    /**
     * Field _endDate.
     */
    private org.exolab.castor.types.Date _endDate;

    /**
     * Field _todo.
     */
    private org.scrinch.model.castor.Todo _todo;

    /**
     * deprecated : use sprint-team instead
     */
    private org.scrinch.model.castor.Team _team;

    /**
     * Field _slowDowns.
     */
    private org.scrinch.model.castor.SlowDowns _slowDowns;

    /**
     * Field _planningReport.
     */
    private org.scrinch.model.castor.PlanningReport _planningReport;

    /**
     * Field _dailyNotes.
     */
    private org.scrinch.model.castor.DailyNotes _dailyNotes;

    /**
     * Field _reviewReport.
     */
    private org.scrinch.model.castor.ReviewReport _reviewReport;

    /**
     * Field _sprintTeam.
     */
    private org.scrinch.model.castor.SprintTeam _sprintTeam;


      //----------------/
     //- Constructors -/
    //----------------/

    public Sprint() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteVelocity(
    ) {
        this._has_velocity= false;
    }

    /**
     * Overrides the java.lang.Object.equals method.
     * 
     * @param obj
     * @return true if the objects are equal.
     */
    @Override()
    public boolean equals(
            final java.lang.Object obj) {
        if ( this == obj )
            return true;

        if (obj instanceof Sprint) {

            Sprint temp = (Sprint)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._name != null) {
                if (temp._name == null) return false;
                if (this._name != temp._name) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._name);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._name);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._name.equals(temp._name)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                    }
                }
            } else if (temp._name != null)
                return false;
            if (this._velocity != temp._velocity)
                return false;
            if (this._has_velocity != temp._has_velocity)
                return false;
            if (this._startingDate != null) {
                if (temp._startingDate == null) return false;
                if (this._startingDate != temp._startingDate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._startingDate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._startingDate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._startingDate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._startingDate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._startingDate.equals(temp._startingDate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._startingDate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._startingDate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._startingDate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._startingDate);
                    }
                }
            } else if (temp._startingDate != null)
                return false;
            if (this._endDate != null) {
                if (temp._endDate == null) return false;
                if (this._endDate != temp._endDate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._endDate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._endDate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._endDate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._endDate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._endDate.equals(temp._endDate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._endDate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._endDate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._endDate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._endDate);
                    }
                }
            } else if (temp._endDate != null)
                return false;
            if (this._todo != null) {
                if (temp._todo == null) return false;
                if (this._todo != temp._todo) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._todo);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._todo);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._todo); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._todo); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._todo.equals(temp._todo)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._todo);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._todo);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._todo);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._todo);
                    }
                }
            } else if (temp._todo != null)
                return false;
            if (this._team != null) {
                if (temp._team == null) return false;
                if (this._team != temp._team) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._team);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._team);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._team); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._team); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._team.equals(temp._team)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._team);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._team);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._team);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._team);
                    }
                }
            } else if (temp._team != null)
                return false;
            if (this._slowDowns != null) {
                if (temp._slowDowns == null) return false;
                if (this._slowDowns != temp._slowDowns) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._slowDowns);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._slowDowns);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._slowDowns); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._slowDowns); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._slowDowns.equals(temp._slowDowns)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._slowDowns);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._slowDowns);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._slowDowns);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._slowDowns);
                    }
                }
            } else if (temp._slowDowns != null)
                return false;
            if (this._planningReport != null) {
                if (temp._planningReport == null) return false;
                if (this._planningReport != temp._planningReport) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._planningReport);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._planningReport);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._planningReport); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._planningReport); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._planningReport.equals(temp._planningReport)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._planningReport);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._planningReport);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._planningReport);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._planningReport);
                    }
                }
            } else if (temp._planningReport != null)
                return false;
            if (this._dailyNotes != null) {
                if (temp._dailyNotes == null) return false;
                if (this._dailyNotes != temp._dailyNotes) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._dailyNotes);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._dailyNotes);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dailyNotes); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dailyNotes); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._dailyNotes.equals(temp._dailyNotes)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dailyNotes);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dailyNotes);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dailyNotes);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dailyNotes);
                    }
                }
            } else if (temp._dailyNotes != null)
                return false;
            if (this._reviewReport != null) {
                if (temp._reviewReport == null) return false;
                if (this._reviewReport != temp._reviewReport) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._reviewReport);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._reviewReport);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reviewReport); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reviewReport); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._reviewReport.equals(temp._reviewReport)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reviewReport);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reviewReport);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reviewReport);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reviewReport);
                    }
                }
            } else if (temp._reviewReport != null)
                return false;
            if (this._sprintTeam != null) {
                if (temp._sprintTeam == null) return false;
                if (this._sprintTeam != temp._sprintTeam) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._sprintTeam);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._sprintTeam);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprintTeam); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprintTeam); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._sprintTeam.equals(temp._sprintTeam)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprintTeam);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprintTeam);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprintTeam);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprintTeam);
                    }
                }
            } else if (temp._sprintTeam != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'dailyNotes'.
     * 
     * @return the value of field 'DailyNotes'.
     */
    public org.scrinch.model.castor.DailyNotes getDailyNotes(
    ) {
        return this._dailyNotes;
    }

    /**
     * Returns the value of field 'endDate'.
     * 
     * @return the value of field 'EndDate'.
     */
    public org.exolab.castor.types.Date getEndDate(
    ) {
        return this._endDate;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'planningReport'.
     * 
     * @return the value of field 'PlanningReport'.
     */
    public org.scrinch.model.castor.PlanningReport getPlanningReport(
    ) {
        return this._planningReport;
    }

    /**
     * Returns the value of field 'reviewReport'.
     * 
     * @return the value of field 'ReviewReport'.
     */
    public org.scrinch.model.castor.ReviewReport getReviewReport(
    ) {
        return this._reviewReport;
    }

    /**
     * Returns the value of field 'slowDowns'.
     * 
     * @return the value of field 'SlowDowns'.
     */
    public org.scrinch.model.castor.SlowDowns getSlowDowns(
    ) {
        return this._slowDowns;
    }

    /**
     * Returns the value of field 'sprintTeam'.
     * 
     * @return the value of field 'SprintTeam'.
     */
    public org.scrinch.model.castor.SprintTeam getSprintTeam(
    ) {
        return this._sprintTeam;
    }

    /**
     * Returns the value of field 'startingDate'.
     * 
     * @return the value of field 'StartingDate'.
     */
    public org.exolab.castor.types.Date getStartingDate(
    ) {
        return this._startingDate;
    }

    /**
     * Returns the value of field 'team'. The field 'team' has the
     * following description: deprecated : use sprint-team instead
     * 
     * @return the value of field 'Team'.
     */
    public org.scrinch.model.castor.Team getTeam(
    ) {
        return this._team;
    }

    /**
     * Returns the value of field 'todo'.
     * 
     * @return the value of field 'Todo'.
     */
    public org.scrinch.model.castor.Todo getTodo(
    ) {
        return this._todo;
    }

    /**
     * Returns the value of field 'velocity'.
     * 
     * @return the value of field 'Velocity'.
     */
    public double getVelocity(
    ) {
        return this._velocity;
    }

    /**
     * Method hasVelocity.
     * 
     * @return true if at least one Velocity has been added
     */
    public boolean hasVelocity(
    ) {
        return this._has_velocity;
    }

    /**
     * Overrides the java.lang.Object.hashCode method.
     * <p>
     * The following steps came from <b>Effective Java Programming
     * Language Guide</b> by Joshua Bloch, Chapter 3
     * 
     * @return a hash code value for the object.
     */
    public int hashCode(
    ) {
        int result = 17;

        long tmp;
        if (_name != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_name)) {
           result = 37 * result + _name.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_name);
        }
        tmp = java.lang.Double.doubleToLongBits(_velocity);
        result = 37 * result + (int)(tmp^(tmp>>>32));
        if (_startingDate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_startingDate)) {
           result = 37 * result + _startingDate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_startingDate);
        }
        if (_endDate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_endDate)) {
           result = 37 * result + _endDate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_endDate);
        }
        if (_todo != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_todo)) {
           result = 37 * result + _todo.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_todo);
        }
        if (_team != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_team)) {
           result = 37 * result + _team.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_team);
        }
        if (_slowDowns != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_slowDowns)) {
           result = 37 * result + _slowDowns.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_slowDowns);
        }
        if (_planningReport != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_planningReport)) {
           result = 37 * result + _planningReport.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_planningReport);
        }
        if (_dailyNotes != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_dailyNotes)) {
           result = 37 * result + _dailyNotes.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_dailyNotes);
        }
        if (_reviewReport != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_reviewReport)) {
           result = 37 * result + _reviewReport.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_reviewReport);
        }
        if (_sprintTeam != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_sprintTeam)) {
           result = 37 * result + _sprintTeam.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_sprintTeam);
        }

        return result;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'dailyNotes'.
     * 
     * @param dailyNotes the value of field 'dailyNotes'.
     */
    public void setDailyNotes(
            final org.scrinch.model.castor.DailyNotes dailyNotes) {
        this._dailyNotes = dailyNotes;
    }

    /**
     * Sets the value of field 'endDate'.
     * 
     * @param endDate the value of field 'endDate'.
     */
    public void setEndDate(
            final org.exolab.castor.types.Date endDate) {
        this._endDate = endDate;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'planningReport'.
     * 
     * @param planningReport the value of field 'planningReport'.
     */
    public void setPlanningReport(
            final org.scrinch.model.castor.PlanningReport planningReport) {
        this._planningReport = planningReport;
    }

    /**
     * Sets the value of field 'reviewReport'.
     * 
     * @param reviewReport the value of field 'reviewReport'.
     */
    public void setReviewReport(
            final org.scrinch.model.castor.ReviewReport reviewReport) {
        this._reviewReport = reviewReport;
    }

    /**
     * Sets the value of field 'slowDowns'.
     * 
     * @param slowDowns the value of field 'slowDowns'.
     */
    public void setSlowDowns(
            final org.scrinch.model.castor.SlowDowns slowDowns) {
        this._slowDowns = slowDowns;
    }

    /**
     * Sets the value of field 'sprintTeam'.
     * 
     * @param sprintTeam the value of field 'sprintTeam'.
     */
    public void setSprintTeam(
            final org.scrinch.model.castor.SprintTeam sprintTeam) {
        this._sprintTeam = sprintTeam;
    }

    /**
     * Sets the value of field 'startingDate'.
     * 
     * @param startingDate the value of field 'startingDate'.
     */
    public void setStartingDate(
            final org.exolab.castor.types.Date startingDate) {
        this._startingDate = startingDate;
    }

    /**
     * Sets the value of field 'team'. The field 'team' has the
     * following description: deprecated : use sprint-team instead
     * 
     * @param team the value of field 'team'.
     */
    public void setTeam(
            final org.scrinch.model.castor.Team team) {
        this._team = team;
    }

    /**
     * Sets the value of field 'todo'.
     * 
     * @param todo the value of field 'todo'.
     */
    public void setTodo(
            final org.scrinch.model.castor.Todo todo) {
        this._todo = todo;
    }

    /**
     * Sets the value of field 'velocity'.
     * 
     * @param velocity the value of field 'velocity'.
     */
    public void setVelocity(
            final double velocity) {
        this._velocity = velocity;
        this._has_velocity = true;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.Sprint
     */
    public static org.scrinch.model.castor.Sprint unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Sprint) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Sprint.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
