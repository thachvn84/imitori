import DashboardLayout from "@/layout/dashboard/DashboardLayout.vue";
// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// Admin pages
import WordManager from "@/pages/WordManager.vue";
import Icons from "@/pages/Icons.vue";
import Overview from "@/pages/Overview.vue";


const routes = [{
        path: "/",
        component: DashboardLayout,
        mode: 'history',
        children: [{
                path: "",
                name: "Overview",
                component: Overview
            }, {
                path: "word",
                name: "word",
                component: WordManager
            },
            {
                path: "icons",
                name: "icons",
                component: Icons
            }
        ]
    },
    { path: "*", component: NotFound }
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;