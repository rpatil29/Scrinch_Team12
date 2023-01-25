/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class SlowDown.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SlowDown implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _memberLinkList.
     */
    private java.util.List<java.lang.String> _memberLinkList;

    /**
     * Field _description.
     */
    private java.lang.String _description;

    /**
     * Field _cost.
     */
    private double _cost;

    /**
     * keeps track of state for field: _cost
     */
    private boolean _has_cost;


      //----------------/
     //- Constructors -/
    //----------------/

    public SlowDown() {
        super();
        this._memberLinkList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vMemberLink
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMemberLink(
            final java.lang.String vMemberLink)
    throws java.lang.IndexOutOfBoundsException {
        this._memberLinkList.add(vMemberLink);
    }

    /**
     * 
     * 
     * @param index
     * @param vMemberLink
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMemberLink(
            final int index,
            final java.lang.String vMemberLink)
    throws java.lang.IndexOutOfBoundsException {
        this._memberLinkList.add(index, vMemberLink);
    }

    /**
     */
    public void deleteCost(
    ) {
        this._has_cost= false;
    }

    /**
     * Method enumerateMemberLink.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateMemberLink(
    ) {
        return java.util.Collections.enumeration(this._memberLinkList);
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

        if (obj instanceof SlowDown) {

            SlowDown temp = (SlowDown)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._memberLinkList != null) {
                if (temp._memberLinkList == null) return false;
                if (this._memberLinkList != temp._memberLinkList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._memberLinkList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._memberLinkList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberLinkList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberLinkList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._memberLinkList.equals(temp._memberLinkList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberLinkList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberLinkList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberLinkList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberLinkList);
                    }
                }
            } else if (temp._memberLinkList != null)
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
            if (this._cost != temp._cost)
                return false;
            if (this._has_cost != temp._has_cost)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'cost'.
     * 
     * @return the value of field 'Cost'.
     */
    public double getCost(
    ) {
        return this._cost;
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
     * Method getMemberLink.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getMemberLink(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._memberLinkList.size()) {
            throw new IndexOutOfBoundsException("getMemberLink: Index value '" + index + "' not in range [0.." + (this._memberLinkList.size() - 1) + "]");
        }

        return (java.lang.String) _memberLinkList.get(index);
    }

    /**
     * Method getMemberLink.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getMemberLink(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._memberLinkList.toArray(array);
    }

    /**
     * Method getMemberLinkCount.
     * 
     * @return the size of this collection
     */
    public int getMemberLinkCount(
    ) {
        return this._memberLinkList.size();
    }

    /**
     * Method hasCost.
     * 
     * @return true if at least one Cost has been added
     */
    public boolean hasCost(
    ) {
        return this._has_cost;
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
        if (_memberLinkList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_memberLinkList)) {
           result = 37 * result + _memberLinkList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_memberLinkList);
        }
        if (_description != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_description)) {
           result = 37 * result + _description.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_description);
        }
        tmp = java.lang.Double.doubleToLongBits(_cost);
        result = 37 * result + (int)(tmp^(tmp>>>32));

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
     * Method iterateMemberLink.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateMemberLink(
    ) {
        return this._memberLinkList.iterator();
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
    public void removeAllMemberLink(
    ) {
        this._memberLinkList.clear();
    }

    /**
     * Method removeMemberLink.
     * 
     * @param vMemberLink
     * @return true if the object was removed from the collection.
     */
    public boolean removeMemberLink(
            final java.lang.String vMemberLink) {
        boolean removed = _memberLinkList.remove(vMemberLink);
        return removed;
    }

    /**
     * Method removeMemberLinkAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeMemberLinkAt(
            final int index) {
        java.lang.Object obj = this._memberLinkList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Sets the value of field 'cost'.
     * 
     * @param cost the value of field 'cost'.
     */
    public void setCost(
            final double cost) {
        this._cost = cost;
        this._has_cost = true;
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
     * 
     * 
     * @param index
     * @param vMemberLink
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setMemberLink(
            final int index,
            final java.lang.String vMemberLink)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._memberLinkList.size()) {
            throw new IndexOutOfBoundsException("setMemberLink: Index value '" + index + "' not in range [0.." + (this._memberLinkList.size() - 1) + "]");
        }

        this._memberLinkList.set(index, vMemberLink);
    }

    /**
     * 
     * 
     * @param vMemberLinkArray
     */
    public void setMemberLink(
            final java.lang.String[] vMemberLinkArray) {
        //-- copy array
        _memberLinkList.clear();

        for (int i = 0; i < vMemberLinkArray.length; i++) {
                this._memberLinkList.add(vMemberLinkArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.SlowDown
     */
    public static org.scrinch.model.castor.SlowDown unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.SlowDown) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.SlowDown.class, reader);
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
