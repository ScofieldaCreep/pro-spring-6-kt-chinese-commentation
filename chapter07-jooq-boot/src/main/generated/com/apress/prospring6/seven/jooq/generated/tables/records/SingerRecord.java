/*
 * This file is generated by jOOQ.
 */
package com.apress.prospring6.seven.jooq.generated.tables.records;


import com.apress.prospring6.seven.jooq.generated.tables.Singer;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SingerRecord extends UpdatableRecordImpl<SingerRecord> implements Record6<Integer, Integer, String, String, LocalDate, byte[]> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>musicdb.SINGER.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>musicdb.SINGER.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>musicdb.SINGER.VERSION</code>.
     */
    public void setVersion(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>musicdb.SINGER.VERSION</code>.
     */
    public Integer getVersion() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>musicdb.SINGER.FIRST_NAME</code>.
     */
    public void setFirstName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>musicdb.SINGER.FIRST_NAME</code>.
     */
    public String getFirstName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>musicdb.SINGER.LAST_NAME</code>.
     */
    public void setLastName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>musicdb.SINGER.LAST_NAME</code>.
     */
    public String getLastName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>musicdb.SINGER.BIRTH_DATE</code>.
     */
    public void setBirthDate(LocalDate value) {
        set(4, value);
    }

    /**
     * Getter for <code>musicdb.SINGER.BIRTH_DATE</code>.
     */
    public LocalDate getBirthDate() {
        return (LocalDate) get(4);
    }

    /**
     * Setter for <code>musicdb.SINGER.PHOTO</code>.
     */
    public void setPhoto(byte[] value) {
        set(5, value);
    }

    /**
     * Getter for <code>musicdb.SINGER.PHOTO</code>.
     */
    public byte[] getPhoto() {
        return (byte[]) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, String, String, LocalDate, byte[]> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, Integer, String, String, LocalDate, byte[]> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Singer.SINGER.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Singer.SINGER.VERSION;
    }

    @Override
    public Field<String> field3() {
        return Singer.SINGER.FIRST_NAME;
    }

    @Override
    public Field<String> field4() {
        return Singer.SINGER.LAST_NAME;
    }

    @Override
    public Field<LocalDate> field5() {
        return Singer.SINGER.BIRTH_DATE;
    }

    @Override
    public Field<byte[]> field6() {
        return Singer.SINGER.PHOTO;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getVersion();
    }

    @Override
    public String component3() {
        return getFirstName();
    }

    @Override
    public String component4() {
        return getLastName();
    }

    @Override
    public LocalDate component5() {
        return getBirthDate();
    }

    @Override
    public byte[] component6() {
        return getPhoto();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getVersion();
    }

    @Override
    public String value3() {
        return getFirstName();
    }

    @Override
    public String value4() {
        return getLastName();
    }

    @Override
    public LocalDate value5() {
        return getBirthDate();
    }

    @Override
    public byte[] value6() {
        return getPhoto();
    }

    @Override
    public SingerRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public SingerRecord value2(Integer value) {
        setVersion(value);
        return this;
    }

    @Override
    public SingerRecord value3(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public SingerRecord value4(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public SingerRecord value5(LocalDate value) {
        setBirthDate(value);
        return this;
    }

    @Override
    public SingerRecord value6(byte[] value) {
        setPhoto(value);
        return this;
    }

    @Override
    public SingerRecord values(Integer value1, Integer value2, String value3, String value4, LocalDate value5, byte[] value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SingerRecord
     */
    public SingerRecord() {
        super(Singer.SINGER);
    }

    /**
     * Create a detached, initialised SingerRecord
     */
    public SingerRecord(Integer id, Integer version, String firstName, String lastName, LocalDate birthDate, byte[] photo) {
        super(Singer.SINGER);

        setId(id);
        setVersion(version);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setPhoto(photo);
    }

    /**
     * Create a detached, initialised SingerRecord
     */
    public SingerRecord(com.apress.prospring6.seven.jooq.generated.tables.pojos.Singer value) {
        super(Singer.SINGER);

        if (value != null) {
            setId(value.getId());
            setVersion(value.getVersion());
            setFirstName(value.getFirstName());
            setLastName(value.getLastName());
            setBirthDate(value.getBirthDate());
            setPhoto(value.getPhoto());
        }
    }
}
