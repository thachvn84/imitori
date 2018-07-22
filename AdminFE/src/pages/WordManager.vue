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

function analyzeWord(that) {
        var tmpWord = that;
        console.log(that);
    if (tmpWord.mean != null) {
      var wArr = tmpWord.mean.split(/\r?\n/);
      console.log("Warr: " + wArr);

      var i = 0;
      var wo = new Array();
      if (wArr.length > 0) {
        var tl = tmpWord.mean.split(/\r?\n/)[0].split(")");
        var wtl = tmpWord.mean.split(/\r?\n/)[0].split(")");
        var ttl = new Array();
        wo.push({ data: wtl.pop() });
        if (tl.length > 1) {
          tl = tl[0].split("(")[1].split(",");
          tl.forEach(element => {
            if (element.trim().length > 0) {
              ttl.push({ data: element.trim() });
              //console.log(element.trim());
            }
          });

          tmpWord.tl = ttl;
          //console.log(tmpWord.tl);
        }
      }
      if (wArr.length > 1) {
        for (i = 1; i < wArr.length; i++) {
          if (wArr[i].length > 0) {
            wo.push({ data: wArr[i] });
          }
        }
      }
      tmpWord.meanlist = wo;
      //console.log(tmpWord.meanlist)

      var res = [{ tl: tmpWord.tl }, { mean: tmpWord.meanlist }];
    } else {
      /*
        this.word = {
          word: "NewWord",
          kana: "NewWord",
          mean: "mean",
          meanlist: [{ data: "mean" }],
          tl: [{ data: "unknown type" }]
        };*/
    }

    return "";

    //Vue.http.get('https://dictionary.cambridge.org/search/english-vietnamese/direct/?q='+w).then(function(response) {
    //console.log(response.body.split("<span class=\"trans\" lang=\"vi\">")[1].split("</span>")[0]);
    //})
    }
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
        analyzeWord(that.resWord);
      })
    }
  }
}
</script>
<style>
</style>
