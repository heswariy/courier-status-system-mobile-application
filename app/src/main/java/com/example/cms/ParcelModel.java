package com.example.cms;

public class ParcelModel {

    private int id;
    private String trackingNum;
    private String parcelStatus;
    private String senderName;
    private String senderEmail;
    private String senderAddressLine1;
    private String senderAddressLine2;
    private String senderPostcode;
    private String senderCity;
    private String senderState;
    private String senderContact;
    private String senderAltContact;
    private String recipientName;
    private String recipientEmail;
    private String recipientAddressLine1;
    private String recipientAddressLine2;
    private String recipientPostcode;
    private String recipientCity;
    private String recipientState;
    private String recipientContact;
    private String recipientAltContact;

    public ParcelModel(int id, String trackingNum, String parcelStatus,
                       String senderName, String senderEmail, String senderAddressLine1, String senderAddressLine2, String senderPostcode, String senderCity, String senderState, String senderContact, String senderAltContact,
                       String recipientName, String recipientEmail, String recipientAddressLine1, String recipientAddressLine2, String recipientPostcode, String recipientCity, String recipientState, String recipientContact, String recipientAltContact) {
        this.id = id;
        this.trackingNum = trackingNum;
        this.parcelStatus = parcelStatus;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderAddressLine1 = senderAddressLine1;
        this.senderAddressLine2 = senderAddressLine2;
        this.senderPostcode = senderPostcode;
        this.senderCity = senderCity;
        this.senderState = senderState;
        this.senderContact = senderContact;
        this.senderAltContact = senderAltContact;
        this.recipientName = recipientName;
        this.recipientEmail = recipientEmail;
        this.recipientAddressLine1 = recipientAddressLine1;
        this.recipientAddressLine2 = recipientAddressLine2;
        this.recipientPostcode = recipientPostcode;
        this.recipientCity = recipientCity;
        this.recipientState = recipientState;
        this.recipientContact = recipientContact;
        this.recipientAltContact = recipientAltContact;
    }

    public ParcelModel() {
    }

    @Override
    public String toString() {
        return "trackingNum: " + trackingNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackingNum() {
        return trackingNum;
    }

    public void setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
    }

    public String getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(String parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderAddressLine1() {
        return senderAddressLine1;
    }

    public void setSenderAddressLine1(String senderAddressLine1) {
        this.senderAddressLine1 = senderAddressLine1;
    }

    public String getSenderAddressLine2() {
        return senderAddressLine2;
    }

    public void setSenderAddressLine2(String senderAddressLine2) {
        this.senderAddressLine2 = senderAddressLine2;
    }

    public String getSenderPostcode() {
        return senderPostcode;
    }

    public void setSenderPostcode(String senderPostcode) {
        this.senderPostcode = senderPostcode;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public String getSenderContact() {
        return senderContact;
    }

    public void setSenderContact(String senderContact) {
        this.senderContact = senderContact;
    }

    public String getSenderAltContact() {
        return senderAltContact;
    }

    public void setSenderAltContact(String senderAltContact) {
        this.senderAltContact = senderAltContact;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientAddressLine1() {
        return recipientAddressLine1;
    }

    public void setRecipientAddressLine1(String recipientAddressLine1) {
        this.recipientAddressLine1 = recipientAddressLine1;
    }

    public String getRecipientAddressLine2() {
        return recipientAddressLine2;
    }

    public void setRecipientAddressLine2(String recipientAddressLine2) {
        this.recipientAddressLine2 = recipientAddressLine2;
    }

    public String getRecipientPostcode() {
        return recipientPostcode;
    }

    public void setRecipientPostcode(String recipientPostcode) {
        this.recipientPostcode = recipientPostcode;
    }

    public String getRecipientCity() {
        return recipientCity;
    }

    public void setRecipientCity(String recipientCity) {
        this.recipientCity = recipientCity;
    }

    public String getRecipientState() {
        return recipientState;
    }

    public void setRecipientState(String recipientState) {
        this.recipientState = recipientState;
    }

    public String getRecipientContact() {
        return recipientContact;
    }

    public void setRecipientContact(String recipientContact) {
        this.recipientContact = recipientContact;
    }

    public String getRecipientAltContact() {
        return recipientAltContact;
    }

    public void setRecipientAltContact(String recipientAltContact) {
        this.recipientAltContact = recipientAltContact;
    }
}
