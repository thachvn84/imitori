<template>
<div class="sentence-view">
    <div class="container-fluild">
        <div class="clearfix">
            <label class="px-2 vcenter" for="sentence.id"><kbd>ID: {{sentence.id}}</kbd></label>
            <button class="btn btn-primary m-1 btn-xs" type="submit" v-on:click="donothing"> Edit </button>
            <button class="btn btn-primary m-1 btn-xs float-right" type="submit" v-on:click="donothing"> Delete </button>
        </div>
        <div class="row more-space">
            <label class="col-sm-1 more-space vcenter">JA</label>
            <input class="form-control more-space text-center input-sm col-sm-11" type="text" for="word.tl" v-model="sentence.ja" autocomplete="" readonly/>
            <label class="col-sm-1 more-space vcenter">VI</label>
            <input class="form-control more-space text-center input-sm col-sm-11" type="text" for="word.tl" v-model="sentence.vi" autocomplete="" readonly/>
        </div>
    </div>
</div>
</template>
<script>
import vueResource from "vue-resource";
import Vue from "vue";

Vue.use(vueResource);

export default {
    name: "sentence-view",
    props: {
        sentence: ""
    },
    beforeMount: function () {},
    methods: {
        donothing: function () { console.log("Clicked")}

    }
};
</script>
<style>
</style>