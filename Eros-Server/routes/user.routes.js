// jshint esversion:6
// ================================ creating list application route ===================================//
module.exports = app => {
    const user = require('../controllers/user.controller');

    //========================================== app users routes ============================================//
    app.route('/createNewUserAccount')
        .post(user.createNewAccount);

    app.route('/deleteExistingAccount')
        .post(user.deleteExistingAccount);

// ==================================================
    app.route('/getAllUsers')
        .post(user.getAllUsers);

    app.route('/getAllUsersWithMatchingPreferences')
        .post(user.getAllUsersWithMatchingPreferences);

// ==================================================
    app.route('/requestMessageFromPossibleMatch')
        .post(user.requestMessageFromPossibleMatch);

    app.route('/likePictureOfPossibleMatch')
        .post(user.likePictureOfPossibleMatch);
        
    app.route('/deletePicturePostedOnPlatform')
        .post(user.deletePicturePostedOnPlatform);

// ==================================================
// Modifying User Attributes.
    app.route('/modifyUserName')
        .post(user.modifyUserName);

    app.route('/modifyContactDetails')
        .post(user.modifyContactDetails);

    app.route('/changeProfileImg')
        .post(user.changeProfileImg);

    app.route('/modifyPreferences')
        .post(user.modifyPreferences);

    app.route('/modifySocialBackground')
        .post(user.modifySocialBackground);
};
