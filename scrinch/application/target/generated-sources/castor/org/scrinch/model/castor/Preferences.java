/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class Preferences.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Preferences implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _burnUpChart.
     */
    private boolean _burnUpChart;

    /**
     * keeps track of state for field: _burnUpChart
     */
    private boolean _has_burnUpChart;


      //----------------/
     //- Constructors -/
    //----------------/

    public Preferences() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteBurnUpChart(
    ) {
        this._has_burnUpChart= false;
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

        if (obj instanceof Preferences) {

            Preferences temp = (Preferences)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._burnUpChart != temp._burnUpChart)
                return false;
            if (this._has_burnUpChart != temp._has_burnUpChart)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'burnUpChart'.
     * 
     * @return the value of field 'BurnUpChart'.
     */
    public boolean getBurnUpChart(
    ) {
        return this._burnUpChart;
    }

    /**
     * Method hasBurnUpChart.
     * 
     * @return true if at least one BurnUpChart has been added
     */
    public boolean hasBurnUpChart(
    ) {
        return this._has_burnUpChart;
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
        result = 37 * result + (_burnUpChart?0:1);

        return result;
    }

    /**
     * Returns the value of field 'burnUpChart'.
     * 
     * @return the value of field 'BurnUpChart'.
     */
    public boolean isBurnUpChart(
    ) {
        return this._burnUpChart;
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
     * Sets the value of field 'burnUpChart'.
     * 
     * @param burnUpChart the value of field 'burnUpChart'.
     */
    public void setBurnUpChart(
            final boolean burnUpChart) {
        this._burnUpChart = burnUpChart;
        this._has_burnUpChart = true;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.Preferences
     */
    public static org.scrinch.model.castor.Preferences unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.Preferences) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.Preferences.class, reader);
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
