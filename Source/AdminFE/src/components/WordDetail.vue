<template>
<div>
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
                        <label class="px-2" for="word.word">Word:</label>
                        <input id="word-edit" class="form-control" type="text" name="word" v-model="word.word" autocomplete="" />
                        <label class="px-2" for="word.kana">kana:</label>
                        <input id="kana-edit" class="form-control" type="text" name="kana" v-model="word.furigana" autocomplete="" />
                        <div class="kanji-analyze">
                        <label class="text-primary h5 more-space text-center" for="word.mean">KANJI</label>
                            <div v-for="k in word.kanji" class="container-fluid">
                                <kanji-view type="hover" :kanji="k">
                                </kanji-view>
                            </div>
                        </div>
                        <button class="btn btn-primary ml-2" type="submit" v-on:click="donothing"> Add </button>
                        <label class="px-2"> Means:</label>
                        <div class="container-fluid row">
                        </div>

                    </div>
                    <input class="d-none" type="submit" v-on:click="donothing">
                    <button class="btn btn-primary" type="submit" v-on:click="donothing"> Save </button>
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
    KanjiView
} from "@/components";

export default {
    name: "word-detail",
    components: {
        'kanji-view':KanjiView
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
