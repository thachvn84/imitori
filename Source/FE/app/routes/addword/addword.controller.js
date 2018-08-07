//@ts-check

var http=require('http'),
    config=require('config');

/**
 * Post Route Controller
 * @param {object} router
 */
module.exports = (router) => {
    router.get("/resever",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const data = {
                message: "GET",
            };
            req.vueOptions.head.title = "Search Example";
            req.vueOptions.head.metas.push({charset: "utf-8"});
            req.vueOptions.head.scripts.push({
                src: "https://unpkg.com/axios/dist/axios.min.js",
            });
            res.renderVue("post/post.vue", data, req.vueOptions);
        },
    );

    router.post("/resever",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const data = {
                message: "POST",
                body: req.body,
            };
            var option = {
                host: config.service.host,
                port: config.service.port,
                path: config.paths.searchOneWord+"?word="+encodeURI(req.body.word)
            };
            console.log(option);
            http.get(option, function(resp) {
                var jsonStr = '';
                resp.on('data', function(data) {
                    jsonStr += data;
                });
                resp.on('end', function() {
                    res.send(jsonStr);
                });
                resp.on('error', function(e) {
                    console.error('Problem with request ${e.message}')
                });
            });
        },
    );
};
