// @ts-check
const jwt = require("jsonwebtoken");
const {SampleModel} = require("../../models");

/**
 * Decrypts Datas from hash
 * @param {string} token
 * @returns {SampleModel[]}
 */
function decryptData(token) {
    /** @type {SampleModel[]} */
    let todoArray = [];
    try {
        const decoded = jwt.verify(token, "shhhhh");
        // @ts-ignore
        if (decoded && typeof decoded === "object" && decoded.data) {
            // @ts-ignore
            const data = decoded.data;
            if (data && Array.isArray(data)) {
                data.forEach(element => {
                    todoArray.push(new SampleModel(element));
                });
            }
        } else {
            throw new Error("bad decode");
        }
        return todoArray;
    } catch (error) {
        throw error;
    }
}

module.exports = decryptData;
