<template>
    <div>
        <h1>{{title}}</h1>
        <p><a href="/post">Post request sample</a></p>
        <p><a href="/todo">Sample MVC</a></p>
    </div>
</template>

<script>
export default {
    data: function () {
        return {
            title: ''
        }
    }
}
</script>
