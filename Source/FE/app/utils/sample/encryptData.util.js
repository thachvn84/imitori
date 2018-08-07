// @ts-check
const jwt = require("jsonwebtoken");
const {SampleModel} = require("../../models");

/**
 * Encrypts Datas from hash
 * @param {SampleModel[]} datas
 * @returns {string}
 */
function encryptData(datas) {
    const data = {
        exp: Math.floor(Date.now() / 1000) + (60 * 60),
        data: datas,
    };
    const token = jwt.sign(data, "shhhhh");
    return token;
}

module.exports = encryptData;
