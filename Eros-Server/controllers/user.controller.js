// jshint esversion:6
// require('dotenv').config({ path: __dirname + './../.env' });

//====================================== requiring modules ===========================================//
// node modules

// custom models
const user = require(`../models/user.models`).user;

//================================== creating HTTP handler methods ==================================//

// Creating & Deleting User Accounts.
// ==================================
// todo => optimise function.
exports.createNewUserAccount = (req, res) => {   
    user.create({
        name: `John Doe`,
        age: 10,
        aboutMe: {
            bio: req.params.bio,
            views: req.params.views,
        },
        preferences: {
            gender: req.params.gender,
            lookingFor: req.params.lookingFor,
            ageRange: req.params.ageRange,
        },
        socialBackground: {
            work: req.params.work,
            school: req.params.school,
            religion: req.params.religion,
        },
        contactInformation: {
            emailAddress: req.params.email,
            phoneNumber: req.params.phone,
        }
    }).then( docs => {
        res.send({
            msg: `Successfully created user account.`,
            // return id of user's mongo collection.
            _id: docs._id,
        })
    }).catch(err => {
        res.send({
            err: err
        })
    });
};

// todo => optimise function.
exports.deleteExistingAccount = (req, res) => {
    console.log(req.params.id);
    user.findByIdAndDelete(req.params.id)
        .then(
            res.send({
                msg: `Successfully deleted`
            })
        ).catch(
            res.send({
                msg: `Unable to successfully delete account.`
            })            
        );
};

// Querying User Accounts
// ======================
// todo => optimise function.
exports.getAllUsers = (req, res) => {   
    user.find({})
        .then(docs => {
            res.send({users: docs})
        }).catch(err => {
            res.send({msg: `Unable fetch user data. Try again later`})
        })
};

exports.getAllUsersWithMatchingPreferences = (req, res) => {   
    res.send({msg: `nothing to show.`})
};

// Person To Person Requests
// =========================
exports.requestMessageFromPossibleMatch = (req, res) => {   
    res.send({msg: `nothing to show.`})
};

exports.likePictureOfPossibleMatch = (req, res) => {   
    res.send({msg: `nothing to show.`})
};


// Manipulating Account Assets & Details
// =====================================

exports.deletePicturePostedOnPlatform = (req, res) => {   
    res.send({msg: `nothing to show.`})
};

exports.modifyUserName = (req, res) => {   
    res.send({msg: `nothing to show.`})
};

exports.modifyContactDetails = (req, res) => {   
    res.send({msg: `nothing to show.`})
};

exports.changeProfileImg = (req, res) => {   
    res.send({msg: `nothing to show.`})
};

exports.modifyPreferences = (req, res) => {   
    res.send({msg: `nothing to show.`})
};

exports.modifySocialBackground = (req, res) => {   
    res.send({msg: `nothing to show.`})
};