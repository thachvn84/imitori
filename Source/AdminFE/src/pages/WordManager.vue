<template>
<div class="container-fluid">
    <h3> {{SearchWordTitle}} </h3>
    <hr/>
    <div>
        <form accept-charset="utf-8">
            <div class="form-group">
                <input id="word" class="form-control" type="text" name="word" v-model="word" autocomplete="" />
            </div>
            <input class="d-none" type="submit" v-on:click="queryWord">
            <button class="btn btn-primary" type="submit" v-on:click="queryWord"> Search </button>
        </form>
    </div>
    <div v-if="this.restype == 0">
        <h4> Result </h4>
        <word-detail type="hover" :word="resWord">

        </word-detail>
    </div>
</div>
</template>

<script>
import axios from 'axios';
import vueResource from 'vue-resource'
import Vue from 'vue'

Vue.use(vueResource);

import {
    WordDetail
} from "@/components";

var tmpWord = "";
var tmpKana = "";
var tmpRomaji = "";

export default {
    components: {
        WordDetail
    },
    props: {
        restype: 0,
        resWord: {
            type: Object,
            default: function () {
                return {
                    id: 0,
                    word: '',
                    kanji: [{
                        data: ''
                    }],
                    furigana: '',
                    romaji: '',
                    tl: '',
                    example: [{
                        data: ''
                    }],
                    similarword: [{
                        data: ''
                    }],
                    transword: [{
                        data: ''
                    }],
                    relatedword: [{
                        data: ''
                    }],
                    oppositeword: [{
                        data: ''
                    }]
                }
            }
        },
        data() {
            return {
                SearchWordTitle: "Search:",
                word: ""
            };
        },

        methods: {
            queryWord: function (e) {
                e.preventDefault();

                var that = this;
                Vue.http.get('http://localhost:9001/dic/word/search?word=' + this.word).then(function (response) {
                    console.log(response.body);
                    /*
                    switch (response.body.restype) {
                        case 0:
                            { //Single word
                                this.restype = 0;
                                that.resWord.id = response.body.id;
                                that.resWord.word = response.body.word;
                                that.resWord.kanji = response.body.kanji;
                                that.resWord.furigana = response.body.furigana;
                                that.resWord.romaji = response.body.romaji;
                                that.resWord.tl = response.body.tl;
                                that.resWord.example = response.body.example;
                                that.resWord.similarword = response.body.similarword;
                                that.resWord.transword = response.body.transword;
                                that.resWord.relatedword = response.body.relatedword;
                                that.resWord.oppositeword = response.body.oppositeword;
                            }
                            break;
                        case 1:
                            { //Multiword

                            }
                            break;
                        default:
                            { //Unknown res

                            }
                            break;
                    }*/

                });
            }
        }
    }
}
</script>

<style>

</style>
