/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class WorkTypes.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class WorkTypes implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _workTypeList.
     */
    private java.util.List<org.scrinch.model.castor.WorkType> _workTypeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public WorkTypes() {
        super();
        this._workTypeList = new java.util.ArrayList<org.scrinch.model.castor.WorkType>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vWorkType
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addWorkType(
            final org.scrinch.model.castor.WorkType vWorkType)
    throws java.lang.IndexOutOfBoundsException {
        // check for the maximum size
        if (this._workTypeList.size() >= 10) {
            throw new IndexOutOfBoundsException("addWorkType has a maximum of 10");
        }

        this._workTypeList.add(vWorkType);
    }

    /**
     * 
     * 
     * @param index
     * @param vWorkType
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addWorkType(
            final int index,
            final org.scrinch.model.castor.WorkType vWorkType)
    throws java.lang.IndexOutOfBoundsException {
        // check for the maximum size
        if (this._workTypeList.size() >= 10) {
            throw new IndexOutOfBoundsException("addWorkType has a maximum of 10");
        }

        this._workTypeList.add(index, vWorkType);
    }

    /**
     * Method enumerateWorkType.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.WorkType> enumerateWorkType(
    ) {
        return java.util.Collections.enumeration(this._workTypeList);
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

        if (obj instanceof WorkTypes) {

            WorkTypes temp = (WorkTypes)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._workTypeList != null) {
                if (temp._workTypeList == null) return false;
                if (this._workTypeList != temp._workTypeList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._workTypeList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._workTypeList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workTypeList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workTypeList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._workTypeList.equals(temp._workTypeList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workTypeList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workTypeList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workTypeList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workTypeList);
                    }
                }
            } else if (temp._workTypeList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getWorkType.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.WorkType
     * at the given index
     */
    public org.scrinch.model.castor.WorkType getWorkType(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._workTypeList.size()) {
            throw new IndexOutOfBoundsException("getWorkType: Index value '" + index + "' not in range [0.." + (this._workTypeList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.WorkType) _workTypeList.get(index);
    }

    /**
     * Method getWorkType.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.WorkType[] getWorkType(
    ) {
        org.scrinch.model.castor.WorkType[] array = new org.scrinch.model.castor.WorkType[0];
        return (org.scrinch.model.castor.WorkType[]) this._workTypeList.toArray(array);
    }

    /**
     * Method getWorkTypeCount.
     * 
     * @return the size of this collection
     */
    public int getWorkTypeCount(
    ) {
        return this._workTypeList.size();
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
        if (_workTypeList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_workTypeList)) {
           result = 37 * result + _workTypeList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_workTypeList);
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
     * Method iterateWorkType.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.WorkType> iterateWorkType(
    ) {
        return this._workTypeList.iterator();
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
    public void removeAllWorkType(
    ) {
        this._workTypeList.clear();
    }

    /**
     * Method removeWorkType.
     * 
     * @param vWorkType
     * @return true if the object was removed from the collection.
     */
    public boolean removeWorkType(
            final org.scrinch.model.castor.WorkType vWorkType) {
        boolean removed = _workTypeList.remove(vWorkType);
        return removed;
    }

    /**
     * Method removeWorkTypeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.WorkType removeWorkTypeAt(
            final int index) {
        java.lang.Object obj = this._workTypeList.remove(index);
        return (org.scrinch.model.castor.WorkType) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vWorkType
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setWorkType(
            final int index,
            final org.scrinch.model.castor.WorkType vWorkType)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._workTypeList.size()) {
            throw new IndexOutOfBoundsException("setWorkType: Index value '" + index + "' not in range [0.." + (this._workTypeList.size() - 1) + "]");
        }

        this._workTypeList.set(index, vWorkType);
    }

    /**
     * 
     * 
     * @param vWorkTypeArray
     */
    public void setWorkType(
            final org.scrinch.model.castor.WorkType[] vWorkTypeArray) {
        //-- copy array
        _workTypeList.clear();

        for (int i = 0; i < vWorkTypeArray.length; i++) {
                this._workTypeList.add(vWorkTypeArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.WorkTypes
     */
    public static org.scrinch.model.castor.WorkTypes unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.WorkTypes) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.WorkTypes.class, reader);
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
