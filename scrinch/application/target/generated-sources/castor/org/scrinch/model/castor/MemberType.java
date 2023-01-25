/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class MemberType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class MemberType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nickname.
     */
    private java.lang.String _nickname;

    /**
     * Field _mobilePhone.
     */
    private java.lang.String _mobilePhone;

    /**
     * Field _internalPhone.
     */
    private java.lang.String _internalPhone;

    /**
     * Field _fullname.
     */
    private java.lang.String _fullname;


      //----------------/
     //- Constructors -/
    //----------------/

    public MemberType() {
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

        if (obj instanceof MemberType) {

            MemberType temp = (MemberType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._nickname != null) {
                if (temp._nickname == null) return false;
                if (this._nickname != temp._nickname) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._nickname);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._nickname);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nickname); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nickname); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._nickname.equals(temp._nickname)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nickname);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nickname);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nickname);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nickname);
                    }
                }
            } else if (temp._nickname != null)
                return false;
            if (this._mobilePhone != null) {
                if (temp._mobilePhone == null) return false;
                if (this._mobilePhone != temp._mobilePhone) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._mobilePhone);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._mobilePhone);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobilePhone); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobilePhone); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._mobilePhone.equals(temp._mobilePhone)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobilePhone);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobilePhone);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobilePhone);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobilePhone);
                    }
                }
            } else if (temp._mobilePhone != null)
                return false;
            if (this._internalPhone != null) {
                if (temp._internalPhone == null) return false;
                if (this._internalPhone != temp._internalPhone) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._internalPhone);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._internalPhone);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._internalPhone); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._internalPhone); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._internalPhone.equals(temp._internalPhone)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._internalPhone);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._internalPhone);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._internalPhone);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._internalPhone);
                    }
                }
            } else if (temp._internalPhone != null)
                return false;
            if (this._fullname != null) {
                if (temp._fullname == null) return false;
                if (this._fullname != temp._fullname) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._fullname);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._fullname);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._fullname); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._fullname); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._fullname.equals(temp._fullname)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._fullname);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._fullname);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._fullname);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._fullname);
                    }
                }
            } else if (temp._fullname != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'fullname'.
     * 
     * @return the value of field 'Fullname'.
     */
    public java.lang.String getFullname(
    ) {
        return this._fullname;
    }

    /**
     * Returns the value of field 'internalPhone'.
     * 
     * @return the value of field 'InternalPhone'.
     */
    public java.lang.String getInternalPhone(
    ) {
        return this._internalPhone;
    }

    /**
     * Returns the value of field 'mobilePhone'.
     * 
     * @return the value of field 'MobilePhone'.
     */
    public java.lang.String getMobilePhone(
    ) {
        return this._mobilePhone;
    }

    /**
     * Returns the value of field 'nickname'.
     * 
     * @return the value of field 'Nickname'.
     */
    public java.lang.String getNickname(
    ) {
        return this._nickname;
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
        if (_nickname != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_nickname)) {
           result = 37 * result + _nickname.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_nickname);
        }
        if (_mobilePhone != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_mobilePhone)) {
           result = 37 * result + _mobilePhone.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_mobilePhone);
        }
        if (_internalPhone != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_internalPhone)) {
           result = 37 * result + _internalPhone.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_internalPhone);
        }
        if (_fullname != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_fullname)) {
           result = 37 * result + _fullname.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_fullname);
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
     * Sets the value of field 'fullname'.
     * 
     * @param fullname the value of field 'fullname'.
     */
    public void setFullname(
            final java.lang.String fullname) {
        this._fullname = fullname;
    }

    /**
     * Sets the value of field 'internalPhone'.
     * 
     * @param internalPhone the value of field 'internalPhone'.
     */
    public void setInternalPhone(
            final java.lang.String internalPhone) {
        this._internalPhone = internalPhone;
    }

    /**
     * Sets the value of field 'mobilePhone'.
     * 
     * @param mobilePhone the value of field 'mobilePhone'.
     */
    public void setMobilePhone(
            final java.lang.String mobilePhone) {
        this._mobilePhone = mobilePhone;
    }

    /**
     * Sets the value of field 'nickname'.
     * 
     * @param nickname the value of field 'nickname'.
     */
    public void setNickname(
            final java.lang.String nickname) {
        this._nickname = nickname;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.MemberType
     */
    public static org.scrinch.model.castor.MemberType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.MemberType) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.MemberType.class, reader);
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
