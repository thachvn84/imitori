<template>
    <div>
        <p> {{word}} </p>
    </div>
</template>
<script>
export default {
    name: 'word-detail',
    props: {
        word: {
            type: String,
            default: ""
        },
        kana: {
            type: String,
            default: ""
        },
        romaji: {
            type: String,
            default: ""
        }
    }
};
</script>
<style>
</style>
