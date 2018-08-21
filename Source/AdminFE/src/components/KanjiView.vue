<template>
<div class="kanji-detail">
    <div class="form-group">
        <label class="px-2" for="kanji.id"><kbd>ID: {{kanji.id}}</kbd></label>
        <div class="row">
            <div class="col-md-1  more-space">
                <label for="Kanji" v-on:click="donothing">Kanji</label>
                <input id="kanji-edit" class="form-control more-space text-center input-sm" type="text" name="kanji" v-model="kanji.kanji" autocomplete="" readonly/>
            </div>
            <div class="col-md-2  more-space">
                <label for="Furigana">Nghĩa</label>
                <div v-for="(on, index) in kanji.mean">
                    <input class="form-control more-space text-center input-sm" type="text" name="On" v-model="kanji.mean[index].word" autocomplete=""  readonly/>
                </div>
            </div>
            <div class="col-md-2 more-space">
                <label for="Klass">Bộ thủ</label>
                <div v-for="(on, index) in kanji.klass">
                    <input class="form-control more-space text-center input-sm" type="text" name="On" v-model="kanji.klass[index].klass" autocomplete=""  readonly/>
                </div>
            </div>
            <div class="col-md-1  more-space">
                <label for="Stroke">Số nét</label>
                <input id="stroke-edit" class="form-control more-space text-center input-sm" type="text" name="stroke" v-model="kanji.stroke" autocomplete=""  readonly/>
            </div>
            <div class="col-md-2  more-space">
                <label for="Furigana">Onyomi</label>
                <div v-for="(on, index) in kanji.onyomi">
                    <input class="form-control more-space text-center input-sm" type="text" name="On" v-model="kanji.onyomi[index]" autocomplete=""  readonly/>
                </div>
            </div>
            <div class="col-md-2  more-space">
                <label for="Furigana">Kunyomi</label>
                <div v-for="(on, index) in kanji.kunyomi">
                    <input class="form-control more-space text-center input-sm" type="text" name="On" autocomplete="" :value="kanji.kunyomi[index].kun + ( kanji.kunyomi[index].okuri.length > 0 ? ' (' + kanji.kunyomi[index].okuri + ')' : '')"  readonly></input>
                </div>
            </div>
            <div class="col-md-2  more-space">
                <label for="Furigana">Wordlist</label>
                <div v-for="(on, index) in kanji.wordlist">
                    <input class="form-control more-space text-center input-sm" type="text" name="On" v-model="kanji.wordlist[index].word" autocomplete=""  readonly/>
                </div>
            </div>
        </div>
        <input class="d-none" type="submit" v-on:click="donothing">
        <div class="row">
            <button class="btn btn-primary m-1" type="submit" v-on:click="donothing"> Edit </button>
            <button class="btn btn-primary m-1" type="submit" v-on:click="donothing"> Save </button>
        </div>
    </div>
</div>
</template>

<script>
import vueResource from "vue-resource";
import Vue from "vue";

Vue.use(vueResource);

export default {
    name: "kanji-view",
    props: {
        kanji: ""
    },
    beforeMount: function () {},
    methods: {
        donothing: function () { console.log("Clicked")}

    }
};
</script>

<style>

</style>

<style>

</style>
