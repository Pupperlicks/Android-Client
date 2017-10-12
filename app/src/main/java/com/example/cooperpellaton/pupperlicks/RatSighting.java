package com.example.cooperpellaton.pupperlicks;

/**
 * Created by Cooper Pellaton on 10/11/2017.
 * The representation of a rat object so that we can use Firebase UI.
 */

public class RatSighting {
    private String uniqueKey;
    private String createdDate;
    private String closedDate;
    private String agency;
    private String agencyName;
    private String complaint;
    private String complaintType;
    private String descriptor;
    private String locationType;
    private String incidentZip;
    private String incidentAddress;
    private String streetName;
    private String crossStreet1;
    private String crossStreet2;
    private String intersectionStreet1;
    private String intersectionStreet2;
    private String addressType;
    private String city;
    private String landmark;
    private String facilityType;
    private String status;
    private String dueDate;
    private String resolutionActionUpdatedDate;
    private String communityBoard;
    private String borough;
    private String xCoordinateStatePlane;
    private String yCoordinateStatePlane;
    private String parkFacilityName;
    private String parkBorough;
    private String schoolName;
    private String schoolNumber;
    private String schoolRegion;
    private String schoolCode;
    private String schoolPhoneNumber;
    private String schoolAddress;
    private String schoolCity;
    private String schoolState;
    private String schoolZip;
    private String schoolNotFound;
    private String schoolOrCityWideComplaint;
    private String vehicleType;
    private String taxiCompanyBorough;
    private String taxiPickupLocation;
    private String bridgeHighwayLocation;
    private String bridgeHighwayName;
    private String bridgeHighwayDirection;
    private String roadRamp;
    private String bridgeHighwaySegment;
    private String garageLotName;
    private String ferryDirection;
    private String ferryTerminalName;
    private String latitude;
    private String longitude;
    private String location;

    public RatSighting(String uniqueKey, String createdDate, String closedDate, String agency, String agencyName, String complaint, String complaintType, String descriptor, String locationType, String incidentZip, String incidentAddress, String streetName, String crossStreet1, String crossStreet2, String intersectionStreet1, String intersectionStreet2, String addressType, String city, String landmark, String facilityType, String status, String dueDate, String resolutionActionUpdatedDate, String communityBoard, String borough, String xCoordinateStatePlane, String yCoordinateStatePlane, String parkFacilityName, String parkBorough, String schoolName, String schoolNumber, String schoolRegion, String schoolCode, String schoolPhoneNumber, String schoolAddress, String schoolCity, String schoolState, String schoolZip, String schoolNotFound, String schoolOrCityWideComplaint, String vehicleType, String taxiCompanyBorough, String taxiPickupLocation, String bridgeHighwayLocation, String bridgeHighwayName, String bridgeHighwayDirection, String roadRamp, String bridgeHighwaySegment, String garageLotName, String ferryDirection, String ferryTerminalName, String latitude, String longitude, String location) {
        this.uniqueKey = uniqueKey;
        this.createdDate = createdDate;
        this.closedDate = closedDate;
        this.agency = agency;
        this.agencyName = agencyName;
        this.complaint = complaint;
        this.complaintType = complaintType;
        this.descriptor = descriptor;
        this.locationType = locationType;
        this.incidentZip = incidentZip;
        this.incidentAddress = incidentAddress;
        this.streetName = streetName;
        this.crossStreet1 = crossStreet1;
        this.crossStreet2 = crossStreet2;
        this.intersectionStreet1 = intersectionStreet1;
        this.intersectionStreet2 = intersectionStreet2;
        this.addressType = addressType;
        this.city = city;
        this.landmark = landmark;
        this.facilityType = facilityType;
        this.status = status;
        this.dueDate = dueDate;
        this.resolutionActionUpdatedDate = resolutionActionUpdatedDate;
        this.communityBoard = communityBoard;
        this.borough = borough;
        this.xCoordinateStatePlane = xCoordinateStatePlane;
        this.yCoordinateStatePlane = yCoordinateStatePlane;
        this.parkFacilityName = parkFacilityName;
        this.parkBorough = parkBorough;
        this.schoolName = schoolName;
        this.schoolNumber = schoolNumber;
        this.schoolRegion = schoolRegion;
        this.schoolCode = schoolCode;
        this.schoolPhoneNumber = schoolPhoneNumber;
        this.schoolAddress = schoolAddress;
        this.schoolCity = schoolCity;
        this.schoolState = schoolState;
        this.schoolZip = schoolZip;
        this.schoolNotFound = schoolNotFound;
        this.schoolOrCityWideComplaint = schoolOrCityWideComplaint;
        this.vehicleType = vehicleType;
        this.taxiCompanyBorough = taxiCompanyBorough;
        this.taxiPickupLocation = taxiPickupLocation;
        this.bridgeHighwayLocation = bridgeHighwayLocation;
        this.bridgeHighwayName = bridgeHighwayName;
        this.bridgeHighwayDirection = bridgeHighwayDirection;
        this.roadRamp = roadRamp;
        this.bridgeHighwaySegment = bridgeHighwaySegment;
        this.garageLotName = garageLotName;
        this.ferryDirection = ferryDirection;
        this.ferryTerminalName = ferryTerminalName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getIncidentZip() {
        return incidentZip;
    }

    public void setIncidentZip(String incidentZip) {
        this.incidentZip = incidentZip;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCrossStreet1() {
        return crossStreet1;
    }

    public void setCrossStreet1(String crossStreet1) {
        this.crossStreet1 = crossStreet1;
    }

    public String getCrossStreet2() {
        return crossStreet2;
    }

    public void setCrossStreet2(String crossStreet2) {
        this.crossStreet2 = crossStreet2;
    }

    public String getIntersectionStreet1() {
        return intersectionStreet1;
    }

    public void setIntersectionStreet1(String intersectionStreet1) {
        this.intersectionStreet1 = intersectionStreet1;
    }

    public String getIntersectionStreet2() {
        return intersectionStreet2;
    }

    public void setIntersectionStreet2(String intersectionStreet2) {
        this.intersectionStreet2 = intersectionStreet2;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getResolutionActionUpdatedDate() {
        return resolutionActionUpdatedDate;
    }

    public void setResolutionActionUpdatedDate(String resolutionActionUpdatedDate) {
        this.resolutionActionUpdatedDate = resolutionActionUpdatedDate;
    }

    public String getCommunityBoard() {
        return communityBoard;
    }

    public void setCommunityBoard(String communityBoard) {
        this.communityBoard = communityBoard;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getxCoordinateStatePlane() {
        return xCoordinateStatePlane;
    }

    public void setxCoordinateStatePlane(String xCoordinateStatePlane) {
        this.xCoordinateStatePlane = xCoordinateStatePlane;
    }

    public String getyCoordinateStatePlane() {
        return yCoordinateStatePlane;
    }

    public void setyCoordinateStatePlane(String yCoordinateStatePlane) {
        this.yCoordinateStatePlane = yCoordinateStatePlane;
    }

    public String getParkFacilityName() {
        return parkFacilityName;
    }

    public void setParkFacilityName(String parkFacilityName) {
        this.parkFacilityName = parkFacilityName;
    }

    public String getParkBorough() {
        return parkBorough;
    }

    public void setParkBorough(String parkBorough) {
        this.parkBorough = parkBorough;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getSchoolRegion() {
        return schoolRegion;
    }

    public void setSchoolRegion(String schoolRegion) {
        this.schoolRegion = schoolRegion;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolPhoneNumber() {
        return schoolPhoneNumber;
    }

    public void setSchoolPhoneNumber(String schoolPhoneNumber) {
        this.schoolPhoneNumber = schoolPhoneNumber;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolCity() {
        return schoolCity;
    }

    public void setSchoolCity(String schoolCity) {
        this.schoolCity = schoolCity;
    }

    public String getSchoolState() {
        return schoolState;
    }

    public void setSchoolState(String schoolState) {
        this.schoolState = schoolState;
    }

    public String getSchoolZip() {
        return schoolZip;
    }

    public void setSchoolZip(String schoolZip) {
        this.schoolZip = schoolZip;
    }

    public String getSchoolNotFound() {
        return schoolNotFound;
    }

    public void setSchoolNotFound(String schoolNotFound) {
        this.schoolNotFound = schoolNotFound;
    }

    public String getSchoolOrCityWideComplaint() {
        return schoolOrCityWideComplaint;
    }

    public void setSchoolOrCityWideComplaint(String schoolOrCityWideComplaint) {
        this.schoolOrCityWideComplaint = schoolOrCityWideComplaint;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getTaxiCompanyBorough() {
        return taxiCompanyBorough;
    }

    public void setTaxiCompanyBorough(String taxiCompanyBorough) {
        this.taxiCompanyBorough = taxiCompanyBorough;
    }

    public String getTaxiPickupLocation() {
        return taxiPickupLocation;
    }

    public void setTaxiPickupLocation(String taxiPickupLocation) {
        this.taxiPickupLocation = taxiPickupLocation;
    }

    public String getBridgeHighwayLocation() {
        return bridgeHighwayLocation;
    }

    public void setBridgeHighwayLocation(String bridgeHighwayLocation) {
        this.bridgeHighwayLocation = bridgeHighwayLocation;
    }

    public String getBridgeHighwayName() {
        return bridgeHighwayName;
    }

    public void setBridgeHighwayName(String bridgeHighwayName) {
        this.bridgeHighwayName = bridgeHighwayName;
    }

    public String getBridgeHighwayDirection() {
        return bridgeHighwayDirection;
    }

    public void setBridgeHighwayDirection(String bridgeHighwayDirection) {
        this.bridgeHighwayDirection = bridgeHighwayDirection;
    }

    public String getRoadRamp() {
        return roadRamp;
    }

    public void setRoadRamp(String roadRamp) {
        this.roadRamp = roadRamp;
    }

    public String getBridgeHighwaySegment() {
        return bridgeHighwaySegment;
    }

    public void setBridgeHighwaySegment(String bridgeHighwaySegment) {
        this.bridgeHighwaySegment = bridgeHighwaySegment;
    }

    public String getGarageLotName() {
        return garageLotName;
    }

    public void setGarageLotName(String garageLotName) {
        this.garageLotName = garageLotName;
    }

    public String getFerryDirection() {
        return ferryDirection;
    }

    public void setFerryDirection(String ferryDirection) {
        this.ferryDirection = ferryDirection;
    }

    public String getFerryTerminalName() {
        return ferryTerminalName;
    }

    public void setFerryTerminalName(String ferryTerminalName) {
        this.ferryTerminalName = ferryTerminalName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public RatSighting(){

    }




}
