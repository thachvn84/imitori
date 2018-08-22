<template>
<div class="word-detail">
    <label class="px-2" for="kanji.id"><kbd>ID: {{word.id}}</kbd></label>
    <div class="border border-left-0 border-right-0 border-top-0">
        <p></p>
        <dt class="word_entry_title">
                    <span class="kanji_kana display-7">
                    <ruby>
                        <rb>{{word.word}}</rb>
                        <rp>/</rp>
                        <rt>{{word.furigana}}</rt>
                        <rp>/</rp>
                    </ruby>
                    </span>
                    <span class="HanViet text-muted p-3 display-8">
                        「{{hvDetect()}}」
                    </span>
                </dt>
        <hr/>
        <div class="word-editor">
            <h3>Detail:</h3>
            <div>
                <form accept-charset="utf-8">
                    <div class="form-group">
                        <div class="row mr-2">
                            <label class="px-2 col-sm-1 vcenter" for="word.word">Từ</label>
                            <input id="word-edit" class="form-control col-sm-2 vcenter" type="text" name="word" v-model="word.word" autocomplete="" />
                            <label class="px-2 col-sm-1 vcenter" for="word.kana">Kana</label>
                            <input id="kana-edit" class="form-control col-sm-2 vcenter" type="text" name="kana" v-model="word.furigana" autocomplete="" />
                            <label class="px-2 col-sm-1 vcenter" for="word.kana">Romaji</label>
                            <input id="kana-edit" class="form-control col-sm-2 vcenter" type="text" name="kana" v-model="word.romaji" autocomplete="" />
                            <label class="px-2 col-sm-1 vcenter" for="word.kana">Từ loại</label>
                            <input id="kana-edit vcenter" class="form-control col-sm-2" type="text" name="kana" v-model="word.tl" autocomplete="" />
                        </div>
                        <div class="kanji-analyze" v-if="word.kanji != null && word.kanji.length > 0">
                            <label class="text-primary h5 more-space text-center" for="word.kanji">KANJI</label>
                            <div v-for="(k,index) in word.kanji"  :key=index class="container-fluid">
                                <kanji-view type="hover" :kanji="k">
                                </kanji-view>
                            </div>
                        </div>
                        <div class="trans-word" v-if="word.transword != null && word.transword.length > 0">
                            <label class="text-primary h5 more-space text-center" for="word.transword">Ý NGHĨA</label>
                            <div v-for="(tr,index) in word.transword"  :key=index class="container-fluid more-space">
                                <viword-view type="hover" :word = "tr">
                                </viword-view>
                            </div>
                        </div>
                        <div class="word-example" v-if="word.example != null && word.example.length > 0">
                            <label class="text-primary h5 more-space text-center" for="word.example">VÍ DỤ</label>
                            <div v-for="(ex,index) in word.example"  :key=index class="container-fluid">
                                <sentence-view type="hover" :sentence="ex">
                                </sentence-view>
                            </div>
                        </div>
                        <div class="similar-word" v-if="word.similarword != null && word.similarword.length > 0">
                            <label class="text-primary h5 more-space text-center" for="word.similarword">TỪ TƯƠNG ĐƯƠNG</label>
                            <div v-for="(w,index) in word.similarword" :key=index class="container-fluid">
                                <word-detail type="hover" :word="w">
                                </word-detail>
                            </div>
                        </div>
                        <div class="opposite-word" v-if="word.oppositeword != null && word.oppositeword.length > 0">
                            <label class="text-primary h5 more-space text-center" for="word.opposite">TỪ TRÁI NGHĨA</label>
                            <div v-for="(w,index) in word.oppositeword"  :key=index class="container-fluid">
                                <word-detail type="hover" :word="w">
                                </word-detail>
                            </div>
                        </div>
                        <div class="related-word" v-if="word.relatedword != null && word.relatedword.length > 0">
                            <label class="text-primary h5 more-space text-center" for="word.relateword">WORD CLOUD</label>
                            <div v-for="(w,index) in word.relatedword"  :key=index class="container-fluid">
                                <word-detail type="hover" :word="w">
                                </word-detail>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import * as hv from "../assets/js/hvconvert.js";
import vueResource from "vue-resource";
import Vue from "vue";

Vue.use(vueResource);
import {
    KanjiView,
    ViWordView,
    SentenceView,
} from "@/components";

export default {
    name: "word-detail",
    components: {
        'kanji-view':KanjiView,
        'sentence-view': SentenceView,
        'viword-view': ViWordView,
    },
    props: {
        word: {
            type: Object,
            default: ""
        }
    },
    beforeMount: function () {},
    methods: {
        hvDetect: function () {
            //console.log(this.word.word);

            var res = "";
            var i = 0;

            for (i = 0; i < this.word.word.length; i++) {
                res += " " + hv.HVConverter(this.word.word.charAt(i)).toUpperCase();
            }

            return res;
        },
        donothing: function () {

        }

    }
};
</script>

<style>

</style>

<style>

</style>
