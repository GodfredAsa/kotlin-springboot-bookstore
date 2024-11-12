// COUNTRY SIGNATURE
export interface Country{
  name: string,
  isoCode: string
  callingCode: string,
  regionalInitial: string,
  regionLabel: string,
  timeZone: string,
  flag: string,
  callingCodeExcludeLeadingZero: boolean
}

// CURRENCY SIGNATURE
export interface Currency{
  name: string,
  initial: string,
  countryId: string, // country id after creation of country then added to this.
  majorDenomination: number, // 14.0
  minorDenomination: number // 13.0
}

// ADDRESS SIGNATURE

export interface GPSLocation{
  latitude: 5.563218,
  longitude: -0.1956351
}

export interface Address{
    city: String
    country: String,
    postalAddress: String,
    regionOrState: String,
    streetAddress: String,
    gpsLocation: GPSLocation // return the object
}

// MERCHANT SIGNATURE
export interface Merchant{
    name: string,
    address: Address, // * clarity of how json object is save in db in merchant
    categories: Object[], // list of object IDs
    countryCode: string, //"ZM",
    // these two attributes are they always true. // THESE VALUES SHOULD BE DEFAULTED TO TRUE WHEN CREATION AND NOT ADDED TO THE PAYLOAD.
    // verified: true,
    // active: true
}


// PRODUCT SIGNATURE
export interface Product{
type: "SERVICE_GOODS"
}

// ASSUMING THIS IS THE FINAL OBJECT SAY TASK-SERVICE
export interface TaskService{
  name: string,
  serviceType: string,
  serviceNumber: string,
  // above are an assumption
  country: Country,
  address: Address,
  merchant: Merchant
}



// SETTING UP A COUNTRY  => ZAMBIA  work on ONLY SEND MONEY
const Zambia = {
  "id": "sds",
  "name": "Zambia",
  "isoCode": "ZM",
  "callingCode": "+260",
  "regionInitial": "LS",
  "regionLabel": "Lusaka",
  "timeZone": "GMT",
  "flag": "/flag",
  "callingCodeExcludeLeadingZero": false
}

// REMITTANCE
const ZambiaRemittance = {

}
