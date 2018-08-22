import SidebarPlugin from "./SidebarPlugin/index";
import Overview from "./Overview.vue";
import ViWordView from "./ViWordView.vue"
import SentenceView from "./SentenceView.vue"
import KanjiView from "./KanjiView.vue";
import WordDetail from "./WordDetail.vue";



let components = {
    SidebarPlugin,
    ViWordView,
    SentenceView,
    KanjiView,
    WordDetail,
    Overview
    
};

export default components;

export {
    SidebarPlugin,
    ViWordView,
    SentenceView,
    KanjiView,
    WordDetail,
    Overview,
    
};