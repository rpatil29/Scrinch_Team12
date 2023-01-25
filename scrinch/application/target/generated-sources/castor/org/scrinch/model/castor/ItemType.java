/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class ItemType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ItemType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * Field _title.
     */
    private java.lang.String _title;

    /**
     * Field _description.
     */
    private java.lang.String _description;

    /**
     * Field _requestType.
     */
    private java.lang.String _requestType;

    /**
     * Field _workType.
     */
    private java.lang.String _workType;

    /**
     * Field _businessValue.
     */
    private int _businessValue;

    /**
     * keeps track of state for field: _businessValue
     */
    private boolean _has_businessValue;

    /**
     * Field _work.
     */
    private int _work;

    /**
     * keeps track of state for field: _work
     */
    private boolean _has_work;

    /**
     * Field _target.
     */
    private java.lang.String _target;

    /**
     * Field _visas.
     */
    private org.scrinch.model.castor.Visas _visas;


      //----------------/
     //- Constructors -/
    //----------------/

    public ItemType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteBusinessValue(
    ) {
        this._has_businessValue= false;
    }

    /**
     */
    public void deleteWork(
    ) {
        this._has_work= false;
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

        if (obj instanceof ItemType) {

            ItemType temp = (ItemType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._id != null) {
                if (temp._id == null) return false;
                if (this._id != temp._id) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._id);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._id);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._id.equals(temp._id)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id);
                    }
                }
            } else if (temp._id != null)
                return false;
            if (this._title != null) {
                if (temp._title == null) return false;
                if (this._title != temp._title) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._title);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._title);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._title); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._title); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._title.equals(temp._title)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._title);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._title);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._title);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._title);
                    }
                }
            } else if (temp._title != null)
                return false;
            if (this._description != null) {
                if (temp._description == null) return false;
                if (this._description != temp._description) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._description);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._description);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._description.equals(temp._description)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description);
                    }
                }
            } else if (temp._description != null)
                return false;
            if (this._requestType != null) {
                if (temp._requestType == null) return false;
                if (this._requestType != temp._requestType) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._requestType);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._requestType);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requestType); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requestType); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._requestType.equals(temp._requestType)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requestType);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requestType);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requestType);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requestType);
                    }
                }
            } else if (temp._requestType != null)
                return false;
            if (this._workType != null) {
                if (temp._workType == null) return false;
                if (this._workType != temp._workType) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._workType);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._workType);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workType); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workType); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._workType.equals(temp._workType)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workType);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workType);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workType);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workType);
                    }
                }
            } else if (temp._workType != null)
                return false;
            if (this._businessValue != temp._businessValue)
                return false;
            if (this._has_businessValue != temp._has_businessValue)
                return false;
            if (this._work != temp._work)
                return false;
            if (this._has_work != temp._has_work)
                return false;
            if (this._target != null) {
                if (temp._target == null) return false;
                if (this._target != temp._target) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._target);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._target);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._target); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._target); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._target.equals(temp._target)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._target);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._target);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._target);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._target);
                    }
                }
            } else if (temp._target != null)
                return false;
            if (this._visas != null) {
                if (temp._visas == null) return false;
                if (this._visas != temp._visas) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._visas);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._visas);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._visas); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._visas); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._visas.equals(temp._visas)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._visas);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._visas);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._visas);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._visas);
                    }
                }
            } else if (temp._visas != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'businessValue'.
     * 
     * @return the value of field 'BusinessValue'.
     */
    public int getBusinessValue(
    ) {
        return this._businessValue;
    }

    /**
     * Returns the value of field 'description'.
     * 
     * @return the value of field 'Description'.
     */
    public java.lang.String getDescription(
    ) {
        return this._description;
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public java.lang.String getId(
    ) {
        return this._id;
    }

    /**
     * Returns the value of field 'requestType'.
     * 
     * @return the value of field 'RequestType'.
     */
    public java.lang.String getRequestType(
    ) {
        return this._requestType;
    }

    /**
     * Returns the value of field 'target'.
     * 
     * @return the value of field 'Target'.
     */
    public java.lang.String getTarget(
    ) {
        return this._target;
    }

    /**
     * Returns the value of field 'title'.
     * 
     * @return the value of field 'Title'.
     */
    public java.lang.String getTitle(
    ) {
        return this._title;
    }

    /**
     * Returns the value of field 'visas'.
     * 
     * @return the value of field 'Visas'.
     */
    public org.scrinch.model.castor.Visas getVisas(
    ) {
        return this._visas;
    }

    /**
     * Returns the value of field 'work'.
     * 
     * @return the value of field 'Work'.
     */
    public int getWork(
    ) {
        return this._work;
    }

    /**
     * Returns the value of field 'workType'.
     * 
     * @return the value of field 'WorkType'.
     */
    public java.lang.String getWorkType(
    ) {
        return this._workType;
    }

    /**
     * Method hasBusinessValue.
     * 
     * @return true if at least one BusinessValue has been added
     */
    public boolean hasBusinessValue(
    ) {
        return this._has_businessValue;
    }

    /**
     * Method hasWork.
     * 
     * @return true if at least one Work has been added
     */
    public boolean hasWork(
    ) {
        return this._has_work;
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
        if (_id != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_id)) {
           result = 37 * result + _id.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_id);
        }
        if (_title != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_title)) {
           result = 37 * result + _title.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_title);
        }
        if (_description != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_description)) {
           result = 37 * result + _description.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_description);
        }
        if (_requestType != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_requestType)) {
           result = 37 * result + _requestType.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_requestType);
        }
        if (_workType != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_workType)) {
           result = 37 * result + _workType.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_workType);
        }
        result = 37 * result + _businessValue;
        result = 37 * result + _work;
        if (_target != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_target)) {
           result = 37 * result + _target.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_target);
        }
        if (_visas != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_visas)) {
           result = 37 * result + _visas.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_visas);
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
     * Sets the value of field 'businessValue'.
     * 
     * @param businessValue the value of field 'businessValue'.
     */
    public void setBusinessValue(
            final int businessValue) {
        this._businessValue = businessValue;
        this._has_businessValue = true;
    }

    /**
     * Sets the value of field 'description'.
     * 
     * @param description the value of field 'description'.
     */
    public void setDescription(
            final java.lang.String description) {
        this._description = description;
    }

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(
            final java.lang.String id) {
        this._id = id;
    }

    /**
     * Sets the value of field 'requestType'.
     * 
     * @param requestType the value of field 'requestType'.
     */
    public void setRequestType(
            final java.lang.String requestType) {
        this._requestType = requestType;
    }

    /**
     * Sets the value of field 'target'.
     * 
     * @param target the value of field 'target'.
     */
    public void setTarget(
            final java.lang.String target) {
        this._target = target;
    }

    /**
     * Sets the value of field 'title'.
     * 
     * @param title the value of field 'title'.
     */
    public void setTitle(
            final java.lang.String title) {
        this._title = title;
    }

    /**
     * Sets the value of field 'visas'.
     * 
     * @param visas the value of field 'visas'.
     */
    public void setVisas(
            final org.scrinch.model.castor.Visas visas) {
        this._visas = visas;
    }

    /**
     * Sets the value of field 'work'.
     * 
     * @param work the value of field 'work'.
     */
    public void setWork(
            final int work) {
        this._work = work;
        this._has_work = true;
    }

    /**
     * Sets the value of field 'workType'.
     * 
     * @param workType the value of field 'workType'.
     */
    public void setWorkType(
            final java.lang.String workType) {
        this._workType = workType;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.ItemType
     */
    public static org.scrinch.model.castor.ItemType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.ItemType) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.ItemType.class, reader);
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
