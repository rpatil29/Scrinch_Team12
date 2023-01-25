/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class ItemSet.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ItemSet implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _archive.
     */
    private boolean _archive = false;

    /**
     * keeps track of state for field: _archive
     */
    private boolean _has_archive;

    /**
     * ID of the corresponding item
     */
    private java.util.List<java.lang.String> _itemLinkList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ItemSet() {
        super();
        this._itemLinkList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vItemLink
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addItemLink(
            final java.lang.String vItemLink)
    throws java.lang.IndexOutOfBoundsException {
        this._itemLinkList.add(vItemLink);
    }

    /**
     * 
     * 
     * @param index
     * @param vItemLink
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addItemLink(
            final int index,
            final java.lang.String vItemLink)
    throws java.lang.IndexOutOfBoundsException {
        this._itemLinkList.add(index, vItemLink);
    }

    /**
     */
    public void deleteArchive(
    ) {
        this._has_archive= false;
    }

    /**
     * Method enumerateItemLink.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateItemLink(
    ) {
        return java.util.Collections.enumeration(this._itemLinkList);
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

        if (obj instanceof ItemSet) {

            ItemSet temp = (ItemSet)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._name != null) {
                if (temp._name == null) return false;
                if (this._name != temp._name) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._name);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._name);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._name.equals(temp._name)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                    }
                }
            } else if (temp._name != null)
                return false;
            if (this._archive != temp._archive)
                return false;
            if (this._has_archive != temp._has_archive)
                return false;
            if (this._itemLinkList != null) {
                if (temp._itemLinkList == null) return false;
                if (this._itemLinkList != temp._itemLinkList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._itemLinkList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._itemLinkList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemLinkList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemLinkList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._itemLinkList.equals(temp._itemLinkList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemLinkList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemLinkList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemLinkList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemLinkList);
                    }
                }
            } else if (temp._itemLinkList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'archive'.
     * 
     * @return the value of field 'Archive'.
     */
    public boolean getArchive(
    ) {
        return this._archive;
    }

    /**
     * Method getItemLink.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getItemLink(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._itemLinkList.size()) {
            throw new IndexOutOfBoundsException("getItemLink: Index value '" + index + "' not in range [0.." + (this._itemLinkList.size() - 1) + "]");
        }

        return (java.lang.String) _itemLinkList.get(index);
    }

    /**
     * Method getItemLink.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getItemLink(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._itemLinkList.toArray(array);
    }

    /**
     * Method getItemLinkCount.
     * 
     * @return the size of this collection
     */
    public int getItemLinkCount(
    ) {
        return this._itemLinkList.size();
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Method hasArchive.
     * 
     * @return true if at least one Archive has been added
     */
    public boolean hasArchive(
    ) {
        return this._has_archive;
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
        if (_name != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_name)) {
           result = 37 * result + _name.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_name);
        }
        result = 37 * result + (_archive?0:1);
        if (_itemLinkList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_itemLinkList)) {
           result = 37 * result + _itemLinkList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_itemLinkList);
        }

        return result;
    }

    /**
     * Returns the value of field 'archive'.
     * 
     * @return the value of field 'Archive'.
     */
    public boolean isArchive(
    ) {
        return this._archive;
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
     * Method iterateItemLink.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateItemLink(
    ) {
        return this._itemLinkList.iterator();
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
    public void removeAllItemLink(
    ) {
        this._itemLinkList.clear();
    }

    /**
     * Method removeItemLink.
     * 
     * @param vItemLink
     * @return true if the object was removed from the collection.
     */
    public boolean removeItemLink(
            final java.lang.String vItemLink) {
        boolean removed = _itemLinkList.remove(vItemLink);
        return removed;
    }

    /**
     * Method removeItemLinkAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeItemLinkAt(
            final int index) {
        java.lang.Object obj = this._itemLinkList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Sets the value of field 'archive'.
     * 
     * @param archive the value of field 'archive'.
     */
    public void setArchive(
            final boolean archive) {
        this._archive = archive;
        this._has_archive = true;
    }

    /**
     * 
     * 
     * @param index
     * @param vItemLink
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setItemLink(
            final int index,
            final java.lang.String vItemLink)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._itemLinkList.size()) {
            throw new IndexOutOfBoundsException("setItemLink: Index value '" + index + "' not in range [0.." + (this._itemLinkList.size() - 1) + "]");
        }

        this._itemLinkList.set(index, vItemLink);
    }

    /**
     * 
     * 
     * @param vItemLinkArray
     */
    public void setItemLink(
            final java.lang.String[] vItemLinkArray) {
        //-- copy array
        _itemLinkList.clear();

        for (int i = 0; i < vItemLinkArray.length; i++) {
                this._itemLinkList.add(vItemLinkArray[i]);
        }
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.ItemSet
     */
    public static org.scrinch.model.castor.ItemSet unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.ItemSet) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.ItemSet.class, reader);
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
