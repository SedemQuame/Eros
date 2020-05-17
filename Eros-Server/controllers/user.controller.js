// jshint esversion:6
require('dotenv').config({ path: __dirname + './../.env' });

//====================================== requiring modules ===========================================//
// node modules
// const bcrypt = require('bcryptjs');
// const fs = require('fs');

// custom models
const user = require('../models/user.model').user;

//================================== creating HTTP handler methods ==================================//

// Creating & Deleting User Accounts.
// ==================================

exports.createNewUserAccount = (req, res) => {   
    
};

exports.deleteExistingAccount = (req, res) => {   
    
};

// Querying User Accounts
// ======================

exports.getAllUsers = (req, res) => {   
    
};

exports.getAllUsersWithMatchingPreferences = (req, res) => {   
    
};

// Person To Person Requests
// =========================


exports.requestMessageFromPossibleMatch = (req, res) => {   
    
};

exports.likePictureOfPossibleMatch = (req, res) => {   
    
};


// Manipulating Account Assets & Details
// =====================================

exports.deletePicturePostedOnPlatform = (req, res) => {   
    
};

exports.modifyUserName = (req, res) => {   
    
};

exports.modifyContactDetails = (req, res) => {   
    
};

exports.changeProfileImg = (req, res) => {   
    
};

exports.modifyPreferences = (req, res) => {   
    
};

exports.modifySocialBackground = (req, res) => {   
    
};