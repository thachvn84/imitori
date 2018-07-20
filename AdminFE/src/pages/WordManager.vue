<template>
    <div class="container-fluid">
        <h3> {{SearchWordTitle}} </h3>
        <hr/>
        <div>
            <form v-on:submit.prevent="sendData" accept-charset="utf-8">
                <div class="form-group">
                    <label class="px-2" for="word">Word:</label>
                    <input id="word" class="form-control" type="text" name="word" v-model="word" autocomplete=""/>
                </div>
                <input class="d-none" type="submit" >
                <button class="btn btn-primary" type="submit"> Search </button>
            </form>
        </div>
        <h4> Result </h4>
        <word-detail type="hover" :word="strWord" 
                    :kana="strKana" 
                    :romaji="strRomaji">

        </word-detail>
    </div>
</template>
<script>
import { WordDetail } from "@/components";

var strWord ="A";
var strKana ="B";
var strRomaji ="C";
export default {
  components: {
    WordDetail
  },
  data() {
    return {
       SearchWordTitle: "Search a Word:"
    };
  }
};
</script>
<style>
</style>
