<template>
    <div class="container-fluid">
        <h3> {{SearchWordTitle}} </h3>
        <hr/>
        <div>
            <form accept-charset="utf-8">
                <div class="form-group">
                    <label class="px-2" for="word">Word:</label>
                    <input id="word" class="form-control" type="text" name="word" v-model="word" autocomplete=""/>
                </div>
                <input class="d-none" type="submit" v-on:click="queryWord" >
                <button class="btn btn-primary" type="submit" v-on:click="queryWord"> Search </button>
            </form>
        </div>
        <h4> Result </h4>
        <word-detail type="hover"
                    :word="resWord">

        </word-detail>
    </div>
</template>
<script>
import axios from 'axios';
import vueResource from 'vue-resource'
import Vue from 'vue'

Vue.use(vueResource);

import { WordDetail } from "@/components";

var tmpWord="";
var tmpKana="";
var tmpRomaji="";

export default {
  components: {
    WordDetail
  },
  props: {
    resWord: {
      type: Object,
      default: function() {
        return {
          word: '',
          kana: '',
          romaji: '',
          hanviet: '',
          tl: [{data: ""}],
          meanlist: [{data: ""}]}
      }
    }
  },
  data() {
    return {
      SearchWordTitle: "Search a Word:",
      word: ""
    };
  },

  methods: {
    queryWord: function(e) {
        e.preventDefault();

        var that = this;
        Vue.http.get('http://localhost:9001/dic/word/search?word='+this.word).then(function(response) {
        console.log(response.body);
        that.resWord.word = response.body.word;
        that.resWord.kana = response.body.kana;
        that.resWord.romaji = response.body.romaji;
        that.resWord.mean = response.body.mean;
      })
    }
  }
}
</script>
<style>
</style>
