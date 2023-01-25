/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class Sprints.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Sprints implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _durationInWeeks.
     */
    private int _durationInWeeks;

    /**
     * keeps track of state for field: _durationInWeeks
     */
    private boolean _has_durationInWeeks;

    /**
     * Field _sprintList.
     */
    private java.util.List<org.scrinch.model.castor.Sprint> _sprintList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Sprints() {
        super();
        this._sprintList = new java.util.ArrayList<org.scrinch.model.castor.Sprint>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSprint
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSprint(
            final org.scrinch.model.castor.Sprint vSprint)
    throws java.lang.IndexOutOfBoundsException {
        this._sprintList.add(vSprint);
    }

    /**
     * 
     * 
     * @param index
     * @param vSprint
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSprint(
            final int index,
            final org.scrinch.model.castor.Sprint vSprint)
    throws java.lang.IndexOutOfBoundsException {
        this._sprintList.add(index, vSprint);
    }

    /**
     */
    public void deleteDurationInWeeks(
    ) {
        this._has_durationInWeeks= false;
    }

    /**
     * Method enumerateSprint.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.Sprint> enumerateSprint(
    ) {
        return java.util.Collections.enumeration(this._sprintList);
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

        if (obj instanceof Sprints) {

            Sprints temp = (Sprints)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._durationInWeeks != temp._durationInWeeks)
                return false;
            if (this._has_durationInWeeks != temp._has_durationInWeeks)
                return false;
            if (this._sprintList != null) {
                if (temp._sprintList == null) return false;
                if (this._sprintList != temp._sprintList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._sprintList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._sprintList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprintList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprintList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._sprintList.equals(temp._sprintList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprintList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprintList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sprintList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sprintList);
                    }
                }
            } else if (temp._sprintList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'durationInWeeks'.
     * 
     * @return the value of field 'DurationInWeeks'.
     */
    public int getDurationInWeeks(
    ) {
        return this._durationInWeeks;
    }

    /**
     * Method getSprint.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.Sprint at
     * the given index
     */
    public org.scrinch.model.castor.Sprint getSprint(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._sprintList.size()) {
            throw new IndexOutOfBoundsException("getSprint: Index value '" + index + "' not in range [0.." + (this._sprintList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.Sprint) _sprintList.get(index);
    }

    /**
     * Method getSprint.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.Sprint[] getSprint(
    ) {
        org.scrinch.model.castor.Sprint[] array = new org.scrinch.model.castor.Sprint[0];
        return (org.scrinch.model.castor.Sprint[]) this._sprintList.toArray(array);
    }

    /**
     * Method getSprintCount.
     * 
     * @return the size of this collection
     */
    public int getSprintCount(
    ) {
        return this._sprintList.size();
    }

    /**
     * Method hasDurationInWeeks.
     * 
     * @return true if at least one DurationInWeeks has been added
     */
    public boolean hasDurationInWeeks(
    ) {
        return this._has_durationInWeeks;
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
        result = 37 * result + _durationInWeeks;
        if (_sprintList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_sprintList)) {
           result = 37 * result + _sprintList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_sprintList);
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
     * Method iterateSprint.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.Sprint> iterateSprint(
    ) {
        return this._sprintList.iterator();
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
     */
    public void removeAllSprint(
    ) {
        this._sprintList.clear();
    }

    /**
     * Method removeSprint.
     * 
     * @param vSprint
     * @return true if the object was removed from the collection.
     */
    public boolean removeSprint(
            final org.scrinch.model.castor.Sprint vSprint) {
        boolean removed = _sprintList.remove(vSprint);
        return removed;
    }

    /**
     * Method removeSprintAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.Sprint removeSprintAt(
            final int index) {
        java.lang.Object obj = this._sprintList.remove(index);
        return (org.scrinch.model.castor.Sprint) obj;
    }

    /**
     * Sets the value of field 'durationInWeeks'.
     * 
     * @param durationInWeeks the value of field 'durationInWeeks'.
     */
    public void setDurationInWeeks(
            final int durationInWeeks) {
        this._durationInWeeks = durationInWeeks;
        this._has_durationInWeeks = true;
    }

    /**
     * 
     * 
     * @param index
     * @param vSprint
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSprint(
            final int index,
            final org.scrinch.model.castor.Sprint vSprint)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._sprintList.size()) {
            throw new IndexOutOfBoundsException("setSprint: Index value '" + index + "' not in range [0.." + (this._sprintList.size() - 1) + "]");
        }

        this._sprintList.set(index, vSprint);
    }

    /**
     * 
     * 
     * @param vSprintArray
     */
    public void setSprint(
            final org.scrinch.model.castor.Sprint[] vSprintArray) {
        //-- copy array
        _sprintList.clear();

        for (int i = 0; i < vSprintArray.length; i++) {
                this._sprintList.add(vSprintArray[i]);
        }
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.Sprints
     */
    public static org.scrinch.model.castor.Sprints unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Sprints) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Sprints.class, reader);
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
