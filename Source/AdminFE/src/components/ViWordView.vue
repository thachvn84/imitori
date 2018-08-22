<template>
<div class="viword-view">
    <div class="container-fuild">
        <div class="clearfix">
            <label class="px-2 vcenter" for="word.id"><kbd>ID: {{word.id}}</kbd></label>
            <button class="btn btn-primary m-1 btn-xs" type="submit" v-on:click="donothing"> Edit </button>
            <button class="btn btn-primary m-1 btn-xs float-right" type="submit" v-on:click="donothing"> Delete </button>
        </div>
        <div class="row more-space">
            <label class="col-sm-1 more-space vcenter"> Từ loại</label>
            <input class="form-control more-space text-center input-sm col-sm-2" type="text" for="word.tl" name="word" v-model="word.tl" autocomplete="" readonly/>
            <label class="col-sm-1 more-space vcenter"> Nghĩa</label>
            <input class="form-control more-space text-center input-sm col-sm-8" type="text" for="word.word" name="word" v-model="word.word" autocomplete="" readonly/>
        </div>
    </div>
</div>
</template>
<script>
import vueResource from "vue-resource";
import Vue from "vue";

Vue.use(vueResource);

export default {
    name: "viword-view",
    props: {
        word: ""
    },
    beforeMount: function () {},
    methods: {
        donothing: function () { console.log("Clicked")}

    }
};
</script>
<style>
</style>