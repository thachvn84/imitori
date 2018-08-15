const translate = require('google-translate-api');

var fs = require("fs");
var content = fs.readFileSync("unknownword.txt", "utf8");


var lines = content.split("\n");

for (var i = 0, len = lines.length; i < len; i = i + 2) {
    translate(lines[i] + "【" + (i + 1).toString() + "】", { from: 'ja', to: 'vi' }).then(res => {
        console.log(res.text);
        return 0;
    }).catch(err => {
        //console.error(err);
        return 0;
    });
}
console.log("completed!----------------------------------")