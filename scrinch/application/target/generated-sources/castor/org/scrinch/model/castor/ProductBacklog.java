/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class ProductBacklog.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ProductBacklog implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _description.
     */
    private java.lang.String _description;

    /**
     * Field _startDate.
     */
    private org.exolab.castor.types.Date _startDate;

    /**
     * Field _endDate.
     */
    private org.exolab.castor.types.Date _endDate;

    /**
     * Field _maintenanceProductBacklog.
     */
    private boolean _maintenanceProductBacklog;

    /**
     * keeps track of state for field: _maintenanceProductBacklog
     */
    private boolean _has_maintenanceProductBacklog;

    /**
     * Field _itemSetList.
     */
    private java.util.List<org.scrinch.model.castor.ItemSet> _itemSetList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ProductBacklog() {
        super();
        this._itemSetList = new java.util.ArrayList<org.scrinch.model.castor.ItemSet>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vItemSet
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addItemSet(
            final org.scrinch.model.castor.ItemSet vItemSet)
    throws java.lang.IndexOutOfBoundsException {
        this._itemSetList.add(vItemSet);
    }

    /**
     * 
     * 
     * @param index
     * @param vItemSet
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addItemSet(
            final int index,
            final org.scrinch.model.castor.ItemSet vItemSet)
    throws java.lang.IndexOutOfBoundsException {
        this._itemSetList.add(index, vItemSet);
    }

    /**
     */
    public void deleteMaintenanceProductBacklog(
    ) {
        this._has_maintenanceProductBacklog= false;
    }

    /**
     * Method enumerateItemSet.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.ItemSet> enumerateItemSet(
    ) {
        return java.util.Collections.enumeration(this._itemSetList);
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

        if (obj instanceof ProductBacklog) {

            ProductBacklog temp = (ProductBacklog)obj;
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
            if (this._startDate != null) {
                if (temp._startDate == null) return false;
                if (this._startDate != temp._startDate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._startDate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._startDate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._startDate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._startDate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._startDate.equals(temp._startDate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._startDate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._startDate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._startDate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._startDate);
                    }
                }
            } else if (temp._startDate != null)
                return false;
            if (this._endDate != null) {
                if (temp._endDate == null) return false;
                if (this._endDate != temp._endDate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._endDate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._endDate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._endDate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._endDate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._endDate.equals(temp._endDate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._endDate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._endDate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._endDate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._endDate);
                    }
                }
            } else if (temp._endDate != null)
                return false;
            if (this._maintenanceProductBacklog != temp._maintenanceProductBacklog)
                return false;
            if (this._has_maintenanceProductBacklog != temp._has_maintenanceProductBacklog)
                return false;
            if (this._itemSetList != null) {
                if (temp._itemSetList == null) return false;
                if (this._itemSetList != temp._itemSetList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._itemSetList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._itemSetList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemSetList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemSetList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._itemSetList.equals(temp._itemSetList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemSetList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemSetList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._itemSetList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._itemSetList);
                    }
                }
            } else if (temp._itemSetList != null)
                return false;
            return true;
        }
        return false;
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
     * Returns the value of field 'endDate'.
     * 
     * @return the value of field 'EndDate'.
     */
    public org.exolab.castor.types.Date getEndDate(
    ) {
        return this._endDate;
    }

    /**
     * Method getItemSet.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.scrinch.model.castor.ItemSet at
     * the given index
     */
    public org.scrinch.model.castor.ItemSet getItemSet(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._itemSetList.size()) {
            throw new IndexOutOfBoundsException("getItemSet: Index value '" + index + "' not in range [0.." + (this._itemSetList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.ItemSet) _itemSetList.get(index);
    }

    /**
     * Method getItemSet.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.ItemSet[] getItemSet(
    ) {
        org.scrinch.model.castor.ItemSet[] array = new org.scrinch.model.castor.ItemSet[0];
        return (org.scrinch.model.castor.ItemSet[]) this._itemSetList.toArray(array);
    }

    /**
     * Method getItemSetCount.
     * 
     * @return the size of this collection
     */
    public int getItemSetCount(
    ) {
        return this._itemSetList.size();
    }

    /**
     * Returns the value of field 'maintenanceProductBacklog'.
     * 
     * @return the value of field 'MaintenanceProductBacklog'.
     */
    public boolean getMaintenanceProductBacklog(
    ) {
        return this._maintenanceProductBacklog;
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
     * Returns the value of field 'startDate'.
     * 
     * @return the value of field 'StartDate'.
     */
    public org.exolab.castor.types.Date getStartDate(
    ) {
        return this._startDate;
    }

    /**
     * Method hasMaintenanceProductBacklog.
     * 
     * @return true if at least one MaintenanceProductBacklog has
     * been added
     */
    public boolean hasMaintenanceProductBacklog(
    ) {
        return this._has_maintenanceProductBacklog;
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
        if (_description != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_description)) {
           result = 37 * result + _description.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_description);
        }
        if (_startDate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_startDate)) {
           result = 37 * result + _startDate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_startDate);
        }
        if (_endDate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_endDate)) {
           result = 37 * result + _endDate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_endDate);
        }
        result = 37 * result + (_maintenanceProductBacklog?0:1);
        if (_itemSetList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_itemSetList)) {
           result = 37 * result + _itemSetList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_itemSetList);
        }

        return result;
    }

    /**
     * Returns the value of field 'maintenanceProductBacklog'.
     * 
     * @return the value of field 'MaintenanceProductBacklog'.
     */
    public boolean isMaintenanceProductBacklog(
    ) {
        return this._maintenanceProductBacklog;
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
     * Method iterateItemSet.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.ItemSet> iterateItemSet(
    ) {
        return this._itemSetList.iterator();
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
    public void removeAllItemSet(
    ) {
        this._itemSetList.clear();
    }

    /**
     * Method removeItemSet.
     * 
     * @param vItemSet
     * @return true if the object was removed from the collection.
     */
    public boolean removeItemSet(
            final org.scrinch.model.castor.ItemSet vItemSet) {
        boolean removed = _itemSetList.remove(vItemSet);
        return removed;
    }

    /**
     * Method removeItemSetAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.ItemSet removeItemSetAt(
            final int index) {
        java.lang.Object obj = this._itemSetList.remove(index);
        return (org.scrinch.model.castor.ItemSet) obj;
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
     * Sets the value of field 'endDate'.
     * 
     * @param endDate the value of field 'endDate'.
     */
    public void setEndDate(
            final org.exolab.castor.types.Date endDate) {
        this._endDate = endDate;
    }

    /**
     * 
     * 
     * @param index
     * @param vItemSet
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setItemSet(
            final int index,
            final org.scrinch.model.castor.ItemSet vItemSet)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._itemSetList.size()) {
            throw new IndexOutOfBoundsException("setItemSet: Index value '" + index + "' not in range [0.." + (this._itemSetList.size() - 1) + "]");
        }

        this._itemSetList.set(index, vItemSet);
    }

    /**
     * 
     * 
     * @param vItemSetArray
     */
    public void setItemSet(
            final org.scrinch.model.castor.ItemSet[] vItemSetArray) {
        //-- copy array
        _itemSetList.clear();

        for (int i = 0; i < vItemSetArray.length; i++) {
                this._itemSetList.add(vItemSetArray[i]);
        }
    }

    /**
     * Sets the value of field 'maintenanceProductBacklog'.
     * 
     * @param maintenanceProductBacklog the value of field
     * 'maintenanceProductBacklog'.
     */
    public void setMaintenanceProductBacklog(
            final boolean maintenanceProductBacklog) {
        this._maintenanceProductBacklog = maintenanceProductBacklog;
        this._has_maintenanceProductBacklog = true;
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
     * Sets the value of field 'startDate'.
     * 
     * @param startDate the value of field 'startDate'.
     */
    public void setStartDate(
            final org.exolab.castor.types.Date startDate) {
        this._startDate = startDate;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * org.scrinch.model.castor.ProductBacklog
     */
    public static org.scrinch.model.castor.ProductBacklog unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.ProductBacklog) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.ProductBacklog.class, reader);
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
