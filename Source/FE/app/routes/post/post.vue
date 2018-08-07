<template>
    <div>
        <h3>{{message}}-request</h3>
        <form v-on:submit.prevent="sendData" accept-charset="utf-8">
            <div class="form-group">
                <label for="word">Content</label>
                <input id="word" type="text" name="word" v-model="word" autocomplete=""/>
            </div>
            <input type="submit"/>
        </form>
        <h3>Result:</h3>
        <pre>{{result}}</pre>
    </div>
</template>

<script>
    export default {
        data: function() {
            return {
                message: "",
                result: {},
                error: ""
            }
        },
        methods: {
            sendData: function() {
                const data = {
                    word: this.word,
                }
                axios.post("/post", data)
                    .then(result => {
                        this.result = result.data;
                    })
                    .catch(error => {
                        this.error = error.data;
                    })
            }
        }
    }
</script>

<style>
    div.form-group {
        padding: 5px;
    }

    div.form-group > * {
        padding: 5px;
    }

    div.form-group input {
        display: inline-block;
    }

</style>