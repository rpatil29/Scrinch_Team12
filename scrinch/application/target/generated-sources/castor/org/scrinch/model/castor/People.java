/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * deprecated : use members instead
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class People implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _memberList.
     */
    private java.util.List<org.scrinch.model.castor.Member> _memberList;


      //----------------/
     //- Constructors -/
    //----------------/

    public People() {
        super();
        this._memberList = new java.util.ArrayList<org.scrinch.model.castor.Member>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vMember
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMember(
            final org.scrinch.model.castor.Member vMember)
    throws java.lang.IndexOutOfBoundsException {
        this._memberList.add(vMember);
    }

    /**
     * 
     * 
     * @param index
     * @param vMember
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMember(
            final int index,
            final org.scrinch.model.castor.Member vMember)
    throws java.lang.IndexOutOfBoundsException {
        this._memberList.add(index, vMember);
    }

    /**
     * Method enumerateMember.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.Member> enumerateMember(
    ) {
        return java.util.Collections.enumeration(this._memberList);
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

        if (obj instanceof People) {

            People temp = (People)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._memberList != null) {
                if (temp._memberList == null) return false;
                if (this._memberList != temp._memberList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._memberList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._memberList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._memberList.equals(temp._memberList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._memberList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._memberList);
                    }
                }
            } else if (temp._memberList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getMember.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.Member at
     * the given index
     */
    public org.scrinch.model.castor.Member getMember(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._memberList.size()) {
            throw new IndexOutOfBoundsException("getMember: Index value '" + index + "' not in range [0.." + (this._memberList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.Member) _memberList.get(index);
    }

    /**
     * Method getMember.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.Member[] getMember(
    ) {
        org.scrinch.model.castor.Member[] array = new org.scrinch.model.castor.Member[0];
        return (org.scrinch.model.castor.Member[]) this._memberList.toArray(array);
    }

    /**
     * Method getMemberCount.
     * 
     * @return the size of this collection
     */
    public int getMemberCount(
    ) {
        return this._memberList.size();
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
        if (_memberList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_memberList)) {
           result = 37 * result + _memberList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_memberList);
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
     * Method iterateMember.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.Member> iterateMember(
    ) {
        return this._memberList.iterator();
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
    public void removeAllMember(
    ) {
        this._memberList.clear();
    }

    /**
     * Method removeMember.
     * 
     * @param vMember
     * @return true if the object was removed from the collection.
     */
    public boolean removeMember(
            final org.scrinch.model.castor.Member vMember) {
        boolean removed = _memberList.remove(vMember);
        return removed;
    }

    /**
     * Method removeMemberAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.Member removeMemberAt(
            final int index) {
        java.lang.Object obj = this._memberList.remove(index);
        return (org.scrinch.model.castor.Member) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vMember
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setMember(
            final int index,
            final org.scrinch.model.castor.Member vMember)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._memberList.size()) {
            throw new IndexOutOfBoundsException("setMember: Index value '" + index + "' not in range [0.." + (this._memberList.size() - 1) + "]");
        }

        this._memberList.set(index, vMember);
    }

    /**
     * 
     * 
     * @param vMemberArray
     */
    public void setMember(
            final org.scrinch.model.castor.Member[] vMemberArray) {
        //-- copy array
        _memberList.clear();

        for (int i = 0; i < vMemberArray.length; i++) {
                this._memberList.add(vMemberArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.People
     */
    public static org.scrinch.model.castor.People unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.People) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.People.class, reader);
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
