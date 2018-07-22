<template>
    <div>
        <div class="border border-left-0 border-right-0 border-top-0">
            <p></p>
            <dt class="word_entry_title">
                <span class="kanji_kana display-7">
                <ruby>
                    <rb>{{word.word}}</rb>
                    <rp>/</rp>
                    <rt>{{word.kana}}</rt>
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
                            <div>
                                {{analyzeWord()}}
                            </div>
                            <label class="px-2" for="word.word">Word:</label>
                            <input id="word-edit" class="form-control" type="text" name="word" v-model="word.word" autocomplete=""/>
                            <label class="px-2" for="word.kana">kana:</label>
                            <input id="kana-edit" class="form-control" type="text" name="kana" v-model="word.kana" autocomplete=""/>
                            <br>
                            <label class="px-2" for="word.mean">Wordtype:</label>
                            <div class="container-fluid row">
                                <input v-for="tl in word.tl" class="form-control px-2 mt-1 col-sm-8" type="text" name="tl" v-model="tl.data" autocomplete=""/>
                                <button class="btn btn-primary ml-2" type="submit" v-on:click="addTl"> Add </button>
                            </div>
                            <br>
                            <label class="px-2"> Means:</label>
                            <div class="container-fluid row">
                                <input v-for="m in word.meanlist" class="form-control px-2 mt-1 col-sm-8" type="text" name="meanlist" v-model="m.data"/>
                                <button class="btn btn-primary ml-2" type="submit" v-on:click="addMean"> Add </button>
                            </div>

                        </div>
                        <input class="d-none" type="submit" v-on:click="disabledFunc" >
                        <button class="btn btn-primary" type="submit" v-on:click="saveWord"> Save </button>
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

export default {
  name: "word-detail",
  props: {
    word: {
      type: Object,
      default: ""
    }
  },
  methods: {
    hvDetect: function() {
      console.log(this.word.word);

      var res = "";
      var i = 0;
      for (i = 0; i < this.word.word.length; i++) {
        res += " " + hv.HVConverter(this.word.word.charAt(i)).toUpperCase();
      }

      return res;
    },
    disabledFunc: function() {},
    analyzeWord: function() {
      console.log(this.word.word);
      console.log(this.word.kana);
      if (this.word.mean != null) {
        var wArr = this.word.mean.split(/\r?\n/);

        var i = 0;
        var wo = new Array();
        if (wArr.length > 0) {
          var tl = this.word.mean.split(/\r?\n/)[0].split(")");
          var wtl = this.word.mean.split(/\r?\n/)[0].split(")");
          var ttl = new Array();
          wo.push({ data: wtl.pop() });
          if (tl.length > 1) {
            tl = tl[0].split("(")[1].split(",");
            tl.forEach(element => {
              if (element.trim().length > 0) {
                ttl.push({ data: element.trim() });
                console.log(element.trim());
              }
            });

            this.word.tl = ttl;
            console.log(this.word.tl);
          }
        }
        if (wArr.length > 1) {
          for (i = 1; i < wArr.length; i++) {
            if (wArr[i].length > 0) {
              wo.push({ data: wArr[i] });
            }
          }
        }
        this.word.meanlist = wo;
        console.log(this.word.meanlist);

        var res = [{ tl: this.word.tl }, { mean: this.word.meanlist }];
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
    },
    addTl: function(e) {
        e.preventDefault();
        this.word.tl.push({data: "newtl"});
    },
    addMean: function(e) {
        e.preventDefault();
        this.word.meanlist.push({data: "new mean"});
    },
    saveWord: function(e) {
      e.preventDefault();
      //this.word.meanlist.push({data: "new mean"});
      console.log(this.word.word);
      console.log(this.word.kana);
      var i = 0;
      for (i = 0; i < this.word.tl.length; i++) {
          console.log(this.word.tl[i].data);
      }
      for (i = 0; i < this.word.meanlist.length; i++) {
          console.log(this.word.meanlist[i].data);
      }
    }
  }
};
</script>
<style src="../assets/css/main.css"></style>
<style src="../assets/css/main1.css"></style>


