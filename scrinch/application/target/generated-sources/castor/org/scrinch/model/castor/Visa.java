/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class Visa.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Visa implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _status.
     */
    private java.lang.String _status;

    /**
     * Field _date.
     */
    private org.exolab.castor.types.Date _date;

    /**
     * Field _memberNickname.
     */
    private java.lang.String _memberNickname;

    /**
     * Field _comment.
     */
    private java.lang.String _comment;


      //----------------/
     //- Constructors -/
    //----------------/

    public Visa() {
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

        if (obj instanceof Visa) {

            Visa temp = (Visa)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._status != null) {
                if (temp._status == null) return false;
                if (this._status != temp._status) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._status);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._status);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._status.equals(temp._status)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                    }
                }
            } else if (temp._status != null)
                return false;
            if (this._date != null) {
                if (temp._date == null) return false;
                if (this._date != temp._date) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._date);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._date);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._date); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._date); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._date.equals(temp._date)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._date);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._date);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._date);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._date);
                    }
                }
            } else if (temp._date != null)
                return false;
            if (this._memberNickname != null) {
                if (temp._memberNickname == null) return false;
                if (this._memberNickname != temp._memberNickname) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._memberNickname);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._memberNickname);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberNickname); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberNickname); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._memberNickname.equals(temp._memberNickname)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberNickname);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberNickname);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberNickname);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberNickname);
                    }
                }
            } else if (temp._memberNickname != null)
                return false;
            if (this._comment != null) {
                if (temp._comment == null) return false;
                if (this._comment != temp._comment) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._comment);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._comment);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._comment); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._comment); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._comment.equals(temp._comment)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._comment);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._comment);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._comment);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._comment);
                    }
                }
            } else if (temp._comment != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'comment'.
     * 
     * @return the value of field 'Comment'.
     */
    public java.lang.String getComment(
    ) {
        return this._comment;
    }

    /**
     * Returns the value of field 'date'.
     * 
     * @return the value of field 'Date'.
     */
    public org.exolab.castor.types.Date getDate(
    ) {
        return this._date;
    }

    /**
     * Returns the value of field 'memberNickname'.
     * 
     * @return the value of field 'MemberNickname'.
     */
    public java.lang.String getMemberNickname(
    ) {
        return this._memberNickname;
    }

    /**
     * Returns the value of field 'status'.
     * 
     * @return the value of field 'Status'.
     */
    public java.lang.String getStatus(
    ) {
        return this._status;
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
        if (_status != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_status)) {
           result = 37 * result + _status.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_status);
        }
        if (_date != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_date)) {
           result = 37 * result + _date.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_date);
        }
        if (_memberNickname != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_memberNickname)) {
           result = 37 * result + _memberNickname.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_memberNickname);
        }
        if (_comment != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_comment)) {
           result = 37 * result + _comment.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_comment);
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
     * Sets the value of field 'comment'.
     * 
     * @param comment the value of field 'comment'.
     */
    public void setComment(
            final java.lang.String comment) {
        this._comment = comment;
    }

    /**
     * Sets the value of field 'date'.
     * 
     * @param date the value of field 'date'.
     */
    public void setDate(
            final org.exolab.castor.types.Date date) {
        this._date = date;
    }

    /**
     * Sets the value of field 'memberNickname'.
     * 
     * @param memberNickname the value of field 'memberNickname'.
     */
    public void setMemberNickname(
            final java.lang.String memberNickname) {
        this._memberNickname = memberNickname;
    }

    /**
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
     */
    public void setStatus(
            final java.lang.String status) {
        this._status = status;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.Visa
     */
    public static org.scrinch.model.castor.Visa unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Visa) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Visa.class, reader);
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
