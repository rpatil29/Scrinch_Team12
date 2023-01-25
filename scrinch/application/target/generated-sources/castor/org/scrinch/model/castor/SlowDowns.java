/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class SlowDowns.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SlowDowns implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _slowDownList.
     */
    private java.util.List<org.scrinch.model.castor.SlowDown> _slowDownList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SlowDowns() {
        super();
        this._slowDownList = new java.util.ArrayList<org.scrinch.model.castor.SlowDown>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSlowDown
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSlowDown(
            final org.scrinch.model.castor.SlowDown vSlowDown)
    throws java.lang.IndexOutOfBoundsException {
        this._slowDownList.add(vSlowDown);
    }

    /**
     * 
     * 
     * @param index
     * @param vSlowDown
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSlowDown(
            final int index,
            final org.scrinch.model.castor.SlowDown vSlowDown)
    throws java.lang.IndexOutOfBoundsException {
        this._slowDownList.add(index, vSlowDown);
    }

    /**
     * Method enumerateSlowDown.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.SlowDown> enumerateSlowDown(
    ) {
        return java.util.Collections.enumeration(this._slowDownList);
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

        if (obj instanceof SlowDowns) {

            SlowDowns temp = (SlowDowns)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._slowDownList != null) {
                if (temp._slowDownList == null) return false;
                if (this._slowDownList != temp._slowDownList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._slowDownList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._slowDownList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._slowDownList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._slowDownList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._slowDownList.equals(temp._slowDownList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._slowDownList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._slowDownList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._slowDownList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._slowDownList);
                    }
                }
            } else if (temp._slowDownList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getSlowDown.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.SlowDown
     * at the given index
     */
    public org.scrinch.model.castor.SlowDown getSlowDown(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._slowDownList.size()) {
            throw new IndexOutOfBoundsException("getSlowDown: Index value '" + index + "' not in range [0.." + (this._slowDownList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.SlowDown) _slowDownList.get(index);
    }

    /**
     * Method getSlowDown.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.SlowDown[] getSlowDown(
    ) {
        org.scrinch.model.castor.SlowDown[] array = new org.scrinch.model.castor.SlowDown[0];
        return (org.scrinch.model.castor.SlowDown[]) this._slowDownList.toArray(array);
    }

    /**
     * Method getSlowDownCount.
     * 
     * @return the size of this collection
     */
    public int getSlowDownCount(
    ) {
        return this._slowDownList.size();
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
        if (_slowDownList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_slowDownList)) {
           result = 37 * result + _slowDownList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_slowDownList);
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
     * Method iterateSlowDown.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.SlowDown> iterateSlowDown(
    ) {
        return this._slowDownList.iterator();
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
    public void removeAllSlowDown(
    ) {
        this._slowDownList.clear();
    }

    /**
     * Method removeSlowDown.
     * 
     * @param vSlowDown
     * @return true if the object was removed from the collection.
     */
    public boolean removeSlowDown(
            final org.scrinch.model.castor.SlowDown vSlowDown) {
        boolean removed = _slowDownList.remove(vSlowDown);
        return removed;
    }

    /**
     * Method removeSlowDownAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.SlowDown removeSlowDownAt(
            final int index) {
        java.lang.Object obj = this._slowDownList.remove(index);
        return (org.scrinch.model.castor.SlowDown) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSlowDown
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSlowDown(
            final int index,
            final org.scrinch.model.castor.SlowDown vSlowDown)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._slowDownList.size()) {
            throw new IndexOutOfBoundsException("setSlowDown: Index value '" + index + "' not in range [0.." + (this._slowDownList.size() - 1) + "]");
        }

        this._slowDownList.set(index, vSlowDown);
    }

    /**
     * 
     * 
     * @param vSlowDownArray
     */
    public void setSlowDown(
            final org.scrinch.model.castor.SlowDown[] vSlowDownArray) {
        //-- copy array
        _slowDownList.clear();

        for (int i = 0; i < vSlowDownArray.length; i++) {
                this._slowDownList.add(vSlowDownArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.SlowDowns
     */
    public static org.scrinch.model.castor.SlowDowns unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.SlowDowns) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.SlowDowns.class, reader);
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
