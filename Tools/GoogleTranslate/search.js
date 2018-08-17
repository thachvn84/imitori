const translate = require('google-translate-api');

var fs = require("fs");
var content = fs.readFileSync("unknown.txt", "utf8");

var lines = content.split("\n");
var start = 183624;
//159624
//163624
//167624
//171624
//175624
//179624
//183624
//
//
//
//
//
//
//
//
//



var count = 0;
for (var i = start; i < lines.length; i = i + 2) {
    if (lines[i] != "--OK--") {
        count++;
        translate(lines[i] + "【" + (i + 1).toString() + "】", { from: 'ja', to: 'vi' }).then(res => {
            console.log(res.text);
            return 0;
        }).catch(err => {
            //console.error(err);
            return 0;
        });
    }
    if (count > 2000) break;
}
console.log("next start: " + i.toString());
console.log("completed!----------------------------------")