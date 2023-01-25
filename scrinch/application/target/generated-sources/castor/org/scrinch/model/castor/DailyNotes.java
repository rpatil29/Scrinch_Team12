/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.scrinch.model.castor;

/**
 * Class DailyNotes.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class DailyNotes implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _format.
     */
    private java.lang.String _format = "text";

    /**
     * Field _encoding.
     */
    private java.lang.String _encoding;


      //----------------/
     //- Constructors -/
    //----------------/

    public DailyNotes() {
        super();
        setContent("");
        setFormat("text");
    }

    public DailyNotes(final java.lang.String defaultValue) {
        try {
            setContent( new java.lang.String(defaultValue));
         } catch(Exception e) {
            throw new RuntimeException("Unable to cast default value for simple content!");
         } 
    }


      //-----------/
     //- Methods -/
    //-----------/

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

        if (obj instanceof DailyNotes) {

            DailyNotes temp = (DailyNotes)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._content != null) {
                if (temp._content == null) return false;
                if (this._content != temp._content) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._content);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._content);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._content.equals(temp._content)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                    }
                }
            } else if (temp._content != null)
                return false;
            if (this._format != null) {
                if (temp._format == null) return false;
                if (this._format != temp._format) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._format);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._format);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._format); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._format); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._format.equals(temp._format)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._format);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._format);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._format);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._format);
                    }
                }
            } else if (temp._format != null)
                return false;
            if (this._encoding != null) {
                if (temp._encoding == null) return false;
                if (this._encoding != temp._encoding) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encoding);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encoding);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encoding.equals(temp._encoding)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding);
                    }
                }
            } else if (temp._encoding != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent(
    ) {
        return this._content;
    }

    /**
     * Returns the value of field 'encoding'.
     * 
     * @return the value of field 'Encoding'.
     */
    public java.lang.String getEncoding(
    ) {
        return this._encoding;
    }

    /**
     * Returns the value of field 'format'.
     * 
     * @return the value of field 'Format'.
     */
    public java.lang.String getFormat(
    ) {
        return this._format;
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
        if (_content != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_content)) {
           result = 37 * result + _content.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_content);
        }
        if (_format != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_format)) {
           result = 37 * result + _format.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_format);
        }
        if (_encoding != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encoding)) {
           result = 37 * result + _encoding.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encoding);
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
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final java.lang.String content) {
        this._content = content;
    }

    /**
     * Sets the value of field 'encoding'.
     * 
     * @param encoding the value of field 'encoding'.
     */
    public void setEncoding(
            final java.lang.String encoding) {
        this._encoding = encoding;
    }

    /**
     * Sets the value of field 'format'.
     * 
     * @param format the value of field 'format'.
     */
    public void setFormat(
            final java.lang.String format) {
        this._format = format;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.scrinch.model.castor.DailyNotes
     */
    public static org.scrinch.model.castor.DailyNotes unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.scrinch.model.castor.DailyNotes) org.exolab.castor.xml.Unmarshaller.unmarshal(org.scrinch.model.castor.DailyNotes.class, reader);
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
