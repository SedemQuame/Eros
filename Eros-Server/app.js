// jshint esversion: 8
require('dotenv').config();

//========================================= requiring modules ========================================//
const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');

// custom user modules
const db = require('./config/db.config');

// creating mongoose connection to db
mongoose.Promise = global.Promise;

const connectDB = async () => {
    mongoose.connect(db.url, db.options);
};

connectDB().then(() => {
    console.log('DB Connected....');
}).catch(() => {
    console.log('DB Not Connected....');
});

//creating app
const app = express();

// parse requests of content-type - application/x-www-form-urlencoded

// parse requests of content-type - application/json
app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({extended: false}));
app.use(fileupload(), bodyParser.urlencoded({ extended: true }));


// serving static files in express
app.use(express.static(__dirname + '/public'));


//====================================== requiring list routes ========================================//
require('./routes/user.route')(app);

// define a simple route
app.get('/', (req, res) => {
    res.redirect('/create');
});

// listening port
let port = process.env.PORT || 8080;
app.listen(port, function() {
    console.log(`Elector app started on port: ${port}`);
});