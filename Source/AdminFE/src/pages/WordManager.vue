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
    <div v-if="restype == 1">
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
        'word-detail':WordDetail,
    },
    props: {
        resWord2: {
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
        }
    },
    data() {
        return {
            SearchWordTitle: "Search:",
            word: "",
            restype: 0,
            resWord: "",
        };
    },
    methods: {
        queryWord: function (e) {
            e.preventDefault();

            var that = this;
            Vue.http.get('http://localhost:9001/dic/word/search?word=' + this.word).then(function (response) {
                console.log(response.body);
                switch (response.body.restype) {
                    case 0:
                        { //Unregistered
                            that.restype = response.body.restype;
                        }
                        break;
                    case 1:
                        { //Multiword
                            that.restype = response.body.restype;
                            that.resWord = JSON.parse(response.body.data);
                            console.log(that.resWord);
                        }
                        break;
                    default:
                        { //Unknown res

                        }
                        break;
                }

            });
        }
    }
}
</script>

<style>

</style>
