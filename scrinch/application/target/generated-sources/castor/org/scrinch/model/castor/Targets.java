/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class Targets.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Targets implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _targetList.
     */
    private java.util.List<org.scrinch.model.castor.Target> _targetList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Targets() {
        super();
        this._targetList = new java.util.ArrayList<org.scrinch.model.castor.Target>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vTarget
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTarget(
            final org.scrinch.model.castor.Target vTarget)
    throws java.lang.IndexOutOfBoundsException {
        this._targetList.add(vTarget);
    }

    /**
     * 
     * 
     * @param index
     * @param vTarget
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTarget(
            final int index,
            final org.scrinch.model.castor.Target vTarget)
    throws java.lang.IndexOutOfBoundsException {
        this._targetList.add(index, vTarget);
    }

    /**
     * Method enumerateTarget.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.Target> enumerateTarget(
    ) {
        return java.util.Collections.enumeration(this._targetList);
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

        if (obj instanceof Targets) {

            Targets temp = (Targets)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._targetList != null) {
                if (temp._targetList == null) return false;
                if (this._targetList != temp._targetList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._targetList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._targetList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._targetList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._targetList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._targetList.equals(temp._targetList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._targetList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._targetList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._targetList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._targetList);
                    }
                }
            } else if (temp._targetList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getTarget.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.Target at
     * the given index
     */
    public org.scrinch.model.castor.Target getTarget(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._targetList.size()) {
            throw new IndexOutOfBoundsException("getTarget: Index value '" + index + "' not in range [0.." + (this._targetList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.Target) _targetList.get(index);
    }

    /**
     * Method getTarget.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.Target[] getTarget(
    ) {
        org.scrinch.model.castor.Target[] array = new org.scrinch.model.castor.Target[0];
        return (org.scrinch.model.castor.Target[]) this._targetList.toArray(array);
    }

    /**
     * Method getTargetCount.
     * 
     * @return the size of this collection
     */
    public int getTargetCount(
    ) {
        return this._targetList.size();
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
        if (_targetList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_targetList)) {
           result = 37 * result + _targetList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_targetList);
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
     * Method iterateTarget.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.Target> iterateTarget(
    ) {
        return this._targetList.iterator();
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
    public void removeAllTarget(
    ) {
        this._targetList.clear();
    }

    /**
     * Method removeTarget.
     * 
     * @param vTarget
     * @return true if the object was removed from the collection.
     */
    public boolean removeTarget(
            final org.scrinch.model.castor.Target vTarget) {
        boolean removed = _targetList.remove(vTarget);
        return removed;
    }

    /**
     * Method removeTargetAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.Target removeTargetAt(
            final int index) {
        java.lang.Object obj = this._targetList.remove(index);
        return (org.scrinch.model.castor.Target) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vTarget
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTarget(
            final int index,
            final org.scrinch.model.castor.Target vTarget)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._targetList.size()) {
            throw new IndexOutOfBoundsException("setTarget: Index value '" + index + "' not in range [0.." + (this._targetList.size() - 1) + "]");
        }

        this._targetList.set(index, vTarget);
    }

    /**
     * 
     * 
     * @param vTargetArray
     */
    public void setTarget(
            final org.scrinch.model.castor.Target[] vTargetArray) {
        //-- copy array
        _targetList.clear();

        for (int i = 0; i < vTargetArray.length; i++) {
                this._targetList.add(vTargetArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.Targets
     */
    public static org.scrinch.model.castor.Targets unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Targets) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Targets.class, reader);
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
