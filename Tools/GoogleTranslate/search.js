const translate = require('google-translate-api');

var fs = require("fs");
var content = fs.readFileSync("retrans.txt", "utf8");

translate("感じ", { from: 'ja', to: 'vi' }).then(res => {
    console.log(res.text);
    return 0;
}).catch(err => {
    //console.error(err);
    return 0;
});

var lines = content.split("\n");

var step = lines.length / 10;
var block = 5;
var stop = step * (block + 1);
var start = (step * block % 2 == 0) ? step * block : step * block + 1
var stop = step * (block + 1);
start = Math.round(start);
stop = Math.round(stop);
console.log(start + "; " + stop);
for (var i = start; i < stop; i = i + 2) {
    if (lines[i] != "--OK--") {
        translate(lines[i] + "【" + (i + 1).toString() + "】", { from: 'ja', to: 'vi' }).then(res => {
            console.log(res.text);
            return 0;
        }).catch(err => {
            //console.error(err);
            return 0;
        });
    }
}
console.log("completed!----------------------------------")