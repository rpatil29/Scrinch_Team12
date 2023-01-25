/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class TargetType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class TargetType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _label.
     */
    private java.lang.String _label;

    /**
     * Field _description.
     */
    private java.lang.String _description;

    /**
     * Deprecated : replaced by deadline
     */
    private org.exolab.castor.types.Date _cfoDate;

    /**
     * Field _deadline.
     */
    private org.exolab.castor.types.Date _deadline;


      //----------------/
     //- Constructors -/
    //----------------/

    public TargetType() {
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

        if (obj instanceof TargetType) {

            TargetType temp = (TargetType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._label != null) {
                if (temp._label == null) return false;
                if (this._label != temp._label) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._label);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._label);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._label); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._label); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._label.equals(temp._label)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._label);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._label);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._label);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._label);
                    }
                }
            } else if (temp._label != null)
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
            if (this._cfoDate != null) {
                if (temp._cfoDate == null) return false;
                if (this._cfoDate != temp._cfoDate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._cfoDate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._cfoDate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cfoDate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cfoDate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._cfoDate.equals(temp._cfoDate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cfoDate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cfoDate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cfoDate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cfoDate);
                    }
                }
            } else if (temp._cfoDate != null)
                return false;
            if (this._deadline != null) {
                if (temp._deadline == null) return false;
                if (this._deadline != temp._deadline) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._deadline);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._deadline);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._deadline); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._deadline); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._deadline.equals(temp._deadline)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._deadline);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._deadline);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._deadline);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._deadline);
                    }
                }
            } else if (temp._deadline != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'cfoDate'. The field 'cfoDate'
     * has the following description: Deprecated : replaced by
     * deadline
     * 
     * @return the value of field 'CfoDate'.
     */
    public org.exolab.castor.types.Date getCfoDate(
    ) {
        return this._cfoDate;
    }

    /**
     * Returns the value of field 'deadline'.
     * 
     * @return the value of field 'Deadline'.
     */
    public org.exolab.castor.types.Date getDeadline(
    ) {
        return this._deadline;
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
     * Returns the value of field 'label'.
     * 
     * @return the value of field 'Label'.
     */
    public java.lang.String getLabel(
    ) {
        return this._label;
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
        if (_label != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_label)) {
           result = 37 * result + _label.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_label);
        }
        if (_description != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_description)) {
           result = 37 * result + _description.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_description);
        }
        if (_cfoDate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_cfoDate)) {
           result = 37 * result + _cfoDate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_cfoDate);
        }
        if (_deadline != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_deadline)) {
           result = 37 * result + _deadline.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_deadline);
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
     * Sets the value of field 'cfoDate'. The field 'cfoDate' has
     * the following description: Deprecated : replaced by deadline
     * 
     * @param cfoDate the value of field 'cfoDate'.
     */
    public void setCfoDate(
            final org.exolab.castor.types.Date cfoDate) {
        this._cfoDate = cfoDate;
    }

    /**
     * Sets the value of field 'deadline'.
     * 
     * @param deadline the value of field 'deadline'.
     */
    public void setDeadline(
            final org.exolab.castor.types.Date deadline) {
        this._deadline = deadline;
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
     * Sets the value of field 'label'.
     * 
     * @param label the value of field 'label'.
     */
    public void setLabel(
            final java.lang.String label) {
        this._label = label;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.TargetType
     */
    public static org.scrinch.model.castor.TargetType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.TargetType) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.TargetType.class, reader);
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
