// @ts-check

/**
 * @typedef SampleType
 * @prop {number} id
 * @prop {string} word
 * @prop {boolean} translated
 */

class SampleModel {
    /**
     * @constructor
     * @param {SampleType} data
     * @prop {number} id
     * @prop {string} word
     * @prop {boolean} [translated]
     */
    constructor(data) {
        this.id = data.id;
        this.word = data.word;
        this.translated = data.translated || false;
    }
}

module.exports = SampleModel;
