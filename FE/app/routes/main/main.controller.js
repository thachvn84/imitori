//@ts-check

/**
 * Main Route Contoller
 * @param {object} router
 */
module.exports = (router) => {
    router.get("/",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const data = {
                title: "Imitori",
            };
            req.vueOptions.head.title = "Imitori";
            req.vueOptions.head.metas.push({charset: "utf-8"});
            console.log(req.vueOptions);
            res.renderVue("main/main.vue", data, req.vueOptions);
        },
    );
};
