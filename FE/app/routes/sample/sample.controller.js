//@ts-check
const {generateSample} = require("../../middleware");
const {SampleModel} = require("../../models");
const {sampleUtils} = require("../../utils");

/**
 * Main Route Contoller
 * @param {object} router
 */
module.exports = (router) => {
    router.get("/sample",
        generateSample,
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const data = {
                title: "Todos",
                todos: req.session.todos,
                showFooter: req.session.todos.length > 0,
            };
            req.vueOptions.head.title = "Todo";
            req.vueOptions.head.styles.push({
                src: "https://unpkg.com/todomvc-app-css@2.0.6/index.css",
            });
            req.vueOptions.head.scripts.push({
                src: "https://unpkg.com/axios/dist/axios.min.js",
            });

            if (req.query.filter) {
                switch (req.query.filter.toUpperCase()) {
                    case "COMPLETED":
                        data.todos = data.todos.filter(
                            /** @param {SampleModel} todo */
                            function(todo) {
                                return todo.translated;
                            },
                        );
                        data.showFooter = true;
                        break;
                    case "ACTIVE":
                        data.todos = data.todos.filter(
                            /** @param {SampleModel} todo */
                            function(todo) {
                                return !todo.translated;
                            },
                        );
                        data.showFooter = true;
                        break;
                }
            }
            if (req.query.share) {
                data.todos = sampleUtils.decrypt(req.query.share);
                data.showFooter = true;
            }

            res.renderVue("sample/sample.vue", data, req.vueOptions);
        },
    );

    router.post("/sample/update",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            req.session.todos = req.body;
            res.json({ok: true});
        },
    );

    router.post("/sample/share",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const encryptedString = sampleUtils.encrypt(req.body);
            res.json({link: `/todo?share=${encryptedString}`});
        },
    );
};
