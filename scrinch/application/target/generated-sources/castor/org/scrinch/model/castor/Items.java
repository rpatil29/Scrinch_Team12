/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class Items.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Items implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _itemList.
     */
    private java.util.List<org.scrinch.model.castor.Item> _itemList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Items() {
        super();
        this._itemList = new java.util.ArrayList<org.scrinch.model.castor.Item>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addItem(
            final org.scrinch.model.castor.Item vItem)
    throws java.lang.IndexOutOfBoundsException {
        this._itemList.add(vItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addItem(
            final int index,
            final org.scrinch.model.castor.Item vItem)
    throws java.lang.IndexOutOfBoundsException {
        this._itemList.add(index, vItem);
    }

    /**
     * Method enumerateItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.Item> enumerateItem(
    ) {
        return java.util.Collections.enumeration(this._itemList);
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

        if (obj instanceof Items) {

            Items temp = (Items)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._itemList != null) {
                if (temp._itemList == null) return false;
                if (this._itemList != temp._itemList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._itemList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._itemList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._itemList.equals(temp._itemList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemList);
                    }
                }
            } else if (temp._itemList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.Item at
     * the given index
     */
    public org.scrinch.model.castor.Item getItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._itemList.size()) {
            throw new IndexOutOfBoundsException("getItem: Index value '" + index + "' not in range [0.." + (this._itemList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.Item) _itemList.get(index);
    }

    /**
     * Method getItem.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.Item[] getItem(
    ) {
        org.scrinch.model.castor.Item[] array = new org.scrinch.model.castor.Item[0];
        return (org.scrinch.model.castor.Item[]) this._itemList.toArray(array);
    }

    /**
     * Method getItemCount.
     * 
     * @return the size of this collection
     */
    public int getItemCount(
    ) {
        return this._itemList.size();
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
        if (_itemList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_itemList)) {
           result = 37 * result + _itemList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_itemList);
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
     * Method iterateItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.Item> iterateItem(
    ) {
        return this._itemList.iterator();
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
    public void removeAllItem(
    ) {
        this._itemList.clear();
    }

    /**
     * Method removeItem.
     * 
     * @param vItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeItem(
            final org.scrinch.model.castor.Item vItem) {
        boolean removed = _itemList.remove(vItem);
        return removed;
    }

    /**
     * Method removeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.Item removeItemAt(
            final int index) {
        java.lang.Object obj = this._itemList.remove(index);
        return (org.scrinch.model.castor.Item) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setItem(
            final int index,
            final org.scrinch.model.castor.Item vItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._itemList.size()) {
            throw new IndexOutOfBoundsException("setItem: Index value '" + index + "' not in range [0.." + (this._itemList.size() - 1) + "]");
        }

        this._itemList.set(index, vItem);
    }

    /**
     * 
     * 
     * @param vItemArray
     */
    public void setItem(
            final org.scrinch.model.castor.Item[] vItemArray) {
        //-- copy array
        _itemList.clear();

        for (int i = 0; i < vItemArray.length; i++) {
                this._itemList.add(vItemArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.Items
     */
    public static org.scrinch.model.castor.Items unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Items) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Items.class, reader);
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
