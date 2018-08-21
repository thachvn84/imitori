<template>
<div class="kanji-detail">
    <div class="form-group">
        <label class="px-2" for="kanji.id">ID: {{kanji.id}}</label>
        <div class="row">
            <div class="col-md-2  more-space">
                <label for="Kanji">Kanji</label>
                <input id="kanji-edit" class="form-control more-space" type="text" name="kanji" v-model="kanji.kanji" autocomplete="" />
            </div>
            <div class="col-md-4  more-space">
                <label for="Furigana">Nghĩa</label>
                <input id="furi-edit" class="form-control more-space" type="text" name="furi" v-model="kanji.mean" autocomplete="" />
            </div>
            <div class="col-md-2  more-space">
                <label for="Klass">Bộ thủ</label>
                <input id="klass-edit" class="form-control more-space" type="text" name="klass" v-model="kanji.klass" autocomplete="" />
            </div>
            <div class="col-md-2  more-space">
                <label for="Stroke">Số nét</label>
                <input id="stroke-edit" class="form-control more-space" type="text" name="stroke" v-model="kanji.stroke" autocomplete="" />
            </div>
            <div class="col-md-2  more-space">
                <label for="Furigana">Onyomi</label>
                <div v-for="(on, index) in kanji.onyomi" class="container-fluid">
                     <input class="form-control more-space" type="text" name="On" v-model="kanji.onyomi[index]" autocomplete="" />
                </div>
            </div>
        </div>
        <input class="d-none" type="submit" v-on:click="donothing">
        <button class="btn btn-primary" type="submit" v-on:click="donothing"> Save </button>
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
        donothing: function () {}

    }
};
</script>

<style>

</style>

<style>

</style>
