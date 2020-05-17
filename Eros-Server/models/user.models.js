//jshint esversion:6
// ===================================== requiring node modules ===================================== //
const mongoose = require('mongoose');

// ==================================== creating database schema=======================================//
const aboutMeSchema = mongoose.Schema({
    bio: { type: String},
    views: { type: String},
});

const socialBackgroundSchema = mongoose.Schema({
    work: {type: String},
    school: {type: String},
    religion: {type: String},
});

const contactInformationSchema = mongoose.Schema({
    emailAddress: {type: String},
    phoneNumber: {type: String},
});

const preferenceSchema = mongoose.Schema({
    gender: {type: String},
    lookingFor: {type: String},
    ageRange: {type: String},
});

const media = mongoose.Schema({
    assetUrl: {type: String},
    assetType: {type: String},
});

const notificationSchema = mongoose.Schema({
    from: {type: String},
    subject: {type: String},
    date: { type: Date, default: Date.now },
});


// Creating the user schema, using the above documents as a sub-schema.
const userSchema = mongoose.Schema({
    // personal information
    name: {type: String},
    age: {type: Number},
    profileImg: {type: String},
    aboutMe: aboutMeSchema,
    socialBackground: socialBackgroundSchema,
    contactInformation: contactInformationSchema,
    preferences: preferenceSchema,
    mediaList: [media],
    notifications: [notificationSchema],
});

// exporting user schema.
module.exports = {
    user: mongoose.model('user', userSchema),
    userSchema: userSchema
};
