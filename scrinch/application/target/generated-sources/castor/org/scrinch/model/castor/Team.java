/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * deprecated : use sprint-team instead
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Team implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _memberLinkList.
     */
    private java.util.List<java.lang.String> _memberLinkList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Team() {
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

        if (obj instanceof Team) {

            Team temp = (Team)obj;
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
            return true;
        }
        return false;
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
     * @return the unmarshaled org.scrinch.model.castor.Team
     */
    public static org.scrinch.model.castor.Team unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Team) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Team.class, reader);
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
