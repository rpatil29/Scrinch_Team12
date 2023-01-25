/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Comment describing your root element
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ScrinchEnv implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _backlogs.
     */
    private org.scrinch.model.castor.Backlogs _backlogs;

    /**
     * Field _sprints.
     */
    private org.scrinch.model.castor.Sprints _sprints;

    /**
     * Field _items.
     */
    private org.scrinch.model.castor.Items _items;

    /**
     * deprecated : use members instead
     */
    private org.scrinch.model.castor.People _people;

    /**
     * Field _targets.
     */
    private org.scrinch.model.castor.Targets _targets;

    /**
     * Field _members.
     */
    private org.scrinch.model.castor.Members _members;

    /**
     * Field _workTypes.
     */
    private org.scrinch.model.castor.WorkTypes _workTypes;

    /**
     * Field _originTypes.
     */
    private org.scrinch.model.castor.OriginTypes _originTypes;

    /**
     * Field _preferences.
     */
    private org.scrinch.model.castor.Preferences _preferences;


      //----------------/
     //- Constructors -/
    //----------------/

    public ScrinchEnv() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

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

        if (obj instanceof ScrinchEnv) {

            ScrinchEnv temp = (ScrinchEnv)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._backlogs != null) {
                if (temp._backlogs == null) return false;
                if (this._backlogs != temp._backlogs) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._backlogs);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._backlogs);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._backlogs); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._backlogs); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._backlogs.equals(temp._backlogs)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._backlogs);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._backlogs);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._backlogs);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._backlogs);
                    }
                }
            } else if (temp._backlogs != null)
                return false;
            if (this._sprints != null) {
                if (temp._sprints == null) return false;
                if (this._sprints != temp._sprints) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._sprints);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._sprints);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprints); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprints); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._sprints.equals(temp._sprints)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprints);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprints);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprints);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprints);
                    }
                }
            } else if (temp._sprints != null)
                return false;
            if (this._items != null) {
                if (temp._items == null) return false;
                if (this._items != temp._items) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._items);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._items);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._items); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._items); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._items.equals(temp._items)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._items);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._items);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._items);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._items);
                    }
                }
            } else if (temp._items != null)
                return false;
            if (this._people != null) {
                if (temp._people == null) return false;
                if (this._people != temp._people) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._people);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._people);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._people); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._people); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._people.equals(temp._people)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._people);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._people);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._people);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._people);
                    }
                }
            } else if (temp._people != null)
                return false;
            if (this._targets != null) {
                if (temp._targets == null) return false;
                if (this._targets != temp._targets) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._targets);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._targets);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._targets); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._targets); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._targets.equals(temp._targets)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._targets);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._targets);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._targets);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._targets);
                    }
                }
            } else if (temp._targets != null)
                return false;
            if (this._members != null) {
                if (temp._members == null) return false;
                if (this._members != temp._members) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._members);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._members);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._members); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._members); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._members.equals(temp._members)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._members);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._members);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._members);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._members);
                    }
                }
            } else if (temp._members != null)
                return false;
            if (this._workTypes != null) {
                if (temp._workTypes == null) return false;
                if (this._workTypes != temp._workTypes) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._workTypes);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._workTypes);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workTypes); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workTypes); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._workTypes.equals(temp._workTypes)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workTypes);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workTypes);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workTypes);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workTypes);
                    }
                }
            } else if (temp._workTypes != null)
                return false;
            if (this._originTypes != null) {
                if (temp._originTypes == null) return false;
                if (this._originTypes != temp._originTypes) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._originTypes);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._originTypes);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originTypes); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originTypes); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._originTypes.equals(temp._originTypes)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originTypes);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originTypes);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originTypes);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originTypes);
                    }
                }
            } else if (temp._originTypes != null)
                return false;
            if (this._preferences != null) {
                if (temp._preferences == null) return false;
                if (this._preferences != temp._preferences) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._preferences);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._preferences);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._preferences); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._preferences); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._preferences.equals(temp._preferences)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._preferences);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._preferences);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._preferences);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._preferences);
                    }
                }
            } else if (temp._preferences != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'backlogs'.
     * 
     * @return the value of field 'Backlogs'.
     */
    public org.scrinch.model.castor.Backlogs getBacklogs(
    ) {
        return this._backlogs;
    }

    /**
     * Returns the value of field 'items'.
     * 
     * @return the value of field 'Items'.
     */
    public org.scrinch.model.castor.Items getItems(
    ) {
        return this._items;
    }

    /**
     * Returns the value of field 'members'.
     * 
     * @return the value of field 'Members'.
     */
    public org.scrinch.model.castor.Members getMembers(
    ) {
        return this._members;
    }

    /**
     * Returns the value of field 'originTypes'.
     * 
     * @return the value of field 'OriginTypes'.
     */
    public org.scrinch.model.castor.OriginTypes getOriginTypes(
    ) {
        return this._originTypes;
    }

    /**
     * Returns the value of field 'people'. The field 'people' has
     * the following description: deprecated : use members instead
     * 
     * @return the value of field 'People'.
     */
    public org.scrinch.model.castor.People getPeople(
    ) {
        return this._people;
    }

    /**
     * Returns the value of field 'preferences'.
     * 
     * @return the value of field 'Preferences'.
     */
    public org.scrinch.model.castor.Preferences getPreferences(
    ) {
        return this._preferences;
    }

    /**
     * Returns the value of field 'sprints'.
     * 
     * @return the value of field 'Sprints'.
     */
    public org.scrinch.model.castor.Sprints getSprints(
    ) {
        return this._sprints;
    }

    /**
     * Returns the value of field 'targets'.
     * 
     * @return the value of field 'Targets'.
     */
    public org.scrinch.model.castor.Targets getTargets(
    ) {
        return this._targets;
    }

    /**
     * Returns the value of field 'workTypes'.
     * 
     * @return the value of field 'WorkTypes'.
     */
    public org.scrinch.model.castor.WorkTypes getWorkTypes(
    ) {
        return this._workTypes;
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
        if (_backlogs != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_backlogs)) {
           result = 37 * result + _backlogs.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_backlogs);
        }
        if (_sprints != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_sprints)) {
           result = 37 * result + _sprints.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_sprints);
        }
        if (_items != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_items)) {
           result = 37 * result + _items.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_items);
        }
        if (_people != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_people)) {
           result = 37 * result + _people.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_people);
        }
        if (_targets != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_targets)) {
           result = 37 * result + _targets.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_targets);
        }
        if (_members != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_members)) {
           result = 37 * result + _members.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_members);
        }
        if (_workTypes != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_workTypes)) {
           result = 37 * result + _workTypes.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_workTypes);
        }
        if (_originTypes != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_originTypes)) {
           result = 37 * result + _originTypes.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_originTypes);
        }
        if (_preferences != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_preferences)) {
           result = 37 * result + _preferences.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_preferences);
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
     * Sets the value of field 'backlogs'.
     * 
     * @param backlogs the value of field 'backlogs'.
     */
    public void setBacklogs(
            final org.scrinch.model.castor.Backlogs backlogs) {
        this._backlogs = backlogs;
    }

    /**
     * Sets the value of field 'items'.
     * 
     * @param items the value of field 'items'.
     */
    public void setItems(
            final org.scrinch.model.castor.Items items) {
        this._items = items;
    }

    /**
     * Sets the value of field 'members'.
     * 
     * @param members the value of field 'members'.
     */
    public void setMembers(
            final org.scrinch.model.castor.Members members) {
        this._members = members;
    }

    /**
     * Sets the value of field 'originTypes'.
     * 
     * @param originTypes the value of field 'originTypes'.
     */
    public void setOriginTypes(
            final org.scrinch.model.castor.OriginTypes originTypes) {
        this._originTypes = originTypes;
    }

    /**
     * Sets the value of field 'people'. The field 'people' has the
     * following description: deprecated : use members instead
     * 
     * @param people the value of field 'people'.
     */
    public void setPeople(
            final org.scrinch.model.castor.People people) {
        this._people = people;
    }

    /**
     * Sets the value of field 'preferences'.
     * 
     * @param preferences the value of field 'preferences'.
     */
    public void setPreferences(
            final org.scrinch.model.castor.Preferences preferences) {
        this._preferences = preferences;
    }

    /**
     * Sets the value of field 'sprints'.
     * 
     * @param sprints the value of field 'sprints'.
     */
    public void setSprints(
            final org.scrinch.model.castor.Sprints sprints) {
        this._sprints = sprints;
    }

    /**
     * Sets the value of field 'targets'.
     * 
     * @param targets the value of field 'targets'.
     */
    public void setTargets(
            final org.scrinch.model.castor.Targets targets) {
        this._targets = targets;
    }

    /**
     * Sets the value of field 'workTypes'.
     * 
     * @param workTypes the value of field 'workTypes'.
     */
    public void setWorkTypes(
            final org.scrinch.model.castor.WorkTypes workTypes) {
        this._workTypes = workTypes;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.ScrinchEnv
     */
    public static org.scrinch.model.castor.ScrinchEnv unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.ScrinchEnv) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.ScrinchEnv.class, reader);
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
