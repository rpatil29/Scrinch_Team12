/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class OriginTypes.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class OriginTypes implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _originTypeList.
     */
    private java.util.List<org.scrinch.model.castor.OriginType> _originTypeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public OriginTypes() {
        super();
        this._originTypeList = new java.util.ArrayList<org.scrinch.model.castor.OriginType>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vOriginType
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addOriginType(
            final org.scrinch.model.castor.OriginType vOriginType)
    throws java.lang.IndexOutOfBoundsException {
        this._originTypeList.add(vOriginType);
    }

    /**
     * 
     * 
     * @param index
     * @param vOriginType
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addOriginType(
            final int index,
            final org.scrinch.model.castor.OriginType vOriginType)
    throws java.lang.IndexOutOfBoundsException {
        this._originTypeList.add(index, vOriginType);
    }

    /**
     * Method enumerateOriginType.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.OriginType> enumerateOriginType(
    ) {
        return java.util.Collections.enumeration(this._originTypeList);
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

        if (obj instanceof OriginTypes) {

            OriginTypes temp = (OriginTypes)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._originTypeList != null) {
                if (temp._originTypeList == null) return false;
                if (this._originTypeList != temp._originTypeList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._originTypeList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._originTypeList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originTypeList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originTypeList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._originTypeList.equals(temp._originTypeList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originTypeList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originTypeList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originTypeList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originTypeList);
                    }
                }
            } else if (temp._originTypeList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getOriginType.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.OriginType
     * at the given index
     */
    public org.scrinch.model.castor.OriginType getOriginType(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._originTypeList.size()) {
            throw new IndexOutOfBoundsException("getOriginType: Index value '" + index + "' not in range [0.." + (this._originTypeList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.OriginType) _originTypeList.get(index);
    }

    /**
     * Method getOriginType.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.OriginType[] getOriginType(
    ) {
        org.scrinch.model.castor.OriginType[] array = new org.scrinch.model.castor.OriginType[0];
        return (org.scrinch.model.castor.OriginType[]) this._originTypeList.toArray(array);
    }

    /**
     * Method getOriginTypeCount.
     * 
     * @return the size of this collection
     */
    public int getOriginTypeCount(
    ) {
        return this._originTypeList.size();
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
        if (_originTypeList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_originTypeList)) {
           result = 37 * result + _originTypeList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_originTypeList);
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
     * Method iterateOriginType.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.OriginType> iterateOriginType(
    ) {
        return this._originTypeList.iterator();
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
    public void removeAllOriginType(
    ) {
        this._originTypeList.clear();
    }

    /**
     * Method removeOriginType.
     * 
     * @param vOriginType
     * @return true if the object was removed from the collection.
     */
    public boolean removeOriginType(
            final org.scrinch.model.castor.OriginType vOriginType) {
        boolean removed = _originTypeList.remove(vOriginType);
        return removed;
    }

    /**
     * Method removeOriginTypeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.OriginType removeOriginTypeAt(
            final int index) {
        java.lang.Object obj = this._originTypeList.remove(index);
        return (org.scrinch.model.castor.OriginType) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vOriginType
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setOriginType(
            final int index,
            final org.scrinch.model.castor.OriginType vOriginType)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._originTypeList.size()) {
            throw new IndexOutOfBoundsException("setOriginType: Index value '" + index + "' not in range [0.." + (this._originTypeList.size() - 1) + "]");
        }

        this._originTypeList.set(index, vOriginType);
    }

    /**
     * 
     * 
     * @param vOriginTypeArray
     */
    public void setOriginType(
            final org.scrinch.model.castor.OriginType[] vOriginTypeArray) {
        //-- copy array
        _originTypeList.clear();

        for (int i = 0; i < vOriginTypeArray.length; i++) {
                this._originTypeList.add(vOriginTypeArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.OriginTypes
     */
    public static org.scrinch.model.castor.OriginTypes unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.OriginTypes) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.OriginTypes.class, reader);
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
