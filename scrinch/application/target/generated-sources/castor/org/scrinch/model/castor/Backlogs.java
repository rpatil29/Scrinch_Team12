/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class Backlogs.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Backlogs implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _productBacklogList.
     */
    private java.util.List<org.scrinch.model.castor.ProductBacklog> _productBacklogList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Backlogs() {
        super();
        this._productBacklogList = new java.util.ArrayList<org.scrinch.model.castor.ProductBacklog>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vProductBacklog
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addProductBacklog(
            final org.scrinch.model.castor.ProductBacklog vProductBacklog)
    throws java.lang.IndexOutOfBoundsException {
        this._productBacklogList.add(vProductBacklog);
    }

    /**
     * 
     * 
     * @param index
     * @param vProductBacklog
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addProductBacklog(
            final int index,
            final org.scrinch.model.castor.ProductBacklog vProductBacklog)
    throws java.lang.IndexOutOfBoundsException {
        this._productBacklogList.add(index, vProductBacklog);
    }

    /**
     * Method enumerateProductBacklog.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.scrinch.model.castor.ProductBacklog> enumerateProductBacklog(
    ) {
        return java.util.Collections.enumeration(this._productBacklogList);
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

        if (obj instanceof Backlogs) {

            Backlogs temp = (Backlogs)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._productBacklogList != null) {
                if (temp._productBacklogList == null) return false;
                if (this._productBacklogList != temp._productBacklogList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._productBacklogList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._productBacklogList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._productBacklogList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._productBacklogList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._productBacklogList.equals(temp._productBacklogList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._productBacklogList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._productBacklogList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._productBacklogList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._productBacklogList);
                    }
                }
            } else if (temp._productBacklogList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getProductBacklog.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.scrinch.model.castor.ProductBacklog at the given index
     */
    public org.scrinch.model.castor.ProductBacklog getProductBacklog(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._productBacklogList.size()) {
            throw new IndexOutOfBoundsException("getProductBacklog: Index value '" + index + "' not in range [0.." + (this._productBacklogList.size() - 1) + "]");
        }

        return (org.scrinch.model.castor.ProductBacklog) _productBacklogList.get(index);
    }

    /**
     * Method getProductBacklog.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.scrinch.model.castor.ProductBacklog[] getProductBacklog(
    ) {
        org.scrinch.model.castor.ProductBacklog[] array = new org.scrinch.model.castor.ProductBacklog[0];
        return (org.scrinch.model.castor.ProductBacklog[]) this._productBacklogList.toArray(array);
    }

    /**
     * Method getProductBacklogCount.
     * 
     * @return the size of this collection
     */
    public int getProductBacklogCount(
    ) {
        return this._productBacklogList.size();
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
        if (_productBacklogList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_productBacklogList)) {
           result = 37 * result + _productBacklogList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_productBacklogList);
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
     * Method iterateProductBacklog.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.scrinch.model.castor.ProductBacklog> iterateProductBacklog(
    ) {
        return this._productBacklogList.iterator();
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
    public void removeAllProductBacklog(
    ) {
        this._productBacklogList.clear();
    }

    /**
     * Method removeProductBacklog.
     * 
     * @param vProductBacklog
     * @return true if the object was removed from the collection.
     */
    public boolean removeProductBacklog(
            final org.scrinch.model.castor.ProductBacklog vProductBacklog) {
        boolean removed = _productBacklogList.remove(vProductBacklog);
        return removed;
    }

    /**
     * Method removeProductBacklogAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.scrinch.model.castor.ProductBacklog removeProductBacklogAt(
            final int index) {
        java.lang.Object obj = this._productBacklogList.remove(index);
        return (org.scrinch.model.castor.ProductBacklog) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vProductBacklog
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setProductBacklog(
            final int index,
            final org.scrinch.model.castor.ProductBacklog vProductBacklog)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._productBacklogList.size()) {
            throw new IndexOutOfBoundsException("setProductBacklog: Index value '" + index + "' not in range [0.." + (this._productBacklogList.size() - 1) + "]");
        }

        this._productBacklogList.set(index, vProductBacklog);
    }

    /**
     * 
     * 
     * @param vProductBacklogArray
     */
    public void setProductBacklog(
            final org.scrinch.model.castor.ProductBacklog[] vProductBacklogArray) {
        //-- copy array
        _productBacklogList.clear();

        for (int i = 0; i < vProductBacklogArray.length; i++) {
                this._productBacklogList.add(vProductBacklogArray[i]);
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
     * @return the unmarshaled org.scrinch.model.castor.Backlogs
     */
    public static org.scrinch.model.castor.Backlogs unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Backlogs) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Backlogs.class, reader);
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
