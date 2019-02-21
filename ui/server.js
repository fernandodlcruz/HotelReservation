const express = require('express');
const app = express();
const path = require('path');

app.use('/', express.static(__dirname +  '/'));

app.get('/*', function(req, res) {
  res.sendFile(path.join(__dirname + '/index.html'));
});

const hostname = 'localhost';
const port = 3000;

const server = app.listen(port, hostname, function () {
  console.log('Server running at http://${hostname}:${port}/');
});