/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class Visas.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Visas implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _visaList.
     */
    private java.util.List<org.scrinch.model.castor.Visa> _visaList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Visas() {
        super();
        this._visaList = new java.util.ArrayList<org.scrinch.model.castor.Visa>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vVisa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addVisa(
            final org.scrinch.model.castor.Visa vVisa)
    throws java.lang.IndexOutOfBoundsException {
        this._visaList.add(vVisa);
    }

    /**
     * 
     * 
     * @param index
     * @param vVisa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addVisa(
            final int index,
            final org.scrinch.model.castor.Visa vVisa)
    throws java.lang.IndexOutOfBoundsException {
        this._visaList.add(index, vVisa);
    }

    /**
     * Method enumerateVisa.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.Visa> enumerateVisa(
    ) {
        return java.util.Collections.enumeration(this._visaList);
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

        if (obj instanceof Visas) {

            Visas temp = (Visas)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._visaList != null) {
                if (temp._visaList == null) return false;
                if (this._visaList != temp._visaList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._visaList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._visaList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._visaList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._visaList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._visaList.equals(temp._visaList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._visaList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._visaList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._visaList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._visaList);
                    }
                }
            } else if (temp._visaList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getVisa.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.Visa at
     * the given index
     */
    public org.scrinch.model.castor.Visa getVisa(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._visaList.size()) {
            throw new IndexOutOfBoundsException("getVisa: Index value '" + index + "' not in range [0.." + (this._visaList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.Visa) _visaList.get(index);
    }

    /**
     * Method getVisa.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.Visa[] getVisa(
    ) {
        org.scrinch.model.castor.Visa[] array = new org.scrinch.model.castor.Visa[0];
        return (org.scrinch.model.castor.Visa[]) this._visaList.toArray(array);
    }

    /**
     * Method getVisaCount.
     * 
     * @return the size of this collection
     */
    public int getVisaCount(
    ) {
        return this._visaList.size();
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
        if (_visaList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_visaList)) {
           result = 37 * result + _visaList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_visaList);
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
     * Method iterateVisa.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.Visa> iterateVisa(
    ) {
        return this._visaList.iterator();
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
    public void removeAllVisa(
    ) {
        this._visaList.clear();
    }

    /**
     * Method removeVisa.
     * 
     * @param vVisa
     * @return true if the object was removed from the collection.
     */
    public boolean removeVisa(
            final org.scrinch.model.castor.Visa vVisa) {
        boolean removed = _visaList.remove(vVisa);
        return removed;
    }

    /**
     * Method removeVisaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.Visa removeVisaAt(
            final int index) {
        java.lang.Object obj = this._visaList.remove(index);
        return (org.scrinch.model.castor.Visa) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vVisa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setVisa(
            final int index,
            final org.scrinch.model.castor.Visa vVisa)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._visaList.size()) {
            throw new IndexOutOfBoundsException("setVisa: Index value '" + index + "' not in range [0.." + (this._visaList.size() - 1) + "]");
        }

        this._visaList.set(index, vVisa);
    }

    /**
     * 
     * 
     * @param vVisaArray
     */
    public void setVisa(
            final org.scrinch.model.castor.Visa[] vVisaArray) {
        //-- copy array
        _visaList.clear();

        for (int i = 0; i < vVisaArray.length; i++) {
                this._visaList.add(vVisaArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.Visas
     */
    public static org.scrinch.model.castor.Visas unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Visas) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Visas.class, reader);
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
