// @ts-check
const {SampleModel} = require("../models");
/**
 * Generates Todo's if empty
 * @param {object} req
 * @param {object} res
 * @param {object} next
 */
function generateSampleMiddleware(req, res, next) {
    if (!req.session.data) {
        let datas = [
            "One",
            "Two",
            "Three",
            "Four",
        ];
        req.session.datas = [];
        datas.forEach((element, key) => {
            let todo = new SampleModel({
                id: key,
                word: element,
                translated: false,
            });
            req.session.todos.push(todo);
        });
    }
    next();
}

module.exports = generateSampleMiddleware;
